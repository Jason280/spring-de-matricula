package pe.edu.upc.spring.dao;
	
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.entity.C_Curso;


@Repository
public interface ICursoDAO extends JpaRepository<C_Curso, Integer>{

	@Query("from C_Curso c where c.Nombre like :Nombre%")
	List<C_Curso> buscarNombreCategoria(@Param("Nombre") String Nombre);
}
