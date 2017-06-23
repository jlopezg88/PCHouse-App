package com.dam.bbdd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dam.modelo.Carrito;
import com.dam.modelo.Producto;

/**
 * Esta clase añade, modifica, elimina busca y lista todo lso datos almacenados
 * en la base de datos interna
 * 
 * @author Javier
 * @version 1
 */

public class CrudCarrito implements Crudable<Carrito> {

	final String tabla = "Carrito";

	SQLiteOpenHelper h;

	/**
	 * Constructor con parámetro
	 * 
	 * @param h
	 */
	public CrudCarrito(SQLiteOpenHelper h) {
		this.h = h;
	}

	@Override
	public long insert(Carrito t) {
		SQLiteDatabase db = h.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("Nombre", t.getNombre());
		cv.put("Precio", t.getPrecio());
		cv.put("Categoria", t.getCategoria());
		cv.put("Tipo", t.getTipo());
		long rowid = db.insert(tabla, null, cv);
		db.close();
		return rowid;
	}

	@Override
	public int update(Carrito t) {
		SQLiteDatabase db = h.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("Nombre", t.getNombre());
		cv.put("Precio", t.getPrecio());
		cv.put("Categoria", t.getCategoria());
		cv.put("Tipo", t.getTipo());
		int n = db.update(tabla, cv, "ID_CA=?",
				new String[] { Integer.toString(t.getId_ca()) });
		db.close();
		return n;
	}

	@Override
	public int delete(Carrito t) {
		SQLiteDatabase db = h.getWritableDatabase();
		int n = db.delete(tabla, "ID_CA=?",
				new String[] { Integer.toString(t.getId_ca()) });
		db.close();
		return n;
	}

	@Override
	public List<Carrito> findAll() {
		SQLiteDatabase db = h.getReadableDatabase();
		ArrayList<Carrito> l = new ArrayList<Carrito>();
		Cursor c = db.query(tabla, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				l.add(new Carrito(c.getInt(0), c.getString(1), c.getInt(2), c
						.getString(3), c.getString(4)));
			} while (c.moveToNext());
		}
		db.close();
		return l;
	}

	@Override
	public Carrito findByPK(String pkvalue) {
		SQLiteDatabase db = h.getReadableDatabase();
		Carrito listaCarrito = null;
		Cursor c = db.query(tabla, null, "ID_CA=?", new String[] { pkvalue },
				null, null, null);
		if (c.moveToFirst()) {
			do {
				listaCarrito = new Carrito(c.getInt(0), c.getString(1),
						c.getInt(2), c.getString(3), c.getString(4));
			} while (c.moveToNext());
		}
		db.close();
		return listaCarrito;
	}

	public List<Carrito> borrarTodo(List<Carrito> lista) {
		SQLiteDatabase db = h.getReadableDatabase();
		ArrayList<Carrito> l = (ArrayList<Carrito>) lista;
		for(Carrito c: l){
			delete(c);
		}
		
		db.close();
		return l;
	}
}
