package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.model.Docente;

public interface IDocenteRepository extends JpaRepository<Docente, Integer>{
	
	@Query("from Docente c where c.nombreDocente like :nombreDocente%")
	List<Docente> buscarNombreDocente(@Param("nombreDocente") String nombreDocente);
	
}

/*

public void insertar(Juegos juegos);
	public List<Juegos> listar();
	public void eliminar(int idJuegos);
	public List<Juegos> findByNameJuegos(Juegos juegos);
	public void update (Juegos juegos);
*/