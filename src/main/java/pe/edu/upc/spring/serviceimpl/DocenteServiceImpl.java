package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.model.Docente;
import pe.edu.upc.spring.repository.IDocenteRepository;
import pe.edu.upc.spring.service.IDocenteService;


@Service
public class DocenteServiceImpl implements IDocenteService {

	
	@Autowired
	private IDocenteRepository mD;

	@Override
	@Transactional
	public boolean insertar(Docente docente) {
		Docente objDoc = mD.save(docente);
		if (objDoc == null)
			return false;
		else
			return true;	
	}

	@Override
	public List<Docente> listar() {
		return mD.findAll();
	}

	@Override
	public void eliminar(int idDocente) {
		mD.deleteById(idDocente);		
	}
	
	@Override
	public List<Docente> buscarNombreDocente(String nameDocente) {
		return mD.buscarNombreDocente(nameDocente);
	}
	
	@Override
	public boolean modificar(Docente docente) {
		boolean flag = false;
		try {
			mD.save(docente);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Docente> listarId(int idDocente) {
		return  mD.findById(idDocente);
	}


}
