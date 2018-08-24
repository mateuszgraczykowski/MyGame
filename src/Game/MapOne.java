package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Object.Tree;
import Object.Mage;
import Object.Object;
import Object.Enemy;

public class MapOne extends Level {
	
	private BufferedImage image=mapsLoader.image;
	private static TexturesLoader mapsLoader=new TexturesLoader("/map3.png");
	private static TexturesLoader textureLoader=new TexturesLoader("/textures.png");	
	private static BufferedImage backgroundTextures=textureLoader.divideImage(0, 0, 50,50);
	public static int red, green, blue, pixel;
	
	protected MapOne() {
		
	}
	
	public void loadBackground(Graphics g) {
		super.loadBackground(g, backgroundTextures);
	}
	
	public void loadMap(Handler handler) {
		super.loadMap(handler, image);		
	}
	
	public void removeMap(Handler handler) {
		super.removeMap(handler, image);		
	}
	


	@Override
	public void addObjectTextures(Handler handler, int xx, int yy, int red, int green, int blue) {
		if(red == 255) {
			Handler.addObject(new Tree(xx*32, yy*32, ID.Block));
		}
		if(blue == 255) {
			Handler.addObject(new Mage(xx*12, yy*5, ID.Player, handler));
		}
		if(green==255) {
			Handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler));
		}
	}

	@Override
	public void removeObjectTextures(Handler handler) {
		
		for(int i= 0; i<Handler.object.size();++i) {
			Object tempObject=Handler.object.get(i);		
		
			Handler.removeObject(tempObject);			
		}
	}
}