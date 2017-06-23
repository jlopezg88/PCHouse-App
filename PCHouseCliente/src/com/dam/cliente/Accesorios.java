
package com.dam.cliente;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.dam.R;
import com.dam.adaptador.AdaptadorProducto;
import com.dam.bbdd.BBDDOpenHelper;
import com.dam.bbdd.CrudCarrito;
import com.dam.bbdd.CrudLineaPedido;
import com.dam.bbdd.CrudProductos;
import com.dam.listas.ListaLineaPedidos;
import com.dam.listas.ListaProductos;
import com.dam.modelo.Carrito;
import com.dam.modelo.Lineapedido;
import com.dam.modelo.Producto;

/**
 * Esta clase tiene la función de mostrar y poder añadir al carrito todos los Accesorios disponibles.
 * 
 * @author Javier
 * @version 1
 *
 */

@SuppressLint("NewApi")
public class Accesorios extends Activity implements OnItemSelectedListener {

	
	Spinner accesorios;
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
	static String categoriaAccesorios="Accesorios";
	static String tipoAccesorio;
	CrudLineaPedido cLped;
	ListaLineaPedidos lis;
	int id;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.accesorios);
	    
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    accesorios = (Spinner) findViewById(R.id.spinnerAccesorios);
	    
	    lista = (ListView) findViewById(R.id.listViewAccesorios);
	    
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.accesorios, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    accesorios.setAdapter(adapter);		
	    accesorios.setOnItemSelectedListener(this);
	    
	    
	    
	    
//	    listaProducto.add(new Producto(1, "EXPANSION USB INTERNO NZXT IU01", "Para tener mas usb si lo desea", , "accesorios"));
//	    listaProducto.add(new Producto(2, "EXPANSION USB INTERNO NZXT IU01", "Para tener mas usb si lo desea", "12 €", "accesorios"));
	
	    
	    //Base de datos coneccion
	    bbdd = new BBDDOpenHelper(getBaseContext(), "CarritoBBDD", null, 1);
	    cCarrito = new CrudCarrito(bbdd);
	    
	    
	    // Creo el crudProducto para poder hacer las diferentes gestion de los productos de la bbdd
	    cProducto = new CrudProductos();
	    cLped = new CrudLineaPedido();
	    //Creo un arrayList de productos para cargar todos los productos de la bbdd
	    gestorProductos = new ArrayList<Producto>();
	    // Creamos el adaptador para los productos
	    adaptadorProducto = new AdaptadorProducto(this, gestorProductos);
	    //Solicitamos la lista de la bbdd
	    //solicitaLista();
	    //Actualizamos la lista por si ha habido algún cambio
		//actualizaLista(l);
		//Le adaptamos a la lista nuestro adaptador
		lista.setAdapter(adaptadorProducto);
	    //Registramos el menú contextual
	    registerForContextMenu(lista);
	
	    lis = cLped.findAll();
		List<Lineapedido> ped = new ArrayList<Lineapedido>();
		ped = lis.getLista();
		id = 0;
		for (Lineapedido u : ped) {
			if (u.getIdLp() > id) {
				id = u.getIdLp();
			}
		}
		id++;
	}

	
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long id) {
		switch(pos){
		case 0:
			tipoAccesorio="Cargador";
			solicitaLista();
			actualizaLista(l);
			break;
		case 1:
			tipoAccesorio="Funda";
			solicitaLista();
			actualizaLista(l);
			break;
		case 2:
			tipoAccesorio="Bateria";
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
            parentActivityIntent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
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
		final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.add:
			producto = gestorProductos.get((int) info.id);
			cCarrito.insert(new Carrito(0,producto.getNombre() ,producto.getPrecio(), producto.getCategoria(), producto.getTipo()));
//			addAcces = new HiloAccesorio();
//			addAcces.execute();
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
		l = cProducto.findByTipo(categoriaAccesorios, tipoAccesorio);
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
//	public class HiloAccesorio extends AsyncTask<Void, Void, Void>{
//
//		@Override
//		protected Void doInBackground(Void... params) {
//			CrudLineaPedido linea = new CrudLineaPedido();
//			linea.insert(new Lineapedido(id, producto.getNombre(), producto.getPrecio(), producto.getCategoria(), producto.getTipo(), "correo", "Tajeta"));
//			return null;
//		}
//		
//	}
}
