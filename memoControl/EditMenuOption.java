package memoControl;

import java.awt.Color;

import javax.swing.JButton;

import memoApplicationCommands.AddresseeSelectionCommand;
import memoApplicationCommands.EditOptionCommand;

import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import sourceSymbolSet.SourceSymbol;

public abstract class EditMenuOption extends JButton implements SourceSymbol {

	private String fullName;
	private double probability;
	private IndirectSelectionFaciltyCommand associatedCommand;

	public EditMenuOption() {
		probability = 0;
		associatedCommand = new EditOptionCommand(this);
		// probability = System.currentTimeMillis();
		// System.gc(); // run this to slow things down, so that each addressee
		// will have a unique timestamp
	}

	public int compareTo(SourceSymbol sgm) {
		System.out.println("Comparing: " + this + " and " + sgm);
		return this.fullName.compareTo(((EditMenuOption) sgm).getFullName());
	}

	public int compareTo(EditMenuOption sgm) {
		System.out.println("aComparing: " + this + " and " + sgm);
		return this.fullName.compareTo(((EditMenuOption) sgm).getFullName());
	}

	public String getFullName() {
		return this.getClass().getName();
	}

	public boolean equals(SourceSymbol sgm) {
		// System.out.println("Is equals: " + this + " and " + sgm);
		return (this.getTextLabel().equals(sgm.getTextLabel()));
	}

	public boolean equals(EditMenuOption sgm) {
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
		return this.getClass().getName();
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

	public EditMenuOption clone() {
		return null; // new EditMenuOption(this.getFullName());
	}

	public String toString() {
		return "Edit Submenu: " + this.getFullName() + "  "
				+ this.getMarginalProbability();
	}

}
