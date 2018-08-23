package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class TexturesContainer {
	
	public static BufferedImage image;
	public BufferedImage textures=loadImage("/map3.png");
	public static Map<String, BufferedImage> lvlTexturesMap=new HashMap<String, BufferedImage>();
	public static Map<String, BufferedImage> objectTexturesMap=new HashMap<String, BufferedImage>();
	
	public TexturesContainer() {
		//textures=loadImage("/map3.png");

		lvlTexturesMap.put("firstMap", textures);
	}
	
	public static BufferedImage getMap(String key) {
		return lvlTexturesMap.get(key);
	}
	
	public BufferedImage loadImage(String path) {
		try {
			image=ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
