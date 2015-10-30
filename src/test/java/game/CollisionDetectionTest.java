package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import objects.Ball;
import objects.GameObject;
import objects.Wall;

import org.junit.Test;

import shapes.Point;

/**
 * 
 * Tests for the CollisionDetection class.
 * 
 * @author Jasper
 *
 */
public class CollisionDetectionTest {

	/**
	 * Tests the colliders list.
	 */
	@Test
	public void collidersTest() {
		// Set colliders
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		Point p = new Point(0, 0);
		Wall a = new Wall(p, 10, 10, null);
		objects.add(a);
		assertNotEquals(CollisionDetection.getColliders(), objects);
		CollisionDetection.setColliders(objects);
		assertEquals(CollisionDetection.getColliders(), objects);

		// Clear List
		assertEquals(CollisionDetection.getColliders().size(), 1);
		CollisionDetection.clear();
		assertEquals(CollisionDetection.getColliders().size(), 0);

		// Add collider
		p = new Point(10, 0);
		Wall b = new Wall(p, 10, 10, null);
		assertFalse(CollisionDetection.getColliders().contains(a));
		assertFalse(CollisionDetection.getColliders().contains(b));
		CollisionDetection.addCollider(a);
		CollisionDetection.addCollider(b);
		assertEquals(CollisionDetection.getColliders().size(), 2);
		assertTrue(CollisionDetection.getColliders().contains(a));
		assertTrue(CollisionDetection.getColliders().contains(b));

		// Remove collider
		CollisionDetection.removeCollider(a);
		assertEquals(CollisionDetection.getColliders().size(), 1);
		assertFalse(CollisionDetection.getColliders().contains(a));
		assertTrue(CollisionDetection.getColliders().contains(b));
	}

	/**
	 * Tests self collisions.
	 */
	@Test
	public void impossibleCollisions() {

		// Self collide
		Point p = new Point(0, 0);
		Wall a = new Wall(p, 10, 10, null);
		CollisionDetection.addCollider(a);
		ArrayList<Collision> collisions = CollisionDetection.collision(a);

		// One Collision
		assertEquals(0, collisions.size());
	}

	/**
	 * Tests Box with a Box collisions.
	 */
	@Test
	public void collideBoxBoxTest() {

		/*
		 * Hit Sides 3 2 4 1
		 */

		// Hit Left of b
		Point pA = new Point(0, 0);
		Point pB = new Point(10, 0);
		Wall a = new Wall(pA, 10, 10, null);
		Wall b = new Wall(pB, 10, 10, null);
		CollisionDetection.addCollider(a);
		ArrayList<Collision> collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the left of b = 2
		assertEquals(2, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Right of b
		a.setPosX(20);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the right of b = 4
		assertEquals(4, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Top of b
		a.setPosX(10);
		a.setPosY(10);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of b = 3
		assertEquals(3, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Bottom of b
		a.setPosY(-10);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of b = 3
		assertEquals(1, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

	}

	/**
	 * Tests Box with a Box nearhit-collisions.
	 */
	@Test
	public void nearHitcollideBoxBoxTest() {

		/*
		 * Hit Sides 3 2 4 1
		 */

		// Near hit Left of b
		Point pA = new Point(-1, 0);
		Point pB = new Point(10, 0);
		Wall a = new Wall(pA, 10, 10, null);
		Wall b = new Wall(pB, 10, 10, null);
		CollisionDetection.clear();
		CollisionDetection.addCollider(a);
		ArrayList<Collision> collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Right of b
		a.setPosX(21);
		collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Top of b
		a.setPosX(10);
		a.setPosY(11);
		collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Bottom of b
		a.setPosY(-11);
		collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

	}

	/**
	 * Tests Box with a Ball collisions.
	 */
	@Test
	public void collideBoxBallTest() {

		/*
		 * Hit Sides 3 2 4 1
		 */
		// Hit Left of b
		Point p = new Point(0, 0);
		Point pB = new Point(15, 5);
		Wall a = new Wall(p, 10, 10, null);
		Ball b = new Ball(pB, 5, 2);
		CollisionDetection.clear();
		CollisionDetection.addCollider(a);
		ArrayList<Collision> collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the left of b = 2
		assertEquals(2, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Right of b
		a.setPosX(20);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the right of b = 4
		assertEquals(4, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Top of b
		a.setPosX(10);
		a.setPosY(10);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of b = 3
		assertEquals(3, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Bottom of b
		a.setPosY(-10);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of b = 3
		assertEquals(1, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

	}

	/**
	 * Tests Ball with a Box nearhit-collisions.
	 */
	@Test
	public void nearHitcollideBoxBallTest() {

		/*
		 * Hit Sides 3 2 4 1
		 */

		// Near hit Left of b
		Point p = new Point(-1, 0);
		Point pB = new Point(15, 5);
		Wall a = new Wall(p, 10, 10, null);
		Ball b = new Ball(pB, 5, 2);
		CollisionDetection.clear();
		CollisionDetection.addCollider(a);
		ArrayList<Collision> collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Right of b
		a.setPosX(21);
		collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Top of b
		a.setPosX(10);
		a.setPosY(11);
		collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Bottom of b
		a.setPosY(-11);
		collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

	}

	/**
	 * Tests Ball with a Box collisions.
	 */
	@Test
	public void collideBallBoxTest() {

		/*
		 * Hit Sides 3 2 4 1
		 */

		// Hit Left of b
		Point p = new Point(10, 0);
		Point pB = new Point(5, 5);
		Ball a = new Ball(pB, 5, 2);
		Wall b = new Wall(p, 10, 10, null);
		CollisionDetection.clear();
		CollisionDetection.addCollider(a);
		ArrayList<Collision> collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the left of b = 2
		assertEquals(2, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Right of b
		a.setPosX(25);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the right of b = 4
		assertEquals(4, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Top of b
		a.setPosX(15);
		a.setPosY(15);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of b = 3
		assertEquals(3, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Bottom of b
		a.setPosY(-5);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of b = 3
		assertEquals(1, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// No Collision
		a.setPosY(-10);
		collisions = CollisionDetection.collision(b);

		// No Collision
		assertEquals(0, collisions.size());

	}
}
