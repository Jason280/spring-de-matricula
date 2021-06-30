package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import pe.edu.upc.spring.entity.C_Docente;
import pe.edu.upc.spring.entity.C_Usuario;

import pe.edu.upc.spring.service.IDocenteService;
import pe.edu.upc.spring.service.IEspecialidadService;
import pe.edu.upc.spring.service.IUsuarioService;
import pe.edu.upc.spring.serviceimpl.DocenteServiceImpl;

@Controller
@RequestMapping("/docente")
public class ControllerDocente {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private IDocenteService docenteService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IEspecialidadService eService;

	@RequestMapping("/")
	public String irDocente(Map<String, Object> model) {
		model.put("listaDocente", docenteService.listar());
		return "/docente/listDocente";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaEspecialidades",eService.listar());

		model.addAttribute("docente", new C_Docente());
		return "/docente/docente";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid C_Docente objDocente, BindingResult binRes, Model model, RedirectAttributes attribute)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "/docente/docente";
		} else {
			
			


			/*C_Usuario aux = new C_Usuario();
			aux.setUsername(objDocente.getUsuario().getUsername());
			aux.setPassword(objDocente.getUsuario().getPassword());
			aux.setEnabled(true);

			String bcryptPassword = passwordEncoder.encode(aux.getPassword());
			aux.setPassword(bcryptPassword);
			usuarioService.insertar(aux);

			aux = usuarioService.Buscar_Por_Nombre(objDocente.getUsuario().getUsername());

			usuarioService.insRol("ROLE_DOCENTE", aux.getId());

			objDocente.setUsuario(aux);
			*/
			boolean flag = docenteService.insertar(objDocente);
			if (flag) {
				return "redirect:/docente/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/docente/irRegistrar";
			}
			
		
			
			
			
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid C_Docente objDocente, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/docente/listar";
		} else {
			

		/*	C_Usuario aux = new C_Usuario();
			aux.setUsername(objDocente.getUsuario().getUsername());
			aux.setPassword(objDocente.getUsuario().getPassword());
			aux.setEnabled(true);

			String bcryptPassword = passwordEncoder.encode(aux.getPassword());
			aux.setPassword(bcryptPassword);
			usuarioService.insertar(aux);

			aux = usuarioService.Buscar_Por_Nombre(objDocente.getUsuario().getUsername());

			usuarioService.insRol("ROLE_DOCENTE", aux.getId());

			objDocente.setUsuario(aux);
			*/
			boolean flag = docenteService.insertar(objDocente);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/docente/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/docente/listar";
			}
		
			
			
		}
	}

	// El update
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir){
		C_Docente objDocente = docenteService.listar_Id(id);
		if (objDocente == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/docente/listar";
		}
		else {
			model.addAttribute("listaEspecialidades",eService.listar());
			//docenteService.eliminar(objDocente.getIdDocente());
			model.addAttribute("docente", objDocente);
			return "/docente/docente";

		}


	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				docenteService.eliminar(id);
				model.put("listaDocente", docenteService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar este elemento");
			model.put("listaDocente", docenteService.listar());

		}
		return "/docente/listDocente";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaDocente", docenteService.listar());
		return "/docente/listDocente";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute C_Docente docente) {

		docenteService.listar_Id(docente.getIdDocente());
		return "/docente/listDocente";

	}

	@PostMapping("/buscar")
	public String buscar2(
			@RequestParam(value = "txtnamesearch") String txtnamesearch,
			Model model) {
		List<C_Docente> listaDocente;
		if (StringUtils.isEmpty(txtnamesearch)) {
			model.addAttribute("mensaje", "No se encontraron resultados");
			listaDocente = docenteService.listar();
		} else {
			listaDocente = docenteService.buscar_Nombre(txtnamesearch);
		}
		
		if (listaDocente.isEmpty()) {
			model.addAttribute("mensaje", "No se encontraron resultados");
		}
		model.addAttribute("listaDocente", listaDocente);				
		return "/docente/listDocente";
	}
	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("/docente/docente", new C_Docente());
		return "buscar";

	}

}
