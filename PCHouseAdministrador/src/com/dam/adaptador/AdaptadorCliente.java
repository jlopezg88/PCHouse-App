package com.dam.adaptador;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dam.administrador.R;
import com.dam.modelo.Cliente;
/**
 * Esta clase crea un listview personalizado de Cliente
 * @author Javier
 * @version 1
 */


public class AdaptadorCliente extends ArrayAdapter<Cliente> {
	
	TextView titulo;
	TextView apellidos;
	List<Cliente> listaClientes;
	
	Activity context;
	
	/**
	 * Constructor con parámetros de AdaptadorCliente.
	 *
	 * @param context
	 * @param listaClientes
	 */
	public AdaptadorCliente(Activity context, List<Cliente> listaClientes) {
		super(context, R.layout.list_client, listaClientes);
		this.context=context;
		this.listaClientes= listaClientes;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {

		View item = convertView;

		LayoutInflater inflater = context.getLayoutInflater();
		item = inflater.inflate(R.layout.list_client, null);

		titulo = (TextView) item.findViewById(R.id.titulo);
		apellidos = (TextView) item.findViewById(R.id.apellidos);
		

		Cliente cargarLista = listaClientes.get(position);

		
		titulo.setText(cargarLista.getNombre());
		apellidos.setText(cargarLista.getApellidos());
		

		return(item);
	}

}
