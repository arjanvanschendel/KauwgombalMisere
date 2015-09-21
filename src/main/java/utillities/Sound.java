package utillities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;


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
	private AudioData audiodata;
	private AudioDataStream audiostream;
	private ContinuousAudioDataStream continuousaudiostream;
	
	private boolean playing;


	/**
	 * Sound: constructor.
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public Sound(final String filename) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		AudioStream audioStream = new AudioStream(fis);
		audiodata = audioStream.getData();
		audiostream = null;
		continuousaudiostream = null;
		
		playing = false;
	}

	/**
	 * play: play the sound.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public void play() throws FileNotFoundException, IOException {
		audiostream = new AudioDataStream(audiodata);
		AudioPlayer.player.start(audiostream);
		playing = true;
	}

	/**
	 * loop: loop the sound.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void loop() throws FileNotFoundException, IOException {
		continuousaudiostream = new ContinuousAudioDataStream(audiodata);
		AudioPlayer.player.start(continuousaudiostream);
		
		playing = true;
	}

	/**
	 * stop: stop the sound.
	 */
	public void stop() {
		if (audiostream != null) {
			AudioPlayer.player.stop(audiostream);
			playing = false;
		}
		if (continuousaudiostream != null) {
			AudioPlayer.player.stop(continuousaudiostream);
			playing = false;
		}
	}

	public boolean isPlaying() {
		return playing;
	}
}
