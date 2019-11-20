package probabilityDistributionsVOCA;

import customGUIComponentsISF.JIndirectSelectionButton;
import sourceSymbolSet.SourceSymbol;

/**
 * 
 * @author mb
 */
public class ProbDist_Fig2GolinRote98 extends
		UtilityClasses.ProbabilityDistribution<JIndirectSelectionButton> {
	private static final double p = 1.0/5.0;

	public ProbDist_Fig2GolinRote98() {
		this.put(SourceSymbol.VK_A, p);
		this.put(SourceSymbol.VK_B, p);
		this.put(SourceSymbol.VK_C, p);
		this.put(SourceSymbol.VK_D, p);
		this.put(SourceSymbol.VK_E, p);
	}
}
