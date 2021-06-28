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
@Table(name = "docente_curso")
public class C_Docente_Curso implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDocente_curso;

	@ManyToOne
	@JoinColumn(name = "idCurso", nullable = false)
	private C_Curso curso;

	@ManyToOne
	@JoinColumn(name = "idDocente", nullable = false)
	private C_Docente docente;

	
	
	public C_Docente_Curso() {
		super();
	}

	public C_Docente_Curso(int idDocente_curso, C_Curso curso, C_Docente docente) {
		super();
		this.idDocente_curso = idDocente_curso;
		this.curso = curso;
		this.docente = docente;
	}

	public int getIdDocente_curso() {
		return idDocente_curso;
	}

	public void setIdDocente_curso(int idDocente_curso) {
		this.idDocente_curso = idDocente_curso;
	}

	public C_Curso getCurso() {
		return curso;
	}

	public void setCurso(C_Curso curso) {
		this.curso = curso;
	}

	public C_Docente getDocente() {
		return docente;
	}

	public void setDocente(C_Docente docente) {
		this.docente = docente;
	}

	


	
}
