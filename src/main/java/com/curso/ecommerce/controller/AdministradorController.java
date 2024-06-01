package com.curso.ecommerce.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Se le indica a la app que la clas 'AdministradorController' es un controlador '@Controller'.
@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	
	@GetMapping("") // Se le indica a la app a que ruta se debe redireccionar cuando este en el localhost
	public String home() {
		return "administrador/home";
	}
}
