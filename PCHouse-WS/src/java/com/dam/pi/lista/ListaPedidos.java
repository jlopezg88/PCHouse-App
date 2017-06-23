/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.lista;

import com.dam.pi.model.Pedido;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jlopez
 */
@XmlRootElement
public class ListaPedidos {
    
    private List<Pedido> lista;

    public ListaPedidos(){
        
    }
    public ListaPedidos(List<Pedido> lista) {
        this.lista = lista;
    }

    public List<Pedido> getLista() {
        return lista;
    }

    public void setLista(List<Pedido> lista) {
        this.lista = lista;
    }
    
    
}
