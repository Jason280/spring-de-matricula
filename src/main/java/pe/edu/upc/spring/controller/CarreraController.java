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

import pe.edu.upc.spring.entity.Carrera;
import pe.edu.upc.spring.service.ICarreraService;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
	@Autowired
	private ICarreraService mService;
	
	@RequestMapping("/")
	public String irCategoria(Map<String, Object> model) {
		model.put("listaCarreras", mService.listar());
		return "listCarrera";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("carrera", new Carrera());
		return "carrera";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Carrera objCat, BindingResult binRes, Model model,RedirectAttributes attribute) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "carrera";
		}
		else {
			boolean flag = mService.insertar(objCat);
			if (flag) {
				attribute.addFlashAttribute("success","Se registro exitosamente!");
				return "redirect:/carrera/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/carrera/irRegistrar";
			}
		}
	}
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Carrera objCat = mService.listarId(id);
		
		if (objCat == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/carrera/listar";
		}
		else {
			model.addAttribute("carrera", objCat);
			return "carrera";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				mService.eliminar(id);
				model.put("listaCarreras", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaCarreras", mService.listar());
		}
		return "listCarrera";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCarreras", mService.listar());
		return "listCarrera";
	}

	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Carrera carrera) 
	throws ParseException
	{
		mService.listarId(carrera.getId_Carrera());
		return "listCarrera";
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
	
}