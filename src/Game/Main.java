package Game;

import ThreadManager.ThreadPool;

public class Main {
	public static int WIDTH=1000;
	public static int HEIGHT=564;
	public static Status gameStatus=Status.Menu;
	
	public static void main(String[] args) {
		ThreadPool threadPool=new ThreadPool(2);
		final Game game=new Game();
		new Window(WIDTH, HEIGHT, "Game", game);
		threadPool.runTask(game);
	}
}
