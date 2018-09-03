package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.Animation;
import Game.Handler;
import Game.ID;
import Game.TexturesLoader;

public class Enemy extends Object implements ObjectInterface {
	
	Random r=new Random();
	int choose=0;
	int helthOrc=100;
	
	
	private static TexturesLoader loader=new TexturesLoader("/orc.png");
	private BufferedImage orcTexturesDown[]=new BufferedImage[8];
	//private BufferedImage orcTexturesUp[]=new BufferedImage[8];
	//private BufferedImage orcTexturesRight[]=new BufferedImage[8];
	//private BufferedImage orcTexturesLeft[]=new BufferedImage[8];
	
	Animation animUp, animDown, animLeft, animRight;
		
	private float diffX, diffY, distance;
	
	public Enemy(float x, float y, ID id) {
		super(x, y, id);
		
		//moveDown
		orcTexturesDown[0]=loader.divideImage(0, 78, 30, 35);
		orcTexturesDown[1]=loader.divideImage(35, 78, 30, 35);
		orcTexturesDown[2]=loader.divideImage(65, 78, 30, 35);
		
		
		animDown=new Animation(5, orcTexturesDown[0], orcTexturesDown[1], orcTexturesDown[2]);
	}

	@Override
	public void tick() {
		collision();
		if(helthOrc<=0) Handler.removeObject(this);
		
		animDown.runAnimation();
	}
	
	private void collision() {
		x+=velX*1;
		y+=velY*1;
		
		choose = r.nextInt(100);
		
		for(int i= 0; i<Handler.object.size();++i) {
			ObjectInterface tempObject=Handler.object.get(i);
			
			
			if(tempObject.getId()==ID.Spell && tempObject.getBounds().intersects(getBounds())) {
				helthOrc-=10;
			}
			
			if(tempObject.getId()==ID.Player && getAgro().intersects(tempObject.getBounds())) {
				
				diffX=x-tempObject.getOX();
				diffY=y-tempObject.getOY();
				distance=(float) Math.sqrt(diffX*diffX+diffY*diffY);
				
				velX=(float) ((-1.0/distance)*diffX)*2;
				velY=(float) ((-1.0/distance)*diffY)*2;
			}if(tempObject.getId()==ID.Block) {
				if(getBoundsBig().intersects(tempObject.getBounds())) {
					x+=-(velX*5);
					y+=-(velY*5);
					velX*=-1;
					velY*=-1;
				}else if(choose==0) {
					velX=(r.nextInt(2));
					velY=(r.nextInt(2));
				}else if(choose==1) {
					velX=-(r.nextInt(2));
					velY=-(r.nextInt(2));
				}
			}else if(tempObject.getId()==ID.Spell && getBounds().intersects(tempObject.getBounds())) {
				helthOrc-=20;
				Handler.removeObject(tempObject);
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)x, (int)y-10, 33, 5);
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y-10, helthOrc/3, 5);
		
		if(velX==0 && velY==0) {
			g.drawImage(orcTexturesDown[0], (int)x, (int)y, null);
		}else
			animDown.drawAnimation(g, x, y);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x-3, (int)y-3, 31, 31);
	}
	
	public Rectangle getBoundsBig() {
		return new Rectangle((int)x-8,(int) y-8, 31, 36);
	}
	
	public Rectangle getAgro() {
		return new Rectangle((int)x-150, (int)y-150,(int)x+ 202, (int)y+202);
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
