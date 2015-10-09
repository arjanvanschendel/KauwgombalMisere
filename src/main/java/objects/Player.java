package objects;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glColor4f;
import game.Collision;
import game.CollisionDetection;
import game.Game;
import game.GameVariables;
import game.Level;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import shapes.Box;
import utillities.Keyboard;
import utillities.Logger;
import utillities.SpriteSheet;
import utillities.Texture;

/**
 * Class Player: this class represents the player of the game.
 * 
 * @author Luke
 *
 */
public class Player extends Box implements GameObject {
	private float deltaX = 0;
	private float deltaY = 0;
	private boolean alive = true;
	private SpriteSheet idle = null;
	private SpriteSheet running = null;
	private SpriteSheet selected = null;
	private int state;
	private boolean mirrored;
	private double lastFrame;
	private double targetDelta = 50/3;
	private double target;

	/**
	 * Player: constructor.
	 * 
	 * @param posx
	 * @param posy
	 */
	public Player(float posx, float posy) {
		super(posx, posy, 60, 100, new Color(1, 1, 1));
		selected = idle;
		state = 0;
		mirrored = false;
		lastFrame = System.currentTimeMillis();
		target = lastFrame + targetDelta;
	}

	/**
	 * update: Update the player's state.
	 */
	public void update(double deltaTime) {

		// First handle inputs
		handleInputs(deltaTime);
		setPosx((float) (getPosx() + deltaX * 60 * deltaTime));
		setPosy((float) (getPosy() + deltaY * 60 * deltaTime));
		ArrayList<Collision> collisions = CollisionDetection.collision(this);
		if (!collisions.isEmpty()) {
			for (Collision collision : collisions) {

				if (collision.getCol() instanceof Ball) {
					Logger.add("player died");
					die();
				} else if (collision.getCol() instanceof Wall) {
					if (collision.getSide() == 4) {
						setPosx(((Box) collision.getCol()).getPosx() - getWidth());
						deltaX = 0;
					} else if (collision.getSide() == 2) {
						setPosx(((Box) collision.getCol()).getPosx()
								+ ((Box) collision.getCol()).getWidth());
						deltaX = 0;
					}
				}
			}
		}
		if (state == 0) {
			selected = idle;
		} else {
			selected = running;
		}
		if (System.currentTimeMillis() >= target && selected != null) {
			selected.nextSprite();
			lastFrame = System.currentTimeMillis();
			target = lastFrame + targetDelta;
		}
	}

	/**
	 * render: render the player's graphics.
	 */
	@Override
	public void render() {
		if(idle == null || running == null){

			idle = new SpriteSheet(Game.textures.get(0), 2, 31);
			running = new SpriteSheet(Game.textures.get(1), 2, 20);
			selected = idle;
		}
		selected.bind();
		float[] c = selected.returnCoordinates(mirrored);
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glBegin(GL_QUADS);
		glTexCoord2f(c[0], c[3]);
		glVertex2f(corners[0].getX(), corners[0].getY());
		glTexCoord2f(c[1], c[3]);
		glVertex2f(corners[1].getX(), corners[1].getY());
		glTexCoord2f(c[1], c[2]);
		glVertex2f(corners[2].getX(), corners[2].getY());
		glTexCoord2f(c[0], c[2]);
		glVertex2f(corners[3].getX(), corners[3].getY());
		glEnd();
		Texture.disable();
		// glDisable(GL_TEXTURE_2D);
	}

	/**
	 * handleInputs: handle keyboard inputs for the player.
	 * 
	 * @param deltaTime
	 */
	private void handleInputs(double deltaTime) {

		if (Keyboard.isKeyDown(GLFW_KEY_LEFT) || Keyboard.isKeyDown(GLFW_KEY_A)) {
			state = 1;
			mirrored = false;
			walkLeft(deltaTime);
		} else if (Keyboard.isKeyDown(GLFW_KEY_RIGHT)
				|| Keyboard.isKeyDown(GLFW_KEY_D)) {
			state = 1;
			mirrored = true;
			walkRight(deltaTime);
		} else {
			state = 0;
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
		Projectile p = new Projectile(this.getPosx() 
				+ (this.getWidth() / 2), this.getPosy());

		Level.setProjectile(p);
	}

	/**
	 * walkRight: lets the player move right over the x-axis.
	 * 
	 * @param deltaTime
	 */
	private void walkRight(double deltaTime) {
		deltaX += 30 * deltaTime;
		if (deltaX > GameVariables.getMovementSpeed()) {
			deltaX = (float) (GameVariables.getMovementSpeed());
		}
	}

	/**
	 * walkLeft: lets the player move left over the x-axis.
	 * 
	 * @param deltaTime
	 */
	private void walkLeft(double deltaTime) {

		deltaX -= 30 * deltaTime;
		if (deltaX < -GameVariables.getMovementSpeed()) {
			deltaX = (float) (-GameVariables.getMovementSpeed());
		}
	}

	/**
	 * walkStop: stops the player from walking.
	 * 
	 * @param deltaTime
	 */
	private void walkStop(double deltaTime) {
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
	private void die() {
		Game.sounds.get(4).play();
		alive = false;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
}
