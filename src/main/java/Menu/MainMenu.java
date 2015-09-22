package Menu;

import static org.lwjgl.opengl.GL11.glColor4f;
import game.Game;
import game.Launcher;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import utillities.Texture;

import org.newdawn.slick.opengl.TextureImpl;

/**
 * Draws and updates the main menu.
 * @author Arjan
 *
 */
public class MainMenu {
		
	private Font awtFont = new Font("Courier New", Font.BOLD, 48);
	private TrueTypeFont font = new TrueTypeFont(awtFont, true);
	private ArrayList<Button> buttons  = new ArrayList<Button>();
	private Button playBtn;
	private Button exitBtn;
	private Button optBtn;
	private Texture Background;
	private int WIDTH;
	private int HEIGHT;
	
	/**
	 * A class to draw and maintain the main menu.
	 */
	public MainMenu() {
		playBtn = new Button(-75, Launcher.getCAMHEIGHT()/3+40, 
				150, 25, java.awt.Color.white, "Play");
		buttons.add(playBtn);
		optBtn = new Button(-75, Launcher.getCAMHEIGHT()/3, 
				150, 25, java.awt.Color.white, "Options");
		buttons.add(optBtn);
		exitBtn = new Button(-75, Launcher.getCAMHEIGHT()/3-40, 
				150, 25, java.awt.Color.white, "Exit");
		buttons.add(exitBtn);
		Background = new Texture("res/KMmain.png",
				GL11.GL_NEAREST, GL11.GL_CLAMP);
		
		WIDTH = Launcher.getCAMWIDTH();
		HEIGHT = Launcher.getCAMHEIGHT();
		System.out.println("WxH = " + WIDTH + "x" + HEIGHT);
		
	}
	
	/**
	 * Finds first positive power of two above the given input.
	 * @param number any integer
	 * @return a power of two
	 */
	public static int findNextTwo(final int number) {
		int result = 1;
		while (result < number){
			result *= 2;
		}
		return result;
	}
	
	/**
	 * update: update the menu-object's state.
	 * @param deltaTime The speed of the game
	 */
	public final void update(final double deltaTime) {
		for (Button btn : buttons) {
			btn.update(deltaTime);
		}
		if (playBtn.isClicked()) {
			Game.loadLevel(1);
			Game.setState(0);
		}
		if (optBtn.isClicked()) {
			System.out.println("Options button clicked");
		}
		if (exitBtn.isClicked()) {
			System.out.println("Exiting system via exit button");
			Game.setState(3);
		}
	}

	/**
	 * render: render the main menu's background, text, and objects.
	 */
	public final void render() {
		Background.bind(); // or GL11.glBind(texture.getTextureID());
         
        GL11.glBegin(GL11.GL_QUADS);
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(-WIDTH / 2, HEIGHT);
            
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex2f(WIDTH / 2, HEIGHT);
            
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f(WIDTH / 2, 0);
            
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f(-WIDTH / 2, 0);
        GL11.glEnd();
        Texture.disable();
        
		for (Button btn : buttons) {
			btn.render();
		}
		
		GL11.glScalef(1, -1, 1);
		TextureImpl.bindNone();
		font.drawString(0, -100, "sample text", Color.cyan);
		GL11.glScalef(1, -1, 1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
