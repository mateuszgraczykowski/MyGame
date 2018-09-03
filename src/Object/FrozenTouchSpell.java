package Object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import Game.Handler;
import Game.ID;

public class FrozenTouchSpell extends Object implements ObjectInterface{
	
	private static BufferedImage frozenWave[]=new BufferedImage[4];
	
	public FrozenTouchSpell(float x, float y, ID id) {
		super(x, y, id);
		
		frozenWave[0]=Mage.loader.divideImage(322, 0, 46, 55);
		frozenWave[1]=Mage.loader.divideImage(322, 58, 54, 55);
		frozenWave[2]=Mage.loader.divideImage(322, 110, 54, 55);
		frozenWave[3]=Mage.loader.divideImage(322, 162, 54, 55);
	}

	@Override
	public void tick() {
		if(Mage.mana>0) {
			Mage.mana-=0.005f;
		}else
			Handler.removeObject(this);
	}

	@Override
	public void render(Graphics g) {
		
		if(Handler.isDown() || (velX==0 && velY==0)) {
			g.drawImage(frozenWave[3], (int)x, (int)y, null);
		}
		if(Handler.isRight() || (Handler.isRight() && Handler.isDown()) || (Handler.isRight() && Handler.isUp()) ){
			g.drawImage(frozenWave[2], (int)x, (int)y, null);
		}
		else if(Handler.isLeft() || (Handler.isLeft() && Handler.isDown()) || (Handler.isLeft() && Handler.isUp())) {
			g.drawImage(frozenWave[1], (int)x, (int)y, null);
		}
		else if(Handler.isUp()) {
			g.drawImage(frozenWave[1], (int)x, (int)y, null);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, 54, 55);
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
