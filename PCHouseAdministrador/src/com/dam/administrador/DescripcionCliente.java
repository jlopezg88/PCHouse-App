package com.dam.administrador;

import com.dam.bbdd.CrudClientes;
import com.dam.modelo.Cliente;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
/**
 * Esta clase proporcinará ver todo los datos del usuario previamente elegido
 * @author Javier
 * @version 1
 */
@SuppressLint({ "NewApi", "UseValueOf" })
public class DescripcionCliente extends Activity {
	
	Intent i;
	int idCli;
	CrudClientes cCliente;
	Integer id;
	Cliente cliente;
	//HiloBuscarCliente buscarId;
	TextView nombre, apellidos,direccion,correo,telefono;
	String mostrarNom, mostrarApellidos,mostrarCorreo, mostrarDireccion,mostrarTelefono;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.descripcion_cliente);
	
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    nombre = (TextView) findViewById(R.id.nombre);
	    apellidos = (TextView) findViewById(R.id.apellidos);
	    direccion = (TextView) findViewById(R.id.direccion);
	    correo = (TextView) findViewById(R.id.correo);
	    telefono = (TextView) findViewById(R.id.telefono);
	    
	    i = getIntent();
		idCli= i.getExtras().getInt("idLisCli");
	    id = new Integer(idCli);
	    mostrarNom =i.getExtras().getString("nombre");
	    mostrarApellidos =i.getExtras().getString("apellidos");
	    mostrarCorreo =i.getExtras().getString("correo");
	    mostrarDireccion=i.getExtras().getString("direccion");
	    mostrarTelefono=i.getExtras().getString("telefono");
	    cCliente = new CrudClientes();
	   // buscarId.execute(id);
    
	    nombre.setText(mostrarNom);
	    apellidos.setText(mostrarApellidos);
	    direccion.setText(mostrarDireccion);
	    correo.setText(mostrarCorreo);
	    telefono.setText(mostrarTelefono);
	    
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

}
