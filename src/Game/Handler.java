package Game;

import java.awt.Graphics;
import java.util.LinkedList;

import Object.Object;

public class Handler {

	public LinkedList<Object> object=new LinkedList<Object>();
	
	private boolean up=false, down=false, right=false, left=false; 
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void tick() {
		
		 for(int i = 0; i <object.size();++i) {
			  Object tempObject = object.get(i);
			  tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		  for(int i = 0; i <object.size();++i) {
			  Object tempObject = object.get(i);
			  tempObject.render(g);
		  }
	}
	
	public void addObject(Object tempObject) {
		object.add(tempObject);
	}
	
	public void removeObject(Object tempObject) {
		object.remove(tempObject);
	}
}
