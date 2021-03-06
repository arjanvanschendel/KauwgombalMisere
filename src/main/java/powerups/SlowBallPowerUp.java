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
	 * @param pos
	 *            position of spawning powerup in Point format
	 */
	public SlowBallPowerUp(final Point pos) {
		super(pos, Color.MAGENTA);
		setTexture(Game.getInstance().getTextures().get("SlowMotion"));
		Point iPos = new Point(Launcher.getCamWidth() / 2 - 40,
				Launcher.getCamHeight() - 75);
		indicator = new TextureBox(iPos, 20, 20, Color.MAGENTA, getTexture());
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
