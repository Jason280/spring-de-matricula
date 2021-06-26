package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.model.Alumno;

public interface IAlumnoRepository extends JpaRepository<Alumno, Integer>{
	
	@Query("from Alumno c where c.nombreAlumno like :nombreAlumno%")
	List<Alumno> buscarNombreAlumno(@Param("nombreAlumno") String nombreAlumnno);
	
}

/*

public void insertar(Juegos juegos);
	public List<Juegos> listar();
	public void eliminar(int idJuegos);
	public List<Juegos> findByNameJuegos(Juegos juegos);
	public void update (Juegos juegos);
*/