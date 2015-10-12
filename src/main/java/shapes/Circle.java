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
	private float posx;
	private float posy;
	private float radius;
	private Color color;
	private static final float EPSILON = 0.000001f;

	/**
	 * 
	 * @param posx
	 *            x position
	 * @param posy
	 *            y position
	 * @param radius
	 *            radius circle
	 * @param color
	 *            color of circle
	 */
	public Circle(float posx, float posy, float radius, Color color) {
		this.posx = posx;
		this.posy = posy;
		this.radius = radius;
		this.color = color;
	}

	/**
	 * 
	 * @param posx
	 *            x position
	 * @param posy
	 *            y position
	 * @param radius
	 *            radius circle
	 */
	public Circle(float posx, float posy, float radius) {
		this.posx = posx;
		this.posy = posy;
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
			glVertex2f((float) Math.cos(i) * radius + posx, (float) Math.sin(i)
					* radius + posy);
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
					&& compareFloats(posx, circle2.getPosx())
					&& compareFloats(posy, circle2.getPosy())
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
	 * @return posx
	 */
	public float getPosx() {
		return posx;
	}

	/**
	 * 
	 * @param posx
	 *            float to set
	 */
	public void setPosx(float posx) {
		this.posx = posx;
	}

	/**
	 * 
	 * @return posy
	 */
	public float getPosy() {
		return posy;
	}

	/**
	 * 
	 * @param posy
	 *            float to set
	 */
	public void setPosy(float posy) {
		this.posy = posy;
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
