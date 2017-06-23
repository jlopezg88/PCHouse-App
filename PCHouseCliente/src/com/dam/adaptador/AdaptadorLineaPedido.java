package com.dam.adaptador;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.dam.R;
import com.dam.modelo.Lineapedido;


/**
 * Esta clase crea un listview personalizado de Lineapedido
 * @author Javier
 * @version 1
 */

public class AdaptadorLineaPedido extends ArrayAdapter<Lineapedido> {
	
	TextView titulo;
	TextView precio;
	List<Lineapedido> listaLineaPed;
	
	Activity context;
	
	/**
	 * Constructor con parámetros de AdaptadorLineaPedido.
	 *
	 * @param context
	 * @param listaLineaPed
	 */
	public AdaptadorLineaPedido(Activity context, List<Lineapedido> listaLineaPed) {
		super(context, R.layout.list_item, listaLineaPed);
		this.context=context;
		this.listaLineaPed= listaLineaPed;
	}
	
	
	
	public View getView(int position, View convertView, ViewGroup parent) {

		View item = convertView;

		LayoutInflater inflater = context.getLayoutInflater();
		item = inflater.inflate(R.layout.list_item, null);

		titulo = (TextView) item.findViewById(R.id.titulo);
		precio = (TextView) item.findViewById(R.id.precio);
		

		Lineapedido cargarLista = listaLineaPed.get(position);

		
		titulo.setText(cargarLista.getNombre());
		precio.setText(cargarLista.getPrecio()+"");
		

		return(item);
	}

}
