package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.entity.C_Usuario;

public interface IUsuarioService {

	public boolean insertar(C_Usuario usuario);

	public boolean modificar(C_Usuario usuario);

	public void eliminar(int idUsuario);

	public C_Usuario listar_Id(int idUsuario);

	public List<C_Usuario> listar_x_Nombre(String Nombre);

	List<C_Usuario> listar();
	
	public C_Usuario Buscar_Por_Nombre(String username);

	public void insRol(String authority, int user_id);
	
	public void delRol(int user_id);
	
	public void modUser(String username, String password, int id);

}
