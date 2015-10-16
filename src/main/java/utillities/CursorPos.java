package utillities;

import game.Launcher;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import shapes.Point;

/**
 * 
 * Callback class which keeps track of the current cursor position.
 * 
 * @author Jasper
 *
 */
public class CursorPos extends GLFWCursorPosCallback {

	private static Point position = new Point(0, 0);

	@Override
	public void invoke(long window, double xpos, double ypos) {

		position.setX((float) xpos);
		position.setY((float) ypos);
		Launcher.pixelToOpenGLPos(position);
	}

	/**
	 * @return the position
	 */
	public static Point getCursorPos() {
		return position;
	}

}
