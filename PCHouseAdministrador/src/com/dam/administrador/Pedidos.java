package com.dam.administrador;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.dam.adaptador.AdaptadorPedido;
import com.dam.bbdd.CrudPedidos;

import com.dam.listas.ListaPedidos;
import com.dam.modelo.Pedido;

/**
 * Realiza un listado de todos los pedidos
 * 
 * @author Javier
 * @version 1
 */
@SuppressLint("NewApi")
public class Pedidos extends Activity {

	ListView lista;
	ArrayList<Pedido> gestorPedido;
	static ListaPedidos l;
	static CrudPedidos cPed;
	static AdaptadorPedido adaptadorPedido;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pedidos);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		lista = (ListView) findViewById(R.id.listViewPedidos);

		// listaCliente.add(new Cliente(1, "Javier", "López", "calle santo rey",
		// "954534521","javi@gmail.com","1234"));
		// listaCliente.add(new Cliente(1, "David", "Romero", "calle falsa ",
		// "954534543","David@gmail.com","1234"));

		// Creo el crudPedido para poder hacer las diferentes gestion de los
		// pedidos de la bbdd
		cPed = new CrudPedidos();
		// Creo un arrayList de pedido para cargar todos los pedidos de la bbdd
		gestorPedido = new ArrayList<Pedido>();
		// Creamos el adaptador para los pedidos
		adaptadorPedido = new AdaptadorPedido(this, gestorPedido);
		// Solicitamos la lista de la bbdd
		solicitaLista();
		// Actualizamos la lista por si ha habido algún cambio
		actualizaLista(l);
		// Le adaptamos a la lista nuestro adaptador
		lista.setAdapter(adaptadorPedido);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed
			// in the Action Bar.
			Intent parentActivityIntent = new Intent(this, MainActivity.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Obtiene la lista de la Base de datos y la recorre entera.
	 */
	public static void solicitaLista() {
		cPed = new CrudPedidos();
		l = cPed.findAll();
	}

	/**
	 * Actualiza el adaptador de la lista por si a habido algún cambio.
	 * 
	 * @param l es una ListaPedidos
	 */
	public static void actualizaLista(ListaPedidos l) {

		adaptadorPedido.clear();
		adaptadorPedido.addAll(l.getLista());
		adaptadorPedido.notifyDataSetChanged();
	}
}
