package sourceSymbolSet;

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
public class MiniSet_Size16 extends SourceSymbolSet {

	public MiniSet_Size16() {
		// For ease-of-use, compose the probability distribution in a
		// spreadsheet and then cut and paste the lines below

		// this.addToSymbolSet(Selectable.VK_SPACE_SYMBOL, 0.166777851901267);

		this.addToSymbolSet(JIndirectSelectionButton.VK_SPACE_TEXT,
				0.166777851901267);
		this.addToSymbolSet(JIndirectSelectionButton.VK_E, 0.108739159439626);
		this.addToSymbolSet(JIndirectSelectionButton.VK_T, 0.0871414276184123);
		this.addToSymbolSet(JIndirectSelectionButton.VK_A, 0.0713809206137425);
		// // this.addToSymbolSet(JIndirectSelectionButton.VK_O,
		// 0.0664609739826551);
		this.addToSymbolSet(JIndirectSelectionButton.VK_N, 0.0589559706470981);
		this.addToSymbolSet(JIndirectSelectionButton.VK_R, 0.056454302868579);
		this.addToSymbolSet(JIndirectSelectionButton.VK_I, 0.0522848565710474);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_S,
		// 0.0506170780520347);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_H,
		// 0.0440293529019346);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_D,
		// 0.0315210140093396);
		//
		// this.addToSymbolSet(JIndirectSelectionButton.VK_L,
		// 0.0282688458972648);
		this.addToSymbolSet(JIndirectSelectionButton.VK_F, 0.0240993995997331);
		this.addToSymbolSet(JIndirectSelectionButton.VK_C, 0.0232655103402268);
		//
		this.addToSymbolSet(JIndirectSelectionButton.VK_M, 0.0207638425617078);
		this.addToSymbolSet(JIndirectSelectionButton.VK_U, 0.0207638425617078);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_G,
		// 0.0165943962641761);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_P,
		// 0.0165943962641761);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_Y,
		// 0.0165943962641761);
		this.addToSymbolSet(JIndirectSelectionButton.VK_W, 0.0124249499666444);
		this.addToSymbolSet(JIndirectSelectionButton.VK_B, 0.0115910607071381);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_V,
		// 0.0076717811874583);
		this.addToSymbolSet(JIndirectSelectionButton.VK_K, 0.00350233488992662);
		this.addToSymbolSet(JIndirectSelectionButton.VK_X, 0.00141761174116077);
		this.addToSymbolSet(JIndirectSelectionButton.VK_J, 0.00108405603735824);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_Q,
		// 0.0010006671114076);

		if (!this.renormalize()) {
			throw new RuntimeException(
					"The probability has a problem, the sum is :"
							+ this.getSumOfProbabilities());
		}
	}
}
