package com.dam.bbdd;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.dam.listas.ListaSugerencias;
import com.dam.modelo.Sugerencia;


/**
 * Esta clase añade, modifica, elimina, busca y lista todos los datos almacenados en la tabla Sugerencia
 * 
 *@author Javier
 *@version 1
 */

public class CrudSugerencias implements
		CrudableWS<Sugerencia, ListaSugerencias> {

	@Override
	public void insert(Sugerencia t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "sugerencia/";
		rest.put(myURL, t, Sugerencia.class);
	}

	@Override
	public void update(Sugerencia t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "sugerencia/";
		rest.postForLocation(myURL, t);
	}

	@Override
	public void delete(Sugerencia t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		int i = t.getIdCon();
		String myURL = CrudableWS.URL + "sugerencia/";
		rest.delete(myURL+i);
	}

	@SuppressWarnings("finally")
	@Override
	public ListaSugerencias findAll() {
		FutureTask<ListaSugerencias> future = new FutureTask<ListaSugerencias>(
				new Callable<ListaSugerencias>() {
					@Override
					public ListaSugerencias call() throws Exception {
						// TODO Auto-generated method stub
						RestTemplate rest = new RestTemplate();
						rest.getMessageConverters().add(
								new MappingJackson2HttpMessageConverter());
						String myURL = CrudableWS.URL + "sugerencia/";
						ListaSugerencias l = rest.getForObject(myURL,
								ListaSugerencias.class);
						return l;
					}
				});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(future);
		ListaSugerencias l = null;
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
	public Sugerencia findByPK(Object id) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "sugerencia/";
		Sugerencia l = rest.getForObject(myURL+id, Sugerencia.class);
		return null;
	}



}
