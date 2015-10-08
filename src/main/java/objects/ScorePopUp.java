package objects;

import game.Launcher;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 * @author Bart
 * Class that supports the rendering of small score popups after a ball has 
 * been popped.
 * note: is rendered in game class in stead of level because this requires no 
 * binding of the textures.
 */
public class ScorePopUp implements GameObject {
	/**
	@param _posx x position of intended popup location
	 */
	private float posx;
	
	/**
	 * @param posy y position of intended popup location
	 */
	private float posy;
	
	/**
	 * amount of points(casted to string).
	 */
	private String score;
	/**
	 * color of the popup.
	 */
	private Color color;
	
	
	/**
	 * @param xpositie xpos van popup.
	 * @param ypositie ypos van popup.
	 * @param radius 
	 */
	public ScorePopUp(final float xpositie, final float ypositie, 
			final float radius) {
	posx = xpositie;
	posy = ypositie * -1;
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
	 * @return horizontal position.
	 */
	public final float getPosx() {
		return posx;
	}


	/**
	 * Setter for horizontal position.
	 */
	public final void setPosx(float posx) {
		this.posx = posx;
	}


	/**
	 * Getter for vertical position.
	 * @return vertical position.
	 */
	public final float getPosy() {
		return posy;
	}


	/**
	 * Setter for vertical position.
	 * @param posY position to set.
	 */
	public final void setPosy(final float posY) {
		this.posy = posY;
	}

	/**
	 * Getter for color.
	 * @return color.
	 */
	public final Color getColor() {
		return color;
	}

	/**
	 * Setter voor color.
	 * @param colorNew to set.
	 */
	public final void setColor(final Color colorNew) {
		this.color = colorNew;
	}
	
	/**
	 * Update method for this class.
	 */
	@Override
	public final void update(final double deltaTime) {
		setPosy((float) (getPosy() -  120 * deltaTime));
	}
	
	/**
	 * Render method for this class.
	 */
	@Override
	public final void render() {
	GL11.glPushMatrix();
	GL11.glScaled(1, -1, 1);
	Launcher.getFont().drawString(getPosx(), getPosy(), score, getColor());
	GL11.glPopMatrix();
	
	}

}
