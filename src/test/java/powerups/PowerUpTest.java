package powerups;

import static org.junit.Assert.assertEquals;
import game.CollisionDetection;
import objects.Player;
import objects.Projectile;
import objects.Wall;

import org.junit.Before;
import org.junit.Test;

import shapes.Point;

/**
 * 
 * Abstract PowerUpTest used to test the basic functionaliteit of all PowerUps.
 * 
 * @author Jasper
 *
 */
public abstract class PowerUpTest {

	/**
	 * The power up to test.
	 */
	private PowerUp power;

	/**
	 * 
	 * Setup executed before every test.
	 */
	@Before
	public final void setup() {
		CollisionDetection.clear();
		power = getPowerUp();
		Point pF = new Point(-100, 0);
		Point pC = new Point(-100, 20);
		Wall floor = new Wall(pF, 200, 15, null);
		Wall ceiling = new Wall(pC, 200, 15, null);
		CollisionDetection.addCollider(floor);
		CollisionDetection.addCollider(ceiling);
	}

	/**
	 * getPowerUp.
	 * 
	 * @return PowerUp to test.
	 */
	abstract PowerUp getPowerUp();

	/**
	 * Test for the effect.
	 */
	abstract void isActiveTest();

	/**
	 * Test the effect when the powerup is deactivated.
	 */
	abstract void isNotActiveTest();

	/**
	 * Execute before activating.
	 */
	abstract void setOldValue();

	/**
	 * Test if gravity properly effects the power up.
	 */
	@Test
	public final void gravityTest() {
		// Gravity test
		assertEquals(power.getPosY(), 20f, 0.1f);
		power.update(0.1);
		assertEquals(power.getPosY(), 17f, 0.1f);
		power.update(0.1);
		// Blocked by the ground
		assertEquals(power.getPosY(), 15f, 0.1f);

	}

	/**
	 * Test if the power up is activated by a player. And then deactivated over
	 * a certain amount of time
	 */
	@Test
	public final void activationTestPlayer() {
		power.setPosY(15);

		setOldValue();
		isNotActiveTest();
		Point p = new Point(0, 15);
		Player player = new Player(p);
		CollisionDetection.addCollider(player);
		setOldValue();
		power.update(0);
		isActiveTest();
		power.update(1);
		if (power.getPowerDuration() > 1) {
			isActiveTest();
		}
		setOldValue();
		power.update(10);
		isNotActiveTest();

		CollisionDetection.removeCollider(player);
	}

	/**
	 * Test if the power up is activated by a projectile. And then deactivated
	 * over a certain amount of time
	 */
	@Test
	public final void activationTestProjectile() {
		power.setPosY(15);

		setOldValue();
		isNotActiveTest();
		Point p = new Point(0, 15);
		Projectile pro = new Projectile(p);
		CollisionDetection.addCollider(pro);
		setOldValue();
		power.update(0);
		isActiveTest();
		power.update(1);
		if (power.getPowerDuration() > 1) {
			isActiveTest();
		}
		setOldValue();
		power.update(10);
		isNotActiveTest();

		CollisionDetection.removeCollider(pro);
	}

}
