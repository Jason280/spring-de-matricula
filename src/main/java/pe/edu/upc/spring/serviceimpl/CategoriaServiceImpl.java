package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.entity.Categoria;
import pe.edu.upc.spring.dao.ICategoriaDAO;
import pe.edu.upc.spring.service.ICategoriaService;


@Service
public class CategoriaServiceImpl implements ICategoriaService {

	
	@Autowired
	private ICategoriaDAO mD;

	@Override
	@Transactional
	public boolean insertar(Categoria categoria) {
		Categoria objCat = mD.save(categoria);
		if (objCat == null)
			return false;
		else
			return true;	
	}

	@Override
	public List<Categoria> listar() {
		return mD.findAll();
	}

	@Override
	@Transactional
	public void eliminar(int idcategoria) {
			mD.delete(idcategoria);
	}
	
	@Override
	public List<Categoria> buscarNombreCategoria(String nameCategoria) {
		return mD.buscarNombreCategoria(nameCategoria);
	}
	
	@Override
	public boolean modificar(Categoria categoria) {
		boolean flag = false;
		try {
			mD.save(categoria);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria listarId(int idcategoria) {
		return  mD.findOne(idcategoria);
	}


}
