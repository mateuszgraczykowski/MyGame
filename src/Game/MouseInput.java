package Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Object.Mage;
import Object.Object;
import Object.FireballSpell;
import Object.FrozenTouchSpell;

public class MouseInput extends MouseAdapter {
	
	
	private Camera camera;
	
	public MouseInput(Handler handler, Camera camera) {
		this.camera=camera;
	}
	
	public void delay(int delay) {
		for(int i=0; i<delay;++i) {
			
		}
	}
	
	public  void mousePressed(MouseEvent e) {
			
			int button=e.getButton();
			int mx = (int) (e.getX() + camera.getX());
			int my = (int) (e.getY() + camera.getY());
			for (int i = 0; i < Handler.object.size(); ++i) {
				Object tempObject = Handler.object.get(i);
				if (button==MouseEvent.BUTTON1 && tempObject.getId()==ID.Player && Mage.mana > 0 && (Game.gameStatus==Status.Game || Game.gameStatus==Status.NewGame)) {
					Handler.addObject(new FireballSpell((int) tempObject.getX() + 16, (int) tempObject.getY() + 24, ID.Spell, Game.handler, mx, my));
					AudioPlayer.getSound("sound_spell").play(3, 0.02f);											
				}
				else if(button==MouseEvent.BUTTON3 && tempObject.getId()==ID.Player && (Game.gameStatus==Status.Game || Game.gameStatus==Status.NewGame)) {
					Handler.addObject(new FrozenTouchSpell((int) tempObject.getX(), (int) tempObject.getY(), ID.Spell));
					
					Handler.removeObject(Handler.object.removeLast());
				}
			} 
	}
}
