package com.curso.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.curso.ecommerce.model.Producto;

// Extendemos a JpaRepository con la tabla o clase 'Producto' y el campo a manejar que es el 'ID' pero haremos uso del Integer.
// Y después hacemos uso de la anotación '@Repository' para que la interfaz se pueda inyectar.
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
