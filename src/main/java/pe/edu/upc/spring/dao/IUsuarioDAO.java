package pe.edu.upc.spring.dao;
	
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.entity.C_Usuario;

@Repository
public interface IUsuarioDAO extends JpaRepository<C_Usuario, Integer>{
	
	@Query("from C_Usuario c where c.username = :username")
	public C_Usuario buscar_Nombre(@Param("username")String username);
	
	//JPADETAIL
	@Query("from C_Usuario c where c.username like %:username%")
	public List<C_Usuario> listar_x_Nombre(@Param("username")String username);

    @Modifying
	@Query(value = "insert into authorities (authority, user_id) VALUES (:authority, :user_id)", nativeQuery = true)
	public void insRol(@Param("authority") String authority, @Param("user_id") int user_id);
		
    @Modifying
    @Query(value = "delete from authorities  where user_id=:uid", nativeQuery = true)
    public void delRol(@Param("uid") int user_id);
    
    @Modifying
    @Query(value = "update usuario u set u.password = :pass, u.username = :Name where u.id = :id", nativeQuery = true)
    public void modUser(@Param("Name") String username ,@Param("pass") String password, @Param("id") int id);
}
