package com.celio.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public String[] options = {"novo jogo","carregar jogo","sair"};
	public int currentOption = 0;
	public int maxOption = options.length -1;
	public boolean up, down;
	
	public void tick() {
		if(up) {
			currentOption --;
			
			if(currentOption < 0) {
				currentOption = maxOption;
			}
		}

		if(down) {
			currentOption --;
			
			if(currentOption > maxOption) {
				currentOption = 0;
			}
		}

	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,36));
		g.drawString(">Danki.Code<", maxOption, currentOption);
	}
}
