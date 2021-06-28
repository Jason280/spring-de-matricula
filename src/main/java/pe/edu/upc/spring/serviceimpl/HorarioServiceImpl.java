package pe.edu.upc.spring.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.entity.Horario;
import pe.edu.upc.spring.dao.IHorarioDAO;
import pe.edu.upc.spring.service.IHorarioService;


@Service
public class HorarioServiceImpl implements IHorarioService {

	
	@Autowired
	private IHorarioDAO mD;

	@Override
	@Transactional
	public boolean insertar(Horario horario) {
		Horario objCat = mD.save(horario);
		if (objCat == null)
			return false;
		else
			return true;	
	}

	@Override
	public List<Horario> listar() {
		return mD.findAll();
	}

	@Override
	@Transactional
	public void eliminar(int idhorario) {
			mD.delete(idhorario);
	}
	
	
	@Override
	public boolean modificar(Horario horario) {
		boolean flag = false;
		try {
			mD.save(horario);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Horario listarId(int idhorario) {
		return  mD.findOne(idhorario);
	}


}
