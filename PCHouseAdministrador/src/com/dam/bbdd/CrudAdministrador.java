package com.dam.bbdd;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.dam.listas.ListaAdministrador;
import com.dam.modelo.Administrador;


public class CrudAdministrador implements CrudableWS<Administrador, ListaAdministrador> {

	@Override
	public void insert(Administrador t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "administrador/";
		rest.put(myURL, t, Administrador.class);
		
	}

	@Override
	public void update(Administrador t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "administrador/";
		rest.postForLocation(myURL, t);
		
	}

	@Override
	public void delete(Administrador t) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		int i = t.getIdA();
		String myURL = CrudableWS.URL + "administrador/";
		rest.delete(myURL+i);
		
	}

	@Override
	public ListaAdministrador findAll() {
		FutureTask<ListaAdministrador> future = new FutureTask<ListaAdministrador>(
				new Callable<ListaAdministrador>() {
					@Override
					public ListaAdministrador call() throws Exception {
						// TODO Auto-generated method stub
						RestTemplate rest = new RestTemplate();
						rest.getMessageConverters().add(
								new MappingJackson2HttpMessageConverter());
						String myURL = CrudableWS.URL + "administrador/";
						ListaAdministrador l = rest.getForObject(myURL,
								ListaAdministrador.class);
						return l;
					}
				});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(future);
		ListaAdministrador l = null;
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
	public Administrador findByPK(Object id) {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String myURL = CrudableWS.URL + "producto/";
		Administrador l = rest.getForObject(myURL+id, Administrador.class);
		return null;
	}
	public ListaAdministrador findByCorreoAndPass(final String correo, final String pass){
		FutureTask<ListaAdministrador> future = new FutureTask<ListaAdministrador>(
				new Callable<ListaAdministrador>() {
					@Override
					public ListaAdministrador call() throws Exception {
						// TODO Auto-generated method stub
						RestTemplate rest = new RestTemplate();
						rest.getMessageConverters().add(
								new MappingJackson2HttpMessageConverter());
						String myURL = CrudableWS.URL + "administrador/comprobar/";
						ListaAdministrador l = rest.getForObject(myURL+"javierlopezg88@gmail.com/1234",
								ListaAdministrador.class);
						return l;
					}
				});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(future);
		ListaAdministrador l = null;
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
