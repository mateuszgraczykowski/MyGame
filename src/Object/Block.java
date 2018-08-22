package Object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.ID;
import Game.TexturesLoader;


public class Block extends Object {
	
	private BufferedImage blockTextures;
	
	public Block(float x, float y, ID id, TexturesLoader tl) {
		super(x, y, id, tl);
		
		blockTextures=tl.divideImage(50, 0, 110, 130);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(blockTextures,(int)x, (int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 100, 110);
	}
}
