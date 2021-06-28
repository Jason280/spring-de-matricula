package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Alumno_Curso;
import pe.edu.upc.spring.entity.C_Curso;
import pe.edu.upc.spring.entity.C_Docente;
import pe.edu.upc.spring.entity.Categoria;
import pe.edu.upc.spring.service.IAlumnoService;
import pe.edu.upc.spring.service.IAlumno_CursoService;
import pe.edu.upc.spring.service.ICategoriaService;
import pe.edu.upc.spring.service.ICursoService;
import pe.edu.upc.spring.service.IDocenteService;
import pe.edu.upc.spring.service.IHorarioService;
import pe.edu.upc.spring.service.ISeccionService;

@Controller
@RequestMapping("/curso")
public class ControllerCurso{

	
	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IDocenteService docenteService;
	
	@Autowired
	private IAlumno_CursoService alumno_cursoService;
	
	@Autowired
	private IAlumnoService alumnoService;
	
	@Autowired
	private ICategoriaService cService;
	
	@Autowired
	private IHorarioService hService;
	@Autowired
	private ISeccionService sService;
	
	
	@RequestMapping("/")
	public String irCurso(Map<String, Object> model) {
		model.put("listaCursos", cursoService.listar());
		return "/curso/listCurso";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaSeccion",sService.listar());
		model.addAttribute("listaHorarios",hService.listar());
		model.addAttribute("listaCategorias",cService.listar());
		model.addAttribute("listaDocente", docenteService.listar());
		model.addAttribute("curso", new C_Curso());
		return "/curso/curso";
	}
	
	@RequestMapping("/seleccionar/{idDocente}")
	public String seleccionar(@PathVariable("idDocente") int idDocente, Model model) {

		model.addAttribute("listaDocente", docenteService.listar());
		C_Docente p = docenteService.listar_Id(idDocente);
		C_Curso dp = new C_Curso();
		dp.setDocente(p);
		model.addAttribute("curso", dp);
		model.addAttribute("listaHorarios",hService.listar());
		model.addAttribute("listaCategorias",cService.listar());
		model.addAttribute("listaSeccion",sService.listar());
		return "/curso/curso";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid C_Curso objCurso, BindingResult binRes, Model model
			, RedirectAttributes flash)
			throws ParseException {
		
		if (binRes.hasErrors()) {
			model.addAttribute("listaHorarios",hService.listar());
			model.addAttribute("listaCategorias",cService.listar());
			model.addAttribute("listaDocente", docenteService.listar());
			return "/curso/curso";
		} else {	

			boolean flag = cursoService.insertar(objCurso);
			if (flag) {
				return "redirect:/curso/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/curso/irRegistrar";
			}
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid C_Curso objCurso, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/curso/listar";
		} else {
			boolean flag = cursoService.modificar(objCurso);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/curso/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/curso/listar";
			}
		}
	}

	// El update
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		C_Curso objCurso = cursoService.listar_Id(id);
		if (objCurso == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/curso/listar";
		} else {
			model.addAttribute("listaHorarios",hService.listar());
			model.addAttribute("listaCategorias",cService.listar());
			model.addAttribute("listaSeccion",sService.listar());
			model.addAttribute("curso", objCurso);
			return "/curso/curso";

		}

	}

	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				cursoService.eliminar(id);
				model.put("listaCursos", cursoService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el elemento asignado");
			model.put("listaCursos", cursoService.listar());

		}
		return "/curso/listCurso";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaCursos", cursoService.listar());
		return "/curso/listCurso";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute C_Curso curso) {

		cursoService.listar_Id(curso.getIdCurso());
		return "/curso/listCurso";

	}

@PostMapping("/buscar")
	public String buscar2(
			@RequestParam(value = "txtnamesearch") String txtnamesearch,
			Model model) {
		List<C_Curso> listaCursos;
		if (StringUtils.isEmpty(txtnamesearch)) {
			model.addAttribute("mensaje", "No hay resultados");
			listaCursos = cursoService.listar();
		} else {
			listaCursos = cursoService.buscarNombreCategoria(txtnamesearch);
		}
		
		if (listaCursos.isEmpty()) {
			model.addAttribute("mensaje", "No hay resultados");
		}
		model.addAttribute("listaCursos", listaCursos);				
		return "/curso/listCurso";
	}
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("/curso/curso", new C_Curso());
		//model.addAttribute("listaCursos", cursoService.listar());
		//model.addAttribute("curso", new C_Curso());
		return "buscar";

	}

}
