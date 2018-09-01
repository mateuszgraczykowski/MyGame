package Object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.ID;
import Game.TexturesLoader;


public class Tree extends Object implements ObjectInterface{
	
	private Random random=new Random();
	private int choose=0; 
	private static BufferedImage treeTextures[]=new BufferedImage[6];
	private static TexturesLoader loader=new TexturesLoader("/tree.png");
		
	public Tree(float x, float y, ID id) {
		super(x, y, id);
		
		choose=random.nextInt(6);
		
		treeTextures[0]=loader.divideImage(6, 160, 110, 125);
		treeTextures[1]=loader.divideImage(135, 160, 110, 125);
		treeTextures[2]=loader.divideImage(265, 160, 110, 125);		
		treeTextures[3]=loader.divideImage(6, 160, 110, 110);
		treeTextures[4]=loader.divideImage(135, 160, 110, 113);
		treeTextures[5]=loader.divideImage(265, 160, 110, 112);		
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		if(choose<3) {
			g.drawImage(treeTextures[choose], (int)x, (int)y, null);
		}
		if(choose>=3) {
			g.drawImage(treeTextures[choose], (int)x, (int)y+15, null);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 95, 105);
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
