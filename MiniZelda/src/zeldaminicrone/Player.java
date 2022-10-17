package zeldaminicrone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import zeldaminicrone.Bullet;

public class Player extends Rectangle{

	//variáves de controle do player
	public boolean right, up, down, left;//direções
	public int spd = 4; //velocidade
	
	//Animações
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15;	
	
	//Tiros
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	public boolean shoot = false;
	
	//Direção do player
	public int dir = 1;
	
	public Player(int x, int y) {
		super(x,y,32,32);/*tamanho do jogador*/
	}
	
	/*Contoles de movimentação*/
	public void tick() {
		
		boolean moved = false;
		
		if(right && World.isFree(x+spd, y)) {
			x+=spd;
			moved = true;
			dir = 1;
		}else if(left && World.isFree(x-spd, y)) {
			x-=spd;
			moved = true;
			dir = -1;
		}
		
		if(up && World.isFree(x+spd, y-spd)) {
			y-=spd;
			moved = true;
		}else if(down && World.isFree(x, y+spd)) {
			y+=spd;
			moved = true;
		}
		
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation ++;
				
				if(curAnimation == Spritesheet.player_front.length) {
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
		g.drawImage(Spritesheet.player_front[curAnimation],x,y,32,32, null);
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);;
		}
		
	}
}
