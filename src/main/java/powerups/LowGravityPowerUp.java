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
public class LowGravityPowerUp extends PowerUp {

	/**
	 * newGravity is the low gravity.
	 */
	private float newGravity = GameVariables.getLowGravity();
	/**
	 * oldGravity is the basic gravity in the level.
	 */
	private float oldGravity = GameVariables.getNormalGravity();
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
	public LowGravityPowerUp(final float posx, final float posy) {
		super(posx, posy, Color.MAGENTA);
		
		if (currentActive) {
			deactivate();
		}
		indicator = new Box(Launcher.getCAMWIDTH() / 2 - 40,
				Launcher.getCAMHEIGHT() - 55, 20, 20, Color.MAGENTA);
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
	 * Defines the effect of that lowers the gravity.
	 */
	@Override
	final void effect() {
		setCurrentActive(true);
		GameVariables.setGravity(newGravity);
	}
	
	/**
	 * Deactivates the effect by resetting the gravity to the old
	 * gravity.
	 */
	@Override
	public final void deactivate() {
		setCurrentActive(false);
		GameVariables.setGravity(oldGravity);
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
