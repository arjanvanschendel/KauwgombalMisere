package main;

import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
 
public class Launcher {
 
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
    private double lastFrame;
    private int WIDTH;
    private int HEIGHT;
    // The window handle
    private long window;
 
    public void run() {
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
 
        try {
            init();
            loop();
 
            // Release window and window callbacks
            glfwDestroyWindow(window);
            keyCallback.release();
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
        if ( glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
 
        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable

        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        
        WIDTH = GLFWvidmode.width(vidmode)/2;
        HEIGHT = GLFWvidmode.height(vidmode)/2;

        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
        //Fullscreen
        //window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", glfwGetPrimaryMonitor(), NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        

        glfwSetKeyCallback(window, keyCallback = new Keyboard());
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
        // Make the window visible
        glfwShowWindow(window);
    }
 
    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GLContext.createFromCurrent();
 
        // Set the clear color
        glClearColor(0.0f, 0.3f, 0.3f, 0.0f);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-WIDTH/2, WIDTH/2, 0, HEIGHT, -1, 1);
		glMatrixMode(GL11.GL_MODELVIEW);
		
        Game game = new Game();
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( glfwWindowShouldClose(window) == GL_FALSE ) {
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
        
        return delta;
    }
    
    public static void main(String[] args) {
        new Launcher().run();
    }
 
}