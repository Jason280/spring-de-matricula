package pe.edu.upc.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import javax.validation.constraints.Pattern;


@Entity
@Table(name = "carrera")
public class Carrera implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_Carrera;
	
	@Column(name="nombre_carrera", nullable=false, length=30)
	@Pattern (regexp ="[^0-9]*",message="El nombre solo debe contener letras")
	private String nombreCarrera;

	

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public int getId_Carrera() {
		return id_Carrera;
	}

	public void setId_Carrera(int id_Carrera) {
		this.id_Carrera = id_Carrera;
	}

	


	
}
