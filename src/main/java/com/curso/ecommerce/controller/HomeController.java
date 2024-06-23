package com.curso.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(HomeController.class); // "import org.slf4j.Logger;", "import org.slf4j.LoggerFactory;", esta variable logger se hace con el fin de hacer pruebas/testeos de lo que se tiene en las variables.
	
	@Autowired // Esta anotación para que lo inyecte al controlador del framework
	private ProductoService productoService;
	
	//Crear metodo para el home de usuario
	@GetMapping("")
	public String home(Model model){ // Se hace uso de un parametro "Model" para que pueda llevar la información de los productos a la vista.
		
		model.addAttribute("productos", productoService.findAll()); // El metodo findAll, trae todos los productos existentes.
		
		return "usuario/home";
	}
	
	// Metodo para llevar al usuario a ver la informacion del producto
	@GetMapping("productohome/{id}") // El método va a resporder a peticiones de tipo get; y se va a llamar "productohome" y va a tener un parametro "{id}" para poder usarlo.
	public String productoHome(@PathVariable Integer id) { // Se usa "@PathVariable" para que tome la variable del "GetMapping".
		
		log.info("Id producto enviado como parámetro {}", id);
		
		return "usuario/productohome";
	}
}
