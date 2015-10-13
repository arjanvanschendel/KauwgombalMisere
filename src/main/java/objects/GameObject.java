package objects;

/**
 * GameObject interface.
 * 
 * @author Luke
 *
 */
public interface GameObject {

	/**
	 * Update method.
	 * 
	 * @param deltaTime
	 *            time between frames
	 */
	void update(double deltaTime);

	/**
	 * Render method.
	 */
	void render();

}
