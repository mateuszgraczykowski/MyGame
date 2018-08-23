package Game;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	public static Map<String, Sound> soundMap=new HashMap<String, Sound>();
	public static Map<String, Music> musicMap=new HashMap<String, Music>();
	
	public static void init() {
		try {
			soundMap.put("sound_spell", new Sound("audio/spell.ogg"));
			soundMap.put("sound_menu", new Sound("audio/UI_Quirky25.ogg"));
			musicMap.put("music", new Music("audio/backgroundMelody.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
	
}



