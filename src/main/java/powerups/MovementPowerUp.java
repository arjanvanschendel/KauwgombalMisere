package powerups;

import java.awt.Color;

import shapes.Box;
import game.GameVariables;
import game.Launcher;

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
	 * currentActive is a static boolean which shows if the current PowerUp is
	 * active.
	 */
	private static boolean currentActive = false;
	/**
	 * The visual indicator which shows if the PowerUp is active.
	 */
	private Box indicator;

	/**
	 * 
	 * Constructor for MovementPowerUp.
	 * 
	 * @param posx
	 *            x-coordinate at which the PowerUp spawns.
	 * @param posy
	 *            y-coordinate at which the PowerUp spawns.
	 */
	public MovementPowerUp(final float posx, final float posy) {
		super(posx, posy, Color.GREEN);
		indicator = new Box(Launcher.getCamWidth() / 2 - 40,
				Launcher.getCamHeight() - 30, 20, 20, Color.GREEN);
		setPowerDuration(3);
	}

	@Override
	public final void render() {
		if (currentActive) {
			indicator.render();
		}
		renderPowerUp();
	}

	/**
	 * Defines the effect of that increases the movement speed.
	 */
	@Override
	final void effect() {
		setCurrentActive(true);
		GameVariables.setMovementSpeed(newSpeed);
	}

	/**
	 * Deactivates the effect by resetting the movement speed to the old
	 * movement speed.
	 */
	@Override
	public final void deactivate() {
		setCurrentActive(false);
		GameVariables.setMovementSpeed(oldSpeed);
		basicDeactivate();
	}

	/**
	 * Activates the power up and sets currentActive to true.
	 */
	@Override
	public final void activate() {
		setCurrentActive(true);
		basicActivate();

	}

	/**
	 * Sets the boolean currentActive.
	 * 
	 * @param bool
	 *            The boolean which currentActive is set to.
	 */
	private static void setCurrentActive(final boolean bool) {
		currentActive = bool;
	}

	/**
	 * Get boolean currentActive.
	 * 
	 * @return currentActive
	 */
	public static boolean isActive() {
		return currentActive;
	}
}
