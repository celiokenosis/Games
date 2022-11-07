package inicio_logica_games;

public class Game implements Runnable{

	private boolean isRunning;
	private Thread thread;
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	public synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();/*chama o m�todo run*/
	}
	
	public void tick() {/*atualiza��o a cada tick*/
		System.out.println("tick");
	}
	
	public void render() {/*renderiza��o do game*/
		System.out.println("render");
	}

	@Override
	public void run() {
		System.out.println("Jogo come�ou...");
		while(isRunning) {/*enquanto estiver rodando o jogo*/
			tick();
			render();
		}
		
	}
}
