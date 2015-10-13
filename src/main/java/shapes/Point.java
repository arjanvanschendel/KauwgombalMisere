package shapes;

/**
 * 
 * @author Jasper
 *
 */
public class Point {
	private float x;
	private float y;

	/**
	 * 
	 * @param x
	 *            x position
	 * @param y
	 *            y position
	 */
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * getX.
	 * 
	 * @return float x
	 */
	public float getX() {
		return x;
	}

	/**
	 * setX.
	 * 
	 * @param x
	 *            float to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * getY.
	 * 
	 * @return float y
	 */
	public float getY() {
		return y;
	}

	/**
	 * setY.
	 * 
	 * @param y
	 *            float to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Dot: returns the dot product of two vectors/points.
	 * 
	 * @param d
	 *            point
	 * @return float
	 */
	public float dot(Point d) {
		return x * d.getX() + y * d.getY();
	}
}
