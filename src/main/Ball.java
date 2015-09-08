package main;

import java.awt.Color;
import java.util.ArrayList;

import shapes.Box;
import shapes.Circle;
import shapes.Point;
import interfaces.Collider;
import interfaces.RenderAble;
import interfaces.UpdateAble;

/**
 * Class Ball: an object of this class represents a bouncing ball in the game.
 * They can either get destroyed or kill the player.
 *
 */
public class Ball extends Circle implements RenderAble, UpdateAble, Collider {
	private float deltaX = 2;
	private float deltaY = 0;
	private float height;

	/**
	 * Ball: constructor.
	 * 
	 * @param posx
	 * @param posy
	 * @param radius
	 * @param color
	 */
	public Ball(float posx, float posy, float radius, Color color) {
		super(posx, posy, radius, color);
		height = posy;
	}

	/**
	 * update: update the ball's state.
	 */
	public void update(double deltaTime) {
		deltaY -= deltaTime * 9.81;
		posx += deltaX;
		posy += deltaY;
		ArrayList<Collision> collisions = CollisionDetection.collision(this);

		if (!collisions.isEmpty()) {
			for (Collision collision : collisions) {
				if (!(collision.getCol() instanceof Projectile || collision
						.getCol() instanceof Player)) {
					if (collision.getSide() == 3) {
						posy = ((Box) collision.getCol()).getPosy()
								+ ((Box) collision.getCol()).getHeight()
								+ radius;
						float time = (float) Math.sqrt(height / (0.5 * 9.81));
						deltaY = (float) (9.81 * time / 10);
						posy += deltaY;
					} else if (collision.getSide() == 2) {
						posx = ((Box) collision.getCol()).getPosx()
								+ ((Box) collision.getCol()).getWidth()
								+ radius;
						deltaX = -deltaX;
						posx += deltaX;
					} else if (collision.getSide() == 4) {
						posx = ((Box) collision.getCol()).getPosx() - radius;
						deltaX = -deltaX;
						posx += deltaX;
					}
				}
			}
		}

	}

	void hit() {
		Level.remove(this);
		Ball ball = new Ball(posx, posy, radius / 2, new Color(
				color.getGreen(), color.getBlue(), color.getRed()));
		Ball ball2 = new Ball(posx, posy, radius / 2, new Color(
				color.getGreen(), color.getBlue(), color.getRed()));
		if (ball.getRadius() > 10) {
			ball2.deltaX = -deltaX;
			ball.deltaX = deltaX;
			ball.height = height - height / 3;
			ball2.height = height - height / 3;
			ball.deltaY = 5;
			ball2.deltaY = 5;
			Level.addBall(ball);
			Level.addBall(ball2);
		}

	}

	/**
	 * render: Render ball graphics.
	 */
	public void render() {
		super.render();
	}

	@Override
	public boolean equals(Object that) {
		if (that instanceof Ball && super.equals(that)) {
			Ball ball2 = (Ball) that;
			if (ball2.getDeltaX() == deltaX && ball2.getDeltaY() == deltaY) {
				return true;
			}
		}
		return false;
	}

	// Getters and setters
	/**
	 * getDeltaX.
	 * 
	 * @return float deltaX
	 */
	public float getDeltaX() {
		return deltaX;
	}

	/**
	 * setDeltaX.
	 */
	public void setDeltaX(float deltaX) {
		this.deltaX = deltaX;
	}

	/**
	 * getDeltaY.
	 * 
	 * @return float deltaY
	 */
	public float getDeltaY() {
		return deltaY;
	}

	/**
	 * setDeltaY.
	 */
	public void setDeltaY(float deltaY) {
		this.deltaY = deltaY;
	}

	@Override
	public int collide(Collider col) {
		if (col instanceof Box) {
			Box box = (Box) col;
			Point[] corners = box.getCorners();
			if (lineIntersect(corners[0], corners[1])) {
				return 1;
			} else if (lineIntersect(corners[1], corners[2])) {
				return 2;
			} else if (lineIntersect(corners[2], corners[3])) {
				return 3;
			} else if (lineIntersect(corners[3], corners[0])) {
				return 4;
			}
		}
		return 0;
	}

}
