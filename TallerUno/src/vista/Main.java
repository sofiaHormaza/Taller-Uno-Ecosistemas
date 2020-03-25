package vista;

import modelo.Logica;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet{
	
	int pantalla;
	PImage inicio,instr1,instr2,instr3,ganador1,ganador2,barraVida;
	PImage fondo1;
	
	Logica logica;

	public static void main(String[] args) {
		PApplet.main("vista.Main");
	}
	
	public void settings() {
		size(1000,600);
	}
	
	public void setup() {
		pantalla=0;
		inicio=loadImage("imagenes/inicio.jpg");
		instr1=loadImage("imagenes/instrucciones.jpg");
		instr2=loadImage("imagenes/instrucciones2.jpg");
		instr3=loadImage("imagenes/instrucciones3.jpg");
		ganador1=loadImage("imagenes/FinalJuego1.jpg");
		ganador2=loadImage("imagenes/FinalJuego2.jpg");
		fondo1=loadImage("imagenes/Juego.jpg");
		barraVida=loadImage("imagenes/BarraJugadores.png");
		
		logica=new Logica(this);
	}
	
	public void draw() {
		background(255);
		switch (pantalla) {
		case 0:
			image(inicio, 0, 0,1000,600);
			break;
		case 1:
			image(instr1,0,0,1000,600);
			break;
		case 2:
			image(instr2,0,0,1000,600);
			break;
		case 3:
			image(instr3,0,0,1000,600);
			break;
		case 4:
			image(fondo1,0,0,1000,1199);
			
			logica.generarEnemigos();
			logica.pintar();
			logica.mover();
			logica.choque();
			logica.borrarObjetos();

			image(barraVida,0,0,1000,46);
			logica.pintarVidas();
			
			if(logica.getVidasJ1()==0) {
				pantalla=6;
			}
			if(logica.getVidasJ2()==0) {
				pantalla=5;
			}
			break;
		case 5:
			image(ganador1,0,0,1000,600);
			break;
		case 6:
			image(ganador2,0,0,1000,600);
			break;
		}
		
		//text("X:"+mouseX+" Y:"+mouseY, mouseX, mouseY);
	}
	
	public void mousePressed() {
		switch (pantalla) {
		case 0:
			if(mouseX>=441 && mouseX<557 && mouseY>=317&&mouseY<=364) {
				pantalla=4;
			}
			
			if(mouseX>=422 && mouseX<579 && mouseY>=382&&mouseY<=430) {
				pantalla=1;
			}
			break;
		case 1:
			if(mouseX>=28 && mouseX<111 && mouseY>=514&&mouseY<=571) {
				pantalla=0;
			}
			
			if(mouseX>=889 && mouseX<971 && mouseY>=512&&mouseY<=570) {
				pantalla=2;
			}
			break;
		case 2:
			if(mouseX>=28 && mouseX<111 && mouseY>=514&&mouseY<=571) {
				pantalla=1;
			}
			
			if(mouseX>=889 && mouseX<971 && mouseY>=512&&mouseY<=570) {
				pantalla=3;
			}
			break;
		case 3:
			if(mouseX>=28 && mouseX<111 && mouseY>=514&&mouseY<=571) {
				pantalla=2;
			}
			
			if(mouseX>=889 && mouseX<971 && mouseY>=512&&mouseY<=570) {
				pantalla=4;
			}
			break;
		case 4:
			if(mouseX>=28 && mouseX<111 && mouseY>=514&&mouseY<=571) {
				pantalla=3;
			}
			
			if(mouseX>=927 && mouseX<971 && mouseY>=512&&mouseY<=570) {
				pantalla=5;
			}
			break;
		case 5:
			break;
		case 6:
			break;
		}
	}
	
	public void keyPressed() {

		logica.generarDefensa();
		logica.teclaPresionada();
	}
	
	public void keyReleased() {
		logica.teclaReleased();
	}

}
