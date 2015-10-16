package powerups;

import java.awt.Color;

import shapes.Box;
import shapes.Point;
import game.GameVariables;
import game.Launcher;

/**
 * 
 * ExtraLife is a PowerUp that increases the lives of the Player by one.
 * 
 * @author Jasper
 *
 */
public class ExtraLifePowerUp extends PowerUp {

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
	 * Constructor for ExtraLifePowerUp.
	 * 
	 * @param pos
	 *            position of spawning powerup in Point format
	 */
	public ExtraLifePowerUp(final Point pos) {
		super(pos, new Color(200, 70, 70));
		Point indPos = new Point(Launcher.getCAMWIDTH() / 2 - 40,
				Launcher.getCAMHEIGHT() - 55);
		indicator = new Box(indPos, 20, 20, new Color(200, 70, 70));
		setPowerDuration(0);
	}

	@Override
	public final void render() {
		if (currentActive) {
			indicator.render();
		}
		renderPowerUp();
	}

	/**
	 * Empty since Extra life PowerUp has a one time effect.
	 */
	@Override
	final void effect() {
		setCurrentActive(true);
	}

	/**
	 * Deactivates the PowerUp. speed.
	 */
	@Override
	public final void deactivate() {
		setCurrentActive(false);
		basicDeactivate();
	}

	/**
	 * Activates the power up and sets currentActive to true.
	 */
	@Override
	public final void activate() {
		setCurrentActive(true);
		GameVariables.setLives(GameVariables.getLives() + 1);
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
