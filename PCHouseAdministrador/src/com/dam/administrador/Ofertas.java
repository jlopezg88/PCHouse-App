package com.dam.administrador;



import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.dam.bbdd.CrudOfertas;
import com.dam.listas.ListaOfertas;
import com.dam.modelo.Oferta;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Esta clase añade ofertas a su correspondiente tabla de la base de datos
 * @author Javier
 * @version 1
 */
@SuppressLint("NewApi")
public class Ofertas extends Activity {

	EditText titulo, descripcion;
	static CrudOfertas cOfertas;
	int port = 5555;
	ListaOfertas l;
	int id;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.oferta);
	    
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    titulo = (EditText) findViewById(R.id.editTitulo);
	    descripcion= (EditText) findViewById(R.id.editDescripcion);
	    cOfertas = new CrudOfertas();
	  //Incrementa el id en uno para añadirlo
		l = cOfertas.findAll();
		List<Oferta> ofer = new ArrayList<Oferta>();
		ofer = l.getLista();
	    id = 0;
		for (Oferta u : ofer) {
			if (u.getIdO() > id) {
				id = u.getIdO();
			}
		}
		id++;
	    
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.oferta, menu);
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
        case R.id.enviar:
        	if (titulo.getText().toString().equals("")|| descripcion.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(),R.string.campoVacio, Toast.LENGTH_LONG).show();
			} else {
				
			AddOferta oferta = new AddOferta();
			oferta.execute();
        	Toast.makeText(getApplicationContext(), R.string.ofertaEnviada, Toast.LENGTH_LONG).show();
        	Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        	startActivity(intent);
			}
        	return true;
    }
	    return super.onOptionsItemSelected(item);
	}
	/**
	 * Esta Clase añade una oferta e envia una notificacion al cliente
	 * @author Javier
	 *
	 */
	public class AddOferta extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			cOfertas.insert(new Oferta(id, titulo.getText().toString(), descripcion.getText().toString()));
			Socket s = null;
			ObjectOutputStream oos = null;
			try {
				s = new Socket("192.168.1.33", port);				
				oos = new ObjectOutputStream(s.getOutputStream());
				String d = titulo.getText().toString();
				oos.writeUTF(d);
				oos.flush();

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (oos != null)
						oos.close();
					if (s != null)
						s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
	}
}
