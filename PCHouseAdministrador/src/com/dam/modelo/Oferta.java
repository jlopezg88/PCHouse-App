package com.dam.modelo;

/**
 * Es una clase modelo de una oferta
 * @author Javier
 * @version 1
 * 
 */
public class Oferta {
	private Integer idO;
	private String titulo;
	private String descripcion;

	/**
	 * Constructor vacion  Oferta
	 */
	public Oferta() {
	}
	
	
	/**
	 * Constructor con parámetros Oferta
	 * @param idO
	 * @param titulo
	 * @param descripcion
	 */
	public Oferta(Integer idO, String titulo, String descripcion) {
		super();
		this.idO = idO;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}



	public Oferta(Integer idO) {
		this.idO = idO;
	}

	public Integer getIdO() {
		return idO;
	}

	public void setIdO(Integer idO) {
		this.idO = idO;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idO != null ? idO.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Oferta)) {
			return false;
		}
		Oferta other = (Oferta) object;
		if ((this.idO == null && other.idO != null)
				|| (this.idO != null && !this.idO.equals(other.idO))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dam.pi.model.Oferta[ idO=" + idO + " ]";
	}

}
