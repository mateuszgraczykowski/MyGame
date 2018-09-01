package Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Object.Mage;
import Object.ObjectInterface;
import Object.FireballSpell;
import Object.FrozenTouchSpell;

public class MouseInput extends MouseAdapter {
	
	
	private Camera camera;
	
	public MouseInput(Handler handler, Camera camera) {
		this.camera=camera;
	}
	
	public  void mousePressed(MouseEvent e) {
			
			int button=e.getButton();
			int mx = (int) (e.getX() + camera.getX());
			int my = (int) (e.getY() + camera.getY());
			for (int i = 0; i < Handler.object.size(); ++i) {
				ObjectInterface tempObject = Handler.object.get(i);
				if (button==MouseEvent.BUTTON1 && tempObject.getId()==ID.Player && Mage.mana > 0 && (Main.gameStatus==Status.Game || Main.gameStatus==Status.NewGame)) {
					Handler.addObject(new FireballSpell((int) tempObject.getOX() + 16, (int) tempObject.getOY() + 24, ID.Spell, Game.handler, mx, my));
					AudioPlayer.getSound("sound_spell").play(3, 0.02f);											
				}
				else if(button==MouseEvent.BUTTON3 && tempObject.getId()==ID.Player && (Main.gameStatus==Status.Game || Main.gameStatus==Status.NewGame)) {
					Handler.addObject(new FrozenTouchSpell((int) tempObject.getOX(), (int) tempObject.getOY(), ID.Spell));
				}
			} 
	}
}
