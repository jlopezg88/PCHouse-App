package com.dam.modelo;
/**
 * Es una clase modelo de una sugerencia
 * @author Javier
 * @version 1
 * 
 */
public class Sugerencia {

	private Integer idCon;
    private String tipo;
    private String asunto;
    private String mensaje;

    /**
     * Constructor vacio Sugerencia
     */
    public Sugerencia() {
    }
    
    
    /**
     * Constructor con parámetros sugerencia
     * @param idCon
     * @param tipo
     * @param asunto
     * @param mensaje
     */
    public Sugerencia(Integer idCon, String tipo, String asunto, String mensaje) {
		super();
		this.idCon = idCon;
		this.tipo = tipo;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}



	public Sugerencia(Integer idCon) {
        this.idCon = idCon;
    }

    public Integer getIdCon() {
        return idCon;
    }

    public void setIdCon(Integer idCon) {
        this.idCon = idCon;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCon != null ? idCon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sugerencia)) {
            return false;
        }
        Sugerencia other = (Sugerencia) object;
        if ((this.idCon == null && other.idCon != null) || (this.idCon != null && !this.idCon.equals(other.idCon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dam.pi.model.Sugerencia[ idCon=" + idCon + " ]";
    }
}
