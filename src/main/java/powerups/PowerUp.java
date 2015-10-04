package powerups;

import game.Collision;
import game.CollisionDetection;
import game.GameVariables;
import game.Level;

import java.awt.Color;
import java.util.ArrayList;

import objects.GameObject;
import objects.Player;
import objects.Projectile;
import objects.Wall;
import shapes.Box;
import utillities.Logger;

/**
 * Abstract class PowerUp is used for the basic functionality in all other
 * PowerUps.
 * 
 * @author Jasper
 *
 */
public abstract class PowerUp extends Box implements GameObject {

	/**
	 * The deltaY with which the PowerUp is falling.
	 */
	private float deltaY = 0;
	/**
	 * The duration of the PowerUp(standard 5).
	 */
	protected float powerDuration = 5;
	/**
	 * The time for which the PowerUp is active.
	 */
	private static float timeRemaining = 0;
	/**
	 * Boolean used to activate the PowerUp.
	 */
	private boolean active = false;

	/**
	 * Constructor for a basic PowerUp.
	 * 
	 * @param posx x-coordinate from which the PowerUp spawns
	 * @param posy y-coordinate from which the PowerUp spawns
	 * @param color color of the PowerUp.
	 */
	public PowerUp(final float posx, final float posy, final Color color) {
		super(posx, posy, 20, 20, color);
	}

	@Override
	public final void update(final double deltaTime) {
		if (active) {
			if (timeRemaining > 0) {
				timeRemaining -= deltaTime;
				effect();
			} else {
				deactivate();
			}
		} else {

			deltaY -= (float) (deltaTime * GameVariables.getGravity());

			ArrayList<Collision> collisions = CollisionDetection
					.collision(this);
			for (Collision coll : collisions) {
				if (coll.getCol() instanceof Projectile
						|| coll.getCol() instanceof Player) {
					activate();
				} else if (coll.getCol() instanceof Wall) {
					if (coll.getSide() == 1) {
						deltaY = 0;
						setPosy(((Wall) coll.getCol()).getPosy()
								+ ((Wall) coll.getCol()).getHeight());
					}

				}
			}
			setPosy((float) (getPosy() + 30 * deltaY * deltaTime));
		}
	}

	
	@Override
	public final void render() {
		if (!active) {
			super.render();
		}
	}
	
	/**
	 * activate: Activates the PowerUp.
	 */
	protected final void activate() {
		Logger.add("powerup activated");
		timeRemaining = powerDuration;
		active = true;
	}

	/**
	 * Abstract method used to deactivate the PowerUp.
	 */
	public abstract void deactivate();

	/**
	 * basicDeactivate: Resets main variables used by every PowerUp.
	 */
	protected final void basicDeactivate() {
		Logger.add("powerup deactivated");
		active = false;
		timeRemaining = powerDuration;
		Level.remove(this);
	}
	/**
	 * The effect the PowerUp has, needs to be implemented per PowerUp.
	 */
	abstract void effect();

}
