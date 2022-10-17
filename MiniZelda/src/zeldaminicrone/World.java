package zeldaminicrone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

	public static List<Blocks> blocos = new ArrayList<Blocks>();
	
	public World() {
		//Cria os blocos na horizontal superior
		for(int xx = 0; xx < 15*2; xx++ ) {
			blocos.add(new Blocks(xx*32,0));
		}
		
		for(int xx = 0; xx < 15*2; xx++ ) {
			blocos.add(new Blocks(xx*32,640-32));
		}
		
		//Cria os blocos na vertical
		for(int yy = 0; yy < 15*2; yy++ ) {
			blocos.add(new Blocks(0,yy*32));
		}
		
		for(int yy = 0; yy < 15*2; yy++ ) {
			blocos.add(new Blocks(640-32,yy*32));
		}
		
		//Cria parede
		blocos.add(new Blocks(220,100));
	}
	
	
	//Colisões
	public static boolean isFree(int x, int y) {
		for(int i = 0; i < blocos.size(); i++) {
			Blocks blocoAtual = blocos.get(i);
			
			if(blocoAtual.intersects(new Rectangle(x,y,32,32))) {
				return false;/*está colidindo*/
			}
		}
		return true;/*não está colidindo*/
	}
	
	public void render(Graphics g) {
		for(int i =0; i < blocos.size(); i++) {
			blocos.get(i).render(g);
		}
	}
}
