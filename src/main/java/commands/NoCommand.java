package commands;

/**
 * 
 * Empty command to prevent null objects to be called.
 * 
 * @author Jasper
 *
 */
public class NoCommand implements Command {

	@Override
	public void execute() {

	}

	@Override
	public void undo() {

	}

}
