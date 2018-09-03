package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import Object.Mage;



public abstract class Level {
	
	protected static Random random=new Random();
	protected static int choose;
	
	protected BufferedImage backgroundTextures;
	protected int red, green, blue, pixel;
	
	public abstract void addObjectTextures(Handler handler, int xx, int yy, int red, int green, int blue);
	public abstract void removeObjectTextures(Handler handler);

	protected Level() {
	}
	
	public void loadMap(Handler handler, BufferedImage image) {
		int w=image.getWidth();
		int h=image.getHeight();
		
		Mage.health=100;
		Mage.mana=100;
		
		for(int xx=0; xx<w; ++xx) {
			for(int yy=0;yy<h;++yy) {
				pixel=image.getRGB(xx, yy);
				red =(pixel>>16) & 0xff;
				green =(pixel>>8) & 0xff;
				blue = (pixel) & 0xff;
				addObjectTextures(handler, xx, yy, red, green, blue);			
			}
		}
	}
	
	public void removeMap(Handler handler, BufferedImage image) {
		
		int w=image.getWidth();
		int h=image.getHeight();
		
		for(int xx=0; xx<w; ++xx) {
			for(int yy=0;yy<h;++yy) {
				pixel=image.getRGB(xx, yy);
				red =(pixel>>16) & 0xff;
				green =(pixel>>8) & 0xff;
				blue = (pixel) & 0xff;
				
				removeObjectTextures(handler);					
			}
		}
	}
	
	public void loadBackground(Graphics g, BufferedImage backgroundTextures) {
		g.drawImage(backgroundTextures, 0, 0, null);
	}
}
