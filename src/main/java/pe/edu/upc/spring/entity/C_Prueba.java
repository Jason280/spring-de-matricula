package pe.edu.upc.spring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class C_Prueba {
	
	public static void main(String[] args) {
	
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.print(passwordEncoder.encode("1234A"));
	}
	
	
	
	
}
