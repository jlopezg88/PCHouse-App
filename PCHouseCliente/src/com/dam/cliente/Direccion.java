package com.dam.cliente;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.dam.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Esta clase muestra la dirección de la empresa y los datos de esta.
 * 
 * @author Javier
 * @version 1
 * 
 */

@SuppressLint("NewApi")
public class Direccion extends FragmentActivity {

	private GoogleMap mMap;
	static final LatLng REPUBLICA_ARGENTINA = new LatLng(37.377955, -6.001746);

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.direccion);
	   
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		
		
		mMap.animateCamera(CameraUpdateFactory.zoomTo(20f));
		//mMap.animateCamera(CameraUpdateFactory.scrollBy(10, 0));
		
		mMap.addMarker(new MarkerOptions()
        .position(REPUBLICA_ARGENTINA)
        .title("PC House")
        .snippet("Tu tienda de informática")
        .draggable(true)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador)));
        //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		
		
		CameraPosition camPos = mMap.getCameraPosition();
		
		Toast.makeText(getApplicationContext(), camPos.zoom+"",Toast.LENGTH_SHORT).show();
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
