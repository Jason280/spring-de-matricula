package pe.edu.upc.spring.controller;


import java.text.ParseException;
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

import pe.edu.upc.spring.model.Docente;
import pe.edu.upc.spring.service.IDocenteService;

import pe.edu.upc.spring.model.Categoria;
import pe.edu.upc.spring.model.Curso;

import pe.edu.upc.spring.service.ICategoriaService;
import pe.edu.upc.spring.service.ICursoService;


@Controller
@RequestMapping("/curso")
public class CursoController {

	
	@Autowired
	private ICursoService mService;
	@Autowired
	private ICategoriaService cService;
	@Autowired
	private IDocenteService dService;
	

	@RequestMapping("/bienvenido")
	public String irJuegosBienvenido() {	
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irJuegos(Map<String, Object> model) {
		model.put("listaCursos", mService.listar());
		return "listCurso";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaDocentes", dService.listar());
		model.addAttribute("docente", new Docente());
		model.addAttribute("listaCategorias",cService.listar());
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("curso", new Curso());
		return "curso";
	}
		
	@RequestMapping("/registrar")
	public String registrar( @ModelAttribute @Valid Curso objCu, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaCategorias",cService.listar());
			model.addAttribute("listaDocentes", dService.listar());
			return "curso";
		}
		else {
			boolean flag = mService.insertar(objCu);
			if (flag) {
				return "redirect:/curso/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/curso/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Curso objCu = mService.listarId(id);
		
		if (objCu == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/curso/listar";
		}
		else {
			
			model.addAttribute("listaCategorias",cService.listar());
			model.addAttribute("listaDocentes", dService.listar());
			return "curso";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				mService.eliminar(id);
				model.put("listaCursos", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaCursos", mService.listar());
		}
		return "listCurso";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCursos", mService.listar());
		return "listCurso";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Curso curso) 
	throws ParseException
	{
		mService.listarId(curso.getIdCurso());
		return "listCurso";
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Curso curso ) throws ParseException {
		List<Curso> listaCurso;
		curso.setNombreCurso(curso.getNombreCurso());
		listaCurso = mService.buscarNombreCurso(curso.getNombreCurso());
		
		if (listaCurso.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaCursos", listaCurso);				
		return "buscar";
	}
	
	@PostMapping("/buscar")
	public String buscar2(
			@RequestParam(value = "txtnamesearch") String txtnamesearch,
			Model model) {
		List<Curso> listaCursos;
		if (StringUtils.isEmpty(txtnamesearch)) {
			listaCursos = mService.listar();
		} else {
			listaCursos = mService.buscarNombreCurso(txtnamesearch);
		}
		
		if (listaCursos.isEmpty()) {
			model.addAttribute("mensaje", "No se encontraron resultados");
		}
		model.addAttribute("listaCursos", listaCursos);				
		return "listCurso";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("curso", new Curso());
		return "buscar";
	}
	

}
