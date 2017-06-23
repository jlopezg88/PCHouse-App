package com.dam.cliente;

import java.util.ArrayList;
import java.util.List;

import com.dam.R;
import com.dam.bbdd.CrudSugerencias;
import com.dam.listas.ListaSugerencias;
import com.dam.modelo.Sugerencia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * Esta clase envia un mensaje según el tipo elegido al administrador.
 * 
 * @author Javier
 * @version 1
 * 
 */

@SuppressLint("NewApi")
public class Consultoria extends Activity implements OnItemSelectedListener {

	Spinner consultoria;
	EditText asunto, mensaje;
	CrudSugerencias cSugerencia;
	static String tipo;
	ListaSugerencias l;
	int id;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultoria);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		consultoria = (Spinner) findViewById(R.id.spinnerConsultoria);
		asunto = (EditText) findViewById(R.id.editAsunto);
		mensaje = (EditText) findViewById(R.id.editMensaje);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(this, R.array.consultoria,
						android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		consultoria.setAdapter(adapter);
		consultoria.setOnItemSelectedListener(this);

		// Creo el crudProducto para poder hacer las diferentes gestion de los
		// productos de la bbdd
		cSugerencia = new CrudSugerencias();

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consultoria, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {
		// Se almacena el tipo de consultoria
		switch (pos) {
		case 0:
			tipo = "Sugerencia";
			break;
		case 1:
			tipo = "Queja";
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
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		case R.id.consultoria:
			if (asunto.getText().toString().equals("")
					|| mensaje.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(), R.string.campoVacio,
						Toast.LENGTH_LONG).show();
			} else {
				// Incrementa el id en uno para añadirlo
				l = cSugerencia.findAll();
				List<Sugerencia> sug = new ArrayList<Sugerencia>();
				sug = l.getLista();
				id = 0;
				for (Sugerencia u : sug) {
					if (u.getIdCon() > id) {
						id = u.getIdCon();
					}
				}
				id++;
				new Thread(new Runnable() {

					@Override
					public void run() {
						cSugerencia.insert(new Sugerencia(id, tipo, asunto
								.getText().toString(), mensaje.getText()
								.toString()));

					}
				}).start();
				Toast.makeText(getApplicationContext(), R.string.enviado,
						Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
			}

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
