package Object;

import java.awt.*;

import Game.Handler;
import Game.ID;
import Game.TexturesLoader;

public class Spell extends Object {
	
	private Handler handler;
	private float diffX, diffY, distance;
	private float spellRange=250;
	private float manaCost=1;
	
	public Spell(float x, float y, ID id, Handler handler, int mx, int my, TexturesLoader tl) {
		super(x, y, id, tl);
		this.handler=handler;
		
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
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 4, 4);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 4, 4);
	}
}
