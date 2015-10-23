package commands;

import game.GameSettings;

/**
 * 
 * Command used to set the game to fullscreen.
 * 
 * @author Jasper
 *
 */
public class FullscreenCommand implements Command {

	@Override
	public void execute() {
		GameSettings.setFullscreen(true);
		System.out.println("fullscreen");
	}

	@Override
	public void undo() {
		GameSettings.setFullscreen(false);
	}

}
