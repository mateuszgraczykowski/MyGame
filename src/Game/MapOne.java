package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Object.Block;
import Object.Mage;
import Object.Object;

public class MapOne extends Level {
	
	private BufferedImage image;
	private TexturesLoader mapsLoader=null;
	private TexturesLoader textureLoader=null;	
	public BufferedImage backgroundTextures=null;
	public static int red, green, blue, pixel;
	
	protected MapOne() {
		textureLoader=new TexturesLoader("/textures.png");	
		mapsLoader=new TexturesLoader("/map3.png");
		backgroundTextures=textureLoader.divideImage(0, 0, 50,50);
		image=mapsLoader.image;
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
			handler.addObject(new Block(xx*32, yy*32, ID.Block, tl));
		}
		if(blue == 255) {
			handler.addObject(new Mage(xx*12, yy*5, ID.Player, handler, null));
		}
		if(green==255) {
			handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler, tl));
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