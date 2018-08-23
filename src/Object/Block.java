package Object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.ID;
import Game.TexturesLoader;


public class Block extends Object {
	
	private static BufferedImage blockTextures;
	private static TexturesLoader loader=new TexturesLoader("/textures.png");
	
	public Block(float x, float y, ID id, TexturesLoader tl) {
		super(x, y, id, tl);
		
		blockTextures=loader.divideImage(50, 0, 110, 130);
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
		return new Rectangle((int)x, (int)y, 95, 105);
	}
}
