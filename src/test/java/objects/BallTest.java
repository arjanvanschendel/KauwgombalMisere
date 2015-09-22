package objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Matthias
 *
 */

public class BallTest {

	Ball g;
	Ball g1;
	Ball g2;
	Ball b;
	Ball r;

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

}
