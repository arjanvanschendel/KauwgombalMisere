package objects;

import java.awt.Color;
import shapes.Box;

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
	 * @param posx
	 *            x position
	 * @param posy
	 *            y position
	 * @param width
	 *            width of wall
	 * @param height
	 *            height of wall
	 * @param color
	 *            color of wall
	 */
	public Wall(float posx, float posy, float width, float height, Color color) {
		super(posx, posy, width, height, color);
	}

	/**
	 * Update method.
	 */
	@Override
	public void update(double deltaTime) {
		setPosx(getPosx() + (float) (deltaX * deltaTime));
		setPosy(getPosy() + (float) (deltaY * deltaTime));
	}

}
