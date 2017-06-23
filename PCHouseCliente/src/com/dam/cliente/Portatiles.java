package com.dam.cliente;

import java.util.ArrayList;
import java.util.List;

import com.dam.R;
import com.dam.adaptador.AdaptadorProducto;
import com.dam.bbdd.BBDDOpenHelper;
import com.dam.bbdd.CrudCarrito;
import com.dam.bbdd.CrudProductos;
import com.dam.listas.ListaProductos;
import com.dam.modelo.Carrito;
import com.dam.modelo.Producto;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Esta clase tiene la función de mostrar y poder añadir al carrito todos los Portatiles disponibles.
 * 
 * @author Javier
 * @version 1
 * 
 */

@SuppressLint("NewApi")
public class Portatiles extends Activity implements OnItemSelectedListener {

	Spinner portatil;
	ListView lista;
	static ListaProductos l;
	static CrudProductos cProducto;
	static AdaptadorProducto adaptadorProducto;
	ArrayList<Producto> gestorProductos;
	Producto producto;
	BBDDOpenHelper bbdd;
	CrudCarrito cCarrito;
	Carrito carrito;
	List<Carrito> listaCarrito;
	static String categoriaPortatiles="Portatiles";
	static String tipoPortatil;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.portatiles);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		portatil = (Spinner) findViewById(R.id.spinnerPortatiles);
		lista = (ListView) findViewById(R.id.listViewPortatiles);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.portatiles, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		portatil.setAdapter(adapter);
		portatil.setOnItemSelectedListener(this);


		// listaProducto.add(new Producto(1, "B580 M94A7SP ",
		// "Un gran rendimiento", "500 €", "Lenovo"));
		// listaProducto.add(new Producto(1, "B580 M94A7SP ",
		// "Un gran rendimiento", "500 €", "Lenovo"));

		//Base de datos coneccion
	    bbdd = new BBDDOpenHelper(getBaseContext(), "CarritoBBDD", null, 1);
	    cCarrito = new CrudCarrito(bbdd);
		
		// Creo el crudProducto para poder hacer las diferentes gestion de los productos de la bbdd
	    cProducto = new CrudProductos();
	    //Creio un arrayList de productos para cargar todos los productos de la bbdd
	    gestorProductos = new ArrayList<Producto>();
	    // Creamos el adaptadpr para los productos
	    adaptadorProducto = new AdaptadorProducto(this, gestorProductos);
	    //Solicitamos la lista de la bbdd
	    //solicitaLista();
	    //Actualizamos la lista por si ha habido algún cambio
		//actualizaLista(l);
		//Le adaptamos a la lista nuestro adaptador
		lista.setAdapter(adaptadorProducto);
	    //Registramos el menú contextual
		registerForContextMenu(lista);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long id) {
		switch(pos){
		case 0:
			tipoPortatil="HP";
			solicitaLista();
			actualizaLista(l);
			break;
		case 1:
			tipoPortatil="TOSHIBA";
			solicitaLista();
			actualizaLista(l);
			break;
		case 2:
			tipoPortatil="ASUS";
			solicitaLista();
			actualizaLista(l);
			break;
		case 3:
			tipoPortatil="Lenovo";
			solicitaLista();
			actualizaLista(l);
			break;
		case 4:
			tipoPortatil="SAMSUNG";
			solicitaLista();
			actualizaLista(l);
			break;
		case 5:
			tipoPortatil="Acer";
			solicitaLista();
			actualizaLista(l);
			break;
		case 6:
			tipoPortatil="Sony";
			solicitaLista();
			actualizaLista(l);
			break;
		case 7:
			tipoPortatil="COMPAQ";
			solicitaLista();
			actualizaLista(l);
			break;
		case 8:
			tipoPortatil="Packard Bell";
			solicitaLista();
			actualizaLista(l);
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

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

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.contextmenu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
		// .getMenuInfo();
		final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.add:
			producto = gestorProductos.get((int) info.id);
			cCarrito.insert(new Carrito(0,producto.getNombre() ,producto.getPrecio(), producto.getCategoria(), producto.getTipo()));
			Toast.makeText(getApplicationContext(), R.string.addCarrito, Toast.LENGTH_LONG).show();
			return true;

		default:
			return super.onContextItemSelected(item);
		}
	}
	/**
	 * Obtiene la lista de la Base de datos y la recorre entera.
	 * 
	 */
	public static void solicitaLista() {
		cProducto= new CrudProductos();
		l = cProducto.findByTipo(categoriaPortatiles, tipoPortatil);
	}
	/**
	 * Actualiza el adaptador de la lista por si a habido algún cambio.
	 * 
	 * @param l es una ListaProducto
	 */
	public static void actualizaLista(ListaProductos l) {

		adaptadorProducto.clear();
		adaptadorProducto.addAll(l.getLista());
		adaptadorProducto.notifyDataSetChanged();
	}
}
