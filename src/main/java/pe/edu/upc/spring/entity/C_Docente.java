package pe.edu.upc.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "docente")
public class C_Docente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDocente;
	
	@Column(name="Nombre", nullable = false, length = 50)
	@Size(min = 3, max = 10,message="El nombre del docente debe tener como minimo 3 caracteres y maximo 30 caracteres")
	@Pattern (regexp ="[^0-9]*",message="El nombre solo debe contener letras")
	private String Nombre;
	
	@Column(name="Apellido", nullable = false, length = 50)
	private String Apellido;
	
	
	@ManyToOne
	@JoinColumn(name="id_Especialidad", nullable = false)
	    private Especialidad especialidad;

	/*@OneToOne
	@JoinColumn(name="idUsuario", referencedColumnName = "id")
	private C_Usuario usuario;
	*/

	
	
	@Column(name = "residenciaDocente", nullable = false, length =30)
	private String residenciaDocente;
	
	
	@Column(name = "correo", nullable = false, length = 80)
	private String correoDocente;
	
	private String foto;
	
	public C_Docente() {
		super();
	}

	public C_Docente(int idDocente, String nombre) {
		super();
		this.idDocente = idDocente;
		Nombre = nombre;
	}

	public int getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public String getResidenciaDocente() {
		return residenciaDocente;
	}

	public void setResidenciaDocente(String residenciaDocente) {
		this.residenciaDocente = residenciaDocente;
	}

	public String getCorreoDocente() {
		return correoDocente;
	}

	public void setCorreoDocente(String correoDocente) {
		this.correoDocente = correoDocente;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	
	
	
}
