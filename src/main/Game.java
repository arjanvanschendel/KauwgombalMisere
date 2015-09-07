package main;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

/**
 * Class Game: a Game object represents a game, holding all the levels.
 * 
 * @author Matthias
 *
 */
public class Game {
	private int state;
	private Level lvl;

	/**
	 * Game: constructor.
	 */
	public Game() {
		// loadTextures();
		loadLevel("levels/level1.lvl");
	}

	/**
	 * loadTextures: load the textures onto memory.
	 */
	private void loadTextures() {
		try {
			TextureLoader.getTexture("PNG", new FileInputStream(new File("res/image.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * loadLevel: load a level.
	 * 
	 * @param location
	 */
	public void loadLevel(String location) {
		lvl = new Level(location);
	}

	/**
	 * update: Update the state of the game.
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

		if (state == 0) {
			// Playing
			lvl.update(deltaTime);
		} else if (state == 1) {
			// Pauzed

		} else if (state == 2) {
			// Main menu
		}
	}

	/**
	 * render: Render the graphics of the game.
	 */
	public void render() {
		if (state == 0) {
			// Playing
			lvl.render();
		} else if (state == 1) {
			// Pauzed
			lvl.render();

		} else if (state == 2) {
			// Main menu
		}
	}

	/**
	 * getState.
	 * @return int state
	 */
	public int getState() {
		return state;
	}

	/**
	 * setState.
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}
}
