/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.lista;

import com.dam.pi.model.Producto;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jlopez
 */
@XmlRootElement
public class ListaProductos {
    
    private List<Producto> lista;

    public ListaProductos(){
        
    }
    public ListaProductos(List<Producto> lista) {
        this.lista = lista;
    }

    public List<Producto> getLista() {
        return lista;
    }

    public void setLista(List<Producto> lista) {
        this.lista = lista;
    }
    
    
}
