package utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utillities.Sound;

public class SoundTest {

	private Sound snd;

	@Before
	public void setup() {
		snd = new Sound("res/Screaming_ducks.wav");
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
		snd.play();

		assertTrue(snd.isPlaying());
	}

	/**
	 * test the stop function.
	 */
	@Test
	public void stopTest() {
		snd.play();
		assertTrue(snd.isPlaying());
		snd.stop();
		assertFalse(snd.isPlaying());

	}
}
