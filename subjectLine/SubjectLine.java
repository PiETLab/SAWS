package subjectLine;

import java.awt.Color;

import javax.swing.JButton;

import memoApplicationCommands.AddresseeSelectionCommand;
import memoApplicationCommands.SubjectLineSelectedCommand;

import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import sourceSymbolSet.SourceSymbol;

public class SubjectLine extends JButton implements SourceSymbol {

	private String fullName;
	private double probability;
	private IndirectSelectionFaciltyCommand associatedCommand;

	public SubjectLine(String subjectLineToBeParsedInTheFuture) {
		fullName = subjectLineToBeParsedInTheFuture;
		probability = 0;
		associatedCommand = new SubjectLineSelectedCommand(this);
		// probability = System.currentTimeMillis();
		// System.gc(); // run this to slow things down, so that each addressee
		// will have a unique timestamp
	}

	public int compareTo(SourceSymbol sgm) {
		System.out.println("Comparing: " + this + " and " + sgm);
		return this.fullName.compareTo(((SubjectLine) sgm).getFullName());
	}

	public int compareTo(SubjectLine sgm) {
		System.out.println("aComparing: " + this + " and " + sgm);
		return this.fullName.compareTo(((SubjectLine) sgm).getFullName());
	}

	public String getFullName() {
		return this.fullName;
	}

	public boolean equals(SourceSymbol sgm) {
		// System.out.println("Is equals: " + this + " and " + sgm);
		return (this.getTextLabel().equals(sgm.getTextLabel()));
	}

	public boolean equals(SubjectLine sgm) {
		// System.out.println("aIs equals: " + this + " and " + sgm);
		return (this.getTextLabel().equals(sgm.getTextLabel()));
	}

	public double getConditionalProbability(SourceSymbol targetSymbol) {
		return 0;
	}

	public Double getMarginalProbability() {
		return probability;
	}

	public String getTextLabel() {
		return this.getFullName();
	}

	public void setTextLabel(String upperCase) {
		fullName = upperCase;
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

	public SubjectLine clone() {
		return new SubjectLine(this.getFullName());
	}

	public String toString() {
		return this.getClass().getName() + ": " + this.getFullName() + "  "
				+ this.getMarginalProbability();
	}

}
