package com.dam.administrador;

import java.util.ArrayList;
import java.util.List;

import com.dam.bbdd.CrudProductos;
import com.dam.listas.ListaProductos;

import com.dam.modelo.Producto;

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
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Esta clase añade nuevos productos
 * 
 * @author Javier
 * @version 1
 */
@SuppressLint("NewApi")
public class NuevoProducto extends Activity implements OnItemSelectedListener {

	EditText nombre, precio;
	Spinner categoria;
	Spinner tipoNuevo;
	static String editarCategoria;
	static String editarTipo;
	CrudProductos cProductos;
	ListaProductos l;
	int id;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nuevo_producto);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		categoria = (Spinner) findViewById(R.id.spinnerNuevo);
	    tipoNuevo = (Spinner) findViewById(R.id.spinnerTipoNuevo);
	    nombre = (EditText) findViewById(R.id.editNombreNuevo);
	    precio=(EditText) findViewById(R.id.editPrecioNuevo);
	    
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.categorias, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categoria.setAdapter(adapter);
		categoria.setOnItemSelectedListener(this);

		tipoNuevo.setOnItemSelectedListener(this);
		
		cProductos = new CrudProductos();
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {
		int idSipiner = arg0.getId();
		switch (idSipiner) {
		case R.id.spinnerNuevo:
			switch (pos) {
			case 0:
				editarCategoria = "Monitores";
				if (editarCategoria.equals("Monitores")) {
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
							.createFromResource(this, R.array.monitores,
									android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					tipoNuevo.setAdapter(adapter2);
				}
				break;
			case 1:
				editarCategoria = "Accesorios";
				if (editarCategoria.equals("Accesorios")) {
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
							.createFromResource(this, R.array.accesorios,
									android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					tipoNuevo.setAdapter(adapter2);
				}
				break;
			case 2:
				editarCategoria = "Perifericos";
				if (editarCategoria.equals("Perifericos")) {
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
							.createFromResource(this, R.array.perifericos,
									android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					tipoNuevo.setAdapter(adapter2);
				}
				break;
			case 3:
				editarCategoria = "Componentes";
				if (editarCategoria.equals("Componentes")) {
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
							.createFromResource(this, R.array.componentes,
									android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					tipoNuevo.setAdapter(adapter2);
				}
				break;
			case 4:
				editarCategoria = "Tablets";
				if (editarCategoria.equals("Tablets")) {
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
							.createFromResource(this, R.array.tablets,
									android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					tipoNuevo.setAdapter(adapter2);
				}
				break;
			case 5:
				editarCategoria = "Portatiles";
				if (editarCategoria.equals("Portatiles")) {
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
							.createFromResource(this, R.array.portatiles,
									android.R.layout.simple_spinner_item);
					adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					tipoNuevo.setAdapter(adapter2);
				}
				break;

			}
			break;
		case R.id.spinnerTipoNuevo:
			if (editarCategoria.equals("Monitores")) {
				switch (pos) {
				case 0:
					editarTipo = "PHILIPS";
					break;
				case 1:
					editarTipo = "BENQ";
					break;
				case 2:
					editarTipo = "SAMSUNG";
					break;
				case 3:
					editarTipo = "LG";
					break;
				case 4:
					editarTipo = "SONY";
					break;
				case 5:
					editarTipo = "TOSHIBA";
					break;
				case 6:
					editarTipo = "Acer";
					break;
				case 7:
					editarTipo = "HP";
					break;
				}
			} else if (editarCategoria.equals("Accesorios")) {
				switch (pos) {
				case 0:
					editarTipo = "Cargador";
					break;
				case 1:
					editarTipo = "Funda";
					break;
				case 2:
					editarTipo = "Bateria";
					break;

				}
			} else if (editarCategoria.equals("Perifericos")) {
				switch (pos) {
				case 0:
					editarTipo = "Escaneres";
					break;
				case 1:
					editarTipo = "WebCams";
					break;
				case 2:
					editarTipo = "Impresoras";
					break;
				case 3:
					editarTipo = "USB";
					break;
				case 4:
					editarTipo = "Auriculares";
					break;
				case 5:
					editarTipo = "Microfonos";
					break;
				case 6:
					editarTipo = "Teclados";
					break;
				case 7:
					editarTipo = "Ratones";
					break;
				case 8:
					editarTipo = "Altavoces";
					break;
				}
			} else if (editarCategoria.equals("Componentes")) {
				switch (pos) {
				case 0:
					editarTipo = "Lector DVD";
					break;
				case 1:
					editarTipo = "Placa base";
					break;
				case 2:
					editarTipo = "Tarjeta sonido";
					break;
				case 3:
					editarTipo = "Tarjeta grafica";
					break;
				case 4:
					editarTipo = "RAM";
					break;
				case 5:
					editarTipo = "Disco duro";
					break;
				case 6:
					editarTipo = "Fuente alimentacion";
					break;
				}
			} else if (editarCategoria.equals("Tablets")) {
				switch (pos) {
				case 0:
					editarTipo = "Acer";
					break;
				case 1:
					editarTipo = "SAMSUNG";
					break;
				case 2:
					editarTipo = "Motorola";
					break;
				case 3:
					editarTipo = "BlackBerry";
					break;
				case 4:
					editarTipo = "Nexus";
					break;
				case 5:
					editarTipo = "HTC";
					break;
				}

			} else if (editarCategoria.equals("Portatiles")) {
				switch (pos) {
				case 0:
					editarTipo = "HP";
					break;
				case 1:
					editarTipo = "TOSHIBA";
					break;
				case 2:
					editarTipo = "ASUS";
					break;
				case 3:
					editarTipo = "Lenovo";
					break;
				case 4:
					editarTipo = "SAMSUNG";
					break;
				case 5:
					editarTipo = "Acer";
					break;
				case 6:
					editarTipo = "Sony";
					break;
				case 7:
					editarTipo = "COMPAQ";
					break;
				case 8:
					editarTipo = "Packard Bell";
					break;
				}
			}
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nuevo, menu);
		return true;
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
		case R.id.menu_nuevo:
			if (nombre.getText().toString().equals("") || precio.getText().toString().equals("")){
				Toast.makeText(getApplicationContext(), R.string.campoVacio, Toast.LENGTH_LONG).show();
			}else{
				l = cProductos.findAll();
				List<Producto> pro = new ArrayList<Producto>();
				pro = l.getLista();
				boolean comprobar = false;
				for (Producto product : pro) {
					if (product.getNombre().equals(
							nombre.getText().toString())) {
						comprobar = true;
					}
				}

				if (comprobar) {
					Toast.makeText(getApplicationContext(), R.string.comprobarProduct, Toast.LENGTH_LONG).show();
				} else {
					//Incrementa el id en uno para añadirlo
					l = cProductos.findAll();
					List<Producto> prod = new ArrayList<Producto>();
					prod = l.getLista();
					id = 0;
					for (Producto u : prod) {
						if (u.getIdP() > id) {
							id = u.getIdP();
						}
					}
					id++;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							String precioString = precio.getText().toString();
							int precioInt = Integer.parseInt(precioString);
							cProductos
									.insert(new Producto(id,
											nombre.getText().toString(), precioInt,
											editarCategoria, editarTipo));
							
						}
					}).start();
					Toast.makeText(getApplicationContext(), R.string.creado, Toast.LENGTH_LONG).show();
					Intent i = new Intent(getApplicationContext(),Productos.class);
					startActivity(i);

				}
			}
	
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

		
}
