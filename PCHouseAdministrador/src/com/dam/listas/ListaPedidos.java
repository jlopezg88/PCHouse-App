package com.dam.listas;

import java.util.List;

import com.dam.modelo.Pedido;


/**
 *  @author Javier
 *  @version 1
 */
public class ListaPedidos {
	
  	private List<Pedido> lista;

	    /**
    	 * Constructor vacio listaPedidos.
    	 */
    	public ListaPedidos(){
	        
	    }
	    
    	/**
    	 * Constructor con parametros listaPedidos.
    	 *
    	 * @param lista the lista
    	 */
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
