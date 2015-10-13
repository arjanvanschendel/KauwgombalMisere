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
 * 
 * @author Luke
 *
 */
public class IdleState implements PlayerState {

	private Game game = Game.getInstance();
	private SpriteSheet idle;

	/**
	 * Set spritesheet if texture detected.
	 */
	public IdleState() {
		if (!game.getTextures().isEmpty()) {
			idle = new SpriteSheet(game.getTextures().get("IdleSprite"), 2, 31);
		}
	}

	/**
	 * Update selected spritesheet.
	 */
	@Override
	public void update(Player player) {
		player.setSelected(idle);
	}

	/**
	 * Handle inputs in this state.
	 */
	@Override
	public void handleInputs(Player player, double deltaTime) {
		if (Keyboard.isKeyDown(GLFW_KEY_LEFT) || Keyboard.isKeyDown(GLFW_KEY_A)
				|| Keyboard.isKeyDown(GLFW_KEY_RIGHT)
				|| Keyboard.isKeyDown(GLFW_KEY_D)) {
			player.setState(new WalkingState());
		} else {
			player.walkStop(deltaTime);
		}
		if (Keyboard.isKeyDown(GLFW_KEY_SPACE)) {
			player.shoot();
		}
	}

}
