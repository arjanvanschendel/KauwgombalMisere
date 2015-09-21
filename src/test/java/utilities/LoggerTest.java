package utilities;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import utillities.Logger;

public class LoggerTest {

	@Before
	public void setup() {
		Logger.init();
	}
	
	/**
	 * test the add function of the logger.
	 */
	@Test
	public void addTest() {
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm" + ":ss");
		String currTime = sdf.format(cal.getTime());
		
		Logger.add("<Running test>");
		assertEquals(Logger.log, currTime + " - <Running test>\n");
	}

}
