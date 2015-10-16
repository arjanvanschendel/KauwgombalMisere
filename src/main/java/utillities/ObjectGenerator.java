package utillities;

import java.awt.Color;

import objects.Ball;
import objects.Player;
import objects.Wall;
import shapes.Point;

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
		Point pos = new Point(Float.parseFloat(args[0]), Float.parseFloat(args[1]));
		Player res = new Player(pos);
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
		Point pos = new Point(Float.parseFloat(args[0]), Float.parseFloat(args[1]));
		Wall res = new Wall(pos, Float.parseFloat(args[2]),
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
		Point pos = new Point(Float.parseFloat(args[0]), Float.parseFloat(args[1]));
		Ball res = new Ball(pos, Float.parseFloat(args[2]));
		return res;
	}
}
