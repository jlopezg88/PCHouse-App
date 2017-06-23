package com.dam.listas;

import java.util.List;

import com.dam.modelo.Lineapedido;
/**
 * @author Javier
 * @version 1
 * 
 */
public class ListaLineaPedidos {
	
	  private List<Lineapedido> lista;

	  /**
	   * Constructor vacio ListaLineaPedidos
	   */
	    public ListaLineaPedidos(){
	        
	    }
	    /**
	     * Constructor con parametros ListaLineaPedidos
	     * 
	     * @param lista
	     */
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
