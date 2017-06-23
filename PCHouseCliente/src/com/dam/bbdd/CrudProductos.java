package com.dam.bbdd;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.dam.listas.ListaProductos;
import com.dam.modelo.Producto;
/**
 * Esta clase añade, modifica, elimina, busca y lista todos los datos almacenados en la tabla Producto
 * 
 *@author Javier
 *@version 1
 */

public class CrudProductos implements
		CrudableWS<Producto, ListaProductos> {

	@Override
	public void insert(Producto t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "producto/";
		rest.put(myURL, t, Producto.class);
	}

	@Override
	public void update(Producto t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "producto/";
		rest.postForLocation(myURL, t);
	}

	@Override
	public void delete(Producto t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		int i = t.getIdP();
		String myURL = CrudableWS.URL + "producto/";
		rest.delete(myURL+i);
	}

	@SuppressWarnings("finally")
	@Override
	public ListaProductos findAll() {
		FutureTask<ListaProductos> future = new FutureTask<ListaProductos>(
				new Callable<ListaProductos>() {
					@Override
					public ListaProductos call() throws Exception {
						// TODO Auto-generated method stub
						RestTemplate rest = new RestTemplate();
						rest.getMessageConverters().add(
								new MappingJackson2HttpMessageConverter());
						String myURL = CrudableWS.URL + "producto/";
						ListaProductos l = rest.getForObject(myURL,
								ListaProductos.class);
						return l;
					}
				});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(future);
		ListaProductos l = null;
		try {
			
			l = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
			return l;
		}
	}

	@Override
	public Producto findByPK(Object id) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "producto/";
		Producto l = rest.getForObject(myURL+id, Producto.class);
		return null;
	}


	@SuppressWarnings("finally")
	public ListaProductos findByTipo(final String categoria, final String tipo){
		
		
		FutureTask<ListaProductos> future = new FutureTask<ListaProductos>(
				new Callable<ListaProductos>() {
					@Override
					public ListaProductos call() throws Exception {
						// TODO Auto-generated method stub
						RestTemplate rest = new RestTemplate();
						rest.getMessageConverters().add(
								new MappingJackson2HttpMessageConverter());
						String myURL = CrudableWS.URL + "producto/categoria/";
						ListaProductos l = rest.getForObject(myURL+categoria+"/"+tipo,
								ListaProductos.class);
						return l;
					}
				});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(future);
		ListaProductos l = null;
		try {
			
			l = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
			return l;
		}
	}

}
