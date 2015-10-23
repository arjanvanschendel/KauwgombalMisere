package commands;

import game.GameSettings;

/**
 * 
 * Command used to set the game to windowed mode.
 * 
 * @author Jasper
 *
 */
public class WindowedCommand implements Command {

	@Override
	public void execute() {
		GameSettings.setFullscreen(false);
	}

	@Override
	public void undo() {
		GameSettings.setFullscreen(true);
	}

}
