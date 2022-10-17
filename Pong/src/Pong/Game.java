package Pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	//Constantes do game
	public static int WIDTH = 160;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	//Jogador e inimigo
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(100, HEIGHT-5);/*posicão onde vai começar o jogador*/
		enemy = new Enemy(100, 0);/*posicão onde vai começar o jogador*/
		ball = new Ball(100,HEIGHT/2 -1);/*cria a bola no centro*/
		
	}
	
	//Cria a janela do game
	public static void main(String[] args) {
		
		Game game = new Game();
		
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);/*não deixa aumentar a janela do game*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*encerra operações ao fechar. Otimiza memória*/
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);/*centraliza a janela*/
		frame.setVisible(true);/*mostra a janela*/
		
		new Thread(game).start();
		
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();/*onde consegue renderizar o game*/
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0,0,WIDTH*SCALE, HEIGHT*SCALE,null);
		
		bs.show();/*mostra tudo o que foi renderizado*/
		
	}
	
	@Override
	public void run() {
		//game looping
		while(true) {
			tick();
			render();
			
			try {
				Thread.sleep(1000/60);/*para rodar a 60 FPS*/
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
		//Quando pressiona a tecla
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Quando solta a tecla
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}	
	}
}
