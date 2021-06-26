package pe.edu.upc.spring.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.model.Inscripcion;

@Repository
public interface IInscripcionRepository extends JpaRepository<Inscripcion, Integer>{
	@Autowired
	@Query("select c from Inscripcion as ac join ac.curso as c where ac.alumno.idAlumno = :idAlumno")
	public List<Curso> listar_cursos_x_alumno(@Param("idAlumno") int idAlumno);

	@Autowired
	@Query("select a from Inscripcion as ac join ac.alumno as a where ac.curso.idCurso = :idCurso")
	public List<Alumno> listar_alumno_x_curso(@Param("idCurso") int idCurso);

	@Autowired
	@Query("select a from Inscripcion as ac join ac.alumno as a where ac.idInscripcion = :id")
	public List<Alumno> listar_alumnos(@Param("id") int idInscripcion);

	@Autowired
	@Query("select c from Inscripcion as ac join ac.curso as c where ac.idInscripcion = :id")
	public List<Curso> listar_cursos(@Param("id") int idInscripcion);
	
}
