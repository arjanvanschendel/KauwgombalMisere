package main;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.Box;
import interfaces.RenderAble;
import interfaces.UpdateAble;

/**
 * Class Player: this class represents the player of the game.
 *
 */
public class Player extends Box implements RenderAble, UpdateAble {
	private float deltaX = 0;
	private float deltaY = 0;

	/**
	 * Player: constructor.
	 * @param posx
	 * @param posy
	 */
	public Player(float posx, float posy) {
		super(posx, posy, 50, 100, new Color(1, 1, 1));
	}

	/**
	 * update: Update the player's state.
	 */
	public void update(double deltaTime) {
		// First handle inputs
		handleInputs(deltaTime);

		posx += deltaX;
		posy += deltaY;

		ArrayList<Collision> collisions = CollisionDetection.collision(this);
		if (!collisions.isEmpty()) {
			for (Collision collision : collisions) {
				if (collision.getSide() == 4) {
					posx = ((Box) collision.getCol()).getPosx() - width;
					deltaX = 0;
				} else if (collision.getSide() == 2) {
					posx = ((Box) collision.getCol()).getPosx() + ((Box) collision.getCol()).getWidth();
					deltaX = 0;
				}
			}
		}
		super.update();
	}

	/**
	 * render: render the player's graphics.
	 */
	public void render() {
		super.render();
	}

	/**
	 * handleInputs: handle keyboard inputs for the player.
	 * @param deltaTime
	 */
	private void handleInputs(double deltaTime) {

		if (Keyboard.isKeyDown(GLFW_KEY_LEFT) || Keyboard.isKeyDown(GLFW_KEY_A)) {
			walkLeft(deltaTime);
		} else if (Keyboard.isKeyDown(GLFW_KEY_RIGHT) || Keyboard.isKeyDown(GLFW_KEY_D)) {
			walkRight(deltaTime);
		} else {
			walkStop(deltaTime);
		}

		if (Keyboard.isKeyDown(GLFW_KEY_SPACE)) {
			shoot();
		}

	}

	/**
	 * shoot: lets the player shoot a vertical beam or activate a powerup.
	 */
	private void shoot() {

	}

	/**
	 * walkRight: lets the player move right over the x-axis.
	 * @param deltaTime
	 */
	private void walkRight(double deltaTime) {
		deltaX += 30 * deltaTime;
		if (deltaX > 5) {
			deltaX = (float) (5);
		}
	}

	/**
	 * walkLeft: lets the player move left over the x-axis.
	 * @param deltaTime
	 */
	public void walkLeft(double deltaTime) {

		deltaX -= 30 * deltaTime;
		if (deltaX < -5) {
			deltaX = (float) (-5);
		}
	}

	/**
	 * walkStop: stops the player from walking.
	 * @param deltaTime
	 */
	public void walkStop(double deltaTime) {
		if (deltaX < 0) {
			deltaX += 30 * deltaTime;
			if (deltaX > 0) {
				deltaX = 0;
			}
		} else if (deltaX > 0) {
			deltaX -= 30 * deltaTime;
			if (deltaX < 0) {
				deltaX = 0;
			}
		}
	}

	/**
	 * die: lets the player die.
	 */
	public void die() {
		height = 0;

	}
}
