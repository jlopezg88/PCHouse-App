package com.dam.administrador;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.dam.adaptador.AdaptadorProducto;
import com.dam.bbdd.CrudProductos;
import com.dam.listas.ListaProductos;
import com.dam.modelo.Producto;

/**
 * Esta clase lista todos los productos y puede modificarlos y eliminarlos
 * 
 * @author Javier
 * @version 1
 */
@SuppressLint("NewApi")
public class Productos extends Activity implements OnItemSelectedListener {

	ListView lista;
	Spinner productos;
	static ListaProductos l;
	static CrudProductos cProducto;
	static AdaptadorProducto adaptadorProducto;
	ArrayList<Producto> gestorProductos;
	static String categoria;
	static Context c;
	Producto product;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productos);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		productos = (Spinner) findViewById(R.id.spinnerProductos);
		lista = (ListView) findViewById(R.id.listViewProductos);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.categorias, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		productos.setAdapter(adapter);
		productos.setOnItemSelectedListener(this);

		// Creo el crudProducto para poder hacer las diferentes gestion de los
		// productos de la bbdd
		// cProducto = new CrudProductos();
		// Creio un arrayList de productos para cargar todos los productos de la
		// bbdd
		gestorProductos = new ArrayList<Producto>();
		// Creamos el adaptadpr para los productos
		adaptadorProducto = new AdaptadorProducto(this, gestorProductos);
		// Solicitamos la lista de la bbdd
		// solicitaLista();
		// Actualizamos la lista por si ha habido algún cambio
		// actualizaLista(l);
		// Le adaptamos a la lista nuestro adaptador
		lista.setAdapter(adaptadorProducto);
		// Registramos el menú contextual
		registerForContextMenu(lista);
		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

			    product = gestorProductos.get(arg2);
	
				Intent i = new Intent(c, Editar.class);
				i.putExtra("idLisPro", product.getIdP());
				i.putExtra("precio", product.getPrecio());
				i.putExtra("nombre", product.getNombre().toString());
				startActivity(i);
			}

		});
		
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {
		switch (pos) {
		case 0:
			categoria = "Monitores";
			solicitaLista();
			actualizaLista(l);
			break;
		case 1:
			categoria = "Accesorios";
			solicitaLista();
			actualizaLista(l);
			break;
		case 2:
			categoria = "Perifericos";
			solicitaLista();
			actualizaLista(l);
			break;
		case 3:
			categoria = "Componentes";
			solicitaLista();
			actualizaLista(l);
			break;
		case 4:
			categoria = "Tablet";
			solicitaLista();
			actualizaLista(l);
			break;
		case 5:
			categoria = "Portatiles";
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
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.contextmenuproductos, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
		// .getMenuInfo();
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.eliminar:
			product = gestorProductos.get((int) info.id);
			dialogo();
			return true;
		case R.id.editar:
			product = gestorProductos.get((int) info.id);
			Intent intent = new Intent(getApplicationContext(), Editar.class);
			intent.putExtra("idLisPro", product.getIdP());
			intent.putExtra("precio", product.getPrecio());
			intent.putExtra("nombre", product.getNombre().toString());
			startActivity(intent);
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
		case R.id.addPro:
			Intent intent = new Intent(getApplicationContext(), NuevoProducto.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 
	 * @author Javier Esta clase privada lanza un hilo para eliminar un elemento
	 *         de la tabla que se le pasa por parámetro
	 */
	private class HiloEliminar extends AsyncTask<Producto, Void, Void> {

		@Override
		protected Void doInBackground(Producto... params) {
			// TODO Auto-generated method stub
			CrudProductos crudSW = new CrudProductos();
			for (Producto p : params) {
				crudSW.delete(p);
			}
			return null;
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.producto, menu);
		return true;
	}
	/**
	 * Obtiene la lista de la Base de datos y la recorre entera.
	 */
	public static void solicitaLista() {
		cProducto = new CrudProductos();
		l = cProducto.findByCategoria(categoria);
	}

	/**
	 * Actualiza el adaptador de la lista por si a habido algún cambio.
	 * 
	 * @param l
	 *            es una ListaProducto
	 */
	public static void actualizaLista(ListaProductos l) {

		adaptadorProducto.clear();
		adaptadorProducto.addAll(l.getLista());
		adaptadorProducto.notifyDataSetChanged();
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
		                    	HiloEliminar eliminar = new HiloEliminar();
		            			eliminar.execute(product);
		            			gestorProductos.remove(product);
		            			adaptadorProducto.notifyDataSetChanged();
		            			Toast.makeText(getApplicationContext(), R.string.eliminado,
		            					Toast.LENGTH_LONG).show();
		                    }
		                });
		AlertDialog alert = builder.create();
		alert.show();
		
	}
}
