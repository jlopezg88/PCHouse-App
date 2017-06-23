package com.dam.listas;

import java.util.List;

import com.dam.modelo.Cliente;

/**
 *  @author Javier
 *  @version 1
 */
public class ListaClientes {
	
	  
  	private List<Cliente> lista;

	    /**
    	 * Constructor vacio listaClientes.
    	 */
    	public ListaClientes(){
	        
	    }
	    
    	/**
    	 * Constructor con parametros listaClientes.
    	 *
    	 * @param lista the lista
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
