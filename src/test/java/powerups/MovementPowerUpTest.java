package powerups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import shapes.Point;
import game.GameVariables;

/**
 * Test for MovementPowerUp.
 * 
 * @author Jasper
 *
 */
public class MovementPowerUpTest extends PowerUpTest {

	@Override
	final PowerUp getPowerUp() {
		Point p = new Point(0, 20);
		return new MovementPowerUp(p);
	}

	@Override
	final void isActiveTest() {
		assertTrue(MovementPowerUp.isActive());
		assertEquals(GameVariables.getMovementSpeed(),
				GameVariables.getImprovedMovementSpeed(), 0);

	}

	@Override
	final void isNotActiveTest() {
		assertFalse(MovementPowerUp.isActive());
		assertEquals(GameVariables.getMovementSpeed(),
				GameVariables.getNormalMovementSpeed(), 0);
	}

	@Override
	void setOldValue() {

	}

}
