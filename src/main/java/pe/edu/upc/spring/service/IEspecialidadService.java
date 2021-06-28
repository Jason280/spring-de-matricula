package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.entity.Especialidad;


public interface IEspecialidadService {
	public boolean insertar(Especialidad especialidad);
	public List<Especialidad> listar();
	public void eliminar(int idespecialidad);
	public boolean modificar (Especialidad especialidad);
	List<Especialidad> buscarNombreEspecialidad(String nameEspecialidad);
	public Especialidad listarId(int idespecialidad);
}