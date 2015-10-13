package objects;

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
import java.util.ArrayList;

import shapes.Box;
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

	private Game game = Game.getInstance();
	private PlayerState state;
	private float deltaX = 0;
	private float deltaY = 0;
	private boolean alive = true;
	private SpriteSheet selected = null;
	private boolean mirrored;
	private double targetDelta = 16.6666667;
	private double target;

	/**
	 * Player: constructor.
	 * 
	 * @param posX
	 *            X value of position
	 * @param posY
	 *            Y value of position
	 */
	public Player(float posX, float posY) {
		super(posX, posY, 60, 100, new Color(1, 1, 1));
		setState(new IdleState());
		state.update(this);
		mirrored = false;
		target = System.currentTimeMillis() + targetDelta;
	}

	/**
	 * Check collision with other objects.
	 */
	private void checkCollision() {
		ArrayList<Collision> collisions = CollisionDetection.collision(this);
		if (!collisions.isEmpty()) {
			for (Collision collision : collisions) {
				if (collision.getCol() instanceof Ball) {
					Logger.add("player died");
					die();
				} else if (collision.getCol() instanceof Wall) {
					if (collision.getSide() == 4) {
						setPosx(((Box) collision.getCol()).getPosx()
								- getWidth());
						deltaX = 0;
					} else if (collision.getSide() == 2) {
						setPosx(((Box) collision.getCol()).getPosx()
								+ ((Box) collision.getCol()).getWidth());
						deltaX = 0;
					}
				}
			}
		}
	}

	/**
	 * Update sprite of spritesheet.
	 */
	private void updateSprite() {
		if (System.currentTimeMillis() >= target) {
			selected.nextSprite();
			target = System.currentTimeMillis() + targetDelta;
		}
	}

	/**
	 * handleInputs: handle keyboard inputs for the player.
	 * 
	 * @param deltaTime
	 */
	private void handleInputs(double deltaTime) {
		state.handleInputs(this, deltaTime);
		setPosx((float) (getPosx() + deltaX * 60 * deltaTime));
		setPosy((float) (getPosy() + deltaY * 60 * deltaTime));
	}

	/**
	 * Update player.
	 * 
	 * @param deltaTime
	 *            time between frames
	 */
	@Override
	public void update(double deltaTime) {
		if (alive) {
			handleInputs(deltaTime);
			checkCollision();
			state.update(this);
			updateSprite();
		}
	}

	/**
	 * Draw player graphics.
	 */
	@Override
	public void render() {
		selected.bind();
		float[] c = selected.returnCoordinates(mirrored);
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glBegin(GL_QUADS);
		glTexCoord2f(c[0], c[3]);
		glVertex2f(getCorners()[0].getX(), getCorners()[0].getY());
		glTexCoord2f(c[1], c[3]);
		glVertex2f(getCorners()[1].getX(), getCorners()[1].getY());
		glTexCoord2f(c[1], c[2]);
		glVertex2f(getCorners()[2].getX(), getCorners()[2].getY());
		glTexCoord2f(c[0], c[2]);
		glVertex2f(getCorners()[3].getX(), getCorners()[3].getY());
		glEnd();
		Texture.disable();
	}

	/**
	 * Lets the player shoot a vertical beam or activate a powerup.
	 */
	public void shoot() {
		Projectile p = new Projectile(this.getPosx() + (this.getWidth() / 2),
				this.getPosy());
		Level.setProjectile(p);
	}

	/**
	 * Lets the player move right over the x-axis.
	 * 
	 * @param deltaTime
	 *            time between frames
	 */
	public void walkRight(double deltaTime) {
		deltaX += 30 * deltaTime;
		if (deltaX > GameVariables.getMovementSpeed()) {
			deltaX = (float) (GameVariables.getMovementSpeed());
		}
	}

	/**
	 * Lets the player move left over the x-axis.
	 * 
	 * @param deltaTime
	 *            time between frames
	 */
	public void walkLeft(double deltaTime) {
		deltaX -= 30 * deltaTime;
		if (deltaX < -GameVariables.getMovementSpeed()) {
			deltaX = (float) (-GameVariables.getMovementSpeed());
		}
	}

	/**
	 * walkStop: stops the player from walking.
	 * 
	 * @param deltaTime
	 *            time between frames
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
	private void die() {
		if (game.getSounds().size() > 0) {
			game.getSounds().get(4).play();
		}
		alive = false;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public final void setState(PlayerState state) {
		this.state = state;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public final void setSelected(SpriteSheet selected) {
		this.selected = selected;
	}

	/**
	 * @param mirrored
	 *            the mirrored to set
	 */
	public final void setMirrored(boolean mirrored) {
		this.mirrored = mirrored;
	}

}
