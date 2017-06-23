package com.dam.cliente;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dam.R;

/**
 * Esta clase se compone de las diferentes opciones que contiene Servicios.
 * 
 * @author Javier
 * @version 1
 * 
 */

@SuppressLint("NewApi")
public class Servicios extends Activity {

	Button consultoria, direccion, quienesSomos;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.servicios);
	    
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    consultoria = (Button) findViewById(R.id.buttonConsultoria);
	    direccion = (Button) findViewById(R.id.buttonDireccion);
	    quienesSomos = (Button) findViewById(R.id.buttonQuienesSomos);
	    
	    consultoria.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (getApplicationContext(),Consultoria.class);
				startActivity(intent);
				
			}
		});
	    
	    direccion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (getApplicationContext(),Direccion.class);
				startActivity(intent);
				
			}
		});
	    
	    quienesSomos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (getApplicationContext(),QuienesSomos.class);
				startActivity(intent);
				
			}
		});
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
