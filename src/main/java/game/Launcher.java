package game;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.Callbacks.glfwSetCallback;
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
import static org.lwjgl.glfw.GLFW.GLFWWindowSizeCallback;
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
 * 
 * JAAAAAASPSPSEEEEEERRRR
 *
 */
public class Launcher {

	// We need to strongly reference callback instances.
	private GLFWErrorCallback errorCallback;
	private Keyboard keyCallback;
	private double lastFrame;
	private Mouse mouseCallback;
	private GLFWWindowSizeCallback WindowResize;
	private static int CAMWIDTH;
	private static int CAMHEIGHT;
	private static int WIDTH;
	private static int HEIGHT;
	private static long window;
	private static TrueTypeFont font;

	public void run() {
		// System.out.println("Hello LWJGL " + Sys.getVersion() + "!");

		try {
			init();
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

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (glfwInit() != GL11.GL_TRUE)
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure our window
		glfwDefaultWindowHints(); // optional, the current window hints are
									// already the default
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden
												// after creation
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable

		// Get the resolution of the primary monitor
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		WIDTH = GLFWvidmode.width(vidmode);
		HEIGHT = GLFWvidmode.height(vidmode);

		// Create the window
		// window = glfwCreateWindow(WIDTH, HEIGHT, "KauwgombalMisere", NULL,
		// NULL);
		// Fullscreen
		window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!",
				glfwGetPrimaryMonitor(), NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		glfwSetKeyCallback(window, keyCallback = new Keyboard());
		glfwSetMouseButtonCallback(window, mouseCallback = new Mouse());
		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(0);
		// Make the window visible
		glfwShowWindow(window);
	}

	private void InitOpenGL() {
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

		double aRatio = (double) WIDTH / (double) HEIGHT;
		if (aRatio < 1.8) {
			CAMWIDTH = 1000;
			CAMHEIGHT = (int) (CAMWIDTH / (double) aRatio);

		} else {
			CAMHEIGHT = 550;
			CAMWIDTH = (int) (CAMHEIGHT * aRatio);
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-CAMWIDTH / (double) 2, CAMWIDTH / (double) 2, 0, CAMHEIGHT, -1, 1);
		glMatrixMode(GL11.GL_MODELVIEW);
		WindowResize = GLFWWindowSizeCallback(new SAM() {
			@Override
			public void invoke(long window, int width, int height) {
				WIDTH = width;
				HEIGHT = height;
				// Change CAMHEIGHT to change zoom level
				double aRatio = (double) WIDTH / (double) HEIGHT;
				if (aRatio < 1.8) {
					CAMWIDTH = 1000;
					CAMHEIGHT = (int) ((double)CAMWIDTH / aRatio);

				} else {
					CAMHEIGHT = 550;
					CAMWIDTH = (int) ((double)CAMHEIGHT * aRatio);
				}
				GL11.glViewport(0, 0, width, height);
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity();
				GL11.glOrtho(-(float)CAMWIDTH / 2, (float)CAMWIDTH / 2, 0, CAMHEIGHT,
						-1, 1);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glLoadIdentity();

			}
		});
		glfwSetCallback(window,WindowResize);
				
	}

	private void loop() {

		InitOpenGL();

		// load a default java font
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		setFont(new TrueTypeFont(awtFont, true));

		Game game = Game.getInstance();
		game.setup();
		game.reset();
		lastFrame = glfwGetTime();

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
		}
	}

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
	public static int getCAMWIDTH() {
		return CAMWIDTH;
	}

	/**
	 * @return the cAMHEIGHT
	 */
	public static int getCAMHEIGHT() {
		return CAMHEIGHT;
	}

	/**
	 * @return the wIDTH
	 */
	public static int getWIDTH() {
		return WIDTH;
	}

	/**
	 * @return the hEIGHT
	 */
	public static int getHEIGHT() {
		return HEIGHT;
	}

	/**
	 * @return the font
	 */
	public static TrueTypeFont getFont() {
		return font;
	}

	/**
	 * @param font the font to set
	 */
	public static void setFont(TrueTypeFont font) {
		Launcher.font = font;
	}

}