package sourceSymbolSet;

import customGUIComponentsISF.JIndirectSelectionButton;

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
public class MeaganCompositionSet extends SourceSymbolSet {

	public MeaganCompositionSet() {
		super();
		// For ease-of-use, compose the probability distribution in a
		// spreadsheet and then cut and paste the lines below
		this.addToSymbolSet(JIndirectSelectionButton.VK_SPACE_SYMBOL_SIMPLE,
				0.165092782143565);
		this.addToSymbolSet(JIndirectSelectionButton.VK_E, 0.107640493957604);
		this.addToSymbolSet(JIndirectSelectionButton.VK_T, 0.0862609786700125);
		this.addToSymbolSet(JIndirectSelectionButton.VK_A, 0.0706597107574457);
		this.addToSymbolSet(JIndirectSelectionButton.VK_O, 0.0657894736842105);
		this.addToSymbolSet(JIndirectSelectionButton.VK_I, 0.0583602984877501);
		this.addToSymbolSet(JIndirectSelectionButton.VK_N, 0.0558839067555966);
		this.addToSymbolSet(JIndirectSelectionButton.VK_S, 0.0517565872020075);
		this.addToSymbolSet(JIndirectSelectionButton.VK_R, 0.0501056593805719);
		this.addToSymbolSet(JIndirectSelectionButton.VK_H, 0.0435844944859011);
		this.addToSymbolSet(JIndirectSelectionButton.VK_L, 0.0312025358251337);
		this.addToSymbolSet(JIndirectSelectionButton.VK_D, 0.0279832265733342);
		this.addToSymbolSet(JIndirectSelectionButton.VK_U, 0.0238559070197451);
		this.addToSymbolSet(JIndirectSelectionButton.VK_C, 0.0230304431090273);
		this.addToSymbolSet(JIndirectSelectionButton.VK_F, 0.0205540513768738);
		this.addToSymbolSet(JIndirectSelectionButton.VK_M, 0.0205540513768738);
		this.addToSymbolSet(JIndirectSelectionButton.VK_W, 0.0164267318232847);
		this.addToSymbolSet(JIndirectSelectionButton.VK_Y, 0.0164267318232847);
		this.addToSymbolSet(JIndirectSelectionButton.VK_G, 0.0164267318232847);
		this.addToSymbolSet(JIndirectSelectionButton.VK_P, 0.0122994122696956);
		this.addToSymbolSet(JIndirectSelectionButton.VK_B, 0.0114739483589777);
		this.addToSymbolSet(JIndirectSelectionButton.VK_V, 0.00759426797860398);
		this.addToSymbolSet(JIndirectSelectionButton.VK_K, 0.00346694842501486);
		this.addToSymbolSet(JIndirectSelectionButton.VK_X, 0.0014032886482203);
		this.addToSymbolSet(JIndirectSelectionButton.VK_J, 0.00107310308393317);
		this
				.addToSymbolSet(JIndirectSelectionButton.VK_Q,
						0.000990556692861388);
		this
				.addToSymbolSet(JIndirectSelectionButton.VK_Z,
						0.000660371128574259);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_COMMA,
		// 0.00065211648946708);
		this.addToSymbolSet(JIndirectSelectionButton.VK_PERIOD,
				0.000643861850359902);

		this.addToSymbolSet(JIndirectSelectionButton.VK_CAPS_TOGGLE,
				0.000643861850359902);

		// this.addToSymbolSet(JIndirectSelectionButton.VK_QUESTIONMARK,
		// 0.000635607211252724);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_APOSTROPHE,
		// 0.000627352572145546);

		// this.addToSymbolSet(JIndirectSelectionButton.VK_PILCROW_FINISH,
		// 0.090619097933038368);
		this.addToSymbolSet(JIndirectSelectionButton.VK_REVIEW_COMMAND,
				0.090619097933038368);
		this.addToSymbolSet(JIndirectSelectionButton.VK_DEL_SINGLE_SYMBOL,
				0.000610843293931189);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_REVIEW_COMMAND,
		// 0.005610843293931189);

		// this
		// .addToSymbolSet(JIndirectSelectionButton.VK_1,
		// 0.000602588654824011);
		// this
		// .addToSymbolSet(JIndirectSelectionButton.VK_2,
		// 0.000594334015716833);
		// this
		// .addToSymbolSet(JIndirectSelectionButton.VK_3,
		// 0.000586079376609655);
		// this
		// .addToSymbolSet(JIndirectSelectionButton.VK_4,
		// 0.000577824737502476);
		// this
		// .addToSymbolSet(JIndirectSelectionButton.VK_5,
		// 0.000569570098395298);
		// this.addToSymbolSet(JIndirectSelectionButton.VK_6,
		// 0.00056131545928812);
		// this
		// .addToSymbolSet(JIndirectSelectionButton.VK_7,
		// 0.000553060820180942);
		// this
		// .addToSymbolSet(JIndirectSelectionButton.VK_8,
		// 0.000544806181073763);
		// this
		// .addToSymbolSet(JIndirectSelectionButton.VK_9,
		// 0.000536551541966585);
		// this
		// .addToSymbolSet(JIndirectSelectionButton.VK_0,
		// 0.000528296902859407);
		// */
		this.renormalize();
		if (!this.isSumsTo1()) {
			throw new RuntimeException(
					"The probability has a problem, the sum is :"
							+ this.getSumOfProbabilities());
		}
	}
}
