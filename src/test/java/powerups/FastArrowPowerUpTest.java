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
public class FastArrowPowerUpTest extends PowerUpTest {

	@Override
	final PowerUp getPowerUp() {
		return new FastArrowPowerUp(0, 20);
	}

	@Override
	final void isActiveTest() {
		assertTrue(FastArrowPowerUp.isActive());
		assertEquals(GameVariables.getArrowSpeed(),
				GameVariables.getFastArrowSpeed(), 0);

	}

	@Override
	final void isNotActiveTest() {
		assertFalse(FastArrowPowerUp.isActive());
		assertEquals(GameVariables.getArrowSpeed(),
				GameVariables.getNormalArrowSpeed(), 0);
	}

}
