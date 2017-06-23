package com.dam.bbdd;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.dam.listas.ListaOfertas;
import com.dam.listas.ListaProductos;
import com.dam.modelo.Oferta;
/**
 * Esta clase añade, modifica, elimina, busca y lista todos los datos almacenados en la tabla Oferta
 * 
 *@author Javier
 *@version 1
 */

public class CrudOfertas implements
		CrudableWS<Oferta, ListaOfertas> {

	@Override
	public void insert(Oferta t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "oferta/";
		rest.put(myURL, t, Oferta.class);
	}

	@Override
	public void update(Oferta t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "oferta/";
		rest.postForLocation(myURL, t);
	}

	@Override
	public void delete(Oferta t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		int i = t.getIdO();
		String myURL = CrudableWS.URL + "oferta/";
		rest.delete(myURL+i);
	}

	@SuppressWarnings("finally")
	@Override
	public ListaOfertas findAll() {
		FutureTask<ListaOfertas> future = new FutureTask<ListaOfertas>(
				new Callable<ListaOfertas>() {
					@Override
					public ListaOfertas call() throws Exception {
						// TODO Auto-generated method stub
						RestTemplate rest = new RestTemplate();
						rest.getMessageConverters().add(
								new MappingJackson2HttpMessageConverter());
						String myURL = CrudableWS.URL + "oferta/";
						ListaOfertas l = rest.getForObject(myURL,
								ListaOfertas.class);
						return l;
					}
				});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(future);
		ListaOfertas l = null;
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
	public Oferta findByPK(Object id) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "oferta/";
		Oferta l = rest.getForObject(myURL+id, Oferta.class);
		return null;
	}



}
