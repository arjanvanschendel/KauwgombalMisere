package powerups;

import static org.junit.Assert.assertEquals;
import game.CollisionDetection;
import objects.Player;
import objects.Projectile;
import objects.Wall;

import org.junit.Before;
import org.junit.Test;

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

		Wall floor = new Wall(-100, 0, 200, 15, null);
		Wall ceiling = new Wall(-100, 20, 200, 15, null);
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
		assertEquals(power.getPosy(), 20, 0);
		power.update(0.1);
		assertEquals(power.getPosy(), 17, 0.1);
		power.update(0.1);
		// Blocked by the ground
		assertEquals(power.getPosy(), 15, 0.1);

	}

	/**
	 * Test if the power up is activated by a player. And then deactivated over
	 * a certain amount of time
	 */
	@Test
	public final void activationTestPlayer() {
		power.setPosy(15);

		setOldValue();
		isNotActiveTest();
		Player player = new Player(0, 15);
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
		power.setPosy(15);

		setOldValue();
		isNotActiveTest();
		Projectile pro = new Projectile(0, 15);
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
