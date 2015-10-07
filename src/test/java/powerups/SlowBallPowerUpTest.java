package powerups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import game.GameVariables;

/**
 * Test for SlowBallPowerUp.
 * 
 * @author Jasper
 *
 */
public class SlowBallPowerUpTest extends PowerUpTest {

	@Override
	final PowerUp getPowerUp() {
		return new SlowBallPowerUp(0, 20);
	}

	@Override
	final void isActiveTest() {
		assertTrue(SlowBallPowerUp.isActive());
		assertEquals(GameVariables.getBallSpeed(),
				GameVariables.getSlowBallSpeed(), 0);

	}

	@Override
	final void isNotActiveTest() {
		assertFalse(SlowBallPowerUp.isActive());
		assertEquals(GameVariables.getBallSpeed(),
				GameVariables.getNormalBallSpeed(), 0);
	}

}
