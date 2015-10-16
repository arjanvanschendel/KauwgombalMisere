package powerups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import shapes.Point;
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
		Point p = new Point(0, 20);
		return new SlowBallPowerUp(p);
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

	@Override
	void setOldValue() {

	}

}
