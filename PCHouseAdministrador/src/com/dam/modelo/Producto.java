package com.dam.modelo;

/**
 * Es una clase modelo de un producto
 * @author Javier
 * @version 1
 * 
 */

public class Producto {

	private Integer idP;
	private String nombre;
	private Integer precio;
	private String categoria;
	private String tipo;

	/**
	 * Constructor vacio Producto
	 */
	public Producto() {
	}

	/**
	 * Constructor con parámetros producto
	 * @param idP
	 * @param nombre
	 * @param precio
	 * @param categoria
	 * @param tipo
	 */
	public Producto(Integer idP, String nombre, Integer precio,
			String categoria, String tipo) {
		super();
		this.idP = idP;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.tipo = tipo;
	}


	public Producto(Integer idP) {
		this.idP = idP;
	}

	public Integer getIdP() {
		return idP;
	}

	public void setIdP(Integer idP) {
		this.idP = idP;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
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
	public int hashCode() {
		int hash = 0;
		hash += (idP != null ? idP.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Producto)) {
			return false;
		}
		Producto other = (Producto) object;
		if ((this.idP == null && other.idP != null)
				|| (this.idP != null && !this.idP.equals(other.idP))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dam.pi.model.Producto[ idP=" + idP + " ]";
	}

}
