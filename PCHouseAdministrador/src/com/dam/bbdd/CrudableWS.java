package com.dam.bbdd;

/**
 * Interfaz CrudableWS.
 *@author Javier
 *@version 1
 * @param <T> 
 * @param <L> 
 */
public interface CrudableWS<T,L>{
	
	/**Es una URL constante. */
	public final static String URL = "http://192.168.1.34:8080/PCHouse-WS/api/";
	//public final static String URL = "http://172.27.60.116:8080/PCHouse-WS/api/";

	/**
	 * Insert.
	 *
	 * @param  t
	 */
	public void insert(T t);
	
	/**
	 * Update.
	 *
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * Delete.
	 *
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * FindAll.
	 *
	 * @return l
	 */
	public L findAll();
	
	/**
	 * FindByPk.
	 *
	 * @param id the id
	 * @return the t
	 */
	public T findByPK(Object id);
}
