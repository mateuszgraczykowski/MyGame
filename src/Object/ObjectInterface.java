package Object;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game.ID;

public interface ObjectInterface {
	public ID getId();
	public float getOX();
	public float getOY();
	public abstract Rectangle getBounds();
	public abstract void render(Graphics g);
	public abstract void tick();
	public void setX(float x);
	public void setY(float y);
	public float getVelX();
	public void setVelX(float velX);
	public float getVelY();
	public void setVelY(float velY);
	public void setId(ID id);
}
