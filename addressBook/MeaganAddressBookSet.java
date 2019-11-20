package addressBook;

import customGUIComponentsISF.JIndirectSelectionButton;
import sourceSymbolSet.SourceSymbolSet;

/**
 * A makeshift probability distribution.
 * 
 * http://mathforum.org/epigone/sci.math.num-analysis/frayclonlax/76pm1t$6j@bgtnsc03
 * .worldnet.att.net "One-Gram Probability Distribution" from Alan G. Konheim's
 * "Cryptography -- A Primer," John Wiley, 1981, p. 16:
 * 
 * 
 * above distribution multiplied by 100000; assumes space occurs every 5
 * characters (therefore count in 100000/5); assume mean gloss length is 5 words
 * (mean of 5 characters each). Every 5*5+4 (spaces) = 29 characters, or
 * 100000/29 assume punctuation has middle-of-road frequency assume digits are
 * low frequency
 * 
 * See"mb/Documents/ProfContrib/ResearchProjects/TextCompFacilitiesDesign/spreadsheets/ProbabilityDistributions.xls"
 * 
 * @author mb
 * @modified by Fatima
 */
public class MeaganAddressBookSet extends SourceSymbolSet {

	public MeaganAddressBookSet() {
		super();

		// Addressee a = new Addressee("Mom");
		// Addressee b = new Addressee("Dad");
		// Addressee c = new Addressee("Pat");
		// Addressee d = new Addressee("Felicia");
		// Addressee e = new Addressee("Robin");
		// Addressee f = new Addressee("Bill");
		// For ease-of-use, compose the probability distribution in a
		// spreadsheet and then cut and paste the lines below
		// this.addToSymbolSet(a, 6);
		// this.addToSymbolSet(b, 5);
		// this.addToSymbolSet(c, 4);
		// this.addToSymbolSet(d, 3);
		// this.addToSymbolSet(e, 2);
		// this.addToSymbolSet(f, 1);
		// this.renormalize();

		// this.renormalize();
		// if (!this.isSumsTo1()) {
		// throw new RuntimeException(
		// "The probability has a problem, the sum is :"
		// + this.getSumOfProbabilities());
		// }
	}

	public void add(Addressee a) {
		this.addToSymbolSet(a, a.getMarginalProbability());
		this.renormalize();
	}
}
