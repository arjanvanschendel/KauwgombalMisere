package filehandling;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Holds file entries and can save them.
 * 
 * @author Jasper
 *
 */
public class File extends ArrayList<FileEntry> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Saves this file to a location.
	 * 
	 * @param location
	 *            The location to save to
	 */
	public void save(String location) {
		try {
			OutputStream stream = new FileOutputStream(location);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(stream,
					StandardCharsets.UTF_8), true);

			for (FileEntry entry : this) {
				out.print(entry.getName() + "(" + entry.getParameters().get(0));

				for (int i = 1; i < entry.getParameters().size(); i++) {
					 String param = entry.getParameters().get(i);
					 out.print(", " + param);
				}
				out.print(")" + "\n");
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
