package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "docente")
public class Docente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDocente;
	
	@Column(name = "nombreDocente", nullable = false, length =30)
	private String nombreDocente;

	@Column(name = "DNIDocente", nullable = false, length =30)
	private String DNIDocente;	
	
	@Column(name = "residenciaDocente", nullable = false, length =30)
	private String residenciaDocente;
	
	@Column(name = "correoDocente", nullable = false, length =30)
	private String correoDocente;

	public Docente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Docente(int idDocente, String nombreDocente, String dNIDocente, String residenciaDocente,
			String correoDocente) {
		super();
		this.idDocente = idDocente;
		this.nombreDocente = nombreDocente;
		DNIDocente = dNIDocente;
		this.residenciaDocente = residenciaDocente;
		this.correoDocente = correoDocente;
	}

	public int getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}

	public String getNombreDocente() {
		return nombreDocente;
	}

	public void setNombreDocente(String nombreDocente) {
		this.nombreDocente = nombreDocente;
	}

	public String getDNIDocente() {
		return DNIDocente;
	}

	public void setDNIDocente(String dNIDocente) {
		DNIDocente = dNIDocente;
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

	

	
}
