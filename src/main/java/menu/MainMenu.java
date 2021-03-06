package menu;

import static org.lwjgl.opengl.GL11.glColor4f;
import game.Game;
import game.GameSettings;
import game.Launcher;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import utillities.Sound;
import shapes.Point;
import utillities.Texture;

/**
 * Draws and updates the main menu.
 * 
 * @author Arjan
 *
 */
public class MainMenu {

	private Game game = Game.getInstance();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private Button playBtn;
	private Button exitBtn;
	private Button optBtn;
	private Texture background;
	private int width;
	private int height;

	/**
	 * A class to draw and maintain the main menu.
	 */
	public MainMenu() {
		Point playPos = new Point(-75f,
				(float) Launcher.getCamHeight() / 3 + 40);
		playBtn = new Button(playPos, 150f, 25f, java.awt.Color.white, "Play");
		buttons.add(playBtn);
		Point optPos = new Point(-75f, (float) Launcher.getCamHeight() / 3);
		optBtn = new Button(optPos, 150f, 25f, java.awt.Color.white, "Options");
		buttons.add(optBtn);
		Point exitPos = new Point(-75f,
				(float) Launcher.getCamHeight() / 3 - 40);
		exitBtn = new Button(exitPos, 150f, 25f, java.awt.Color.white, "Exit");
		buttons.add(exitBtn);
		background = new Texture("res/KMmain.png", GL11.GL_NEAREST,
				GL11.GL_CLAMP);

		width = Launcher.getCamWidth();
		height = Launcher.getCamHeight();
		System.out.println("WxH = " + width + "x" + height);
	}

	/**
	 * Finds first positive power of two above the given input.
	 * 
	 * @param number
	 *            any integer
	 * @return a power of two
	 */
	public static int findNextTwo(final int number) {
		int result = 1;
		while (result < number) {
			result *= 2;
		}
		return result;
	}

	/**
	 * update: update the menu-object's state.
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

		if (playBtn.isClicked()) {
			game.setState(0);
		}
		if (optBtn.isClicked()) {
			System.out.println("Options button clicked");
			game.setState(3);
		}
		if (exitBtn.isClicked()) {
			System.out.println("Exiting system via exit button");
			GameSettings.setExit(true);
		}
	}

	/**
	 * render: render the main menu's background, text, and objects.
	 */
	public final void render() {
		background.bind(); // or GL11.glBind(texture.getTextureID());

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
