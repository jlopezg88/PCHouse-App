/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "SUGERENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sugerencia.findAll", query = "SELECT s FROM Sugerencia s"),
    @NamedQuery(name = "Sugerencia.findByIdCon", query = "SELECT s FROM Sugerencia s WHERE s.idCon = :idCon"),
    @NamedQuery(name = "Sugerencia.findByTipo", query = "SELECT s FROM Sugerencia s WHERE s.tipo LIKE :tipo"),
    @NamedQuery(name = "Sugerencia.findByAsunto", query = "SELECT s FROM Sugerencia s WHERE s.asunto = :asunto"),
    @NamedQuery(name = "Sugerencia.findByMensaje", query = "SELECT s FROM Sugerencia s WHERE s.mensaje = :mensaje")})
public class Sugerencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CON")
    private Integer idCon;
    @Size(max = 50)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 100)
    @Column(name = "ASUNTO")
    private String asunto;
    @Size(max = 300)
    @Column(name = "MENSAJE")
    private String mensaje;

    public Sugerencia() {
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
