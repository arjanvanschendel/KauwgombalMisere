package utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utillities.Sound;

public class SoundTest {

	private Sound snd;

	@Before
	public void setup() {
		try {
			snd = new Sound("res/Screaming_ducks.wav");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * make sure the music doesn't keep playing.
	 */
	@After
	public void teardown() {
		snd.stop();
	}

	/**
	 * test the start function.
	 */
	@Test
	public void startTest() {
		try {
			snd.play();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertTrue(snd.isPlaying());
	}

	/**
	 * test the stop function.
	 */
	@Test
	public void stopTest() {
		try {
			snd.play();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(snd.isPlaying());
		snd.stop();
		assertFalse(snd.isPlaying());

	}
}
