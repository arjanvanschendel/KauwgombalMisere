package Menu;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.opengl.GL11.glColor4f;
import game.Game;
import game.Launcher;

import java.awt.Font;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import utillities.Texture;

import org.newdawn.slick.opengl.TextureImpl;


public class MainMenu {
		
	private Font awtFont = new Font("Courier New", Font.BOLD, 48);
	private TrueTypeFont font = new TrueTypeFont(awtFont, true);
	private ArrayList<Button> buttons  = new ArrayList<Button>();
	private Button playBtn;
	private Texture texture;
	private int WIDTH;
	private int HEIGHT;
	
	public MainMenu(){
		playBtn = new Button(-75, Launcher.getCAMHEIGHT()/3, 150, 25, java.awt.Color.white, "Play");
		buttons.add(playBtn);
		texture = new Texture("res/KMmain.png",GL11.GL_NEAREST,GL11.GL_CLAMP);
		
		WIDTH = Launcher.getCAMWIDTH();
		HEIGHT = Launcher.getCAMHEIGHT();
		System.out.println("WxH = "+WIDTH+"x"+HEIGHT);
		
	}
	
	public static int findNextTwo(int number){
		int result = 1;
		while(result<number){
			result*=2;
		}
		return result;
	}
	
	/**
	 * update: update the level-object's state.
	 * @param deltaTime
	 */
	public final void update(final double deltaTime) {
		for (Button btn : buttons) {
			btn.update(deltaTime);
		}
		if(playBtn.isClicked()){
			Game.loadLevel(1);
			Game.setState(0);
		}
	}

	/**
	 * render: render the level-object's graphics.
	 */
	public final void render() {
        texture.bind(); // or GL11.glBind(texture.getTextureID());
         
        GL11.glBegin(GL11.GL_QUADS);
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		
            GL11.glTexCoord2f(0,0);
            GL11.glVertex2f(-WIDTH/2,HEIGHT);
            
            GL11.glTexCoord2f(1,0);
            GL11.glVertex2f(WIDTH/2,HEIGHT);
            
            GL11.glTexCoord2f(1,1);
            GL11.glVertex2f(WIDTH/2,0);
            
            GL11.glTexCoord2f(0,1);
            GL11.glVertex2f(-WIDTH/2,0);
        GL11.glEnd();
        Texture.disable();
        
		for (Button btn : buttons) {
			btn.render();
		}
		
		GL11.glScalef(1, -1, 1);
		TextureImpl.bindNone();
		font.drawString(0, -100, "sample text" , Color.cyan);
		GL11.glScalef(1, -1, 1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
