package memoField;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;

import addressBook.Addressee;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import memoApplicationCommands.DefaultMemoFillerCommand;

import IndirectSelectionFacilityCommands.AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import sourceSymbolSet.SourceSymbol;
import userComposedMemo.UserComposedMemo;

public abstract class MemoField extends JButton implements SourceSymbol {

	private static final String SPACE = " ";
	protected static final String FORMATTER = ": ";
	private String fullNameOfField;
	// private String associatedValueForField;
	private String associatedValue;
	private double marginalProbability;

	public MemoField() {
		fullNameOfField = this.getClass().getName();
	}

	public MemoField(String nameOfMemoField) {
		fullNameOfField = nameOfMemoField;
	}

	public int compareTo(SourceSymbol sgm) {
		// System.out.println("Comparing: " + this + " and " + sgm);
		return this.fullNameOfField.compareTo(((MemoField) sgm)
				.getNameOfField());
	}

	public int compareTo(MemoField sgm) {
		// System.out.println("aComparing: " + this + " and " + sgm);
		return this.fullNameOfField.compareTo(((MemoField) sgm)
				.getNameOfField());
	}

	protected String getNameOfField() {
		return this.fullNameOfField;
	}

	public boolean equals(SourceSymbol sgm) {
		// System.out.println("Is equals: " + this + " and " + sgm);
		return (this.getTextLabel().equals(sgm.getTextLabel()));
	}

	public boolean equals(MemoField sgm) {
		// System.out.println("aIs equals: " + this + " and " + sgm);
		return (this.getTextLabel().equals(sgm.getTextLabel()));
	}

	public double getConditionalProbability(SourceSymbol targetSymbol) {
		return 0;
	}

	// public String getTextLabel() {
	// if (this.getAssociatedValue() == null) {
	// return this.getNameOfField() + FORMATTER;
	// } else {
	// return this.getNameOfField() + FORMATTER
	// + this.getAssociatedValue();
	// }
	// }

	public String getTextLabel() {
		if (this.getAssociatedValue() == null) {
			return this.getNameOfField() + FORMATTER;
		} else {
			return "<html>" + this.getNameOfField() + FORMATTER + "<br>"
					+ this.getAssociatedValue() + "</html>";
		}
	}

	// public abstract IndirectSelectionFaciltyCommand getVOCACommand();

	public IndirectSelectionFaciltyCommand getVOCACommand() {
		return new DefaultMemoFillerCommand();
	}

	// {
	// return new MemoFieldCommand(this.getFullName(), null);
	// }

	public void setBackground(Color color) {
	}

	public String toStringLaTeX() {
		return this.getNameOfField();
	}

	/**
	 * must be unique!!!
	 * 
	 */
	public String toStringOneChar() {
		return this.getNameOfField().substring(0, 1);
	}

	// {
	// return new MemoField(this.getFullName());
	// }

	public String toString() {
		return this.getClass().getName() + ": " + this.getNameOfField()
				+ FORMATTER + this.getAssociatedValue();
	}

	public Double getMarginalProbability() {
		return marginalProbability;
	}

	public Double setConditionalProbability(SourceSymbol targetSymbol,
			double prob) {
		return null;
	}

	public void setMarginalProbability(double prob) {
		this.marginalProbability = prob;
	}

	protected String getAssociatedValue() {
		return associatedValue;
	}

	public void setAssociatedValue(String associatedValue) {
		this.associatedValue = associatedValue;
	}

	public MemoField clone() {
		MemoField theClone = null;
		Class<?>[] params = { getNameOfField().getClass() };
		Constructor<?> constr;
		try {
			constr = Class.forName(getClass().getName()).getConstructor(params);
			Object[] args = { getNameOfField() };
			theClone = (MemoField) constr.newInstance(args);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		} catch (ClassNotFoundException e) {
		} catch (IllegalArgumentException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return theClone;
	}

	public void assignValue(String selectedString) {
		this.associatedValue = selectedString;
	}

	public void appendToPDFDocument(Document document) {
		try {
			float[] widths = { 0.20f, 0.80f };

			PdfPTable t = new PdfPTable(widths);
			// PdfPTable table = new PdfPTable(widths);
			// t.setWidthPercentage(0.95f);
			// t.
			// t.set
			// t.setBorderColor(Color.WHITE);
			// t.setPadding(0);
			// t.setSpacing(0);
			// t.setBorderWidth(0);
			PdfPCell cell = new PdfPCell(new Paragraph(this.getNameOfField()
					+ FORMATTER, FontFactory.getFont(FontFactory.TIMES, 20,
					Font.BOLD, Color.BLACK)));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			// Rectangle border = new Rectangle(0f, 0f);
			// border.setBorder(0);
			cell.setBorder(0);

			PdfPCell cell2 = new PdfPCell(new Paragraph(this
					.getAssociatedValue(), FontFactory.getFont(
					FontFactory.TIMES, 20, Font.NORMAL, Color.BLACK)));
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setBorder(0);

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
			document.add(t);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
