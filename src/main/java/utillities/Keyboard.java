package utillities;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFWKeyCallback;

/**
 * 
 * JAPSER
 *
 */
public class Keyboard extends GLFWKeyCallback {

	public static boolean[] keys = new boolean[65536];
	public static boolean[] releasedKeys = new boolean[65536];
	private static ArrayList<Integer> released = new ArrayList<Integer>();

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

	public static boolean isKeyDown(int keycode) {
		return keys[keycode];
	}

	public static boolean isKeyReleased(int keycode) {
		boolean res = releasedKeys[keycode];
		Keyboard.releasedKeys[keycode] = false;
		return res;
	}
	
	public static void resetReleased(){
		for (int index : released) {
			releasedKeys[index] = false;
		}
		released.clear();
	}

}
