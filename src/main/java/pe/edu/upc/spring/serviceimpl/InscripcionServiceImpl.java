package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.model.Inscripcion;
import pe.edu.upc.spring.repository.IInscripcionRepository;
import pe.edu.upc.spring.service.IInscripcionService;

@Service
public class InscripcionServiceImpl implements IInscripcionService{

	
	@Autowired
	private IInscripcionRepository mD;

	@Override
	@Transactional
	public boolean insertar(Inscripcion inscripcion) {
		Inscripcion objIns = mD.save(inscripcion);
		if (objIns == null)
			return false;
		else
			return true;
	}	
	

	@Override
	public List<Inscripcion> listar() {
		return mD.findAll();
	}

	@Override
	public void eliminar(int idInscripcion) {
		mD.deleteById(idInscripcion);		
	}
	
	@Override
	@Transactional	
	public boolean modificar(Inscripcion inscripcion) {
		boolean flag = false;
		try {
			mD.save(inscripcion);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}


	@Override
	@Transactional(readOnly=true)
	public Inscripcion listar_Id(int idInscripcion) {
		return mD.findById(idInscripcion).get();
	}


	@Override
	public List<Alumno> listar_alumno_x_curso(int idCurso) {
		return mD.listar_alumno_x_curso(idCurso);
	}


	@Override
	public List<Curso> listar_curso_x_alumno(int idAlumno) {
		return mD.listar_cursos_x_alumno(idAlumno);
	}


	@Override
	public List<Alumno> listar_alumno(int idInscripcion) {
	return mD.listar_alumnos(idInscripcion);
	}


}

