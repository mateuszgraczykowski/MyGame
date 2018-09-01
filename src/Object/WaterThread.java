package Object;

import Game.Handler;
import Game.ID;

public class WaterThread extends Thread {
	
	private int speed=4;
	private static int index;
	private float x, y;
	private ID id;
	
	public WaterThread(float x, float y, ID id) {	
		this.x=x;
		this.y=y;
		this.id=id;
	}
	
	public void run() {
		index++;
		if(index>speed) {
			index=0;
			Handler.addObject(new Water(x, y, id));
		}
	}
}
