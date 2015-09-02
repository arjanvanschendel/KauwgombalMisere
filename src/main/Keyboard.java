package main;

import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard  extends GLFWKeyCallback{

	public static boolean[] keys = new boolean[65536];
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		// TODO Auto-generated method stub
		keys[key] = action != 0;
	}
	
	public static boolean isKeyDown(int keycode) {
		return keys[keycode];
	}
	
}
