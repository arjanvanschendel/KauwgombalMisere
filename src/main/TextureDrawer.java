package main;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjglx.LWJGLException;
import org.lwjglx.opengl.Display;
import org.lwjglx.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
 
public class TextureDrawer {
     
    /** The texture that will hold the image details */
    private Texture texture;
    private String loc;
    private int width;
    private int height;
    
     
    public TextureDrawer(String location, int wid, int hei){
    	loc = location;
    	width = wid;
    	height = hei;
    	start();
    }
     
    /**
     * Start the example
     */
    public void start() {
       // initGL(width,height);
        init();
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            render();
          
    }
     
    /**
     * Initialise the GL display
     * 
     * @param width The width of the display
     * @param height The height of the display
     */
    private void initGL(int wid, int hei) {
        try {
            Display.setDisplayMode(new DisplayMode(wid,hei));
            Display.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
 
        GL11.glEnable(GL11.GL_TEXTURE_2D);               
         
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
         
            // enable alpha blending
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         
            GL11.glViewport(0,0,width,height);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
     
    /**
     * Initialise resources
     */
    public void init() {
         
        try {
            // load texture from PNG file
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(loc));
         
            System.out.println("Texture loaded: "+texture);
            System.out.println(">> Image width: "+texture.getImageWidth());
            System.out.println(">> Image height: "+texture.getImageHeight());
            System.out.println(">> Texture width: "+texture.getTextureWidth());
            System.out.println(">> Texture height: "+texture.getTextureHeight());
            System.out.println(">> Texture ID: "+texture.getTextureID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * draw a quad with the image on it
     */
    public void render() {
    	System.out.println("rendering");
    	float Iheight = texture.getImageHeight();
    	float Iwidth = texture.getImageWidth();
    	float Theight = texture.getTextureHeight();
    	float Twidth = texture.getTextureHeight();
    	
    	ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
    	int WIDTH = GLFWvidmode.width(vidmode)/2;
        int HEIGHT = GLFWvidmode.height(vidmode)/2;
    	
    	
        Color.white.bind();
        texture.bind(); // or GL11.glBind(texture.getTextureID());
        
        
        
        GL11.glBegin(GL11.GL_QUADS);
            //Linksonder
            GL11.glTexCoord2f(0,1);
            GL11.glVertex2f(-WIDTH/2,-Theight+Iheight);
            //Rechtsonder
            GL11.glTexCoord2f(1,1);
            GL11.glVertex2f((WIDTH/2)+Twidth-Iwidth,-Theight+Iheight);
            //Rechtsboven
            GL11.glTexCoord2f(1,0);
            GL11.glVertex2f((WIDTH/2)+Twidth-Iwidth,HEIGHT);
            //Linksboven
            GL11.glTexCoord2f(0,0);
            GL11.glVertex2f(-WIDTH/2,HEIGHT);
        GL11.glEnd();
    }
    
}