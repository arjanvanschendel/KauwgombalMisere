package powerups;

import game.Game;
import game.GameVariables;
import game.Launcher;

import java.awt.Color;

import shapes.Box;
import shapes.Point;
import shapes.TextureBox;

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
	 * @param pos
	 *            position of spawning powerup in Point format
	 */
	public MovementPowerUp(final Point pos) {
		super(pos, Color.GREEN);
		setTexture(Game.getInstance().getTextures().get("Movement"));
		Point iPos = new Point(Launcher.getCamWidth() / 2 - 40,
				Launcher.getCamHeight() - 30);
		indicator = new TextureBox(iPos, 20, 20, Color.GREEN, getTexture());
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
