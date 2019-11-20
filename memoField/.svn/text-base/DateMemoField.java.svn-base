package memoField;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DateMemoField extends MemoField {

	// private GregorianCalendar dateStamp;

	public DateMemoField(String nameOfMemoField) {
		super(nameOfMemoField);
		// dateStamp = new GregorianCalendar();
		// // DateFormat format = DateFormat.getInstance();
		// // System.out.println("Default locale format gives: "
		// // + format.format(calendar.getTime()));
		//
		// // http://pleac.sourceforge.net/pleac_java/datesandtimes.html
		// SimpleDateFormat customFormat = new SimpleDateFormat(
		// "EEE MMM d");// hh:mm aa");// yyyy");
		// // Fri Apr 3 11:32 AM 2009
		// this.setAssociatedValue(customFormat.format(dateStamp.getTime()));
	}

	public DateMemoField() {
	}

	public String getTextLabel() {
		if (this.getAssociatedValue() == null) {
			return this.getNameOfField() + FORMATTER;
		} else {
			return "<html>" + this.getNameOfField() + FORMATTER + "<br>"
					+ this.getAssociatedValue() + "</html>";
		}
	}

	public String toString() {
		return this.getNameOfField() + FORMATTER + "\n"
				+ this.getAssociatedValue();
	}

}
