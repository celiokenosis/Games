package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right, left;
	public int x, y, width, height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}
	
	/*cuida da lógica do jogo*/
	public void tick() {
		if(right) {
			x++;
		}else if(left) {
			x--;
		}
		
		//verifica tamanho da janela
		if(x+width > Game.WIDTH) {
			x = Game.WIDTH - width;
		}else if(x < 0) {
			x = 0;
		}
	}
	
	/*Renderiza o jogo*/
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);/*posição inicial do jogador*/
	}
}
