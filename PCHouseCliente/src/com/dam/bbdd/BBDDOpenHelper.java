package com.dam.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


/**
 * Esta clase crea una tabla, la actualiza y la elimina de la base de datos interna
 * 
 * @author Javier
 * @version 1
 */
 
public class BBDDOpenHelper extends SQLiteOpenHelper {

	//Creamos una tabla carrito en la base de datos
	private final String CREATE_TABLE_CARRITO=
			"CREATE TABLE CARRITO"
			+ "(" 
			+ "		ID_CA INTEGER PRIMARY KEY,"
			+ "		NOMBRE TEXT,"
			+ "		PRECIO INTEGER,"
			+ "		CATEGORIA TEXT,"
			+ "		TIPO TEXT"
			+ ")";
	
	/**
	 * Constructor con parámetros.
	 *
	 * @param context 
	 * @param name 
	 * @param factory 
	 * @param version 
	 */
	public BBDDOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_CARRITO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String DROP_TABLE_CARRITO = "DROP TABLE IF EXISTS CARRITO";
		db.execSQL(DROP_TABLE_CARRITO);

		
		onCreate(db);
				
	}


}
