package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.dao.IAlumno_CursoDAO;
import pe.edu.upc.spring.dao.ICursoDAO;
import pe.edu.upc.spring.entity.C_Alumno_Curso;
import pe.edu.upc.spring.entity.C_Curso;
import pe.edu.upc.spring.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {

	@Autowired
	private ICursoDAO dCurso;

	@Autowired
	private IAlumno_CursoDAO dAC;
	
	@Override
	@Transactional
	public boolean insertar(C_Curso curso) {
		C_Curso objCurso = dCurso.save(curso);
		if (objCurso == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(C_Curso curso) {
		boolean flag = false;
		try {
			dCurso.save(curso);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idCurso) {
		
		for (C_Alumno_Curso dp : dAC.findAll()) {
			if(dp.getCurso().getIdCurso() == idCurso)
			{
				dAC.delete(dp.getIdAlumno_curso());
			}
		}
		
		dCurso.delete(idCurso);

	}

	
	@Override
	@Transactional(readOnly=true)
	public C_Curso listar_Id(int idCurso) {
		return dCurso.findOne(idCurso);
	}

	@Override
	@Transactional(readOnly=true)
	public List<C_Curso> listar() {
		return dCurso.findAll();
	}

	@Override
	public List<C_Curso> buscarNombreCategoria(String Nombre) {
		return dCurso.buscarNombreCategoria(Nombre);
	}

}
