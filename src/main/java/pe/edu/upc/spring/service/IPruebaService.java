package pe.edu.upc.spring.service;

import java.util.List;


import pe.edu.upc.spring.entity.C_Docente;
import pe.edu.upc.spring.entity.C_Usuario;

public interface IPruebaService {

	public boolean insertar(C_Docente docente);

	public boolean modificar(C_Docente docente);

	public void eliminar(int idDocente);

	public C_Docente listar_Id(int idDocente);

	List<C_Docente> listar();
	List<C_Docente> buscar_Nombre(String Nombre);
	
	public C_Docente buscar_Docente_por_Usuario(C_Usuario user); 

	public boolean modDo(String Nombre, int id);
	

}
