package utillities;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import game.Launcher;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;
import shapes.Point;

/**
 * 
 * @author Jasper
 *
 */
public class Mouse extends GLFWMouseButtonCallback {

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
	 * 
	 * @return Point
	 */
	public static Point getCursorPos() {

		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

		glfwGetCursorPos(Launcher.getWindow(), x, y);

		x.rewind();
		y.rewind();
		float xf = (float) x.get();
		float yf = (float) y.get();
		xf = ((float) xf / Launcher.getWidth()) * Launcher.getCamWidth()
				- (float) Launcher.getCamWidth() / 2;
		yf = -(((float) yf / Launcher.getHeight()) * Launcher.getCamHeight() - Launcher
				.getCamHeight());
		Point res = new Point(xf, yf);

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
