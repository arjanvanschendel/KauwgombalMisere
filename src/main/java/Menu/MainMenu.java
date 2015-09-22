package Menu;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
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
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class MainMenu {
		
	private Font awtFont = new Font("Courier New", Font.BOLD, 48);
	private TrueTypeFont font = new TrueTypeFont(awtFont, true);
	private ArrayList<Button> buttons  = new ArrayList<Button>();
	private Button playBtn;
	private Texture texture;
	private int WIDTH;
	private int HEIGHT;
	
	public MainMenu(){
		playBtn = new Button(0, Launcher.getCAMHEIGHT()/2, 75, 25, java.awt.Color.white, "Play");
		buttons.add(playBtn);
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		WIDTH = GLFWvidmode.width(vidmode);
		HEIGHT = GLFWvidmode.height(vidmode);
		System.out.println("WxH = "+WIDTH+"x"+HEIGHT);
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/KMmain.png"));
		} catch (IOException e) {
			System.out.println(e);;
		}
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
		
		Color.white.bind();
		texture.bind();
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(1,0);
		GL11.glVertex2f(WIDTH/4,HEIGHT/2);
		
		GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(-WIDTH/4,HEIGHT/2);
		
		GL11.glTexCoord2f(0,1);
		GL11.glVertex2f(-WIDTH/4,0);
		
		GL11.glTexCoord2f(1,1);
		GL11.glVertex2f(WIDTH/4,0);
		
		GL11.glEnd();
		
		
		for (Button btn : buttons) {
			btn.render();
		}
		
		GL11.glScalef(1, -1, 1);
		TextureImpl.bindNone();
		font.drawString(0, -500, "Kauwgombal Misere" , Color.cyan);
		font.drawString(-500, -300, "Fancy plaatje hier" , Color.black);
		GL11.glScalef(1, -1, 1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
