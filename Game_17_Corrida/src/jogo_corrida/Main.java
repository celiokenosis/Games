package jogo_corrida;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	
	public static Player player;
	public static PointCar point1;
	public static PointCar point2;
	public static int voltas = 0;
	public static int ticks = 0;
	public static int contador = 0;
	
	public static List<Bloco> blocos= new ArrayList<Bloco>();
	
		
	public Main() {
		this.setPreferredSize(new Dimension(640,480));
		this.addKeyListener(this);
		
		new Sprites();
		new World("/mapa.png");
		
		player = new Player(50,380);
		
		point1 = new PointCar(30,350,80,80);
		point2 = new PointCar(550,20,80,80);
		
		point1.point = 1;
		point2.point = 2;
		
	}
	
	public void tick() {
		ticks++;
		
		if(ticks >= 60) {
			ticks = 0;
			contador++;
		}
		player.tick();
	
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, 640, 480);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//desenhar o mapa
		for(int i = 0; i < blocos.size(); i++) {
			blocos.get(i).render(g);
		}
		
		player.render(g2);
		point1.render(g);
		point2.render(g);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("Voltas: " + Main.voltas, 10, 20);
		g.drawString("Tempos: " + Main.contador, 10, 50);
		
		bs.show();
		
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		JFrame frame = new JFrame("Jogo de Corrida");
		frame.add(main);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.requestFocus();
		
		new Thread(main).start();	
		
	}
	
	@Override
	public void run() {
		
		requestFocus();//deixa o foco na janela ao iniciar
		
		while(true) {
			tick();
			render();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
	}

}
