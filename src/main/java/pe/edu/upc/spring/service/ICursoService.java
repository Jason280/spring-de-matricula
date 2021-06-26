package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Curso;

public interface ICursoService {
	public boolean insertar(Curso curso);
	public List<Curso> listar();
	public void eliminar(int idCurso);
	public boolean update (Curso curso);
	List<Curso> buscarNombreCurso(String nameCurso);
	public Curso listarId(int idCurso);
}
