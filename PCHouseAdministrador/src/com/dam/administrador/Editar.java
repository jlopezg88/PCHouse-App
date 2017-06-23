package com.dam.administrador;



import com.dam.bbdd.CrudProductos;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
/**
 * Esta clase es donde se puede editar algun producto existente
 * @author Javier
 * @version 1
 */
@SuppressLint({ "NewApi", "UseValueOf" })
public class Editar extends Activity implements OnItemSelectedListener {
	EditText nombre,precio;
	Spinner categoria;
	Spinner tipo;
	int idProducto;
	Integer idProduct;
	int preci;
	String mostrarPrecio;
	String mostrarNombre;
	Intent i;
	Producto product;
	CrudProductos cProducto;
	//HiloFind buscarId;
	static String editarCategoria;
	static String editarTipo;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.editar);
	    
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    categoria = (Spinner) findViewById(R.id.spinnerEditar);
	    tipo= (Spinner) findViewById(R.id.spinnerTipo);
	    nombre = (EditText) findViewById(R.id.editNombre);
	    precio=(EditText) findViewById(R.id.editPrecio);
	    
	    
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.categorias, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    categoria.setAdapter(adapter);		
	    categoria.setOnItemSelectedListener(this);
	    
	    tipo.setOnItemSelectedListener(this);
	    
	    i = getIntent();
		idProducto= i.getExtras().getInt("idLisPro");
		idProduct = new Integer(idProducto);
		preci=i.getExtras().getInt("precio");
		mostrarNombre=i.getExtras().getString("nombre");
		
		cProducto = new CrudProductos();
		//paso de entero a string
		mostrarPrecio = Integer.toString(preci);
	//	buscarId.execute(idProduct);
		
		nombre.setText(mostrarNombre);
		precio.setText(mostrarPrecio);
		
	}
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long id) {
		int idSipiner = arg0.getId();
		switch (idSipiner) {
		case R.id.spinnerEditar:
			switch (pos) {
			case 0:
				editarCategoria ="Monitores";
				if(editarCategoria.equals("Monitores")){
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
					        R.array.monitores, android.R.layout.simple_spinner_item);
				    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				    tipo.setAdapter(adapter2);		
				}
				break;
			case 1:
				editarCategoria ="Accesorios";
				if(editarCategoria.equals("Accesorios")){
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
					        R.array.accesorios, android.R.layout.simple_spinner_item);
				    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				    tipo.setAdapter(adapter2);		
				}
				break;
			case 2:
				editarCategoria ="Perifericos";
				if(editarCategoria.equals("Perifericos")){
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
					        R.array.perifericos, android.R.layout.simple_spinner_item);
				    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				    tipo.setAdapter(adapter2);		
				}
				break;
			case 3:
				editarCategoria ="Componentes";
				if(editarCategoria.equals("Componentes")){
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
					        R.array.componentes, android.R.layout.simple_spinner_item);
				    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				    tipo.setAdapter(adapter2);		
				}
				break;
			case 4:
				editarCategoria ="Tablets";
				if(editarCategoria.equals("Tablets")){
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
					        R.array.tablets, android.R.layout.simple_spinner_item);
				    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				    tipo.setAdapter(adapter2);		
				}
				break;
			case 5:
				editarCategoria ="Portatiles";
				if(editarCategoria.equals("Portatiles")){
					ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
					        R.array.portatiles, android.R.layout.simple_spinner_item);
				    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				    tipo.setAdapter(adapter2);		
				}
				break;

			}
			break;
		case R.id.spinnerTipo:
			if(editarCategoria.equals("Monitores")){
				switch (pos) {
				case 0:
					editarTipo="PHILIPS";
					break;
				case 1:
					editarTipo="BENQ";
					break;
				case 2:
					editarTipo="SAMSUNG";
					break;
				case 3:
					editarTipo="LG";
					break;
				case 4:
					editarTipo="SONY";
					break;
				case 5:
					editarTipo="TOSHIBA";
					break;
				case 6:
					editarTipo="Acer";
					break;
				case 7:
					editarTipo="HP";
					break;
				}
			} else if(editarCategoria.equals("Accesorios")){
				switch(pos){
				case 0:
					editarTipo="Cargador";
					break;
				case 1:
					editarTipo="Funda";
					break;
				case 2:
					editarTipo="Bateria";
					break;
			
				}
			} else if(editarCategoria.equals("Perifericos")){
				switch(pos){
				case 0:
					editarTipo="Escaneres";
					break;
				case 1:
					editarTipo="WebCams";
					break;
				case 2:
					editarTipo="Impresoras";
					break;
				case 3:
					editarTipo="USB";
					break;
				case 4:
					editarTipo="Auriculares";
					break;
				case 5:
					editarTipo="Microfonos";
					break;
				case 6:
					editarTipo="Teclados";
					break;
				case 7:
					editarTipo="Ratones";
					break;
				case 8:
					editarTipo="Altavoces";
					break;
				}
			} else if(editarCategoria.equals("Componentes")){
				switch(pos){
				case 0:
					editarTipo="Lector DVD";
					break;
				case 1:
					editarTipo="Placa base";
					break;
				case 2:
					editarTipo="Tarjeta sonido";
					break;
				case 3:
					editarTipo="Tarjeta grafica";
					break;
				case 4:
					editarTipo="RAM";
					break;
				case 5:
					editarTipo="Disco duro";
					break;
				case 6:
					editarTipo="Fuente alimentacion";
					break;
				}
			} else if(editarCategoria.equals("Tablets")){
				switch(pos){
				case 0:
					editarTipo="Acer";
					break;
				case 1:
					editarTipo="SAMSUNG";
					break;
				case 2:
					editarTipo="Motorola";
					break;
				case 3:
					editarTipo="BlackBerry";
					break;
				case 4:
					editarTipo="Nexus";
					break;
				case 5:
					editarTipo="HTC";
					break;
				}
				
			} else if(editarCategoria.equals("Portatiles")){
				switch(pos){
				case 0:
					editarTipo="HP";
					break;
				case 1:
					editarTipo="TOSHIBA";
					break;
				case 2:
					editarTipo="ASUS";
					break;
				case 3:
					editarTipo="Lenovo";
					break;
				case 4:
					editarTipo="SAMSUNG";
					break;
				case 5:
					editarTipo="Acer";
					break;
				case 6:
					editarTipo="Sony";
					break;
				case 7:
					editarTipo="COMPAQ";
					break;
				case 8:
					editarTipo="Packard Bell";
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
		getMenuInflater().inflate(R.menu.editar, menu);
		return true;
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
        case R.id.menu_editar:
        	//pasamos de string a entero
        	String precioString = precio.getText().toString();
        	int precioInt =Integer.parseInt(precioString);
        	HiloEditar editar = new HiloEditar();
        	editar.execute(new Producto(idProduct,nombre.getText().toString(),precioInt,editarCategoria,editarTipo));
        	Toast.makeText(getApplicationContext(), R.string.editado, Toast.LENGTH_LONG).show();
        	Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        	startActivity(intent);
        	return true;
    }
	    return super.onOptionsItemSelected(item);
	}
	/**
	 * 
	 * @author Javier
	 * Esta clase privada edita un producto ya existente.
	 */
	private class HiloEditar extends AsyncTask<Producto, Void, Void>{

			@Override
			protected Void doInBackground(Producto... params) {
				// TODO Auto-generated method stub
				CrudProductos pro = new CrudProductos();
				for(Producto c : params){
					pro.update(c);				
				}	
				return null;
			}
	    }
//	 private class HiloFind extends AsyncTask<Integer, Void, Producto>{
//
//			@Override
//			protected Producto doInBackground(Integer... params) {
//				// TODO Auto-generated method stub
//				Producto lpro = new Producto();
//				CrudProductos pro = new CrudProductos();
//				
//				for(Integer c : params){
//					lpro = pro.findByPK(c);			
//				}	
//				
//				return lpro;
//			}
//	    }
}
