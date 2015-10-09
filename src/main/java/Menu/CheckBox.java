package menu;

import java.awt.Color;

import shapes.Box;
import utillities.Mouse;

/**
 * 
 * Checkbox used in the options menu.
 * 
 * @author Jasper
 *
 */
public class CheckBox extends Box {
	/**
	 * Right side of the checkbox.
	 */
	private Box rightSide;

	/**
	 * left side of the checkbox.
	 */
	private Box leftSide;

	/**
	 * Top of the checkbox.
	 */
	private Box top;

	/**
	 * Bottom of the checkbox.
	 */
	private Box bottom;

	/**
	 * Inner box if this is checked.
	 */
	private Box checkedBox;

	/**
	 * Boolean checked, indicates if the checkbox is checked.
	 */
	private boolean checked;

	/**
	 * 
	 * Constructor for checkbox.
	 * 
	 * @param posx
	 *            x-coordinate
	 * @param posy
	 *            y-coordinate
	 * @param size
	 *            size of the checkbox
	 * @param color
	 *            Color of the checkbox
	 * @param check
	 *            Starting value of the checkbox
	 */
	public CheckBox(final float posx, final float posy, final float size,
			final Color color, final boolean check) {
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

	/**
	 * Update method used to check if the player checked this box.
	 * 
	 * @param deltaTime Between the last 2 frames.
	 */
	public final void update(final double deltaTime) {
		if (Mouse.isButtonReleased(0)
				&& super.pointInShape(Mouse.getCursorPos())) {
			checked = !checked;
		}
	}
	
	@Override
	public final void render() {
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
	public final boolean isChecked() {
		return checked;
	}

	/**
	 * @param check
	 *            the checked to set
	 */
	public final void setClicked(final boolean check) {
		this.checked = check;
	}

}
