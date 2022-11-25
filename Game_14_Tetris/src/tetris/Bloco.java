package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bloco {

	public Color color;
	public int shape = 0;
	public int x,y;
	public int spd = 4;
	
	public boolean collisionFloor = true;
	public boolean colisao = false;
	
	
	public Bloco(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.shape = Shapes.random.nextInt(5);
		
		int colorChance = Shapes.random.nextInt(6);
		
		if(colorChance == 0) {
			color = Color.red;
		}else if(colorChance == 1) {
			color = Color.green;
		}else if(colorChance == 2) {
			color = Color.blue;
		}else if(colorChance == 3) {
			color = Color.yellow;
		} else if(colorChance == 3) {
			color = Color.GRAY;
		}else if(colorChance == 3) {
			color = Color.MAGENTA;
		}
	}
	
	public Bloco getCollision() {//checa o bloco que est� colidindo
		for(int i = 0; i < Main.blocos.size(); i++) {
			
			Bloco bloco = Main.blocos.get(i);
			
			if(bloco != this) {
				Rectangle me = new Rectangle(x,y,Shapes.shapes.get(shape).width,Shapes.shapes.get(shape).height);
				Rectangle other = new Rectangle(bloco.x,bloco.y,Shapes.shapes.get(bloco.shape).width,Shapes.shapes.get(bloco.shape).height);
				
				if(me.intersects(other)) {
					return bloco;
				}
			}
		}
		
		return null;
	}
	
	public void tick() {
		int hh = Shapes.shapes.get(shape).height;//recupera altura

		if(collisionFloor) {
			Bloco colisor = getCollision();
			
			if(colisor == null) {//sem colis�o
				y+=spd;	
			}else {
				if(colisor.color == this.color) {//� da mesma cor?
					Main.player = new Player(0,0);
					
					Main.blocos.remove(colisor);
					Main.blocos.remove(this);
					
					System.out.println("Pontuei...");
					
					return;
					
				}else if(colisor.colisao == false || this.colisao == false) {
					//Clonar o bloco
					Main.player = new Player(0,0);

					Bloco blocoClone = new Bloco(this.x, this.y);
					blocoClone.color = this.color;
					blocoClone.shape = this.shape;
					blocoClone.colisao = true;
					
					colisor.colisao = true;
					Main.blocos.add(blocoClone);
					
					Main.blocos.remove(this);
					
				}
			}
			
		}
		
		if(y+hh > 480) {//chegou no limite e n�o pode mais deixar
			
			y = 480 -hh;
			
			Bloco blocoClone = new Bloco(this.x, this.y);
			blocoClone.color = this.color;
			blocoClone.shape = this.shape;
			blocoClone.collisionFloor = false;
			
			Main.player = new Player(0,0);
			
			Main.blocos.add(blocoClone);
		}
		
		
	}
	
	public void render(Graphics g) {

		int ww = Shapes.shapes.get(shape).width;
		int hh = Shapes.shapes.get(shape).height;
			
		g.setColor(color);
		g.fillRect(x, y, ww, hh);

	}
}
