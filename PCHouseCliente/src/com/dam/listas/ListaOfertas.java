package com.dam.listas;

import java.util.List;

import com.dam.modelo.Oferta;
/**
 * @author Javier
 * @version 1
 * 
 */
public class ListaOfertas {
	
	  private List<Oferta> lista;
	  
	  /**
	   * Constructor vacio ListaOfertas
	   */
	    public ListaOfertas(){
	        
	    }
	    /**
	     * Constructor con parametros ListaOfertas
	     * 
	     * @param lista
	     */
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
