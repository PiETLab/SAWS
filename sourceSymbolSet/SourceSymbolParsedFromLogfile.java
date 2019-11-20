package sourceSymbolSet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import customGUIComponentsISF.JIndirectSelectionButton;

import IndirectSelectionFacilityCommands.AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand;
import IndirectSelectionFacilityCommands.DeleteCommand;
import IndirectSelectionFacilityCommands.GoToReviewModeCommand;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import IndirectSelectionFacilityCommands.SendToTTSCommand;
import IndirectSelectionFacilityCommands.SignifyFinishedCommand;

public class SourceSymbolParsedFromLogfile implements SourceSymbol {

	private IndirectSelectionFaciltyCommand command;

	private double probabilityOfOccurrence;

	private String equivLaTeX;

	private String theSourceSymbolString;

	private Map<SourceSymbol, Double> conditionalProbabilities = new TreeMap<SourceSymbol, Double>();

	public SourceSymbolParsedFromLogfile(String sourceSymbol,
			double sourceSymbolProbability) {
		this.theSourceSymbolString = sourceSymbol;
		equivLaTeX = sourceSymbol;
		this.setMarginalProbability(sourceSymbolProbability);
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

	public double getConditionalProbability(SourceSymbol targetSymbol) {
		return conditionalProbabilities.get(targetSymbol);
	}

	public Double getMarginalProbability() {
		return probabilityOfOccurrence;
	}

	public String getTextLabel() {
		return "";
	}


	public IndirectSelectionFaciltyCommand getVOCACommand() {
		return command;
	}

	public void setBackground(Color color) {
	}

	public void setMarginalProbability(double prob) {
		probabilityOfOccurrence = prob;
	}

	public String toStringLaTeX() {
		return equivLaTeX;
	}

	public String toStringOneChar() {
		return null;
	}

	public Double setConditionalProbability(SourceSymbol targetSymbol,
			double prob) {
		return conditionalProbabilities.put(targetSymbol, prob);
	}

	public SourceSymbolParsedFromLogfile clone() {
		SourceSymbolParsedFromLogfile but = new SourceSymbolParsedFromLogfile(
				this.theSourceSymbolString, this.getMarginalProbability());
		return but;

	}

	public void setTextLabel(String upperCase) {
	}

}
