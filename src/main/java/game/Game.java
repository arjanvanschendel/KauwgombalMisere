package game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import objects.GameObject;
import objects.ScorePopUp;

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
	private static int lives;
	public static final ArrayList<Sound> sounds = new ArrayList<Sound>();
	public static final ArrayList<Texture> textures = new ArrayList<Texture>();
	private static ArrayList<GameObject> popUpObjects = new ArrayList<GameObject>();
	private int frames;
	private int lastFrames;
	private long lastUpdateTime;
	private int WIDTH;
	private TrueTypeFont fpsFont;

	/**
	 * Game: constructor.
	 */
	public Game() {
		setLives(3);
		Logger.add("game started");
		loadTextures();
		loadSounds();
		setLvl(1);
		maxLvl = countLevels();
		loadLevel(lvl);
		mm = new MainMenu();
		setState(2);

		WIDTH = Launcher.getCAMWIDTH();

		fpsFont = new TrueTypeFont(new Font("Courier New", Font.PLAIN, 12),
				true);
	}

	private void loadSounds() {

		sounds.add(new Sound("sounds/arrowHitBall.wav"));
		sounds.add(new Sound("sounds/arrowHitCeiling.wav"));
		sounds.add(new Sound("sounds/arrowShoot.wav"));
		sounds.add(new Sound("sounds/ballBounce.wav"));
		sounds.add(new Sound("sounds/playerHit.wav"));
		// Main menu sound
		sounds.add(new Sound("sounds/arrowHitCeiling.wav"));
	}

	/**
	 * loadTextures: load the textures onto memory.
	 */
	private void loadTextures() {
		textures.add(0, new Texture("res/IdleSprite.png", GL11.GL_NEAREST,
				GL11.GL_REPEAT));
		textures.add(1, new Texture("res/Run.png", GL11.GL_NEAREST,
				GL11.GL_REPEAT));
		textures.add(2, new Texture("res/arrow.png", GL11.GL_NEAREST,
				GL11.GL_CLAMP));
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
	public final void update(double deltaTime) {
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
			updateThePopUps(deltaTime);
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
			// System.exit(1);

		}
		Mouse.resetReleased();
		Keyboard.resetReleased();
	}

	/**
	 * Adds a popup.
	 * @param popUp
	 */
	public static void addPopUp(final ScorePopUp popUp) {
		if (popUpObjects.size() > 3) {
			for (int i = 0; i < popUpObjects.size() - 1; ++i) {
				popUpObjects.remove(0);
			}
		}
		popUpObjects.add(popUp);
	}

	/**
	 * Updates the popups.
	 * @param deltaTime
	 */
	public final void updateThePopUps(final double deltaTime) {
		for (GameObject popup : popUpObjects) {
			popup.update(deltaTime);
		}
	}

	/**
	 * Renders the popups.
	 */
	public final void renderThePopUps() {
		for (GameObject render : popUpObjects) {
			render.render();
		}
	}

	/**
	 * Updates the current FPScount.
	 */
	public final void updateFPS() {
		// Counting frames
		if (System.currentTimeMillis() < lastUpdateTime + 1000) {
			frames += 1;
		} else {
			lastFrames = frames;
			lastUpdateTime = System.currentTimeMillis();
			frames = 0;
		}
	}

	/**
	 * render: Render the graphics of the game.
	 */
	public final void render() {

		updateFPS();

		switch (state) {
		case (0):
			// game
			currentLvl.render();
			TextureImpl.bindNone();
			renderThePopUps();
			GL11.glScalef(1, -1, 1);
			// TextureImpl.bindNone();
			String levelString = "Level " + lvl + ": " + currentLvl.getName();
			String scoreString = "Score: " + Level.getScore();
			Launcher.getFont().drawString(-400, -540, scoreString, Color.blue);
			Launcher.getFont().drawString(
					-(float) Launcher.getFont().getWidth(levelString) / 2,
					-100, levelString, Color.black);

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

		// Drawing the FPScounter
		TextureImpl.bindNone();
		GL11.glScalef(1, -1, 1);
		if (lastFrames > 0) {
			fpsFont.drawString(-WIDTH / 2 + 5, -15, lastFrames + " FPS",
					Color.white);
		} else {
			fpsFont.drawString(-WIDTH / 2 + 5, -15, "? FPS", Color.white);
		}
		GL11.glScalef(1, -1, 1);
	}

	/**
	 * TODO: JAVADOC!
	 * @param x
	 * @param y
	 * @param size
	 */
	public static void ballhit(float x, float y, char size) {
		String s;
		if (size == 'b') {
			s = "20";
		} else {
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
	 * Sets a new game for the game.
	 * @param newState The new state for the game to use
	 */
	public static void setState(final int newState) {
		Game.state = newState;
	}

	/**
	 * @return the lvl
	 */
	public static final int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl
	 *            the lvl to set
	 */
	public static final void setLvl(final int lvl) {
		Game.lvl = lvl;
	}

	/**
	 * @return the lives
	 */
	public static final int getLives() {
		return lives;
	}

	/**
	 * @param lives
	 *            the lives to set
	 */
	public static final void setLives(final int lives) {
		Game.lives = lives;
	}

	/**
	 *  Lowers the available lives by one.
	 */
	public static final void decreaseLives() {
		Game.lives--;
	}
}
