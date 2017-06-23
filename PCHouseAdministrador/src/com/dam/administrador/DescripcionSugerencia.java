package com.dam.administrador;

import com.dam.bbdd.CrudSugerencias;
import com.dam.modelo.Sugerencia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
/**
 * Esta clase proporcinará ver todo los datos de la sugerencia previamente elegido
 * @author Javier
 * @version 1
 */
@SuppressLint({ "NewApi", "UseValueOf" })
public class DescripcionSugerencia extends Activity {

	TextView tipo, titulo, mensaje;
	Intent i;
	CrudSugerencias cSugerencia;
	//HiloFind buscarId;
	String mostrarTipo, mostrarTitulo, mostrarMensaje;
	Sugerencia sugerencia;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.descripcion_sugerencia);
	   
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    tipo = (TextView) findViewById(R.id.tipo);
	    titulo = (TextView) findViewById(R.id.titulosu);
	    mensaje = (TextView) findViewById(R.id.mensaje);
	    
	    i = getIntent();
	  	mostrarTipo= i.getExtras().getString("tipo");
	  	mostrarTitulo=i.getExtras().getString("titulo");
	  	mostrarMensaje=i.getExtras().getString("mensaje");
	    cSugerencia = new CrudSugerencias();
	  	    
	  	//buscarId.execute(id);
	    
	  	tipo.setText(mostrarTipo);
	  	titulo.setText(mostrarTitulo);
	  	mensaje.setText(mostrarMensaje);
	    
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
