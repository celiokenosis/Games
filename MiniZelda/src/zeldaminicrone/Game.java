package zeldaminicrone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	//Variáveis globais
	public static int WIDTH = 640, HEIGHT = 640;//Tamanho da janela do game
	public static int SCALE = 3;
	public static Player player;
	public static World world;
	
	public List<Inimigo> inimigos = new ArrayList<Inimigo>();
	
	public Game() {
		this.addKeyListener(this);/*adiciona eventos de teclado*/
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		new Spritesheet();
		
		world = new World();/*renderiza todo o mundo*/
		player = new Player(32,32);
		
		inimigos.add(new Inimigo(32,32));
		inimigos.add(new Inimigo(32,64));
		
	}
	
	//Responsável pela Movimentação do play, colisões, etc.
	public void tick() {
		player.tick();
		
		//vários inimigos
		for(int i = 0; i < inimigos.size(); i++) {
			inimigos.get(i).tick();
		}
	}
	
	//Renderização dos gráficos
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);/*otimização da parte gráfica*/
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(0,135,13));
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		player.render(g);
		
		//renderiza os inimigos
		for(int i = 0; i < inimigos.size(); i++) {
			inimigos.get(i).render(g);
		}
		
		world.render(g);
		
		bs.show();
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		JFrame frame = new JFrame();//Janela
		
		frame.add(game);
		frame.setTitle("Mini Zelda em Java");
		frame.pack();
		frame.setLocationRelativeTo(null);//Deixa centralizada a janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//para não ficar memória presa ao encerrar
		frame.setVisible(true);//mostra a janela
		
		//deixar o game rodando - procura o método run na classe
		new Thread(game).start();
	}
	
	@Override
	public void run() {
		//Método principal que roda o jogo
		while(true) {
			tick();
			render();
			
			try {
				Thread.sleep(1000/60);
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
		
		//Tiro
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			player.shoot = true;
		}
		
		//Movimentação
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
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
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
	}

	
}
