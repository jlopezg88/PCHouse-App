package com.dam.administrador;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * Esta clase lista todas las categorias
 * @author Javier
 * @version 1
 */
@SuppressLint("NewApi")
public class Categorias extends Activity {

	ListView lista;
	private String[] categorias = { "Componentes", "Monitores", "Accesorios",
			"Portatiles", "Perifericos", "Tablets" };

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categorias);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		lista = (ListView) findViewById(R.id.listViewCategoria);

		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, categorias);
		lista.setAdapter(adaptador);
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

		}
		return super.onOptionsItemSelected(item);
	}

}
