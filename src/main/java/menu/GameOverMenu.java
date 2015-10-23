package menu;

import static org.lwjgl.opengl.GL11.glColor4f;
import game.Game;
import game.GameVariables;
import game.Launcher;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import utillities.Sound;
import shapes.Point;
import utillities.Texture;

/**
 * Draws and updates the game over menu.
 * 
 * @author Arjan
 *
 */
public class GameOverMenu {

	private Game game = Game.getInstance();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private Button replayBtn;
	private Button menuBtn;
	private Button rageBtn;
	private Texture background;
	private Texture background2;
	private Texture background3;
	private Texture background4;
	private int width;
	private int height;
	private int bgState;
	private long lastUpdate;

	/**
	 * A class to draw and maintain the main menu.
	 */
	public GameOverMenu() {
		
		Point playPos = new Point(-375f, 30f);
		replayBtn = new Button(playPos, 150f, 25f, java.awt.Color.white,
				"Retry");
		buttons.add(replayBtn);
		
		Point optPos = new Point(-75f, 30f);
		menuBtn = new Button(optPos, 150f, 25f, java.awt.Color.white,
				"Main Menu");
		buttons.add(menuBtn);
		
		Point exitPos = new Point(225f,	30f);
		rageBtn = new Button(exitPos, 150f, 25f, java.awt.Color.white,
				"RAGEQUIT");
		buttons.add(rageBtn);

		background = new Texture("res/gameOver.png", GL11.GL_NEAREST,
				GL11.GL_CLAMP);
		background2 = new Texture("res/gameOver2.png", GL11.GL_NEAREST,
				GL11.GL_CLAMP);
		background3 = new Texture("res/gameOver3.png", GL11.GL_NEAREST,
				GL11.GL_CLAMP);
		background4 = new Texture("res/gameOver4.png", GL11.GL_NEAREST,
				GL11.GL_CLAMP);

		width = Launcher.getCamWidth();
		height = Launcher.getCamHeight();
		System.out.println("WxH = " + width + "x" + height);
	}

	/**
	 * update: update the screenobject's state.
	 * 
	 * @param deltaTime
	 *            The speed of the game
	 */
	public final void update(final double deltaTime) {
		if (!game.getMusic().get("Solar_Sailer").isPlaying()) {
			for (Entry<String, Sound> entry : game.getMusic().entrySet()) {
				entry.getValue().stop();
			}
			if (game.getCurrentLvl().getLevelSong() != null) {
				game.getCurrentLvl().getLevelSong().stop();
			}
			game.getMusic().get("Solar_Sailer").loop(-1);
		}

		for (Button btn : buttons) {
			btn.update(deltaTime);
		}

		if (replayBtn.isClicked()) {
			GameVariables.setLives(3);
			game.setScore(0);
			game.loadLevel(1);
			game.setState(0);
		}
		if (menuBtn.isClicked()) {
			System.out.println("Menu button clicked");
			game.reset();
		}
		if (rageBtn.isClicked()) {
			System.out.println("Exiting system via exit button");
			Launcher.close();
		}
	}

	/**
	 * render: render the screen's background, text, and objects.
	 */
	public final void render() {
		long time = System.currentTimeMillis();
		if (time - lastUpdate > 500) {
			lastUpdate = time;
			switch (bgState) {
			case(0):
				bgState = 1;
				break;
			case(1):
				bgState = 2;
				break;
			case(2):
				bgState = 3;
				break;
			case(3):
				bgState = 0;
				break;
			default:
				System.out.println("Invalid bgState: " + bgState);
				bgState = 0;
				break;
			}
		}
		switch(bgState){
		case(0):
			background.bind();
			break;
		case(1):
			background2.bind();
			break;
		case(2):
			background3.bind();
			break;
		case(3):
			background4.bind();
			break;
		}
		
		

		GL11.glBegin(GL11.GL_QUADS);
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(-(float) width / 2, height);

		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f((float) width / 2, height);

		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f((float) width / 2, 0);

		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(-(float) width / 2, 0);
		GL11.glEnd();
		Texture.disable();
		for (Button btn : buttons) {
			btn.render();
		}
	}

}
