package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import pe.edu.upc.spring.model.Alumno;

import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.model.Inscripcion;
import pe.edu.upc.spring.service.IAlumnoService;
import pe.edu.upc.spring.service.ICursoService;
import pe.edu.upc.spring.service.IInscripcionService;



@Controller
@RequestMapping("/inscripcion")
public class InscripcionController {

	
	@Autowired
	private IInscripcionService mService;
	@Autowired
	private IAlumnoService uService;
	@Autowired
	private ICursoService cService;
	
	@RequestMapping("/bienvenido")
	public String irArticuloBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irInscripcion(Map<String, Object> model) {
		model.put("listaInscripciones", mService.listar());
		return "listInscripcion";
	}
	
	@RequestMapping("/irRegistrar/{id}")
	public String irRegistrar(@PathVariable int id,Model model) {
		
		
		model.addAttribute("listaCursos",cService.listar());
		model.addAttribute("listaAlumnos",uService.listar());
		
		Inscripcion dp= new Inscripcion();
		Alumno alumno= uService.listarId(id);
		dp.setAlumno(alumno);
		
		
		model.addAttribute("inscripcion", new Inscripcion());
		model.addAttribute("alumnoid", alumno.getIdAlumno());
		
		return "inscripcion";
	}
	
	@RequestMapping("/seleccionar/{idAlumno}/{idCurso}")
	public String seleccionar(@PathVariable("idAlumno") int idAlumno, @PathVariable("idCurso") int idCurso, Model model) {

		model.addAttribute("listaCursos", cService.listar());
		model.addAttribute("listaAlumnos", uService.listar());
		Inscripcion dp = new Inscripcion();
		Alumno p = uService.listarId(idAlumno);


		model.addAttribute("alumnoid", idAlumno);
		
		Curso art = cService.listarId(idCurso);
		dp.setCurso(art);
		dp.setAlumno(p);
		model.addAttribute("inscripcion", dp);
		return "inscripcion";
	}
	
	
	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Inscripcion objIns, BindingResult binRes, Model model,RedirectAttributes attribute) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaAlumnos", uService.listar());
			model.addAttribute("listaCursos", cService.listar());
			
			return "inscripcion";
		}
		else {
			
			boolean flag;
			
			Curso curso = cService.listarId(objIns.getCurso().getIdCurso());
			Alumno alumno = uService.listarId(objIns.getAlumno().getIdAlumno());
			int id = -1;
			int mat = 0;
			for (Inscripcion dp : mService.listar()) {
				if(dp.getAlumno().getIdAlumno() == alumno.getIdAlumno() &&
						dp.getCurso().getIdCurso() == curso.getIdCurso())
				{
					id = dp.getIdInscripcion();
				}
				if(dp.getCurso().getIdCurso() == curso.getIdCurso())
				{
					mat += 1;
				}
			}
			
			model.addAttribute("alumnoid", alumno.getIdAlumno());
			
			
			if(id != -1||mat>=10)
			{
				attribute.addFlashAttribute("warning","Ya estas matriculado en este curso!");
				
			 return "redirect:/inscripcion/irRegistrar/" + alumno.getIdAlumno();
			
			}else
			{
				flag = mService.insertar(objIns);
				
			}
			if (flag && id == -1) {
				attribute.addFlashAttribute("success","Se registro en este curso exitosamente!");
				return "redirect:/inscripcion/detalles/" + objIns.getAlumno().getIdAlumno();
			} else {
				
				attribute.addFlashAttribute("warning","Ocurrio un error!");
				return "redirect:/inscripcion/irRegistrar";
			}
			
			
			
			
		}
	}
	
	@RequestMapping("/detalles/{id}")
	public String listar_Detalles(@PathVariable int id, Map<String, Object> model, RedirectAttributes objRedir) {

		
	Alumno al = uService.listarId(id);

		model.put("alumno", al);

		List<Curso> lc = new ArrayList<Curso>();
		List<Inscripcion> lac = new ArrayList<Inscripcion>();
		
	
		
		lac = mService.listar();
		
		for (Inscripcion dp : lac) {
			if(dp.getAlumno().getIdAlumno() == al.getIdAlumno())
			{
				lc.add(dp.getCurso());
			}
		}
		
		model.put("alumnoid", al.getIdAlumno());
		model.put("listaCursos", lc);

		return "listCursoxAlumno";
	
	
	}
	
	
	
	
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		model.addAttribute("listaAlumnos", uService.listar());
		model.addAttribute("listaCursos", cService.listar());
		Inscripcion objIns = mService.listar_Id(id);
		
		if (objIns == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/inscripcion/listar";
		}
		else {
			
			return "inscripcion";
		}
	}
	@RequestMapping("/eliminar/{idCurso}/{idAlumno}")
	public String eliminar(@PathVariable(value = "idCurso") Integer idCurso, @PathVariable("idAlumno") int idAlumno,
			RedirectAttributes flash) 
	{
		Curso curso= cService.listarId(idCurso);
		Alumno alumno= uService.listarId(idAlumno);
		int id=-1;
		for (Inscripcion dp : mService.listar()) {
			if(dp.getAlumno().getIdAlumno() == alumno.getIdAlumno() &&
					dp.getCurso().getIdCurso() == curso.getIdCurso())
			{
				id = dp.getIdInscripcion();
			}
		}
		
		if (id > 0) {
			mService.eliminar(id);
		}
		
		
		
		return "redirect:/inscripcion/detalles"+alumno.getIdAlumno();
	}
	
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaInscripciones", mService.listar());
		return "listInscripcion";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Inscripcion inscripcion) 
	throws ParseException
	{
		mService.listar_Id(inscripcion.getIdInscripcion());
		return "listInscripcion";
	}	
	
	/*@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Inscripcion inscripcion ) throws ParseException {
		List<Inscripcion> listaInscripciones;
		inscripcion.setNombreArticulo(inscripcion.getNombreArticulo());
		listaArticulos = mService.buscarNombre(articulo.getNombreArticulo());
		
		if (listaArticulos.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaArticulos", listaArticulos);				
		return "buscar";
	}
	*/
	
}