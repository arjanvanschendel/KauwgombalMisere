package objects;

/**
 * GameObject interface
 * @author Luke
 *
 */
public interface GameObject {

    	/**
    	 * Update method.
    	 * @param deltaTime time between frames
    	 */
	public void update(double deltaTime);
	
	/**
	 * Render method.
	 */
	public void render();
	
}
