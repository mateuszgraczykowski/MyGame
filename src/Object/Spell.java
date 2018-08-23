package Object;

import java.awt.*;
import java.awt.image.BufferedImage;

import Game.Animation;
import Game.Handler;
import Game.ID;
import Game.TexturesLoader;

public class Spell extends Object {
	
	private static TexturesLoader loader=new TexturesLoader("/spellFire.png");
	private static BufferedImage spellTextures[]=new BufferedImage[13];
	private static Animation animSpell, animCollision;
	
	private Handler handler;
	private float diffX, diffY, distance;
	private float spellRange=250;
	private float manaCost=1;
	
	public Spell(float x, float y, ID id, Handler handler, int mx, int my, TexturesLoader tl) {
		super(x, y, id, tl);
		this.handler=handler;
		
		spellTextures[0]=loader.divideImage(2, 8, 6, 11);
		spellTextures[1]=loader.divideImage(15, 5, 11, 16);
		spellTextures[2]=loader.divideImage(28, 0, 14, 17);
		spellTextures[3]=loader.divideImage(44, 0, 20, 30);
		spellTextures[4]=loader.divideImage(65, 0, 26, 30);
		spellTextures[5]=loader.divideImage(92, 0, 26, 30);
		spellTextures[6]=loader.divideImage(123, 0, 22, 30);
		spellTextures[7]=loader.divideImage(156, 0, 32, 30);
		spellTextures[8]=loader.divideImage(190, 0, 32, 30);
		spellTextures[9]=loader.divideImage(224, 0, 33, 30);
		spellTextures[10]=loader.divideImage(259, 0, 35, 30);
		spellTextures[11]=loader.divideImage(295, 0, 36, 30);
		spellTextures[12]=loader.divideImage(332, 0, 37, 30);
		animSpell= new Animation(10, spellTextures[0], spellTextures[1], spellTextures[2]);
		//animCollision= new Animation(3, spellTextures[3], spellTextures[4], spellTextures[5], spellTextures[6], spellTextures[7], spellTextures[8], spellTextures[9], spellTextures[10], spellTextures[11], spellTextures[12]);
		
		Mage.mana-=manaCost;
		velX=(mx-x)/60;
		velY=(my-y)/60;		
	}
	
	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		for(int i=0; i<handler.object.size(); ++i) {
			Object tempObject=handler.object.get(i);
			if(tempObject.getId()==ID.Player) {				
				diffX=x-tempObject.getX();
				diffY=y-tempObject.getY();
				distance=(float) Math.sqrt(diffX*diffX+diffY*diffY);
				
				if (distance>spellRange) {
					handler.removeObject(this);
				}
			}
			if(tempObject.getId()==ID.Block && getBounds().intersects(tempObject.getBounds())) {	
					
					handler.removeObject(this);								
			}
		}
		animSpell.runAnimation();
		//animCollision.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		animSpell.drawAnimation(g, x, y);				
		//animCollision.drawAnimation(g, x, y);	
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 4, 4);
	}
}
