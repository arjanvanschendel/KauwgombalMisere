package objects;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glColor4f;
import game.Collision;
import game.CollisionDetection;
import game.Game;
import game.GameVariables;
import game.Level;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import shapes.Box;
import utillities.Texture;

/**
 * Projectile class
 * 
 * @author Luke
 *
 */
public class Projectile extends Box implements GameObject {

    private Game game = Game.getInstance();
    private boolean hitBall = false;
    private Texture texture = null;

    /**
     * 
     * @param posx x position
     * @param posy y position
     */
    public Projectile(float posx, float posy) {
	super(posx, posy, 10, 50, new Color(205, 205, 205));

    }

    /**
     * Update method.
     * 
     * @param deltaTime time between frames
     */
    @Override
    public void update(double deltaTime) {
	ArrayList<Collision> collisions = CollisionDetection.collision(this);
	Ball ball = null;
	if (!collisions.isEmpty()) {
	    for (Collision collision : collisions) {
		if (collision.getCol() instanceof Wall
			&& collision.getSide() == 3) {
		    Level.setProjectile(null);
		    if (!game.getSounds().isEmpty()) {
			game.getSounds().get(1).play();
		    }
		} else if (collision.getCol() instanceof Ball) {
		    ball = (Ball) collision.getCol();
		    hitBall = true;
		}
	    }
	}
	if (hitBall) {
	    if (!game.getSounds().isEmpty()) {
		game.getSounds().get(0).play();
	    }
	    ball.hit();
	    Level.setProjectile(null);
	}

	setHeight((float) (getHeight() + GameVariables.getArrowSpeed()
		* deltaTime));

    }

    /**
     * Renders the projectile.
     */
    @Override
    public final void render() {
	if (texture == null) {
	    texture = game.getTextures().get(2);
	}
	texture.bind();
	glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	glBegin(GL_QUADS);
	glTexCoord2f(0.0f, 1.0f);
	glVertex2f(corners[0].getX(), corners[0].getY());
	glTexCoord2f(1.0f, 1.0f);
	glVertex2f(corners[1].getX(), corners[1].getY());
	glTexCoord2f(1.0f, 0.0f);
	glVertex2f(corners[2].getX(), corners[2].getY());
	glTexCoord2f(0.0f, 0.0f);
	glVertex2f(corners[3].getX(), corners[3].getY());
	glEnd();
	GL11.glDisable(GL11.GL_TEXTURE_2D);
	// TODO Auto-generated method stub

    }

    /**
     * 
     * @return true if ball hit
     */
    public boolean getHitBall() {
	return hitBall;
    }
}
