package com.dam.listas;

import java.util.List;

import com.dam.modelo.Producto;
/**
 * @author Javier
 * @version 1
 * 
 */
public class ListaProductos {
	
	  private List<Producto> lista;
	  /**
	   * Constructor vacio ListaProductos
	   */
	    public ListaProductos(){
	        
	    }
	    /**
	     * Constructor con parametros ListaProductos
	     * 
	     * @param lista
	     */
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
