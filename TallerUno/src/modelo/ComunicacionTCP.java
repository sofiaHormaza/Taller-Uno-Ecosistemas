package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


import processing.core.PApplet;

public class ComunicacionTCP extends Thread {

	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private PApplet app;
	String line;
	Logica logica;
	
	public ComunicacionTCP(Logica logica) {
		this.logica=logica;
	}
	
	public void run() {
		try {
			ServerSocket server=new ServerSocket(5000);
			System.out.println("esperando");
			this.socket=server.accept();
			System.out.println("conexion aceptada");
			
			InputStream is=socket.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			this.reader=new BufferedReader(isr);
			
			OutputStream os=socket.getOutputStream();
			OutputStreamWriter osw=new OutputStreamWriter(os);
			this.writer=new BufferedWriter(osw);
			
			while(true) {
				recibirMensaje();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void esperarConexion() {
		this.start();
	}
	
	public void mandarMensaje(String mensaje) {
		new Thread(
				()->{
					try {
						writer.write(mensaje+"\n");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			).start();
	}
	
	public void recibirMensaje() throws IOException {
		line=reader.readLine();
		System.out.println(line);
		
		switch(line) {
		case "RIGHT":
			logica.jugador2.mover();
			break;
			
		case "LEFT":
			logica.jugador2.moverIzq();
			break;
			
		case "DISP":
			logica.generarDefensaDos();
			break;
		}
	}
	
	
	
	public void cerrarConexion() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
