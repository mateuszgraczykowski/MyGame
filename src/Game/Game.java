package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;

import Object.ObjectInterface;
import Object.WaterThread;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static int WIDTH=1000;
	public static int HEIGHT=564;
	public static Status gameStatus=Status.Menu;
		
	private boolean isRunning=false;
	public 	static  Handler handler;
	private Camera camera;
	private HUD hud=new HUD();
	private UI ui=new UI();
	public WaterThread waterThread[]=new WaterThread[3];

	
	public MapOne mapOne=new MapOne();
	
	public Game() {
		
		AudioPlayer.init();			
		AudioPlayer.getMusic("music").loop( 1, 0.01f);			
		start();
		
		handler=new Handler();
		camera=new Camera(0,0);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(ui);
		
		this.addMouseListener(new MouseInput(handler, camera));
		
		mapOne.loadMap(handler);
	}
	
	private synchronized void start() {	
		if(isRunning)return;
		
		isRunning=true;
	}
	
	private synchronized void stop() {
		isRunning=false;
	}
	
	@Override 	
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
	
	//This method responds for update the game.
	private void tick() {
		waterThread[0]=new WaterThread(750, 210, ID.Water);
		waterThread[1]=new WaterThread(760, 210, ID.Water);

		waterThread[0].start();
		waterThread[1].start();
		if(Main.gameStatus==Status.Game || Main.gameStatus==Status.NewGame) {
			for(int i= 0; i<Handler.object.size();++i) {
				ObjectInterface tempObject=Handler.object.get(i);
				
				if(tempObject.getId()==ID.Player)
					camera.tick(tempObject);
			}
		Handler.tick();
		}
	}
	
	//This method responds for rendering game.
	private void render() {
		BufferStrategy bs=getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d =(Graphics2D) g;		
		
		if(Main.gameStatus==Status.Game || Main.gameStatus==Status.NewGame) {
			g2d.translate(-camera.getX(), -camera.getY());
			
			mapOne.loadBackground(g);
		
			Handler.render(g);
			g2d.translate(camera.getX(), camera.getY());
			hud.draw(g);
		}
		if(Main.gameStatus==Status.Menu || Main.gameStatus==Status.Help){
			ui.render(g);
		}
		
		bs.show();
		g.dispose();
	}
}
