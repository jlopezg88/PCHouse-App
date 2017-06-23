package com.dam.bbdd;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import com.dam.listas.ListaPedidos;
import com.dam.modelo.Pedido;

/**
 * Esta clase añade, modifica, elimina, busca y lista todos los datos almacenados en la tabla Pedido
 * 
 *@author Javier
 *@version 1
 */

public class CrudPedidos implements
		CrudableWS<Pedido, ListaPedidos> {

	@Override
	public void insert(Pedido t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "pedido/";
		rest.put(myURL, t, Pedido.class);
	}

	@Override
	public void update(Pedido t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "pedido/";
		rest.postForLocation(myURL, t);
	}

	@Override
	public void delete(Pedido t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		int i = t.getIdPe();
		String myURL = CrudableWS.URL + "pedido/";
		rest.delete(myURL+i);
	}

	@SuppressWarnings("finally")
	@Override
	public ListaPedidos findAll() {
		FutureTask<ListaPedidos> future = new FutureTask<ListaPedidos>(
				new Callable<ListaPedidos>() {
					@Override
					public ListaPedidos call() throws Exception {
						// TODO Auto-generated method stub
						RestTemplate rest = new RestTemplate();
						rest.getMessageConverters().add(
								new MappingJackson2HttpMessageConverter());
						String myURL = CrudableWS.URL + "pedido/";
						ListaPedidos l = rest.getForObject(myURL,
								ListaPedidos.class);
						return l;
					}
				});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(future);
		ListaPedidos l = null;
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
	public Pedido findByPK(Object id) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "pedido/";
		Pedido l = rest.getForObject(myURL+id, Pedido.class);
		return null;
	}



}
