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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlumno;
	
	@Column(name = "nombreAlumno", nullable = false, length =30)
	private String nombreAlumno;

	@Column(name = "edadAlumno", nullable = false, length =30)
	private int edadAlumno;	
	
	@Column(name = "correoAlumno", nullable = false, length =30)
	@Email(message="Ingrese su correo en el siguiente formato: abcd@nombredelamepresa.com")
	private String correoAlumno;
	
	
	@ManyToOne
	@JoinColumn(name="id")
	private User user ;


	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public int getEdadAlumno() {
		return edadAlumno;
	}

	public void setEdadAlumno(int edadAlumno) {
		this.edadAlumno = edadAlumno;
	}

	public String getCorreoAlumno() {
		return correoAlumno;
	}

	public void setCorreoAlumno(String correoAlumno) {
		this.correoAlumno = correoAlumno;
	}	


	
}
