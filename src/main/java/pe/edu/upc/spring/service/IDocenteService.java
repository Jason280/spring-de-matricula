package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.spring.model.Docente;

public interface IDocenteService {
	public boolean insertar(Docente docente);
	public List<Docente> listar();
	public void eliminar(int idDocente);
	public boolean modificar (Docente docente);
	List<Docente> buscarNombreDocente(String nameDocente);
	public Optional<Docente> listarId(int idDocente);
}