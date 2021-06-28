package pe.edu.upc.spring.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.entity.Carrera;
import pe.edu.upc.spring.dao.ICarreraDAO;
import pe.edu.upc.spring.service.ICarreraService;


@Service
public class CarreraServiceImpl implements ICarreraService {

	
	@Autowired
	private ICarreraDAO mD;

	@Override
	@Transactional
	public boolean insertar(Carrera carrera) {
		Carrera objCat = mD.save(carrera);
		if (objCat == null)
			return false;
		else
			return true;	
	}

	@Override
	public List<Carrera> listar() {
		return mD.findAll();
	}

	@Override
	@Transactional
	public void eliminar(int idcarrera) {
			mD.delete(idcarrera);
	}
	
	@Override
	public List<Carrera> buscarNombreCarrera(String nameCarrera) {
		return mD.buscarNombreCarrera(nameCarrera);
	}
	
	@Override
	public boolean modificar(Carrera carrera) {
		boolean flag = false;
		try {
			mD.save(carrera);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Carrera listarId(int idcarrera) {
		return  mD.findOne(idcarrera);
	}


}
