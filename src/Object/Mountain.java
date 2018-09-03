package Object;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game.ID;

public class Mountain extends Object implements ObjectInterface {

	public Mountain(float x, float y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void render(Graphics g) {
		
	}
	
	@Override
	public Rectangle getBounds() {
		return null;
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
