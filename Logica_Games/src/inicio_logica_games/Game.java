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
		thread = new Thread(this);/*this é a classe que tem a interface Runnable*/
		thread.start();
	}

	//Atualizar o jogo
	public void tick() {
		System.out.println("Tick");
	}
	
	//Renderizar o jogo
	public void render() {
		System.out.println("Render");
	}
	
	//rodar o game
	@Override
	public void run() {
	
		System.out.println("Jogo começou...passo 1");
		
		while(isRunning) {
			tick();
			render();
		}
		
	}
}

