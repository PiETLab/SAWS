package coreMemoApplication;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterFile;
import SoftwareDeployment.InvocationParameterModel;

import invocationParametersISF.FullScreenModeParameter;
import invocationParametersRSVP.DisplayFontSizeParameter;
import invocationParametersRSVP.DwellPeriodParameter;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import basicGuiParameters.BackgroundColourParameter;
import basicGuiParameters.FontFamilyParameter;
import basicGuiParameters.TextColourParameter;

import addressBook.AddressBook;
import addressBook.AddressBookParameter;

import subjectLine.SubjectLineBank;
import subjectLine.SubjectLineParameter;

/**
 * This class implements a model that keeps track of all of the various
 * parameters that can be modified when instantiating a rsvp application.
 * 
 * Each possible parameter is instantiated by a class that extends the
 * InvocationParameter class.
 * 
 * Use Focus Advancement: true/false
 * 
 * If false, then need n input actions.
 * 
 * If true, then specify Use Passive: true/false
 * 
 * If false, then need x2 input actions (map to select, adv focus)
 * 
 * If true, then need x1 input action
 * 
 * 
 */

public class MemoFillerInvocationParameterModel extends
		InvocationParameterModel {

	List<InvocationParameter> allParameters;

	private InvocationParameter dwellParam;
	private InvocationParameter fontFamilyParam;
	private InvocationParameter fontSizeParam;
	private InvocationParameter backgroundColour;
	private InvocationParameter foregroundColour;
	private InvocationParameter addressBookParam;
	private InvocationParameter subjectLineParam;
	private InvocationParameter pdfLocationParam;
	private InvocationParameter fullScreenModeParam;

	private Font font = null;

	private AddressBook addressBook;
	private SubjectLineBank subjectLines;

	private GregorianCalendar theCalendar = new GregorianCalendar();

	private String currentPDFFile;

	/**
	 * 
	 * 
	 * 
	 * @param args
	 *            of the form {"-fa", "active", "-dt", "1050"} as would be
	 *            derived from command-line invocation
	 */
	public MemoFillerInvocationParameterModel(String[] args) {
		super(args);
		System.out.println(super.generateSummary());
	}

	public MemoFillerInvocationParameterModel(String args) {
		super(args);
	}

	public MemoFillerInvocationParameterModel(InvocationParameterFile file) {
		String s2 = file.extractFirstNonCommentLine();
		System.out.println("line extracted from parameter file > " + s2);
		System.out.println("number of parameters > "
				+ super.getAllParameters().size());
		super.setPassedParameters(s2);

	}

	public MemoFillerInvocationParameterModel() {
	}

	@Override
	protected List<InvocationParameter> defineAllParameters() {

		dwellParam = new DwellPeriodParameter();
		fontFamilyParam = new FontFamilyParameter();
		fontSizeParam = new DisplayFontSizeParameter();
		backgroundColour = new BackgroundColourParameter();
		foregroundColour = new TextColourParameter();
		addressBookParam = new AddressBookParameter();
		subjectLineParam = new SubjectLineParameter();
		pdfLocationParam = new PDFLocationParameter();
		fullScreenModeParam = new FullScreenModeParameter();

		allParameters = new Vector<InvocationParameter>();
		allParameters.add(dwellParam);
		allParameters.add(fontFamilyParam);
		allParameters.add(fontSizeParam);
		allParameters.add(backgroundColour);
		allParameters.add(foregroundColour);
		allParameters.add(addressBookParam);
		allParameters.add(subjectLineParam);
		allParameters.add(pdfLocationParam);
		allParameters.add(fullScreenModeParam);
		return allParameters;
	}

	public List<InvocationParameter> getAllParameters() {
		return allParameters;
	}

	public AddressBook getAddressBook() {
		if (addressBook == null) {
			System.out.println("location of address book: "
					+ addressBookParam.getAssociatedValue());
			addressBook = new AddressBook(addressBookParam.getAssociatedValue());
		}
		return addressBook;
	}

	public SubjectLineBank getSubjectLineBank() {
		if (subjectLines == null) {
			System.out.println("location of subject lines: "
					+ subjectLineParam.getAssociatedValue());
			subjectLines = new SubjectLineBank(subjectLineParam
					.getAssociatedValue());
		}
		return subjectLines;
	}

	public String getDisplayFontFamily() {
		return fontFamilyParam.getAssociatedValue();
	}

	public int getDisplayFontSize() {
		return Integer.parseInt(fontSizeParam.getAssociatedValue());
	}

	public int getEventRateInMSec() {
		return Integer.parseInt(dwellParam.getAssociatedValue());
	}

	public Font getDisplayFont() {
		if (font == null) {
			font = new Font(this.getDisplayFontFamily(), Font.PLAIN, this
					.getDisplayFontSize());
		}
		return font;
	}

	public void setDisplayFont(Font newFont) {
		fontFamilyParam.setAssociatedValue(newFont.getFamily());
		fontSizeParam.setAssociatedValue("" + newFont.getSize());
	}

	public Color getBackgroundColour() {
		Color tmp = null;
		try {
			Class t = Class.forName("java.awt.Color");
			Field f = t.getField(backgroundColour.getAssociatedValue());
			tmp = (Color) f.get(null);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return tmp;
	}

	public Color getTextColour() {
		Color tmp = null;
		try {
			Class t = Class.forName("java.awt.Color");
			Field f = t.getField(foregroundColour.getAssociatedValue());
			tmp = (Color) f.get(null);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return tmp;
	}

	public String getETConstructTechnique() {
		return "encodingTrees.HuffmanEqualCosts";
	}

	public Integer getEncodingAlphabetSize() {
		return this.getAddressBook().size();
	}

	public String getKeyboardVariant() {
		return "keyboardLayouts.MeaganKeyboardLayout2";
	}

	public double getKeyboardRealEstateProportion() {
		return 0.50;
	}

	public String getPDFDirectory() {
		return pdfLocationParam.getAssociatedValue();
	}

	public String getFullFileNameForPDFFile() {
		theCalendar = new GregorianCalendar();
		SimpleDateFormat formatForfileName = new SimpleDateFormat(
				"dMMMyyyy_HH:mm");
		String s = formatForfileName.format(theCalendar.getTime());

		if (currentPDFFile == null) {
			currentPDFFile = getPDFDirectory() + s + ".pdf";
		}
		return currentPDFFile;
	}

	public String getFullFileNameForObjectFile() {
		return getFullFileNameForPDFFile() + ".obj";
	}

	public String getDateStampForMemoField() {
		SimpleDateFormat formatForDateField = new SimpleDateFormat("EEE MMM d");// hh:mm
		// aa");// yyyy");
		return formatForDateField.format(theCalendar.getTime());
	}

}
