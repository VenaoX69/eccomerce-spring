package com.curso.ecommerce.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.service.ProductoService;


// Se le indica a la app que la clas 'AdministradorController' es un controlador '@Controller'.
@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	
	@Autowired // Para que el @Controller haga su magia.
	private ProductoService productoService;
	
	@GetMapping("") // Se le indica a la app a que ruta se debe redireccionar cuando este en el localhost
	public String home(Model model) {
		List<Producto> productos = productoService.findAll(); // El findAll(), nos muestra todos los productos.
		model.addAttribute("productos", productos);
		
		return "administrador/home";
	}
}
