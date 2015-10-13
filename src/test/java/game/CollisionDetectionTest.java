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
		Wall a = new Wall(0, 0, 10, 10, null);
		objects.add(a);
		assertNotEquals(CollisionDetection.getColliders(), objects);
		CollisionDetection.setColliders(objects);
		assertEquals(CollisionDetection.getColliders(), objects);

		// Clear List
		assertEquals(CollisionDetection.getColliders().size(), 1);
		CollisionDetection.clear();
		assertEquals(CollisionDetection.getColliders().size(), 0);

		// Add collider
		Wall b = new Wall(10, 0, 10, 10, null);
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
		Wall a = new Wall(0, 0, 10, 10, null);
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
		Wall a = new Wall(0, 0, 10, 10, null);
		Wall b = new Wall(10, 0, 10, 10, null);
		CollisionDetection.addCollider(a);
		ArrayList<Collision> collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the left of b = 2
		assertEquals(2, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Right of b
		a.setPosx(20);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the right of b = 4
		assertEquals(4, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Top of b
		a.setPosx(10);
		a.setPosy(10);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of b = 3
		assertEquals(3, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Bottom of b
		a.setPosy(-10);
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
		Wall a = new Wall(-1, 0, 10, 10, null);
		Wall b = new Wall(10, 0, 10, 10, null);
		CollisionDetection.clear();
		CollisionDetection.addCollider(a);
		ArrayList<Collision> collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Right of b
		a.setPosx(21);
		collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Top of b
		a.setPosx(10);
		a.setPosy(11);
		collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Bottom of b
		a.setPosy(-11);
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
		Wall a = new Wall(0, 0, 10, 10, null);
		Ball b = new Ball(15, 5, 5);
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
		a.setPosx(20);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the right of b = 4
		assertEquals(4, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Top of b
		a.setPosx(10);
		a.setPosy(10);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of b = 3
		assertEquals(3, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Bottom of b
		a.setPosy(-10);
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
		Wall a = new Wall(-1, 0, 10, 10, null);
		Ball b = new Ball(15, 5, 5);
		CollisionDetection.clear();
		CollisionDetection.addCollider(a);
		ArrayList<Collision> collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Right of b
		a.setPosx(21);
		collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Top of b
		a.setPosx(10);
		a.setPosy(11);
		collisions = CollisionDetection.collision(b);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Bottom of b
		a.setPosy(-11);
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
		Ball a = new Ball(5, 5, 5);
		Wall b = new Wall(10, 0, 10, 10, null);
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
		a.setPosx(25);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the right of b = 4
		assertEquals(4, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Top of b
		a.setPosx(15);
		a.setPosy(15);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of b = 3
		assertEquals(3, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// Hit Bottom of b
		a.setPosy(-5);
		collisions = CollisionDetection.collision(b);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of b = 3
		assertEquals(1, collisions.get(0).getSide());
		// With object a
		assertEquals(a, collisions.get(0).getCol());

		// No Collision
		a.setPosy(-10);
		collisions = CollisionDetection.collision(b);

		// No Collision
		assertEquals(0, collisions.size());

	}
}
