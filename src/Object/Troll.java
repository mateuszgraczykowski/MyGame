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

public class Troll extends Object implements ObjectInterface{
	
	private int hp=1000;
	private float diffX, diffY, distance;
	Random r=new Random();
	int choose=0;
	
	private static Animation animTrollLeft, animTrollRight, animTrollUp, animTrollDown, animTrollIdle ;
	private static TexturesLoader loader=new TexturesLoader("/Idle_000.png");
	private static BufferedImage trollTexturesRight[]=new BufferedImage[10];
	private static BufferedImage trollTexturesLeft[]=new BufferedImage[10];
	private static BufferedImage trollTexturesIdle[]=new BufferedImage[10];
	
	public Troll(float x, float y, ID id) {
		super(x, y, id);
		
		//Move right
		trollTexturesRight[0]=loader.divideImage(0, 0, 370, 260);
		trollTexturesRight[1]=loader.divideImage(380, 0, 370, 260);
		trollTexturesRight[2]=loader.divideImage(750, 0, 380, 260);
		trollTexturesRight[3]=loader.divideImage(1150, 0, 370, 260);
		trollTexturesRight[4]=loader.divideImage(1480, 0, 370, 260);
		trollTexturesRight[5]=loader.divideImage(1835, 0, 360, 260);
		trollTexturesRight[6]=loader.divideImage(2198, 0, 352, 260);
		trollTexturesRight[7]=loader.divideImage(2555, 0, 360, 260);
		trollTexturesRight[8]=loader.divideImage(2925, 0, 363, 260);
		trollTexturesRight[9]=loader.divideImage(3303, 0, 370, 260);
		//Move left
		trollTexturesLeft[9]=loader.divideImage(15, 264, 380, 260);
		trollTexturesLeft[8]=loader.divideImage(400, 264, 370, 260);
		trollTexturesLeft[7]=loader.divideImage(775, 264, 380, 260);
		trollTexturesLeft[6]=loader.divideImage(1153, 264, 370, 260);
		trollTexturesLeft[5]=loader.divideImage(1505, 264, 370, 260);
		trollTexturesLeft[4]=loader.divideImage(1835, 264, 362, 260);
		trollTexturesLeft[3]=loader.divideImage(2198, 264, 355, 260);
		trollTexturesLeft[2]=loader.divideImage(2550, 264, 385, 260);
		trollTexturesLeft[1]=loader.divideImage(2940, 264, 360, 260);
		trollTexturesLeft[0]=loader.divideImage(3308, 264, 365, 260);
		
		//Move down
		//Move up
		//Idle
		trollTexturesIdle[0]=loader.divideImage(0, 527, 381, 260);
		trollTexturesIdle[1]=loader.divideImage(382, 527, 381, 260);
		trollTexturesIdle[2]=loader.divideImage(763, 527, 381, 260);
		trollTexturesIdle[3]=loader.divideImage(1144, 527, 381, 260);
		trollTexturesIdle[4]=loader.divideImage(1525, 527, 381, 260);
		trollTexturesIdle[5]=loader.divideImage(1906, 527, 381, 260);
		trollTexturesIdle[6]=loader.divideImage(2287, 527, 381, 260);
		trollTexturesIdle[7]=loader.divideImage(2668, 527, 381, 260);
		trollTexturesIdle[8]=loader.divideImage(3049, 527, 381, 260);
		trollTexturesIdle[9]=loader.divideImage(3430, 527, 381, 260);
		
		animTrollLeft=new Animation(20,	trollTexturesLeft[0], trollTexturesLeft[1], trollTexturesLeft[2],trollTexturesLeft[3], trollTexturesLeft[4], trollTexturesLeft[5], trollTexturesLeft[6], trollTexturesLeft[7], trollTexturesLeft[8], trollTexturesLeft[9] );
		animTrollRight=new Animation(20,trollTexturesRight[0], trollTexturesRight[1], trollTexturesRight[2],trollTexturesRight[3], trollTexturesRight[4], trollTexturesRight[5], trollTexturesRight[6], trollTexturesRight[7], trollTexturesRight[8], trollTexturesRight[9] );
		animTrollIdle=new Animation(20,trollTexturesIdle[0], trollTexturesIdle[1], trollTexturesIdle[2],trollTexturesIdle[3], trollTexturesIdle[4], trollTexturesIdle[5], trollTexturesIdle[6], trollTexturesIdle[7], trollTexturesIdle[8], trollTexturesIdle[9] );
		
	}

	@Override
	public void tick() {
		
		x+=velX*1;
		y+=velY*1;
		
		choose = r.nextInt(100);
		
		for(int i= 0; i<Handler.object.size();++i) {
			ObjectInterface tempObject=Handler.object.get(i);
		
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
				}
			}else if(tempObject.getId()==ID.Spell && getBounds().intersects(tempObject.getBounds())) {
				hp-=100;
				Handler.removeObject(tempObject);
			}
		}
		if(hp<=0) Handler.removeObject(this);
		
		animTrollRight.runAnimation();
		animTrollLeft.runAnimation();
		animTrollIdle.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)x, (int)y-10, 33, 5);
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y-10, hp/3, 5);
		
		if(velX>0 && velY!=0) {
			animTrollRight.drawAnimation(g, x, y);
		}
		if(velX<0 && velY!=0) {
		animTrollLeft.drawAnimation(g, x, y);
		}
		if(velY>0 && velX==0) {
			animTrollLeft.drawAnimation(g, x, y);
		}
		if(velY<0 && velX==0) {
			animTrollRight.drawAnimation(g, x, y);
		}
		if(velX==0 && velY==0){
			animTrollIdle.drawAnimation(g, x, y);
		}
		/*else
			g.drawImage(trollTexturesIdle[0], (int)x, (int)y, null);*/
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 381, 263);
	}

	private Rectangle getAgro() {
		return new Rectangle((int)x-120, (int)y-120,(int)x+ 152, (int)y+152);
	}
	
	private Rectangle getBoundsBig() {
		return new Rectangle((int)x,(int) y, 381, 263);
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
