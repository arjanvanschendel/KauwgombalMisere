package menu;

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

import utillities.Sound;
import utillities.Texture;

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

	/**
	 * A class to draw and maintain the main menu.
	 */
	public OptionMenu() {
		background = new Texture("res/KMmain.png", GL11.GL_NEAREST,
				GL11.GL_CLAMP);

		saveBtn = new Button(-(float) Launcher.getCAMWIDTH() / 10,
				(float) Launcher.getCAMHEIGHT() / 3 - 150, (float) 150,
				(float) 25, java.awt.Color.white, "Save");
		buttons.add(saveBtn);
		backBtn = new Button(+(float) Launcher.getCAMWIDTH() / 10,
				(float) Launcher.getCAMHEIGHT() / 3 - 150, (float) 150,
				(float) 25, java.awt.Color.white, "Back");
		buttons.add(backBtn);

		sfxVolumeSlider = new Slider(+(float) Launcher.getCAMWIDTH() / 10,
				(float) Launcher.getCAMHEIGHT() / 3 + 180, (float) 200,
				(float) 15, java.awt.Color.blue, GameSettings.getSFXVolume());
		sliders.add(sfxVolumeSlider);
		musicVolumeSlider = new Slider(+(float) Launcher.getCAMWIDTH() / 10,
				(float) Launcher.getCAMHEIGHT() / 3 + 110, (float) 200,
				(float) 15, java.awt.Color.blue, GameSettings.getMusicVolume());
		sliders.add(musicVolumeSlider);

		fullscreenCheckbox = new CheckBox(+(float) Launcher.getCAMWIDTH() / 10,
				(float) Launcher.getCAMHEIGHT() / 3 + 30, 20f,
				java.awt.Color.blue, GameSettings.isFullscreen());
		checkboxes.add(fullscreenCheckbox);

		width = Launcher.getCAMWIDTH();
		height = Launcher.getCAMHEIGHT();
		System.out.println("WxH = " + width + "x" + height);

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
			game.getMusic().get("Solar_Sailer").play();
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
			save();
		}

		if (backBtn.isClicked()) {
			game.setState(2);
		}

		if (sfxVolumeSlider.isChanged()) {
			GameSettings.setSFXVolume(sfxVolumeSlider.getPercentage());
			game.getSoundFX().get("playerHit").setVolume(GameSettings.getSFXVolume());
			game.getSoundFX().get("playerHit").play();
		}

		if (musicVolumeSlider.isChanged()) {
			GameSettings.setMusicVolume(musicVolumeSlider.getPercentage());
			game.getMusic().get("Solar_Sailer").setVolume(GameSettings.getMusicVolume());
		}
	}

	/**
	 * Method to save settings.
	 */
	private void save() {
		for (Entry<String, Sound> entry : game.getSoundFX().entrySet()) {
			entry.getValue().setVolume(GameSettings.getSFXVolume());
		}

		for (Entry<String, Sound> entry : game.getMusic().entrySet()) {
			entry.getValue().setVolume(GameSettings.getMusicVolume());
		}
		GameSettings.setFullscreen(fullscreenCheckbox.isChecked());
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
				(float) -Launcher.getCAMHEIGHT() / 3f - 180
						- font.getHeight("Sound Effect Volume:") / 2f,
				"Sound Effect Volume:", Color.white);

		font.drawString(
				-font.getWidth("Music Effect Volume:"),
				(float) -Launcher.getCAMHEIGHT() / 3f - 110
						- font.getHeight("Music Effect Volume:") / 2f,
				"Music Effect Volume:", Color.white);

		font.drawString(-font.getWidth("Fullscreen:"),
				(float) -Launcher.getCAMHEIGHT() / 3f - 50, "Fullscreen:",
				Color.white);
		GL11.glScalef(1, -1, 1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

	}
}
