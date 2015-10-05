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
public class FastArrowPowerUp extends PowerUp {

	/**
	 * newSpeed is the improved movement speed.
	 */
	private float newSpeed = GameVariables.getFastArrowSpeed();
	/**
	 * oldSpeed is the basic movement speed.
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
	 * Constructor for MovementPowerUp.
	 * 
	 * @param posx
	 *            x-coordinate at which the PowerUp spawns.
	 * @param posy
	 *            y-coordinate at which the PowerUp spawns.
	 */
	public FastArrowPowerUp(final float posx, final float posy) {
		super(posx, posy, Color.CYAN);
		
		if (currentActive) {
			deactivate();
		}
		indicator = new Box(Launcher.getCAMWIDTH() / 2 - 40,
				Launcher.getCAMHEIGHT() - 55, 20, 20, Color.CYAN);
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
		GameVariables.setArrowSpeed(newSpeed);
	}
	
	/**
	 * Deactivates the effect by resetting the movement spped to the old
	 * movement speed.
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
	 * @param bool The boolean which currentActive is st to.
	 */
	private static void setCurrentActive(final boolean bool) {
		currentActive = bool;
	}
	
}
