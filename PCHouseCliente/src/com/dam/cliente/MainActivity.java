package com.dam.cliente;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.dam.R;
import com.dam.adaptador.AdaptadorOfertas;
import com.dam.bbdd.CrudOfertas;
import com.dam.listas.ListaOfertas;
import com.dam.modelo.Oferta;
import com.dam.preferencia.Preferencias;
import com.dam.receceiver.RecibidorOfertas;
import com.dam.slidemenu.SlideMenu;
import com.dam.slidemenu.SlideMenuInterface.OnSlideMenuItemClickListener;

/**
 * Esta clase tiene la función de mostrar todos los menú y ofertas.
 * 
 * @author Javier
 * @version 1
 * 
 */

@SuppressLint("NewApi")
public class MainActivity extends Activity implements
		OnSlideMenuItemClickListener {

	private SlideMenu slidemenu;
	// private final static int MYITEMID = 42;
	ListView lista;
	static CrudOfertas cOfertas;
	static ListaOfertas l;
	ArrayList<Oferta> gestorOfertas;
	static AdaptadorOfertas adaptadorOfertas;
	SharedPreferences pref;
	String usuario;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pref = getSharedPreferences("com.dam", Context.MODE_PRIVATE);
		usuario = pref.getString(getString(R.string.usuarioPref), null);
		if (usuario == null) {
			Intent intent = new Intent(getApplicationContext(), Login.class);
			startActivity(intent);
		} else {

			getActionBar().setDisplayHomeAsUpEnabled(true);

			lista = (ListView) findViewById(R.id.listaOfertas);

			// slidemenu = new SlideMenu(this, R.menu.slide, this, 333);
			// Esto infla el menú de XML. estado abierto / cerrado será
			// restaurado después de la rotación, pero usted tendrá que llamar a
			// init.
			slidemenu = (SlideMenu) findViewById(R.id.slideMenu);
			slidemenu.init(this, R.menu.slide, this, 333);

			cOfertas = new CrudOfertas();

			gestorOfertas = new ArrayList<Oferta>();
			adaptadorOfertas = new AdaptadorOfertas(this, gestorOfertas);
			solicitaLista();
			actualizaLista(l);
			lista.setAdapter(adaptadorOfertas);
			// Esto se puede ajustar el menú de muestra inicialmente en vez de
			// oculto
			// slidemenu.setAsShown();

			// Poner obcionalmente imagen de cabecera
			// slidemenu.setHeaderImage(getResources().getDrawable(R.drawable.logo));

			// Esto muestra cómo agregar dinámicamente los elementos de menú
			// SlideMenuItem item = new SlideMenuItem();
			// item.id = MYITEMID;
			// item.icon = getResources().getDrawable(R.drawable.logo);
			// item.label = "Dynamically added item";
			// slidemenu.addMenuItem(item);

			

					
				Intent i = new Intent(getApplicationContext(),RecibidorOfertas.class);
				i.setAction("notification");
				sendBroadcast(i);
			
		}
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onSlideMenuItemClick(int itemId) {

		switch (itemId) {
		case R.id.item_one:
			Intent intent = new Intent(getApplicationContext(), Catalogo.class);
			startActivity(intent);
			break;
		case R.id.item_two:
			Intent intent2 = new Intent(getApplicationContext(),
					CarritoListaCompra.class);
			startActivity(intent2);
			break;
		case R.id.item_three:
			Intent intent3 = new Intent(getApplicationContext(),
					Servicios.class);
			startActivity(intent3);
			break;
		case R.id.item_four:
			Intent intent4 = new Intent(getApplicationContext(),
					MisCompras.class);
			startActivity(intent4);
			break;
		// case MYITEMID:
		// Toast.makeText(this, "Dynamically added item selected",
		// Toast.LENGTH_SHORT).show();
		// break;
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home: // Este es el icono de la aplicación del
								// actionbar
			slidemenu.show();
			break;
		case R.id.action_settings:
			startActivity(new Intent(MainActivity.this, Preferencias.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Obtiene la lista de la Base de datos y la recorre entera.
	 * 
	 */
	public static void solicitaLista() {
		cOfertas = new CrudOfertas();
		l = cOfertas.findAll();
	}

	/**
	 * Actualiza el adaptador de la lista por si a habido algún cambio.
	 * 
	 * @param l
	 *            es una ListaOfertas
	 */
	public static void actualizaLista(ListaOfertas l) {

		adaptadorOfertas.clear();
		adaptadorOfertas.addAll(l.getLista());
		adaptadorOfertas.notifyDataSetChanged();
	}

}
