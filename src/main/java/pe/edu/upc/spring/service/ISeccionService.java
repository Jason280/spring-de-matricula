package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.entity.Carrera;
import pe.edu.upc.spring.entity.Seccion;


public interface ISeccionService {
	public boolean insertar(Seccion seccion);
	public List<Seccion> listar();
	public void eliminar(int idseccion);
	public boolean modificar (Seccion seccion);
	public Seccion listarId(int idseccion);
}