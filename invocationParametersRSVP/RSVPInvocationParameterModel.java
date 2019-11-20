package invocationParametersRSVP;

import org.apache.fop.hyphenation.HyphenationException;
import org.apache.fop.hyphenation.HyphenationTree;

import basicGuiParameters.BackgroundColourParameter;
import basicGuiParameters.FontFamilyParameter;
import basicGuiParameters.TextColourParameter;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterFile;
import SoftwareDeployment.InvocationParameterModel;


import invocationParametersISF.FullScreenModeParameter;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

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

public class RSVPInvocationParameterModel extends InvocationParameterModel {

	private List<InvocationParameter> allParameters;

	private InvocationParameter helpParam;
	private InvocationParameter dwellParam;
	private InvocationParameter hyphenationTreeParam;
	private InvocationParameter fontFamilyParam;
	private InvocationParameter fontSizeParam;
	private InvocationParameter backgroundColour;
	private InvocationParameter foregroundColour;
	private InvocationParameter fullScreenModeParam;

	private Font font = null;

	/**
	 * 
	 * 
	 * 
	 * @param args
	 *            of the form {"-fa", "active", "-dt", "1050"} as would be
	 *            derived from command-line invocation
	 */
	public RSVPInvocationParameterModel(String[] args) {
		super(args);
		System.out.println(super.generateSummary());
	}

	public RSVPInvocationParameterModel(String args) {
		super(args);
		System.out.println(super.generateSummary());
	}

	public RSVPInvocationParameterModel(InvocationParameterFile file) {
		String s2 = file.extractFirstNonCommentLine();
		System.out.println("> " + s2);
		super.setPassedParameters(s2);
	}

	public RSVPInvocationParameterModel() {
	}

	@Override
	protected List<InvocationParameter> defineAllParameters() {

		helpParam = new VerboseHelpParameter();
		dwellParam = new DwellPeriodParameter();
		hyphenationTreeParam = new HyphenationTreeParameter();
		fontFamilyParam = new FontFamilyParameter();
		fontSizeParam = new DisplayFontSizeParameter();
		backgroundColour = new BackgroundColourParameter();
		foregroundColour = new TextColourParameter();
		fullScreenModeParam = new FullScreenModeParameter();

		allParameters = new Vector<InvocationParameter>();
		allParameters.add(helpParam);
		allParameters.add(dwellParam);
		allParameters.add(hyphenationTreeParam);
		allParameters.add(fontFamilyParam);
		allParameters.add(fontSizeParam);
		allParameters.add(backgroundColour);
		allParameters.add(fullScreenModeParam);
		return allParameters;
	}

	public List<InvocationParameter> getAllParameters() {
		return allParameters;
	}

	public HyphenationTree getHyphenationTree() {
		HyphenationTree ht = new HyphenationTree();
		try {
			ht.loadPatterns(hyphenationTreeParam.getAssociatedValue());
		} catch (HyphenationException e) {
			e.printStackTrace();
		}
		return ht;
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

	public boolean isFullScreen() {
		return Boolean.parseBoolean(fullScreenModeParam.getAssociatedValue());
	}

	public boolean isUsingAssistiveTechnologyMouseLikeDevice() {
		return true;
	}

}
