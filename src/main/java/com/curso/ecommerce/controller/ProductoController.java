package com.curso.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.ProductoService;
import com.curso.ecommerce.service.UploadFileService;



@Controller // Le indicamos al programa que esto va ser un controlador para que se pueda inyectar.
@RequestMapping("/productos") // Mapeado el controlador en la carpeta 'productos' que se encuentra en templates.
public class ProductoController {
	
	// Se declara una variable logger para pruebas.
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	// Se declara una variable de tipo 'ProductoService' para hacer uso de los metodos crud.
	// Se hace uso de '@Autowired' para que spring se encargue instanciar el motodo en el contenedor y no tener que hacerlo manualmente.
	@Autowired
	private ProductoService productoService;
	
	@Autowired // Esto es para inyectarla a la clase en la que esta'ProductoController'.
	// Para agregar una imagenes se crea una variable privada de tipo 'UploadFileService' que es la clase donde estan lo metodos.
	private UploadFileService upload;
	
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
	public String save(Producto producto,@RequestParam("img") MultipartFile file) throws IOException { // En el parametro 'MultipartFile file' se hace uso de la anotación propia de Spring '@RequestParam' donde se especificá de donde se está cargando la imagen, que en este caso la podemos encontrar en la template 'cretae.html' en parte de name="img".
		LOGGER.info("Este es el objecto 'Producto' de la vista {}", producto); // El uso del {} es para indicarle al programa que acontinuación va a venir una variable o a su vez sea un objecto.
		
		// Antes del guardado, debemos instanciar el usuario.
		Usuario u = new Usuario(1, "", "", "", "", "", "", "");
		
		// Le indicamos al programa que usuario 'u' debe agregar al campo de 'usuario' en la tabla 'Producto', 
		producto.setUsuario(u);
		
		// Lógica para subir la 'imagen' al servidor y guardar el nombre en la DB y en la tabla productos.
		// Vamos hacer validación para guardar la imagen de varia formas:
		if (producto.getId()==null) { // En la primera validación se va hacer cuando la imagen sea ingresada por 'primera vez' (Se crea el producto).
			String nombreImagen = upload.saveImage(file); // Recordar colocar el parametro 'file' en el constructor.
										// Se debe hacer uso una excepción para que funcione el 'upload.saveImage(file).
		
			producto.setImagen(nombreImagen); // Se le especificá al programa que al campo 'Image' del 'Producto' se le va agregar el 'nombre de la imagen'
		}else { // Lógica por si el producto ya existe y se necesitá editar, aquí existen 2 casos cuando se edite un producto pero se cargué la misma imagen o se cargué una diferente.
			if (file.isEmpty()) { // Primer caso, cuando se edite el producto pero se cargue la misma imagen.
				Producto p = new Producto();
				p = productoService.get(producto.getId()).get(); // El programa buscará el producto atraves del 'productoService', donde obtendrá el producto.
				producto.setImagen(p.getImagen()); // Se obtine la misma imagen y se le pasa al producto. 
			}else { // Caso en que se quiera editar la imagen con el producto
				String nombreImagen = upload.saveImage(file); // Recordar colocar el parametro 'file' en el constructor.
				// Se debe hacer uso una excepción para que funcione el 'upload.saveImage(file).
			}
		}
		
		
		
		productoService.save(producto); // Hacemos uso del metodo 'save()' creado en el paquete 'service' en el archivo interfaz 'ProductoService.java'
		
		return "redirect:/productos"; // Se le indica al programa que debe hacer una petición 'redirect' a la tabla 'productos'.
		// NOTA: Debemos verificar que en la clase de producto, se encuentre el To_String()
	}
	
	// Metodo para editar el 'producto'.
	@GetMapping("edit/{id}") // Se obtiene un mapeo de la template 'edit' y se indica al programa que va a recibir un campo de tipo id.
	public String edit(@PathVariable Integer id, Model model) { // Se declara una variable para poder recibir el 'id', y anteponemos la anotación '@PathVariable' que es propia de spring para pasarle el id que se necesita desde el '@GetMapping', uso del 'Model' para mostrar información.
		Producto producto = new Producto(); // En esta variable el sistema va a guardar el objecto buscado.
		Optional<Producto> optionalProducto=productoService.get(id); // Esto es lo que devuelve cuando se hace una consulta de un tipo producto.
		producto = optionalProducto.get(); // Esta linea permite obtener el como tal el producto que se mando a buscar.
		
		// Vamos a verificar que se este obteniendo correctamente el 'producto'.
		LOGGER.info("Producto buscado: {}", producto); // Se le pasa la variable con el 'producto' para que se muestre en la consola.
		
		//
		model.addAttribute("producto", producto);
		
		return "productos/edit";
	}
	
	@PostMapping("/updated")
	public String updated(Producto producto) {
		productoService.update(producto);
		return "redirect:/productos";
	}
	
	
	// Metodo para eliminar un producto.
	@GetMapping("delete/{id}") // Se le indica al programa 
	public String delete(@PathVariable Integer id) {
		productoService.delate(id);
		return "redirect:/productos";
	}
	
	// Lógica para subir la imagen y ponerle nombre.
}
