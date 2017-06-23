package com.dam.listas;

import java.util.List;

import com.dam.modelo.Cliente;
/**
 * @author Javier
 * @version 1
 * 
 */
public class ListaClientes {
	
	  private List<Cliente> lista;

	  /**
	   * Constructor vacio ListaClientes
	   */
	    public ListaClientes(){
	        
	    }
	    /**
	     * Constructor con parametros ListaClientes
	     * 
	     * @param lista
	     */
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
