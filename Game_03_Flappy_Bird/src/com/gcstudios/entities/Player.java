package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class Player extends Entity{
	
	public boolean isPressed = false;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}
	
	public void tick(){
		
		depth = 2;//deixa o passaro por cima
		
		if(!isPressed) {
			y+=2;
		}else {
			if(y > 0) {
				y-=2;	
			}
		}
		
		if(y > Game.HEIGHT) {//passaro saiu da tela
			World.restartGame();//reinicia o jogo
		}
		
		//Testar colisão
		for(int i = 0 ; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			
			if(e !=this) {//exclui a colisão consigo mesmo
				if(Entity.isColidding(this, e)) {
					//game Over
					World.restartGame();//reinicia o jogo
					return;	
				}
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		if(!isPressed) {
			g2.rotate(Math.toRadians(20), this.getX()+width/2, this.getY()+height/2);
			g2.drawImage(sprite,this.getX(), this.getY(), null);
			g2.rotate(Math.toRadians(-20), this.getX()+width/2, this.getY()+height/2);
		}else {
			//g2.rotate(Math.toRadians(-20), this.getX()+width/2, this.getY()+height/2);
			g2.drawImage(sprite,this.getX(), this.getY(), null);
			//g2.rotate(Math.toRadians(20), this.getX()+width/2, this.getY()+height/2);
		}
	}
}
