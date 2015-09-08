package main;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

/**
 * Class Game: a Game object represents a game, holding all the levels.
 */
public class Game {
	private int state;
	private Level currentLvl;
	private int lvl;
	private int maxLvl;

	/**
	 * Game: constructor.
	 */
	public Game() {
		// loadTextures();

		lvl = 1;
		maxLvl = 1;
		loadLevel("levels/level" + lvl + ".lvl");
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
			// game won wohoo
		}

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
		if(Keyboard.isKeyReleased(GLFW_KEY_SPACE)) {
			if(state == 2){
				state = 0;
			}
		}
		if(Level.levelComplete() && state == 0){
			state = 3;
		}

		switch(state){
		case(0):
			//Playing
			currentLvl.update(deltaTime);
			break;
		case(1):
			//Paused
			break;
		
		case(2):
			//Main Menu
			//mm.update(deltaTime);
			break;
		case(3):
			System.out.println("new state");
			break;
		default:
			System.out.println("INVALID STATE: "+state+". (Game.update method)");System.exit(-1);
		
		}
	}
	
	/**
	 * render: Render the graphics of the game.
	 */
	public void render() {
		switch (state) {
		case(0):
			//game
			currentLvl.render();
			break;
		case(1):
			//paused
			currentLvl.render();
			break;
		case(2):
			break;
		default:
			System.out.println("INVALID STATE: "+state+". (Game.render method)");System.exit(-1);
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
