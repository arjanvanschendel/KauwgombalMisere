package Menu;

import game.Launcher;

import java.awt.Color;
import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureImpl;

import shapes.Box;
import shapes.Circle;
import utillities.Mouse;

/**
 * 
 * @author Luke
 *
 */
public class Slider extends Box {

	private boolean changed;
	private boolean dragged;
	private float percentage;
	private Circle begin;
	private Circle end;
	private Circle value;

	public Slider(float posx, float posy, float width, float height,
			Color color, float percent) {
		super(posx, posy, width, height, color);
		percentage = percent;
		begin = new Circle(posx, posy + height / 2, height / 2, color);
		end = new Circle(posx + width, posy + height / 2, height / 2, color);
		value = new Circle((float) (posx + getWidth() * (percentage / 100)),
				posy + height / 2, height / 2, Color.black);
	}

	public final void update(double deltaTime) {
		changed = false;
		if (Mouse.isButtonReleased(0) && dragged) {
			changed = true;
			dragged = false;
		}

		value.setPosx((float) (getPosx() + getWidth() * (percentage / 100)));
		if (Mouse.isButtonDown(0) && super.pointInShape(Mouse.getCursorPos())) {
			dragged = true;
		}

		if (dragged) {
			float mouseValue = ((Mouse.getCursorPos().getX() - getPosx()) / getWidth()) * 100;
			if (mouseValue > 100) {
				percentage = 100;
			} else if (mouseValue < 0) {
				percentage = 0;
			} else {
				percentage = mouseValue;
			}
		}
	}

	@Override
	public final void render() {
		super.render();

		GL11.glScalef(1, -1, 1);
		TextureImpl.bindNone();
		Launcher.getFont().drawString(
				getPosx() - Launcher.getFont().getWidth("0") / 2f, -getPosy(),
				"0", org.newdawn.slick.Color.white);

		Launcher.getFont().drawString(
				getPosx() + getWidth() - Launcher.getFont().getWidth("100")
						/ 2f, -getPosy(), "100", org.newdawn.slick.Color.white);

		DecimalFormat df = new DecimalFormat("#");
		Launcher.getFont().drawString(
				(float) (getPosx() + getWidth() * (percentage / 100) - Launcher
						.getFont().getWidth(df.format(percentage)) / 2f),
				-getPosy()
						- Launcher.getFont().getHeight(df.format(percentage))
						- getHeight(), df.format(percentage),
				org.newdawn.slick.Color.white);
		GL11.glScalef(1, -1, 1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		begin.render();
		end.render();
		value.render();
	}

	/**
	 * @return the clicked
	 */
	public final boolean isChanged() {
		return changed;
	}

	/**
	 * @param clicked
	 *            the clicked to set
	 */
	public final void setChanged(final boolean clicked) {
		this.changed = clicked;
	}

	/**
	 * @return the dragged
	 */
	public final boolean isDragged() {
		return dragged;
	}

	/**
	 * @param drag
	 *            the dragged to set
	 */
	public final void setDragged(final boolean drag) {
		this.dragged = drag;
	}

	/**
	 * @return the percentage
	 */
	public final float getPercentage() {
		return percentage;
	}

	/**
	 * @param percent
	 *            the percentage to set
	 */
	public final void setPercentage(final float percent) {
		this.percentage = percent;
	}
}
