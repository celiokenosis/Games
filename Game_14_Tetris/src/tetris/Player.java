package tetris;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right, left;
	public boolean changeShape = false;
	
	public int x,y;
	
	public Bloco bloco;
		
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		
		bloco = new Bloco(x,y);
	}
	
	public boolean canGo(int nextx, int nexty) {
		
		if(x < 0) {
			x = 0;
			return false;
		}	
		
		int ww = Shapes.shapes.get(bloco.shape).width;
		
		if(x + ww > 480) {
			x = 480 - ww;
			
			return false;
		}	
				
		return true;/*padr�o � ir*/
	}
	public void tick() {
		
		if(right && canGo(x+4,y)){
			x+=4;
		}else if(left  && canGo(x-4,y)) {
			x-=4;
		}
		
		if(changeShape) {
			changeShape = false;
			bloco.shape++;
			
			if(bloco.shape == Shapes.shapes.size()) {
				bloco.shape = 0;
			}
		}
		
		bloco.x = x;
		bloco.tick();
				
	}
	
	public void render(Graphics g) {
		bloco.render(g);
	}
}
