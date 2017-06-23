package com.dam.adaptador;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dam.administrador.R;
import com.dam.modelo.Pedido;
/**
 * Esta clase crea un listview personalizado de Pedido
 * @author Javier
 * @version 1
 */


public class AdaptadorPedido extends ArrayAdapter<Pedido> {
	
	TextView titulo;
	TextView apellidos;
	List<Pedido> listaPedidos;
	
	Activity context;
	
	/**
	 * Constructor con parámetros de AdaptadorPedido.
	 *
	 * @param context
	 * @param listaPedidos
	 */
	public AdaptadorPedido(Activity context, List<Pedido> listaPedidos) {
		super(context, R.layout.list_pedido, listaPedidos);
		this.context=context;
		this.listaPedidos= listaPedidos;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {

		View item = convertView;

		LayoutInflater inflater = context.getLayoutInflater();
		item = inflater.inflate(R.layout.list_pedido, null);

		titulo = (TextView) item.findViewById(R.id.tituloPed);
		

		Pedido cargarLista = listaPedidos.get(position);

		
		titulo.setText(cargarLista.getCorreo());
		

		return(item);
	}

}
