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
@Table(name = "LINEAPEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lineapedido.findAll", query = "SELECT l FROM Lineapedido l"),
    @NamedQuery(name = "Lineapedido.findByIdLp", query = "SELECT l FROM Lineapedido l WHERE l.idLp = :idLp"),
    @NamedQuery(name = "Lineapedido.findByNombre", query = "SELECT l FROM Lineapedido l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Lineapedido.findByPrecio", query = "SELECT l FROM Lineapedido l WHERE l.precio = :precio"),
    @NamedQuery(name = "Lineapedido.findByCategoria", query = "SELECT l FROM Lineapedido l WHERE l.categoria = :categoria"),
    @NamedQuery(name = "Lineapedido.findByTipo", query = "SELECT l FROM Lineapedido l WHERE l.tipo = :tipo"),
    @NamedQuery(name = "Lineapedido.findByCorreo", query = "SELECT l FROM Lineapedido l WHERE l.correo LIKE :correo"),
    @NamedQuery(name = "Lineapedido.findByTarjeta", query = "SELECT l FROM Lineapedido l WHERE l.tarjeta = :tarjeta")})
public class Lineapedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LP")
    private Integer idLp;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PRECIO")
    private Integer precio;
    @Size(max = 100)
    @Column(name = "CATEGORIA")
    private String categoria;
    @Size(max = 100)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 100)
    @Column(name = "CORREO")
    private String correo;
    @Size(max = 100)
    @Column(name = "TARJETA")
    private String tarjeta;

    public Lineapedido() {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lineapedido)) {
            return false;
        }
        Lineapedido other = (Lineapedido) object;
        if ((this.idLp == null && other.idLp != null) || (this.idLp != null && !this.idLp.equals(other.idLp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dam.pi.model.Lineapedido[ idLp=" + idLp + " ]";
    }
    
}
