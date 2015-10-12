package utilities;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import utillities.Logger;

/**
 * Tests for the Logger class.
 * 
 * @author Matthias
 *
 */
public class LoggerTest {

	/**
	 * Setup executed before each test.
	 */
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
		assertEquals(Logger.getLog(), currTime + " - <Running test>\n");
	}

}
