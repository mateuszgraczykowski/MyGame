package Game;

import java.awt.Color;
import java.awt.Graphics;

import Object.Mage;

public class HUD {
	
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(10, 5, 200, 32);
		g.setColor(Color.red);
		g.fillRect(10, 5, (int) (Mage.health*2), 32);
		g.setColor(Color.black);
		g.drawRect(10, 5, 200, 32);
		
		g.setColor(Color.gray);
		g.fillRect(780, 5, 200, 32);
		g.setColor(Color.blue);
		g.fillRect(780, 5, (int) (Mage.mana*2), 32);
		g.setColor(Color.black);
		g.drawRect(780, 5, 200, 32);
	}
}
