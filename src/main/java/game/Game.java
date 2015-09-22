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

import utillities.Keyboard;
import utillities.Texture;

//import org.newdawn.slick.opengl.TextureLoader;

/**
 * Class Game: a Game object represents a game, holding all the levels.
 */
public class Game {
	private int state;
	private Level currentLvl;
	private int lvl;
	private int maxLvl;
	public static final ArrayList<Texture> textures = new ArrayList<Texture>();

	/**
	 * Game: constructor.
	 */
	public Game() {
		loadTextures();
		lvl = 1;
		maxLvl = countLevels();
		loadLevel("levels/level" + lvl + ".lvl");
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
	public void loadLevel(String location) {
		currentLvl = new Level(location);
	}

	/**
	 * nextLevel: moves the player to the next level.
	 */
	public void nextLevel() {
		if (lvl < maxLvl) {
			lvl++;
			loadLevel("levels/level" + lvl + ".lvl");
		} else {
			state = 3;
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
				state = 1;
			} else if (state == 1) {
				state = 0;
			}
		}
		if (Keyboard.isKeyReleased(GLFW_KEY_SPACE)) {
			if (state == 2) {
				state = 0;
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
			// mm.update(deltaTime);
			break;
		case (3):
			System.out.println("new state");
			break;
		default:
			System.out.println("INVALID STATE: " + state
					+ ". (Game.update method)");
			state = 2;
			//System.exit(1);

		}
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
			Launcher.font.drawString(-50, -100, "Level " + lvl + ": "
					+ currentLvl.getName(), Color.black);
			GL11.glScalef(1, -1, 1);
			break;
		case (1):
			// paused
			currentLvl.render();
			GL11.glScalef(1, -1, 1);
			TextureImpl.bindNone();
			Launcher.font.drawString(-50, -300, "PAUSED", Color.yellow);
			GL11.glScalef(1, -1, 1);

			break;
		case (2):
			break;
		default:
			System.out.println("INVALID STATE: " + state
					+ ". (Game.render method)");
			// System.exit(1);
			state = 2;
		}
	}

	/**
	 * getState.
	 * 
	 * @return int state
	 */
	public int getState() {
		return state;
	}

	/**
	 * setState.
	 * 
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}
}
