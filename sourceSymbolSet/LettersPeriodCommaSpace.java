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
public class LettersPeriodCommaSpace extends SourceSymbolSet {

	public LettersPeriodCommaSpace() {
		// For ease-of-use, compose the probability distribution in a
		// spreadsheet and then cut and paste the lines below

		this.addToSymbolSet(JIndirectSelectionButton.VK_SPACE_TEXT, 0.16010246557797);
		this.addToSymbolSet(JIndirectSelectionButton.VK_E, 0.104386807556836);
		this.addToSymbolSet(JIndirectSelectionButton.VK_T, 0.0836535382644893);
		this.addToSymbolSet(JIndirectSelectionButton.VK_A, 0.0685238552673711);
		this.addToSymbolSet(JIndirectSelectionButton.VK_O, 0.063800832532821);
		this.addToSymbolSet(JIndirectSelectionButton.VK_N, 0.0565962215818124);
		this.addToSymbolSet(JIndirectSelectionButton.VK_R, 0.0541946845981428);
		this.addToSymbolSet(JIndirectSelectionButton.VK_I, 0.0501921229586936);
		this.addToSymbolSet(JIndirectSelectionButton.VK_S, 0.0485910983029139);
		this.addToSymbolSet(JIndirectSelectionButton.VK_H, 0.0422670509125841);
		this.addToSymbolSet(JIndirectSelectionButton.VK_D, 0.0302593659942363);

		this.addToSymbolSet(JIndirectSelectionButton.VK_L, 0.0271373679154659);
		this.addToSymbolSet(JIndirectSelectionButton.VK_F, 0.0231348062760166);
		this.addToSymbolSet(JIndirectSelectionButton.VK_C, 0.0223342939481268);

		this.addToSymbolSet(JIndirectSelectionButton.VK_COMMA, 0.0200128081972462);
		this.addToSymbolSet(JIndirectSelectionButton.VK_PERIOD, 0.0200128081972462);

		this.addToSymbolSet(JIndirectSelectionButton.VK_M, 0.0199327569644572);
		this.addToSymbolSet(JIndirectSelectionButton.VK_U, 0.0199327569644572);
		this.addToSymbolSet(JIndirectSelectionButton.VK_G, 0.015930195325008);
		this.addToSymbolSet(JIndirectSelectionButton.VK_P, 0.015930195325008);
		this.addToSymbolSet(JIndirectSelectionButton.VK_Y, 0.015930195325008);
		this.addToSymbolSet(JIndirectSelectionButton.VK_W, 0.0119276336855588);
		this.addToSymbolSet(JIndirectSelectionButton.VK_B, 0.0111271213576689);
		this.addToSymbolSet(JIndirectSelectionButton.VK_V, 0.00736471341658661);
		this.addToSymbolSet(JIndirectSelectionButton.VK_K, 0.00336215177713737);
		this.addToSymbolSet(JIndirectSelectionButton.VK_X, 0.00136087095741274);
		this.addToSymbolSet(JIndirectSelectionButton.VK_J, 0.0010406660262568);
		this.addToSymbolSet(JIndirectSelectionButton.VK_Q, 0.000960614793467819);
		this.addToSymbolSet(JIndirectSelectionButton.VK_Z, 0.000595025586100202);

		if (!this.renormalize()) {
			throw new RuntimeException(
					"The probability has a problem, the sum is :"
							+ this.getSumOfProbabilities());
		}
	}
}
