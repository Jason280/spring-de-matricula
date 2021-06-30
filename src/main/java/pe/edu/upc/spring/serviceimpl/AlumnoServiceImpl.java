package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.dao.IAlumnoDAO;
import pe.edu.upc.spring.dao.IUsuarioDAO;
import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Usuario;
import pe.edu.upc.spring.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private IAlumnoDAO dAlumno;
	
	@Autowired
	private IUsuarioDAO dUser;

	@Override
	@Transactional
	public boolean insertar(C_Alumno alumno) {
		C_Alumno objAlumno = dAlumno.save(alumno);
		if (objAlumno == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(C_Alumno alumno) {
		boolean flag = false;
		try {
			
			C_Alumno a = dAlumno.findOne(alumno.getIdAlumno());
			C_Usuario aux = a.getUsuario();
			dUser.delRol(aux.getId());
			dAlumno.delete(alumno.getIdAlumno());
			dUser.delete(aux.getId());
			dAlumno.save(alumno);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idAlumno) {

		C_Alumno a = dAlumno.findOne(idAlumno);
		C_Usuario aux = a.getUsuario();
		dUser.delRol(aux.getId());
		dAlumno.delete(idAlumno);
		dUser.delete(aux.getId());
	}

	
	@Override
	@Transactional(readOnly=true)
	public C_Alumno listar_Id(int idAlumno) {
		return dAlumno.findOne(idAlumno);
	}

	@Override
	@Transactional(readOnly=true)
	public List<C_Alumno> buscar_Nombre(String Razon_social) {

		return dAlumno.buscar_Nombre(Razon_social);

	}

	@Override
	@Transactional(readOnly=true)
	public List<C_Alumno> listar() {
		return dAlumno.findAll();
	}

	public C_Alumno buscar_Alumno_por_Usuario(C_Usuario user)
	{
		return dAlumno.findByUsuario(user);
	}
	
	@Override //@Transactional
	public boolean modAl(String nombre, int id)
	{
		boolean flag = false;
		try {
			dAlumno.modAl(nombre, id);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
		
	}

	@Override
	public boolean eliminarNombre(C_Alumno alumno) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			dAlumno.save(alumno);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	public List<C_Alumno> buscar_Username(String username) {
		return dAlumno.buscar_Username(username);
	}

}
