package memoControl;

import addressBook.Addressee;
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
public class EditMenuOptionSet extends SourceSymbolSet {

	public EditMenuOptionSet() {
		super();
		EditMenuOption compose = new OptionGoToEditMode();
		EditMenuOption review = new OptionGoToReviewMode();
		EditMenuOption goToMemoFields = new OptionGoToMemoFieldLevel();
		this.addToSymbolSet(goToMemoFields, 1);
		this.addToSymbolSet(compose, 2);
		this.addToSymbolSet(review, 3);

		this.renormalize();
		// if (!this.isSumsTo1()) {
		// throw new RuntimeException(
		// "The probability has a problem, the sum is :"
		// + this.getSumOfProbabilities());
		// }
	}
}
