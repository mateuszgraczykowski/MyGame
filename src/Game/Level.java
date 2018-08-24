package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Object.Mage;



public abstract class Level {
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
		
		for(int xx=0;xx<30*72; xx+=32) {
			for(int yy=0; yy<30*72; yy+=32) {
				g.drawImage(backgroundTextures, xx, yy, null);
			}
		}
	}

}
