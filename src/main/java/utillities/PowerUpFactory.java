package utillities;

import java.util.Random;

import powerups.ExtraLifePowerUp;
import powerups.FastArrowPowerUp;
import powerups.MovementPowerUp;
import powerups.PowerUp;
import powerups.SlowBallPowerUp;
import shapes.Point;

/**
 * This object is a factory for powerups.
 * @author Luke
 *
 */
public class PowerUpFactory {
	
	/**
	 * Choose random powerup and return this to the caller.
	 * @param c spawn position as Point object
	 * @return powerup of any type
	 */
	public PowerUp getPowerUp(Point c, Random r) {
		Point pos = new Point(c);
		double p = r.nextDouble();
		if (p > 0.5) {
			return new FastArrowPowerUp(pos);
		} else if (p < 0.125) {
			return new SlowBallPowerUp(pos);
		} else if (p >= 0.375 && p <= 0.5) {
			return new ExtraLifePowerUp(pos);
		} else if (p >= 0.125 && p < 0.375) {
			return new MovementPowerUp(pos);
		} else {
			return new MovementPowerUp(pos);
		}
	}

}
