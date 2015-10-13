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
	 * @param posx
	 *            X position
	 * @param posy
	 *            Y position
	 * @param radius
	 *            radius of the ball
	 */
	public Ball(final float posx, final float posy, final float radius) {
		super(posx, posy, radius);

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
		height = posy;
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
		setPosx((float) (getPosx() + deltaX * 60 * deltaTime));
		setPosy((float) (getPosy() + deltaY * 60 * deltaTime));
		ArrayList<Collision> collisions = CollisionDetection.collision(this);

		if (!collisions.isEmpty()) {
			for (Collision collision : collisions) {
				if (collision.getCol() instanceof Wall) {
					if (collision.getSide() == 1) {
						setPosy(((Box) collision.getCol()).getPosy()
								+ ((Box) collision.getCol()).getHeight()
								+ getRadius());
						float time = (float) Math.sqrt(height
								/ (GameVariables.getGravity() / 2));
						deltaY = (float) (GameVariables.getGravity() * time / 10);
						setPosy((float) (getPosy() + deltaY * 60 * deltaTime));

						game.getSounds().get(3).play();

					} else if (collision.getSide() == 2) {
						setPosx(((Box) collision.getCol()).getPosx()
								+ ((Box) collision.getCol()).getWidth()
								+ getRadius());
						deltaX = -deltaX;
						setPosx((float) (getPosx() + deltaX * 60 * deltaTime));

						game.getSounds().get(3).play();

					} else if (collision.getSide() == 4) {
						setPosx(((Box) collision.getCol()).getPosx()
								- getRadius());
						deltaX = -deltaX;
						setPosx((float) (getPosx() + deltaX * 60 * deltaTime));

						game.getSounds().get(3).play();
					}
				}
			}
		}

	}

	/**
	 * Hit method is called when a projectile hits the ball.
	 */
	final void hit() {
		this.updateScore();
		Logger.add("ball hit");
		ScorePopUp popUp = new ScorePopUp(this.getPosx(), this.getPosy(),
				this.getRadius());
		Level.addPopUp(popUp);
		Level.remove(this);
		Ball ball = new Ball(getPosx(), getPosy(), getRadius() / 2);
		Ball ball2 = new Ball(getPosx(), getPosy(), getRadius() / 2);
		if (ball.getRadius() > 10) {
			Logger.add("ball splits");

			ball2.deltaX = -deltaX;
			ball.deltaX = deltaX;
			ball.height = height - height / 3;
			ball2.height = height - height / 3;
			ball.deltaY = GameVariables.getGravity() / 3;
			ball2.deltaY = GameVariables.getGravity() / 3;
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
			if (randomNum2 > 0.5) {
				Level.addPowerUp(new FastArrowPowerUp(getPosx(), getPosy()));
			} else if (randomNum2 < 0.125) {
				Level.addPowerUp(new SlowBallPowerUp(getPosx(), getPosy()));
			} else if (randomNum2 >= 0.375 && randomNum2 <= 0.5) {
				Level.addPowerUp(new ExtraLifePowerUp(getPosx(), getPosy()));
			} else if (randomNum2 >= 0.125 && randomNum2 < 0.375) {
				Level.addPowerUp(new MovementPowerUp(getPosx(), getPosy()));
			}
		}
	}

	/**
	 * updateScore: Adds points to the score board depending on ball size.
	 */
	final void updateScore() {

		// game.ballHit(this.getPosx(), this.getPosy(), ballsize); is this being
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

}
