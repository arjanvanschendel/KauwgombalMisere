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
	 * Current gravity.
	 */
	private static float gravity = (float) 9.81;
	
	
	/**
	 * @return the arrowSpeed
	 */
	public static float getArrowSpeed() {
		return arrowSpeed;
	}
	
	/**
	 * @param aSpeed the arrowSpeed to set
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
	 * @param mSpeed the new movementSpeed to set
	 */
	public static void setMovementSpeed(final float mSpeed) {
		GameVariables.movementSpeed = mSpeed;
	}

	/**
	 * @return the gravity
	 */
	public static float getGravity() {
		return gravity;
	}

	/**
	 * @param grav the gravity to set
	 */
	public static void setGravity(final float grav) {
		GameVariables.gravity = grav;
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

	
}
