package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="inscripcion")
public class Inscripcion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idInscripcion;
	
	@ManyToOne
	@JoinColumn(name="idCurso", nullable = false)
	    private Curso curso;
	
	@ManyToOne
	@JoinColumn(name="idAlumno", nullable = false)
	    private Alumno alumno;;

	@Future(message = "Debe escoger una fecha futura para el vencimiento del pago")
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inscripcion")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInscripcion ;

	public int getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(int idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	
	
}
