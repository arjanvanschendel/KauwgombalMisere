package objects;

/**
 * Player state interface.
 * @author Luke
 *
 */
public interface PlayerState {
    
    /**
     * 
     * @param player the player
     */
    void update(Player player);
    
    /**
     * 
     * @param player the player
     * @param deltaTime time between frames
     */
    void handleInputs(Player player, double deltaTime);
    
    
}
