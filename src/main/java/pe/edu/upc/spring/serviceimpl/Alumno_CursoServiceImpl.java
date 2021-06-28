package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.dao.IAlumno_CursoDAO;
import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Alumno_Curso;
import pe.edu.upc.spring.entity.C_Curso;
import pe.edu.upc.spring.service.IAlumno_CursoService;

@Service
public class Alumno_CursoServiceImpl implements IAlumno_CursoService {

	@Autowired
	private IAlumno_CursoDAO dAlumno_curso;

	@Override
	@Transactional
	public boolean insertar(C_Alumno_Curso alumno_curso) {
		C_Alumno_Curso objAlumno_curso = dAlumno_curso.save(alumno_curso);
		if (objAlumno_curso == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(C_Alumno_Curso alumno_curso) {
		boolean flag = false;
		try {
			dAlumno_curso.save(alumno_curso);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idAlumno_curso) {

		dAlumno_curso.delete(idAlumno_curso);

	}

	
	@Override
	@Transactional(readOnly=true)
	public C_Alumno_Curso listar_Id(int idAlumno_curso) {
		return dAlumno_curso.findOne(idAlumno_curso);
	}


	@Override
	@Transactional(readOnly=true)
	public List<C_Alumno_Curso> listar() {
		return dAlumno_curso.findAll();
	}
	

	@Override
	@Transactional(readOnly=true)
	public List<C_Alumno> listar_alumno_x_curso(int id_Curso)
	{
		return dAlumno_curso.listar_alumno_x_curso(id_Curso);
	}
	@Override
	@Transactional(readOnly=true)
	public List<C_Curso> listar_curso_x_alumno(int idAlumno)
	{
		return dAlumno_curso.listar_cursos_x_alumno(idAlumno);
	}
	@Override
	@Transactional(readOnly=true)
	public List<C_Alumno> listar_alumno(int idAlumno_curso)
	{
		return dAlumno_curso.listar_alumnos(idAlumno_curso);
	}

}
