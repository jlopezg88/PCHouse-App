package com.dam.receceiver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.dam.R;
import com.dam.cliente.MainActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * Este clase recibe todas las ntificaciones y las muestra en el menu principal
 * @author Javier
 * @version 1
 */
public class RecibidorOfertas extends BroadcastReceiver {
	private static final int NOTIF_ALERTA_ID = 1;
	NotificationManager notManager;
	int port = 5555;
	ArrayList<String> oferta;
	String ofertaEntrante;
	boolean entra;
	String textoEntrante;
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Sockets socket = new Sockets();
		socket.execute();
		
		if(ofertaEntrante != null){
			entra=true;
		}else{
			entra=false;
		}
		if(entra){
		// Obtenemos una referencia al servicio de
		// notificaciones
		notManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

		// Configuramos la notificación
		int icono = R.drawable.notificacion;
		CharSequence textoEstado = "PCHouse";
		long hora = System.currentTimeMillis();

		Notification notif = new Notification(icono, textoEstado, hora);

		// Configuramos el Intent;
		CharSequence titulo = textoEntrante;
		CharSequence descripcion = "Nueva oferta en el la página principal.";

		Intent notIntent = new Intent(context, R.class);

		PendingIntent contIntent = PendingIntent.getActivity(context, 0,
				notIntent, 0);

		notif.setLatestEventInfo(context, titulo, descripcion, contIntent);

		// AutoCancel: cuando se pulsa la notificaión ésta
		// desaparece
		notif.flags |= Notification.FLAG_AUTO_CANCEL;

		// Añadir sonido, vibración y luces
		 notif.defaults |= Notification.DEFAULT_SOUND;
		 notif.defaults |= Notification.DEFAULT_VIBRATE;
		// notif.defaults |= Notification.DEFAULT_LIGHTS;

		// Enviar notificación
		notManager.notify(NOTIF_ALERTA_ID, notif);
		}
	}
	/**
	 * Esta clase recibe la oferta con el socket
	 * @author Javier
	 *
	 */
	public class Sockets extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			Socket s = null;
			ObjectInputStream ois = null;
			try {
				s = new Socket("192.168.1.33", port);				
		
				ois = new ObjectInputStream(s.getInputStream());
				oferta = (ArrayList<String>) ois.readObject();
				for (String x : oferta) {
					ofertaEntrante=x;
				}
				

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (ois != null)
						ois.close();
					if (s != null)
						s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return ofertaEntrante;
		}
		@Override
		protected void onPostExecute(String result) {
			textoEntrante = result;
		}
	}

}
