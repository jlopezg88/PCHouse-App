package com.dam.adaptador;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dam.administrador.R;
import com.dam.modelo.Producto;
/**
 * Esta clase crea un listview personalizado de Producto
 * @author Javier
 * @version 1
 */


public class AdaptadorProducto extends ArrayAdapter<Producto> {
	
	TextView titulo;
	TextView precio;
	List<Producto> listaProductos;
	
	Activity context;
	
	/**
	 * Constructor con parámetros de AdaptadorProducto.
	 *
	 * @param context
	 * @param listaProductos
	 */
	public AdaptadorProducto(Activity context, List<Producto> listaProductos) {
		super(context, R.layout.list_item, listaProductos);
		this.context=context;
		this.listaProductos= listaProductos;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {

		View item = convertView;

		LayoutInflater inflater = context.getLayoutInflater();
		item = inflater.inflate(R.layout.list_item, null);

		titulo = (TextView) item.findViewById(R.id.titulo);
		precio = (TextView) item.findViewById(R.id.precio);
		

		Producto cargarLista = listaProductos.get(position);

		
		titulo.setText(cargarLista.getNombre());
		precio.setText(cargarLista.getPrecio()+"");
		

		return(item);
	}

}
