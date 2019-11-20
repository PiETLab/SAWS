package userComposedMemo;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.text.*;

import memoField.BodyMemoField;
import memoField.DateMemoField;
import memoField.FromMemoField;
import memoField.MemoField;
import memoField.MemoFieldsToBeCompletedSet;
import memoField.SubjectMemoField;
import memoField.ThisMemoIsDoneCommandField;
import memoField.ToMemoField;

import addressBook.Addressee;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import coreMemoApplication.MemoFillerInvocationParameterModel;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;
import subjectLine.SubjectLine;

public class UserComposedMemo extends DefaultStyledDocument implements
		Serializable {

	private MemoFillerInvocationParameterModel paramModel;

	public SourceSymbolSet sourceSymbolsRepresentingModifiableFields;
	public SourceSymbolSet allFields;

	private MemoField toField;
	private MemoField fromField;
	private MemoField dateField;
	private MemoField subjectField;
	private BodyMemoField memoBody;
	private MemoField completedField;

	// private List<MemoField> allFields;
	private Map<MemoField, Double> fields;

	private String uniqueFileNameForPDF;
	private String uniqueFileNameForObject;

	public UserComposedMemo(MemoFillerInvocationParameterModel paramModel) {
		this.paramModel = paramModel;
		toField = new ToMemoField("To");
		fromField = new FromMemoField("From");
		dateField = new DateMemoField("Date");
		subjectField = new SubjectMemoField("Subject");
		memoBody = new BodyMemoField("Body");
		completedField = new ThisMemoIsDoneCommandField("Done");

		fields = new TreeMap<MemoField, Double>();

		// let the integer represent frequency of occurence, huffman will insert
		// them in descending order
		fields.put(completedField, .1);
		fields.put(toField, .6);
		fields.put(fromField, .5);
		fields.put(dateField, .4);
		fields.put(subjectField, .3);
		fields.put(memoBody, .2);

		// allFields = new Vector<MemoField>();
		// allFields.add(toField);
		// allFields.add(fromField);
		// allFields.add(dateField);
		// allFields.add(subjectField);
		// allFields.add(memoBody);

		sourceSymbolsRepresentingModifiableFields = new MemoFieldsToBeCompletedSet();
		allFields = new MemoFieldsToBeCompletedSet();
		for (MemoField mf : fields.keySet()) {
			if ((mf instanceof FromMemoField) || (mf instanceof DateMemoField)) {
			} else {
				sourceSymbolsRepresentingModifiableFields.addToSymbolSet(mf,
						fields.get(mf));
			}
			allFields.addToSymbolSet(mf, fields.get(mf));
		}
		// sourceSymbolsRepresentingFields.addToSymbolSet(toField, 5);
		// sourceSymbolsRepresentingFields.addToSymbolSet(fromField, 4);
		// sourceSymbolsRepresentingFields.addToSymbolSet(dateField, 3);
		// sourceSymbolsRepresentingFields.addToSymbolSet(subjectField, 2);
		// sourceSymbolsRepresentingFields.addToSymbolSet(memoBody, 1);
		sourceSymbolsRepresentingModifiableFields.renormalize();
		allFields.renormalize();

		dateField.setAssociatedValue(paramModel.getDateStampForMemoField());

		this.uniqueFileNameForPDF = paramModel.getFullFileNameForPDFFile();
		this.uniqueFileNameForObject = paramModel
				.getFullFileNameForObjectFile();
	}

	public void updateField(Addressee addressee) {
		((ToMemoField) toField).setAssociatedValue(addressee);
		// ((SourceSymbol) toField);
	}

	public void updateField(String messageBody) {
		((BodyMemoField) memoBody).setAssociatedValue(messageBody);
		// ((SourceSymbol) toField);
	}

	public void updateField(MemoField theField) {
		// for (MemoField mf : allFields) {
		// if (theField.matches(theField)) {
		//				
		// }
		// }
		// theField.setAssociatedValue("");
	}

	// public void updateField(MemoField field, String str) {
	// field.setAssociatedValue(str);
	// }

	public StyledDocument getStyledDocument() {
		return null;
	}

	public String getBody() {
		return memoBody.getBody();
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (MemoField f : fields.keySet()) {
			buf.append(f.toString() + "\n");
		}
		return buf.toString();
	}

	public SourceSymbolSet getFieldsAsSourceSymbolSet() {
		return sourceSymbolsRepresentingModifiableFields;
	}

	public SourceSymbolSet getAllFields() {
		return allFields;
	}

	public void updateField(SubjectLine subjectLine) {
		((SubjectMemoField) subjectField).setAssociatedValue(subjectLine);
	}

	/**
	 * writes this memo in a nicely-formatted way to a pdf file. If the
	 * specified pdf file already exists, then the existing version gets
	 * obliterated.
	 * 
	 * @param fullPathAndFileName
	 */
	public void writeToPDF() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					this.uniqueFileNameForPDF));
			// System.out.println("Writing PDF to: "
			// + getFullPathAndFileNameForPDF());
			document.open();

			List<SourceSymbol> theFields = this.getAllFields()
					.getSourceSymbolsByRankOrder();
			//System.out.println(this.getAllFields().toString());
			//Collections.reverse(theFields);
			// for (int i = 1; i <= 5; i++) {
			for (SourceSymbol mf : theFields) {
				MemoField mf2 = (MemoField) mf;
				// fields.(i).appendToPDFDocument(document);
				mf2.appendToPDFDocument(document);
			}
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
			// System.exit(0);
		}
	}

	public void writeObjectToFile() {
		File fileForObject = new File(uniqueFileNameForObject);

		// long startTime = System.currentTimeMillis();
		System.out.println("Writing UserComposedMemo object to file... : "
				+ fileForObject.getAbsolutePath());
		FileOutputStream outFileStream;
		ObjectOutputStream s;
		try {
			outFileStream = new FileOutputStream(fileForObject);
			s = new ObjectOutputStream(outFileStream);
			s.writeObject(this);
			s.flush();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// out.println("Object is in: " + objectFileName);
		System.out.println("Object will be in file: " + fileForObject);

	}

	public static UserComposedMemo readObjectFromFile(String fullPathAndName) {
		FileInputStream inFileStream;
		UserComposedMemo newInstance = null;
		try {
			inFileStream = new FileInputStream(fullPathAndName);
			ObjectInputStream i = new ObjectInputStream(inFileStream);
			newInstance = (UserComposedMemo) i.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return newInstance;
	}
}
