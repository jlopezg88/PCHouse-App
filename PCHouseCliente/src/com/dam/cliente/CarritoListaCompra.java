package com.dam.cliente;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

import com.dam.R;
import com.dam.adaptador.AdaptadorCarrito;
import com.dam.adaptador.AdaptadorLineaPedido;
import com.dam.bbdd.BBDDOpenHelper;
import com.dam.bbdd.CrudCarrito;
import com.dam.bbdd.CrudLineaPedido;
import com.dam.listas.ListaLineaPedidos;
import com.dam.modelo.Carrito;
import com.dam.modelo.Lineapedido;

/**
 * Esta clase tiene la función poder tener la opción de compra de todos los
 * prductos elegidos por el cliente en el carrito.
 * 
 * @author Javier
 * @version 1
 * 
 */

@SuppressLint("NewApi")
public class CarritoListaCompra extends Activity {

	ListView lista;
	ArrayList<Lineapedido> gestorCarrito;
	static ListaLineaPedidos l;
	static CrudLineaPedido cLineaPed;
	static AdaptadorLineaPedido adaptadorLineaPedido;
	Lineapedido lineaPed;
	BBDDOpenHelper bbdd;
	CrudCarrito cCarrito;
	static List<Carrito> listaCarrito;
	static AdaptadorCarrito adaptadorCarrito;
	Carrito carrito;
	static Context c;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carrito);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		lista = (ListView) findViewById(R.id.listViewCarrito);

		// listaProducto.add(new Producto(1, "EXPANSION USB INTERNO NZXT IU01",
		// (double) 12 , "accesorios", "USB"));
		// listaProducto.add(new Producto(1, "Nexus 7", (double) 160 ,
		// "Nexus","Tablets"));

		bbdd = new BBDDOpenHelper(getBaseContext(), "CarritoBBDD", null, 1);
		cCarrito = new CrudCarrito(bbdd);

		listaCarrito = cCarrito.findAll();
		adaptadorCarrito = new AdaptadorCarrito(this, listaCarrito);
		adaptadorCarrito.notifyDataSetChanged();
		lista.setAdapter(adaptadorCarrito);

		// Creo el crudProducto para poder hacer las diferentes gestion de los
		// productos de la bbdd
		// cLineaPed = new CrudLineaPedido();
		// Creio un arrayList de productos para cargar todos los productos de la
		// bbdd
		// gestorCarrito = new ArrayList<Lineapedido>();
		// Creamos el adaptadpr para los productos
		// adaptadorLineaPedido = new AdaptadorLineaPedido(this, gestorCarrito);
		// Solicitamos la lista de la bbdd
		// solicitaLista();
		// Actualizamos la lista por si ha habido algún cambio
		// actualizaLista(l);
		// Le adaptamos a la lista nuestro adaptador
		// lista.setAdapter(adaptadorLineaPedido);
		// Registramos el menú contextual
		registerForContextMenu(lista);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.carrito, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.contextmenucarrito, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
		// .getMenuInfo();
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.eliminar:
			carrito = listaCarrito.get((int) info.id);
			dialogo();
			return true;

		default:
			return super.onContextItemSelected(item);
		}
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
		case R.id.carrito:
			Intent intent = new Intent(getApplicationContext(), Pago.class);
			startActivity(intent);
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Obtiene la lista de la Base de datos y la recorre entera.
	 * 
	 */
	public static void solicitaLista() {
		cLineaPed = new CrudLineaPedido();
		l = cLineaPed.findAll();
	}

	/**
	 * Actualiza el adaptador de la lista por si a habido algún cambio.
	 * 
	 * @param l
	 *            es una ListaProducto
	 */
	public static void actualizaLista(ListaLineaPedidos l) {

		adaptadorLineaPedido.clear();
		adaptadorLineaPedido.addAll(l.getLista());
		adaptadorLineaPedido.notifyDataSetChanged();
	}
	/**
	 * Este método muestra un dialogo para confirmar la eliminación del producto
	 */
	public void dialogo (){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.seguroEliminar)
		        .setTitle(R.string.advertencia)
		        .setCancelable(false)
		        .setNegativeButton(R.string.cancelar,
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int id) {
		                        dialog.cancel();
		                    }
		                })
		        .setPositiveButton(R.string.continuar,
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int id) {
		                    	cCarrito.delete(carrito);
		            			listaCarrito.remove(carrito);
		            			adaptadorCarrito.notifyDataSetChanged();
		            			Toast.makeText(getApplicationContext(), R.string.eliminado,
		            					Toast.LENGTH_LONG).show();
		                    }
		                });
		AlertDialog alert = builder.create();
		alert.show();
		
	}
	
}
