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
public class FastArrowPowerUpTest extends PowerUpTest {

	@Override
	final PowerUp getPowerUp() {
		Point p = new Point(0, 20);
		return new FastArrowPowerUp(p);
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

	@Override
	void setOldValue() {
	}

}
