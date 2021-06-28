package pe.edu.upc.spring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Docente;
import pe.edu.upc.spring.entity.C_Usuario;
import pe.edu.upc.spring.service.IAlumnoService;
import pe.edu.upc.spring.service.IDocenteService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller

@RequestMapping("/login")
public class ControllerLogin {


	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IAlumnoService aService;
	
	@Autowired
	private IDocenteService dService;

	//@RequestMapping("")
	@GetMapping("")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {
		
		if (error != null) {
			model.addAttribute("error","ERROR: USUARIO Y/O CONTRASEÑA ERRONEAS");
			return "login";
		}
		
		
		if (principal != null) {
			flash.addFlashAttribute("warning", "Ya ha inciado sesión anteriormente");
			return "redirect:/home";
		}

		/*if (error != null) {
			model.addAttribute("error","Usuario o contraseña invalido");
			//return "login";
		}*/

		if (logout != null) {
			flash.addFlashAttribute("success", "Ha cerrado sesión con éxito!");
			return "login";
		}
		
		return "login";

	}
	@RequestMapping("/home")
	public String home(Model model,RedirectAttributes flash) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String name = principal.toString();
		String roleName = "";
		if (principal instanceof UserDetails) {
			UserDetails userDetail = (UserDetails) principal;
			name = userDetail.getUsername();
				userDetail.getAuthorities().forEach(obj -> System.out.println(">>> role: " + obj.getAuthority()));
			roleName = userDetail.getAuthorities().stream()
					.map(obj -> obj.getAuthority())
					.findFirst().get();
			System.out.println("-------->" + roleName);
		} else {
			name = principal.toString();
		}
		
		if(roleName.equals("ROLE_USER")) {
		C_Usuario u = uService.Buscar_Por_Nombre(name);
		model.addAttribute("username", u.getUsername());
		
		u.getRoles().forEach(role -> System.out.println("from u -> " + role.getAuthority()));
		 C_Alumno a =aService.buscar_Alumno_por_Usuario(u);
		model.addAttribute("alumnoid", a.getIdAlumno());
		model.addAttribute("nombre", a.getNombre());
		return "home";
		}
		else if(roleName.equals("ROLE_DOCENTE")) {
			
			C_Usuario u = uService.Buscar_Por_Nombre(name);
			model.addAttribute("username", u.getUsername());

		/*C_Docente d = dService.buscar_Docente_por_Usuario(u);
			model.addAttribute("alumnoid", d.getIdDocente());
			model.addAttribute("nombre", d.getNombre());
			//model.addAttribute("success", "BIENVENID@ DOCENTE! "*/
			return "home2";
		}
		else {
			//model.addAttribute("success", "BIENVENID@ ADMIN !");
		 return "/admin/home";
		}
	}
		/*
		if(u.getUsername().contains("admin"))
		{
			return "/admin/home";
		}
		else if(u.getRoles().contains("ROLE_USER")){
		
			C_Alumno a = aService.buscar_Alumno_por_Usuario(u);
			model.addAttribute("alumnoid", a.getIdAlumno());
			model.addAttribute("nombre", a.getNombre());
			return "home";
		}
		else {
			C_Docente d = dService.buscar_Docente_por_Usuario(u);
			model.addAttribute("docenteid", d.getIdDocente());
			model.addAttribute("nombre", d.getNombre());
			return "home2";
		}
		

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String name = principal.toString();
		if (principal instanceof UserDetails) {
			name = ((UserDetails) principal).getUsername();
		} else {
			name = principal.toString();
		}

		C_Usuario u = uService.Buscar_Por_Nombre(name);
		model.addAttribute("username", u.getUsername());
	if(u.getUsername().contains("admin")){
		return "/admin/home";
	}
	else  {

		C_Docente d = dService.buscar_Docente_por_Usuario(u);
		model.addAttribute("alumnoid", d.getIdDocente());
		model.addAttribute("nombre", d.getNombre());
		return "home2";
		
	}
		
*/		
		

		@RequestMapping("/home2")
		public String home2(Model model) {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Object principal = auth.getPrincipal();
			String name = principal.toString();
			if (principal instanceof UserDetails) {
				name = ((UserDetails) principal).getUsername();
			} else {
				name = principal.toString();
			}

			C_Usuario u = uService.Buscar_Por_Nombre(name);
			model.addAttribute("username", u.getUsername());
			
		//if(u.getUsername().contains("admin")){
			return "/admin/home";
		}
		/*else  {

			C_Docente d = dService.buscar_Docente_por_Usuario(u);
			model.addAttribute("docenteid", d.getIdDocente());
			model.addAttribute("nombre", d.getNombre());
			return "home2";
			*/
		}


