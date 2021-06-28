package pe.edu.upc.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.entity.Categoria;


public interface ICategoriaDAO extends JpaRepository<Categoria, Integer>{
	@Query("from Categoria c where c.nombreCategoria like :nombreCategoria%")
	List<Categoria> buscarNombreCategoria(@Param("nombreCategoria") String nombreCategoria);
	
}