package shapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Bart
 *
 */
public class CircleTest {

	private Circle circle;
	private Circle circle1;

	/**
	 * Setup executed before each test.
	 */
	@Before
	public void setup() {
		Point p = new Point(0, 400);
		circle = new Circle(p, 25, Color.CYAN);
		circle1 = new Circle(p, 25);
	}

	/**
	 * Tests the color constructor.
	 */
	@Test
	public void firstColorTest() {
		assertEquals(circle.getColor(), Color.CYAN);
	}

	/**
	 * Tests the setColor method.
	 */
	@Test
	public void secondColorTest() {
		circle.setColor(Color.GREEN);
		assertNotEquals(circle.getColor(), Color.CYAN);
		assertEquals(circle.getColor(), Color.GREEN);
	}

	/**
	 * Tests the radius constructor.
	 */
	@Test
	public void radiusTest() {
		assertEquals((long) circle.getRadius(), 25);
	}

	/**
	 * Tests posx, posy and radius setters.
	 */
	@Test
	public void changeParametersTest() {
		circle.setPosX(-100);
		circle.setPosY(300);
		circle.setRadius(50);
		assertEquals((long) circle.getPosX(), -100);
		assertEquals((long) circle.getPosY(), 300);
		assertEquals((long) circle.getRadius(), 50);
	}

	/**
	 * Tests position constructor.
	 */
	@Test
	public void positionTest() {
		assertEquals((long) circle.getPosX(), 0);
		assertEquals((long) circle.getPosY(), 400);
	}

	/**
	 * Tests constructor without a color.
	 */
	@Test
	public void noColorConstructor() {
		assertEquals(circle1.getColor(), new Color(0, 0, 0));
	}

	/**
	 * Tests the equals method of circle.
	 */
	@Test
	public void equalsTest() {
		assertNotEquals(circle1, circle);
		Circle duplicate = circle;
		assertEquals(duplicate, circle);
	}

}
