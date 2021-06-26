package pe.edu.upc.spring.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.User;
import pe.edu.upc.spring.service.IUserService;

@Controller
@RequestMapping
public class SecurityController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping(value = { "/login" })
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		if (principal != null) {

			flash.addFlashAttribute("Info", "Ya ha iniciado sesion anteriormente");
			return "redirect:/curso/bienvenido";
		}

		if (error != null) {

			model.addAttribute("error", "Error en el login:Nombre de usuario o contraseña incorrecto");
			return "login";
		}
		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesion con exito");
			return "login";
		}

		return "login";
	}

	@GetMapping(value = { "/createAccount" })
	public String createAccount() {
		return "createAccount";
	}	
	
	@PostMapping(value = { "/createAccount" })
	public String registerUser(@ModelAttribute @Valid User objUser, BindingResult binRes, Model model) {
		
		try {
			userService.registerUser(objUser);
		} catch (Exception e) {
			model.addAttribute("mensaje", e.getMessage());
			return "createAccount";
		}
		
		
		return "login";
	}	
	
}
