package pe.edu.upc.spring.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.entity.Seccion;
import pe.edu.upc.spring.dao.ISeccionDAO;
import pe.edu.upc.spring.service.ISeccionService;


@Service
public class SeccionServiceImpl implements ISeccionService {

	
	@Autowired
	private ISeccionDAO mD;

	@Override
	@Transactional
	public boolean insertar(Seccion seccion) {
		Seccion objCat = mD.save(seccion);
		if (objCat == null)
			return false;
		else
			return true;	
	}

	@Override
	public List<Seccion> listar() {
		return mD.findAll();
	}

	@Override
	@Transactional
	public void eliminar(int idseccion) {
			mD.delete(idseccion);
	}
	
	
	@Override
	public boolean modificar(Seccion seccion) {
		boolean flag = false;
		try {
			mD.save(seccion);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Seccion listarId(int idseccion) {
		return  mD.findOne(idseccion);
	}


}
