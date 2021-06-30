package pe.edu.upc.spring.dao;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.entity.C_Curso;
import pe.edu.upc.spring.entity.C_Docente;
import pe.edu.upc.spring.entity.C_Docente_Curso;

@Repository
public interface IDocente_CursoDAO extends JpaRepository<C_Docente_Curso, Integer>{

	@Autowired
	@Query("select c from C_Docente_Curso as ac join ac.curso as c where ac.docente.idDocente = :idDocente")
	public List<C_Curso> listar_cursos_x_docente(@Param("idDocente") int idDocente);

	@Autowired
	@Query("select a from C_Docente_Curso as ac join ac.docente as a where ac.curso.idCurso = :idCurso")
	public List<C_Docente> listar_docente_x_curso(@Param("idCurso") int idCurso);

	@Autowired
	@Query("select a from C_Docente_Curso as ac join ac.docente as a where ac.idDocente_curso = :id")
	public List<C_Docente> listar_docentes(@Param("id") int idDocente_curso);

	@Autowired
	@Query("select c from C_Docente_Curso as ac join ac.curso as c where ac.idDocente_curso = :id")
	public List<C_Curso> listar_cursos(@Param("id") int idDocente_curso);
	
}
