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


import pe.edu.upc.spring.entity.C_Curso;
import pe.edu.upc.spring.entity.C_Docente;
import pe.edu.upc.spring.entity.C_Docente_Curso;
import pe.edu.upc.spring.service.ICursoService;
import pe.edu.upc.spring.service.IDocenteService;
import pe.edu.upc.spring.service.IDocente_CursoService;

@Controller
@RequestMapping("/docente_curso")
public class ControllerDocente_Curso {

	@Autowired
	private IDocente_CursoService docente_cursoService;
	@Autowired
	private ICursoService cursoService;
	@Autowired
	private IDocenteService docenteService;

	// @Autowired
	// private IUsuarioService uService;
	@RequestMapping("/")
	public String ir_Alumno_curso(Map<String, Object> model) {
		model.put("listaDocente_cursos", docente_cursoService.listar());
		return "/docente_curso/listCursoxDocente";
	}

	@RequestMapping("/irRegistrar/{id}")
	public String ir_Registrar(@PathVariable int id, Model model) {

		model.addAttribute("listaCurso", cursoService.listar());
		model.addAttribute("listaDocente", docenteService.listar());
		C_Docente_Curso dp = new C_Docente_Curso();
		C_Docente p = docenteService.listar_Id(id);
		dp.setDocente(p);
		model.addAttribute("docente_curso", dp);
		model.addAttribute("docenteid", p.getIdDocente());
		return "/curso/registrocurso";
	}
	

	@RequestMapping("/seleccionar/{idDocente}/{idCurso}")
	public String seleccionar(@PathVariable("idDocente") int idDocente, @PathVariable("idCurso") int idCurso, Model model) {

		model.addAttribute("listaCurso", cursoService.listar());
		model.addAttribute("listaDocente", docenteService.listar());
		C_Docente_Curso dp = new C_Docente_Curso();
		C_Docente p = docenteService.listar_Id(idDocente);


		model.addAttribute("docenteid", idDocente);
		
		C_Curso art = cursoService.listar_Id(idCurso);
		dp.setCurso(art);
		dp.setDocente(p);
		model.addAttribute("docente_curso", dp);
		return "/curso/registrocurso";
	}



	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid C_Docente_Curso objDocente_curso, BindingResult binRes, Model model,RedirectAttributes attribute)
			throws ParseException {
		if (binRes.hasErrors()) {
			
			
			model.addAttribute("listaCurso", cursoService.listar());
			model.addAttribute("listaDocente", docenteService.listar());
			return "/curso/registrocurso";
		} else {
			
			boolean flag;
			
			C_Curso curso = cursoService.listar_Id(objDocente_curso.getCurso().getIdCurso());
			C_Docente docente = docenteService.listar_Id(objDocente_curso.getDocente().getIdDocente());
			int id = -1;
			int mat = 0;
			for (C_Docente_Curso dp : docente_cursoService.listar()) {
				if(dp.getDocente().getIdDocente() == docente.getIdDocente() &&
						dp.getCurso().getIdCurso() == curso.getIdCurso())
				{
					id = dp.getIdDocente_curso();
				}
				if(dp.getCurso().getIdCurso() == curso.getIdCurso())
				{
					mat += 1;
				}
			}
			
			model.addAttribute("docenteid", docente.getIdDocente());
			
			
			if(id != -1)
			{
				attribute.addFlashAttribute("warning","Ya estas registrado en este curso!");
				//model.addAttribute("mensaje", "Ya estas matriculado en este curso");
				return "redirect:/docente_curso/irRegistrar/" + docente.getIdDocente();
			
			}
			else if(mat>=10) {
				attribute.addFlashAttribute("error", "NO HAY VACANTES DISPONIBLES!");
				return "redirect:/docente_curso/irRegistrar/"+ docente.getIdDocente();
			}
			else
			{
				flag = docente_cursoService.insertar(objDocente_curso);
				
			}
			if (flag && id == -1) {
				attribute.addFlashAttribute("success","Se registro en este curso exitosamente!");
				return "redirect:/docente_curso/detalles/" + objDocente_curso.getDocente().getIdDocente();
			} else {
				
				attribute.addFlashAttribute("warning","Ocurrio un error!");
				return "redirect:/docente_curso/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/detalles/{id}")
	public String listar_Detalles(@PathVariable int id, Map<String, Object> model, RedirectAttributes objRedir) {

		
		C_Docente al = docenteService.listar_Id(id);

		model.put("docente", al);

		List<C_Curso> lc = new ArrayList<C_Curso>();
		List<C_Docente_Curso> lac = new ArrayList<C_Docente_Curso>();
		
	
		
		lac = docente_cursoService.listar();
		
		for (C_Docente_Curso dp : lac) {
			if(dp.getDocente().getIdDocente() == al.getIdDocente())
			{
				lc.add(dp.getCurso());
			}
		}
		
		model.put("docenteid", al.getIdDocente());
		model.put("listaCurso", lc);

		return "/curso/listCursoxDocente";
	
	
	}

	@RequestMapping("/dxc/{id}")
	public String listar_alumno_x_curso(@PathVariable int id, Map<String, Object> model, RedirectAttributes objRedir) {

		
		C_Curso c = cursoService.listar_Id(id);
		
		model.put("curso", c);

		List<C_Curso> lc = new ArrayList<C_Curso>();
		List<C_Docente> la = new ArrayList<C_Docente>();
		List<C_Docente_Curso> lac = new ArrayList<C_Docente_Curso>();
		List<C_Docente_Curso> aux = new ArrayList<C_Docente_Curso>();
		
		lac = docente_cursoService.listar();
		
		for (C_Docente_Curso dp : lac) {
			if(dp.getCurso().getIdCurso() == c.getIdCurso())
			{
				aux.add(dp);
			}
		}	
		
		model.put("mat", aux.size());
		model.put("cursoid", id);
		model.put("listaDC", aux);

		return "/curso/listDocentexCurso";
	
	
	}
	
	@RequestMapping("/cxd/{id}")
	public String listar_c_x_a(@PathVariable int id, Map<String, Object> model, RedirectAttributes objRedir) {

		
		C_Docente a = docenteService.listar_Id(id);
		
		model.put("docente", a);

		List<C_Curso> lc = new ArrayList<C_Curso>();
		List<C_Docente> la = new ArrayList<C_Docente>();
		List<C_Docente_Curso> lac = new ArrayList<C_Docente_Curso>();
		List<C_Docente_Curso> aux = new ArrayList<C_Docente_Curso>();
		
		lac = docente_cursoService.listar();
		
		for (C_Docente_Curso dp : lac) {
			if(dp.getDocente().getIdDocente() == a.getIdDocente())
			{
				aux.add(dp);
			}
		}	
		
		model.put("mat", aux.size());
		model.put("docenteid", id);
		model.put("listaDC", aux);

		return "/curso/listDC";
	
	
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid C_Docente_Curso objDocente_curso, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/docente_curso/listar";
		} else {

			model.addAttribute("listaCurso", cursoService.listar());
			model.addAttribute("listaDocente", docenteService.listar());
			boolean flag = docente_cursoService.modificar(objDocente_curso);

			if (flag) {
				objRedir.addFlashAttribute("success", "Se actualizó correctamente");
				return "redirect:/docente_curso/detalles/" + objDocente_curso.getDocente().getIdDocente();

			} else {
				objRedir.addFlashAttribute("warning", "Ocurrió un error");
				return "redirect:/docente_curso/detalles/" + objDocente_curso.getDocente().getIdDocente();
			}
		}
	}
	
	@RequestMapping(value = "/eliminardxc/{idCurso}/{idDocente}")
	public String eliminar_AXC(@PathVariable(value = "idCurso") Integer idCurso, @PathVariable("idDocente") int idDocente,
			RedirectAttributes flash) {

		C_Curso curso = cursoService.listar_Id(idCurso);
		C_Docente docente = docenteService.listar_Id(idDocente);
		
		int id = -1;
		for (C_Docente_Curso dp : docente_cursoService.listar()) {
			if(dp.getDocente().getIdDocente() == docente.getIdDocente() &&
					dp.getCurso().getIdCurso() == curso.getIdCurso())
			{
				id = dp.getIdDocente_curso();
			}
		}
		
		if (id > 0) {
			docente_cursoService.eliminar(id);
		}
		return "redirect:/docente_curso/detalles/" + docente.getIdDocente();
	}

	// El update
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		model.addAttribute("listaCurso", cursoService.listar());
		model.addAttribute("listaDocente", docenteService.listar());
		C_Docente_Curso objDocente_curso = docente_cursoService.listar_Id(id);
		if (objDocente_curso == null) {

			objRedir.addFlashAttribute("warning", "Ocurrió un error");
			return "redirect:/docente_curso/listar";
		} else {
			model.addAttribute("docente_curso", objDocente_curso);
			return "/curso/registrocurso";

		}

	}

	@RequestMapping(value = "/eliminar/{idCurso}/{idDocente}")
	public String eliminar(@PathVariable(value = "idCurso") Integer idCurso, @PathVariable("idDocente") int idDocente,
			RedirectAttributes flash) {

		C_Curso curso = cursoService.listar_Id(idCurso);
		C_Docente docente = docenteService.listar_Id(idDocente);
		
		int id = -1;
		for (C_Docente_Curso dp : docente_cursoService.listar()) {
			if(dp.getDocente().getIdDocente() == docente.getIdDocente() &&
					dp.getCurso().getIdCurso() == curso.getIdCurso())
			{
				id = dp.getIdDocente_curso();
			}
		}
		
		if (id > 0) {
			docente_cursoService.eliminar(id);
		}
		return "redirect:/docente_curso/detalles/" + docente.getIdDocente();
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
		model.put("listaDocente_cursos", docente_cursoService.listar());

		
		return "/curso/listCursoxDocente";

	}

	@RequestMapping("/listarId")
	public String listar_Id(Map<String, Object> model, @ModelAttribute C_Docente_Curso docente_curso) {

		docente_cursoService.listar_Id(docente_curso.getIdDocente_curso());
		return "/curso/listCursoxDocente";

	}



}
