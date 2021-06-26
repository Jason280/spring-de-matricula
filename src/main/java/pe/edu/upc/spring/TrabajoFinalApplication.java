package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TrabajoFinalApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(TrabajoFinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String contra = "juegos";
		
		for(int i=0; i<2; i++) {
			String bcryptPassword2 = passwordEncoder.encode(contra);
			System.out.println(bcryptPassword2);
		}
		
	}

}
