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
	private Random random=new Random();
	private int randomSide=0;
	private float startY, startX;

	public static TexturesLoader loader=new TexturesLoader("/water.png");
	public static BufferedImage waterTextures[]=new BufferedImage[2];
		
	public Water(float x, float y, ID id) {
		this.x=x;
		this.y=y;
		this.id=id;
		this.startX=x;
		this.startY=y;
		waterTextures[0]=loader.divideImage(0, 0, 8, 10);
		waterTextures[1]=loader.divideImage(20, 0, 8, 10);
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		velX=0;

		velY=0.6f;
		randomSide=random.nextInt(2);
		
		for(int i= 0; i<Handler.object.size();++i) {
			ObjectInterface tempObject=Handler.object.get(i);
			
			if(tempObject.getId()==ID.Player && getBounds().intersects(tempObject.getBounds())) {
				if(randomSide==0) {
					velX=-0.5f;
					x+=velX;
				}
				if(randomSide==1) {
					velX=0.5f;
					x+=velX;
				}
			}
			if(tempObject.getId()==ID.Water) {
				if(y>140) {
					velY=0.4f;
					if(randomSide==0) {
						velX=-0.01f;
						x+=velX;
					}
					if(randomSide==1) {
						velX=0.01f;
						x+=velX;
					}
				}
				if(y>150) {
					x=startX;
					y=startY;
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(waterTextures[0], (int)x, (int)y, null);
		if(y>140)
			g.drawImage(waterTextures[1], (int)x, (int)y, null);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 10);
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
		this.x=x;
	}

	@Override
	public void setY(float y) {
		this.y=y;
	}

	@Override
	public float getVelX() {
		return velX;
	}

	@Override
	public void setVelX(float velX) {
		this.velX=velX;
	}

	@Override
	public float getVelY() {
		return velY;
	}

	@Override
	public void setVelY(float velY) {
		this.velY=velY;
	}

	@Override
	public void setId(ID id) {
		this.id=id;
	}
}
