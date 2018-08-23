package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;

import Object.Object;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static int WIDTH=1000;
	public static int HEIGHT=564;
	public static Status gameStatus=Status.Menu;
	
	private Thread thread;
	private boolean isRunning=false;
	public 	static  Handler handler;
	private Camera camera;
	private HUD hud=new HUD();
	private UI ui=new UI();
	
	
	public MapOne mapOne=new MapOne();
	
	
	public Game() {
		AudioPlayer.init();			
		AudioPlayer.getMusic("music").loop( 1, 0.01f);			
		new Window(WIDTH, HEIGHT, "First Game", this);
		//mapOne=new MapOne();
		//mapOne=new MapOne();
		
		start();
		
		handler=new Handler();
		camera=new Camera(0,0);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(ui);
		
		this.addMouseListener(new MouseInput(handler, camera));
	
		
		mapOne.loadMap(handler, null);
		
	}
	
	private synchronized void start() {	
		isRunning=true;
		thread=new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		isRunning=false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override 	//This method creates a loop for game.
	public void run() {
		this.requestFocus();
		long lastTime=System.nanoTime();
		double amountOfTicks=60.0;
		double ns=1000000000/amountOfTicks;
		double delta=0;
		long timer = System.currentTimeMillis();
		int frames=0;
		
		while(isRunning) {
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			
			while(delta>=1) {
				tick();
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				frames=0;
			}
		}
		stop();				 
	}
	
	private void tick() {
		//This method responds for update the game.
		if(gameStatus==Status.Game || gameStatus==Status.NewGame) {
			for(int i= 0; i<handler.object.size();++i) {
				Object tempObject=handler.object.get(i);
				
				if(tempObject.getId()==ID.Player)
					camera.tick(tempObject);
			}
			
		handler.tick();
		}
	}
	
	private void render() {
		//This method responds for rendering game.
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d =(Graphics2D) g;		
		
		if(gameStatus==Status.Game || gameStatus==Status.NewGame) {
			g2d.translate(-camera.getX(), -camera.getY());
			
			mapOne.loadBackground(g);
		
			handler.render(g);
			g2d.translate(camera.getX(), camera.getY());
			hud.draw(g);
		}
		if(gameStatus==Status.Menu || gameStatus==Status.Help){
			ui.render(g);
		}
		
		bs.show();
		g.dispose();
	}
	
	public static void main(String[] args) {
		new Game();	
	}
}
