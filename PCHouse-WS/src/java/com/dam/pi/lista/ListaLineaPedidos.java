/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.lista;

import com.dam.pi.model.Lineapedido;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jlopez
 */
@XmlRootElement
public class ListaLineaPedidos {
    
    private List<Lineapedido> lista;

    public ListaLineaPedidos(){
        
    }
    public ListaLineaPedidos(List<Lineapedido> lista) {
        this.lista = lista;
    }

    public List<Lineapedido> getLista() {
        return lista;
    }

    public void setLista(List<Lineapedido> lista) {
        this.lista = lista;
    }
    
    
}
