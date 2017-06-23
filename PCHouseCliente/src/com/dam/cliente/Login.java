package com.dam.cliente;

import java.util.ArrayList;
import java.util.List;

import com.dam.R;
import com.dam.bbdd.CrudClientes;
import com.dam.listas.ListaClientes;
import com.dam.modelo.Cliente;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Esta clase permite logearse a un usuario ya existente y permite que se registren nuevos usuarios.
 * 
 * @author Javier
 * @version 1
 * 
 */

public class Login extends Activity {
	EditText usuario, pass;
	Button entrar, registrarse;
	ListaClientes l;
	CrudClientes cClient;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.login);
	    
	    usuario= (EditText) findViewById(R.id.loginUsuario);
	    pass = (EditText) findViewById(R.id.loginPass);
	    entrar= (Button) findViewById(R.id.buttonLoginEntrar);
	    registrarse=(Button) findViewById(R.id.buttonLoginRegistrar);
	    cClient = new CrudClientes();
	    
	    entrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				l = cClient.findAll();
				List<Cliente> clients = new ArrayList<Cliente>();
				clients = l.getLista();
				boolean comprobar = false;
				for (Cliente cliente : clients) {
					if (cliente.getCorreo().equals(
							usuario.getText().toString())
							&& cliente.getPass().equals(
									pass.getText().toString())) {
						comprobar = true;
					} 
				}

				if (comprobar) {
					Intent intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					guardarPref();
				} else {
					Toast.makeText(getApplicationContext(),
							R.string.comprobarLogin, Toast.LENGTH_LONG).show();

				}
			}
		});
	    
	    registrarse.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Registrarse.class);
				startActivity(intent);
				
				
			}
		});
	}
	/**
	 * Este método guarda en preferencias el correo electrónico para así logearse automaticamente
	 * 
	 */
	public void guardarPref (){
		String user = usuario.getText().toString();
		SharedPreferences pref = getSharedPreferences("com.dam",Context.MODE_PRIVATE);
		SharedPreferences.Editor ed = pref.edit();
		ed.putString(getString(R.string.usuarioPref), user);
		ed.commit();
	}

}
