package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.dao.IUsuarioDAO;
import pe.edu.upc.spring.entity.C_Usuario;
import pe.edu.upc.spring.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDAO dUsuario;

	@Override
	@Transactional
	public boolean insertar(C_Usuario race) {
		C_Usuario objUsuario = dUsuario.save(race);
		if (objUsuario == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(C_Usuario race) {
		boolean flag = false;
		try {
			dUsuario.save(race);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idUsuario) {

		dUsuario.delete(idUsuario);

	}
	
	@Override
	@Transactional(readOnly=true)
	public C_Usuario listar_Id(int idUsuario) {
		return dUsuario.findOne(idUsuario);
	}

	@Override
	@Transactional(readOnly=true)
	public List<C_Usuario> listar_x_Nombre(String Nombre) {

		return dUsuario.listar_x_Nombre(Nombre);

	}
	@Override
	@Transactional(readOnly=true)
	public C_Usuario Buscar_Por_Nombre(String username) {

		return dUsuario.buscar_Nombre(username);

	}
	
	@Override
	@Transactional(readOnly=true)
	public List<C_Usuario> listar() {
		return dUsuario.findAll();
	}

	@Override
	@Transactional
	public void insRol(String authority, int user_id)
	{
		dUsuario.insRol(authority, user_id);
	}
	
	@Override
	@Transactional
	public void delRol(int user_id)
	{
		dUsuario.delRol(user_id);
	}
	
	@Override
	@Transactional
	public void modUser(String username, String password, int id)
	{
		dUsuario.modUser(username, password, id);
	}

}
