package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TexturesLoader {

	public BufferedImage image;
	public TexturesLoader(String path) {
		loadImage(path);
	}
	
	public BufferedImage loadImage(String path) {
		try {
			image=ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public BufferedImage divideImage(int col, int row, int width, int height) {
		return image.getSubimage(col, row, width, height);
	}
}
