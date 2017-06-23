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
@Table(name = "PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdPe", query = "SELECT p FROM Pedido p WHERE p.idPe = :idPe"),
    @NamedQuery(name = "Pedido.findByCorreo", query = "SELECT p FROM Pedido p WHERE p.correo LIKE :correo")})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PE")
    private Integer idPe;
    @Size(max = 200)
    @Column(name = "CORREO")
    private String correo;

    public Pedido() {
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
