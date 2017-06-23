package com.dam.cliente;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.dam.R;
import com.dam.adaptador.AdaptadorLineaPedido;
import com.dam.bbdd.CrudLineaPedido;
import com.dam.listas.ListaLineaPedidos;
import com.dam.modelo.Lineapedido;


/**
 * Esta clase muestra todas las compras que ha ido realizando el cliente desde que se registro.
 * 
 * @author Javier
 * @version 1
 * 
 */
@SuppressLint("NewApi")
public class MisCompras extends Activity {

	ListView lista;
	ArrayList<Lineapedido>gestorCarrito;
	static ListaLineaPedidos l;
	static CrudLineaPedido cLineaPed;
	static AdaptadorLineaPedido adaptadorLineaPedido;
	static int precio;
	TextView mostrarPrecio;
	SharedPreferences pref;
	static String correo;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mis_compras);
	    
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    lista = (ListView) findViewById(R.id.listViewMisCompras);
	    mostrarPrecio = (TextView) findViewById(R.id.textResultadoTotal);
	    
	    
//	    listaProducto.add(new Producto(1, "EXPANSION USB INTERNO NZXT IU01", "Para tener mas usb si lo desea", "12 €", "accesorios"));
//	    listaProducto.add(new Producto(1, "Nexus 7", "Un gran rendimiento", "160 €", "Nexus"));
//	    listaProducto.add(new Producto(1, "920-003115 LOGITECH ", "Un gran rendimiento", "90 €", "teclado"));
//	    listaProducto.add(new Producto(1, "EXPANSION USB INTERNO NZXT IU01", "Para tener mas usb si lo desea", "12 €", "accesorios"));
	    
	    pref = getSharedPreferences("com.dam",Context.MODE_PRIVATE);
		correo = pref.getString(getString(R.string.usuarioPref), null);

		 
	    // Creo el crudProducto para poder hacer las diferentes gestion de los productos de la bbdd
	    cLineaPed = new CrudLineaPedido();
	    //Creo un arrayList de productos para cargar todos los productos de la bbdd
	    gestorCarrito = new ArrayList<Lineapedido>();
	    // Creamos el adaptador para los productos
	    adaptadorLineaPedido = new AdaptadorLineaPedido(this, gestorCarrito);
	    //Solicitamos la lista de la bbdd
	    solicitaLista();
	    //Actualizamos la lista por si ha habido algún cambio
		actualizaLista(l);
		//Le adaptamos a la lista nuestro adaptador
		lista.setAdapter(adaptadorLineaPedido);
		totalGastado();
		mostrarPrecio.setText(precio+"");

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
	/**
	 * Obtiene la lista de la Base de datos y la recorre entera.
	 * 
	 */
	public static void solicitaLista() {
		System.out.println(correo);
		cLineaPed= new CrudLineaPedido();
		l = cLineaPed.findByCorreo(correo);
	}
	/**
	 * Actualiza el adaptador de la lista por si a habido algún cambio.
	 * 
	 * @param l es una ListaLineaPedidos
	 */
	public static void actualizaLista(ListaLineaPedidos l) {

		adaptadorLineaPedido.clear();
		adaptadorLineaPedido.addAll(l.getLista());
		adaptadorLineaPedido.notifyDataSetChanged();
	}
	/**
	 * Este método calcula el total gastado en total en nuestra aplicación
	 */
	public static void totalGastado(){
		ListaLineaPedidos l = cLineaPed.findByCorreo(correo);
		List<Lineapedido> pedidos = new ArrayList<Lineapedido>();
		pedidos = l.getLista();
		
		for (Lineapedido ped : pedidos) {
			precio+=ped.getPrecio();	
		}
	}
}
