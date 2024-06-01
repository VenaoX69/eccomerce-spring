package com.curso.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.repository.ProductoRepository;

@Service //Esto se hace con el fin de que nos permita inyectar en el controlador y hacer uso de los metodos CRUD.
public class ProductoServiceImpl implements ProductoService { // Hacemos uso de la implementación de la 'interfaz' con el CRUD.

	@Autowired // Esto le permite a la aplicación saber que se le esta inyectando un 'objecto'.
	private ProductoRepository productoRepository;
	
	// Se implementan los metodos.
	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		return productoRepository.findById(id);
	}

	@Override
	public void update(Producto producto) {
		productoRepository.save(producto);
	}

	@Override
	public void delate(Integer id) {
		productoRepository.deleteById(id);
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

}
