package tetris;

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

public class Main extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	
	public static List<Bloco> blocos = new ArrayList<Bloco>();

	public static Player player;
	
	public Main() {
		this.setPreferredSize(new Dimension(480,480));
		this.addKeyListener(this);
		
		new Shapes();
		
		player = new Player(0,0);
		
	}
	
	public void tick() {
		player.tick();
		
		for(int i = 0; i < blocos.size(); i++) {
			blocos.get(i).tick();
		}
			
		for(int i = 0; i < blocos.size(); i++) {
			
			if(blocos.get(i).y == 0 && blocos.get(i).colisao == true) {
				System.out.println("Game Over");
				System.exit(i);
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
		g.fillRect(0, 0, 480, 480);
		
		player.render(g);
				
		for(int i = 0; i < blocos.size(); i++) {
			blocos.get(i).render(g);
		}
		bs.show();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		JFrame frame = new JFrame("Tetris");
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
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.changeShape = true;
		}
	}

}
