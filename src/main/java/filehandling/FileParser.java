package filehandling;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Gets a file location as input and handles the content.
 * 
 * @author Jasper
 *
 */
public class FileParser {

	private File entries = new File();

	/**
	 * Reads the file and saves all entries.
	 * 
	 * @param loc
	 *            Location of the file
	 */
	public FileParser(String loc) {

		InputStreamReader inputStreamReader;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(loc),
					StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {

				if (!line.contains("//")) {
					int parameterStart = line.indexOf('(');
					int parameterEnd = line.indexOf(')');

					if (parameterStart != -1 && parameterEnd != -1) {
						String type = line.substring(0, parameterStart);
						String param = line.substring(parameterStart + 1,
								parameterEnd);
						ArrayList<String> para = new ArrayList<String>(
								Arrays.asList(param.split("\\,")));
						entries.add(new FileEntry(type, para));
					}
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the entries
	 */
	public File getEntries() {
		return entries;
	}
}
