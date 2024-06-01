package com.curso.ecommerce.service;

import java.util.List;
import java.util.Optional;
import com.curso.ecommerce.model.Producto;

// Aquí se definiran los metodos CRUD para la clase o tabla 'Producto'
public interface ProductoService {
	public Producto save(Producto producto); // Metodo para guardar el producto.
	public Optional<Producto> get(Integer id); // Optional nos ayuda a identificar si el objecto de la base de datos existe, y 'get' para obtener en este caso el 'id'.
	public void update(Producto producto); // Metodo para 'actualizar' el producto.
	public void delate(Integer id); // Metodo para 'eliminar' el producto.
	public List<Producto> findAll(); // Metodo para mostrar los productos.
}
