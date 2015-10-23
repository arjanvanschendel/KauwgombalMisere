package commands;

import java.util.Map.Entry;

import utillities.Sound;
import game.Game;
import game.GameSettings;

/**
 * 
 * Command used to adjust the Music volume.
 * 
 * @author Jasper
 *
 */
public class MusicVolumeCommand implements Command {

	private float undoValue;
	private float newValue;
	private Game game = Game.getInstance();

	/**
	 * Constructor sets the new float value of the command.
	 * 
	 * @param nVolume
	 *            New value used in execute to set the music volume.
	 */
	public MusicVolumeCommand(float nVolume) {
		newValue = nVolume;
	}

	@Override
	public void execute() {
		undoValue = GameSettings.getMusicVolume();
		GameSettings.setMusicVolume(newValue);

		for (Entry<String, Sound> entry : game.getMusic().entrySet()) {
			entry.getValue().setVolume(GameSettings.getMusicVolume());
		}

	}

	@Override
	public void undo() {
		GameSettings.setMusicVolume(undoValue);

		for (Entry<String, Sound> entry : game.getMusic().entrySet()) {
			entry.getValue().setVolume(GameSettings.getMusicVolume());
		}
	}

}
