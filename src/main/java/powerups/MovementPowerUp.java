package powerups;

import java.awt.Color;

import game.GameVariables;

/**
 * 
 * MovementPowerUp is a PowerUp that increases the MovementSpeed of the Player.
 * 
 * @author Jasper
 *
 */
public class MovementPowerUp extends PowerUp {
	
	/**
	 * newSpeed is the improved movement speed.
	 */
	private float newSpeed = GameVariables.getImprovedMovementSpeed();
	/**
	 * oldSpeed is the basic movement speed.
	 */
	private float oldSpeed = GameVariables.getNormalMovementSpeed();

	/**
	 * 
	 * Constructor for MovementPowerUp.
	 * 
	 * @param posx x-coordinate at which the PowerUp spawns.
	 * @param posy y-coordinate at which the PowerUp spawns.
	 */
	public MovementPowerUp(final float posx, final float posy) {
		super(posx, posy, Color.GREEN);
		powerDuration = 3;
	}

	/**
	 *	Defines the effect of that increases the movement speed.
	 */
	@Override
	final void effect() {
		GameVariables.setMovementSpeed(newSpeed);
	}

	/**
	 *	Defines the effect of that increases the movement speed.
	 */
	@Override
	public final void deactivate() {
		GameVariables.setMovementSpeed(oldSpeed);
		basicDeactivate();
	}
}
