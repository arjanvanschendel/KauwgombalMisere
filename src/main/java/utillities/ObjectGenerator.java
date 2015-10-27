package utillities;

import java.awt.Color;
import java.util.ArrayList;

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
	public static Player genPlayer(ArrayList<String> args) {
		Point pos = new Point(Float.parseFloat(args.get(0)),
				Float.parseFloat(args.get(1)));
		Player res = new Player(pos);
		return res;
	}

	/**
	 * Generate wall.
	 * 
	 * @param para
	 *            arguments
	 * @return wall
	 */
	public static Wall genWall(ArrayList<String> para) {
		Point pos = new Point(Float.parseFloat(para.get(0)),
				Float.parseFloat(para.get(1)));
		Wall res = new Wall(pos, Float.parseFloat(para.get(2)),
				Float.parseFloat(para.get(3)), new Color(Float.parseFloat(para
						.get(4)), Float.parseFloat(para.get(5)),
						Float.parseFloat(para.get(6))));
		return res;
	}

	/**
	 * Generate ball.
	 * 
	 * @param args
	 *            arguments
	 * @return ball
	 */
	public static Ball genBall(ArrayList<String> args) {
		Point pos = new Point(Float.parseFloat(args.get(0)),
				Float.parseFloat(args.get(1)));
		Ball res = new Ball(pos, Float.parseFloat(args.get(2)),
				Float.parseFloat(args.get(3)));
		return res;
	}
}
