package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.repository.IAlumnoRepository;
import pe.edu.upc.spring.service.IAlumnoService;


@Service
public class AlumnoServiceImpl implements IAlumnoService {

	
	@Autowired
	private IAlumnoRepository mD;

	@Override
	@Transactional
	public boolean insertar(Alumno alumno) {
		Alumno objAlum = mD.save(alumno);
		if (objAlum == null)
			return false;
		else
			return true;	
	}

	@Override
	@Transactional(readOnly=true)
	public List<Alumno> listar() {
		return mD.findAll();
	}

	@Override
	@Transactional
	public void eliminar(int idAlumno) {
		mD.deleteById(idAlumno);		
	}
	
	@Override
	public List<Alumno> buscarNombreAlumno(String nameAlumno) {
		return mD.buscarNombreAlumno(nameAlumno);
	}
	
	@Override
	
	public boolean modificar(Alumno alumno) {
		boolean flag = false;
		try {
			mD.save(alumno);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Alumno listarId(int idAlumno) {
		return  mD.findById(idAlumno).get();
	}


}
