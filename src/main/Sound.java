package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sound {

	private String fileName;

	public Sound(String fName) {
		fileName = fName;
	}

	@SuppressWarnings("restriction")
	public void play() throws FileNotFoundException, IOException {
			AudioPlayer.player.start(new AudioStream(new FileInputStream(
					fileName)));
	}

}
