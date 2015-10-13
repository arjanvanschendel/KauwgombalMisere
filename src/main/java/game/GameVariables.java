package game;

/**
 * GameVariables contains basic variables used in-game.
 * 
 * @author Jasper
 *
 */
public final class GameVariables {

	/**
	 * GameVariables constructor.
	 */
	private GameVariables() {

	}

	/**
	 * Current movement speed of the player.
	 */
	private static float movementSpeed = 5;
	/**
	 * Number of lives left.
	 */
	private static int lives = 3;
	/**
	 * Basic movement speed.
	 */
	private static float normalMovementSpeed = 5;
	/**
	 * Improved movement speed.
	 */
	private static float improvedMovementSpeed = (float) 7.5;
	/**
	 * Current arrow speed.
	 */
	private static float arrowSpeed = 500;

	/**
	 * Current arrow speed.
	 */
	private static float normalArrowSpeed = 500;

	/**
	 * Fast arrow speed.
	 */
	private static float fastArrowSpeed = 700;
	/**
	 * slow arrow speed.
	 */
	private static float slowArrowSpeed = 200;
	/**
	 * Current gravity.
	 */
	private static float gravity = (float) 9.81;
	/**
	 * Current ballSpeed.
	 */
	private static float ballSpeed = (float) 1;
	/**
	 * Normal ball speed.
	 */
	private static float normalBallSpeed = (float) 1;
	/**
	 * Slow ball speed.
	 */
	private static float slowBallSpeed = (float) 0.5;

	/**
	 * @return the normal Ball speed
	 */
	public static float getNormalBallSpeed() {
		return normalBallSpeed;
	}

	/**
	 * @return the slow Ball speed
	 */
	public static float getSlowBallSpeed() {
		return slowBallSpeed;
	}

	/**
	 * @return the arrowSpeed
	 */
	public static float getArrowSpeed() {
		return arrowSpeed;
	}

	/**
	 * @return the normalArrowSpeed
	 */
	public static float getNormalArrowSpeed() {
		return normalArrowSpeed;
	}

	/**
	 * @return the fastArrowSpeed
	 */
	public static float getFastArrowSpeed() {
		return fastArrowSpeed;
	}

	/**
	 * @return the slowArrowSpeed
	 */
	public static float getSlowArrowSpeed() {
		return slowArrowSpeed;
	}

	/**
	 * @param aSpeed
	 *            the arrowSpeed to set
	 */
	public static void setArrowSpeed(final float aSpeed) {
		GameVariables.arrowSpeed = aSpeed;
	}

	/**
	 * @return the movementSpeed
	 */
	public static float getMovementSpeed() {
		return movementSpeed;
	}

	/**
	 * @param mSpeed
	 *            the new movementSpeed to set
	 */
	public static void setMovementSpeed(final float mSpeed) {
		GameVariables.movementSpeed = mSpeed;
	}

	/**
	 * @return the ball update speed
	 */
	public static float getBallSpeed() {
		return ballSpeed;
	}

	/**
	 * @param speed
	 *            the ball speed to set
	 */
	public static void setBallSpeed(final float speed) {
		GameVariables.ballSpeed = speed;
	}

	/**
	 * 
	 * @return the improvedMovementSpeed
	 */
	public static float getImprovedMovementSpeed() {
		return improvedMovementSpeed;
	}

	/**
	 * 
	 * @return the normalMovementSpeed
	 */
	public static float getNormalMovementSpeed() {
		return normalMovementSpeed;
	}

	/**
	 * @return the gravity
	 */
	public static float getGravity() {
		return gravity;
	}

	/**
	 * @param gravity
	 *            the gravity to set
	 */
	public static void setGravity(float gravity) {
		GameVariables.gravity = gravity;
	}

	/**
	 * @return the lives
	 */
	public static int getLives() {
		return lives;
	}

	/**
	 * @param lives
	 *            the lives to set
	 */
	public static void setLives(int lives) {
		GameVariables.lives = lives;
	}

}
