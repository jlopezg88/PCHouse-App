package com.dam.adaptador;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dam.R;
import com.dam.modelo.Oferta;

/**
 * Esta clase crea un listview personalizado de Producto
 * @author Javier
 * @version 1
 */


public class AdaptadorOfertas extends ArrayAdapter<Oferta> {
	
	TextView titulo;
	TextView descripcion;
	List<Oferta> listaOfertas;
	
	Activity context;
	
	/**
	 * Constructor con parámetros de AdaptadorProducto.
	 *
	 * @param context
	 * @param listaOfertas
	 */
	public AdaptadorOfertas(Activity context, List<Oferta> listaOfertas) {
		super(context, R.layout.list_oferta, listaOfertas);
		this.context=context;
		this.listaOfertas= listaOfertas;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {

		View item = convertView;

		LayoutInflater inflater = context.getLayoutInflater();
		item = inflater.inflate(R.layout.list_oferta, null);

		titulo = (TextView) item.findViewById(R.id.tituloOfer);
		descripcion = (TextView) item.findViewById(R.id.descripcionOfer);
		

		Oferta cargarLista = listaOfertas.get(position);

		
		titulo.setText(cargarLista.getTitulo());
		descripcion.setText(cargarLista.getDescripcion());
		

		return(item);
	}

}
