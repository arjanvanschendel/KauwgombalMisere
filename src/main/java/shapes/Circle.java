package shapes;

import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Color;

/**
 * Circle class.
 * 
 * @author Luke
 *
 */
public class Circle {
	
	private Point pos;
	private float radius;
	private Color color;
	private static final float EPSILON = 0.000001f;

	/**
	 * 
	 * @param pos
	 *            the starting position of the circle in Point format
	 * @param radius
	 *            radius circle
	 * @param color
	 *            color of circle
	 */
	public Circle(Point pos, float radius, Color color) {
		this.pos = pos;
		this.radius = radius;
		this.color = color;
	}

	/**
	 * 
	 * @param pos
	 *            the starting position of the circle in Point format
	 * @param radius
	 *            radius circle
	 */
	public Circle(Point pos, float radius) {
		this.pos = pos;
		this.radius = radius;
		this.color = new Color(0, 0, 0);
	}

	/**
	 * Render method.
	 */
	public void render() {
		// Draw Circle
		glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255,
				(float) color.getBlue() / 255, 1.0f);
		glBegin(GL_POLYGON);
		for (double i = 0; i < 2 * Math.PI; i += Math.PI / 12) {
			glVertex2f((float) Math.cos(i) * radius + pos.getX(),
					(float) Math.sin(i) * radius + pos.getY());
		}
		glEnd();

	}

	/**
	 * Compare floats properly.
	 * 
	 * @param x
	 *            float
	 * @param y
	 *            float
	 * @return true if equal, false if not
	 */
	protected boolean compareFloats(final float x, final float y) {
		return Math.abs(x - y) < EPSILON;
	}

	/**
	 * Equals method.
	 */
	@Override
	public boolean equals(Object that) {

		if (that instanceof Circle) {
			Circle circle2 = (Circle) that;
			if (circle2.getColor().equals(color)
					&& compareFloats(getPosX(), circle2.getPosX())
					&& compareFloats(getPosY(), circle2.getPosY())
					&& compareFloats(radius, circle2.getRadius())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Hashcode not used.
	 */
	@Override
	public int hashCode() {
		assert false : "hashCode not designed";
		return 42;
	}

	/**
	 * 
	 * @return x value of position
	 */
	public float getPosX() {
		return pos.getX();
	}

	/**
	 * 
	 * @param x
	 *            float to set
	 */
	public void setPosX(float x) {
		this.pos.setX(x);
	}

	/**
	 * 
	 * @return y value of position
	 */
	public float getPosY() {
		return pos.getY();
	}

	/**
	 * 
	 * @param y
	 *            float to set
	 */
	public void setPosY(float y) {
		this.pos.setY(y);
	}

	/**
	 * 
	 * @return radius
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * 
	 * @param radius
	 *            radius to set
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}

	/**
	 * 
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 
	 * @param color
	 *            color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
