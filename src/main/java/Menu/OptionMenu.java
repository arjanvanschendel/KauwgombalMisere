package Menu;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.system.MemoryUtil.NULL;
import game.Game;
import game.GameSettings;
import game.Launcher;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import shapes.TextureBox;
import utillities.Texture;

import org.newdawn.slick.opengl.TextureImpl;

/**
 * Draws and updates the main menu.
 * 
 * @author Arjan
 *
 */
public class OptionMenu {

	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<Slider> sliders = new ArrayList<Slider>();
	private ArrayList<CheckBox> checkboxes = new ArrayList<CheckBox>();
	private Button saveBtn;
	private Button backBtn;
	private CheckBox fullscreenCheckbox;
	private Texture Background;
	private int WIDTH;
	private int HEIGHT;
	private Slider sfxVolumeSlider;
	private Slider musicVolumeSlider;

	/**
	 * A class to draw and maintain the main menu.
	 */
	public OptionMenu() {
		Background = new Texture("res/KMmain.png", GL11.GL_NEAREST,
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

		WIDTH = Launcher.getCAMWIDTH();
		HEIGHT = Launcher.getCAMHEIGHT();
		System.out.println("WxH = " + WIDTH + "x" + HEIGHT);

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
		if (!Game.sounds.get(5).isPlaying()) {
			Game.sounds.get(5).play();
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
			Game.setState(2);
		}

		if (sfxVolumeSlider.isChanged()) {
			GameSettings.setSFXVolume(sfxVolumeSlider.getPercentage());
			Game.sounds.get(2).setVolume(GameSettings.getSFXVolume());
			Game.sounds.get(2).play();
		}

		if (musicVolumeSlider.isChanged()) {
			GameSettings.setMusicVolume(musicVolumeSlider.getPercentage());
			Game.sounds.get(5).setVolume(GameSettings.getMusicVolume());
		}
	}

	private void save() {
		Game.sounds.get(0).setVolume(GameSettings.getSFXVolume());
		Game.sounds.get(1).setVolume(GameSettings.getSFXVolume());
		Game.sounds.get(2).setVolume(GameSettings.getSFXVolume());
		Game.sounds.get(3).setVolume(GameSettings.getSFXVolume());
		Game.sounds.get(4).setVolume(GameSettings.getSFXVolume());
		Game.sounds.get(5).setVolume(GameSettings.getMusicVolume());
		GameSettings.setFullscreen(fullscreenCheckbox.isChecked());
	}

	/**
	 * render: render the main menu's background, text, and objects.
	 */
	public final void render() {
		Background.bind(); // or GL11.glBind(texture.getTextureID());

		GL11.glBegin(GL11.GL_QUADS);
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(-(float) WIDTH / 2, HEIGHT);

		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f((float) WIDTH / 2, HEIGHT);

		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f((float) WIDTH / 2, 0);

		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(-(float) WIDTH / 2, 0);

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
	}
}
