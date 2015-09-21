package objects;

import game.Collision;
import game.CollisionDetection;
import game.Level;

import java.awt.Color;
import java.util.ArrayList;

import shapes.Box;
import shapes.Circle;

/**
 * Class Ball: an object of this class represents a bouncing ball in the game.
 * They can either get destroyed or kill the player.
 *
 */
public class Ball extends Circle implements GameObject {
	/**
	 * deltaX the force in the X direction.
	 */
	private float deltaX = 2;

	/**
	 * deltaY the force in the Y direction.
	 */
	private float deltaY = 0;

	/**
	 * Height the ball bounces to.
	 */
	private float height;

	/**
	 * Ball: constructor.
	 * 
	 * @param posx
	 *            X position
	 * @param posy
	 *            Y position
	 * @param radius
	 *            radius of the ball
	 * @param color
	 *            color of the ball
	 */
	public Ball(final float posx, final float posy, final float radius) {
		super(posx, posy, radius);
		
		switch ((int) radius) {
		case 50: color = Color.red; break;
		case (50/2): color = Color.blue; break;
		case (50/4): color = Color.green; break;
		}
		
		height = posy;
	}

	/**
	 * update: update the ball's state.
	 * 
	 * @param deltaTime
	 *            time between last and current frame
	 */
	public final void update(final double deltaTime) {
		deltaY -= deltaTime * Level.getGravity();
		posx += deltaX * 60 * deltaTime;
		posy += deltaY * 60 * deltaTime;
		ArrayList<Collision> collisions = CollisionDetection.collision(this);

		if (!collisions.isEmpty()) {
			for (Collision collision : collisions) {
				if (!(collision.getCol() instanceof Projectile || collision
						.getCol() instanceof Player)) {
					if (collision.getSide() == 1) {
						posy = ((Box) collision.getCol()).getPosy()
								+ ((Box) collision.getCol()).getHeight()
								+ radius;
						float time = (float) Math.sqrt(height
								/ (Level.getGravity() / 2));
						deltaY = (float) (Level.getGravity() * time / 10);
						posy += deltaY * 60 * deltaTime;
					} else if (collision.getSide() == 2) {
						posx = ((Box) collision.getCol()).getPosx()
								+ ((Box) collision.getCol()).getWidth()
								+ radius;
						deltaX = -deltaX;
						posx += deltaX * 60 * deltaTime;
					} else if (collision.getSide() == 4) {
						posx = ((Box) collision.getCol()).getPosx() - radius;
						deltaX = -deltaX;
						posx += deltaX * 60 * deltaTime;
					}
				}
			}
		}

	}

	/**
	 * Hit method is called when a projectile hits the ball.
	 */
	final void hit() {
		Level.remove(this);
		Ball ball = new Ball(posx, posy, radius / 2);
		Ball ball2 = new Ball(posx, posy, radius / 2);
		if (ball.getRadius() > 10) {
			ball2.deltaX = -deltaX;
			ball.deltaX = deltaX;
			ball.height = height - height / 3;
			ball2.height = height - height / 3;
			ball.deltaY = Level.getGravity() / 3;
			ball2.deltaY = Level.getGravity() / 3;
			Level.addBall(ball);
			Level.addBall(ball2);
		}
		System.out.println(Level.levelComplete());

	}

	/**
	 * render: Render ball graphics.
	 */
	public final void render() {
		super.render();
	}

	/**
	 * 
	 * @param that
	 * @return boolean
	 */
	@Override
	public final boolean equals(final Object that) {
		if (that instanceof Ball && super.equals(that)) {
			Ball ball2 = (Ball) that;
			if (ball2.getDeltaX() == deltaX && ball2.getDeltaY() == deltaY) {
				return super.equals(that);
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
	public final float getDeltaX() {
		return deltaX;
	}

	/**
	 * setDeltaX.
	 * 
	 * @param float deltaX
	 */
	public final void setDeltaX(float deltaX) {
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

}
