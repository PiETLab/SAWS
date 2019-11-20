package memoField;

import java.awt.Color;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import memoApplicationCommands.DefaultMemoFillerCommand;
import memoApplicationCommands.CompleteTheMemoBodyFieldCommand;
import memoApplicationCommands.MemoBodySignifyAsFinishedSourceSymbolCommand;
import memoApplicationCommands.CompleteTheToFieldCommand;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class BodyMemoField extends MemoField {

	private String theMemoBody;

	public BodyMemoField(String nameOfMemoField) {
		super(nameOfMemoField);
	}

	public BodyMemoField() {
	}

	public IndirectSelectionFaciltyCommand getVOCACommand() {
		// return new MemoBodySourceSymbolCommand(getNameOfField(), this);
		return new CompleteTheMemoBodyFieldCommand();

	}

	public String getBody() {
		return this.getAssociatedValue();
	}

	public void appendToPDFDocument(Document document) {
		try {
			float[] widths = { 1.0f };

			PdfPTable t = new PdfPTable(widths);

			PdfPCell cell = new PdfPCell();
			// PdfPCell cell = new PdfPCell(new Paragraph(this.getNameOfField()
			// + FORMATTER, FontFactory.getFont(FontFactory.TIMES, 20,
			// Font.BOLD, Color.BLACK)));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			// Rectangle border = new Rectangle(0f, 0f);
			// border.setBorder(0);
			cell.setBorder(0);

			PdfPCell cell2 = new PdfPCell(new Paragraph(this
					.getAssociatedValue(), FontFactory.getFont(
					FontFactory.TIMES, 20, Font.NORMAL, Color.BLACK)));
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setBorderWidthTop(2);
			cell2.setBorderWidthBottom(0);
			cell2.setBorderWidthLeft(0);
			cell2.setBorderWidthRight(0);

			t.addCell(cell);
			t.addCell(cell2);
			// new Paragraph(this.getNameOfField() + FORMATTER,
			// FontFactory.getFont(FontFactory.TIMES, 20, Font.BOLD,
			// Color.BLACK)));

			// t.addCell(new Paragraph("\t\t\t\t" + this.getAssociatedValue()
			// + "\n\n", FontFactory.getFont(FontFactory.TIMES, 20,
			// Font.NORMAL, Color.BLACK)));

			// t.addCell("1.3");

			// Paragraph p = new Paragraph();
			// p.add(new Chunk(this.getNameOfField() + FORMATTER, FontFactory
			// .getFont(FontFactory.TIMES, 20, Font.BOLD, Color.BLACK)));
			// p.add(new Chunk("\t\t\t\t" + this.getAssociatedValue() + "\n\n",
			// FontFactory.getFont(FontFactory.TIMES, 20, Font.NORMAL,
			// Color.BLACK)));
			// p.add(t);
			document.add(new Paragraph());
			document.add(t);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
