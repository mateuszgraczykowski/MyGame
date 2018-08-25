package Object;

import java.awt.*;
import java.awt.image.BufferedImage;

import Game.Animation;
import Game.Handler;
import Game.ID;
import Game.TexturesLoader;

public class FireballSpell extends Object {
	
	private static TexturesLoader loader=new TexturesLoader("/spellFire.png");
	private static BufferedImage spellTextures[]=new BufferedImage[13];
	private static Animation animSpell;
	
	private float diffX, diffY, distance;
	private float spellRange=250;
	private float manaCost=2;
	
	public FireballSpell(float x, float y, ID id, Handler handler, int mx, int my) {
		super(x, y, id);
		
		spellTextures[0]=loader.divideImage(2, 8, 6, 11);
		spellTextures[1]=loader.divideImage(15, 5, 11, 16);
		spellTextures[2]=loader.divideImage(28, 0, 14, 17);
		
		animSpell= new Animation(10, spellTextures[0], spellTextures[1], spellTextures[2]);
		
		Mage.mana-=manaCost;
		velX=(mx-x)/60;
		velY=(my-y)/60;		
	}
	
	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		for(int i=0; i<Handler.object.size(); ++i) {
			Object tempObject=Handler.object.get(i);
			if(tempObject.getId()==ID.Player) {				
				diffX=x-tempObject.getX();
				diffY=y-tempObject.getY();
				distance=(float) Math.sqrt(diffX*diffX+diffY*diffY);
				
				if (distance>spellRange) {
					Handler.removeObject(this);
				}
			}
			if(tempObject.getId()==ID.Block && getBounds().intersects(tempObject.getBounds())) {	
					
					Handler.removeObject(this);								
			}
		}
		animSpell.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		animSpell.drawAnimation(g, x, y);				
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 4, 4);
	}
}
