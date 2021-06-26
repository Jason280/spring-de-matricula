package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.model.Inscripcion;

public interface IInscripcionService {
	public boolean insertar(Inscripcion inscripcion);
	
	public void eliminar(int idInscripcion);
	boolean modificar(Inscripcion inscripcion);
	public Inscripcion listar_Id(int idInscripcion);
	
	 List<Inscripcion> listar();
	
	public List<Alumno> listar_alumno_x_curso(int idCurso);

	public List<Curso> listar_curso_x_alumno(int idAlumno);

	public List<Alumno> listar_alumno(int idInscripcion);
}

