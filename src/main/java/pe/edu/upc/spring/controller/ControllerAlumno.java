package pe.edu.upc.spring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Usuario;
import pe.edu.upc.spring.service.IAlumnoService;
import pe.edu.upc.spring.service.ICarreraService;
import pe.edu.upc.spring.service.IDocenteService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/alumno")
public class ControllerAlumno {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IAlumnoService alumnoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IDocenteService docenteService;

	@Autowired
	private ICarreraService mService;
	
	/*@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}*/
	

	@RequestMapping("/")
	public String irAlumno(Map<String, Object> model) {
		model.put("listaAlumnos", alumnoService.listar());
		return "/alumno/listAlumno";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaCarreras",mService.listar());
		model.addAttribute("alumno", new C_Alumno());
		//model.addAttribute("listadocente", docenteService.listar());
		return "/alumno/alumno";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid C_Alumno objAlumno, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto,RedirectAttributes attribute)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listadocente", docenteService.listar());
			attribute.addFlashAttribute("warning","Existen errores en el registro");
			return "/alumno/alumno";
		} else {

			C_Usuario aux = new C_Usuario();
			aux.setUsername(objAlumno.getUsuario().getUsername());
			aux.setPassword(objAlumno.getUsuario().getPassword());
			aux.setEnabled(true);

			String bcryptPassword = passwordEncoder.encode(aux.getPassword());
			aux.setPassword(bcryptPassword);
			usuarioService.insertar(aux);

			aux = usuarioService.Buscar_Por_Nombre(objAlumno.getUsuario().getUsername());

			usuarioService.insRol("ROLE_USER", aux.getId());

			objAlumno.setUsuario(aux);
			
				if(!foto.isEmpty()) {
				Path directorioImagenes=Paths.get("src//main//resources//static//img");
				String rutAbsoluta=directorioImagenes.toFile().getAbsolutePath();
					//String rutAbsoluta="C://Alumnos//Imagenes";
					try {
					byte[] bytesImg=foto.getBytes();
					Path rutaCompleta= Paths.get(rutAbsoluta+"//"+foto.getOriginalFilename());
					Files.write(rutaCompleta,bytesImg);
					objAlumno.setFoto(foto.getOriginalFilename());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			boolean flag = alumnoService.insertar(objAlumno);
			if (flag) {
				attribute.addFlashAttribute("success","Operación exitosa");
				return "redirect:/alumno/listar";
			} else {
				attribute.addFlashAttribute("warning", "Ocurrió un error");
				return "redirect:/alumno/irRegistrar";
			}
			
		}
	}
		/*if(!foto.isEmpty()) {
		Path directorioRecursos= Paths.get("src//main//resources//static//uploads");
		String rootPath= directorioRecursos.toFile().getAbsolutePath();
	
		try {
			byte[] bytes=foto.getBytes();
			Path rutaCompleta=Paths.get(rootPath+ "//" + foto.getOriginalFilename());
			Files.write(rutaCompleta, bytes);
			 objAlumno.setFoto(foto.getOriginalFilename());
		}catch(IOException e) {
			e.printStackTrace();
			}
		}
*/

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid C_Alumno objAlumno, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {

			return "redirect:/alumno/listar";
		} else {
			C_Usuario aux = new C_Usuario();
			aux.setUsername(objAlumno.getUsuario().getUsername());
			aux.setPassword(objAlumno.getUsuario().getPassword());
			aux.setEnabled(true);

			String bcryptPassword = passwordEncoder.encode(aux.getPassword());
			aux.setPassword(bcryptPassword);
			usuarioService.insertar(aux);

			aux = usuarioService.Buscar_Por_Nombre(objAlumno.getUsuario().getUsername());

			usuarioService.insRol("ROLE_USER", aux.getId());

			objAlumno.setUsuario(aux);
		
			boolean flag = alumnoService.modificar(objAlumno);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/alumno/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/alumno/listar";
			}
		}
	}

	
	@RequestMapping("/detalle/{id}")
	public String detalle(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		C_Alumno objAlumno = alumnoService.listar_Id(id);
		if (objAlumno == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/alumno/listar";
		} else {
			model.addAttribute("detalle","Detalle Alumno:"+objAlumno.getNombre());
			model.addAttribute("alumno", objAlumno);
			return "/alumno/detalleAlumno";

		}

	}
	

	
	// El update
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir,RedirectAttributes attribute) {

		C_Alumno objAlumno = alumnoService.listar_Id(id);
		if (objAlumno == null) {
			
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/alumno/listar";
		} else {
			//alumnoService.eliminar(objAlumno.getIdAlumno());
			//alumnoService.eliminarNombre(objAlumno);
			model.addAttribute("listaCarreras",mService.listar());
			model.addAttribute("alumno", objAlumno);
			//attribute.addFlashAttribute("success","Alumno MODIFICADO exitosamente");
			return "/alumno/alumno";
		}
		
		}
	

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				
				
				alumnoService.eliminar(id);
				model.put("listaAlumnos", alumnoService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el elemento seleccionado");
			model.put("listaAlumnos", alumnoService.listar());

		}
		return "/alumno/listAlumno";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaAlumnos", alumnoService.listar());
		return "/alumno/listAlumno";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute C_Alumno alumno) {

		alumnoService.listar_Id(alumno.getIdAlumno());
		return "/alumno/listAlumno";

	}

	@PostMapping("/buscar")
	public String buscar2(
			@RequestParam(value = "txtnamesearch") String txtnamesearch,
			Model model) {

		List<C_Alumno> listaAlumnos;

		if (StringUtils.isEmpty(txtnamesearch)) {
			model.addAttribute("mensaje", "No se encontraron resultados");
			listaAlumnos = alumnoService.listar();
		} else {
			listaAlumnos = alumnoService.buscar_Nombre(txtnamesearch);
		}
		
		if (listaAlumnos.isEmpty()) {

			model.addAttribute("mensaje", "No se encontraron resultados");
		}

		model.addAttribute("listaAlumnos", listaAlumnos);
		return "/alumno/listAlumno";

	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("/alumno/alumno", new C_Alumno());
		return "buscar";

	}

}
