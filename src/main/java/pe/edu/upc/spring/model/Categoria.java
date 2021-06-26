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
@Table(name = "categorias")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Categoria")
	private int id_categoria;
	
	@NotBlank(message="Ingrese el nombre de la Categoria")
	@Column(name="nombre_categoria", nullable=false, length=30)
    @Size(min = 3, max = 30,message="El nombre de la categoria debe tener como minimo 3 caracteres y maximo 30 caracteres")
	@Pattern (regexp ="[^0-9]*",message="El nombre solo debe contener letras")
	private String nombreCategoria;

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	


	
}
