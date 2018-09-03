package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Object.Tree;
import Object.WaterThread;
import Object.Mage;
import Object.Enemy;

public class MapOne extends Level {
	
	
	
	private BufferedImage image=mapsLoader.image;
	private static TexturesLoader mapsLoader=new TexturesLoader("/map3.png");
	private static TexturesLoader backgroundLoader=new TexturesLoader("/backgroundMap.png");	
	private static BufferedImage backgroundTextures[]= new BufferedImage[1];
	public static int red, green, blue, pixel;
	
	public MapOne() {
		choose=random.nextInt(6);
		backgroundTextures[0]=backgroundLoader.divideImage(0, 0, 3000, 2500);
	}
	
	public void loadBackground(Graphics g) {
		super.loadBackground(g, backgroundTextures[0]);
	}
	
	public void loadMap(Handler handler) {
		super.loadMap(handler, image);		
	}
	
	public void removeMap(Handler handler) {
		super.removeMap(handler, image);		
	}
	
	@Override
	public void addObjectTextures(Handler handler, int xx, int yy, int red, int green, int blue) {
	
		if(red == 255 && green==0 && blue==0) {
			Handler.addObject(new Tree(xx*32, yy*32, ID.Block));
		}
		if(blue == 255) {
			Handler.addObject(new Mage(xx*35, yy*20, ID.Player));
		}
		if(green==255) {
			//Handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy));
		}
		/*if(red==162 || green==216) {
			Handler.addObject(new Troll(xx*32, yy*32, ID.Boss));
		}*/
		
	}

	@Override
	public void removeObjectTextures(Handler handler) {
				Handler.object.clear();
				WaterThread.waterIndex=0;
	}
}