package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.model.Curso;

public interface ICursoRepository extends JpaRepository<Curso, Integer>{
	
	@Query("from Curso c where c.nombreCurso like :nombreCurso%")
	List<Curso> buscarNombreCurso(@Param("nombreCurso") String nombreCurso);
	
}

/*

public void insertar(Juegos juegos);
	public List<Juegos> listar();
	public void eliminar(int idJuegos);
	public List<Juegos> findByNameJuegos(Juegos juegos);
	public void update (Juegos juegos);
*/