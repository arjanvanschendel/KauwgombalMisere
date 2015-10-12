package shapes;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Color;

/**
 * Box class.
 * 
 * @author Jasper
 *
 */
public class Box {
	private float posx;
	private float posy;
	private float width;
	private float height;
	private Point[] corners = new Point[4];
	private Color color;

	/**
	 * 
	 * @param posx
	 *            x position
	 * @param posy
	 *            y position
	 * @param width
	 *            width of box
	 * @param height
	 *            height of box
	 * @param color
	 *            color of box
	 */
	public Box(float posx, float posy, float width, float height, Color color) {
		this.posx = posx;
		this.posy = posy;
		this.width = width;
		this.height = height;
		this.color = color;
		corners[0] = new Point(posx, posy);
		corners[1] = new Point(posx + width, posy);
		corners[2] = new Point(posx + width, posy + height);
		corners[3] = new Point(posx, posy + height);
	}

	/**
	 * Render method.
	 */
	public void render() {
		glColor3f((float) color.getRed() / 255, (float) color.getGreen() / 255,
				(float) color.getBlue() / 255);
		glBegin(GL_QUADS);
		glVertex2f(corners[0].getX(), corners[0].getY());
		glVertex2f(corners[1].getX(), corners[1].getY());
		glVertex2f(corners[2].getX(), corners[2].getY());
		glVertex2f(corners[3].getX(), corners[3].getY());
		glEnd();
	}

	/**
	 * Determine if point is located in shape.
	 * 
	 * @param p
	 *            point in shape
	 * @return true if point is in shape
	 */
	public boolean pointInShape(Point p) {
		if (p.getY() >= posy && p.getY() <= posy + height && p.getX() > posx
				&& p.getX() < posx + width) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return x position
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
		corners[0].setX(posx);
		corners[1].setX(posx + width);
		corners[2].setX(posx + width);
		corners[3].setX(posx);
		this.posx = posx;
	}

	/**
	 * 
	 * @return y position
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
		corners[0].setY(posy);
		corners[1].setY(posy);
		corners[2].setY(posy + height);
		corners[3].setY(posy + height);
		this.posy = posy;
	}

	/**
	 * 
	 * @return width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * 
	 * @param width
	 *            width to set
	 */
	public void setWidth(float width) {
		corners[1].setX(posx + width);
		corners[2].setX(posx + width);
		this.width = width;
	}

	/**
	 * 
	 * @return height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * 
	 * @param height
	 *            float to set
	 */
	public void setHeight(float height) {
		corners[2].setY(posy + height);
		corners[3].setY(posy + height);
		this.height = height;
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

	/**
	 * 
	 * @return corners of box
	 */
	public Point[] getCorners() {
		return corners.clone();
	}

}
