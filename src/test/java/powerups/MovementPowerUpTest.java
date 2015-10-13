package powerups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
		return new MovementPowerUp(0, 20);
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
