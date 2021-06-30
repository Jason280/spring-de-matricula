package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.entity.C_Alumno;
import pe.edu.upc.spring.entity.C_Usuario;

public interface IAlumnoService {

	public boolean insertar(C_Alumno alumno);

	public boolean modificar(C_Alumno alumno);

	public void eliminar(int idAlumno);

	public C_Alumno listar_Id(int idAlumno);

	List<C_Alumno> listar();

	List<C_Alumno> buscar_Nombre(String nombre);
	
	List<C_Alumno> buscar_Username(String username);

	public C_Alumno buscar_Alumno_por_Usuario(C_Usuario user); 

	public boolean modAl(String nombre, int id);
	
	public boolean eliminarNombre(C_Alumno alumno);
}
