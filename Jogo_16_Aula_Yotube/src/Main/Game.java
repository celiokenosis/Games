package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, MouseListener{

	public static final int WIDHT = 640;
	public static final int HEIGHT = 480;
	public static int contador = 10;
	public static int pontuacao = 0;
	public static int mx, my;
	
	public static boolean clicado = false;
	public boolean gameOver = false;
	
	public Spawner spawner; 
	
	
	public Game() {
		Dimension dimension = new Dimension(WIDHT,HEIGHT);
		this.setPreferredSize(dimension);
		this.addMouseListener(this);
		
		spawner = new Spawner();
		
		
	}
	
	public void update() {
		
		if(gameOver == false) {
			
		
			spawner.update();
			
			if(contador <= 0) {
				//Game Over
				contador = 100;
				gameOver = true;
			}
		}
		
	}
	
	public void render() {

		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDHT, HEIGHT);
		
		
		if(gameOver == false) {
			
			g.setColor(Color.green);
			g.fillRect(Game.WIDHT/2 - 100-70, 20, contador*3, 20);
			
			g.setColor(Color.white);
			g.drawRect(Game.WIDHT/2 - 100-70, 20, 300, 20);
			
			spawner.render(g);
		}else {
			g.setColor(Color.white);
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.drawString("Game Over", WIDTH/2, HEIGHT/2 - 50);
			g.drawString("Your Final Score Was: "+ this.pontuacao, WIDTH/2 - 200, HEIGHT/2+80-50);
		}
		
		bs.show();
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		JFrame jframe = new JFrame("Meu Jogo");
		jframe.add(game);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		
		new Thread(game).start();
		
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			update();
			
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
	public void mouseClicked(MouseEvent e) {
	
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		clicado = true;
		mx = e.getX();
		my = e.getY();	
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
