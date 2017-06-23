package com.dam.listas;

import java.util.List;

import com.dam.modelo.Sugerencia;
/**
 * 
 * @author Javier
 * @version 1
 *
 */
public class ListaSugerencias {
	
	  private List<Sugerencia> lista;
	  /**
	   * Constructot vacio ListaSugerencias
	   */
	    public ListaSugerencias(){
	        
	    }
	    /**
	     * Constructor con parametros ListaSugerencias
	     * @param lista
	     */
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
