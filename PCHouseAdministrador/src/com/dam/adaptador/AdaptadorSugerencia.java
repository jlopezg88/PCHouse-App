package com.dam.adaptador;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dam.administrador.R;
import com.dam.modelo.Sugerencia;
/**
 * Esta clase crea un listview personalizado de sugerencia
 * @author Javier
 * @version 1
 */

public class AdaptadorSugerencia extends ArrayAdapter<Sugerencia> {
	
	TextView titulo;
	TextView texto;
	List<Sugerencia> listaSugerencias;
	
	Activity context;
	
	/**
	 * Constructor con parámetros de AdaptadorSugerencia.
	 *
	 * @param context
	 * @param listaSugerencias
	 */
	public AdaptadorSugerencia(Activity context, List<Sugerencia> listaSugerencias) {
		super(context, R.layout.list_sugerencia, listaSugerencias);
		this.context=context;
		this.listaSugerencias= listaSugerencias;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {

		View item = convertView;

		LayoutInflater inflater = context.getLayoutInflater();
		item = inflater.inflate(R.layout.list_sugerencia, null);

		titulo = (TextView) item.findViewById(R.id.titulo);
		texto = (TextView) item.findViewById(R.id.texto);
		

		Sugerencia cargarLista = listaSugerencias.get(position);

		
		titulo.setText(cargarLista.getTipo());
		texto.setText(cargarLista.getAsunto());
		

		return(item);
	}

}
