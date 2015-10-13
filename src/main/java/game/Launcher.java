package game;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.Callbacks.glfwSetCallback;
import static org.lwjgl.glfw.GLFW.GLFWWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.awt.Font;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback.SAM;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.TrueTypeFont;

import utillities.Keyboard;
import utillities.Logger;
import utillities.Mouse;

/**
 * Initialises window and OpenGL. Also starts the loop and calculates the delta
 * time between frames.
 * 
 * @author Jasper
 *
 */
public class Launcher {

	// We need to strongly reference callback instances.
	private GLFWErrorCallback errorCallback;
	private Keyboard keyCallback;
	private double lastFrame;
	private Mouse mouseCallback;
	private GLFWWindowSizeCallback windowResize;
	private static int camWidth;
	private static int camHeight;
	private static int width;
	private static int height;
	private static long window;
	private static TrueTypeFont font;
	private Game game;

	/**
	 * Run method.
	 */
	public void run() {
		try {
			init();
			initOpenGL();

			loop();

			// Release window and window callbacks
			glfwDestroyWindow(window);
			keyCallback.release();
			mouseCallback.release();
		} finally {
			// Terminate GLFW and release the GLFWerrorfun
			glfwTerminate();
			errorCallback.release();
		}
	}

	/**
	 * Initial setup.
	 */
	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		errorCallback = errorCallbackPrint(System.err);
		glfwSetErrorCallback(errorCallback);

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (glfwInit() != GL11.GL_TRUE) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}

		// Configure our window
		glfwDefaultWindowHints(); // optional, the current window hints are
		// already the default
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden
		// after creation
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable

		// Get the resolution of the primary monitor
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		width = GLFWvidmode.width(vidmode);
		height = GLFWvidmode.height(vidmode);

		if (GameSettings.isFullscreen()) {
			// Fullscreen
			long newwindow = glfwCreateWindow(width, height, "Hello World!",
					glfwGetPrimaryMonitor(), window);
			if (window != NULL) {
				glfwDestroyWindow(window);
			}
			window = newwindow;
		} else {
			// Create the window
			long newwindow = glfwCreateWindow(width / 2, height / 2,
					"KauwgombalMisere", NULL, window);
			if (window != NULL) {
				glfwDestroyWindow(window);
			}
			window = newwindow;

			// Correct window size
			IntBuffer w = BufferUtils.createIntBuffer(1);
			IntBuffer h = BufferUtils.createIntBuffer(1);

			GLFW.glfwGetWindowSize(window, w, h);

			w.rewind();
			h.rewind();
			width = w.get();
			height = h.get();
		}
		if (window == NULL) {
			throw new RuntimeException("Failed to create the GLFW window");
		}

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(0);
		// Make the window visible
		glfwShowWindow(window);

	}

	/**
	 * Initial OpenGL setup.
	 */
	private void initOpenGL() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the ContextCapabilities instance and makes the OpenGL
		// bindings available for use.
		GLContext.createFromCurrent();

		// Set the clear color
		glClearColor(0.0f, 0.3f, 0.3f, 0.0f);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		double aRatio = (double) width / (double) height;
		if (aRatio < 1.8) {
			camWidth = 1000;
			camHeight = (int) (camWidth / (double) aRatio);

		} else {
			camHeight = 550;
			camWidth = (int) (camHeight * aRatio);
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-camWidth / (double) 2, camWidth / (double) 2, 0,
				camHeight, -1, 1);
		glMatrixMode(GL11.GL_MODELVIEW);
		windowResize = GLFWWindowSizeCallback(new SAM() {
			@Override
			public void invoke(long window, int w, int h) {
				width = w;
				height = h;
				// Change CAMHEIGHT to change zoom level
				double aRatio = (double) width / (double) height;
				if (aRatio < 1.8) {
					camWidth = 1000;
					camHeight = (int) ((double) camWidth / aRatio);

				} else {
					camHeight = 550;
					camWidth = (int) ((double) camHeight * aRatio);
				}
				GL11.glViewport(0, 0, width, height);
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity();
				GL11.glOrtho(-(float) camWidth / 2, (float) camWidth / 2, 0,
						camHeight, -1, 1);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glLoadIdentity();

			}
		});

		glfwSetCallback(window, windowResize);

		keyCallback = new Keyboard();
		mouseCallback = new Mouse();
		glfwSetKeyCallback(window, keyCallback);
		glfwSetMouseButtonCallback(window, mouseCallback);
	}

	/**
	 * Game loop.
	 */
	private void loop() {

		// load a default java font
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		setFont(new TrueTypeFont(awtFont, true));

		lastFrame = glfwGetTime();

		game = Game.getInstance();
		game.setup();
		game.reset();

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (glfwWindowShouldClose(window) == GL_FALSE) {
			glClear(GL_COLOR_BUFFER_BIT);
			glLoadIdentity();

			game.update(getDelta());
			game.render();

			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();

			if (GameSettings.reload()) {
				GameSettings.setReload(false);
				init();
				initOpenGL();
			}
		}
	}

	/**
	 * 
	 * @return deltaTime
	 */
	public double getDelta() {
		double time = glfwGetTime();
		double delta = time - lastFrame;
		lastFrame = time;
		if (delta < 0.07) {
			return delta;
		} else {
			return 0.07;
		}
	}

	/**
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		Logger.init();
		Logger.setPrintLog(false);
		new Launcher().run();
	}

	/**
	 * @return the window
	 */
	public static long getWindow() {
		return window;
	}

	/**
	 * @return the cAMWIDTH
	 */
	public static int getCamWidth() {
		return camWidth;
	}

	/**
	 * @return the cAMHEIGHT
	 */
	public static int getCamHeight() {
		return camHeight;
	}

	/**
	 * @return the wIDTH
	 */
	public static int getWidth() {
		return width;
	}

	/**
	 * @return the hEIGHT
	 */
	public static int getHeight() {
		return height;
	}

	/**
	 * @return the font
	 */
	public static TrueTypeFont getFont() {
		return font;
	}

	/**
	 * @param font
	 *            the font to set
	 */
	public static void setFont(TrueTypeFont font) {
		Launcher.font = font;
	}

}