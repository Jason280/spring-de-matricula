package pe.edu.upc.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.entity.Especialidad;

public interface IEspecialidadDAO extends JpaRepository<Especialidad, Integer>{
	@Query("from Especialidad c where c.nombreEspecialidad like :nombreEspecialidad%")
	List<Especialidad> buscarNombreEspecialidad(@Param("nombreEspecialidad") String nombreEspecialidad);
	
}