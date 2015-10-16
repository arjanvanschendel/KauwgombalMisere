package utillities;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
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
	 * dVolume: the change in volume. Does not work for values beneath -6
	 * somehow.
	 */
	private float volume;

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

	private FloatControl gainControl;

	private BooleanControl muteControl;

	/**
	 * Sound: constructor.
	 * 
	 * @param filename
	 *            name of sound
	 * @throws IOException
	 */
	public Sound(final String filename) {
		f = new File(filename);
		audiostream = null;
		volume = 100;
		playing = false;
		


		try {
			audiostream = AudioSystem.getAudioInputStream(f);
			clip = AudioSystem.getClip();
			clip.open(audiostream);
			gainControl = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
			muteControl = (BooleanControl) clip
					.getControl(BooleanControl.Type.MUTE);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sound: secondary constructor. It includes an argument for delta volume.
	 * Added protection against crashing by capping dVolume to -6f.
	 * 
	 * @param filename
	 *            name of sound
	 * @param dv
	 *            volume
	 * @throws IOException
	 */
	public Sound(final String filename, final float dv) {
		audiostream = null;
		f = new File(filename);
		volume = Math.max(0, dv);
		playing = false;


		try {
			audiostream = AudioSystem.getAudioInputStream(f);
			clip = AudioSystem.getClip();
			clip.open(audiostream);
			gainControl = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
			muteControl = (BooleanControl) clip
					.getControl(BooleanControl.Type.MUTE);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * @param n
	 *            loops
	 */
	public final void loop(final int n) {
		try {
			if (volume == 0) {
				muteControl.setValue(true);
			} else {
				muteControl.setValue(false);
				gainControl.setValue((float) (Math.log(volume / 100d)
						/ Math.log(10.0) * 20.0));

			}
			clip.loop(n);

			device = true;

		} catch (IllegalArgumentException e) {
			device = false;
			System.out.println("no device to play audio from");
		}
		playing = true;
	}

	/**
	 * stop: Stop the sound.
	 */
	public final void stop() {
		if (isPlaying() && device) {
			clip.stop();
		}
		playing = false;
	}

	/**
	 * isPlaying: returns playing.
	 * 
	 * @return playing
	 */
	public final boolean isPlaying() {
		return playing;
	}

	/**
	 * getVolume: returns dVolume.
	 * 
	 * @return dVolume
	 */
	public final float getVolume() {
		return volume;
	}

	/**
	 * setVolume: set volume.
	 * 
	 * @param vol
	 *            volume to set
	 */
	public final void setVolume(final float vol) {
		volume = Math.max(0, vol);
		if (clip != null) {
			FloatControl gainControl = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
			BooleanControl muteControl = (BooleanControl) clip
					.getControl(BooleanControl.Type.MUTE);
			if (volume == 0) {
				muteControl.setValue(true);
			} else {
				muteControl.setValue(false);
				gainControl.setValue((float) (Math.log(volume / 100d)
						/ Math.log(10.0) * 20.0));
			}
		}
	}
}
