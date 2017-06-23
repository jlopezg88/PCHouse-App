package com.dam.listas;

import java.util.List;

import com.dam.modelo.Administrador;



/**
 *  @author Javier
 *  @version 1
 */
public class ListaAdministrador {
	
  	private List<Administrador> lista;

	    /**
    	 * Constructor vacio ListaAdministrador.
    	 */
    	public ListaAdministrador(){
	        
	    }
	    
    	/**
    	 * Constructor con parametros ListaAdministrador.
    	 *
    	 * @param lista the lista
    	 */
    	public ListaAdministrador(List<Administrador> lista) {
	        this.lista = lista;
	    }

	    
    	public List<Administrador> getLista() {
	        return lista;
	    }

	    
    	public void setLista(List<Administrador> lista) {
	        this.lista = lista;
	    }
}
