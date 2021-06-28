package pe.edu.upc.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.spring.entity.Seccion;


public interface ISeccionDAO extends JpaRepository<Seccion, Integer>{
	
}