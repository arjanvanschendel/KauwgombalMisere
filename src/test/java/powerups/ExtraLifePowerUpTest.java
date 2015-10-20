package powerups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import shapes.Point;
import game.GameVariables;

/**
 * Test for FastArrowPowerUp.
 * 
 * @author Jasper
 *
 */
public class ExtraLifePowerUpTest extends PowerUpTest {

	private int oldLives;

	@Override
	final PowerUp getPowerUp() {
		Point p = new Point(0, 20);
		return new ExtraLifePowerUp(p);
	}

	@Override
	final void isActiveTest() {
		assertTrue(ExtraLifePowerUp.isActive());
		assertEquals(GameVariables.getLives(), oldLives + 1, 0);

	}

	@Override
	final void isNotActiveTest() {
		assertFalse(ExtraLifePowerUp.isActive());
		assertEquals(GameVariables.getLives(), oldLives, 0);
	}

	@Override
	void setOldValue() {
		oldLives = GameVariables.getLives();

	}

}
