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
public class ProbDist_KonheimWithoutDigitsPunct extends
		UtilityClasses.ProbabilityDistribution<SourceSymbol> {

	public ProbDist_KonheimWithoutDigitsPunct() {
		// For ease-of-use, compose the probability distribution in a
		// spreadsheet and then cut and paste the lines below

		//this.put(Selectable.VK_SPACE_SYMBOL, 0.166777851901267);
		this.put(SourceSymbol.VK_SPACE_TEXT, 0.166777851901267);
		this.put(SourceSymbol.VK_E, 0.108739159439626);
		this.put(SourceSymbol.VK_T, 0.0871414276184123);
		this.put(SourceSymbol.VK_A, 0.0713809206137425);
		this.put(SourceSymbol.VK_O, 0.0664609739826551);
		this.put(SourceSymbol.VK_N, 0.0589559706470981);
		this.put(SourceSymbol.VK_R, 0.056454302868579);
		this.put(SourceSymbol.VK_I, 0.0522848565710474);
		this.put(SourceSymbol.VK_S, 0.0506170780520347);
		this.put(SourceSymbol.VK_H, 0.0440293529019346);
		this.put(SourceSymbol.VK_D, 0.0315210140093396);

		this.put(SourceSymbol.VK_L, 0.0282688458972648);
		this.put(SourceSymbol.VK_F, 0.0240993995997331);
		this.put(SourceSymbol.VK_C, 0.0232655103402268);

		this.put(SourceSymbol.VK_M, 0.0207638425617078);
		this.put(SourceSymbol.VK_U, 0.0207638425617078);
		this.put(SourceSymbol.VK_G, 0.0165943962641761);
		this.put(SourceSymbol.VK_P, 0.0165943962641761);
		this.put(SourceSymbol.VK_Y, 0.0165943962641761);
		this.put(SourceSymbol.VK_W, 0.0124249499666444);
		this.put(SourceSymbol.VK_B, 0.0115910607071381);
		this.put(SourceSymbol.VK_V, 0.0076717811874583);
		this.put(SourceSymbol.VK_K, 0.00350233488992662);
		this.put(SourceSymbol.VK_X, 0.00141761174116077);
		this.put(SourceSymbol.VK_J, 0.00108405603735824);
		this.put(SourceSymbol.VK_Q, 0.0010006671114076);
	}
}
