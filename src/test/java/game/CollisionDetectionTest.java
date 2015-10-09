package game;

import static org.junit.Assert.*;
import interfaces.Observer;
import java.util.ArrayList;
import objects.Ball;
import objects.Wall;
import org.junit.Test;

public class CollisionDetectionTest {

	@Test
	public void collidersTest() {
		// Set colliders
		ArrayList<Observer> objects = new ArrayList<Observer>();
		Wall A = new Wall(0, 0, 10, 10, null);
		objects.add(A);
		assertNotEquals(CollisionDetection.getColliders(), objects);
		CollisionDetection.setColliders(objects);
		assertEquals(CollisionDetection.getColliders(), objects);

		// Clear List
		assertEquals(CollisionDetection.getColliders().size(), 1);
		CollisionDetection.clear();
		assertEquals(CollisionDetection.getColliders().size(), 0);

		// Add collider
		Wall B = new Wall(10, 0, 10, 10, null);
		assertFalse(CollisionDetection.getColliders().contains(A));
		assertFalse(CollisionDetection.getColliders().contains(B));
		CollisionDetection.addCollider(A);
		CollisionDetection.addCollider(B);
		assertEquals(CollisionDetection.getColliders().size(), 2);
		assertTrue(CollisionDetection.getColliders().contains(A));
		assertTrue(CollisionDetection.getColliders().contains(B));

		// Remove collider
		CollisionDetection.removeCollider(A);
		assertEquals(CollisionDetection.getColliders().size(), 1);
		assertFalse(CollisionDetection.getColliders().contains(A));
		assertTrue(CollisionDetection.getColliders().contains(B));
	}

	@Test
	public void impossibleCollisions() {

		// Self collide
		Wall A = new Wall(0, 0, 10, 10, null);
		CollisionDetection.addCollider(A);
		ArrayList<Collision> collisions = CollisionDetection.collision(A);

		// One Collision
		assertEquals(0, collisions.size());
	}

	@Test
	public void collideBoxBoxTest() {


		/*
		 *  Hit Sides
		 * 		3
		 * 	2		4
		 * 		1
		 */

		// Hit Left of B
		Wall A = new Wall(0, 0, 10, 10, null);
		Wall B = new Wall(10, 0, 10, 10, null);
		CollisionDetection.addCollider(A);
		ArrayList<Collision> collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the left of B = 2
		assertEquals(2, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

		// Hit Right of B
		A.setPosx(20);
		collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the right of B = 4
		assertEquals(4, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

		// Hit Top of B
		A.setPosx(10);
		A.setPosy(10);
		collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of B = 3
		assertEquals(3, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

		// Hit Bottom of B
		A.setPosy(-10);
		collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of B = 3
		assertEquals(1, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

	}

	@Test
	public void nearHitcollideBoxBoxTest() {


		/*
		 *  Hit Sides
		 * 		3
		 * 	2		4
		 * 		1
		 */

		// Near hit Left of B
		Wall A = new Wall(-1, 0, 10, 10, null);
		Wall B = new Wall(10, 0, 10, 10, null);
		CollisionDetection.clear();
		CollisionDetection.addCollider(A);
		ArrayList<Collision> collisions = CollisionDetection.collision(B);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Right of B
		A.setPosx(21);
		collisions = CollisionDetection.collision(B);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Top of B
		A.setPosx(10);
		A.setPosy(11);
		collisions = CollisionDetection.collision(B);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Bottom of B
		A.setPosy(-11);
		collisions = CollisionDetection.collision(B);

		// No Collisions
		assertEquals(0, collisions.size());

	}

	@Test
	public void collideBoxBallTest() {


		/*
		 *  Hit Sides
		 * 		3
		 * 	2		4
		 * 		1
		 */
		// Hit Left of B
		Wall A = new Wall(0, 0, 10, 10, null);
		Ball B = new Ball(15, 5, 5);
		CollisionDetection.clear();
		CollisionDetection.addCollider(A);
		ArrayList<Collision> collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the left of B = 2
		assertEquals(2, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

		// Hit Right of B
		A.setPosx(20);
		collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the right of B = 4
		assertEquals(4, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

		// Hit Top of B
		A.setPosx(10);
		A.setPosy(10);
		collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of B = 3
		assertEquals(3, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

		// Hit Bottom of B
		A.setPosy(-10);
		collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of B = 3
		assertEquals(1, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

	}

	@Test
	public void nearHitcollideBoxBallTest() {


		/*
		 *  Hit Sides
		 * 		3
		 * 	2		4
		 * 		1
		 */

		// Near hit Left of B
		Wall A = new Wall(-1, 0, 10, 10, null);
		Ball B = new Ball(15, 5, 5);
		CollisionDetection.clear();
		CollisionDetection.addCollider(A);
		ArrayList<Collision> collisions = CollisionDetection.collision(B);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Right of B
		A.setPosx(21);
		collisions = CollisionDetection.collision(B);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Top of B
		A.setPosx(10);
		A.setPosy(11);
		collisions = CollisionDetection.collision(B);

		// No Collisions
		assertEquals(0, collisions.size());

		// Hit Bottom of B
		A.setPosy(-11);
		collisions = CollisionDetection.collision(B);

		// No Collisions
		assertEquals(0, collisions.size());

	}

	@Test
	public void collideBallBoxTest() {


		/*
		 *  Hit Sides
		 * 		3
		 * 	2		4
		 * 		1
		 */

		// Hit Left of B
		Ball A = new Ball(5, 5, 5);
		Wall B = new Wall(10, 0, 10, 10, null);
		CollisionDetection.clear();
		CollisionDetection.addCollider(A);
		ArrayList<Collision> collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the left of B = 2
		assertEquals(2, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

		// Hit Right of B
		A.setPosx(25);
		collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the right of B = 4
		assertEquals(4, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

		// Hit Top of B
		A.setPosx(15);
		A.setPosy(15);
		collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of B = 3
		assertEquals(3, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

		// Hit Bottom of B
		A.setPosy(-5);
		collisions = CollisionDetection.collision(B);

		// One Collision
		assertEquals(1, collisions.size());
		// On the Top of B = 3
		assertEquals(1, collisions.get(0).getSide());
		// With object A
		assertEquals(A, collisions.get(0).getCol());

		// No Collision
		A.setPosy(-10);
		collisions = CollisionDetection.collision(B);

		// No Collision
		assertEquals(0, collisions.size());

	}
}
