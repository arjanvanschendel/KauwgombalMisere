package objects;

import game.Collision;
import game.CollisionDetection;
import game.Game;
import game.GameVariables;
import game.Level;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import powerups.ExtraLifePowerUp;
import powerups.FastArrowPowerUp;
import powerups.SlowBallPowerUp;
import powerups.MovementPowerUp;
import shapes.Box;
import shapes.Circle;
import shapes.Point;
import utillities.Logger;

/**
 * Class Ball: an object of this class represents a bouncing ball in the game.
 * They can either get destroyed or kill the player.
 *
 */
public class Ball extends Circle implements GameObject {

	/**
	 * Get current game instance.
	 */
	private Game game = Game.getInstance();
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

	// private ScorePopUp popUp;

	/**
	 * Ball: constructor.
	 * 
	 * @param pos
	 *            the starting position of the circle in Point format
	 * @param radius
	 *            radius of the ball
	 */
	public Ball(final Point pos, final float radius) {
		super(pos, radius);

		switch ((int) radius) {
		case 50:
			setColor(Color.red);
			break;
		case (50 / 2):
			setColor(Color.blue);
			break;
		case (50 / 4):
			setColor(Color.green);
			break;
		default:
			setColor(Color.green);
			break;
		}
		height = getPosY();
	}

	/**
	 * update: update the ball's state.
	 * 
	 * @param updateTime
	 *            time between last and current frame
	 */
	public final void update(final double updateTime) {
		double deltaTime = (double) (updateTime * GameVariables.getBallSpeed());
		deltaY -= deltaTime * GameVariables.getGravity();
		setPosX((float) (getPosX() + deltaX * 60 * deltaTime));
		setPosY((float) (getPosY() + deltaY * 60 * deltaTime));
		ArrayList<Collision> collisions = CollisionDetection.collision(this);

		if (!collisions.isEmpty()) {
			for (Collision collision : collisions) {
				if (collision.getCol() instanceof Wall) {
					if (collision.getSide() == 1) {
						setPosY(((Box) collision.getCol()).getPosY()
								+ ((Box) collision.getCol()).getHeight()
								+ getRadius());
						float time = (float) Math.sqrt(height
								/ (GameVariables.getGravity() / 2));
						deltaY = (float) (GameVariables.getGravity() * time / 10);
						setPosY((float) (getPosY() + deltaY * 60 * deltaTime));

						game.getSounds().get(3).play();

					} else if (collision.getSide() == 2) {
						setPosX(((Box) collision.getCol()).getPosX()
								+ ((Box) collision.getCol()).getWidth()
								+ getRadius());
						deltaX = -deltaX;
						setPosX((float) (getPosX() + deltaX * 60 * deltaTime));

						game.getSounds().get(3).play();

					} else if (collision.getSide() == 4) {
						setPosX(((Box) collision.getCol()).getPosX()
								- getRadius());
						deltaX = -deltaX;
						setPosX((float) (getPosX() + deltaX * 60 * deltaTime));

						game.getSounds().get(3).play();
					}
				}
			}
		}

	}

	/**
	 * Hit method is called when a projectile hits the ball.
	 */
	public final void hit() {
		this.updateScore();
		Logger.add("ball hit");
		Point p = new Point(getPosX(), getPosY());
		ScorePopUp popUp = new ScorePopUp(this.getPosX(), this.getPosY(),
				this.getRadius());
		Level.addPopUp(popUp);
		Level.remove(this);
		
		Ball ball = new Ball(p, getRadius() / 2);
		Ball ball2 = new Ball(new Point(p), getRadius() / 2);	
		if (ball.getRadius() > 10) {
			Logger.add("ball splits");
			ball2.setDeltaX(-deltaX);
			ball.setDeltaX(deltaX);
			ball.setHeight(height - height / 3);
			ball2.setHeight(height - height / 3);
			ball.setDeltaY(GameVariables.getGravity() / 3);
			ball2.setDeltaY(GameVariables.getGravity() / 3);
			Level.addBall(ball);
			Level.addBall(ball2);
		}
		dropPowerUp();
	}

	/**
	 * dropPowerUp: Randomly decides if a powerup is dropped and if so which
	 * one.
	 */
	final void dropPowerUp() {
		Random rand = new Random();
		double randomNum = rand.nextDouble();
		if (randomNum > 0.5) {
			double randomNum2 = rand.nextDouble();
			Point pos = new Point(getPosX(), getPosY());
			if (randomNum2 > 0.5) {
				Level.addPowerUp(new FastArrowPowerUp(pos));
			} else if (randomNum2 < 0.125) {
				Level.addPowerUp(new SlowBallPowerUp(pos));
			} else if (randomNum2 >= 0.375 && randomNum2 <= 0.5) {
				Level.addPowerUp(new ExtraLifePowerUp(pos));
			} else if (randomNum2 >= 0.125 && randomNum2 < 0.375) {
				Level.addPowerUp(new MovementPowerUp(pos));
			}
		}
	}

	/**
	 * updateScore: Adds points to the score board depending on ball size.
	 */
	final void updateScore() {

		// game.ballHit(this.getPosX(), this.getPosY(), ballsize); is this being
		// used?
		if (this.getRadius() > 20) {
			game.setScore(game.getScore() + 20);
		} else {
			game.setScore(game.getScore() + 10);
		}

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
			if (compareFloats(deltaX, ball2.getDeltaX())
					&& compareFloats(deltaY, ball2.getDeltaY())) {
				return super.equals(that);
			}
		}
		return false;
	}

	/**
	 * Hashcode not used.
	 */
	@Override
	public final int hashCode() {
		assert false : "hashCode not designed";
		return 42;
	}

	/**
	 * get DeltaX.
	 * 
	 * @return float deltaX
	 */
	public final float getDeltaX() {
		return deltaX;
	}

	/**
	 * set DeltaX.
	 * 
	 * @param deltaX
	 *            float to set
	 */
	public final void setDeltaX(float deltaX) {
		this.deltaX = deltaX;
	}

	/**
	 * get DeltaY.
	 * 
	 * @return float deltaY
	 */
	public float getDeltaY() {
		return deltaY;
	}

	/**
	 * set DeltaY.
	 * 
	 * @param deltaY
	 *            float to set
	 */
	public void setDeltaY(float deltaY) {
		this.deltaY = deltaY;
	}

	/**
	 * @return the height
	 */
	public final float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public final void setHeight(float height) {
		this.height = height;
	}

}
