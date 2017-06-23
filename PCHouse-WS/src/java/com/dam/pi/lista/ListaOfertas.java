/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.lista;

import com.dam.pi.model.Oferta;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jlopez
 */
@XmlRootElement
public class ListaOfertas {
    
    private List<Oferta> lista;

    public ListaOfertas(){
        
    }
    public ListaOfertas(List<Oferta> lista) {
        this.lista = lista;
    }

    public List<Oferta> getLista() {
        return lista;
    }

    public void setLista(List<Oferta> lista) {
        this.lista = lista;
    }
    
    
}
