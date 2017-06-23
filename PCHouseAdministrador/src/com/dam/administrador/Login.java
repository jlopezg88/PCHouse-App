package com.dam.administrador;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dam.bbdd.CrudAdministrador;
import com.dam.listas.ListaAdministrador;
import com.dam.modelo.Administrador;

/**
 * Esta clase es donde se logea el administrador con una cuenta previa creada
 * 
 * @author Javier
 * @version 1
 */
public class Login extends Activity {
	Button entrar;
	CrudAdministrador cAdmin;
	ListaAdministrador l;
	EditText usuario, contraseña;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		usuario = (EditText) findViewById(R.id.loginUsuario);
		contraseña = (EditText) findViewById(R.id.loginPass);
		entrar = (Button) findViewById(R.id.buttonLoginEntrar);
		cAdmin = new CrudAdministrador();

		entrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				l = cAdmin.findAll();
				List<Administrador> adminis = new ArrayList<Administrador>();
				adminis = l.getLista();
				boolean comprobar = false;
				for (Administrador administrador : adminis) {
					if (administrador.getCorreo().equals(
							usuario.getText().toString())
							&& administrador.getPass().equals(
									contraseña.getText().toString())) {
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
	}

	/**
	 * Este método guarda en preferencias el correo electrónico para así
	 * logearse automaticamente
	 * 
	 */
	public void guardarPref() {
		String user = usuario.getText().toString();
		SharedPreferences pref = getSharedPreferences("com.dam.administrador",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor ed = pref.edit();
		ed.putString(getString(R.string.usuarioPref), user);
		ed.commit();
	}

}
