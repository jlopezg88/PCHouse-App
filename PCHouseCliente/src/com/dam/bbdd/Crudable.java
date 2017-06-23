package com.dam.bbdd;

import java.util.List;


/**
 * 
 *Interfaz crudable
 *
 *@author Javier
 *@version 1
 *
 * @param <T> 
 */
public interface Crudable<T> {

	/**
	 * Insert.
	 *
	 * @param t 
	 * @return long
	 */
	public long insert(T t);
	
	/**
	 * Update.
	 *
	 * @param t 
	 * @return int
	 */
	public int update(T t);
	
	/**
	 * Delete.
	 *
	 * @param t
	 * @return int
	 */
	public int delete(T t);
	
	/**
	 * FindAll.
	 *
	 * @return list
	 */
	public List<T> findAll();
	
	/**
	 * FindByPk.
	 *
	 * @param pkvalue
	 * @return the
	 */
	public T findByPK(String pkvalue);
	
	
	
}
