package com.dam.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

	private Server server = null;
	private Socket socket = null;
	private ObjectOutputStream streamOut = null;
	private ObjectInputStream streamIn = null;
	private String telefono = null;

	//
	public ServerThread(Server _server, Socket _socket) {

		super();
		server = _server;
		socket = _socket;
	}

	public void sendOfertas() {
		try {
			streamOut.writeObject(server.getList());
			streamOut.flush();
		} catch (IOException ioe) {
			System.out.println(" ERROR: " + ioe.getMessage());
			server.stop();
			interrupt();
		}
	}

	public void run() {

		System.out.println("Server Thread Escritor andando.");
		while (true) {

		}
	}

	public void open() throws IOException {

		streamOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		streamIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		sendOfertas();
		
		telefono = streamIn.readUTF();
		server.addOferta(telefono);
	}

	public void close() throws IOException {		
		interrupt();
		if (socket != null)
			socket.close();
		if (streamOut != null)
			streamOut.close();
		if (streamIn != null)
			streamIn.close();
	}
}