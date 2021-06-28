package pe.edu.upc.spring.dao;
	
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Usuario;

@Repository
public interface IAlumnoDAO extends JpaRepository<C_Alumno, Integer>{
	
	@Query("from C_Alumno c where c.nombre like %:nombre%")
	List<C_Alumno> buscar_Nombre(@Param("nombre")String nombre);
 	
	@Query("from C_Alumno c where c.usuario.username like :username%")
	List<C_Alumno> buscar_Username(@Param("username")String username);
	
	@Modifying
	@Query(value = "update C_Alumno u set u.nombre = :nombre where u.id_alumno = :idAlumno",nativeQuery = true)
	public void modAl(@Param("nombre") String nombre, @Param("idAlumno") int id);

	public C_Alumno findByUsuario(C_Usuario user);
}
