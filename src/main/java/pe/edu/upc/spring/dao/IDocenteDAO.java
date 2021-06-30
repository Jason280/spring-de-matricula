package pe.edu.upc.spring.dao;
	
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.entity.C_Docente;
import pe.edu.upc.spring.entity.C_Usuario;

@Repository
public interface IDocenteDAO extends JpaRepository<C_Docente, Integer>{
 
	@Query("from C_Docente d where d.Nombre like %:Nombre%")
	List<C_Docente> buscar_Nombre(@Param("Nombre")String Nombre);
 	
	@Modifying
	@Query(value = "update C_Docente u set u.Nombre = :Nombre where u.id_docente = :id",nativeQuery = true)
	public void modDo(@Param("Nombre") String Nombre, @Param("id") int id);

	//public C_Docente findByUsuario(C_Usuario user);
}
