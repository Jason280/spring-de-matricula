package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.dao.IDocente_CursoDAO;
import pe.edu.upc.spring.entity.C_Curso;
import pe.edu.upc.spring.entity.C_Docente;
import pe.edu.upc.spring.entity.C_Docente_Curso;
import pe.edu.upc.spring.service.IDocente_CursoService;

@Service
public class Docente_CursoServiceImpl implements IDocente_CursoService {

	@Autowired
	private IDocente_CursoDAO dDocente_curso;

	@Override
	@Transactional
	public boolean insertar(C_Docente_Curso docente_curso) {
		C_Docente_Curso objDocente_curso = dDocente_curso.save(docente_curso);
		if (objDocente_curso == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(C_Docente_Curso docente_curso) {
		boolean flag = false;
		try {
			dDocente_curso.save(docente_curso);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idDocente_curso) {

		dDocente_curso.delete(idDocente_curso);

	}

	
	@Override
	@Transactional(readOnly=true)
	public C_Docente_Curso listar_Id(int idDocente_curso) {
		return dDocente_curso.findOne(idDocente_curso);
	}


	@Override
	@Transactional(readOnly=true)
	public List<C_Docente_Curso> listar() {
		return dDocente_curso.findAll();
	}
	

	@Override
	@Transactional(readOnly=true)
	public List<C_Docente> listar_docente_x_curso(int id_Curso)
	{
		return dDocente_curso.listar_docente_x_curso(id_Curso);
	}
	@Override
	@Transactional(readOnly=true)
	public List<C_Curso> listar_curso_x_docente(int idDocente)
	{
		return dDocente_curso.listar_cursos_x_docente(idDocente);
	}
	@Override
	@Transactional(readOnly=true)
	public List<C_Docente> listar_docente(int idDocente_curso)
	{
		return dDocente_curso.listar_docentes(idDocente_curso);
	}

}
