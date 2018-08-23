package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Object.Block;
import Object.Mage;
import Object.Object;

public class MapOne extends Level {
	
	//private static BufferedImage image=TexturesContainer.getMap("firstMap");
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
	
	public void loadMap(Handler handler, TexturesLoader tl) {
		super.loadMap(handler, tl, image);		
	}
	
	public void removeMap(Handler handler) {
		super.removeMap(handler, image);		
	}
	


	@Override
	public void addObjectTextures(Handler handler, TexturesLoader tl, int xx, int yy, int red, int green, int blue) {
		if(red == 255) {
			handler.addObject(new Block(xx*32, yy*32, ID.Block, null));
		}
		if(blue == 255) {
			handler.addObject(new Mage(xx*12, yy*5, ID.Player, handler, null));
		}
		if(green==255) {
			handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler, null));
		}
	}

	@Override
	public void removeObjectTextures(Handler handler) {
		
		for(int i= 0; i<handler.object.size();++i) {
			Object tempObject=handler.object.get(i);		
		
			handler.removeObject(tempObject);			
		}
	}
}