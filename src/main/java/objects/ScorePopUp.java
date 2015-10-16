package objects;

import game.Launcher;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

import shapes.Point;

/**
 * @author Bart
 * 
 *         Class that supports the rendering of small score popups after a ball
 *         has been popped. note: is rendered in game class in stead of level
 *         because this requires no binding of the textures.
 */
public class ScorePopUp implements GameObject {

	private Point pos;
	private String score;
	private Color color;

	/**
	 * @param pos
	 *            position of the popup
	 * @param radius
	 *            the radius of the ball hit.
	 */
	public ScorePopUp(final Point pos, final float radius) {
		this.pos = pos;
		pos.setY(pos.getY() * -1);
		switch ((int) radius) {
		case 50:
			score = 20 + "";
			color = Color.red;
			break;
		case (50 / 2):
			score = 20 + "";
			color = Color.blue;
			break;
		case (50 / 4):
			score = 10 + "";
			color = Color.green;
			break;
		default:
			color = Color.green;
			break;
		}
	}

	/**
	 * Getter for horizontal position.
	 * 
	 * @return horizontal position.
	 */
	public final float getPosX() {
		return pos.getX();
	}

	/**
	 * Setter for horizontal position.
	 * 
	 * @param x
	 *            float to set
	 */
	public final void setPosX(float x) {
		this.pos.setX(x);
	}

	/**
	 * Getter for vertical position.
	 * 
	 * @return vertical position.
	 */
	public final float getPosY() {
		return pos.getY();
	}

	/**
	 * Setter for vertical position.
	 * 
	 * @param y
	 *            position to set.
	 */
	public final void setPosY(final float y) {
		this.pos.setY(y);
	}
	
	/**
	 * Update method for this class.
	 */
	@Override
	public final void update(final double deltaTime) {
		setPosY((float) (getPosY() - 120 * deltaTime));
	}

	/**
	 * Render method for this class.
	 */
	@Override
	public final void render() {
		TextureImpl.bindNone();
		GL11.glPushMatrix();
		GL11.glScaled(1, -1, 1);
		Launcher.getFont().drawString(getPosX(), getPosY(), score, color);
		GL11.glPopMatrix();

	}

}
