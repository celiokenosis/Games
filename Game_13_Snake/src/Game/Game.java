/*
 * Author: Celio Mendes Pereira
 * Treinamento: Desenvolvimento de Games
 * Danki Code
 */

package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public Node[] nodeSnake = new Node[5];//tamanho da cobra

	public boolean left = false;
	public boolean right = true;
	public boolean up = false;
	public boolean down = false;
	
	public int WIDTH = 480;
	public int HEIGHT = 480;
	public int score = 0;
	public int macaX = new Random().nextInt(WIDTH - 10);
	public int macaY = new Random().nextInt(HEIGHT - 10);
	public int spd = 10;
	public int frameSpd = 5;

	
	public Game() {

		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		for(int i = 0; i < nodeSnake.length; i++) {
			nodeSnake[i] = new Node(0,0);
		}
		
		this.addKeyListener(this);

	}

	public void tick() {
		
		/*l�gica do movimento*/
		for(int i = nodeSnake.length -1; i > 0; i--) {
			nodeSnake[i].x = nodeSnake[i-1].x;
			nodeSnake[i].y = nodeSnake[i-1].y;
		}
		
		/*sistema de sair da tela e retornar do outro lado*/
		if(nodeSnake[0].x + 10 < 0) {
			nodeSnake[0].x = WIDTH;
		}else if(nodeSnake[0].x >= WIDTH) {
			nodeSnake[0].x = -10;
		}
		
		if(nodeSnake[0].y + 10 < 0) {
			nodeSnake[0].y = HEIGHT;
		}else if(nodeSnake[0].y >= HEIGHT) {
			nodeSnake[0].y = -10;
		}
		
		//Controles do movimento da cobra
		if(right) {
			nodeSnake[0].x+=spd;
			collision();
		}else if(up) {
			nodeSnake[0].y-=spd;
			collision();
		}else if(down) {
			nodeSnake[0].y+=spd;
			collision();
		}else if(left) {
			nodeSnake[0].x-=spd;
			collision();
		}
		
		
		//Checa colis�o e pontua
		if(new Rectangle(nodeSnake[0].x,nodeSnake[0].y,10,10).intersects(new Rectangle(macaX, macaY,10,10))) {
			macaX = new Random().nextInt(WIDTH-10);
			macaY = new Random().nextInt(HEIGHT-10);
			score++;
			frameSpd++;
			System.out.println("Pontos: " + score);
		}
		
	}

	public void collision() {
			for(int i = 0; i < nodeSnake.length; i++) {
				
				if(i == 0) continue;//cabeca colidindo com a cabe�a n�o interessa por isso pula
				
				Rectangle box1 = new Rectangle(nodeSnake[0].x, nodeSnake[0].y,10,10);
				Rectangle box2 = new Rectangle(nodeSnake[i].x, nodeSnake[i].y,10,10);
				
				if(box1.intersects(box2)) {
					System.out.println("Game Over");
					frameSpd = 20;
					score = 0;
					right = false;
					up = false;
					left = false;
					down = false;
					
					//reinicia a snake para posi��o inicial
					for(int o = 0; o < nodeSnake.length; o++) {
						nodeSnake[o] = new Node(0,0);
					}
				}
			}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for(int i = 0; i < nodeSnake.length; i++) {
			g.setColor(Color.blue);
			g.fillRect(nodeSnake[i].x, nodeSnake[i].y, 10, 10);
		}

		g.setColor(Color.red);
		g.fillRect(macaX, macaY, 10, 10);

		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {
		
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.setTitle("Snake");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		new Thread(game).start();
	}
	
	@Override
	public void run() {

		while (true) {
			tick();
			render();
			try {
				Thread.sleep(1000 / frameSpd);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
			left = false;
			up = false;
			down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			right = false;
			left = true;
			up = false;
			down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			right = false;
			left = false;
			up = true;
			down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			right = false;
			left = false;
			up = false;
			down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
