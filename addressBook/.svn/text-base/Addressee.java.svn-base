package addressBook;

import java.awt.Color;

import javax.swing.JButton;

import memoApplicationCommands.AddresseeSelectionCommand;

import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import sourceSymbolSet.SourceSymbol;

public class Addressee extends JButton implements SourceSymbol {

	private String fullName;
	private double probability;
	private IndirectSelectionFaciltyCommand associatedCommand;

	public Addressee(String addressFileLineToBeParsedInTheFuture) {
		fullName = addressFileLineToBeParsedInTheFuture;
		probability = 0;
		associatedCommand = new AddresseeSelectionCommand(this);
		// probability = System.currentTimeMillis();
		// System.gc(); // run this to slow things down, so that each addressee
		// will have a unique timestamp
	}

	public int compareTo(SourceSymbol sgm) {
		System.out.println("Comparing: " + this + " and " + sgm);
		return this.fullName.compareTo(((Addressee) sgm).getFullName());
	}

	public int compareTo(Addressee sgm) {
		System.out.println("aComparing: " + this + " and " + sgm);
		return this.fullName.compareTo(((Addressee) sgm).getFullName());
	}

	public String getFullName() {
		return this.fullName;
	}

	public boolean equals(SourceSymbol sgm) {
		// System.out.println("Is equals: " + this + " and " + sgm);
		return (this.getTextLabel().equals(sgm.getTextLabel()));
	}

	public boolean equals(Addressee sgm) {
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

	public Addressee clone() {
		return new Addressee(this.getFullName());
	}

	public String toString() {
		return this.getClass().getName() + "Ê: " + this.getFullName() + "  "
				+ this.getMarginalProbability();
	}

	public void setTextLabel(String upperCase) {
		this.fullName = upperCase;
	}

}
