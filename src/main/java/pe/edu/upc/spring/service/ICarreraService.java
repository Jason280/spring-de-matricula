package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.entity.Carrera;


public interface ICarreraService {
	public boolean insertar(Carrera carrera);
	public List<Carrera> listar();
	public void eliminar(int idcarrera);
	public boolean modificar (Carrera carrera);
	List<Carrera> buscarNombreCarrera(String nameCarrera);
	public Carrera listarId(int idcarrera);
}