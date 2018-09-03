package Object;

import Game.Handler;
import Game.ID;

public class WaterThread extends Thread {
	
	private int speed=4;
	private static int index;
	public float x=710, y=20;
	private ID id=ID.Water;
	public static int waterLimit=250;
	public static int waterIndex=0;
	
	public WaterThread() {
	}
	
	public void run() {
		
		if(waterIndex<waterLimit) {
			waterIndex++;
			index++;
			if(index>speed) {
				index=0;
				Handler.addObject(new Water(x, y, id));
				Handler.addObject(new Water(x+8, y, id));
				Handler.addObject(new Water(x+16, y, id));
			}
		}
	}
}
