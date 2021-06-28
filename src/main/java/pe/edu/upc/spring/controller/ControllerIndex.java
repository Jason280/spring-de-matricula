package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping(value = "")
public class ControllerIndex {
	

	//@Autowired
	//private IUsuarioService uService;
	
	@RequestMapping(value = "")
	public String index(Model model) {
		
		/*
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String name = principal.toString();
		if (principal instanceof UserDetails) {
			name = ((UserDetails) principal).getUsername();
		} else {
			name = principal.toString();
		}

		Usuario u = uService.BuscarPorNombre(name);
		
		model.addAttribute("username", u.getUsername());
		
		if(u.getUsername() != null)
		{
			if(u.getUsername().contains("admin"))
			{
				return "/admin/home";
			}
			else
			{
				return "home";
			}
		}
		else
		{
			return "login";
		}*/
		return "login";
	}
	
}
