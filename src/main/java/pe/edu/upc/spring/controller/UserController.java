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

import pe.edu.upc.spring.model.User;

import pe.edu.upc.spring.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private IUserService uService;

	@RequestMapping("/")
	public String irUser(Map<String, Object> model) {
		model.put("listaUser", uService.listar());
		return "listUser";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}
		
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid User objUser, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "user";
		}
		else {
			boolean flag = uService.insert(objUser);
			if (flag) {
				return "redirect:/user/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/user/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Optional<User> objUser = uService.listarId(id);
		
		if (objUser == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/user/listar";
		}
		else {
			model.addAttribute("user", objUser);
			return "user";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				uService.delete(id);
				model.put("listaUser", uService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaUser", uService.listar());
		}
		return "listUser";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUser", uService.listar());
		return "listUser";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute User user) 
	throws ParseException
	{
		uService.listarId(user.getId());
		return "listUser";
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute User user ) throws ParseException {
		List<User> listaUser;
		user.setId(user.getId());
		listaUser = uService.buscarIdUser(user.getId());
		
		if (listaUser.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaUser", listaUser);				
		return "buscar";
	}
	
	
	@PostMapping("/buscar")
	public String buscar2(
			@RequestParam(value = "txtnamesearch") String txtnamesearch,
			Model model) {
		List<User> listaUser;
		if (StringUtils.isEmpty(txtnamesearch)) {
			listaUser = uService.listar();
		} else {
			listaUser = uService.buscarUsername(txtnamesearch);
		}
		
		if (listaUser.isEmpty()) {
			model.addAttribute("mensaje", "No se encontraron resultados");
		}
		model.addAttribute("listaUser", listaUser);				
		return "listUser";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("user", new User());
		return "buscar";
	}
	

}
