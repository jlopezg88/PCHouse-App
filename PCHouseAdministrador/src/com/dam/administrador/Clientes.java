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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.dam.adaptador.AdaptadorCliente;
import com.dam.bbdd.CrudClientes;
import com.dam.listas.ListaClientes;
import com.dam.modelo.Cliente;
/**
 * Esta clase muestra a todos los clientes registrados en nuestra aplicación
 * @author Javier
 * @version 1
 *
 */
@SuppressLint("NewApi")
public class Clientes extends Activity {

	ListView lista;
	static ListaClientes l;
	static AdaptadorCliente adaptadorCliente;
	static CrudClientes cCliente;
	ArrayList<Cliente> gestorCliente;
	Cliente cliente;
	static Context c;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clientes);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		lista = (ListView) findViewById(R.id.listViewClientes);

		// listaCliente.add(new Cliente(1, "Javier", "López", "calle santo rey",
		// "954534521","javi@gmail.com","1234"));
		// listaCliente.add(new Cliente(1, "David", "Romero", "calle falsa ",
		// "954534543","David@gmail.com","1234"));

		// Creo el crudProducto para poder hacer las diferentes gestion de los
		// productos de la bbdd
		cCliente = new CrudClientes();
		// Creio un arrayList de productos para cargar todos los productos de la
		// bbdd
		gestorCliente = new ArrayList<Cliente>();
		// Creamos el adaptadpr para los productos
		adaptadorCliente = new AdaptadorCliente(this, gestorCliente);
		// Solicitamos la lista de la bbdd
		solicitaLista();
		// Actualizamos la lista por si ha habido algún cambio
		actualizaLista(l);
		// Le adaptamos a la lista nuestro adaptador
		lista.setAdapter(adaptadorCliente);
		// Registramos el menú contextual
		registerForContextMenu(lista);

		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				cliente = gestorCliente.get(arg2);
				Intent i = new Intent(c,DescripcionCliente.class);
				i.putExtra("idLisCli", cliente.getIdC());
				i.putExtra("nombre", cliente.getNombre().toString());
				i.putExtra("apellidos", cliente.getApellidos().toString());
				i.putExtra("direccion", cliente.getDireccion().toString());
				i.putExtra("telefono", cliente.getTelefono().toString());
				i.putExtra("correo", cliente.getCorreo().toString());
				startActivity(i);
			}
		});

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.contextmenucliente, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
		// .getMenuInfo();
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.eliminar:
			cliente = gestorCliente.get((int) info.id);
			dialogo();
			return true;
		case R.id.mostrar:
			cliente = gestorCliente.get((int) info.id);
			Intent intent = new Intent(getApplicationContext(),
					DescripcionCliente.class);
			intent.putExtra("idLisCli", cliente.getIdC());
			intent.putExtra("nombre", cliente.getNombre().toString());
			intent.putExtra("apellidos", cliente.getApellidos().toString());
			intent.putExtra("direccion", cliente.getDireccion().toString());
			intent.putExtra("telefono", cliente.getTelefono().toString());
			intent.putExtra("correo", cliente.getCorreo().toString());
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
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Obtiene la lista de la Base de datos y la recorre entera.
	 */
	public static void solicitaLista() {
		cCliente = new CrudClientes();
		l = cCliente.findAll();
	}

	/**
	 * Actualiza el adaptador de la lista por si a habido algún cambio.
	 * 
	 * @param l
	 *            es una ListaClientesAdmin
	 */
	public static void actualizaLista(ListaClientes l) {

		adaptadorCliente.clear();
		adaptadorCliente.addAll(l.getLista());
		adaptadorCliente.notifyDataSetChanged();

	}

	/**
	 * 
	 * @author Javier Esta clase privada lanza un hilo para eliminar un elemento
	 *         de la tabla que se le pasa por parámetro
	 */
	private class HiloEliminar extends AsyncTask<Cliente, Void, Void> {

		@Override
		protected Void doInBackground(Cliente... params) {
			// TODO Auto-generated method stub
			CrudClientes crudSW = new CrudClientes();
			for (Cliente p : params) {
				crudSW.delete(p);
			}
			return null;
		}
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
		            			eliminar.execute(cliente);
		            			gestorCliente.remove(cliente);
		            			adaptadorCliente.notifyDataSetChanged();
		            			Toast.makeText(getApplicationContext(),
		            					R.string.eliminado, Toast.LENGTH_LONG).show();
		                    }
		                });
		AlertDialog alert = builder.create();
		alert.show();
		
	}
}
