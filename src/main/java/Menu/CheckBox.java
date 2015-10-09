package Menu;

import game.Launcher;

import java.awt.Color;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureImpl;

import shapes.Box;
import utillities.Mouse;

/**
 * 
 * @author Jasper
 *
 */
public class CheckBox extends Box {

	private Box rightSide;
	private Box leftSide;
	private Box top;
	private Box bottom;
	private Box checkedBox;
	private boolean checked;

	public CheckBox(float posx, float posy, float size, Color color,
			boolean check) {
		super(posx, posy, size, size, color);
		checked = check;
		checkedBox = new Box(posx + size / 8, posy + size / 8, size - size / 4,
				size - size / 4, color);
		leftSide = new Box(posx, posy, size / 11, size, color);
		rightSide = new Box(posx + size - size / 11, posy, size / 11, size,
				color);
		top = new Box(posx, posy + size - size / 11, size, size / 11, color);
		bottom = new Box(posx, posy, size, size / 11, color);
	}

	public void update(double deltaTime) {
		if (Mouse.isButtonReleased(0)
				&& super.pointInShape(Mouse.getCursorPos())) {
			checked = !checked;
		}
	}

	public void render() {
		leftSide.render();
		rightSide.render();
		top.render();
		bottom.render();
		if (checked) {
			checkedBox.render();
		}
	}

	/**
	 * @return checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param check
	 *            the checked to set
	 */
	public void setClicked(boolean check) {
		this.checked = check;
	}

}
