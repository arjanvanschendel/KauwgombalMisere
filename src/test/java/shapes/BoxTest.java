package shapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Bart
 *
 */

public class BoxTest {

	private Box box;
	private Point point = new Point(-460, 1900);
	private Point point1 = new Point(-499, 1999);
	private Point point2 = new Point(-501, 2001);

	/**
	 * Setup executed before each test.
	 */
	@Before
	public void before() {
		Point pos = new Point(-500, 0);
		box = new Box(pos, 50, 2000, Color.BLACK);
	}

	/**
	 * Tests the position of a box.
	 * 
	 */
	@Test
	public void posTest() {
		assertEquals((long) box.getHeight(), 2000);
		assertEquals((long) box.getWidth(), 50);
		assertEquals((long) box.getPosX(), -500);
		assertEquals((long) box.getPosY(), 0);
		box.setPosX(-400);
		box.setPosY(-100);
		assertEquals((long) box.getPosX(), -400);
		assertEquals((long) box.getPosY(), -100);
	}

	/**
	 * Tests if the corners are in the correct position.
	 */
	@Test
	public void cornersTest() {
		assertEquals((long) box.getCorners()[0].getX(), -500);
		assertEquals((long) box.getCorners()[2].getY(), 2000);
		assertEquals((long) box.getCorners()[3].getX(), -500);
	}

	/**
	 * Checks the corners width a different position and width.
	 */
	@Test
	public void anotherCornersTest() {
		box.setHeight(1000);
		box.setWidth(200);
		assertEquals((long) box.getCorners()[2].getX(), -300);
		assertEquals((long) box.getCorners()[2].getY(), 1000);
	}

	/**
	 * Tests if setColor works correctly.
	 */
	@Test
	public void colorTest() {
		assertEquals(box.getColor(), Color.BLACK);
		box.setColor(Color.RED);
		assertEquals(box.getColor(), Color.RED);
	}

	/**
	 * Tests the pointInShape with different points.
	 */
	@Test
	public void pointInShapeTest() {
		assertTrue(box.pointInShape(point));
		assertTrue(box.pointInShape(point1));
		assertFalse(box.pointInShape(point2));
	}
}
