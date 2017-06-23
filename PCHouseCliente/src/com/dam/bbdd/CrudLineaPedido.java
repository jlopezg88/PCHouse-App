package com.dam.bbdd;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.dam.listas.ListaLineaPedidos;
import com.dam.modelo.Lineapedido;


/**
 * Esta clase añade, modifica, elimina, busca y lista todos los datos almacenados en la tabla Lineapedido
 * 
 *@author Javier
 *@version 1
 */

public class CrudLineaPedido implements
		CrudableWS<Lineapedido, ListaLineaPedidos> {

	@Override
	public void insert(Lineapedido t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "lineapedido/";
		rest.put(myURL, t, Lineapedido.class);
	}

	@Override
	public void update(Lineapedido t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "lineapedido/";
		rest.postForLocation(myURL, t);
	}

	@Override
	public void delete(Lineapedido t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		int i = t.getIdLp();
		String myURL = CrudableWS.URL + "lineapedido/";
		rest.delete(myURL+i);
	}

	@Override
	public ListaLineaPedidos findAll() {
		FutureTask<ListaLineaPedidos> future = new FutureTask<ListaLineaPedidos>(
				new Callable<ListaLineaPedidos>() {
					@Override
					public ListaLineaPedidos call() throws Exception {
						// TODO Auto-generated method stub
						RestTemplate rest = new RestTemplate();
						rest.getMessageConverters().add(
								new MappingJackson2HttpMessageConverter());
						String myURL = CrudableWS.URL + "lineapedido/";
						ListaLineaPedidos l = rest.getForObject(myURL,
								ListaLineaPedidos.class);
						return l;
					}
				});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(future);
		ListaLineaPedidos l = null;
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
	public Lineapedido findByPK(Object id) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "lineapedido/";
		Lineapedido l = rest.getForObject(myURL+id, Lineapedido.class);
		return null;
	}
	public ListaLineaPedidos findByCorreo(final String correo) {
		FutureTask<ListaLineaPedidos> future = new FutureTask<ListaLineaPedidos>(
				new Callable<ListaLineaPedidos>() {
					@Override
					public ListaLineaPedidos call() throws Exception {
						// TODO Auto-generated method stub
						RestTemplate rest = new RestTemplate();
						rest.getMessageConverters().add(
								new MappingJackson2HttpMessageConverter());
						String myURL = CrudableWS.URL + "lineapedido/correo/";
						ListaLineaPedidos l = rest.getForObject(myURL+correo,
								ListaLineaPedidos.class);
						return l;
					}
				});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(future);
		ListaLineaPedidos l = null;
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
