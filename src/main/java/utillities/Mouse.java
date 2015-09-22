package utillities;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import game.Launcher;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;
import shapes.Point;

/**
 * 
 * JAPSER
 *
 */
public class Mouse extends GLFWMouseButtonCallback{

	private static final boolean[] buttons = new boolean[8];
	private static final boolean[] releasedButtons = new boolean[8];
	
	public static boolean isButtonDown(int keycode) {
		return glfwGetMouseButton(Launcher.getWindow(), keycode) == 1;
	}

	public static boolean isButtonReleased(int keycode) {
		boolean res = releasedButtons[keycode];
		releasedButtons[keycode] = false;
		return res;
	}

	public static Point getCursorPos() {

		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

		glfwGetCursorPos(Launcher.getWindow(), x, y);

		x.rewind();
		y.rewind();
		float xf = (float) x.get();
		float yf = (float) y.get();
		xf = (xf / Launcher.getWIDTH()) * Launcher.getCAMWIDTH() - Launcher.getCAMWIDTH() / 2f;
		yf = -((yf / Launcher.getHEIGHT()) * Launcher.getCAMHEIGHT() - Launcher.getCAMHEIGHT());
		Point res = new Point(xf, yf);
		return res;
	}

	@Override
	public void invoke(long window, int button, int action, int mods) {
		buttons[button] = action != 0;
		releasedButtons[button] = action == 0;
		
	}

}
