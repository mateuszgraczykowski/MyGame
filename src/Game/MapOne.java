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
	private static TexturesLoader backgroundLoader=new TexturesLoader("/textures.png");	
	private static BufferedImage backgroundTextures[]= new BufferedImage[7];
	public static int red, green, blue, pixel;
	
	protected MapOne() {
		choose=random.nextInt(6);

		backgroundTextures[0]=backgroundLoader.divideImage(54, 0, 45, 45);
		backgroundTextures[1]=backgroundLoader.divideImage(96, 0, 45, 45);
		backgroundTextures[2]=backgroundLoader.divideImage(0, 0, 45, 45);
		backgroundTextures[3]=backgroundLoader.divideImage(0, 45, 45, 45);
		backgroundTextures[4]=backgroundLoader.divideImage(0, 90, 45, 45);
		backgroundTextures[5]=backgroundLoader.divideImage(0, 135, 45, 45);
	}
	
	public void loadBackground(Graphics g) {
		super.loadBackground(g, backgroundTextures[choose]);
	}
	
	public void loadMap(Handler handler) {
		super.loadMap(handler, image);		
	}
	
	public void removeMap(Handler handler) {
		super.removeMap(handler, image);		
	}
	
	@Override
	public void addObjectTextures(Handler handler, int xx, int yy, int red, int green, int blue) {
		if(red == 255 && green==0) {
			Handler.addObject(new Tree(xx*32, yy*32, ID.Block));
		}
		if(blue == 255) {
			Handler.addObject(new Mage(xx*12, yy*5, ID.Player, handler));
		}
		if(green==255) {
			Handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy));
		}
		if(red==162 || green==216) {
			//Handler.addObject(new Troll(xx*32, yy*32, ID.Boss));
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