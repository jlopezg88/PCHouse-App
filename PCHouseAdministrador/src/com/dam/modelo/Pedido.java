package com.dam.modelo;
/**
 * Es una clase modelo de un pedido
 * @author Javier
 * @version 1
 * 
 */
public class Pedido {
	
	private Integer idPe;
    private String correo;

    /**
     * Constructor vacio Pedido
     */
    public Pedido() {
    }
    
    
    /**
     * Constructor con parámetros pedido
     * @param idPe
	 * @param correo
	 */
    public Pedido(Integer idPe,  String correo) {
		super();
		this.idPe = idPe;
		this.correo = correo;
	}



	public Pedido(Integer idPe) {
        this.idPe = idPe;
    }

    public Integer getIdPe() {
        return idPe;
    }

    public void setIdPe(Integer idPe) {
        this.idPe = idPe;
    }

    
    public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idPe != null ? idPe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPe == null && other.idPe != null) || (this.idPe != null && !this.idPe.equals(other.idPe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dam.pi.model.Pedido[ idPe=" + idPe + " ]";
    }

}
