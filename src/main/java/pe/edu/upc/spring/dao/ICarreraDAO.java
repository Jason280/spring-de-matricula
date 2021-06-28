package pe.edu.upc.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.entity.Carrera;
import pe.edu.upc.spring.entity.Categoria;


public interface ICarreraDAO extends JpaRepository<Carrera, Integer>{
	@Query("from Carrera c where c.nombreCarrera like :nombreCarrera%")
	List<Carrera> buscarNombreCarrera(@Param("nombreCarrera") String nombreCarrera);
	
}