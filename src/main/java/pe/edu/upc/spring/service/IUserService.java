package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.User;

public interface IUserService {
	public boolean insert(User user) ;

	public boolean update(User user) ;

	public void delete(int iduser) ;

	public List<User> listar();

	public List<User> buscarIdUser(int idUser);

	public List<User> buscarUsername(String userName);
	
	public Optional<User> listarId(int idUser);
	
	public void registerUser(User user) throws Exception;
}