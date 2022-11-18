package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	public static int mx, my;
	public boolean pressed = false;
	
	public int PLAYER = 1, OPONENTE = -1, CURRENT = OPONENTE;
	
	public BufferedImage PLAYER_SPRITE, OPONENTE_SPRITE;
	
	public int[][] TABULEIRO = new int[3][3];
			
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.addKeyListener(this);
		this.addMouseListener(this);
				
		try {
			PLAYER_SPRITE = ImageIO.read(getClass().getResource("/player.png"));
			OPONENTE_SPRITE = ImageIO.read(getClass().getResource("/opositor.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resetTabuleiro();
		
	}
	
	public void resetTabuleiro() {
		for(int xx = 0; xx < TABULEIRO.length; xx++) {
			
			for(int yy = 0; yy < TABULEIRO.length; yy++) {
				TABULEIRO[xx][yy] = 0;
			}
		}
	}
	
	public int checkVictory() {
		//Verificar se Player ganhou na horizontal
		if(TABULEIRO[0][0] == PLAYER && TABULEIRO[1][0] == PLAYER && TABULEIRO[2][0] == PLAYER) {
			return PLAYER;
		}
		
		if(TABULEIRO[0][1] == PLAYER && TABULEIRO[1][1] == PLAYER && TABULEIRO[2][1] == PLAYER) {
			return PLAYER;
		}
		
		if(TABULEIRO[0][2] == PLAYER && TABULEIRO[1][2] == PLAYER && TABULEIRO[2][2] == PLAYER) {
			return PLAYER;
		}
		
		//Verificar se Player ganhou na vertical
		if(TABULEIRO[0][0] == PLAYER && TABULEIRO[0][1] == PLAYER && TABULEIRO[0][2] == PLAYER) {
			return PLAYER;
		}
		
		if(TABULEIRO[1][0] == PLAYER && TABULEIRO[1][1] == PLAYER && TABULEIRO[1][2] == PLAYER) {
			return PLAYER;
		}
		
		if(TABULEIRO[2][0] == PLAYER && TABULEIRO[2][1] == PLAYER && TABULEIRO[2][2] == PLAYER) {
			return PLAYER;
		}
		
		//Verificar se Player ganhou na diagonal
		if(TABULEIRO[0][0] == PLAYER && TABULEIRO[1][1] == PLAYER && TABULEIRO[2][2] == PLAYER) {
			return PLAYER;
		}
		
		if(TABULEIRO[2][0] == PLAYER && TABULEIRO[1][1] == PLAYER && TABULEIRO[0][2] == PLAYER) {
			return PLAYER;
		}
		
		// Verifica vitoria do oponente
		
		//Verificar se Player ganhou na horizontal
				if(TABULEIRO[0][0] == OPONENTE && TABULEIRO[1][0] == OPONENTE && TABULEIRO[2][0] == OPONENTE) {
					return OPONENTE;
				}
				
				if(TABULEIRO[0][1] == OPONENTE && TABULEIRO[1][1] == OPONENTE && TABULEIRO[2][1] == OPONENTE) {
					return OPONENTE;
				}
				
				if(TABULEIRO[0][2] == OPONENTE && TABULEIRO[1][2] == OPONENTE && TABULEIRO[2][2] == OPONENTE) {
					return OPONENTE;
				}
				
				//Verificar se Player ganhou na vertical
				if(TABULEIRO[0][0] == OPONENTE && TABULEIRO[0][1] == OPONENTE && TABULEIRO[0][2] == OPONENTE) {
					return OPONENTE;
				}
				
				if(TABULEIRO[1][0] == OPONENTE && TABULEIRO[1][1] == OPONENTE && TABULEIRO[1][2] == OPONENTE) {
					return OPONENTE;
				}
				
				if(TABULEIRO[2][0] == OPONENTE && TABULEIRO[2][1] == OPONENTE && TABULEIRO[2][2] == OPONENTE) {
					return OPONENTE;
				}
				
				//Verificar se Player ganhou na diagonal
				if(TABULEIRO[0][0] == OPONENTE && TABULEIRO[1][1] == OPONENTE && TABULEIRO[2][2] == OPONENTE) {
					return OPONENTE;
				}
				
				if(TABULEIRO[2][0] == OPONENTE && TABULEIRO[1][1] == OPONENTE && TABULEIRO[0][2] == OPONENTE) {
					return OPONENTE;
				}
		
		int curScore = 0;
		
		//Empate
		for(int xx = 0; xx < TABULEIRO.length; xx++) {
			for(int yy = 0; yy < TABULEIRO.length; yy++) {
				
				if(TABULEIRO[xx][yy] != 0) {
					curScore++;
				}
			}
			
		}
		
		if(curScore == TABULEIRO.length * TABULEIRO[0].length) {
			return 0;
		}
		
		//Ningu�m ganhou
		return -10;
	}
	
	public void tick() {
		
		if(CURRENT == PLAYER) {
			
			if(pressed) {
				pressed = false;
				mx /=100;
				my /=100;
				
				if(TABULEIRO[mx][my] == 0) {
					TABULEIRO[mx][my] = PLAYER;
					CURRENT = OPONENTE;
				}
			}
			
		}else if(CURRENT == OPONENTE) {
			//Inteligencia artificial do Oponente
			for(int xx = 0; xx < TABULEIRO.length; xx++) {
				
				for(int yy = 0; yy < TABULEIRO.length; yy++) {
					
					//checa se a posi��o est� livre para jogar
					if(TABULEIRO[xx][yy] == 0) {
						Node bestMove = getBestMove(xx,yy,0,OPONENTE);
						TABULEIRO[bestMove.x][bestMove.y] = OPONENTE;
						CURRENT = PLAYER;
						
						return;
					}
				}
			}
			

			
		}
		
		if(checkVictory() == PLAYER) {
			System.out.println("PLAYER ganhou!!!");

		}else if(checkVictory() == OPONENTE) {
			System.out.println("OPONENTE ganhou!!!");

		}else if(checkVictory() == 0) {
			System.out.println("EMPATE!!!");

		}
	}
	
	public Node getBestMove(int x, int y, int depth, int turno) {
		//Jogo termina aqui
		if(checkVictory() == PLAYER) {
			return new Node(x,y,depth-10,depth);
			
		}else if(checkVictory() == OPONENTE) {
			return new Node(x,y,10-depth,depth);
			
		}else if(checkVictory() == 0) {//empate
			return new Node(x,y,0,depth);
		}
		
		//Lista para armazenar as posi��es
		List<Node> nodes = new ArrayList<Node>();
		
		for(int xx = 0; xx < TABULEIRO.length; xx++) {
			for(int yy =0; yy < TABULEIRO.length; yy++) {
				
			
				
				if(TABULEIRO[xx][yy] == 0) {//livre e pode jogar
					
					Node node;
					
					if(turno == PLAYER) {
						TABULEIRO[xx][yy] = PLAYER;
						node = getBestMove(xx, yy, depth+1, OPONENTE);
						TABULEIRO[xx][yy] = 0;
						
					}else {
						TABULEIRO[xx][yy] = OPONENTE;
						node = getBestMove(xx, yy, depth+1, PLAYER);
						TABULEIRO[xx][yy] = 0;
					}
					
					nodes.add(node);
				}
			}
		}
		
		Node finalNode = nodes.get(0);
		
		for(int i = 0; i < nodes.size(); i++) {
			
			Node n = nodes.get(i);
			
			if(turno == PLAYER) {
				
				if(n.score > finalNode.score) {
					finalNode = n;
				}
				
			}else if(turno == OPONENTE) {
				if(n.score < finalNode.score) {
					finalNode = n;
				}
			}
		}
		
		return finalNode;
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Renderizar tabuleiro
		for(int xx = 0; xx < TABULEIRO.length; xx++) {
			
			for(int yy = 0; yy < TABULEIRO.length; yy++) {
				g.setColor(Color.black);
				g.drawRect(xx*100, yy*100, 100, 100);
				
				if(TABULEIRO[xx][yy] == PLAYER) {
					g.drawImage(PLAYER_SPRITE, xx*100+25, yy*100+25,50,50,null);
					
				}else if(TABULEIRO[xx][yy] == OPONENTE) {
					g.drawImage(OPONENTE_SPRITE, xx*100+25, yy*100+25,50,50,null);
				}
			}
		}
		
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String args[]) {

		Game game = new Game();

		JFrame frame = new JFrame("Tic Tac Toe");

		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(game).start();

	}

	@Override
	public void run() {
		
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
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		pressed = true;
		
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