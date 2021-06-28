package pe.edu.upc.spring.dao;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Alumno_Curso;
import pe.edu.upc.spring.entity.C_Curso;

@Repository
public interface IAlumno_CursoDAO extends JpaRepository<C_Alumno_Curso, Integer>{

	@Autowired
	@Query("select c from C_Alumno_Curso as ac join ac.curso as c where ac.alumno.idAlumno = :idAlumno")
	public List<C_Curso> listar_cursos_x_alumno(@Param("idAlumno") int idAlumno);

	@Autowired
	@Query("select a from C_Alumno_Curso as ac join ac.alumno as a where ac.curso.idCurso = :idCurso")
	public List<C_Alumno> listar_alumno_x_curso(@Param("idCurso") int idCurso);

	@Autowired
	@Query("select a from C_Alumno_Curso as ac join ac.alumno as a where ac.idAlumno_curso = :id")
	public List<C_Alumno> listar_alumnos(@Param("id") int idAlumno_curso);

	@Autowired
	@Query("select c from C_Alumno_Curso as ac join ac.curso as c where ac.idAlumno_curso = :id")
	public List<C_Curso> listar_cursos(@Param("id") int idAlumno_curso);
	
}
