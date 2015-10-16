package objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import game.Level;

import org.junit.Before;
import org.junit.Test;

import shapes.Point;

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

	/**
	 * Setup executed before the tests.
	 */
	@Before
	public void setup() {
		g = new Ball(new Point(100, 100), 50 / 4);
		g1 = new Ball(new Point(100, 100), 50 / 4);
		g2 = new Ball(new Point(1, 0), 50 / 4);
		b = new Ball(new Point(0, 0), 50 / 2);
		r = new Ball(new Point(0, 0), 50);
	}

	/**
	 * Tests equals method.
	 */
	@Test
	public void equalsTest() {
		assertEquals(g, g1);
		assertNotEquals(g, g2);
		assertNotEquals(b, r);
	}

	/**
	 * Hit test small ball.
	 */
	@Test
	public void hitTestSmallBall() {
		Level lvl = new Level("levels/test/level1.lvl");
		Level.addBall(g);
		assertFalse(Level.levelComplete());
		g.hit();
		lvl.update(0);
		assertTrue(Level.levelComplete());
	}

	/**
	 * Hit test big ball.
	 */
	@Test
	public void hitTestBigBall() {
		Level.clear();
		Level.addBall(r);
		assertFalse(Level.levelComplete());
		r.hit();
		assertFalse(Level.levelComplete());
	}
}
