package Object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.Animation;
import Game.Handler;
import Game.ID;
import Game.Main;
import Game.Status;
import Game.TexturesLoader;

public class Mage extends Object implements ObjectInterface{
	
	public static TexturesLoader  loader=new TexturesLoader("/wizard.png");
	private static BufferedImage mageTexturesDown[]=new BufferedImage[4];
	private static BufferedImage mageTexturesUp[]=new BufferedImage[4];
	private static BufferedImage mageTexturesLeft[]=new BufferedImage[4];
	private static BufferedImage mageTexturesRight[]=new BufferedImage[4];
	
	private static Animation animUp, animLeft, animRight, animDown;
	
	public static float health=100;
	public static float mana=100;
	private float  speedChange=(float) 1.5;

	public Mage(float x, float y, ID id) {
		super(x, y, id);
		
		//moveUp
		mageTexturesUp[0]=loader.divideImage(155, 0, 36, 55);
		mageTexturesUp[1]=loader.divideImage(191, 0, 36, 55);
		mageTexturesUp[2]=loader.divideImage(227, 0, 36, 55);
		mageTexturesUp[3]=loader.divideImage(264, 0, 36, 55);
		//moveLeft
		mageTexturesLeft[0]=loader.divideImage(148, 58, 37, 54);
		mageTexturesLeft[1]=loader.divideImage(186, 58, 37, 54);
		mageTexturesLeft[2]=loader.divideImage(223, 58, 37, 54);
		mageTexturesLeft[3]=loader.divideImage(261, 58, 37, 54);
		//moveRight
		mageTexturesRight[0]=loader.divideImage(148, 110, 35, 53);
		mageTexturesRight[1]=loader.divideImage(186, 110, 35, 53);
		mageTexturesRight[2]=loader.divideImage(223, 110, 35, 53);
		mageTexturesRight[3]=loader.divideImage(261, 110, 35, 53);
		//moveDown
		mageTexturesDown[0]=loader.divideImage(0, 0, 35, 55);
		mageTexturesDown[1]=loader.divideImage(37, 0, 35, 55);
		mageTexturesDown[2]=loader.divideImage(74, 0, 35, 55);
		//spellState
		mageTexturesDown[3]=loader.divideImage(111, 0, 35, 55);
		
		
		
		animUp=new Animation(3,mageTexturesUp[0], mageTexturesUp[1], mageTexturesUp[2], mageTexturesUp[3]);
		animLeft=new Animation(3,mageTexturesLeft[0], mageTexturesLeft[1], mageTexturesLeft[2], mageTexturesLeft[3]);
		animRight=new Animation(3, mageTexturesRight[0], mageTexturesRight[1], mageTexturesRight[2], mageTexturesRight[3]);
		animDown=new Animation(3,mageTexturesDown[0], mageTexturesDown[1], mageTexturesDown[0], mageTexturesDown[2]);
		//animSpell=new Animation(3,mageTexturesDown[3], mageTexturesDown[0]);		
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		
		collision();
		
		if(Handler.isUp()) velY=-speedChange;
		else if(!Handler.isDown()) velY=0;
		
		if(Handler.isDown()) velY=speedChange;
		else if(!Handler.isUp()) velY=0;
		
		if(Handler.isRight()) velX=speedChange;
		else if(!Handler.isLeft()) velX=0;
		
		if(Handler.isLeft()) velX=-speedChange;
		else if(!Handler.isRight()) velX=0;
		
		animUp.runAnimation();
		animLeft.runAnimation();
		animRight.runAnimation();
		animDown.runAnimation();
		//animSpell.runAnimation();
	}
	
	private void collision() {
		speedChange=1.5f;
		
		for(int i= 0; i<Handler.object.size();++i) {
			ObjectInterface tempObject=Handler.object.get(i);
			
			if(tempObject.getId()==ID.Block && getBounds().intersects(tempObject.getBounds())==true) {
					x+=velX*-1;
					y+=velY*-1;
			}
			else if(tempObject.getId()==ID.Enemy && getBounds().intersects(tempObject.getBounds())) {
				if(health==0) {
					Handler.removeObject(this);
					Main.gameStatus=Status.Menu;
				}else {
					health-=1;
				}
			}
			else if(tempObject.getId()==ID.Water && getBounds().intersects(tempObject.getBounds())) {
					speedChange=0.5f;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		
		if(velX==0 && velY==0) {
			g.drawImage(mageTexturesDown[0], (int)x, (int)y, null);
		}
		if(Handler.isRight() || (Handler.isRight() && Handler.isDown()) || (Handler.isRight() && Handler.isUp()) ){
			animRight.drawAnimation(g, x, y);
		}
		else if(Handler.isLeft() || (Handler.isLeft() && Handler.isDown()) || (Handler.isLeft() && Handler.isUp())) {
			animLeft.drawAnimation(g, x, y);
		}
		else if(Handler.isDown()) {
			animDown.drawAnimation(g, x, y);
		}
		else if(Handler.isUp()) {
			animUp.drawAnimation(g, x, y);
		}
	}
	
	@Override
	public Rectangle getBounds() {		
		return new Rectangle((int)x-10,(int)y+5, 33, 50);
	}

	@Override
	public float getOX() {
		return x;
	}

	@Override
	public float getOY() {
		return y;
	}
}
