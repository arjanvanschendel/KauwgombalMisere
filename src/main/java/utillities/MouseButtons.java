package utillities;

import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;
import game.Launcher;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

/**
 * 
 * Keeps track of the currently pressed/released mouse buttons.
 * 
 * @author Jasper
 *
 */
public class MouseButtons extends GLFWMouseButtonCallback {

	private static boolean[] buttons = new boolean[8];
	private static boolean[] releasedButtons = new boolean[8];
	private static ArrayList<Integer> released = new ArrayList<Integer>();

	/**
	 * 
	 * @param keycode
	 *            button
	 * @return true if button down
	 */
	public static boolean isButtonDown(int keycode) {
		return glfwGetMouseButton(Launcher.getWindow(), keycode) == 1;
	}

	/**
	 * 
	 * @param keycode
	 *            button
	 * @return true if button released
	 */
	public static boolean isButtonReleased(int keycode) {
		boolean res = releasedButtons[keycode];
		return res;
	}
	/**
	 * Reset released.
	 */
	public static void resetReleased() {
		for (int index : released) {
			releasedButtons[index] = false;
		}
		released.clear();
	}

	/**
	 * Invoke button.
	 */
	@Override
	public void invoke(long window, int button, int action, int mods) {
		if (button >= 0 && button < 8) {
			buttons[button] = action != 0;
			if (action == 0) {
				releasedButtons[button] = action == 0;
				released.add(button);
			}
		}
	}

}
