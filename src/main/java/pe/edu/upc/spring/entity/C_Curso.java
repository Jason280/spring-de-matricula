package pe.edu.upc.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "curso")
public class C_Curso implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCurso;
	
	@NotBlank(message= "No puede quedar vacio")
	@Column(name="Nombre", nullable = false, length = 50)
	private String Nombre;

	@ManyToOne
	@JoinColumn(name = "idDocente", nullable = false)
	private C_Docente docente;
	
	@ManyToOne
	@JoinColumn(name="id_Categoria", nullable = false)
	    private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="id_Seccion", nullable = false)
	    private Seccion seccion;

	@ManyToOne
	@JoinColumn(name="id_Horario", nullable = false)
	    private Horario horario;
	public C_Curso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public C_Curso(int idCurso, String nombre, C_Docente docente) {
		super();
		this.idCurso = idCurso;
		Nombre = nombre;
		this.docente = docente;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public C_Docente getDocente() {
		return docente;
	}

	public void setDocente(C_Docente docente) {
		this.docente = docente;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	
	

}
	
	