package game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import menu.MainMenu;
import menu.OptionMenu;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

import utillities.Keyboard;
import utillities.Logger;
import utillities.Mouse;
import utillities.Sound;
import utillities.Texture;

//import org.newdawn.slick.opengl.TextureLoader;

/**
 * @author Luke, Jasper Class Game: a Game object represents a game, holding all
 *         the levels.
 */
public final class Game {

	private static Game unique = new Game();
	private int state;
	private Level currentLvl;
	private int score;
	private int lvl;
	private int maxLvl;
	private MainMenu menu;
	private OptionMenu options;
	private HashMap<String, Sound> soundFX = new HashMap<String, Sound>();
	private HashMap<String, Sound> music = new HashMap<String, Sound>();
	private HashMap<String, Texture> textures = new HashMap<String, Texture>();

	/**
	 * Only instanced once Game: constructor.
	 */
	private Game() {
		Logger.add("Instanced");
	}

	/**
	 * This method sets up the resources for the game.
	 */
	public void setup() {
		loadTextures();
		loadSounds();
		maxLvl = countLevels();
		menu = new MainMenu();
		options = new OptionMenu();

	}

	/**
	 * This method resets the progress of the game.
	 */
	public void reset() {
		GameVariables.setLives(3);
		setState(2);
		setScore(0);
		loadLevel(1);
	}

	/**
	 * 
	 * @return single instance of game
	 */
	public static Game getInstance() {
		return unique;
	}

	private void loadSounds() {

		// Sound effects
		soundFX.put("arrowHitBall", new Sound("sounds/sfx/arrowHitBall.wav",
				GameSettings.getSFXVolume()));
		soundFX.put("arrowHitCeiling", new Sound(
				"sounds/sfx/arrowHitCeiling.wav", GameSettings.getSFXVolume()));
		soundFX.put("arrowShoot", new Sound("sounds/sfx/arrowShoot.wav",
				GameSettings.getSFXVolume()));
		soundFX.put("ballBounce", new Sound("sounds/sfx/ballBounce.wav",
				GameSettings.getSFXVolume()));
		soundFX.put("playerHit", new Sound("sounds/sfx/playerHit.wav",
				GameSettings.getSFXVolume()));

		// Music Files
		music.put("Solar_Sailer", new Sound("sounds/music/Solar_Sailer.wav",
				GameSettings.getMusicVolume()));
		music.put("Derezzed", new Sound("sounds/music/Derezzed.wav",
				GameSettings.getMusicVolume()));
		music.put("HelemaalMooi", new Sound("sounds/music/HelemaalMooi.wav",
				GameSettings.getMusicVolume()));
		music.put("JOHNCENA", new Sound("sounds/music/JOHNCENA.wav",
				GameSettings.getMusicVolume()));
		music.put("Satisfaction", new Sound("sounds/music/Satisfaction.wav",
				GameSettings.getMusicVolume()));
		music.put("shakeItOff", new Sound("sounds/music/shakeItOff.wav",
				GameSettings.getMusicVolume()));
	}

	/**
	 * loadTextures: load the textures onto memory.
	 */
	private void loadTextures() {
		textures.put("IdleSprite", new Texture("res/IdleSprite.png",
				GL11.GL_NEAREST, GL11.GL_REPEAT));
		textures.put("Run", new Texture("res/Run.png", GL11.GL_NEAREST,
				GL11.GL_REPEAT));
		textures.put("arrow", new Texture("res/arrow.png", GL11.GL_NEAREST,
				GL11.GL_CLAMP));
	}

	/**
	 * 
	 * @param number
	 *            Level number
	 */
	public void loadLevel(int number) {
		setLvl(number);
		Logger.add("Loading in new level " + number);
		currentLvl = new Level("levels/level" + number + ".lvl");
	}

	/**
	 * nextLevel: moves the player to the next level.
	 */
	public void nextLevel() {
		for (Entry<String, Sound> entry : soundFX.entrySet()) {
			entry.getValue().stop();
		}
		if (lvl < maxLvl) {
			Logger.add("next level");
			loadLevel(getLvl() + 1);
		} else {
			Logger.add("game won");
			GameVariables.setLives(3);
			loadLevel(1);
			setState(2);
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
	 * Stops all current songs and plays the song for the current level.
	 */
	public void playCurrentLevelSong() {
		if (currentLvl.getLevelSong() != null && !currentLvl.getLevelSong().isPlaying()) {
			for (Entry<String, Sound> entry : getMusic().entrySet()) {
				entry.getValue().stop();
			}
			currentLvl.getLevelSong().loop(-1);
		}
	}

	/**
	 * update: Update the state of the game.
	 * 
	 * @param deltaTime
	 *            time between frames.
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
			playCurrentLevelSong();
			currentLvl.update(deltaTime);
			break;
		case (1):
			// Paused
			playCurrentLevelSong();
			break;

		case (2):
			// Main Menu
			menu.update(deltaTime);
			break;
		case (3):
			options.update(deltaTime);
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
	 * render: Render the graphics of the game.
	 */
	public void render() {
		switch (state) {
		case (0):
			// game
			currentLvl.render();
			TextureImpl.bindNone();

			GL11.glScalef(1, -1, 1);
			// TextureImpl.bindNone();
			String levelString = "Level " + lvl + ": " + currentLvl.getName();
			String scoreString = "Score: " + getScore();
			String livesString = "Lives : " + GameVariables.getLives();

			// Draw scoreString
			Launcher.getFont().drawString(-400, -540, scoreString, Color.blue);

			// Draw levelString
			Launcher.getFont().drawString(
					-(float) Launcher.getFont().getWidth(levelString) / 2,
					-100, levelString, Color.black);

			// Draw livesString
			Launcher.getFont().drawString(
					-(float) Launcher.getFont().getWidth(livesString) / 2, -70,
					livesString, Color.black);

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
			menu.render();
			break;
		case (3):
			options.render();
			break;
		default:
			System.out.println("INVALID STATE: " + state
					+ ". (Game.render method)");
			// System.exit(1);
			setState(2);
		}
	}

	/**
	 * 
	 * @return int state
	 */
	public int getState() {
		return state;
	}

	/**
	 * 
	 * @param state
	 *            number of state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl
	 *            the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	/**
	 * Decrease lives by one.
	 */
	public void decreaseLives() {
		GameVariables.setLives(GameVariables.getLives() - 1);
	}

	/**
	 * @return the sounds
	 */
	public HashMap<String, Sound> getSoundFX() {
		return soundFX;
	}

	/**
	 * @return the sounds
	 */
	public HashMap<String, Sound> getMusic() {
		return music;
	}

	/**
	 * @param sounds
	 *            the sounds to set
	 */
	public void setSoundFX(HashMap<String, Sound> sounds) {
		this.soundFX = sounds;
	}

	/**
	 * @return the textures
	 */
	public HashMap<String, Texture> getTextures() {
		return textures;
	}

	/**
	 * @param textures
	 *            the textures to set
	 */
	public void setTextures(HashMap<String, Texture> textures) {
		this.textures = textures;
	}

	/**
	 * @return the currentLvl
	 */
	public Level getCurrentLvl() {
		return currentLvl;
	}

	/**
	 * @param currentLvl
	 *            the currentLvl to set
	 */
	public void setCurrentLvl(Level currentLvl) {
		this.currentLvl = currentLvl;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
