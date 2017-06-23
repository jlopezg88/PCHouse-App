package com.dam.cliente;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.dam.R;
import com.dam.bbdd.CrudClientes;
import com.dam.listas.ListaClientes;
import com.dam.modelo.Cliente;

/**
 * Esta clase tiene la función de crear nuevos Clientes.
 * 
 * @author Javier
 * @version 1
 * 
 */

public class Registrarse extends Activity {

	EditText nombre, apellidos, correo, direccion, telefono, contraseña;
	CrudClientes cCliente;
	ListaClientes l;
	int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrarse);

		nombre = (EditText) findViewById(R.id.editNombre);
		apellidos = (EditText) findViewById(R.id.editApellidos);
		direccion = (EditText) findViewById(R.id.editDireccion);
		correo = (EditText) findViewById(R.id.editCorreo);
		telefono = (EditText) findViewById(R.id.editTelefono);
		contraseña = (EditText) findViewById(R.id.editpass);

		cCliente = new CrudClientes();

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registrarse, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.registro:
			if (nombre.getText().toString().equals("")
					|| apellidos.getText().toString().equals("")
					|| correo.getText().toString().equals("")
					|| direccion.getText().toString().equals("")
					|| telefono.getText().toString().equals("")
					|| contraseña.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(), R.string.campoVacio,
						Toast.LENGTH_LONG).show();
			} else {
				//recojo la lista de clientes
				l = cCliente.findAll();
				List<Cliente> clients = new ArrayList<Cliente>();
				clients = l.getLista();
				boolean comprobar = false;
				//los recorro para comprobar si los datos son correctos
				for (Cliente cliente : clients) {
					if (cliente.getCorreo().equals(correo.getText().toString())) {
						comprobar = true;
					}
				}
				//Si el correo existe  te salta un mensaje diciendo que es invalido
				if (comprobar) {
					Toast.makeText(getApplicationContext(),
							R.string.correoNoValido, Toast.LENGTH_LONG).show();
				} else {

					//Incrementa el id en uno para añadirlo
					l = cCliente.findAll();
					List<Cliente> cliente = new ArrayList<Cliente>();
					cliente = l.getLista();
					id = 0;
					for (Cliente u : cliente) {
						if (u.getIdC() > id) {
							id = u.getIdC();
						}
					}
					id++;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							//Inserta el cliente
							cCliente.insert(new Cliente(id, nombre.getText()
									.toString(),
									apellidos.getText().toString(), direccion
											.getText().toString(), correo
											.getText().toString(), telefono
											.getText().toString(), contraseña
											.getText().toString()));
							
						}
					}).start();
					Toast.makeText(getApplicationContext(), R.string.registrado, Toast.LENGTH_LONG).show();
					Intent intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					
				}

			}

			return true;

		}
		return super.onOptionsItemSelected(item);
	}


}
