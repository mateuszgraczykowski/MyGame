package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Object.ObjectInterface;

public class KeyInput extends KeyAdapter {
	
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler=handler;		
	}
	
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		
		for(int i= 0; i<Handler.object.size();++i) {
			ObjectInterface tempObject=Handler.object.get(i);
			if(tempObject.getId()==ID.Player) {
				if(key==KeyEvent.VK_W) handler.setUp(true);
				if(key==KeyEvent.VK_S) handler.setDown(true);
				if(key==KeyEvent.VK_D) handler.setRight(true);
				if(key==KeyEvent.VK_A) handler.setLeft(true);
				if(key==KeyEvent.VK_ESCAPE) Main.gameStatus=Status.Menu;
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		
		for(int i= 0; i<Handler.object.size();++i) {
			ObjectInterface tempObject=Handler.object.get(i);
			if(tempObject.getId()==ID.Player) {
				if(key==KeyEvent.VK_W) handler.setUp(false);
				if(key==KeyEvent.VK_S) handler.setDown(false);
				if(key==KeyEvent.VK_D) handler.setRight(false);
				if(key==KeyEvent.VK_A) handler.setLeft(false);
			}
		}
	}
}




















