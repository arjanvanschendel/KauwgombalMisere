package utillities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Logger class.
 * 
 * @author Matthias
 *
 */
public final class Logger {

	private static String log;
	private static boolean printLog;

	private Logger() {

	}

	/**
	 * init: initialize logger, set default variables.
	 */
	public static void init() {
		Logger.setLog("");
		Logger.setPrintLog(true);
	}

	/**
	 * add: add a string to the log.
	 * 
	 * @param s
	 *            string to add
	 */
	public static void add(String s) {
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm" + ":ss");
		String currTime = sdf.format(cal.getTime());

		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currDate = sdf.format(cal.getTime());

		//
		String boodschap = currTime + " - " + s;

		Logger.setLog(Logger.getLog() + boodschap + "\n");

		Logger.appendLog("KM " + currDate + ".log", boodschap + "\r\n");

		System.out.println(boodschap);
	}

	/**
	 * appendLog: appends a line s to the log file.
	 * 
	 * @param filename
	 *            name of file
	 * @param s
	 *            string to append
	 */
	public static void appendLog(String filename, String s) {
		try {
			OutputStreamWriter fw = new OutputStreamWriter(
					new FileOutputStream(filename), "UTF-8");
			fw.write(s);
			fw.close();
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}

	/**
	 * @return the log
	 */
	public static String getLog() {
		return log;
	}

	/**
	 * @param log
	 *            the log to set
	 */
	public static void setLog(String log) {
		Logger.log = log;
	}

	/**
	 * @return the printLog
	 */
	public static boolean isPrintLog() {
		return printLog;
	}

	/**
	 * @param printLog
	 *            the printLog to set
	 */
	public static void setPrintLog(boolean printLog) {
		Logger.printLog = printLog;
	}
}
