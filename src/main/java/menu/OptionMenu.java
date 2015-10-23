package menu;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Z;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Y;
import static org.lwjgl.opengl.GL11.glColor4f;
import game.Game;
import game.GameSettings;
import game.Launcher;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

import shapes.Point;
import utillities.Keyboard;
import utillities.Sound;
import utillities.Texture;
import commands.MusicVolumeCommand;
import commands.Remote;
import commands.SfxVolumeCommand;

/**
 * Draws and updates the option menu.
 * 
 * @author Jasper, Arjan
 *
 */
public class OptionMenu {

	private Game game = Game.getInstance();
	/**
	 * Font type used.
	 */
	private Font awtFont = new Font("Courier New", Font.BOLD, 24);
	/**
	 * Font used to draw text.
	 */
	private TrueTypeFont font = new TrueTypeFont(awtFont, true);
	/**
	 * Buttons active in current menu.
	 */
	private ArrayList<Button> buttons = new ArrayList<Button>();
	/**
	 * Sliders active in current menu.
	 */
	private ArrayList<Slider> sliders = new ArrayList<Slider>();
	/**
	 * Checkboxes active in current menu.
	 */
	private ArrayList<CheckBox> checkboxes = new ArrayList<CheckBox>();
	/**
	 * Buttons to save the setting.
	 */
	private Button saveBtn;
	/**
	 * Buttons to save the setting.
	 */
	private Button backBtn;
	/**
	 * Checkbox to set fullscreen.
	 */
	private CheckBox fullscreenCheckbox;
	/**
	 * Background texture.
	 */
	private Texture background;
	/**
	 * Width of the scene.
	 */
	private int width;
	/**
	 * Height of the screen.
	 */
	private int height;
	/**
	 * Slider used to control sfx volume slider.
	 */
	private Slider sfxVolumeSlider;
	/**
	 * Slider used to control music volume slider.
	 */
	private Slider musicVolumeSlider;
	private Remote r;
	private boolean undo;

	/**
	 * A class to draw and maintain the main menu.
	 */
	public OptionMenu() {
		background = new Texture("res/KMmain.png", GL11.GL_NEAREST,
				GL11.GL_CLAMP);

		Point savePos = new Point((float) -Launcher.getCamWidth() / 10,
				(float) Launcher.getCamHeight() / 3 - 150);
		saveBtn = new Button(savePos, 150f, 25f, java.awt.Color.white, "Save");
		buttons.add(saveBtn);

		Point backPos = new Point((float) Launcher.getCamWidth() / 10,
				(float) Launcher.getCamHeight() / 3 - 150);
		backBtn = new Button(backPos, 150f, 25f, java.awt.Color.white, "Back");
		buttons.add(backBtn);

		Point sfxPos = new Point((float) Launcher.getCamWidth() / 10,
				(float) Launcher.getCamHeight() / 3 + 180);
		sfxVolumeSlider = new Slider(sfxPos, 200f, 15f, java.awt.Color.blue,
				GameSettings.getSFXVolume());
		sliders.add(sfxVolumeSlider);

		Point musicPos = new Point((float) Launcher.getCamWidth() / 10,
				(float) Launcher.getCamHeight() / 3 + 110);
		musicVolumeSlider = new Slider(musicPos, 200f, 15f,
				java.awt.Color.blue, GameSettings.getMusicVolume());
		sliders.add(musicVolumeSlider);

		Point pos = new Point((float) Launcher.getCamWidth() / 10,
				(float) Launcher.getCamHeight() / 3 + 30);
		fullscreenCheckbox = new CheckBox(pos, 20f, java.awt.Color.blue,
				GameSettings.isFullscreen());
		checkboxes.add(fullscreenCheckbox);

		width = Launcher.getCamWidth();
		height = Launcher.getCamHeight();
		System.out.println("WxH = " + width + "x" + height);

		r = new Remote();

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

		for (Slider sld : sliders) {
			sld.update(deltaTime);
		}

		for (CheckBox chk : checkboxes) {
			chk.update(deltaTime);
		}

		if (saveBtn.isClicked()) {
			System.out.println("save");
			GameSettings.save();
		}

		if (backBtn.isClicked()) {
			game.setState(2);
		}

		if (sfxVolumeSlider.isChanged()) {
			r.setSfxCommand(new SfxVolumeCommand(sfxVolumeSlider
					.getPercentage()));
			r.adjustSfxVolume();
			game.getSoundFX().get("playerHit").play();
		}

		if (musicVolumeSlider.isChanged()) {
			r.setMusicCommand(new MusicVolumeCommand(musicVolumeSlider
					.getPercentage()));
			r.adjustMusicVolume();
		}

		if (fullscreenCheckbox.isChecked() != GameSettings.isFullscreen()) {
			if (fullscreenCheckbox.isChecked()) {
				r.fullscreen();
			} else {
				r.windowed();
			}
		}

		if (Keyboard.isKeyDown(GLFW_KEY_LEFT_CONTROL)
				&& Keyboard.isKeyDown(GLFW_KEY_Z)) {
			if (!undo) {

				undo = true;
				r.undo();
				sfxVolumeSlider.setPercentage(GameSettings.getSFXVolume());
				musicVolumeSlider.setPercentage(GameSettings.getMusicVolume());
				fullscreenCheckbox.setClicked(GameSettings.isFullscreen());
			}
		} else if (Keyboard.isKeyDown(GLFW_KEY_LEFT_CONTROL)
				&& Keyboard.isKeyDown(GLFW_KEY_Y)) {

			if (!undo) {
				undo = true;
				r.redo();
				sfxVolumeSlider.setPercentage(GameSettings.getSFXVolume());
				musicVolumeSlider.setPercentage(GameSettings.getMusicVolume());
				fullscreenCheckbox.setClicked(GameSettings.isFullscreen());
			}
		} else {
			undo = false;
		}
	}

	/**
	 * render: render the main menu's background, text, and objects.
	 */
	public final void render() {
		background.bind();

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

		for (Slider sld : sliders) {
			sld.render();
		}

		for (CheckBox chk : checkboxes) {
			chk.render();
		}

		GL11.glScalef(1, -1, 1);
		TextureImpl.bindNone();
		font.drawString(
				-font.getWidth("Sound Effect Volume:"),
				(float) -Launcher.getCamHeight() / 3f - 180
						- font.getHeight("Sound Effect Volume:") / 2f,
				"Sound Effect Volume:", Color.white);

		font.drawString(
				-font.getWidth("Music Effect Volume:"),
				(float) -Launcher.getCamHeight() / 3f - 110
						- font.getHeight("Music Effect Volume:") / 2f,
				"Music Effect Volume:", Color.white);

		font.drawString(-font.getWidth("Fullscreen:"),
				(float) -Launcher.getCamHeight() / 3f - 50, "Fullscreen:",
				Color.white);
		GL11.glScalef(1, -1, 1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

	}
}
