package customGUIComponentsISF;

import javax.swing.*;

import sourceSymbolSet.SourceSymbol;

import IndirectSelectionFacilityCommands.AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand;
import IndirectSelectionFacilityCommands.CapsToggleCommand;
import IndirectSelectionFacilityCommands.DeleteCommand;
import IndirectSelectionFacilityCommands.GoToReviewModeCommand;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import IndirectSelectionFacilityCommands.SendToTTSCommand;
import IndirectSelectionFacilityCommands.SignifyFinishedCommand;

import java.awt.event.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * Created on 7-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * This class implements a button on a JVirtualKeyboard. Such buttons have:
 * <ol>
 * <li>a button label (which is a string and which appears as the button's label
 * when the keyboard is presented visually), [PRESENTLY, we don't enforce
 * anything to ensure that this label must be UNIQUE!!]
 * <li>an id (which uniquely identifies the button and distinguishes it from the
 * others, typically a row-column coordinate), and
 * <li>a corresponding action command (the character that results from the
 * activation of that button).
 * <li>an optional LaTeX version of the button label, provided for the purposes
 * of program debugging and output
 * </ol>
 * 
 * @author Melanie Baljko
 */
public class JIndirectSelectionButton extends JButton implements
		ActionListener, SourceSymbol {

	private static Color DEFAULT_TEXT_COLOUR = Color.BLACK;

	private static final long serialVersionUID = -8083866213664729212L;

	private static boolean isVerbose = false;

	private static PrintStream output = System.out;

	private Map<SourceSymbol, Double> conditionalProbabilities = new TreeMap<SourceSymbol, Double>();

	/**
	 * ATTRIBUTES OF BUTTONS
	 * 
	 * (1) its text (which is what appears on the button, optionally marked up
	 * in html) [inheirited from AbstractButton, prev described as its label]
	 * 
	 * (2) An associated VOCACommand
	 * 
	 * (3) An associated LaTeX equivalent (as a default, the same as the label
	 * text)
	 */

	private IndirectSelectionFaciltyCommand command;

	private double probabilityOfOccurrence;

	private String equivLaTeX;

	public JIndirectSelectionButton(String _label,
			IndirectSelectionFaciltyCommand _command) {
		this(_label, _command, _label, 0.0);
	}

	public JIndirectSelectionButton(String stringForKBButton,
			IndirectSelectionFaciltyCommand stringToAppend,
			String stringForLaTeX) {
		this(stringForKBButton, stringToAppend, stringForLaTeX, 0.0);
	}

	public JIndirectSelectionButton(String _label,
			IndirectSelectionFaciltyCommand _command, String _equivLaTeX,
			double expectedProb) {
		equivLaTeX = _equivLaTeX;
		command = _command;
		setText(_label);
		setActionCommand(_label);
		setEnabled(true);
		setFocusable(false);
		setMarginalProbability(expectedProb);

		this.setFont(new Font("sanserif", Font.BOLD, 40));
		Insets insets = new Insets(1, 1, 1, 1);
		this.setMargin(insets);
	}

	public JIndirectSelectionButton clone() {
		JIndirectSelectionButton but = new JIndirectSelectionButton(this
				.getText(), this.getVOCACommand(), this.getEquivLaTeXText());
		but.setMarginalProbability(this.getMarginalProbability());
		but.setEnabled(this.isEnabled());
		return but;

	}

	public void setMarginalProbability(double prob) {
		probabilityOfOccurrence = prob;
	}

	public Double getMarginalProbability() {
		return probabilityOfOccurrence;
	}

	public IndirectSelectionFaciltyCommand getVOCACommand() {
		return command;
	}

	public String getEquivLaTeXText() {
		return equivLaTeX;
	}

	public void actionPerformed(ActionEvent ae) {
		if (isVerbose) {
			output.println("actionPerformed on: " + toStringClassNameOnly());
			output.println("\tmore specifically on: " + getText());
			output.println("\t" + ae.toString());
		}
		setBackground(Color.RED);
	}

	public void modifyBkgd(Color colour) {
		setBackground(colour);
	}

	public void modifyTextColor(Color colour) {
		setForeground(colour);
	}

	public void resetTextColor() {
		setForeground(DEFAULT_TEXT_COLOUR);
	}

	public String toString() {
		return toStringAbbr();
	}

	public String toStringAbbr() {
		return new String("but:[" + getText() + "," + getActionCommand() + "]");
	}

	public String toStringLaTeX() {
		return equivLaTeX;
	}

	public String toStringClassNameOnly() {
		String tmp = super.toString();
		return tmp.substring(0, tmp.indexOf("["));
		// return "TextCompositionFacilityFrame";
	}

	public int compareTo(JIndirectSelectionButton arg0) {
		JIndirectSelectionButton other = (JIndirectSelectionButton) arg0;
		return this.getText().compareTo(other.getText());
	}

	public int compareTo(SourceSymbol other) {
		return this.getTextLabel().compareTo(other.getTextLabel());

		// return this.getTextLabel().compareTo(other.getTextLabel());
		// return -1
		// * (this.getMarginalProbability()).compareTo(other
		// .getMarginalProbability());

	}

	public boolean equals(SourceSymbol sgm) {
		return this.compareTo(sgm) == 0;
	}

	public void setBackground(Color arg0) {
		super.setBackground(arg0);
	}

	// public boolean equals(SourceSymbol other) {
	// return this.getTextLabel() == other.getTextLabel()
	// && this.toString() == other.toString()
	// && this.getMarginalProbability() == other
	// .getMarginalProbability()
	// && this.getVOCACommand() == other.getVOCACommand();
	// }

	public String getTextLabel() {
		return this.getText();
	}

	public String toStringOneChar() {
		return toStringLaTeX();
	}

	final static double FILLER_FREQ = 0.0;

	public static final JIndirectSelectionButton VK_FILLER = new JIndirectSelectionButton(
			"FillerLabel", null, "$\\emptyset$", FILLER_FREQ);
	// "FillerLabel" + i, null, "$\\emptyset$", FILLER_FREQ);

	public static final JIndirectSelectionButton VK_A = new JIndirectSelectionButton(
			"A", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("A"));

	public static final JIndirectSelectionButton VK_B = new JIndirectSelectionButton(
			"B", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("B"));

	public static final JIndirectSelectionButton VK_C = new JIndirectSelectionButton(
			"C", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("C"));

	public static final JIndirectSelectionButton VK_D = new JIndirectSelectionButton(
			"D", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("D"));

	public static final JIndirectSelectionButton VK_E = new JIndirectSelectionButton(
			"E", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("E"));

	public static final JIndirectSelectionButton VK_F = new JIndirectSelectionButton(
			"F", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("F"));

	public static final JIndirectSelectionButton VK_G = new JIndirectSelectionButton(
			"G", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("G"));

	public static final JIndirectSelectionButton VK_H = new JIndirectSelectionButton(
			"H", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("H"));

	public static final JIndirectSelectionButton VK_I = new JIndirectSelectionButton(
			"I", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("I"));

	public static final JIndirectSelectionButton VK_J = new JIndirectSelectionButton(
			"J", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("J"));

	public static final JIndirectSelectionButton VK_K = new JIndirectSelectionButton(
			"K", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("K"));

	public static final JIndirectSelectionButton VK_L = new JIndirectSelectionButton(
			"L", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("L"));

	public static final JIndirectSelectionButton VK_M = new JIndirectSelectionButton(
			"M", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("M"));

	public static final JIndirectSelectionButton VK_N = new JIndirectSelectionButton(
			"N", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("N"));

	public static final JIndirectSelectionButton VK_O = new JIndirectSelectionButton(
			"O", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("O"));

	public static final JIndirectSelectionButton VK_P = new JIndirectSelectionButton(
			"P", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("P"));

	public static final JIndirectSelectionButton VK_Q = new JIndirectSelectionButton(
			"Q", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("Q"));

	public static final JIndirectSelectionButton VK_R = new JIndirectSelectionButton(
			"R", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("R"));

	public static final JIndirectSelectionButton VK_S = new JIndirectSelectionButton(
			"S", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("S"));

	public static final JIndirectSelectionButton VK_T = new JIndirectSelectionButton(
			"T", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("T"));

	public static final JIndirectSelectionButton VK_U = new JIndirectSelectionButton(
			"U", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("U"));

	public static final JIndirectSelectionButton VK_V = new JIndirectSelectionButton(
			"V", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("V"));

	public static final JIndirectSelectionButton VK_W = new JIndirectSelectionButton(
			"W", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("W"));

	public static final JIndirectSelectionButton VK_X = new JIndirectSelectionButton(
			"X", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("X"));

	public static final JIndirectSelectionButton VK_Y = new JIndirectSelectionButton(
			"Y", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("Y"));

	public static final JIndirectSelectionButton VK_Z = new JIndirectSelectionButton(
			"Z", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("Z"));

	// DIGITS
	public static final JIndirectSelectionButton VK_0 = new JIndirectSelectionButton(
			"0", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("0"));

	public static final JIndirectSelectionButton VK_1 = new JIndirectSelectionButton(
			"1", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("1"));

	public static final JIndirectSelectionButton VK_2 = new JIndirectSelectionButton(
			"2", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("2"));

	public static final JIndirectSelectionButton VK_3 = new JIndirectSelectionButton(
			"3", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("3"));

	public static final JIndirectSelectionButton VK_4 = new JIndirectSelectionButton(
			"4", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("4"));

	public static final JIndirectSelectionButton VK_5 = new JIndirectSelectionButton(
			"5", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("5"));

	public static final JIndirectSelectionButton VK_6 = new JIndirectSelectionButton(
			"6", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("6"));

	public static final JIndirectSelectionButton VK_7 = new JIndirectSelectionButton(
			"7", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("7"));

	public static final JIndirectSelectionButton VK_8 = new JIndirectSelectionButton(
			"8", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("8"));

	public static final JIndirectSelectionButton VK_9 = new JIndirectSelectionButton(
			"9", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("9"));
	public static final JIndirectSelectionButton VK_DIGIT = new JIndirectSelectionButton(
			"DIGIT", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("DIGIT"));

	// PUNCTUATION
	// \u0027 is APOSTROPHE '
	public static final JIndirectSelectionButton VK_APOSTROPHE = new JIndirectSelectionButton(
			"\u0027", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("\u0027"));

	public static final JIndirectSelectionButton VK_COMMA = new JIndirectSelectionButton(
			",", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand(","));

	public static final JIndirectSelectionButton VK_PERIOD = new JIndirectSelectionButton(
			".", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand(". "));

	public static final JIndirectSelectionButton VK_QUESTIONMARK = new JIndirectSelectionButton(
			"?", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("?"));

	// SPACE

	public static final JIndirectSelectionButton VK_SPACE_SYMBOL = new JIndirectSelectionButton(
			"<html>\u02fd</html>",
			new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("\u0020"),
			"$\\sqcup$");

	public static final JIndirectSelectionButton VK_SPACE_SYMBOL_SIMPLE = new JIndirectSelectionButton(
			"_", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("\u0020"),
			"$\\sqcup$");

	/*
	 * public static final JVirtualKeyboardButton VK_SPACE_SYMBOL = new
	 * JVirtualKeyboardButton( "\u02fd", new AppendCommand("\u0020"),
	 * "$\\sqcup$");
	 */
	public static final JIndirectSelectionButton VK_SPACE_TEXT = new JIndirectSelectionButton(
			"SP", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("\u0020"),
			"$\\sqcup$");

	// CONTROL COMMANDS
	// public static final JIndirectSelectionButton VK_DEL = new
	// JIndirectSelectionButton(
	// "DEL", new DeleteCommand(), "?`");
	public static final JIndirectSelectionButton VK_DEL = new JIndirectSelectionButton(
			"DEL", new DeleteCommand(), "\\#");

	public static final JIndirectSelectionButton VK_DEL_SYMBOL2 = new JIndirectSelectionButton(
			"\u00bf", new DeleteCommand(), "?`");

	// to indicate delete, use the symbol of \u00ab is double less than sign,
	// \u21d0 is double back arrow
	public static final JIndirectSelectionButton VK_DEL_SINGLE_SYMBOL = new JIndirectSelectionButton(
			"\u21d0", new DeleteCommand(), "\\#");

	// \u00bf is unicode for inverted question mark (used to append this
	// character)
	public static final JIndirectSelectionButton VK_DEL_SYMBOL = new JIndirectSelectionButton(
			"\u00bf", new DeleteCommand(), "?`");

	// \u00b6 is unicode for "Pilcrow" sign (backwards paragraph symbol) which
	// will be
	// used here to denote "enter" or "send to TTS", \P in math mode in LaTeX
	// generates the same symbol
	public static final JIndirectSelectionButton VK_PILCROW = new JIndirectSelectionButton(
			"\u00b6", new SendToTTSCommand(), "$\\P$");

	public static final JIndirectSelectionButton VK_PILCROW_FINISH = new JIndirectSelectionButton(
			"\u00b6", new SignifyFinishedCommand("\u00b6"), "$\\P$");

	public static final JIndirectSelectionButton VK_REVIEW_COMMAND = new JIndirectSelectionButton(
			"\u00a4", new GoToReviewModeCommand("\u00b6"), "$\\P$");

	// to indicate delete, use the symbol of \u00ab is double less than sign,
	// \u21d0 is double back arrow
	public static final JIndirectSelectionButton VK_CAPS_TOGGLE = new JIndirectSelectionButton(
			"\u21d1", new CapsToggleCommand(), "\\#");

	public static final JIndirectSelectionButton VK_EMPTY = new JIndirectSelectionButton(
			"", null);

	public Double setConditionalProbability(SourceSymbol targetSymbol,
			double prob) {
		return conditionalProbabilities.put(targetSymbol, prob);
	}

	public double getConditionalProbability(SourceSymbol targetSymbol) {
		return conditionalProbabilities.get(targetSymbol);
	}

	public void setTextLabel(String upperCase) {
		this.setText(upperCase);
	}

}
