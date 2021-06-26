package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.ICursoRepository;
import pe.edu.upc.spring.model.Curso;

import pe.edu.upc.spring.service.ICursoService;



@Service
public class CursoServiceImpl implements ICursoService{

	@Autowired
	private ICursoRepository cD;

	@Override
	@Transactional
	public boolean insertar(Curso curso) {
		Curso objCu = cD.save(curso);
		if (objCu == null)
			return false;
		else
			return true;		
	}

	@Override
	public List<Curso> listar() {
		return cD.findAll();
	}

	@Override
	public void eliminar(int idCurso) {
		cD.deleteById(idCurso);		
	}
	
	@Override
	public List<Curso> buscarNombreCurso (String nameCurso) {
	 return  cD.buscarNombreCurso(nameCurso);
	}
	
	@Override
	public boolean update(Curso curso) {
		boolean flag = false;
		try {
			cD.save(curso);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Curso listarId(int idCurso) {
		return  cD.findById(idCurso).get();
	}

}
