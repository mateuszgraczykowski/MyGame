package Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Object.Mage;
import Object.Object;
import Object.Spell;

public class MouseInput extends MouseAdapter {
	
	
	private Handler handler;
	private Camera camera;
	
	public MouseInput(Handler handler, Camera camera) {
		this.handler=handler;
		this.camera=camera;
	}
			
	public  void mousePressed(MouseEvent e) {
		
			int mx = (int) (e.getX() + camera.getX());
			int my = (int) (e.getY() + camera.getY());
			for (int i = 0; i < handler.object.size(); ++i) {
				Object tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player && Mage.mana > 0 && (Game.gameStatus==Status.Game || Game.gameStatus==Status.NewGame)) {
					handler.addObject(new Spell((int) tempObject.getX() + 16, (int) tempObject.getY() + 24, ID.Spell, handler, mx, my, null));
					AudioPlayer.getSound("sound_spell").play(3, 0.02f);											
				}
			} 
	}
}
