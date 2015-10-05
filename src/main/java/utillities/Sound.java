package utillities;

import java.io.File;
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

	/**
	 * indicates whether there is an audio device or not.
	 */
	private boolean device;
	
	/**
	 * The audio file.
	 */
	private File f;

	/**
	 * dVolume: the change in volume. 
	 * Does not work for values beneath -6 somehow.
	 */
	private float dVolume;
	
	/**
	 * The AudioInputStream. Gets reset with every .play();
	 */
	private AudioInputStream audiostream;
	
	/**
	 * the Clip.
	 */
	private Clip clip;
	
	/**
	 * Holds a boolean value representing whether the Sound is playing or not.
	 */
	private boolean playing;

	/**
	 * Sound: constructor.
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public Sound(final String filename) {
			f = new File(filename);
			audiostream = null;
			dVolume = 0;
			playing = false;
			device = true;
	}

	/**
	 * Sound: secondary constructor. It includes an argument for delta volume.
	 * Added protection against crashing by capping dVolume to -6f. 
	 * 
	 * @param filename
	 * @param dv
	 * @throws IOException
	 */
	public Sound(final String filename, final float dv) {
		audiostream = null;
		f = new File(filename);
		dVolume = Math.max(-6f,dv);
		playing = false;
	}

	/**
	 * play: play the sound.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public final void play() {
		loop(0);
	}

	/**
	 * play: play the sound.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public final void loop(final int n) {
		try {
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
		} catch (IllegalArgumentException e){
			device = false;
			System.out.println("no device to play audio from");
		}
	}

	/**
	 * stop: Stop the sound.
	 */
	public final void stop() {
		if (isPlaying()) { 
			clip.stop();
			playing = false;
		}
	}
	
	/**
	 * isPlaying: returns playing.
	 * @return playing
	 */
	public final boolean isPlaying() {
		return playing;
	}
	
	/**
	 * getVolume: returns dVolume.
	 * @return dVolume
	 */
	public final float getVolume() {
		return dVolume;
	}

	/**
	 * unvalidDevice: returns wheter device is unvalid or not.
	 * @return device
	 */
	public final boolean unvalidDevice() {
		return !device;
	}
	
	
	/**
	 * setVolume: set dVolume.
	 * @param ndV
	 */
	public final void setVolume(final float ndV) {
		dVolume = Math.max(-6,ndV);
	}
}
