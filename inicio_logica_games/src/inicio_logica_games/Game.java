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
		thread.start();/*chama o método run*/
	}
	
	public void tick() {/*atualização a cada tick*/
		System.out.println("tick");
	}
	
	public void render() {/*renderização do game*/
		System.out.println("render");
	}

	@Override
	public void run() {
		System.out.println("Jogo começou...");
		while(isRunning) {/*enquanto estiver rodando o jogo*/
			tick();
			render();
		}
		
	}
}
