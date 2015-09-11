package shapes;

import java.awt.Color;
import static org.lwjgl.opengl.GL11.*;

public class Circle {
	protected float posx;
	protected float posy;
	protected float radius;
	protected Color color;

	public Circle(float posx, float posy, float radius, Color color) {
		this.posx = posx;
		this.posy = posy;
		this.radius = radius;
		this.color = color;
	}

	public void render() {
		// Draw Circle
		glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255,
				(float) color.getBlue() / 255, 1.0f);
		glBegin(GL_POLYGON);

		for (double i = 0; i < 2 * Math.PI; i += Math.PI / 12)
			glVertex2f((float) Math.cos(i) * radius + posx, (float) Math.sin(i)
					* radius + posy);
		glEnd();
	}

	@Override
	public boolean equals(Object that) {

		if (that instanceof Circle) {
			Circle circle2 = (Circle) that;
			if (circle2.getColor().equals(color) && circle2.getPosx() == posx
					&& circle2.getPosy() == posy
					&& circle2.getRadius() == radius) {
				return true;
			}
		}

		return false;
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

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
