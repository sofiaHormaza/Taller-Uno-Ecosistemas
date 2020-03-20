package modelo;

import processing.core.PApplet;
import processing.core.PImage;

public class Defensa {

	int posX;
	int posY;
	int vel;
	PImage img;
	PApplet app;
	
	public Defensa(int posX,int posY,PApplet app) {
		this.posX=posX;
		this.posY=posY;
		this.img=app.loadImage("imagenes/Disparo.png");
		this.app=app;
	}
	
	public void pintar(PApplet app) {
		app.imageMode(app.CENTER);
		app.image(img, posX, posY,21,21);
		app.imageMode(app.CORNER);
	}
	
	public void disparar() {
			posY-=10;
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
