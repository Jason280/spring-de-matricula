package pe.edu.upc.spring.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.entity.C_Curso;

public interface ICursoService {

	public boolean insertar(C_Curso curso);

	public boolean modificar(C_Curso curso);

	public void eliminar(int idCurso);

	public C_Curso listar_Id(int idCurso);

	List<C_Curso> listar();
	List<C_Curso> buscarNombreCategoria( String Nombre);

}
