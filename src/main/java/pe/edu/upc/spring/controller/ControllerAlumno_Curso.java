package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Alumno_Curso;
import pe.edu.upc.spring.entity.C_Curso;
import pe.edu.upc.spring.service.IAlumnoService;
import pe.edu.upc.spring.service.IAlumno_CursoService;
import pe.edu.upc.spring.service.ICursoService;

@Controller
@RequestMapping("/alumno_curso")
public class ControllerAlumno_Curso {

	@Autowired
	private IAlumno_CursoService alumno_cursoService;
	@Autowired
	private ICursoService cursoService;
	@Autowired
	private IAlumnoService alumnoService;

	// @Autowired
	// private IUsuarioService uService;
	@RequestMapping("/")
	public String ir_Alumno_curso(Map<String, Object> model) {
		model.put("listaAlumno_cursos", alumno_cursoService.listar());
		return "/alumno_curso/listCursoxAlumno";
	}

	@RequestMapping("/irRegistrar/{id}")
	public String ir_Registrar(@PathVariable int id, Model model) {

		model.addAttribute("listaCurso", cursoService.listar());
		model.addAttribute("listaAlumno", alumnoService.listar());
		C_Alumno_Curso dp = new C_Alumno_Curso();
		C_Alumno p = alumnoService.listar_Id(id);
		dp.setAlumno(p);
		model.addAttribute("alumno_curso", dp);
		model.addAttribute("alumnoid", p.getIdAlumno());
		return "/curso/matricula";
	}
	
	@RequestMapping("/seleccionar/{idAlumno}/{idCurso}")
	public String seleccionar(@PathVariable("idAlumno") int idAlumno, @PathVariable("idCurso") int idCurso, Model model) {

		model.addAttribute("listaCurso", cursoService.listar());
		model.addAttribute("listaAlumno", alumnoService.listar());
		C_Alumno_Curso dp = new C_Alumno_Curso();
		C_Alumno p = alumnoService.listar_Id(idAlumno);


		model.addAttribute("alumnoid", idAlumno);
		
		C_Curso art = cursoService.listar_Id(idCurso);
		dp.setCurso(art);
		dp.setAlumno(p);
		model.addAttribute("alumno_curso", dp);
		return "/curso/matricula";
	}

	

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid C_Alumno_Curso objAlumno_curso, BindingResult binRes, Model model,RedirectAttributes attribute)
			throws ParseException {
		if (binRes.hasErrors()) {
			
			
			model.addAttribute("listaCurso", cursoService.listar());
			model.addAttribute("listaAlumno", alumnoService.listar());
			return "/curso/matricula";
		} else {
			
			boolean flag;
			
			C_Curso curso = cursoService.listar_Id(objAlumno_curso.getCurso().getIdCurso());
			C_Alumno alumno = alumnoService.listar_Id(objAlumno_curso.getAlumno().getIdAlumno());
			int id = -1;
			int mat = 0;
			for (C_Alumno_Curso dp : alumno_cursoService.listar()) {
				if(dp.getAlumno().getIdAlumno() == alumno.getIdAlumno() &&
						dp.getCurso().getIdCurso() == curso.getIdCurso())
				{
					id = dp.getIdAlumno_curso();
				}
				if(dp.getCurso().getIdCurso() == curso.getIdCurso())
				{
					mat += 1;
				}
			}
			
			model.addAttribute("alumnoid", alumno.getIdAlumno());
			
			
			if(id != -1)
			{
				attribute.addFlashAttribute("warning","ERROR: YA TE ENCUENTRAS MATRICULADO EN ESTE CURSO");
				//model.addAttribute("mensaje", "Ya estas matriculado en este curso");
				return "redirect:/alumno_curso/irRegistrar/" + alumno.getIdAlumno();
			
			}
			else if(mat>=10) {
				attribute.addFlashAttribute("error", "ERROR: SUPERAS LA CANTIDAD DE VACANTES DISPONIBLES");
				return "redirect:/alumno_curso/irRegistrar/"+ alumno.getIdAlumno();
			}
			else
			{
				flag = alumno_cursoService.insertar(objAlumno_curso);
				
			}
			if (flag && id == -1) {
				attribute.addFlashAttribute("success","Registro correcto");
				return "redirect:/alumno_curso/detalles/" + objAlumno_curso.getAlumno().getIdAlumno();
			} else {
				
				attribute.addFlashAttribute("warning","Ocurrio un error!");
				return "redirect:/alumno_curso/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/detalles/{id}")
	public String listar_Detalles(@PathVariable int id, Map<String, Object> model, RedirectAttributes objRedir) {

		
		C_Alumno al = alumnoService.listar_Id(id);

		model.put("alumno", al);

		List<C_Curso> lc = new ArrayList<C_Curso>();
		List<C_Alumno_Curso> lac = new ArrayList<C_Alumno_Curso>();
		
	
		
		lac = alumno_cursoService.listar();
		
		for (C_Alumno_Curso dp : lac) {
			if(dp.getAlumno().getIdAlumno() == al.getIdAlumno())
			{
				lc.add(dp.getCurso());
			}
		}
		
		model.put("alumnoid", al.getIdAlumno());
		model.put("listaCurso", lc);

		return "/curso/listCursoxAlumno";
	
	
	}

	@RequestMapping("/axc/{id}")
	public String listar_alumno_x_curso(@PathVariable int id, Map<String, Object> model, RedirectAttributes objRedir) {

		
		C_Curso c = cursoService.listar_Id(id);
		
		model.put("curso", c);

		List<C_Curso> lc = new ArrayList<C_Curso>();
		List<C_Alumno> la = new ArrayList<C_Alumno>();
		List<C_Alumno_Curso> lac = new ArrayList<C_Alumno_Curso>();
		List<C_Alumno_Curso> aux = new ArrayList<C_Alumno_Curso>();
		
		lac = alumno_cursoService.listar();
		
		for (C_Alumno_Curso dp : lac) {
			if(dp.getCurso().getIdCurso() == c.getIdCurso())
			{
				aux.add(dp);
			}
		}	
		
		model.put("mat", aux.size());
		model.put("cursoid", id);
		model.put("listaAC", aux);

		return "/curso/listAlumnoxCurso";
	
	
	}
	
	@RequestMapping("/cxa/{id}")
	public String listar_c_x_a(@PathVariable int id, Map<String, Object> model, RedirectAttributes objRedir) {

		
		C_Alumno a = alumnoService.listar_Id(id);
		
		model.put("alumno", a);

		List<C_Curso> lc = new ArrayList<C_Curso>();
		List<C_Alumno> la = new ArrayList<C_Alumno>();
		List<C_Alumno_Curso> lac = new ArrayList<C_Alumno_Curso>();
		List<C_Alumno_Curso> aux = new ArrayList<C_Alumno_Curso>();
		
		lac = alumno_cursoService.listar();
		
		for (C_Alumno_Curso dp : lac) {
			if(dp.getAlumno().getIdAlumno() == a.getIdAlumno())
			{
				aux.add(dp);
			}
		}	
		
		model.put("mat", aux.size());
		model.put("alumnoid", id);
		model.put("listaAC", aux);

		return "/curso/listAC";
	
	
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid C_Alumno_Curso objAlumno_curso, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/alumno_curso/listar";
		} else {

			model.addAttribute("listaCurso", cursoService.listar());
			model.addAttribute("listaAlumno", alumnoService.listar());
			boolean flag = alumno_cursoService.modificar(objAlumno_curso);

			if (flag) {
				objRedir.addFlashAttribute("success", "Se actualizó correctamente");
				return "redirect:/alumno_curso/detalles/" + objAlumno_curso.getAlumno().getIdAlumno();

			} else {
				objRedir.addFlashAttribute("warning", "Ocurrió un error");
				return "redirect:/alumno_curso/detalles/" + objAlumno_curso.getAlumno().getIdAlumno();
			}
		}
	}
	
	@RequestMapping(value = "/eliminaraxc/{idCurso}/{idAlumno}")
	public String eliminar_AXC(@PathVariable(value = "idCurso") Integer idCurso, @PathVariable("idAlumno") int idAlumno,
			RedirectAttributes flash) {

		C_Curso curso = cursoService.listar_Id(idCurso);
		C_Alumno alumno = alumnoService.listar_Id(idAlumno);
		
		int id = -1;
		for (C_Alumno_Curso dp : alumno_cursoService.listar()) {
			if(dp.getAlumno().getIdAlumno() == alumno.getIdAlumno() &&
					dp.getCurso().getIdCurso() == curso.getIdCurso())
			{
				id = dp.getIdAlumno_curso();
			}
		}
		
		if (id > 0) {
			alumno_cursoService.eliminar(id);
		}
		return "redirect:/alumno_curso/detalles/" + alumno.getIdAlumno();
	}

	// El update
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		model.addAttribute("listaCurso", cursoService.listar());
		model.addAttribute("listaAlumno", alumnoService.listar());
		C_Alumno_Curso objAlumno_curso = alumno_cursoService.listar_Id(id);
		if (objAlumno_curso == null) {

			objRedir.addFlashAttribute("warning", "Ocurrió un error");
			return "redirect:/alumno_curso/listar";
		} else {
			model.addAttribute("alumno_curso", objAlumno_curso);
			return "/curso/matricula";

		}

	}

	@RequestMapping(value = "/eliminar/{idCurso}/{idAlumno}")
	public String eliminar(@PathVariable(value = "idCurso") Integer idCurso, @PathVariable("idAlumno") int idAlumno,
			RedirectAttributes flash) {

		C_Curso curso = cursoService.listar_Id(idCurso);
		C_Alumno alumno = alumnoService.listar_Id(idAlumno);
		
		int id = -1;
		for (C_Alumno_Curso dp : alumno_cursoService.listar()) {
			if(dp.getAlumno().getIdAlumno() == alumno.getIdAlumno() &&
					dp.getCurso().getIdCurso() == curso.getIdCurso())
			{
				id = dp.getIdAlumno_curso();
			}
		}
		
		if (id > 0) {
			alumno_cursoService.eliminar(id);
		}
		return "redirect:/alumno_curso/detalles/" + alumno.getIdAlumno();
	}


	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		/*
		 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 * Object principal = auth.getPrincipal(); String name = principal.toString();
		 * if(principal instanceof UserDetails) { name =
		 * ((UserDetails)principal).getUsername(); }else { name = principal.toString();
		 * }
		 * 
		 * Usuario u = uService.BuscarPorNombre(name); model.put("rol", u.getRoles());
		 */
		model.put("listaAlumno_cursos", alumno_cursoService.listar());

		
		return "/curso/listCursoxAlumno";

	}

	@RequestMapping("/listarId")
	public String listar_Id(Map<String, Object> model, @ModelAttribute C_Alumno_Curso alumno_curso) {

		alumno_cursoService.listar_Id(alumno_curso.getIdAlumno_curso());
		return "/curso/listCursoxAlumno";

	}



}
