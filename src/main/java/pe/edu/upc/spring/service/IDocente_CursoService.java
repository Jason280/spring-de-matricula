package pe.edu.upc.spring.service;

import java.util.List;


import pe.edu.upc.spring.entity.C_Curso;
import pe.edu.upc.spring.entity.C_Docente;
import pe.edu.upc.spring.entity.C_Docente_Curso;

public interface IDocente_CursoService {


	public boolean insertar(C_Docente_Curso docente_curso);

	public boolean modificar(C_Docente_Curso docente_curso);

	public void eliminar(int idDocente_curso);

	public C_Docente_Curso listar_Id(int idDocente_curso);

	List<C_Docente_Curso> listar();

	public List<C_Docente> listar_docente_x_curso(int idDocente);

	public List<C_Curso> listar_curso_x_docente(int idDocente);

	public List<C_Docente> listar_docente(int idDocente_curso);
	
	/*
	public List<Curso> listarcurso(int idAlumno_curso);
	*/

}
