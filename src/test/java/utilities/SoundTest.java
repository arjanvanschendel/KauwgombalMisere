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
 * 
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
		snd2 = new Sound("res/Screaming_ducks.wav", 0);
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

		assertTrue(snd.isPlaying());
	}

	/**
	 * test the stop function.
	 */
	@Test
	public final void stopPlayTest() {
		snd.play();
		assertTrue(snd.isPlaying());
		snd.stop();
		assertFalse(snd.isPlaying());
	}

	/**
	 * test the loop function.
	 */
	@Test
	public final void loopTest() {
		snd.loop(5);

		assertTrue(snd.isPlaying());
	}

	/**
	 * test the stop function.
	 */
	@Test
	public final void stopLoopTest() {
		snd.loop(5);
		assertTrue(snd.isPlaying());
		snd.stop();
		assertFalse(snd.isPlaying());
	}

	/**
	 * test the play function.
	 */
	@Test
	public final void playTest2() {
		snd2.play();

		assertTrue(snd2.isPlaying());
	}

	/**
	 * test the stop function.
	 */
	@Test
	public final void stopPlayTest2() {
		snd2.play();
		assertTrue(snd2.isPlaying());
		snd2.stop();
		assertFalse(snd2.isPlaying());
	}

	/**
	 * test the loop function.
	 */
	@Test
	public final void loopTest2() {
		snd2.loop(5);

		assertTrue(snd2.isPlaying());
	}

	/**
	 * test the stop function.
	 */
	@Test
	public final void stopLoopTest2() {
		snd2.loop(5);
		assertTrue(snd2.isPlaying());
		snd2.stop();
		assertFalse(snd2.isPlaying());
	}

	/**
	 * Start while sound is already playing.
	 */
	@Test
	public final void restart() {
		assertFalse(snd.isPlaying());
		snd.play();
		assertTrue(snd.isPlaying());
		snd.play();
		assertTrue(snd.isPlaying());
	}

	/**
	 * Set volume while playing.
	 */
	@Test
	public final void testSetVolume() {
		snd.play();
		assertTrue(snd.isPlaying());
		assertEquals(snd.getVolume(), 100, 0);
		snd.setVolume(50);
		assertTrue(snd.isPlaying());
		assertEquals(snd.getVolume(), 50, 0);
		snd.setVolume(0);
		assertTrue(snd.isPlaying());
		assertEquals(snd.getVolume(), 0, 0);
	}

	/**
	 * Tests the two constructors.
	 */
	@Test
	public final void testConstruct() {
		assertEquals(snd.getVolume(), 100, 0);
		assertEquals(snd2.getVolume(), 0, 0);
	}

	/**
	 * volumeCapTest: tests if the volume cap works.
	 */
	@Test
	public final void volumeCapTest() {
		Sound snd3 = new Sound("res/Screaming_ducks.wav", -10);
		assertEquals(snd3.getVolume(), 0, 0.01);
		snd3.setVolume(10f);
		assertTrue(snd3.getVolume() == 10f);
		snd3.setVolume(-10f);
		assertEquals(snd3.getVolume(), 0, 0.01);
	}

}
