package shapes;

import java.awt.Color;

import static org.lwjgl.opengl.GL11.*;

/**
 * 
 * JAPSEEER
 *
 */
public class Box {
    private float posx;
    private float posy;
    private float width;
    private float height;
    protected Point[] corners = new Point[4];
    private Color color;

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

    public boolean pointInShape(Point p) {
	if (p.getY() >= posy && p.getY() <= posy + height && p.getX() > posx
		&& p.getX() < posx + width) {
	    return true;
	}
	return false;
    }

    public float getPosx() {
	return posx;
    }

    public void setPosx(float posx) {

	corners[0].setX(posx);
	corners[1].setX(posx + width);
	corners[2].setX(posx + width);
	corners[3].setX(posx);
	this.posx = posx;
    }

    public float getPosy() {
	return posy;
    }

    public void setPosy(float posy) {

	corners[0].setY(posy);
	corners[1].setY(posy);
	corners[2].setY(posy + height);
	corners[3].setY(posy + height);
	this.posy = posy;
    }

    public float getWidth() {
	return width;
    }

    public void setWidth(float width) {
	corners[1].setX(posx + width);
	corners[2].setX(posx + width);
	this.width = width;
    }

    public float getHeight() {
	return height;
    }

    public void setHeight(float height) {
	corners[2].setY(posy + height);
	corners[3].setY(posy + height);
	this.height = height;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(Color color) {
	this.color = color;
    }

    public Point[] getCorners() {
	return corners;
    }

}
