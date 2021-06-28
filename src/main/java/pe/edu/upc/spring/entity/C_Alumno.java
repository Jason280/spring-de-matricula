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
@Table(name="alumno")
public class C_Alumno implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlumno;

	@OneToOne
	@JoinColumn(name="idUsuario", referencedColumnName = "id")
	private C_Usuario usuario;

	
	@Column(name = "nombre", nullable = false, length = 50)
	@Size(min = 3, max = 10,message="El nombre del alumno debe tener como minimo 3 caracteres y maximo 10 caracteres")
	@Pattern (regexp ="[^0-9]*",message="El nombre solo debe contener letras")
	private String nombre;
	
	@Column(name="Apellido", nullable = false, length = 50)
	private String Apellido;
	
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "correoAlumno", nullable = false, length =30)
	private String correoAlumno;
	
	private String foto;
	
	@ManyToOne
	@JoinColumn(name="id_Carrera", nullable = false)
	    private Carrera carrera;

	

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public C_Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(C_Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getCorreoAlumno() {
		return correoAlumno;
	}

	public void setCorreoAlumno(String correoAlumno) {
		this.correoAlumno = correoAlumno;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	
	
	/*public C_Alumno() {
		super();
	}

	public C_Alumno(int idAlumno, C_Usuario usuario, String nombre,int edadAlumno,String correoAlumno,String foto) {
		super();
		this.idAlumno = idAlumno;
		this.usuario = usuario;
		this.nombre = nombre;
		this.edadAlumno=edadAlumno;
		this.correoAlumno= correoAlumno;
		this.foto=foto;
	}*/

	

	
}