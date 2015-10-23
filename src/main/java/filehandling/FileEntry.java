package filehandling;

import java.util.ArrayList;

/**
 * Holds a file entry and its parameters.
 * 
 * @author Jasper
 *
 */
public class FileEntry {

	private String name;
	private ArrayList<String> parameters = new ArrayList<String>();

	/**
	 * Constructor for a FileEntry.
	 * 
	 * @param name
	 *            Name of the entry
	 * @param para
	 *            Parameters of the entry;
	 */
	public FileEntry(String name, ArrayList<String> para) {
		setName(name);
		setParameters(para);
	}

	/**
	 * Constructor for a FileEntry with only one parameter.
	 * 
	 * @param name
	 *            Name of the entry
	 * @param parameter
	 *            Parameter of the entry;
	 */
	public FileEntry(String name, String parameter) {

		setName(name);
		parameters.add(parameter);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parameters
	 */
	public ArrayList<String> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

}
