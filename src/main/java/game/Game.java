package game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

import java.io.File;
import java.util.ArrayList;

import menu.MainMenu;
import objects.GameObject;
import objects.ScorePopUp;

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
 * Class Game: a Game object represents a game, holding all the levels.
 */
public final class Game {

    private static Game unique = new Game();
    private int state;
    private Level currentLvl;
    private int score;
    private int lvl;
    private int maxLvl;
    private MainMenu menu;
    private int lives;
    private ArrayList<Sound> sounds = new ArrayList<Sound>();
    private ArrayList<Texture> textures = new ArrayList<Texture>();
    private ArrayList<GameObject> popUpObjects = new ArrayList<GameObject>();

    /**
     * Only instanced once
     * Game: constructor.
     */
    private Game() {
	Logger.add("Instanced");
    }

    /**
     * This method sets up the resources for the game
     */
    public void setup() {
	loadTextures();
	loadSounds();
	maxLvl = countLevels();
	menu = new MainMenu();
    }
    
    /**
     * This method resets the progress of the game
     */
    public void reset() {
	setLives(3);
	loadLevel(1);
	setState(2);
	setScore(0);
    }
    
    public static Game getInstance() {
	return unique;
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
    public void loadLevel(int number) {
	setLvl(number);
	Logger.add("Loading in new level " + number);
	currentLvl = new Level("levels/level" + number + ".lvl");
    }

    /**
     * nextLevel: moves the player to the next level.
     */
    public void nextLevel() {
	if (lvl < maxLvl) {
	    Logger.add("next level");
	    loadLevel(getLvl() + 1);
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
	if (currentLvl.levelComplete() && state == 0) {
	    Logger.add("complete " + currentLvl.levelComplete());
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
	    menu.update(deltaTime);
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
     * 
     * @param popUp
     */
    public void addPopUp(ScorePopUp popUp) {
	if (popUpObjects.size() > 3) {
	    for (int i = 0; i < popUpObjects.size() - 1; ++i) {
		popUpObjects.remove(0);
	    }
	}
	popUpObjects.add(popUp);
    }
    
    /**
     * 
     * @param deltaTime
     */
    public void updateThePopUps(double deltaTime) {
	for (GameObject popup : popUpObjects) {
	    popup.update(deltaTime);
	}
    }

    /**
     * 
     */
    public void renderThePopUps() {
	for (GameObject render : popUpObjects) {
	    render.render();
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
	    TextureImpl.bindNone();
	    renderThePopUps();
	    GL11.glScalef(1, -1, 1);
	    // TextureImpl.bindNone();
	    String levelString = "Level " + lvl + ": " + currentLvl.getName();
	    String scoreString = "Score: " + getScore();
	    String livesString = "Lives : " + lives;

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
	default:
	    System.out.println("INVALID STATE: " + state
		    + ". (Game.render method)");
	    // System.exit(1);
	    setState(2);
	}
    }

//    public void ballHit(float x, float y, char size) {
//	String s;
//	if (size == 'b') {
//	    s = "20";
//	} else {
//	    s = "10";
//	}
//    }

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
    public void setState(int newState) {
	state = newState;
    }

    /**
     * @return the lvl
     */
    public final int getLvl() {
	return lvl;
    }

    /**
     * @param lvl
     *            the lvl to set
     */
    public final void setLvl(int lvl) {
	this.lvl = lvl;
    }

    /**
     * @return the lives
     */
    public final int getLives() {
	return lives;
    }

    /**
     * @param lives
     *            the lives to set
     */
    public final void setLives(int lives) {
	this.lives = lives;
    }

    /**
     * @param lives
     *            the lives to set
     */
    public final void decreaseLives() {
	lives--;
    }

    /**
     * @return the sounds
     */
    public final ArrayList<Sound> getSounds() {
        return sounds;
    }

    /**
     * @param sounds the sounds to set
     */
    public final void setSounds(ArrayList<Sound> sounds) {
        this.sounds = sounds;
    }

    /**
     * @return the textures
     */
    public final ArrayList<Texture> getTextures() {
        return textures;
    }

    /**
     * @param textures the textures to set
     */
    public final void setTextures(ArrayList<Texture> textures) {
        this.textures = textures;
    }

    /**
     * @return the popUpObjects
     */
    public final ArrayList<GameObject> getPopUpObjects() {
        return popUpObjects;
    }

    /**
     * @param popUpObjects the popUpObjects to set
     */
    public final void setPopUpObjects(ArrayList<GameObject> popUpObjects) {
        this.popUpObjects = popUpObjects;
    }

    /**
     * @return the currentLvl
     */
    public final Level getCurrentLvl() {
        return currentLvl;
    }

    /**
     * @param currentLvl the currentLvl to set
     */
    public final void setCurrentLvl(Level currentLvl) {
        this.currentLvl = currentLvl;
    }

    /**
     * @return the score
     */
    public final int getScore() {
	return score;
    }

    /**
     * @param score the score to set
     */
    public final void setScore(int score) {
	this.score = score;
    }
}
