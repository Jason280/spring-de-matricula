package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.spring.model.User;
import pe.edu.upc.spring.repository.IUserRepository;
import pe.edu.upc.spring.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository uD;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public boolean insert(User user) {
		User objUser = uD.save(user);
		if (objUser == null)
			return false;
		else
			return true;
	}

	@Transactional
	@Override
	public boolean update(User user) {
		boolean flag = false;
		try {
			uD.save(user);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Transactional
	@Override
	public void delete(int iduser) {
		uD.deleteById(iduser);
		;
	}

	@Override
	public List<User> listar() {
		return uD.findAll();
	}

	@Override
	public List<User> buscarIdUser(int idUser) {
		return uD.buscarIdUser(idUser);
	}

	@Override
	public List<User> buscarUsername(String userName) {
		return uD.buscarUsername(userName);
	}

	@Override
	public Optional<User> listarId(int idUser) {
		return uD.findById(idUser);
	}

	@Transactional
	@Override
	public void registerUser(User user) throws Exception {

		Optional<User> usuarioEncontrado = uD.findByUsername(user.getUsername());
		Optional<User> usuarioEncontrado2 = uD.findByCorreo(user.getCorreo());

		if (usuarioEncontrado.isPresent()) {
			throw new Exception("El username a registrar ya existe");
		}
		if (usuarioEncontrado2.isPresent()) {
			throw new Exception("El correo a registrar ya existe");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setEnabled(true);
		
		uD.save(user);

	}

}
