package modelo;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica {

	int vidasJ1;
	int vidasJ2;
	PApplet app;
	ArrayList<Enemigo>enemigos;
	ArrayList<Enemigo>enemigos2;
	ArrayList<Defensa>defensa;
	ArrayList<Defensa>defensa2;
	JugadorUno jugador1;
	JugadorDos jugador2;
	boolean teclaLeft;
	boolean teclaRight;
	
	ComunicacionTCP comm;
	
	public Logica(PApplet app) {
		vidasJ1=3;
		vidasJ2=3;
		teclaLeft=false;
		teclaRight=false;
		
		this.app=app;
		jugador1=new JugadorUno(246, 474, app);
		jugador2=new JugadorDos(761, 474, app);
		enemigos=new ArrayList<Enemigo>();
		enemigos2=new ArrayList<Enemigo>();
		defensa=new ArrayList<Defensa>();
		defensa2=new ArrayList<Defensa>();
		
		comm=new ComunicacionTCP(this);
		comm.esperarConexion();
	}
	
	public void choque() {
		//eliminar enemigos y balas cuando chocan en el espacio del jugador 1
		for (int i = 0; i < enemigos.size(); i++) {
			for (int j = 0; j < defensa.size(); j++) {
				if(app.dist(enemigos.get(i).getPosX(), enemigos.get(i).getPosY(), 
						defensa.get(j).getPosX(), defensa.get(j).getPosY())<=20) {
					enemigos.remove(i);
					defensa.remove(j);
				}
			}
		}
		
		
		//choque enemigos y jugador 1
		for (int k = 0; k < enemigos.size(); k++) {
			if(app.dist(jugador1.getPosX(), jugador1.getPosY(), 
					enemigos.get(k).getPosX(), enemigos.get(k).getPosY())<=65) {
				vidasJ1-=1;
				enemigos.remove(k);
			}
		}
	}
	
	public void mover() {
		
		//mover con las flechas del teclado al jugador 1
		if(teclaRight && jugador1.getPosX()<450) {
			jugador1.mover();
		}
		if(teclaLeft && jugador1.getPosX()>50) {
			jugador1.moverIzq();
		}
		
	}
	
	public void generarEnemigos() {
		
		//generar enemigos en el espacio del jugador 1
		if(app.frameCount%50==0) {
			enemigos.add(new Enemigo((int)app.random(50,450),20,(int)app.random(0,3),app));
		}
		
		//generar enemigos en el espacio del jugador 2
		if(app.frameCount%50==0) {
			enemigos2.add(new Enemigo((int)app.random(550,950),20,(int)app.random(0,3),app));
		}
	}
	
	public void generarDefensa() {
		
		//generar defensa para el jugador 1
		if(app.keyCode==app.UP) {
			defensa.add(new Defensa(jugador1.getPosX(),jugador1.getPosY()-50,app));
		}
	}
	
	public void borrarObjetos() {
		
		//borrar enemigos del jugador 1 cuando se salen de la pantalla
		for (int i = 0; i < enemigos.size(); i++) {
			if(enemigos.get(i).getPosY()>=600) {
				enemigos.remove(i);
			}
		}
		
		//borrar enemigos del jugador 2 cuando se salen de la pantalla
		for (int i = 0; i < enemigos2.size(); i++) {
			if(enemigos2.get(i).getPosY()>=600) {
				enemigos2.remove(i);
			}
		}
		
		//borrar defensa del jugador 1 cuando se sale de la pantalla
		for (int i = 0; i < defensa.size(); i++) {
			if(defensa.get(i).getPosY()<=5) {
				defensa.remove(i);
			}
		}
	}
	

	
	public void pintar() {
		
		//pintar jugadores
		jugador1.pintar(app);
		jugador2.pintar(app);
		
		//pintar enemigos del jugador1
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).pintar(app);
			enemigos.get(i).mover();
		}
		
		//pintar enemigos del jugador 2
		for (int j = 0; j < enemigos2.size(); j++) {
			enemigos2.get(j).pintar(app);
			enemigos2.get(j).mover();
		}
		
		//pintar defensa del jugador 1
		for (int i = 0; i < defensa.size(); i++) {
			defensa.get(i).pintar(app);
			defensa.get(i).disparar();
		}
		
		//pintar defensa del jugador 2
		for (int i = 0; i < defensa2.size(); i++) {
			defensa2.get(i).pintar(app);
			defensa2.get(i).disparar();
		}
	}
	
	public void pintarVidas() {
		app.fill(0);
		app.textSize(18);
		app.text(vidasJ1,305,30);
		app.text(vidasJ2, 800, 30);
		app.fill(255);
	}
	
	public void teclaPresionada() {
		if(app.keyCode==app.RIGHT ) {
			teclaRight=true;
		}
		if(app.keyCode==app.LEFT ) {
			teclaLeft=true;
		}
	}
	
	public void teclaReleased() {
		if(app.keyCode==app.RIGHT) {
			teclaRight=false;
		}
		if(app.keyCode==app.LEFT) {
			teclaLeft=false;
		}
	}

	public int getVidasJ1() {
		return vidasJ1;
	}

	public void setVidasJ1(int vidasJ1) {
		this.vidasJ1 = vidasJ1;
	}

	public int getVidasJ2() {
		return vidasJ2;
	}

	public void setVidasJ2(int vidasJ2) {
		this.vidasJ2 = vidasJ2;
	}

	public ArrayList<Defensa> getDefensa2() {
		return defensa2;
	}

	public void setDefensa2(ArrayList<Defensa> defensa2) {
		this.defensa2 = defensa2;
	}
	
	
	
	
}
