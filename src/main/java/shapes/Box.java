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
	// private float posx;
	// private float posy;
	private float width;
	private float height;
	private Point[] corners = new Point[4];
	private Color color;

	private Point pos;

	// /**
	// *
	// * @param posx
	// * x position
	// * @param posy
	// * y position
	// * @param width
	// * width of box
	// * @param height
	// * height of box
	// * @param color
	// * color of box
	// */
	// public Box(float posx, float posy, float width, float height, Color
	// color) {
	// this.posx = posx;
	// this.posy = posy;
	// this.width = width;
	// this.height = height;
	// this.color = color;
	// corners[0] = new Point(posx, posy);
	// corners[1] = new Point(posx + width, posy);
	// corners[2] = new Point(posx + width, posy + height);
	// corners[3] = new Point(posx, posy + height);
	// }

	/**
	 * 
	 * @param pos
	 *            position of object in Point format
	 * @param width
	 *            width of box in float
	 * @param height
	 *            height of box in float
	 * @param color
	 *            color of box in color format
	 */
	public Box(Point pos, float width, float height, Color color) {
		setPos(pos);
		setCorners(pos, width, height);
		setWidth(width);
		setHeight(height);
		setColor(color);
		
	}

	private void setCorners(Point pos, float width, float height) {
		corners[0] = new Point(pos);
		corners[1] = new Point(pos.getX() + width, pos.getY());
		corners[2] = new Point(pos.getX() + width, pos.getY() + height);
		corners[3] = new Point(pos.getX(), pos.getY() + height);
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
		if (p.getY() >= pos.getY() && p.getY() <= pos.getY() + height
				&& p.getX() > pos.getX() && p.getX() < pos.getX() + width) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return x position
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
		corners[0].setX(x);
		corners[1].setX(x + width);
		corners[2].setX(x + width);
		corners[3].setX(x);
		this.pos.setX(x);
	}

	/**
	 * 
	 * @return y position
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
		corners[0].setY(y);
		corners[1].setY(y);
		corners[2].setY(y + height);
		corners[3].setY(y + height);
		this.pos.setY(y);
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
		corners[1].setX(pos.getX() + width);
		corners[2].setX(pos.getX() + width);
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
		corners[2].setY(pos.getY() + height);
		corners[3].setY(pos.getY() + height);
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

	/**
	 * 
	 * @param pos pos to set
	 */
	public void setPos(Point pos) {
		this.pos = pos;
	}

}
