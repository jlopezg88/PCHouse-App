/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.lista;

import com.dam.pi.model.Cliente;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jlopez
 */
@XmlRootElement
public class ListaClientes {
    
    private List<Cliente> lista;

    public ListaClientes(){
        
    }
    public ListaClientes(List<Cliente> lista) {
        this.lista = lista;
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }
    
    
}
