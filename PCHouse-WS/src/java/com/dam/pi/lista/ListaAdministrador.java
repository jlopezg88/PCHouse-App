/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.lista;


import com.dam.pi.model.Administrador;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jlopez
 */
@XmlRootElement
public class ListaAdministrador {
    
    private List<Administrador> lista;

    public ListaAdministrador(){
        
    }
    public ListaAdministrador(List<Administrador> lista) {
        this.lista = lista;
    }

    public List<Administrador> getLista() {
        return lista;
    }

    public void setLista(List<Administrador> lista) {
        this.lista = lista;
    }
    
    
}
