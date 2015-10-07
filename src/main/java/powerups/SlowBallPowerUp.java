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
public class SlowBallPowerUp extends PowerUp {

	/**
	 * newGravity is the low gravity.
	 */
	private float newGravity = GameVariables.getSlowBallSpeed();
	/**
	 * oldGravity is the basic gravity in the level.
	 */
	private float oldGravity = GameVariables.getNormalBallSpeed();
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
	public SlowBallPowerUp(final float posx, final float posy) {
		super(posx, posy, Color.MAGENTA);

		indicator = new Box(Launcher.getCAMWIDTH() / 2 - 40,
				Launcher.getCAMHEIGHT() - 75, 20, 20, Color.MAGENTA);
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
	 * Defines the effect of that slows down the ball.
	 */
	@Override
	final void effect() {
		setCurrentActive(true);
		GameVariables.setBallSpeed(newGravity);
	}

	/**
	 * Deactivates the effect by resetting the ball speed to the old ball speed.
	 */
	@Override
	public final void deactivate() {
		setCurrentActive(false);
		GameVariables.setBallSpeed(oldGravity);
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
