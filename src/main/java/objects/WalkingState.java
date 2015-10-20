/**
 * 
 */
package objects;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import game.Game;
import utillities.Keyboard;
import utillities.SpriteSheet;

/**
 * @author Luke
 *
 */
public class WalkingState implements PlayerState {

	private Game game = Game.getInstance();
	private SpriteSheet walking;

	/**
     * 
     */
	public WalkingState() {
		if (!game.getTextures().isEmpty()) {
			walking = new SpriteSheet(game.getTextures().get("Run"), 2, 20);
		}
	}

	/**
	 * Update method.
	 */
	@Override
	public void update(Player player) {
		player.setSelected(walking);
	}

	/**
	 * Handle inputs.
	 */
	@Override
	public void handleInputs(Player player, double deltaTime) {
		if (Keyboard.isKeyDown(GLFW_KEY_LEFT) || Keyboard.isKeyDown(GLFW_KEY_A)) {
			player.setMirrored(false);
			player.walkLeft(deltaTime);
		} else if (Keyboard.isKeyDown(GLFW_KEY_RIGHT)
				|| Keyboard.isKeyDown(GLFW_KEY_D)) {
			player.setMirrored(true);
			player.walkRight(deltaTime);
		} else {
			player.setState(new IdleState());
		}
		if (Keyboard.isKeyDown(GLFW_KEY_SPACE)) {
			player.shoot();
		}
	}

}
