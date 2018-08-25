package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Object.Mage;

public class UI extends MouseAdapter {
	
	private Handler handler;
	MapOne mapOne=new MapOne();
	
	public UI() {
	
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX=e.getX();
		int mouseY=e.getY();
		
		if(Game.gameStatus==Status.Menu) {
			//Continue
			if(checkIntersection(mouseX, mouseY,350, 220, 300, 75) && Mage.health!=0) {
				Game.gameStatus=Status.Game;
			}
			//Help menu
			if(checkIntersection(mouseX, mouseY,350, 320, 300, 75)) {
				Game.gameStatus=Status.Help;
			}
			//New Game
			if(checkIntersection(mouseX, mouseY, 350, 120, 300, 75)) {
				mapOne.removeMap(handler);							
				mapOne.loadMap(handler);
				Game.gameStatus=Status.NewGame;
			}
			//Exit game
			if(checkIntersection(mouseX, mouseY,350, 420, 300, 75)) {
				System.exit(1);
			}
		}
		if(Game.gameStatus==Status.Help) {
			if(checkIntersection(mouseX, mouseY,350, 150, 300, 75)) {
				Game.gameStatus=Status.Menu;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	
	private boolean checkIntersection(int mouseX, int mouseY, int x, int y, int width, int height) {
		
		if(mouseX>x && mouseX<x+width && mouseY>y && mouseY<y+height) {
			AudioPlayer.getSound("sound_menu").play(1, 0.1f);
			return true;
		}else 
			return false;
	}
	
	public void render(Graphics g) {
	
		if(Game.gameStatus==Status.Menu || Game.gameStatus==Status.Help) {
			
			g.setFont(new Font("arial", 1, 20));
			g.setColor(Color.white);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setColor(Color.black);
			
			if(Game.gameStatus==Status.Menu) {
				
				g.drawRect(350, 120, 300, 75);
				g.drawString("NEW GAME", 445, 165);
				
				g.drawRect(350, 320, 300, 75);
				g.drawString("HELP", 481, 370);
				
				g.drawRect(350, 420, 300, 75);
				g.drawString("EXIT", 483, 465);
				
				g.setFont(new Font("arial", 1, 50));
				g.drawString("MENU", 427, 75);	
				
				g.setFont(new Font("arial", 1, 20));
				
				if(Mage.health==0) {
					g.setColor(Color.LIGHT_GRAY);
					g.drawRect(350, 220, 300, 75);
					g.drawString("CONTINUE", 450, 270);
				}else {
					g.drawRect(350, 220, 300, 75);
					g.drawString("CONTINUE", 450, 270);
				}
			}
			
			if(Game.gameStatus==Status.Help) {				
				
				g.drawRect(350, 150, 300, 75);
				g.drawString("BACK", 470, 190);
				
				g.drawString("Use WASD to move and LMB to fire.", 50, 400);
				
				g.setFont(new Font("arial", 1, 50));
				g.drawString("HELP", 437, 75);				
			}
		}
	}
}
