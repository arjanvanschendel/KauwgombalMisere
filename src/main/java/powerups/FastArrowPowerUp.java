package powerups;

import game.GameVariables;
import game.Launcher;

import java.awt.Color;

import shapes.Box;
import shapes.Point;

/**
 * 
 * MovementPowerUp is a PowerUp that increases the MovementSpeed of the Player.
 * 
 * @author Jasper
 *
 */
public class FastArrowPowerUp extends PowerUp {

	/**
	 * newSpeed is the improved arrow speed.
	 */
	private float newSpeed = GameVariables.getFastArrowSpeed();
	/**
	 * oldSpeed is the basic arrow speed.
	 */
	private float oldSpeed = GameVariables.getNormalArrowSpeed();
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
	 * Constructor for FastArrowPowerUp.
	 * 
	 * @param pos
	 *            position of spawning powerup in Point format
	 */
	public FastArrowPowerUp(final Point pos) {
		super(pos, Color.CYAN);
		Point iPos = new Point(Launcher.getCamWidth() / 2 - 40,
				Launcher.getCamHeight() - 55);
		indicator = new Box(iPos, 20, 20, Color.CYAN);
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
	 * Defines the effect of that increases the arrow speed.
	 */
	@Override
	final void effect() {
		setCurrentActive(true);
		GameVariables.setArrowSpeed(newSpeed);
	}

	/**
	 * Deactivates the effect by resetting the arrow speed to the old arrow
	 * speed.
	 */
	@Override
	public final void deactivate() {
		setCurrentActive(false);
		GameVariables.setArrowSpeed(oldSpeed);
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
