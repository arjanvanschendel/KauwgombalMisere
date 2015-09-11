package utillities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Sound class: this class represents a playable audio file.
 * 
 * @author Matthias Tavasszy
 *
 */
@SuppressWarnings("restriction")
public class Sound {

	/**
	 * filename.
	 */
	private String fileName;

	/**
	 * Sound: constructor.
	 * 
	 * @param fName
	 */
	public Sound(final String fName) {
		fileName = fName;
	}

	/**
	 * play: play the sound.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public void play() throws FileNotFoundException, IOException {
			AudioPlayer.player.start(new AudioStream(new FileInputStream(
					fileName)));
	}

}
