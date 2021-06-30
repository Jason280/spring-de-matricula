package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.entity.Horario;


public interface IHorarioService {
	public boolean insertar(Horario horario);
	public List<Horario> listar();
	public void eliminar(int idhorario);
	public boolean modificar (Horario horario);
	public Horario listarId(int idhorario);
}