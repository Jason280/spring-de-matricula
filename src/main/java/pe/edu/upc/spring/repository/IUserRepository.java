package pe.edu.upc.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.User;

@Repository
public interface IUserRepository extends JpaRepository<pe.edu.upc.spring.model.User, Integer>{
		
		@Query("from User u where u.id like :idUser%")
		List<User> buscarIdUser(@Param("idUser") int idUser);
		
		@Query("from User u where u.username like :username%")
		List<User> buscarUsername(@Param("username") String username);
		
		Optional<User> findByUsername(String username);
		
		Optional<User> findByCorreo(String correo);
}

/*
 Integer insert(User user) throws Exception;

	Integer update(User user) throws Exception;

	Integer delete(User user) throws Exception;

	List<User> findAll() throws Exception;

	Optional<User> findById(User user) throws Exception;

	String getPassworHashedByUserName(String username) throws Exception;

	Optional<User> findUserByUsername(User user) throws Exception;
 */
