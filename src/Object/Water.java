package Object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.Handler;
import Game.ID;
import Game.TexturesLoader;

public class Water implements ObjectInterface{
	public float x,y;
	public float velX, velY;
	public ID id;
	private float diffX, diffY, distance;
	private Random random=new Random();
	private int randomSide=0;

	public static TexturesLoader loader=new TexturesLoader("/water.png");
	public static BufferedImage waterTextures=loader.divideImage(0, 0, 10, 10);
		
	public Water(float x, float y, ID id) {
		this.x=x;
		this.y=y;
		this.id=id;
	}
	
	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		//velX=0;
		velY=0.7f;
		randomSide=random.nextInt(2);
		for(int i= 0; i<Handler.object.size();++i) {
			ObjectInterface tempObject=Handler.object.get(i);
			diffX=x-tempObject.getOX();
			diffY=y-tempObject.getOY();
			distance=(float) Math.sqrt(diffX*diffX+diffY*diffY);
			
			if(tempObject.getId()==ID.Player && getBounds().intersects(tempObject.getBounds())) {
				if(randomSide==0) {
					velX=-0.7f;
					x+=velX;
				}
				if(randomSide==1) {
					velX=0.7f;
					x+=velX;
					
				}
			}
			
			if(tempObject.getId()==ID.Water) {
				if(distance>130) {
					Handler.removeObject(this);
				}
				if(distance>80 && distance<150) {
					velX=0;
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(waterTextures, (int)x, (int)y, null);
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 10, 10);
	}
	
	
	
	@Override
	public ID getId() {
		return id;
	}

	@Override
	public float getOX() {
		return x;
	}

	@Override
	public float getOY() {
		return y;
	}


	@Override
	public void setX(float x) {
		
	}

	@Override
	public void setY(float y) {
		
	}

	@Override
	public float getVelX() {
		return velX;
	}

	@Override
	public void setVelX(float velX) {
		
	}

	@Override
	public float getVelY() {
		return velY;
	}

	@Override
	public void setVelY(float velY) {
		
	}

	@Override
	public void setId(ID id) {
	}
	
}
