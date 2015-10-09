package menu;

import game.Launcher;

import java.awt.Color;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureImpl;

import shapes.Box;
import utillities.Mouse;

/**
 * 
 * @author Luke
 *
 */
public class Button extends Box {

	private String buttonString = "";
	private boolean clicked;

	public Button(float posx, float posy, float width, float height,
			Color color, String buttonString) {
		super(posx, posy, width, height, color);
		this.buttonString = buttonString;
	}
	
	public void update(double deltaTime) {
		if(Mouse.isButtonReleased(0) && super.pointInShape(Mouse.getCursorPos())){
			clicked = true;
		} else{
			clicked = false;
		}
	}

	@Override
	public void render() {
		super.render();
		GL11.glScalef(1, -1, 1);
		TextureImpl.bindNone();
		Launcher.getFont().drawString(
				(getPosx() + getWidth() / 2f) - Launcher.getFont().getWidth(buttonString) / 2f,
				-getPosy() - Launcher.getFont().getHeight(buttonString),
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
	 * @param clicked the clicked to set
	 */
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

}
