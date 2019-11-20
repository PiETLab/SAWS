package driverApps_MemoFillerApps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class systemCheck1 {

	/**
	 * 
	 * Ensure all of the system fonts are denoted by strings without spaces
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GregorianCalendar calendar = new GregorianCalendar();
		DateFormat format1 = DateFormat.getInstance();
		System.out.println("Default locale format gives: "
				+ format1.format(calendar.getTime()));

		// SimpleDateFormat format2 = new
		// SimpleDateFormat("EEE MMM d hh:mm:ss yyyy zzz");
		// Fri Apr 3 11:31:07 2009 PDT
		SimpleDateFormat format2 = new SimpleDateFormat(
				"EEE MMM d hh:mm aa yyyy");
		// Fri Apr 3 11:32 AM 2009
		System.out.println("Customized format gives: "
				+ format2.format(calendar.getTime()));

		SimpleDateFormat fileNameFormat = new SimpleDateFormat("dMMMyyyy_HH:mm");

		System.out.println("Filename: "
				+ fileNameFormat.format(calendar.getTime()));

		// System.out.println("\u00A4");

	}

}
