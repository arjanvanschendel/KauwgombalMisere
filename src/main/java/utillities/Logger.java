package utillities;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

	public static String log;

	public static void add(String s) {
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm" + ":ss");
		String currTime = sdf.format(cal.getTime());

		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currDate = sdf.format(cal.getTime());
		
		//
		String boodschap = currTime + " - " + s;

		Logger.log += boodschap + "\n";

		Logger.appendLog("KM " + currDate + ".log", boodschap + "\r\n");

		System.out.println(boodschap);
	}

	public static void appendLog(String filename, String s) {
		try {
			FileWriter fw = new FileWriter(filename, true);
			fw.write(s);
			fw.close();
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}
}
