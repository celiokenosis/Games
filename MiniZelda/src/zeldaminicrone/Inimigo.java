package zeldaminicrone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zeldaminicrone.Bullet;

public class Inimigo extends Rectangle{

	//vari�ves de controle do player
	public int right = 1, up = 0, down = 0, left = 0;//dire��es
	public int spd = 2; //velocidade
	
	//Anima��es
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15;	
	
	//Tiros
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	public boolean shoot = false;
	
	//Dire��o do player
	public int dir = 1;
	
	public Inimigo(int x, int y) {
		super(x,y,32,32);/*tamanho do jogador*/
	}
	
	//Perseguir Player
	public void perseguirPlayer() {
		
		Player p = Game.player;
		
		if(x < p.x && World.isFree(x+spd, y)) {
			//movimenta��o randomica/espontanea
			if(new Random().nextInt(100) < 50) {
				x+=spd;
			}
		}else if(x > p.x && World.isFree(x-spd, y)) {
			//movimenta��o randomica/espontanea
			if(new Random().nextInt(100) < 50) {
				x-=spd;
			}
		}
		
		if(y < p.y && World.isFree(x, y+spd)) {
			//movimenta��o randomica/espontanea
			if(new Random().nextInt(100) < 50) {
				y+=spd;
			}
		}else if(y > p.y && World.isFree(x, y-spd)) {
			//movimenta��o randomica/espontanea
			if(new Random().nextInt(100) < 50) {
				y-=spd;
			}
		}
	}
	
	/*Contoles de movimenta��o*/
	public void tick() {
		
		boolean moved = true;

		perseguirPlayer();
		
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation ++;
				
				if(curAnimation == Spritesheet.inimigo_front.length) {
					curAnimation = 0;
				}
			}
		}
		
		//Tiro
		if(shoot) {
			shoot = false;
			bullets.add(new Bullet(x,y,dir));
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Spritesheet.inimigo_front[curAnimation],x,y,32,32, null);
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);;
		}
		
	}
}
