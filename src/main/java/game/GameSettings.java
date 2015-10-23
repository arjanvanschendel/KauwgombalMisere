package game;

import filehandling.File;
import filehandling.FileEntry;
import filehandling.FileParser;

/**
 * GameVariables contains basic variables used in-game.
 * 
 * @author Jasper
 *
 */
public final class GameSettings {

	/**
	 * GameSettings constructor.
	 */
	private GameSettings() {

	}

	/**
	 * Current sound effects volume.
	 */
	private static float sfxVolume = 100;

	/**
	 * Current music volume.
	 */
	private static float musicVolume = 100;

	/**
	 * Current music volume.
	 */
	private static boolean fullscreen = true;

	private static boolean reload = false;

	private static boolean exit;

	/**
	 * @return the volume
	 */
	public static float getSFXVolume() {
		return sfxVolume;
	}

	/**
	 * @param vol
	 *            the volume to set
	 */
	public static void setSFXVolume(final float vol) {
		sfxVolume = vol;
	}

	/**
	 * @return the volume
	 */
	public static float getMusicVolume() {
		return musicVolume;
	}

	/**
	 * @param vol
	 *            the volume to set
	 */
	public static void setMusicVolume(final float vol) {
		musicVolume = vol;
	}

	/**
	 * @return the fullscreen
	 */
	public static boolean isFullscreen() {
		return fullscreen;
	}

	/**
	 * @param full
	 *            the fullscreen to set
	 */
	public static void setFullscreen(final boolean full) {
		if (fullscreen != full) {
			reload = true;
		}
		fullscreen = full;
	}

	/**
	 * Let's the launcher know that it should reload the window.
	 * 
	 * @return reload
	 */
	public static boolean reload() {
		return reload;
	}

	/**
	 * Let's the launcher know that it should exit the program.
	 * 
	 * @return reload
	 */
	public static boolean exit() {
		return exit;
	}

	/**
	 * @param r
	 *            sets reload.
	 */
	public static void setReload(final boolean r) {
		reload = r;
	}

	/**
	 * @param e
	 *            sets exit.
	 */
	public static void setExit(final boolean e) {
		exit = e;
	}

	/**
	 * Loads settings from 'res/settings.set'.
	 */
	public static void load() {
		java.io.File f = new java.io.File("res/settings.set");
		if (f.exists() && !f.isDirectory()) {
			FileParser fp = new FileParser("res/settings.set");
			for (FileEntry entry : fp.getEntries()) {
				String type = entry.getName();
				String value = entry.getParameters().get(0);
				if (type.equals("fullscreen")) {
					if (value.equals("true")) {
						setFullscreen(true);
					} else {
						setFullscreen(false);
					}
				} else if (type.equals("sfxVolume")) {
					setSFXVolume(Float.parseFloat(value));

				} else if (type.equals("musicVolume")) {
					setMusicVolume(Float.parseFloat(value));
				}
			}
		}
	}

	
	/**
	 * Saves current settings to 'res/settings.set'.
	 */
	public static void save() {
		File f = new File();
		f.add(new FileEntry("fullscreen", Boolean.toString(fullscreen)));
		f.add(new FileEntry("sfxVolume", Float.toString(sfxVolume)));
		f.add(new FileEntry("musicVolume", Float.toString(musicVolume)));
		f.save("res/settings.set");
	}
}
