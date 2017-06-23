/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.lista;

import com.dam.pi.model.Sugerencia;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jlopez
 */
@XmlRootElement
public class ListaSugerencias {
    
    private List<Sugerencia> lista;

    public ListaSugerencias(){
        
    }
    public ListaSugerencias(List<Sugerencia> lista) {
        this.lista = lista;
    }

    public List<Sugerencia> getLista() {
        return lista;
    }

    public void setLista(List<Sugerencia> lista) {
        this.lista = lista;
    }
    
    
}
