package utillities;

import java.awt.Color;

import objects.Ball;
import objects.Player;
import objects.Wall;

/**
 * Parse objects.
 * 
 * @author
 *
 */
public final class ObjectGenerator {

	private ObjectGenerator() {

	}

	/**
	 * Generate player.
	 * 
	 * @param args
	 *            arguments
	 * @return player
	 */
	public static Player genPlayer(String[] args) {
		Player res = new Player(Float.parseFloat(args[0]),
				Float.parseFloat(args[1]));
		return res;
	}

	/**
	 * Generate wall.
	 * 
	 * @param args
	 *            arguments
	 * @return wall
	 */
	public static Wall genWall(String[] args) {
		Wall res = new Wall(Float.parseFloat(args[0]),
				Float.parseFloat(args[1]), Float.parseFloat(args[2]),
				Float.parseFloat(args[3]), new Color(Float.parseFloat(args[4]),
						Float.parseFloat(args[5]), Float.parseFloat(args[6])));
		return res;
	}

	/**
	 * Generate ball.
	 * 
	 * @param args
	 *            arguments
	 * @return ball
	 */
	public static Ball genBall(String[] args) {
		Ball res = new Ball(Float.parseFloat(args[0]),
				Float.parseFloat(args[1]), Float.parseFloat(args[2]));
		return res;
	}
}
