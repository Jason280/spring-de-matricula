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

@Controller
@RequestMapping("/docente")
public class DocenteController {
	@Autowired
	private IDocenteService mService;
	
	@RequestMapping("/")
	public String irDocente(Map<String, Object> model) {
		model.put("listaDocentes", mService.listar());
		return "listDocente";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("docente", new Docente());
		return "docente";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Docente objDoc, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "docente";
		}
		else {
			boolean flag = mService.insertar(objDoc);
			if (flag) {
				return "redirect:/docente/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/docente/irRegistrar";
			}
		}
	}
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Optional<Docente> objDoc = mService.listarId(id);
		
		if (objDoc == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/docente/listar";
		}
		else {
			model.addAttribute("docente", objDoc);
			return "docente";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				mService.eliminar(id);
				model.put("listaDocentes", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaDocentes", mService.listar());
		}
		return "listDocente";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDocentes", mService.listar());
		return "listDocente";
	}

	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Docente docente) 
	throws ParseException
	{
		mService.listarId(docente.getIdDocente());
		return "listDocente";
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Docente  docente) throws ParseException {
		List<Docente> listaDocentes;
		docente.setNombreDocente(docente.getNombreDocente());
		listaDocentes = mService.buscarNombreDocente(docente.getNombreDocente());
		
		if (listaDocentes.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDocentes", listaDocentes);				
		return "buscar";
	}
	
	@PostMapping("/buscar")
	public String buscar2(
			@RequestParam(value = "txtnamesearch") String txtnamesearch,
			Model model) {
		List<Docente> listaDocentes;
		if (StringUtils.isEmpty(txtnamesearch)) {
			listaDocentes = mService.listar();
		} else {
			listaDocentes = mService.buscarNombreDocente(txtnamesearch);
		}
		
		if (listaDocentes.isEmpty()) {
			model.addAttribute("mensaje", "No se encontraron resultados");
		}
		model.addAttribute("listaDocentes", listaDocentes);				
		return "listDocente";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("docente", new Docente());
		return "buscar";
	}
	
}