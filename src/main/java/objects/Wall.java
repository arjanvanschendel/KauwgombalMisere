package objects;

import java.awt.Color;

import shapes.Box;
import shapes.Point;

/**
 * Class that represents wall.
 * 
 * @author Luke
 *
 */
public class Wall extends Box implements GameObject {

	private float deltaY = 0;
	private float deltaX = 0;

	/**
	 * 
	 * @param pos
	 *            position of the wall in Point format
	 * @param width
	 *            width of wall
	 * @param height
	 *            height of wall
	 * @param color
	 *            color of wall
	 */
	public Wall(Point pos, float width, float height, Color color) {
		super(pos, width, height, color);
	}

	/**
	 * Update method.
	 */
	@Override
	public void update(double deltaTime) {
		setPosX(getPosX() + (float) (deltaX * deltaTime));
		setPosY(getPosY() + (float) (deltaY * deltaTime));
	}

}
