package com.curso.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired // Esta anotación para que lo inyecte al controlador del framework
	private ProductoService productoService;
	
	//Crear metodo para el home de usuario
	@GetMapping("")
	public String home(Model model){ // Se hace uso de un parametro "Model" para que pueda llevar la información de los productos a la vista.
		
		model.addAttribute("productos", productoService.findAll()); // El metodo findAll, trae todos los productos existentes.
		
		return "usuario/home";
	}
}
