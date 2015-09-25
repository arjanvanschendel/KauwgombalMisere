package utillities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Sound class: this class represents a playable audio file.
 * 
 * @author Matthias Tavasszy
 *
 */
@SuppressWarnings("restriction")
public class Sound {

	private File f;

	private float dVolume;
	private AudioInputStream audiostream;
	Clip clip;
	private boolean playing;

	/**
	 * Sound: constructor.
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public Sound(final String filename) {
		audiostream = null;
		f = new File(filename);
		dVolume = 0;
		playing = false;
	}

	/**
	 * Sound: secondary constructor. It includes an argument for delta volume.
	 * 
	 * @param filename
	 * @param dv
	 * @throws IOException
	 */
	public Sound(final String filename, float dv) {
		audiostream = null;
		f = new File(filename);
		dVolume = dv;
		playing = false;
	}

	/**
	 * play: play the sound.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void play() {
		loop(0);
	}

	/**
	 * play: play the sound.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void loop(int n) {
		clip = null;

		try {
			audiostream = AudioSystem.getAudioInputStream(f);
			clip = AudioSystem.getClip();
			clip.open(audiostream);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(dVolume);
		clip.loop(n);

		playing = true;
	}

	public void stop() {
		if (isPlaying()) { 
			clip.stop();
			playing = false;
		}
	}
	
	public boolean isPlaying() {
		return playing;
	}
}
