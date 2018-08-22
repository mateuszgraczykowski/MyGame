package Game;

import Object.Object;

public class Camera {
	
	private float x, y;
	
	public Camera(float x, float y) {
		this.x=x;
		this.y=y;
	}
	
	public void tick(Object object) {
		x+=((object.getX()-x)-1000/2)*0.05f;
		y+=((object.getY()-y)-564/2)*0.05f;
		
		if(x<=0) x=0;
		if(x>=1070) x=1070;
		if(y<=0) y=0;
		if(y>=1564+32) y=1564+32;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
		
}
