package commands;

/**
 * 
 * Command interface used to execute and undo commands.
 * 
 * @author Jasper
 *
 */
public interface Command {

	/**
	 * Execute the command.
	 */
	void execute();

	/**
	 * Undo the command.
	 */
	void undo();

}
