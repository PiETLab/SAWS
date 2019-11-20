package memoControl;

import java.awt.Color;

import javax.swing.JButton;

import memoApplicationCommands.AddresseeSelectionCommand;
import memoApplicationCommands.EditOptionCommand;
import memoApplicationCommands.CompleteTheMemoBodyFieldCommand;
import memoApplicationCommands.MemoBodyGoToReviewSourceSymbolCommand;

import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import sourceSymbolSet.SourceSymbol;

public class OptionGoToReviewMode extends EditMenuOption {

	private String fullName = "Review Memo Body";
	private double probability;
	private IndirectSelectionFaciltyCommand associatedCommand;

	public OptionGoToReviewMode() {
		probability = 0;
		associatedCommand = new MemoBodyGoToReviewSourceSymbolCommand();
		// probability = System.currentTimeMillis();
		// System.gc(); // run this to slow things down, so that each addressee
		// will have a unique timestamp
	}

	public String getTextLabel() {
		return "Read";
	}

	public String getFullName() {
		return this.fullName;
	}

	public int compareTo(SourceSymbol sgm) {
		System.out.println("Comparing: " + this + " and " + sgm);
		return this.fullName.compareTo(((OptionGoToReviewMode) sgm)
				.getFullName());
	}

	public int compareTo(OptionGoToReviewMode sgm) {
		System.out.println("aComparing: " + this + " and " + sgm);
		return this.fullName.compareTo(((OptionGoToReviewMode) sgm)
				.getFullName());
	}

	public boolean equals(SourceSymbol sgm) {
		// System.out.println("Is equals: " + this + " and " + sgm);
		return (this.getTextLabel().equals(sgm.getTextLabel()));
	}

	public boolean equals(OptionGoToReviewMode sgm) {
		// System.out.println("aIs equals: " + this + " and " + sgm);
		return (this.getTextLabel().equals(sgm.getTextLabel()));
	}

	public double getConditionalProbability(SourceSymbol targetSymbol) {
		return 0;
	}

	public Double getMarginalProbability() {
		return probability;
	}

	public IndirectSelectionFaciltyCommand getVOCACommand() {
		return associatedCommand;
	}

	public void setBackground(Color color) {
		// TODO Auto-generated method stub

	}

	public Double setConditionalProbability(SourceSymbol targetSymbol,
			double prob) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMarginalProbability(double prob) {
		// TODO Auto-generated method stub

	}

	public String toStringLaTeX() {
		return this.getFullName();
	}

	/**
	 * must be unique!!!
	 * 
	 */
	public String toStringOneChar() {
		return this.getFullName().substring(0, 1);
	}

	public OptionGoToReviewMode clone() {
		return null; // new EditMenuOption(this.getFullName());
	}

	public void setTextLabel(String upperCase) {
		// TODO Auto-generated method stub
		
	}

}
