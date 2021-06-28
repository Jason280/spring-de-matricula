package pe.edu.upc.spring;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import pe.edu.upc.spring.entity.C_Usuario;

import pe.edu.upc.spring.service.IUsuarioService;


@Component
public class MyPostConstructBean {

	@Autowired
	private IUsuarioService uService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostConstruct
	void postConstruct() {
		
		try {
			List<pe.edu.upc.spring.entity.Role> roles = new ArrayList<pe.edu.upc.spring.entity.Role>();
			
			C_Usuario user = new C_Usuario();
			
			user.setId(1);
			user.setEnabled(true);
			user.setUsername("admin01");
			user.setPassword(passwordEncoder.encode("admin01"));
            user.setRoles(roles);
			uService.insertar(user);
		} catch (Exception ex) {

		}
	}

}
