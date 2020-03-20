package modelo;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemigo {

	int posX;
	int posY;
	int vel;
	PImage img,img1,img2,img3;
	PApplet app;
	int tipo;
	
	public Enemigo(int posX, int posY, int tipo, PApplet app) {
		this.posX=posX;
		this.posY=posY;
		this.tipo=tipo;
		this.img=app.loadImage("imagenes/Enemigo1.png");
		this.img1=app.loadImage("imagenes/Enemigo2.png");
		this.img2=app.loadImage("imagenes/Enemigo3.png");
		this.img3=app.loadImage("imagenes/Enemigo4.png");
		this.app=app;
	}
	
	public void pintar(PApplet app) {
		
		//pintar los enemigos segun su tipo
		switch(tipo) {
		case 0:
			app.imageMode(app.CENTER);
			app.image(img, posX, posY,80,144);
			app.imageMode(app.CORNER);
			break;
		case 1:
			app.imageMode(app.CENTER);
			app.image(img1, posX, posY,55,77);
			app.imageMode(app.CORNER);
			break;
		case 2:
			app.imageMode(app.CENTER);
			app.image(img2, posX, posY,124,124);
			app.imageMode(app.CORNER);
			break;
		case 3:
			app.imageMode(app.CENTER);
			app.image(img3, posX, posY,32,96);
			app.imageMode(app.CORNER);
			break;
		}
	}
	
	public void mover() {
		
		//velocidad de los enemigos segun su tipo
		switch(tipo) {
			
		case 0:
			posY+=3;
			break;
			
		case 1: 
			posY+=5;
			break;
			
		case 2:
			posY+=7;
			break;
			
		case 3:
			posY+=10;
			break;
		}
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
