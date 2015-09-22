package objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import game.Level;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Matthias
 *
 */

public class BallTest {

	private Ball g;
	private Ball g1;
	private Ball g2;
	private Ball b;
	private Ball r;

	@Before
	public void setup() {
		g = new Ball(0, 0, 50 / 4);
		g1 = new Ball(0, 0, 50 / 4);
		g2 = new Ball(1, 0, 50 / 4);
		b = new Ball(0, 0, 50 / 2);
		r = new Ball(0, 0, 50);
	}

	@Test
	public void equalsTest() {
		assertEquals(g, g1);
		assertNotEquals(g, g2);
		assertNotEquals(b, r);
	}

	@Test
	public void hitTestSmallBall() {
		Level.clear();
		Level.addBall(g);
		assertFalse(Level.levelComplete());
		g.hit();
		assertTrue(Level.levelComplete());
	}

	@Test
	public void hitTestBigBall() {
		Level.clear();
		Level.addBall(r);
		assertFalse(Level.levelComplete());
		r.hit();
		assertFalse(Level.levelComplete());
	}
}
