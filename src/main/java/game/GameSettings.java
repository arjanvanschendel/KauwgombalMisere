package game;

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
	private static boolean fullscreen = false;

	private static boolean reload = false;
	
	/**
	 * @return the volume
	 */
	public static float getSFXVolume() {
		return sfxVolume;
	}
	/**
	 * @param vol the volume to set
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
	 * @param vol the volume to set
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
	 * @param full the fullscreen to set
	 */
	public static void setFullscreen(final boolean full) {
		if (fullscreen != full) {
			reload = true;			
		}
		fullscreen = full;
	}

	/**
	 * Let's the launcher know that it should reload the window.
	 * @return reload 
	 */
	public static boolean reload() {
		return reload;
	}
	/**
	 * @param r sets reload.
	 */
	public static void setReload(final boolean r) {
		reload = r;
	}
}
