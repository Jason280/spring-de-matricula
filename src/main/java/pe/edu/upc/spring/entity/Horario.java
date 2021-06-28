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
@Table(name = "horario")
public class Horario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Horario")
	private int id_horario;
	
	@Column(name="tiempoinicio", nullable=false, length=30)
	private String tiempoInicio;
	
	@Column(name="tiempofinal", nullable=false, length=30)
	private String tiempoFinal;
	
	public int getId_horario() {
		return id_horario;
	}
	public void setId_horario(int id_horario) {
		this.id_horario = id_horario;
	}
	public String getTiempoInicio() {
		return tiempoInicio;
	}
	public void setTiempoInicio(String tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
	public String getTiempoFinal() {
		return tiempoFinal;
	}
	public void setTiempoFinal(String tiempoFinal) {
		this.tiempoFinal = tiempoFinal;
	}

	

	
}
