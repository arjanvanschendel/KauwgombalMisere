package objects;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import game.CollisionDetection;
import game.Launcher;
import game.Level;

import org.junit.Before;
import org.junit.Test;

import utillities.Keyboard;

/**
 * Tests for player class. 
 * > 90% branch coverage
 * > 70% instructions coverage
 * @author Luke
 *
 */
public class PlayerTest {

	private Player player;
	private Keyboard keyboard;

	/**
	 * Setup for player tests.
	 */
	@Before
	public void setup() {
		CollisionDetection.clear();
		player = new Player(250, 100);
		keyboard = new Keyboard();
		keyboard.invoke(Launcher.getWindow(), GLFW_KEY_LEFT, 0, 0, 0);
		keyboard.invoke(Launcher.getWindow(), GLFW_KEY_RIGHT, 0, 0, 0);
	}

	/**
	 * Test player walking left.
	 */
	@Test
	public void playerWalkLeftTest() {
		float expected = player.getPosx();
		keyboard.invoke(Launcher.getWindow(), GLFW_KEY_LEFT, 0, 1, 0);
		player.walkLeft(0.1);
		player.update(0.1);
		float actual = player.getPosx();
		assertTrue(actual < expected);
	}

	/**
	 * Test player walking right.
	 */
	@Test
	public void playerWalkRightTest() {
		float expected = player.getPosx();
		keyboard.invoke(Launcher.getWindow(), GLFW_KEY_RIGHT, 0, 1, 0);
		player.walkRight(0.1);
		player.update(0.1);
		float actual = player.getPosx();
		assertTrue(actual > expected);
	}

	/**
	 * Test player walking left faster than given GameVariable.
	 */
	@Test
	public void playerWalkLeftFasterThanGameVariableTest() {
		float expected = player.getPosx();
		keyboard.invoke(Launcher.getWindow(), GLFW_KEY_LEFT, 0, 1, 0);
		player.walkLeft(1);
		player.update(1);
		float actual = player.getPosx();
		assertTrue(actual < expected);
	}

	/**
	 * Test player walking right faster than given GameVariable.
	 */
	@Test
	public void playerWalkRightFasterThanGameVariableTest() {
		float expected = player.getPosx();
		keyboard.invoke(Launcher.getWindow(), GLFW_KEY_RIGHT, 0, 1, 0);
		player.walkRight(5);
		player.update(5);
		float actual = player.getPosx();
		assertTrue(actual > expected);
	}

	/**
	 * Test player stop from walking to the right.
	 */
	@Test
	public void playerWalkRightToStopTest() {
		float expected = player.getPosx();
		player.walkRight(1);
		player.update(1);
		float actual = player.getPosx();
		assertEquals(actual, expected, 0f);
	}

	/**
	 * Test player stop from walking to the left.
	 */
	@Test
	public void playerWalkLeftToStopTest() {
		float expected = player.getPosx();
		player.walkLeft(1);
		player.update(1);
		float actual = player.getPosx();
		assertEquals(actual, expected, 0f);
	}

	/**
	 * Test player stopping from walking to the right.
	 */
	@Test
	public void playerWalkRightToStoppingTest() {
		float expected = player.getPosx();
		player.walkRight(1);
		player.update(.1);
		float actual = player.getPosx();
		assertTrue(actual > expected);
	}

	/**
	 * Test player stopping from walking to the left.
	 */
	@Test
	public void playerWalkLeftToStoppingTest() {
		float expected = player.getPosx();
		player.walkLeft(1);
		player.update(.1);
		float actual = player.getPosx();
		assertTrue(actual < expected);
	}

	/**
	 * Test player stop from walking while idle.
	 */
	@Test
	public void playerIdleToStopTest() {
		float expected = player.getPosx();
		player.update(.1);
		float actual = player.getPosx();
		assertEquals(actual, expected, 0f);
	}

	/**
	 * Test player colliding with ball.
	 */
	@Test
	public void playerCollidesWithBallTest() {
		Ball ball = new Ball(250, 100, 20);
		CollisionDetection.addCollider(ball);
		player.update(0.1);
		assertFalse(player.isAlive());
	}

	/**
	 * Test player colliding with right wall.
	 */
	@Test
	public void playerCollidesWithRightWallTest() {
		Wall wall = new Wall(500, 100, 60, 1000, null);
		CollisionDetection.addCollider(wall);
		keyboard.invoke(Launcher.getWindow(), GLFW_KEY_RIGHT, 0, 1, 0);
		for (int i = 0; i < 10; i++) {
			player.update(.1);

		}
		assertEquals(440f, player.getPosx(), 0f);
	}

	/**
	 * Test player colliding with left wall.
	 */
	@Test
	public void playerCollidesWithLeftWallTest() {
		Wall wall = new Wall(0, 100, 60, 1000, null);
		CollisionDetection.addCollider(wall);
		keyboard.invoke(Launcher.getWindow(), GLFW_KEY_LEFT, 0, 1, 0);
		for (int i = 0; i < 10; i++) {
			player.update(.1);
		}
		assertEquals(60f, player.getPosx(), 0f);
	}

	/**
	 * Test player colliding with wall above.
	 */
	@Test
	public void playerCollidesWithtWallTest() {
		Wall wall = new Wall(250, 150, 60, 1000, null);
		CollisionDetection.addCollider(wall);
		player.update(.1);
		assertEquals(250f, player.getPosx(), 0f);
	}

	/**
	 * Test player colliding with projectile.
	 */
	@Test
	public void playerCollidesWithProjectileTest() {
		Projectile projectile = new Projectile(250, 100);
		CollisionDetection.addCollider(projectile);
		player.update(.1);
		assertEquals(250f, player.getPosx(), 0f);
	}

	/**
	 * Test player update while not alive.
	 */
	@Test
	public void playerUpdateWhileNotAlive() {
		float expected = player.getPosx();
		Ball ball = new Ball(250, 100, 20);
		CollisionDetection.addCollider(ball);
		player.update(0.1);
		keyboard.invoke(Launcher.getWindow(), GLFW_KEY_LEFT, 0, 1, 0);
		for (int i = 0; i < 10; i++) {
			player.update(.1);
		}
		float actual = player.getPosx();
		assertEquals(actual, expected, 0f);
	}

	
	/**
	 * Test player shooting projectile.
	 */
	@Test
	public void playerShootsProjectileTest() {
		player.shoot();
		assertFalse(Level.getProjectile() == null);
	}

}
