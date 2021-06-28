package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.spring.auth.handler.LoginSuccessHandler;
import pe.edu.upc.spring.serviceimpl.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private LoginSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		try {
			http.authorizeRequests()

					.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')" )
					.antMatchers("/categoria/irRegistrar").access("hasRole('ROLE_ADMIN')" )
					.antMatchers("/categoria/listar").access("hasRole('ROLE_ADMIN')" )
					.antMatchers("/docente/irRegistrar").access("hasRole('ROLE_ADMIN')" )
					.antMatchers("/docente/listar").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')or hasRole('ROLE_DOCENTE') " )
					.antMatchers("/alumno/irRegistrar").access("hasRole('ROLE_ADMIN')" )
					.antMatchers("/alumno/modificar/{id}").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/alumno/eliminar").access("hasRole('ROLE_ADMIN')" )
					.antMatchers("/alumno/listar").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCENTE')or hasRole('ROLE_USER')" )
					.antMatchers("/curso/listar").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_DOCENTE')")
					.antMatchers("/curso/irRegistrar").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_DOCENTE')")
					//.antMatchers("/curso/irRegistrar").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCENTE')")
					.antMatchers("/curso/modificar/{id}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCENTE')")
					.antMatchers("/curso/eliminar").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCENTE')")
					.antMatchers("/alumno_curso/eliminaraxc/{idCurso}/{idAlumno}").access("hasRole('ROLE_ADMIN')")
					
				.antMatchers("/curso/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')or hasRole('ROLE_DOCENTE') ")
				    
					.antMatchers("/user/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')or hasRole('ROLE_DOCENTE')")
					.antMatchers("/error_403/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')or hasRole('ROLE_DOCENTE')")
					

					.and()

					.formLogin().successHandler(successHandler).loginPage("/login").loginProcessingUrl("/login")
					.defaultSuccessUrl("/login/home").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
					.and().exceptionHandling().accessDeniedPage("/error_403");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}

}