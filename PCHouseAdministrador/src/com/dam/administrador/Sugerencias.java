package com.dam.administrador;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.dam.adaptador.AdaptadorSugerencia;
import com.dam.bbdd.CrudSugerencias;
import com.dam.listas.ListaSugerencias;
import com.dam.modelo.Sugerencia;
/**
 * Esta clase muestra todas la quejas y sugerencias de los clientes.
 * @author Javier
 * @version 1
 */
@SuppressLint("NewApi")
public class Sugerencias extends Activity implements OnItemSelectedListener{

	Spinner sugerencia;
	static CrudSugerencias cSugerencia;
	ListView lista;
	static ListaSugerencias l;
	static AdaptadorSugerencia adaptadorSugerencia;
	ArrayList<Sugerencia> gestorSugerencia;
	static String tipoSugerencia;
	Sugerencia suge;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.sugerencias);
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    sugerencia = (Spinner) findViewById(R.id.spinnerSugerencia);
	    lista = (ListView) findViewById(R.id.listViewSugerencia);
	    
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.sugerencias, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    sugerencia.setAdapter(adapter);		
	    sugerencia.setOnItemSelectedListener(this);
	    
	    cSugerencia = new CrudSugerencias();
	    
	    gestorSugerencia = new ArrayList<Sugerencia>();
	    
	    
	    adaptadorSugerencia = new AdaptadorSugerencia(this, gestorSugerencia);
	    
	    lista.setAdapter(adaptadorSugerencia);
	    
	    registerForContextMenu(lista);
	    
		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				suge = gestorSugerencia.get(arg2);
				Intent i = new Intent(getApplicationContext(),DescripcionSugerencia.class);
				i.putExtra("tipo", suge.getTipo().toString());
				i.putExtra("titulo", suge.getAsunto().toString());
				i.putExtra("mensaje", suge.getMensaje().toString());
				startActivity(i);
			}
		});

	 
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long id) {
		switch(pos){
		case 0:
			tipoSugerencia="Sugerencia";
			solicitaLista();
			actualizaLista(l);
			break;
		case 1:
			tipoSugerencia="Queja";
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
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.contextmenusugerencia, menu);
	}
	public boolean onContextItemSelected(MenuItem item) {
		// AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
		// .getMenuInfo();
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.mostrar:
			suge =gestorSugerencia.get((int) info.id);
			Intent intent = new Intent(getApplicationContext(), DescripcionSugerencia.class);
			intent.putExtra("tipo", suge.getTipo().toString());
			intent.putExtra("titulo", suge.getAsunto().toString());
			intent.putExtra("mensaje", suge.getMensaje().toString());
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
		cSugerencia= new CrudSugerencias();
		l = cSugerencia.findByTipoSu(tipoSugerencia);
	}
	/**
	 * Actualiza el adaptador de la lista por si a habido algún cambio.
	 * 
	 * @param l es una  ListaSugerencias
	 */
	public static void actualizaLista(ListaSugerencias l) {

		adaptadorSugerencia.clear();
		adaptadorSugerencia.addAll(l.getLista());
		adaptadorSugerencia.notifyDataSetChanged();
	}
}
