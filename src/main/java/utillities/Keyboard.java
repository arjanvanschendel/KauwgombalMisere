package utillities;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFWKeyCallback;

/**
 * Handles keyboard input.
 * @author Jasper
 *
 */
public class Keyboard extends GLFWKeyCallback {

	private static boolean[] keys;
	private static boolean[] releasedKeys;
	private static ArrayList<Integer> released;
	
	/**
	 * Sets basic variables for the keyboard.
	 */
	public Keyboard() {
		setKeys(new boolean[65536]);
		setReleasedKeys(new boolean[65536]);
		setReleased(new ArrayList<Integer>());
	}

	/**
	 * @param keys the keys to set
	 */
	public static void setKeys(boolean[] keys) {
		Keyboard.keys = keys.clone();
	}

	/**
	 * @param releasedKeys the releasedKeys to set
	 */
	public static void setReleasedKeys(boolean[] releasedKeys) {
		Keyboard.releasedKeys = releasedKeys.clone();
	}

	/**
	 * @param released the released to set
	 */
	public static void setReleased(ArrayList<Integer> released) {
		Keyboard.released = released;
	}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		// TODO Auto-generated method stub
		if (key < 65536 && key >= 0) {
			keys[key] = action != 0;
			if (action == 0) {
				releasedKeys[key] = action == 0;
				released.add(key);
			}

		}
	}

	/**
	 * 
	 * @param keycode
	 *            key
	 * @return true if down
	 */
	public static boolean isKeyDown(int keycode) {
		return keys[keycode];
	}

	/**
	 * 
	 * @param keycode
	 *            key
	 * @return true if released
	 */
	public static boolean isKeyReleased(int keycode) {
		boolean res = releasedKeys[keycode];
		Keyboard.releasedKeys[keycode] = false;
		return res;
	}

	/**
	 * Reset released.
	 */
	public static void resetReleased() {
		for (int index : released) {
			releasedKeys[index] = false;
		}
		released.clear();
	}

}
