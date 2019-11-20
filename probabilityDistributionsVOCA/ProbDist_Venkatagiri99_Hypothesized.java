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
 * @modified by Fatima
 */
public class ProbDist_Venkatagiri99_Hypothesized extends
		UtilityClasses.ProbabilityDistribution {

	public ProbDist_Venkatagiri99_Hypothesized() {
		super();
		// For ease-of-use, compose the probability distribution in a
		// spreadsheet and then cut and paste the lines below
		/*
		 * this.put(Selectable.VK_SPACE_SYMBOL,0.165111293);
		 * this.put(Selectable.VK_E, 0.107659004);
		 * this.put(Selectable.VK_T,0.086279489);
		 * this.put(Selectable.VK_A,0.070678221); this.put(Selectable.VK_O,
		 * 0.065807984); this.put(Selectable.VK_I ,0.058378809);
		 * this.put(Selectable.VK_N, 0.055902417); this.put(Selectable.VK_S,
		 * 0.051775098); this.put(Selectable.VK_R, 0.05012417);
		 * this.put(Selectable.VK_H, 0.043603005); this.put(Selectable.VK_L,
		 * 0.031221046); this.put(Selectable.VK_U, 0.023874417);
		 * this.put(Selectable.VK_C, 0.023048954); this.put(Selectable.VK_F,
		 * 0.020572562); this.put(Selectable.VK_M, 0.020572562);
		 * this.put(Selectable.VK_W, 0.016445242); this.put(Selectable.VK_Y,
		 * 0.016445242); this.put(Selectable.VK_G, 0.016445242);
		 * this.put(Selectable.VK_P, 0.012317923);
		 * this.put(Selectable.VK_B,0.011492459);
		 * this.put(Selectable.VK_V,0.007612778);
		 * this.put(Selectable.VK_DIGIT,0.005672938);
		 * this.put(Selectable.VK_K,0.003485459);
		 * this.put(Selectable.VK_X,0.001421799);
		 * this.put(Selectable.VK_J,0.001091613);
		 * this.put(Selectable.VK_Q,0.001009067);
		 * this.put(Selectable.VK_Z,0.000678882);
		 * this.put(Selectable.VK_COMMA,0.000670627);
		 * this.put(Selectable.VK_PERIOD,0.000662372);
		 * this.put(Selectable.VK_QUESTIONMARK,0.000654118);
		 * this.put(Selectable.VK_APOSTROPHE,0.000645863);
		 * this.put(Selectable.VK_PILCROW,0.000637608);
		 */

		// this.put(Selectable.VK_DEL_SYMBOL, 0.000610843293931189);
		// this.put(Selectable.VK_SPACE_TEXT, 0.165092782143565);
		this.put(SourceSymbol.VK_SPACE_SYMBOL, 0.165092782143565);
		this.put(SourceSymbol.VK_E, 0.107640493957604);
		this.put(SourceSymbol.VK_T, 0.0862609786700125);
		this.put(SourceSymbol.VK_A, 0.0706597107574457);
		this.put(SourceSymbol.VK_O, 0.0657894736842105);
		this.put(SourceSymbol.VK_I, 0.0583602984877501);
		this.put(SourceSymbol.VK_N, 0.0558839067555966);
		this.put(SourceSymbol.VK_S, 0.0517565872020075);
		this.put(SourceSymbol.VK_R, 0.0501056593805719);
		this.put(SourceSymbol.VK_H, 0.0435844944859011);
		this.put(SourceSymbol.VK_L, 0.0312025358251337);
		this.put(SourceSymbol.VK_D, 0.0279832265733342);
		this.put(SourceSymbol.VK_U, 0.0238559070197451);
		this.put(SourceSymbol.VK_C, 0.0230304431090273);
		this.put(SourceSymbol.VK_F, 0.0205540513768738);
		this.put(SourceSymbol.VK_M, 0.0205540513768738);
		this.put(SourceSymbol.VK_W, 0.0164267318232847);
		this.put(SourceSymbol.VK_Y, 0.0164267318232847);
		this.put(SourceSymbol.VK_G, 0.0164267318232847);
		this.put(SourceSymbol.VK_P, 0.0122994122696956);
		this.put(SourceSymbol.VK_B, 0.0114739483589777);
		this.put(SourceSymbol.VK_V, 0.00759426797860398);
		this.put(SourceSymbol.VK_K, 0.00346694842501486);
		this.put(SourceSymbol.VK_X, 0.0014032886482203);
		this.put(SourceSymbol.VK_J, 0.00107310308393317);
		this.put(SourceSymbol.VK_Q, 0.000990556692861388);
		this.put(SourceSymbol.VK_Z, 0.000660371128574259);
		this.put(SourceSymbol.VK_COMMA, 0.00065211648946708);
		this.put(SourceSymbol.VK_PERIOD, 0.000643861850359902);
		this.put(SourceSymbol.VK_QUESTIONMARK, 0.000635607211252724);
		this.put(SourceSymbol.VK_APOSTROPHE, 0.000627352572145546);
		this.put(SourceSymbol.VK_PILCROW, 0.000619097933038368);
		this.put(SourceSymbol.VK_DEL, 0.000610843293931189);
		this.put(SourceSymbol.VK_1, 0.000602588654824011);
		this.put(SourceSymbol.VK_2, 0.000594334015716833);
		this.put(SourceSymbol.VK_3, 0.000586079376609655);
		this.put(SourceSymbol.VK_4, 0.000577824737502476);
		this.put(SourceSymbol.VK_5, 0.000569570098395298);
		this.put(SourceSymbol.VK_6, 0.00056131545928812);
		this.put(SourceSymbol.VK_7, 0.000553060820180942);
		this.put(SourceSymbol.VK_8, 0.000544806181073763);
		this.put(SourceSymbol.VK_9, 0.000536551541966585);
		this.put(SourceSymbol.VK_0, 0.000528296902859407);
		// */
		if (this.isProbabilitySumValid()) {
			throw new RuntimeException(
					"The probability has a problem, the um is :"
							+ this.getSumOfProbabilities());
		}
	}
}
