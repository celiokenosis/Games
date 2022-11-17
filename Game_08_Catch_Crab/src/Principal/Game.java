package Principal;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, MouseListener{
	
	public static int WIDTH =  480;
	public static int HEIGHT =  480;
	
	public static int score = 0;
	
	public static List<Crab> crabs;
	public static List<Smoke> smokes;
	public Spawner spawner;
	public static Spritesheet spritesheet;
	
	public static Rectangle maskBuraco;
	public static int mx,my;/*coordenadas do mouse*/
	public static boolean isPressed = false;
	
	//Engine do Game
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addMouseListener(this);
		spritesheet = new Spritesheet("/crabs.png");
		crabs = new ArrayList<>();
		smokes = new ArrayList<>();
		spawner = new Spawner();
		maskBuraco = new Rectangle(WIDTH/2 - 20, HEIGHT/2 - 20,40,40);
				
	}
	
	//Atualização
	public void update() {
		
		spawner.update();
		
		for(int i = 0; i < crabs.size(); i++) {
			crabs.get(i).update();
		}
		
		for(int i = 0; i < smokes.size(); i++) {
			smokes.get(i).update();
		}
		
	}

	//Renderização do Game
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		//Cor de fundo
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(255,229,102));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Um buraco simulando de onde sairão os caranguejos
		g.setColor(Color.black);
		g.fillOval(WIDTH/2 - 20, HEIGHT/2 - 20,40,40);
		
		//Renderedizar os crabs
		for(int i = 0; i < crabs.size(); i++) {
			crabs.get(i).render(g);
		}
		
		for(int i = 0; i < smokes.size(); i++) {
			smokes.get(i).render(g);
		}
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial",Font.BOLD,22));
		g.drawString("Score: " + score, 10, 20);
		
		g.dispose();
		bs.show();		
	}
	
	//Principal
	public static void main(String[] args) {
		
		Game game = new Game();
		
		//Janela do Game
		JFrame frame = new JFrame();
		frame.setTitle("Catch the Crab");
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		new Thread(game).start();
		
	}

	//Rodar o Game
	@Override
	public void run() {
		
		double fps = 60.0;
		
		while(true) {
			update();
			render();
			
			try {
				Thread.sleep((int)(1000/fps));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
		//recupera coordenados do mouse
		mx = e.getX();
		my = e.getY();
		isPressed = true;
		
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
