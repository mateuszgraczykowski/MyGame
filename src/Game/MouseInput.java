package Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Object.Mage;
import Object.Object;
import Object.Spell;

public class MouseInput extends MouseAdapter {
	
	
	private Handler handler;
	private Camera camera;
	private TexturesLoader tl;
	
	public MouseInput(Handler handler, Camera camera, TexturesLoader tl) {
		this.handler=handler;
		this.camera=camera;
		this.tl=tl;
	}
			
	public  void mousePressed(MouseEvent e) {
		
			int mx = (int) (e.getX() + camera.getX());
			int my = (int) (e.getY() + camera.getY());
			for (int i = 0; i < handler.object.size(); ++i) {
				Object tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player && Mage.mana > 0) {
					handler.addObject(new Spell((int) tempObject.getX() + 16, (int) tempObject.getY() + 24, ID.Spell,
							handler, mx, my, tl));
				}
			} 
	}
}
