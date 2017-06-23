package com.dam.modelo;
/**
 * Es una clase modelo de un carrito
 * @author Javier
 * @version 1
 * 
 */
public class Carrito {
	
	int id_ca;
	String nombre;
	int precio;
	String categoria;
	String tipo;
	 
	/**
	 * Constructor vacio Carrito
	 */
	public Carrito() {
		
	}
	/**
	 * Constructor con parametros Carrito
	 * 
	 * @param id_ca
	 * @param nombre
	 * @param precio
	 * @param categoria
	 * @param tipo
	 */
	public Carrito(int id_ca, String nombre, int precio, String categoria,
			String tipo) {
		super();
		this.id_ca = id_ca;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.tipo = tipo;
	}

	public int getId_ca() {
		return id_ca;
	}

	public void setId_ca(int id_ca) {
		this.id_ca = id_ca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Carrito [id_ca=" + id_ca + ", nombre=" + nombre + ", precio="
				+ precio + ", categoria=" + categoria + ", tipo=" + tipo + "]";
	}
	
	
	

}
