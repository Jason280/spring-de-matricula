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

import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.model.Docente;
import pe.edu.upc.spring.model.User;
import pe.edu.upc.spring.service.IAlumnoService;
import pe.edu.upc.spring.service.IUserService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	@Autowired
	private IAlumnoService mService;
	@Autowired
	private IUserService uService;
	
	@RequestMapping("/")
	public String irAlumno(Map<String, Object> model) {
		model.put("listaAlumnos", mService.listar());
		return "listAlumno";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaUser", uService.listar());
		model.addAttribute("user", new User());
		
		model.addAttribute("alumno", new Alumno());
		return "alumno";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Alumno objAlu, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaUser", uService.listar());
			
			return "alumno";
		}
		else {
			boolean flag = mService.insertar(objAlu);
			if (flag) {
				return "redirect:/alumno/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/alumno/irRegistrar";
			}
		}
	}
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Alumno objAlu = mService.listarId(id);
		
		if (objAlu == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/alumno/listar";
		}
		else {
			
			return "alumno";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				mService.eliminar(id);
				model.put("listaAlumnos", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaAlumnos", mService.listar());
		}
		return "listAlumno";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAlumnos", mService.listar());
		return "listAlumno";
	}

	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Alumno alumno) 
	throws ParseException
	{
		mService.listarId(alumno.getIdAlumno());
		return "listAlumno";
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Alumno alumno) throws ParseException {
		List<Alumno> listaAlumnos;
		alumno.setNombreAlumno(alumno.getNombreAlumno());
		listaAlumnos = mService.buscarNombreAlumno(alumno.getNombreAlumno());
		
		if (listaAlumnos.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaAlumnos", listaAlumnos);				
		return "buscar";
	}
	
	@PostMapping("/buscar")
	public String buscar2(
			@RequestParam(value = "txtnamesearch") String txtnamesearch,
			Model model) {
		List<Alumno> listaAlumnos;
		if (StringUtils.isEmpty(txtnamesearch)) {
			listaAlumnos = mService.listar();
		} else {
			listaAlumnos = mService.buscarNombreAlumno(txtnamesearch);
		}
		
		if (listaAlumnos.isEmpty()) {
			model.addAttribute("mensaje", "No se encontraron resultados");
		}
		model.addAttribute("listaAlumnos", listaAlumnos);				
		return "listAlumno";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "buscar";
	}
	
}