package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="curso")
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCurso;
	
	@NotBlank(message="Ingrese el nombre del curso")
	@Column(name="nombre_curso", nullable=false, length=30)
	@Size(min = 1, max = 40,message="El nombre del Curso debe tener como minimo 1 caracter y maximo 40 caracteres")
	private String nombreCurso;

	
	@ManyToOne
	@JoinColumn(name="id_Categoria", nullable = false)
	    private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="id_docente", nullable = false)
	private Docente docente;

	@NotBlank(message="Ingrese la descripcion del Curso")
	@Column(name="descripcion_curso", nullable=false, length=30)
	@Size(min = 5, max = 40,message="El nombre del Curso debe tener como minimo 5 caracteres y maximo 40 caracteres")
	private String descripcionCurso;
	
	@Positive(message="El precio debe ser mayor a cero")
	@Range(max=900,message="El precio maximo de un curso es de 900 soles")
	@Digits(integer = 3, fraction = 1,message="El precio debe tener dos decimales. Ejemplo: 150.9")
	private float precioCurso;
	

	public Curso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Curso(int idCurso,
			@NotBlank(message = "Ingrese el nombre del curso") @Size(min = 1, max = 40, message = "El nombre del Curso debe tener como minimo 1 caracter y maximo 40 caracteres") String nombreCurso,
			Categoria categoria, Docente docente,
			@NotBlank(message = "Ingrese la descripcion del Curso") @Size(min = 5, max = 40, message = "El nombre del Curso debe tener como minimo 5 caracteres y maximo 40 caracteres") String descripcionCurso,
			@Positive(message = "El precio debe ser mayor a cero") @Range(max = 900, message = "El precio maximo de un curso es de 900 soles") @Digits(integer = 3, fraction = 1, message = "El precio debe tener dos decimales. Ejemplo: 150.9") float precioCurso) {
		super();
		this.idCurso = idCurso;
		this.nombreCurso = nombreCurso;
		this.categoria = categoria;
		this.docente = docente;
		this.descripcionCurso = descripcionCurso;
		this.precioCurso = precioCurso;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public String getDescripcionCurso() {
		return descripcionCurso;
	}

	public void setDescripcionCurso(String descripcionCurso) {
		this.descripcionCurso = descripcionCurso;
	}

	public float getPrecioCurso() {
		return precioCurso;
	}

	public void setPrecioCurso(float precioCurso) {
		this.precioCurso = precioCurso;
	}
	
	
	
	
}
