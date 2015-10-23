package commands;

import java.util.Map.Entry;

import utillities.Sound;
import game.Game;
import game.GameSettings;

/**
 * 
 * Command used to adjust the SFX volume.
 * 
 * @author Jasper
 *
 */
public class SfxVolumeCommand implements Command {

	private float undoValue;
	private float newValue;
	private Game game = Game.getInstance();

	/**
	 * Constructor sets the new float value of the command.
	 * 
	 * @param nVolume
	 *            New value used in execute.
	 */
	public SfxVolumeCommand(float nVolume) {
		newValue = nVolume;
	}

	@Override
	public void execute() {
		undoValue = GameSettings.getSFXVolume();
		GameSettings.setSFXVolume(newValue);

		for (Entry<String, Sound> entry : game.getSoundFX().entrySet()) {
			entry.getValue().setVolume(GameSettings.getSFXVolume());
		}
	}

	@Override
	public void undo() {
		GameSettings.setSFXVolume(undoValue);

		for (Entry<String, Sound> entry : game.getSoundFX().entrySet()) {
			entry.getValue().setVolume(GameSettings.getSFXVolume());
		}
	}

}
