package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.model.Categoria;


public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{
	@Query("from Categoria c where c.nombreCategoria like :nombreCategoria%")
	List<Categoria> buscarNombreCategoria(@Param("nombreCategoria") String nombreCategoria);
	
}

/*

public void insertar(Juegos juegos);
	public List<Juegos> listar();
	public void eliminar(int idJuegos);
	public List<Juegos> findByNameJuegos(Juegos juegos);
	public void update (Juegos juegos);
*/