package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Alumno_Curso;
import pe.edu.upc.spring.entity.C_Curso;

public interface IAlumno_CursoService {


	public boolean insertar(C_Alumno_Curso alumno_curso);

	public boolean modificar(C_Alumno_Curso alumno_curso);

	public void eliminar(int idAlumno_curso);

	public C_Alumno_Curso listar_Id(int idAlumno_curso);

	List<C_Alumno_Curso> listar();

	public List<C_Alumno> listar_alumno_x_curso(int idCurso);

	public List<C_Curso> listar_curso_x_alumno(int idAlumno);

	public List<C_Alumno> listar_alumno(int idAlumno_curso);
	
	/*
	public List<Curso> listarcurso(int idAlumno_curso);
	*/

}
