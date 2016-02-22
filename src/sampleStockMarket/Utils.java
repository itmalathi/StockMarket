///////////////////////////////////////////////////////////////////////////////////
////////////////SampleStockMarket//////////////////////////////////////////////////

package sampleStockMarket;

import java.util.Calendar;
import java.util.Date;

public class Utils {
	private static final int TRADE_TIME = 5; // 5 seconds

	// Returns current Time
	public static Date GetCurrentTime() {
		return new Date();
	}

	// Checks whether the input is integer
	public static boolean isInteger(String arg) {
		try {
			Integer.parseInt(arg);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// Checks whether the input is double
	public static boolean isDouble(String arg) {
		try {
			Double.parseDouble(arg);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// Returns the Start Time (5 minutes before the current time)
	public static Date getStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, -TRADE_TIME);
		return cal.getTime();
	}
}
