package main;

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

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import shapes.Box;
import interfaces.RenderAble;
import interfaces.UpdateAble;

/**
 * Class Player: this class represents the player of the game.
 * 
 * @author Luke
 *
 */
public class Player extends Box implements RenderAble, UpdateAble {
    private float deltaX = 0;
    private float deltaY = 0;
    private Texture texture;
    private SpriteSheet idle;
    private SpriteSheet running;
    private SpriteSheet selected;
    private int state;
    private boolean mirrored;

    /**
     * Player: constructor.
     * 
     * @param posx
     * @param posy
     */
    public Player(float posx, float posy) {
	super(posx, posy, 60, 100, new Color(1, 1, 1));
	texture = new Texture("res/figure.png", GL11.GL_NEAREST, GL11.GL_REPEAT);
	idle = new SpriteSheet(new Texture("res/IdleSprite.png",
		GL11.GL_NEAREST, GL11.GL_REPEAT), 2, 31);
	running = new SpriteSheet(new Texture("res/Run.png",
		GL11.GL_NEAREST, GL11.GL_REPEAT), 2, 20);
	state = 0;
	mirrored = false;
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

		if (collision.getCol() instanceof Ball) {
		    die();
		} else if (!(collision.getCol() instanceof Projectile)) {
		    if (collision.getSide() == 4) {
			posx = ((Box) collision.getCol()).getPosx() - width;
			deltaX = 0;
		    } else if (collision.getSide() == 2) {
			posx = ((Box) collision.getCol()).getPosx()
				+ ((Box) collision.getCol()).getWidth();
			deltaX = 0;
		    }
		}
	    }
	}
	super.update();
    }

    /**
     * render: render the player's graphics.
     */
    @Override
    public void render() {
	if (state == 0) {
	    selected = idle;   
	} else {
	    selected = running;
	}
	selected.bind();
	selected.nextSprite();
	float [] c = selected.returnCoordinates(mirrored);
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
	Projectile p = new Projectile(this.posx + 0.5f * this.width, this.posy);
	Level.addProjectile(p);
    }

    /**
     * walkRight: lets the player move right over the x-axis.
     * 
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
     * 
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
     * 
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
