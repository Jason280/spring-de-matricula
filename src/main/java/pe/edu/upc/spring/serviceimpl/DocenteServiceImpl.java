package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.dao.IDocenteDAO;
import pe.edu.upc.spring.dao.IUsuarioDAO;
import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Docente;
import pe.edu.upc.spring.entity.C_Usuario;
import pe.edu.upc.spring.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {

	@Autowired
	private IDocenteDAO dDocente;

	@Autowired
	private IUsuarioDAO dUser;
	
	@Override
	@Transactional
	public boolean insertar(C_Docente docente) {
		C_Docente objDocente = dDocente.save(docente);
		if (objDocente == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(C_Docente docente) {
		boolean flag = false;
		
		
		
		try {
		//	C_Docente d = dDocente.findOne(docente.getIdDocente());
		//	C_Usuario aux = d.getUsuario();
			//dUser.delRol(aux.getId());
			//dDocente.delete(docente.getIdDocente());
			//dUser.delete(aux.getId());
			dDocente.save(docente);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	/*	try {
			dDocente.save(docente);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
*/
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idDocente) {

		//C_Docente d = dDocente.findOne(idDocente);
		//C_Usuario aux = d.getUsuario();
		//dUser.delRol(aux.getId());
		dDocente.delete(idDocente);
		//dUser.delete(aux.getId());
		

	}

	
	@Override
	@Transactional(readOnly=true)
	public C_Docente listar_Id(int idDocente) {
		return dDocente.findOne(idDocente);
	}


	@Override
	@Transactional(readOnly=true)
	public List<C_Docente> listar() {
		return dDocente.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<C_Docente> buscar_Nombre(String Razon_social) {
		return dDocente.buscar_Nombre(Razon_social);
	}

	/*@Override
	public C_Docente buscar_Docente_por_Usuario(C_Usuario user) {
		
		return dDocente.findByUsuario(user);
	}
*/
	@Override
	public boolean modDo(String Nombre, int id) {
		// TODO Auto-generated method stub
		return false;
	}



}
