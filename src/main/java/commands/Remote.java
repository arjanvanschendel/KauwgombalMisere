package commands;

import java.util.Stack;

import utillities.Logger;

/**
 * 
 * This remote controls the GameSettings for fullscreen and SFX/Music volume.
 * 
 * @author Jasper
 *
 */
public class Remote {

	private Stack<Command> history = new Stack<Command>();
	private Stack<Command> future = new Stack<Command>();

	private Command fullscreen = new FullscreenCommand();
	private Command windowed = new WindowedCommand();
	private Command sfx;
	private Command music;

	/**
	 * Constructor sets sfx and music to an empty command since they have not
	 * been set.
	 */
	public Remote() {
		sfx = new NoCommand();
		music = new NoCommand();
	}

	/**
	 * Sets the new SfxCommand.
	 * 
	 * @param c
	 *            New SfxCommand
	 */
	public void setSfxCommand(Command c) {
		sfx = c;
	}

	/**
	 * Sets the new MusicCommand.
	 * 
	 * @param c
	 *            New MusicCommand
	 */
	public void setMusicCommand(Command c) {
		music = c;
	}

	/**
	 * Executes the music command and adds it to the history. Also clears the
	 * future since the current state is with the most recent change.
	 */
	public void adjustMusicVolume() {
		music.execute();
		history.push(music);
		future.clear();
	}

	/**
	 * Executes the sfx command and adds it to the history. Also clears the
	 * future since the current state is with the most recent change.
	 */
	public void adjustSfxVolume() {
		sfx.execute();
		history.push(sfx);
		future.clear();
	}

	/**
	 * Executes the fullscreen command and adds it to the history. Also clears
	 * the future since the current state is with the most recent change.
	 */
	public void fullscreen() {
		fullscreen.execute();
		history.add(fullscreen);
		future.clear();
	}

	/**
	 * Executes the windowed command and adds it to the history. Also clears the
	 * future since the current state is with the most recent change.
	 */
	public void windowed() {
		windowed.execute();
		history.push(windowed);
		future.clear();
	}

	/**
	 * Pops the last command from the history stack and executes the undo
	 * method. Also adds this command to the future to be able to redo the
	 * command.
	 */
	public void undo() {
		if (!history.isEmpty()) {
			Command lastCommand = history.pop();
			future.push(lastCommand);
			lastCommand.undo();
		}
		Logger.add("Undo last setting");
	}

	/**
	 * Pops the next command from the future stack and executes the command.
	 * Also adds this command to the history command to be able to undo the
	 * command.
	 */
	public void redo() {

		if (!future.isEmpty()) {
			Command nextCommand = future.pop();
			history.push(nextCommand);
			nextCommand.execute();
		}
		Logger.add("Redo next setting");
	}
}
