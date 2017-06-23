package com.dam.modelo;
/**
 * Es una clase modelo de un administrador
 * @author Javier
 * @version 1
 *
 */
public class Administrador {
	private Integer idA;
    private String correo;
    private String pass;

    /**
     * Constructor vacio Administrador
     */
    public Administrador() {
    }
    
    
    /**
     * Constructor con parámetros Administrador
     * @param idA
     * @param correo
     * @param pass
     */
    public Administrador(Integer idA, String correo, String pass) {
		super();
		this.idA = idA;
		this.correo = correo;
		this.pass = pass;
	}



	public Administrador(Integer idA) {
        this.idA = idA;
    }

    public Integer getIdA() {
        return idA;
    }

    public void setIdA(Integer idA) {
        this.idA = idA;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
        hash += (idA != null ? idA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.idA == null && other.idA != null) || (this.idA != null && !this.idA.equals(other.idA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dam.pi.model.Administrador[ idA=" + idA + " ]";
    }

}
