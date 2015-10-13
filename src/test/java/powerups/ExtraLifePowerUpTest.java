package powerups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
		return new ExtraLifePowerUp(0, 20);
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
