package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	//@NotEmpty(message="No puede estar vacio")
   // @NotBlank(message="No puede estar en blanco")
	//@Column(name = "username", nullable = false, length = 10)
	//@Pattern (regexp ="[^0-9]*",message="El username solo debe contener letras. Ejemplo: Alejo_Gm")
	//@Size(min = 3, max = 10,message="El nombre del usuario debe tener como minimo 3 caracteres y maximo 10 caracteres")
	private String username;
	
 
    //@NotBlank(message="No puede estar en blanco")
	//@Column(name = "password", nullable = false, length = 8)
    //@Pattern(regexp = "[0-9]{8}", message = "El numero de tarjeta debe tener 8 digitos númericos")
   //@Size(max = 8,message="La contraseña debe tener como maximo 8 caracteres")
	private String password;
	
	
	//@NotEmpty(message="No puede estar vacio")
    //@NotBlank(message="No puede estar en blanco")
	//@Column(name = "correo", nullable = false, length = 80)
	//@Email(message="Ingrese su correo en el siguiente formato: abcd@nombredelamepresa.com")
	private String correo;
	
	private boolean enabled;
	
	@OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Role> roles;

     
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	
}
