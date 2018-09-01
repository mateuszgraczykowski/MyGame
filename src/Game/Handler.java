package Game;

import java.awt.Graphics;
import java.util.LinkedList;

import Object.ObjectInterface;

public class Handler {

	public static LinkedList<ObjectInterface> object=new LinkedList<ObjectInterface>();
	
	private static boolean up=false, down=false, right=false, left=false; 
	
	public static boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		Handler.up = up;
	}

	public static boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		Handler.down = down;
	}

	public static boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		Handler.right = right;
	}

	public static boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		Handler.left = left;
	}

	public static void tick() {
		
		 for(int i = 0; i <object.size();++i) {
			 ObjectInterface tempObject = object.get(i);
			  tempObject.tick();
		}
	}
	
	public static void render(Graphics g) {
		  for(int i = 0; i <object.size();++i) {
			  ObjectInterface tempObject = object.get(i);
			  tempObject.render(g);
		  }
	}
	
	public static void addObject(ObjectInterface tempObject) {
		object.add(tempObject);
	}
	
	public static void removeObject(ObjectInterface tempObject) {
		object.remove(tempObject);
	}
}
