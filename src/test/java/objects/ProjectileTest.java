package objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import game.CollisionDetection;

import org.junit.Before;
import org.junit.Test;

import shapes.Point;

/**
 * 
 * @author Luke
 *
 */
public class ProjectileTest {

	/**
	 * Setup executed before each test.
	 */
	@Before
	public void setup() {
		CollisionDetection.clear();
	}

	/**
     * 
     */
	@Test
	public void updateEmptyCollisionsTest() {
		Point p = new Point(0, 0);
		Projectile a = new Projectile(p);
		a.update(0);
		assertFalse(a.getHitBall());
	}

	/**
     * 
     */
	@Test
	public void updateNonEmptyCollisionsTest() {
		Point pA = new Point(0, 0);
		Point pB = new Point(10, 10);
		Projectile a = new Projectile(pA);
		Projectile b = new Projectile(pB);
		CollisionDetection.addCollider(b);
		a.update(0);
		assertFalse(a.getHitBall());
	}

	/**
     * 
     */
	@Test
	public void updateBallCollisionsTest() {
		Point p = new Point(0, 0);
		Projectile a = new Projectile(p);
		Ball b = new Ball(new Point(0, 0), 1, 2);
		CollisionDetection.addCollider(b);
		a.update(0);
		assertTrue(a.getHitBall());
	}

	/**
     * 
     */
	@Test
	public void updateBoxCollisionsTest() {
		Point p = new Point(0, 0);
		Projectile a = new Projectile(p);
		Point pW = new Point(0, 0);
		Wall b = new Wall(pW, 1, 1, null);
		CollisionDetection.addCollider(b);
		a.update(0);
		assertFalse(a.getHitBall());
	}

	/**
     * 
     */
	@Test
	public void updateCeilingCollisionsTest() {
		Point p = new Point(0, 0);
		Projectile a = new Projectile(p);
		Point pW = new Point(0, 1);
		Wall b = new Wall(pW, 1, 10, null);
		a.setHeight(1.1f);
		CollisionDetection.addCollider(b);
		a.update(0);
		assertFalse(a.getHitBall());
	}

}
