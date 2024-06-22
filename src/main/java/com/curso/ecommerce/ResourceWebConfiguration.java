package com.curso.ecommerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Clase path para apuntar desde cualquier lugar las imagenes
public class ResourceWebConfiguration implements WebMvcConfigurer { // Se hace uso de una interfaz ya definida 'WebMvcConfigurer'.
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		registry.addResourceHandler("/images/**").addResourceLocations("file:images/"); // Se le indica a la aplicación que cuando este en el directorio de las imagenes, va a seleccionar todo'**'.
	}
}


