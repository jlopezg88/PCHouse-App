package com.dam.bbdd;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.dam.listas.ListaClientes;
import com.dam.modelo.Cliente;


/**
 * Esta clase añade, modifica, elimina, busca y lista todos los datos almacenados en la tabla Cliente
 * 
 *@author Javier
 *@version 1
 */

public class CrudClientes implements
		CrudableWS<Cliente, ListaClientes> {


	@Override
	public void insert(Cliente t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "cliente/";
		rest.put(myURL, t, Cliente.class);
	}


	@Override
	public void update(Cliente t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "cliente/";
		rest.postForLocation(myURL, t);
	}


	@Override
	public void delete(Cliente t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		int i = t.getIdC();
		String myURL = CrudableWS.URL + "cliente/";
		rest.delete(myURL+i);
	}


	@Override
	public ListaClientes findAll() {
		FutureTask<ListaClientes> future = new FutureTask<ListaClientes>(
				new Callable<ListaClientes>() {
					@Override
					public ListaClientes call() throws Exception {
						// TODO Auto-generated method stub
						RestTemplate rest = new RestTemplate();
						rest.getMessageConverters().add(
								new MappingJackson2HttpMessageConverter());
						String myURL = CrudableWS.URL + "cliente/";
						ListaClientes l = rest.getForObject(myURL,
								ListaClientes.class);
						return l;
					}
				});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(future);
		ListaClientes l = null;
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
	public Cliente findByPK(Object id) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "cliente/";
		Cliente l = rest.getForObject(myURL+id, Cliente.class);
		return null;
	}



}
