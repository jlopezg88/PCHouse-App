package com.dam.cliente;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dam.R;
import com.dam.bbdd.BBDDOpenHelper;
import com.dam.bbdd.CrudCarrito;
import com.dam.bbdd.CrudLineaPedido;
import com.dam.bbdd.CrudPedidos;
import com.dam.listas.ListaLineaPedidos;
import com.dam.listas.ListaPedidos;
import com.dam.modelo.Carrito;
import com.dam.modelo.Lineapedido;
import com.dam.modelo.Pedido;


/**
 * En esta clase es donde se comprueba que los datos son correctos y hace el pago efectivo.
 * 
 * @author Javier
 * @version 1
 * 
 */

@SuppressLint("NewApi")
public class Pago extends Activity implements OnItemSelectedListener{

	Spinner pago;
	EditText tarjeta,ccv;
	Button comprar;
	String tipoTarjeta;
	BBDDOpenHelper bbdd;
	List<Carrito> lista;
	SharedPreferences pref;
	CrudCarrito cCarrito;
	CrudLineaPedido cLineaped;
	CrudPedidos cPedidos;
	ListaPedidos l;
	ListaLineaPedidos lis;
	String correo;
	int id;
	int idLinea;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.pago);
		
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    
	    tarjeta = (EditText) findViewById(R.id.editTarjeta);
	    ccv = (EditText) findViewById(R.id.editCcv);
	    pago = (Spinner) findViewById(R.id.spinnerPago);
	    comprar = (Button) findViewById(R.id.buttonCompra);
	    
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.pago, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    pago.setAdapter(adapter);		
	    pago.setOnItemSelectedListener(this);
	    bbdd = new BBDDOpenHelper(getBaseContext(), "CarritoBBDD", null, 1);
	    cCarrito = new CrudCarrito(bbdd);
	    cPedidos = new CrudPedidos();
	    cLineaped = new CrudLineaPedido();
	    lista = cCarrito.findAll();
	    
	    pref = getSharedPreferences("com.dam",Context.MODE_PRIVATE);		
	    correo = pref.getString(getString(R.string.usuarioPref), null);
		//Incrementa el id en uno para añadirlo
		l = cPedidos.findAll();
		List<Pedido> ped = new ArrayList<Pedido>();
		ped = l.getLista();
		id = 0;
		for (Pedido u : ped) {
			if (u.getIdPe() > id) {
				id = u.getIdPe();
			}
		}
		id++;
		//Incrementa el id en uno para añadirlo
		lis = cLineaped.findAll();
		List<Lineapedido> lineped = new ArrayList<Lineapedido>();
		lineped = lis.getLista();
		idLinea = 0;
		for (Lineapedido u : lineped) {
			if (u.getIdLp() > idLinea) {
				idLinea = u.getIdLp();
			}
		}
		idLinea++;		
	    comprar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(tarjeta.getText().toString().equals("")||ccv.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), R.string.campoVacio, Toast.LENGTH_LONG).show();
				}else{
//					AddLinea line= new AddLinea();
//					line.execute();
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							for (Carrito carro : lista) {
								cLineaped.insert(new Lineapedido(idLinea, carro.getNombre(), carro.getPrecio(), carro.getCategoria(), carro.getTipo(), correo, tipoTarjeta));
								idLinea++;
							}
							cPedidos.insert(new Pedido(id, correo));
						}
					}).start();
					cCarrito.borrarTodo(lista);
					Toast.makeText(getApplicationContext(), R.string.pagado, Toast.LENGTH_LONG).show();
					Intent intent = new Intent(getApplicationContext(),MisCompras.class);
					startActivity(intent);
				}
				
				
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
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long id) {
		switch (pos) {
		case 0:
			tipoTarjeta="VISA";
			break;
		case 1:
			tipoTarjeta="VISA ELECTRON";
			break;
		case 2:
			tipoTarjeta="MASTERCARD";
			break;

		}
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
