package com.curso.ecommerce.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id //El atributo sera el id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Hace que el atributo sea autoincrementable.
	private Integer id;
	private String nombre;
	private String username;
	private String email;
	private String direccion;
	private String telefono;
	private String tipoUsua;
	private String password;
	
	
	//Se le va a indicar al programa que en algún momento de la aplicación va hacer uso de una lista de 'Producto':
	@OneToMany(mappedBy = "usuario") // 'usuario' es la variable de tipo 'Usuario' que se inicializo en 'Producto'
	private List<Producto> producto;
	
	// Uso de lista de 'Orden'.
	@OneToMany(mappedBy ="usuario") // 'usuario' es la variable de tipo 'Usuario' que se inicializo en 'Orden'
	private List<Orden> ordenes;
	
	// Constructor
	
	public Usuario() {
	}
	
	public Usuario(Integer id, String nombre, String username, String email, String direccion, String telefono,
			String tipoUsua, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.tipoUsua = tipoUsua;
		this.password = password;
	}
	
	
	//Get's and Set's.
	
	// Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	// Nombre
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	// Username
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	// Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Dirección
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	// Teléfono
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	// Tipo de usuario
	public String getTipoUsua() {
		return tipoUsua;
	}
	public void setTipoUsua(String tipoUsua) {
		this.tipoUsua = tipoUsua;
	}
	
	// Contraseña
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Lista 'Producto'
	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}
	
	
	// To String
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", email=" + email
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", tipoUsua=" + tipoUsua + ", password="
				+ password + "]";
	}

	
}
