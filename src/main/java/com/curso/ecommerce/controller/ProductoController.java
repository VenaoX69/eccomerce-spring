package com.curso.ecommerce.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.ProductoService;



@Controller // Le indicamos al programa que esto va ser un controlador para que se pueda inyectar.
@RequestMapping("/productos") // Mapeado el controlador en la carpeta 'productos' que se encuentra en templates.
public class ProductoController {
	
	// Se declara una variable logger para pruebas.
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	// Se declara una variable de tipo 'ProductoService' para hacer uso de los metodos crud.
	// Se hace uso de '@Autowired' para que spring se encargue instanciar el motodo en el contenedor y no tener que hacerlo manualmente.
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("") // Se hace uso de esto para que se mapee correctamente a 'productos'.
	public String show(Model model) { // El 'Model' nos permite llevar información del Backen hacia la vista.
		model.addAttribute("productos", productoService.findAll()); // Se le indica al programa el nombre 'productos' y va a tener el metodo 'findAll()' del archivo 'ProductoService'.
		return "productos/show";
	}
	
	// Se crea un metodo para que pueda acceder a la vista de 'crear_producto'.
	// Tambien se hace uso del controlador @GetMapping para indicar o mapear el nombre o ruta de la vista para crear productos. 
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	
	// Metodos para recibir y postear los datos ingresados desde la template 'create' hacia la tabla de 'Producto' en la base de datos.
	// El objecto va a pertenecer a un tipo '@PostMaping'
	@PostMapping("/save") // Mapeamos el metodo como 'save', pero ese nombre puede variar según la necesidad.
	public String save(Producto producto) {
		LOGGER.info("Este es el objecto 'Producto' de la vista {}", producto); // El uso del {} es para indicarle al programa que acontinuación va a venir una variable o a su vez sea un objecto.
		
		// Antes del guardado, debemos instanciar el usuario.
		Usuario u = new Usuario(1, "", "", "", "", "", "", "");
		
		// Le indicamos al programa que usuario 'u' debe agregar al campo de 'usuario' en la tabla 'Producto', 
		producto.setUsuario(u);
		
		productoService.save(producto); // Hacemos uso del metodo 'save()' creado en el paquete 'service' en el archivo interfaz 'ProductoService.java'
		
		return "redirect:/productos"; // Se le indica al programa que debe hacer una petición 'redirect' a la tabla 'productos'.
		// NOTA: Debemos verificar que en la clase de producto, se encuentre el To_String()
	}
	
}
