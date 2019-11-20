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
import memoApplicationCommands.StopTheMemoCompositionCommand;
import memoApplicationCommands.CompleteTheToFieldCommand;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class ThisMemoIsDoneCommandField extends MemoField {

	public ThisMemoIsDoneCommandField(String nameOfMemoField) {
		super(nameOfMemoField);
	}

	public ThisMemoIsDoneCommandField() {
	}

	public IndirectSelectionFaciltyCommand getVOCACommand() {
		// return new MemoBodySourceSymbolCommand(getNameOfField(), this);
		return new StopTheMemoCompositionCommand(getNameOfField(), this);

	}

	public String getBody() {
		return this.getAssociatedValue();
	}

	public void appendToPDFDocument(Document document) {
		// don't add anything
	}

	public String getTextLabel() {
		return this.getNameOfField() + "!";
	}

}
