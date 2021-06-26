package pe.edu.upc.spring;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import pe.edu.upc.spring.model.Role;
import pe.edu.upc.spring.model.User;
import pe.edu.upc.spring.service.IUserService;

@Component
public class MyPostConstructBean {

	@Autowired
	private IUserService uService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostConstruct
	void postConstruct() {

		try {
			List<Role> roles = new ArrayList<Role>();
			roles.add(new Role("ROLE_ADMIN"));
			roles.add(new Role("ROLE_USER"));

			User user = new User();
			user.setId(1);
			user.setCorreo("alejo_gm@gmail.com");
			user.setEnabled(true);
			user.setUsername("alejoGM");
			user.setPassword(passwordEncoder.encode("123456"));
			user.setRoles(roles);

			uService.insert(user);
		} catch (Exception ex) {

		}
	}

}
