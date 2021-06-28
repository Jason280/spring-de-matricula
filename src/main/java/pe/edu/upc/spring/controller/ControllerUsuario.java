package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.entity.C_Usuario;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class ControllerUsuario{

	
	@Autowired
	private IUsuarioService usuarioService;

	@RequestMapping("/")
	public String irUsuario(Map<String, Object> model) {
		model.put("listaUsuarios", usuarioService.listar());
		return "listUsuario";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {

		model.addAttribute("usuario", new C_Usuario());
		return "usuario";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid C_Usuario objUsuario, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "usuario";
		} else {
			
				boolean flag = usuarioService.insertar(objUsuario);
				
				if (flag) {
					return "redirect:/usuario/listar";
				} else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/usuario/irRegistrar";
				}
			
			
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid C_Usuario objUsuario, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/usuario/listar";
		} else {
			boolean flag = usuarioService.modificar(objUsuario);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/usuario/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/usuario/listar";
			}
		}
	}

	// El update
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		C_Usuario objUsuario = usuarioService.listar_Id(id);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/pet/listar";
		} else {
			model.addAttribute("usuario", objUsuario);
			return "usuario";

		}

	}

	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				usuarioService.eliminar(id);
				model.put("listaUsuarios", usuarioService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Raza asignada");
			model.put("listaUsuarios", usuarioService.listar());

		}
		return "listUsuario";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaUsuarios", usuarioService.listar());
		return "listUsuario";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute C_Usuario usuario) {

		usuarioService.listar_Id(usuario.getId());
		return "listUsuario";

	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute C_Usuario usuario) throws ParseException {

		List<C_Usuario> listaUsuarios;

		usuario.setUsername(usuario.getUsername());
		listaUsuarios = usuarioService.listar_x_Nombre(usuario.getUsername());

		if (listaUsuarios.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaUsuarios", listaUsuarios);
		return "buscar";

	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("usuario", new C_Usuario());
		return "buscar";

	}

}
