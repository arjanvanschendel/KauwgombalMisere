package utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utillities.Sound;

/**
 * Tests the Sound class.
 * @author Matthias
 *
 */
public class SoundTest {

	/**
	 * Sound.
	 */
	private Sound snd;
	/**
	 * Sound with extra volume.
	 */
	private Sound snd2;

	/**
	 * setup: set up all objects before executing each test case.
	 */
	@Before
	public final void setup() {
		snd = new Sound("res/Screaming_ducks.wav");
		snd2 = new Sound("res/Screaming_ducks.wav", 5);
	}

	/**
	 * teardown: make sure the music doesn't keep playing.
	 */
	@After
	public final void teardown() {
		snd.stop();
	}

	/**
	 * test the play function.
	 */
	@Test
	public final void playTest() {
		snd.play();

		assertTrue(snd.isPlaying() || snd.unvalidDevice());
	}

	/**
	 * test the stop function.
	 */
	@Test
	public final void stopPlayTest() {
		snd.play();
		assertTrue(snd.isPlaying() || snd.unvalidDevice());
		snd.stop();
		assertFalse(snd.isPlaying() || snd.unvalidDevice());
	}
	
	/**
	 * test the loop function.
	 */
	@Test
	public final void loopTest() {
		snd.loop(5);

		assertTrue(snd.isPlaying() || snd.unvalidDevice());
	}

	/**
	 * test the stop function.
	 */
	@Test
	public final void stopLoopTest() {
		snd.loop(5);
		assertTrue(snd.isPlaying() || snd.unvalidDevice());
		snd.stop();
		assertFalse(snd.isPlaying() || snd.unvalidDevice());
	}

	/**
	 * test the play function.
	 */
	@Test
	public final void playTest2() {
		snd2.play();

		assertTrue(snd2.isPlaying() || snd.unvalidDevice());
	}

	/**
	 * test the stop function.
	 */
	@Test
	public final void stopPlayTest2() {
		snd2.play();
		assertTrue(snd2.isPlaying() || snd.unvalidDevice());
		snd2.stop();
		assertFalse(snd2.isPlaying() || snd.unvalidDevice());
	}
	
	/**
	 * test the loop function.
	 */
	@Test
	public final void loopTest2() {
		snd2.loop(5);

		assertTrue(snd2.isPlaying() || snd.unvalidDevice());
	}

	/**
	 * test the stop function.
	 */
	@Test
	public final void stopLoopTest2() {
		snd2.loop(5);
		assertTrue(snd2.isPlaying() || snd.unvalidDevice());
		snd2.stop();
		assertFalse(snd2.isPlaying() || snd.unvalidDevice());
	}
	
	/**
	 * volumeCapTest: tests if the volume cap works.
	 */
	@Test
	public final void volumeCapTest() {
		Sound snd3 = new Sound("res/Screaming_ducks.wav", -10);
		assertTrue(snd3.getVolume() == -6f);
		snd3.setVolume(10f);
		assertTrue(snd3.getVolume() == 10f);
		snd3.setVolume(-10f);
		assertTrue(snd3.getVolume() == -6f);
	}

}
