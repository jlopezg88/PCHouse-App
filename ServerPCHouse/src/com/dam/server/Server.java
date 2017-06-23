package com.dam.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

	private List<ServerThread> serverThreadList=new ArrayList<ServerThread>();
	private ArrayList<String> listaOfertas = new ArrayList<String>();
	private ServerSocket server = null;
	private Thread thread = null;

	public synchronized int addOferta(String s) {
		listaOfertas.add(s);
		System.out.println("Ofertas enviadas: ");
		for (String oferta : listaOfertas) {
			System.out.println("\t - " +oferta);
		}
		return listaOfertas.size();
	}

	public synchronized int closeClient(String s) {
		listaOfertas.remove(s);
		return listaOfertas.size();
	}

	public ArrayList<String> getList() {
		return listaOfertas;
	}

	public Server(int port) {
		try {

			System.out
					.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			start();
		} catch (IOException ioe) {
			System.out.println("Can not bind to port " + port + ": "
					+ ioe.getMessage());
		}
	}

	public void run() {

		while (thread != null) {
			try {
				System.out.println("Waiting for a client ...");
				addThread(server.accept());// acepta el cliente y nos devuelve
											// un socket qu es la conexion con
											// el cliente
			} catch (IOException ioe) {
				System.out.println("Server accept error: " + ioe);
				stop();
			}
		}
	}

	public void start() {// crea el hilo y lo arranca

		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {

		if (thread != null) {
			for (ServerThread serverThread : serverThreadList) {
				try {
					serverThread.close();
				} catch (IOException e) {
				}
			}
			thread.interrupt();
			thread = null;
		}
	}

	private void addThread(Socket socket) {

		ServerThread clienteNuevo = new ServerThread(this, socket);
	
		try {
			clienteNuevo.open();
			clienteNuevo.start();
			serverThreadList.add(clienteNuevo);
		} catch (IOException ioe) {
			//ioe.printStackTrace()
			System.out.println("Error opening thread: " + ioe);
		}

	}

	public static void main(String args[]) {

		Server server = null;
		if (args.length != 1)
			System.out.println("Usage: java ChatServer port");
		else
			server = new Server(Integer.parseInt(args[0]));
	}
}
