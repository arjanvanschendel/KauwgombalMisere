package game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

import Menu.MainMenu;
import utillities.Keyboard;
import utillities.Logger;
import utillities.Mouse;
import utillities.Sound;
import utillities.Texture;

//import org.newdawn.slick.opengl.TextureLoader;

/**
 * Class Game: a Game object represents a game, holding all the levels.
 */
public class Game {
	private static int state;
	private static Level currentLvl;
	private static int lvl;
	private int maxLvl;
	private MainMenu mm;
	public static final ArrayList<Sound> sounds = new ArrayList<Sound>();
	public static final ArrayList<Texture> textures = new ArrayList<Texture>();

	/**
	 * Game: constructor.
	 */
	public Game() {
		
		Logger.add("game started");
		
		loadTextures();
		loadSounds();
		setLvl(1);
		maxLvl = countLevels();
		loadLevel(lvl);
		mm = new MainMenu();
		setState(2);
	}

	private void loadSounds() {

		try {
			sounds.add(new Sound("sounds/arrowHitBall.wav"));
			sounds.add(new Sound("sounds/arrowHitCeiling.wav"));
			sounds.add(new Sound("sounds/arrowShoot.wav"));
			sounds.add(new Sound("sounds/ballBounce.wav"));
			sounds.add(new Sound("sounds/playerHit.wav"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * loadTextures: load the textures onto memory.
	 */
	private void loadTextures() {
		textures.add(0, new Texture("res/IdleSprite.png", GL11.GL_NEAREST,
				GL11.GL_REPEAT));
		textures.add(1, new Texture("res/Run.png", GL11.GL_NEAREST,
				GL11.GL_REPEAT));
		textures.add(2, new Texture("res/arrow.png", GL11.GL_NEAREST, GL11.GL_CLAMP));
	}

	/**
	 * loadLevel: load a level.
	 * 
	 * @param location
	 */
	public static void loadLevel(int number) {
		setLvl(number);
		Logger.add("loading in new level");
		currentLvl = new Level("levels/level" + number + ".lvl");
	}

	/**
	 * nextLevel: moves the player to the next level.
	 */
	public void nextLevel() {
		if (lvl < maxLvl) {
			Logger.add("next level");
			setLvl(getLvl() + 1);
			loadLevel(lvl);
		} else {
			Logger.add("game won");
			setState(3);
		}

	}

	/**
	 * countLevels: count the amount of level files.
	 * 
	 * @return integer result
	 */
	public int countLevels() {
		int result = 0;
		File f = new File("levels/level" + (result + 1) + ".lvl");
		while (f.exists() && !f.isDirectory()) {
			result++;
			f = new File("levels/level" + (result + 1) + ".lvl");
		}

		return result;
	}

	/**
	 * update: Update the state of the game.
	 * 
	 * @param deltaTime
	 */
	public void update(double deltaTime) {
		if (Keyboard.isKeyReleased(GLFW_KEY_ESCAPE)) {
			if (state == 0) {
				setState(1);
			} else if (state == 1) {
				setState(0);
			}
		}
		if (Keyboard.isKeyReleased(GLFW_KEY_SPACE)) {
			if (state == 2) {
				setState(0);
			}
		}
		if (Level.levelComplete() && state == 0) {
			nextLevel();
		}

		switch (state) {
		case (0):
			// Playing
			currentLvl.update(deltaTime);
			break;
		case (1):
			// Paused
			break;

		case (2):
			// Main Menu
			mm.update(deltaTime);
			break;
		case (3):
			System.out.println("new state");
			break;
		default:
			System.out.println("INVALID STATE: " + state
					+ ". (Game.update method)");
			setState(2);
			//System.exit(1);

		}
		Mouse.resetReleased();
		Keyboard.resetReleased();
	}

	/**
	 * render: Render the graphics of the game.
	 */
	public void render() {
		switch (state) {
		case (0):
			// game
			currentLvl.render();
			GL11.glScalef(1, -1, 1);
			TextureImpl.bindNone();
			String levelString = "Level " + lvl + ": "
					+ currentLvl.getName();
			Launcher.getFont().drawString(-(float)Launcher.getFont().getWidth(levelString)/2, -100, levelString, Color.black);
			GL11.glScalef(1, -1, 1);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			break;
		case (1):
			// paused
			currentLvl.render();
			GL11.glScalef(1, -1, 1);
			TextureImpl.bindNone();
			Launcher.getFont().drawString(-50, -300, "PAUSED", Color.yellow);			
			GL11.glScalef(1, -1, 1);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			break;
		case (2):
			mm.render();
			break;
		default:
			System.out.println("INVALID STATE: " + state
					+ ". (Game.render method)");
			// System.exit(1);
			setState(2);
		}
	}
	
	public static void ballhit(float x, float y, char size){
	String s;
	if(size == 'b'){
		s = "20";
	}else{
		s = "10";
	}
	
	
	}
	/**
	 * getState.
	 * 
	 * @return int state
	 */
	public final int getState() {
		return state;
	}

	/**
	 * setState.
	 * 
	 * @param state
	 */
	public static void setState(int newState) {
		Game.state = newState;
	}

	/**
	 * @return the lvl
	 */
	public static final int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl the lvl to set
	 */
	public static final void setLvl(int lvl) {
		Game.lvl = lvl;
	}
}
