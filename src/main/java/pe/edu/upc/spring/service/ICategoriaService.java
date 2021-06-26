package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.spring.model.Categoria;

public interface ICategoriaService {
	public boolean insertar(Categoria categoria);
	public List<Categoria> listar();
	public void eliminar(int idcategoria);
	public boolean modificar (Categoria categoria);
	List<Categoria> buscarNombreCategoria(String nameCategoria);
	public Optional<Categoria> listarId(int idcategoria);
}