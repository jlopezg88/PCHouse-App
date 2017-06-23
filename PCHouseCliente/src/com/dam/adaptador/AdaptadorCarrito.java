package com.dam.adaptador;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dam.R;
import com.dam.modelo.Carrito;


/**
 * Esta clase crea un listview personalizado de Carrito
 * @author Javier
 * @version 1
 */

public class AdaptadorCarrito extends ArrayAdapter<Carrito> {
	
	TextView titulo;
	TextView precio;
	List<Carrito> listaCarrito;
	
	Activity context;
	
	/**
	 * Constructor con parámetros de AdaptadorCarrito.
	 *
	 * @param context
	 * @param listaCarrito
	 */
	public AdaptadorCarrito(Activity context, List<Carrito> listaCarrito) {
		super(context, R.layout.list_item, listaCarrito);
		this.context=context;
		this.listaCarrito= listaCarrito;
	}
	
	
	
	public View getView(int position, View convertView, ViewGroup parent) {

		View item = convertView;

		LayoutInflater inflater = context.getLayoutInflater();
		item = inflater.inflate(R.layout.list_item, null);

		titulo = (TextView) item.findViewById(R.id.titulo);
		precio = (TextView) item.findViewById(R.id.precio);
		

		Carrito cargarLista = listaCarrito.get(position);

		
		titulo.setText(cargarLista.getNombre());
		precio.setText(cargarLista.getPrecio()+"");
		

		return(item);
	}

}
