package pe.edu.upc.spring.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.entity.Especialidad;
import pe.edu.upc.spring.dao.IEspecialidadDAO;
import pe.edu.upc.spring.service.IEspecialidadService;


@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

	
	@Autowired
	private IEspecialidadDAO mD;

	@Override
	@Transactional
	public boolean insertar(Especialidad especialidad) {
		Especialidad objCat = mD.save(especialidad);
		if (objCat == null)
			return false;
		else
			return true;	
	}

	@Override
	public List<Especialidad> listar() {
		return mD.findAll();
	}

	@Override
	@Transactional
	public void eliminar(int idespecialidad) {
			mD.delete(idespecialidad);
	}
	
	@Override
	public List<Especialidad> buscarNombreEspecialidad(String nameEspecialidad) {
		return mD.buscarNombreEspecialidad(nameEspecialidad);
	}
	
	@Override
	public boolean modificar(Especialidad especialidad) {
		boolean flag = false;
		try {
			mD.save(especialidad);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Especialidad listarId(int idespecialidad) {
		return  mD.findOne(idespecialidad);
	}


}
