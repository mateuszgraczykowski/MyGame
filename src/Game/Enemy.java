package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Object.Object;

public class Enemy extends Object {
	
	private Handler handler;
	Random r=new Random();
	int choose=0;
	int hp=100;
	
	private TexturesLoader loader=new TexturesLoader("/orc.png");
	private BufferedImage orcTexturesDown[]=new BufferedImage[8];
	private BufferedImage orcTexturesUp[]=new BufferedImage[8];
	private BufferedImage orcTexturesRight[]=new BufferedImage[8];
	private BufferedImage orcTexturesLeft[]=new BufferedImage[8];
	
	Animation animUp, animDown, animLeft, animRight;
		
	private float diffX, diffY, distance;
	
	public Enemy(float x, float y, ID id, Handler handler, TexturesLoader tl) {
		super(x, y, id, tl);
		this.handler=handler;
		
		//moveDown
		orcTexturesDown[0]=loader.divideImage(0, 78, 30, 35);
		orcTexturesDown[1]=loader.divideImage(35, 78, 30, 35);
		orcTexturesDown[2]=loader.divideImage(65, 78, 30, 35);
		
		
		animDown=new Animation(5, orcTexturesDown[0], orcTexturesDown[1], orcTexturesDown[2]);
	}

	@Override
	public void tick() {
		x+=velX*1;
		y+=velY*1;
		
		choose = r.nextInt(100);
		
		for(int i= 0; i<handler.object.size();++i) {
			Object tempObject=handler.object.get(i);
			
			if(tempObject.getId()==ID.Player && getAgro().intersects(tempObject.getBounds())) {
			
				diffX=x-tempObject.getX();
				diffY=y-tempObject.getY();
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
				hp-=50;
				handler.removeObject(tempObject);
			}
		}
		if(hp<=0) handler.removeObject(this);
		
		animDown.runAnimation();
	}

	@Override
	public void render(Graphics g) {
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
		return new Rectangle((int)x-120, (int)y-120,(int)x+ 152, (int)y+152);
	}
}
