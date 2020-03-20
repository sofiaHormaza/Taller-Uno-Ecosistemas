package modelo;

import processing.core.PApplet;
import processing.core.PImage;

public class JugadorUno {

	int posX;
	int posY;
	int vel;
	PImage img;
	PApplet app;
	
	public JugadorUno(int posX,int posY,PApplet app) {
		this.posX=posX;
		this.posY=posY;
		this.app=app;
		this.img=app.loadImage("imagenes/J1.png");
		this.vel=5;
	}
	
	public void pintar(PApplet app) {
		app.imageMode(app.CENTER);
		app.image(img, posX, posY,130,131);
		app.imageMode(app.CORNER);
	}
	
	public void mover() {
			posX+=vel;
	}
	
	public void moverIzq() {
		posX-=vel;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
}
