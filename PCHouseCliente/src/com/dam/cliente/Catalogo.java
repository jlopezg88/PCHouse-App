package com.dam.cliente;

import com.dam.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Esta clase se compone de las opciones que tiene el Catalogo disponibles.
 * 
 * @author Javier
 * @version 1
 * 
 */

@SuppressLint("NewApi")
public class Catalogo extends Activity {

	Button componentes, monitores, accesorios, portatiles, perifericos, tablets;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.catalogo);
	    
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    componentes = (Button) findViewById(R.id.buttonComponentes);
	    monitores = (Button) findViewById(R.id.buttonMonitores);
	    accesorios = (Button) findViewById(R.id.buttonAccesorios);
	    portatiles = (Button) findViewById(R.id.buttonPortatiles);
	    perifericos = (Button) findViewById(R.id.buttonPerifericos);
	    tablets = (Button) findViewById(R.id.buttonTablets);
	    
	    //Accede a la categoria componentes
	    componentes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Componentes.class);
				startActivity(intent);
			}
		});
	    //Accede a la categoria monitores
	    monitores.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Monitores.class);
				startActivity(intent);
				
			}
		});
	    //Accede a la categoria accesorios
	    accesorios.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Accesorios.class);
				startActivity(intent);
				
			}
		});
	    //Accede a la categoria perifericos
	    perifericos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Perifericos.class);
				startActivity(intent);
				
			}
		});
	    //Accede a la categoria tablets
	    tablets.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Tablets.class);
				startActivity(intent);
				
			}
		});
	    
	    //Accede a la categoria portatiles
	    portatiles.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Portatiles.class);
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
