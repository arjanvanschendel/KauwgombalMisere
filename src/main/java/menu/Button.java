package menu;

import game.Launcher;

import java.awt.Color;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureImpl;

import shapes.Box;
import shapes.Point;
import utillities.Mouse;

/**
 * 
 * @author Luke
 *
 */
public class Button extends Box {

	private String buttonString = "";
	private boolean clicked;

	/**
	 * 
	 * @param pos
	 *            position of the button in Point format
	 * @param width
	 *            width of button
	 * @param height
	 *            height of button
	 * @param color
	 *            color of button
	 * @param buttonString
	 *            text
	 */
	public Button(Point pos, float width, float height, Color color,
			String buttonString) {
		super(pos, width, height, color);
		this.buttonString = buttonString;
	}

	/**
	 * Update method.
	 * 
	 * @param deltaTime
	 *            time between frames
	 */
	public void update(double deltaTime) {
		if (Mouse.isButtonReleased(0)
				&& super.pointInShape(Mouse.getCursorPos())) {
			clicked = true;
		} else {
			clicked = false;
		}
	}

	/**
	 * Render method.
	 */
	@Override
	public void render() {
		super.render();
		GL11.glScalef(1, -1, 1);
		TextureImpl.bindNone();
		Launcher.getFont().drawString(
				(getPosX() + getWidth() / 2f)
						- Launcher.getFont().getWidth(buttonString) / 2f,
				-getPosY() - Launcher.getFont().getHeight(buttonString),
				buttonString, org.newdawn.slick.Color.black);
		GL11.glScalef(1, -1, 1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

	}

	/**
	 * @return the clicked
	 */
	public boolean isClicked() {
		return clicked;
	}

	/**
	 * @param clicked
	 *            the clicked to set
	 */
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

}
