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

import pe.edu.upc.spring.entity.Categoria;
import pe.edu.upc.spring.service.ICategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	private ICategoriaService mService;
	
	@RequestMapping("/")
	public String irCategoria(Map<String, Object> model) {
		model.put("listaCategorias", mService.listar());
		return "listCategoria";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "categoria";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Categoria objCat, BindingResult binRes, Model model,RedirectAttributes attribute) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "categoria";
		}
		else {
			boolean flag = mService.insertar(objCat);
			if (flag) {
				attribute.addFlashAttribute("success","OPERACION CORRECTA");
				return "redirect:/categoria/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/categoria/irRegistrar";
			}
		}
	}
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Categoria objCat = mService.listarId(id);
		
		if (objCat == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/categoria/listar";
		}
		else {
			model.addAttribute("categoria", objCat);
			return "categoria";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				mService.eliminar(id);
				model.put("listaCategorias", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaCategorias", mService.listar());
		}
		return "listCategoria";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCategorias", mService.listar());
		return "listCategoria";
	}

	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Categoria categoria) 
	throws ParseException
	{
		mService.listarId(categoria.getId_categoria());
		return "listCategoria";
	}	
	
	@RequestMapping("/buscar")
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
	
	@PostMapping("/buscar")
	public String buscar2(
			@RequestParam(value = "txtnamesearch") String txtnamesearch,
			Model model) {
		List<Categoria> listaCategorias;
		if (StringUtils.isEmpty(txtnamesearch)) {
			model.addAttribute("mensaje", "No existen resultados");
			listaCategorias = mService.listar();
		} else {
			listaCategorias = mService.buscarNombreCategoria(txtnamesearch);
		}
		
		if (listaCategorias.isEmpty()) {
			model.addAttribute("mensaje", "No existen resultados");
		}
		model.addAttribute("listaCategorias", listaCategorias);				
		return "listCategoria";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "buscar";
	}
	
}