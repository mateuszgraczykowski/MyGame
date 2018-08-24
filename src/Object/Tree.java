package Object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.ID;
import Game.TexturesLoader;


public class Tree extends Object {
	
	private Random random=new Random();
	private int choose=0; 
	private static BufferedImage treeTextures[]=new BufferedImage[3];
	private static TexturesLoader loader=new TexturesLoader("/tree.png");
		
	public Tree(float x, float y, ID id) {
		super(x, y, id);
		
		choose=random.nextInt(2);
		
		treeTextures[0]=loader.divideImage(6, 160, 110, 125);
		treeTextures[1]=loader.divideImage(135, 160, 110, 125);
		treeTextures[2]=loader.divideImage(265, 160, 110, 125);		
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(treeTextures[choose], (int)x, (int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 95, 105);
	}
}
