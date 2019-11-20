package driverApps_MemoFillerApps;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tester {

	/**
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

		String all = "-di displayScheme.ShowKeyboardWithAsFewElementsAsPossible -dt 750 -ea 2 -ec encodingTrees.RowColFromKB -el false -fa passive -ff java.awt.Font[family=Serif,name=Serif,style=plain,size=1] -fs 30 -kb buttonLayouts.VenkatagiriKeyboardLayout_A2A5 -kp 0.5 -mt encodingTrees.UnigramBasedET_2Level -rb true -sl sourceSymbolSet.Venkatagiri99Set -te manual -ui default -tt default -fu true -ha 0 -va 0 -bg java.awt.Color[r=0,g=0,b=255] -fg black -as actionScheme.PassiveFocusAdvancementActionScheme_TrialMode2";
		String REGEX_FOR_OPEN_SQUARE_BRACKET = "\\[";
		String REGEX_FOR_CLOSE_SQUARE_BRACKET = "\\]";
		String REGEX_FOR_COMMA = ",";
		String REGEX_FOR_EQUALS_SIGN = "=";
		String REGEX_FOR_PERIOD = "\\u002e";
		String REGEX_FOR_FORWARDSLASH = "/";
		String REGEX_FOR_WORD_CHARACTER = "\\w";
		String REGEX = "-\\w\\w" + " " + "[" + REGEX_FOR_OPEN_SQUARE_BRACKET
				+ REGEX_FOR_CLOSE_SQUARE_BRACKET + REGEX_FOR_COMMA
				+ REGEX_FOR_EQUALS_SIGN + REGEX_FOR_WORD_CHARACTER
				+ REGEX_FOR_FORWARDSLASH + REGEX_FOR_PERIOD + "]+" + "[ ]*";
		// String REGEX = "-\\w\\w" + " " + "[" + "-\\w/\\u002e]+" + "[ ]*";
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(all);
		while (m.find()) {
			// System.out.println(m.start());
			// System.out.println(m.end());
			// System.out.println(m.group());
			// System.out.println("*");
			System.out.println(m.group());
			// System.out.println(result.get(result.size() - 1));
		}
		// System.out.println("*");
		// String[] res = new String[result.size()];
		// return result.toArray(res);

		// System.out.println("\u00A4");

		String string = "java.awt.Color[r=51,g=51,b=255]";
		System.out.println("attempting to set value: " + string);
		String REGEX_RED = "r=\\w+";
		String REGEX_GREEN = "g=\\w+";
		String REGEX_BLUE = "b=\\w+";
		Pattern p1 = Pattern.compile(REGEX_RED);
		Matcher m1 = p1.matcher(string);

		System.out.println("***");

		int red = 0;
		int green = 0;
		int blue = 0;

		if (m1.find()) {
			System.out.println(m1.group());
			red = Integer.parseInt(m1.group().substring(2));
			System.out.println(red);
		}

		Matcher m2 = Pattern.compile(REGEX_GREEN).matcher(string);
		if (m2.find()) {
			System.out.println(m2.group());
			green = Integer.parseInt(m2.group().substring(2));
			System.out.println(green);
		}

		Matcher m3 = Pattern.compile(REGEX_BLUE).matcher(string);
		if (m3.find()) {
			System.out.println(m3.group());
			blue = Integer.parseInt(m3.group().substring(2));
			System.out.println(blue);
		}

		Color theColor = new Color(red, green, blue);
		System.out.println(theColor);
		// int red = 0;

	}
}
