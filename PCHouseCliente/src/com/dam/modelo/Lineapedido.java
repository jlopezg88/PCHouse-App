package com.dam.modelo;

/**
 * Es una clase modelo de una lineapedido
 * @author Javier
 * @version 1
 * 
 */
public class Lineapedido {
	private Integer idLp;
	private String nombre;
	private Integer precio;
	private String categoria;
	private String tipo;
	private String correo;
	private String tarjeta;

	/**
	 * Constructor vacio Lineapedido
	 */
	public Lineapedido() {
	}

	/**
	 * Constructor con parámetros Lineapedido
	 * @param idLp
	 * @param nombre
	 * @param precio
	 * @param categoria
	 * @param tipo
	 * @param correo
	 */
	public Lineapedido(Integer idLp, String nombre, Integer precio,
			String categoria, String tipo, String correo, String tarjeta) {
		super();
		this.idLp = idLp;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.tipo = tipo;
		this.correo = correo;
		this.tarjeta = tarjeta;
	}


	public Lineapedido(Integer idLp) {
		this.idLp = idLp;
	}

	public Integer getIdLp() {
		return idLp;
	}

	public void setIdLp(Integer idLp) {
		this.idLp = idLp;
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

	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idLp != null ? idLp.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Lineapedido)) {
			return false;
		}
		Lineapedido other = (Lineapedido) object;
		if ((this.idLp == null && other.idLp != null)
				|| (this.idLp != null && !this.idLp.equals(other.idLp))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dam.pi.model.Lineapedido[ idLp=" + idLp + " ]";
	}
}
