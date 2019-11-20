package sourceSymbolSet;

import java.awt.Color;

import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public interface SourceSymbol extends Comparable<SourceSymbol> {

	public abstract String toStringLaTeX();

	public abstract void setBackground(Color color);

	public abstract SourceSymbol clone();

	// public abstract boolean isEnabled();

	public abstract int compareTo(SourceSymbol sgm);

	public abstract boolean equals(SourceSymbol sgm);

	public abstract String getTextLabel();

	public abstract String toString();

	/**
	 * This method returns a one-character representation for the selection
	 * group member
	 * 
	 * @return
	 */
	public abstract String toStringOneChar();

	public abstract IndirectSelectionFaciltyCommand getVOCACommand();

	/**
	 * @return the unconditional probability P(s) of the event s; that is, the
	 *         probability of this source symbol being transmitted.
	 */
	public abstract Double getMarginalProbability();

	public abstract void setMarginalProbability(double prob);

	public abstract double getConditionalProbability(SourceSymbol targetSymbol);

	public abstract Double setConditionalProbability(SourceSymbol targetSymbol,
			double prob);

	public abstract void setTextLabel(String upperCase);

}
