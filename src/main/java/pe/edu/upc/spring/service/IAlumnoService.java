package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.spring.model.Alumno;

public interface IAlumnoService {
	public boolean insertar(Alumno alumno);
	public List<Alumno> listar();
	public void eliminar(int idAlumno);
	public boolean modificar (Alumno alumno);
	List<Alumno> buscarNombreAlumno(String nameDocente);
	public Alumno listarId(int idAlumno);
}