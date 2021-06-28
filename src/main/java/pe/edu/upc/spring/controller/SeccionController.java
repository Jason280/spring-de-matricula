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

import pe.edu.upc.spring.entity.Seccion;

import pe.edu.upc.spring.service.ISeccionService;
import pe.edu.upc.spring.service.ISeccionService;

@Controller
@RequestMapping("/seccion")
public class SeccionController {
	@Autowired
	private ISeccionService mService;
	
	@RequestMapping("/")
	public String irCategoria(Map<String, Object> model) {
		model.put("listaSeccion", mService.listar());
		return "listSeccion";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("seccion", new Seccion());
		return "seccion";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Seccion objCat, BindingResult binRes, Model model,RedirectAttributes attribute) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "seccion";
		}
		else {
			boolean flag = mService.insertar(objCat);
			if (flag) {
				attribute.addFlashAttribute("success","Se registro exitosamente!");
				return "redirect:/seccion/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/seccion/irRegistrar";
			}
		}
	}
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Seccion objCat = mService.listarId(id);
		
		if (objCat == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/seccion/listar";
		}
		else {
			model.addAttribute("seccion", objCat);
			return "seccion";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				mService.eliminar(id);
				model.put("listaSeccion", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaSeccion", mService.listar());
		}
		return "listSeccion";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSeccion", mService.listar());
		return "listSeccion";
	}

	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Seccion seccion) 
	throws ParseException
	{
		mService.listarId(seccion.getId_seccion());
		return "listSeccion";
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
	
	/*@PostMapping("/buscar")
	public String buscar2(
			@RequestParam(value = "txtnamesearch") String txtnamesearch,
			Model model) {
		List<Carrera> listaCarreras;
		if (StringUtils.isEmpty(txtnamesearch)) {
			model.addAttribute("mensaje", "No se encontraron resultados");
			listaCarreras = mService.listar();
		} else {
			listaCarreras = mService.buscarNombreCarrera(txtnamesearch);
		}
		
		if (listaCarreras.isEmpty()) {
			model.addAttribute("mensaje", "No se encontraron resultados");
		}
		model.addAttribute("listaCarreras", listaCarreras);				
		return "listCarrera";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("carrera", new Carrera());
		return "buscar";
	}
	*/
}