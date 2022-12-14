package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.gcstudios.main.Game;
import com.gcstudios.world.AStar;
import com.gcstudios.world.Camera;
import com.gcstudios.world.Vector2i;



public class Enemy extends Entity{
	
	public boolean ghostMode = false;
	public int ghostFrames = 0;
	public int nextTime = Entity.rand.nextInt(60*5 - 60*3) + 60*3;/*para deixar aleat?rio a parada dos fantasmas*/

	public Enemy(int x, int y, int width, int height,int speed, BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}

	public void tick(){
		depth = 0;

		if(ghostMode == false ) {//persegue
		
			if(path == null || path.size() == 0) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
					path = AStar.findPath(Game.world, start, end);
				}
			
				if(new Random().nextInt(100) < 80)//velocidade do inimigo
					followPath(path);
				
				if(x % 16 == 0 && y % 16 == 0) {
					if(new Random().nextInt(100) < 10) {
						Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
						Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
						path = AStar.findPath(Game.world, start, end);
					}
				}
		}
			
			ghostFrames++;
			
			if(ghostFrames == 60*4) {/*60 ? 1 segundo*/
				
				nextTime = Entity.rand.nextInt(60*5 - 60*3) + 60*3;
				ghostFrames=0;
				
				if(ghostMode == false) {
					ghostMode = true;
				}else {
					ghostMode = false;
				}
				
			}
	}
	

	
	public void render(Graphics g) {

		super.render(g);
		
	}
}
