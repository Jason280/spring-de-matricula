package pe.edu.upc.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.entity.Categoria;
import pe.edu.upc.spring.entity.Horario;


public interface IHorarioDAO extends JpaRepository<Horario, Integer>{
	
}