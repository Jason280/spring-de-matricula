package pe.edu.upc.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
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



@Entity
@Table(name = "usuario")
public class C_Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 9, unique = true)
	private String username;

	@Column(length = 60)
	private String password;

	private Boolean enabled;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Role> roles;

	@OneToOne(mappedBy="usuario", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = true)
	private C_Alumno alumno;
	
	/*@OneToOne(mappedBy="usuario", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = true)
	private C_Docente docente;
	*/
	public C_Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public C_Usuario(int id, String username, String password, Boolean enabled, List<Role> roles, C_Alumno alumno) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
		this.alumno = alumno;
		//this.docente=docente;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public C_Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(C_Alumno alumno) {
		this.alumno = alumno;
	}

	/*public C_Docente getDocente() {
		return docente;
	}

	public void setDocente(C_Docente docente) {
		this.docente = docente;
	}
*/

}
