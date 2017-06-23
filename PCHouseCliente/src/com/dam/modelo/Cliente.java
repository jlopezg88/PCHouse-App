package com.dam.modelo;

/**
 * Es una clase modelo de un cliente
 * @author Javier
 * @version 1
 * 
 */
public class Cliente {

	private Integer idC;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String correo;
	private String telefono;
	private String pass;

	/**
	 * Constructor vacio Client
	 */
	public Cliente() {
	}

	/**
	 * Constructor con parámetros Cliente
	 * @param idC
	 * @param nombre
	 * @param apellidos
	 * @param direccion
	 * @param correo
	 * @param telefono
	 * @param pass
	 */
	public Cliente(Integer idC, String nombre, String apellidos,
			String direccion, String correo, String telefono, String pass) {
		super();
		this.idC = idC;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.pass = pass;
	}


	public Cliente(Integer idC) {
		this.idC = idC;
	}

	public Integer getIdC() {
		return idC;
	}

	public void setIdC(Integer idC) {
		this.idC = idC;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idC != null ? idC.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Cliente)) {
			return false;
		}
		Cliente other = (Cliente) object;
		if ((this.idC == null && other.idC != null)
				|| (this.idC != null && !this.idC.equals(other.idC))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dam.pi.model.Cliente[ idC=" + idC + " ]";
	}
}
