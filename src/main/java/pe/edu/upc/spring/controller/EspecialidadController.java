package pe.edu.upc.spring.controller;


import java.text.ParseException;

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

import pe.edu.upc.spring.entity.Especialidad;
import pe.edu.upc.spring.service.IEspecialidadService;

@Controller
@RequestMapping("/especialidad")
public class EspecialidadController {
	@Autowired
	private IEspecialidadService mService;
	
	@RequestMapping("/")
	public String irCategoria(Map<String, Object> model) {
		model.put("listaEspecialidades", mService.listar());
		return "listEspecialidad";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("especialidad", new Especialidad());
		return "especialidad";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Especialidad objCat, BindingResult binRes, Model model,RedirectAttributes attribute) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "especialidad";
		}
		else {
			boolean flag = mService.insertar(objCat);
			if (flag) {
				attribute.addFlashAttribute("success","Se registro exitosamente!");
				return "redirect:/especialidad/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/especialidad/irRegistrar";
			}
		}
	}
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Especialidad objCat = mService.listarId(id);
		
		if (objCat == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/especialidad/listar";
		}
		else {
			model.addAttribute("especialidad", objCat);
			return "especialidad";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				mService.eliminar(id);
				model.put("listaEspecialidades", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaEspecialidades", mService.listar());
		}
		return "listEspecialidad";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEspecialidades", mService.listar());
		return "listEspecialidad";
	}

	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Especialidad especialidad) 
	throws ParseException
	{
		mService.listarId(especialidad.getId_especialidad());
		return "listEspecialidad";
	}	
	
	/*@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Categoria categoria ) throws ParseException {
		List<Categoria> listaCategorias;
		categoria.setNombreCategoria(categoria.getNombreCategoria());
		listaCategorias = mService.buscarNombreCategoria(categoria.getNombreCategoria());
		
		if (listaCategorias.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaCategorias", listaCategorias);				
		return "buscar";
	}
	*/
	
	@PostMapping("/buscar")
	public String buscar2(
			@RequestParam(value = "txtnamesearch") String txtnamesearch,
			Model model) {
		List<Especialidad> listaEspecialidades;
		if (StringUtils.isEmpty(txtnamesearch)) {
			model.addAttribute("mensaje", "No existen resultados");
			listaEspecialidades = mService.listar();
		} else {
			listaEspecialidades = mService.buscarNombreEspecialidad(txtnamesearch);
		}
		
		if (listaEspecialidades.isEmpty()) {
			model.addAttribute("mensaje", "No existen resultados");
		}
		model.addAttribute("listaEspecialidades", listaEspecialidades);				
		return "listEspecialidad";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("especialidad", new Especialidad());
		return "buscar";
	}
	
}