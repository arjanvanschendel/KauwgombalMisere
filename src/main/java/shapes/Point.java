package shapes;

/**
 * 
 * JAPSEEER
 *
 */
public class Point {
	private float x;
	private float y;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * getX.
	 * @return float x
	 */
	public float getX() {
		return x;
	}

	/**
	 * setX.
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * getY.
	 * @return float y
	 */
	public float getY() {
		return y;
	}

	/**
	 * setY.
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Dot: returns the dot product of two vectors/points.
	 * @param d
	 * @return float
	 */
	public float dot(Point d) {
		return x * d.getX() + y * d.getY();
	}
}
