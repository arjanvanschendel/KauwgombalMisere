package shapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Bart
 *
 */
public class PointTest {

	private Point point;
	private Point point1;

	/**
	 * Setup executed before each test.
	 */
	@Before
	public void before() {
		point = new Point(-200, 150);
		point1 = new Point(-400, -150);
	}
	
	/**
	 * Tests the dot product method in Point.
	 */
	@Test
	public void dotProductTest() {
		assertEquals((long) point.dot(point1), 57500);
	}
}
