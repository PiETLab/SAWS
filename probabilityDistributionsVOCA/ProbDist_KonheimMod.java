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
public class ProbDist_KonheimMod extends UtilityClasses.ProbabilityDistribution<JIndirectSelectionButton> {

	public ProbDist_KonheimMod() {
		// For ease-of-use, compose the probability distribution in a
		// spreadsheet and then cut and paste the lines below

		// the following two are interchangeable
		this.put(SourceSymbol.VK_SPACE_SYMBOL, 0.148756396525051);
		//this.put(Selectable.VK_SPACE_TEXT, 0.148756396525051);
		
		this.put(SourceSymbol.VK_E, 0.096989170534333);
		this.put(SourceSymbol.VK_T, 0.0777252171843389);
		this.put(SourceSymbol.VK_A, 0.0636677377127216);
		this.put(SourceSymbol.VK_O, 0.0592794240152326);
		this.put(SourceSymbol.VK_N, 0.0525853861716054);
		this.put(SourceSymbol.VK_R, 0.0503540402237296);
		this.put(SourceSymbol.VK_I, 0.0466351303106034);
		this.put(SourceSymbol.VK_S, 0.0451475663453529);
		this.put(SourceSymbol.VK_H, 0.0392716886826134);
		this.put(SourceSymbol.VK_D, 0.0281149589432346);
		this.put(SourceSymbol.VK_PILCROW, 0.0256456027609187);
		this.put(SourceSymbol.VK_L, 0.0252142092109961);
		this.put(SourceSymbol.VK_F, 0.0214952992978698);
		this.put(SourceSymbol.VK_C, 0.0207515173152446);
		this.put(SourceSymbol.VK_APOSTROPHE, 0.0185945495656313);
		this.put(SourceSymbol.VK_COMMA, 0.0185945495656313);
		this.put(SourceSymbol.VK_PERIOD, 0.0185945495656313);
		this.put(SourceSymbol.VK_QUESTIONMARK, 0.0185945495656313);
		this.put(SourceSymbol.VK_M, 0.0185201713673688);
		this.put(SourceSymbol.VK_U, 0.0185201713673688);
		this.put(SourceSymbol.VK_G, 0.0148012614542425);
		this.put(SourceSymbol.VK_P, 0.0148012614542425);
		this.put(SourceSymbol.VK_Y, 0.0148012614542425);
		this.put(SourceSymbol.VK_W, 0.0110823515411163);
		this.put(SourceSymbol.VK_B, 0.010338569558491);
		this.put(SourceSymbol.VK_V, 0.00684279424015233);
		this.put(SourceSymbol.VK_K, 0.00312388432702606);
		this.put(SourceSymbol.VK_X, 0.00126442937046293);
		this.put(SourceSymbol.VK_J, 0.000966916577412829);
		this.put(SourceSymbol.VK_Q, 0.000892538379150303);
		this.put(SourceSymbol.VK_0, 0.000743781982625253);
		this.put(SourceSymbol.VK_1, 0.000743781982625253);
		this.put(SourceSymbol.VK_2, 0.000743781982625253);
		this.put(SourceSymbol.VK_3, 0.000743781982625253);
		this.put(SourceSymbol.VK_4, 0.000743781982625253);
		this.put(SourceSymbol.VK_5, 0.000743781982625253);
		this.put(SourceSymbol.VK_6, 0.000743781982625253);
		this.put(SourceSymbol.VK_7, 0.000743781982625253);
		this.put(SourceSymbol.VK_8, 0.000743781982625253);
		this.put(SourceSymbol.VK_9, 0.000743781982625253);
		this.put(SourceSymbol.VK_Z, 0.000595025586100202);
		
		this.put(SourceSymbol.VK_DEL, 0.0);
		//this.put(Selectable.VK_DEL_SYMBOL, 0.0);
	}
}
