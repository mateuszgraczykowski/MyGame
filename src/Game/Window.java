package Game;

import java.awt.*;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public Window(int width, int height, String title, Game game) {
		
		new JFrame(title);
		
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		add(game);
		setVisible(true);		
	}	
}
