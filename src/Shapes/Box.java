package Shapes;

import java.awt.Color;
import java.util.ArrayList;

import Interfaces.Collider;
import Interfaces.RenderAble;
import static org.lwjgl.opengl.GL11.*;

public class Box implements RenderAble, Collider {
	protected float posx;
	protected float posy;
	protected float width;
	protected float height;
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

	public void update() {
		corners[0] = new Point(posx, posy);
		corners[1] = new Point(posx + width, posy);
		corners[2] = new Point(posx + width, posy + height);
		corners[3] = new Point(posx, posy + height);
	}

	public void render() {
		glBegin(GL_QUADS);
		glColor3f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255);
		glVertex2f(corners[0].getX(), corners[0].getY());
		glVertex2f(corners[1].getX(), corners[1].getY());
		glVertex2f(corners[2].getX(), corners[2].getY());
		glVertex2f(corners[3].getX(), corners[3].getY());
		glEnd();
	}

	public boolean pointInShape(Point p) {
		if (p.getY() >= posy && p.getY() <= posy + height && p.getX() > posx && p.getX() < posx + width) {
			return true;
		}
		return false;
	}

	@Override
	public int collide(Collider col) {
		if (col instanceof Box) {
			Box Box = (Box) col;
			float AX1 = this.posx;
			float AX2 = this.posx + this.width;
			float AY1 = this.posy;
			float AY2 = this.posy + this.height;

			float BX1 = Box.getPosx();
			float BX2 = Box.getPosx() + Box.getWidth();
			float BY1 = Box.getPosy();
			float BY2 = Box.getPosy() + Box.getHeight();

			if (AX1 < BX2 && AX2 > BX1 && AY1 < BY2 && AY2 > BY1) {
				float[] distances = new float[4];

				distances[0] = Math.abs(AY1 - BY2); // Hit ceiling
				distances[1] = Math.abs(AX1 - BX2); // Hit left of wall
				distances[2] = Math.abs(BY1 - AY2); // Hit floor
				distances[3] = Math.abs(BX1 - AX2); // Hit Right of wall

				float smallest = distances[0];
				int index = 0;
				for (int i = 0; i < 4; i++) {
					float num = distances[i];
					if (num < smallest) {
						smallest = num;
						index = i;
					}
				}
				return index + 1;
			}
		}
		return -1;
	}

	public float getPosx() {
		return posx;
	}

	public void setPosx(float posx) {
		this.posx = posx;
	}

	public float getPosy() {
		return posy;
	}

	public void setPosy(float posy) {
		this.posy = posy;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
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
