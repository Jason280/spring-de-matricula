package pe.edu.upc.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alumno_curso")
public class C_Alumno_Curso implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlumno_curso;

	@ManyToOne
	@JoinColumn(name = "idCurso", nullable = false)
	private C_Curso curso;

	@ManyToOne
	@JoinColumn(name = "idAlumno", nullable = false)
	private C_Alumno alumno;

	
	
	public C_Alumno_Curso() {
		super();
	}

	public C_Alumno_Curso(int idAlumno_curso, C_Curso curso, C_Alumno alumno) {
		super();
		this.idAlumno_curso = idAlumno_curso;
		this.curso = curso;
		this.alumno = alumno;
	}

	public int getIdAlumno_curso() {
		return idAlumno_curso;
	}

	public void setIdAlumno_curso(int idAlumno_curso) {
		this.idAlumno_curso = idAlumno_curso;
	}

	public C_Curso getCurso() {
		return curso;
	}

	public void setCurso(C_Curso curso) {
		this.curso = curso;
	}

	public C_Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(C_Alumno alumno) {
		this.alumno = alumno;
	}



	
}
