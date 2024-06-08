package com.curso.ecommerce.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


// Se crean 2 metodos, 1 para subir una imagen y otro para eliminarla.

@Service
public class UploadFileService {
	private String folder="images//"; // Para la optimzación de recursos en la DB, la imagen se va a guardar en el proyecto.
							// Se va a crear una carpeta'images' en la raíz del proyecto.
	
	// Creación del método agregar:
	public String saveImage(MultipartFile file) throws IOException { // Se le agrega como parametro un tipo de 'MultipartFile'.
		if (file.isEmpty()) { // Aquí se hace una validación de 'si' el parametro 'file' viene con con contenido.
			// Se pasará la imagen a 'bytes' para que se pueda enviarse desde el cliente al servidor, es decir vamos a convertir la imagen en 0 y 1 para que se pueda enviar de un lado a otro.
			byte [] bytes = file.getBytes(); // Tenemos que hacer una captura de excepción en caso que la haya.
			Path path = Paths.get(folder+file.getOriginalFilename()); // Se le indicá al programa que va a guadar la imagen en la carpeta del path que se creó.
			Files.write(path, bytes); // La aplicación va escribir en el directorio'Path' lo que se le esta pasando'bytes'.
			return file.getOriginalFilename(); // Se retona el nombre que tiene la imagen.
		}
		return "default.jpg"; // Esto es en caso que el usuario no le pase nada al parametro.
	}
	
	// Creación del método elimar:
	public void deleteImage(String nombre) {
		String ruta = "images//";
		File file = new File(ruta+nombre); // Recordar importar el paquete de File de 'import java.io.File;'
		file.delete();
	}
}
