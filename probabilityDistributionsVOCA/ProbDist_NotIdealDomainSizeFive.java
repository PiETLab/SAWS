package probabilityDistributionsVOCA;

import customGUIComponentsISF.JIndirectSelectionButton;
import sourceSymbolSet.SourceSymbol;

/**
 * A makeshift probability distribution.
 * 
 * http://mathforum.org/epigone/sci.math.num-analysis/frayclonlax/76pm1t$6j@bgtnsc03.worldnet.att.net
 * "One-Gram Probability Distribution" from Alan G. Konheim's "Cryptography -- A
 * Primer," John Wiley, 1981, p. 16:
 * 
 * 
 * above distribution multiplied by 100000; assumes space occurs every 5
 * characters (therefore count in 100000/5); assume mean gloss length is 5 words
 * (mean of 5 characters each). Every 5*5+4 (spaces) = 29 characters, or
 * 100000/29 assume punctuation has middle-of-road frequency assume digits are
 * low frequency
 * 
 * See
 * "mb/Documents/ProfContrib/ResearchProjects/TextCompFacilitiesDesign/spreadsheets/ProbabilityDistributions.xls"
 * 
 * @author mb
 * 
 */
public class ProbDist_NotIdealDomainSizeFive extends
		UtilityClasses.ProbabilityDistribution<JIndirectSelectionButton> {

	/**
	 * 
	 * The probabilities have the following properties: all are negative powers
	 * of 2; none are bigger than 2^(-1)
	 * 
	 * 2^(-2) = 1/4
	 * 
	 * e 1/4
	 * t 1/4
	 * a 1/4
	 * o 1/8
	 * n 1/8
	 */
	public ProbDist_NotIdealDomainSizeFive() {
		final double DELTA = 0.005;
		this.put(SourceSymbol.VK_E, Math.pow(2,-2)+DELTA);
		this.put(SourceSymbol.VK_T, Math.pow(2,-2)-DELTA);
		this.put(SourceSymbol.VK_A, Math.pow(2,-2));
		this.put(SourceSymbol.VK_O, Math.pow(2,-3));
		this.put(SourceSymbol.VK_N, Math.pow(2,-3));
	}
}
