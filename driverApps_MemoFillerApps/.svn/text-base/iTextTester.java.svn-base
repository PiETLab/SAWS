package driverApps_MemoFillerApps;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import userComposedMemo.UserComposedMemo;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public class iTextTester {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		UserComposedMemo memo = new UserComposedMemo(
				"/Users/mb/Documents/workspace-new2/Prj-Meagan/PDF/");
		memo.updateField("Here is a sample memo body");
		memo.writeToPDF();
		memo.writeObjectToFile();

		String fullPathAndName = memo.getFullPathAndFileNameForPDF();
		String fullPathAndName2 = memo.getFullPathAndFileNameForObjectFile();

		UserComposedMemo memo2 = UserComposedMemo
				.readObjectFromFile(fullPathAndName2);
		System.out.println(memo2.toString());

		// PdfReader reader = new PdfReader(fullPathAndName);
		// //PdfImportedPage page = reader.getp
		// byte[] pageContentByteArray = reader.getPageContent(1);
		// //String test = Encoding.ASCII.GetString(pageContentByteArray);
		// for (byte b : pageContentByteArray) {
		// System.out.println((char) b);
		// }
		// System.out.println(pageContentByteArray);

		// Document document = new Document();
		// try {
		// PdfWriter.getInstance(document, new FileOutputStream(
		// "HelloWorld.pdf"));
		// document.open();
		// document.add(new Paragraph("Hello World"));
		//
		// document.add(new
		// Paragraph("First page of the document. asfhdsjdfh"));
		//
		// document
		// .add(new Paragraph(
		// "Some more text on the  first page with different color and font type.",
		// FontFactory.getFont(FontFactory.COURIER, 14,
		// Font.BOLD, new Color(255, 150, 200))));
		//
		// document.close();
		//
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (DocumentException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// GregorianCalendar calendar = new GregorianCalendar();
		// DateFormat format1 = DateFormat.getInstance();
		// System.out.println("Default locale format gives: "
		// + format1.format(calendar.getTime()));
		//
		// // SimpleDateFormat format2 = new
		// // SimpleDateFormat("EEE MMM d hh:mm:ss yyyy zzz");
		// // Fri Apr 3 11:31:07 2009 PDT
		// SimpleDateFormat format2 = new SimpleDateFormat(
		// "EEE MMM d hh:mm aa yyyy");
		// // Fri Apr 3 11:32 AM 2009
		// System.out.println("Customized format gives: "
		// + format2.format(calendar.getTime()));
		//
		// SimpleDateFormat fileNameFormat = new
		// SimpleDateFormat("dMMMyyyy_HH:mm");
		//
		// System.out.println("Filename: "
		// + fileNameFormat.format(calendar.getTime()));
		//
		// System.out.println("\u00A4");

	}
}
