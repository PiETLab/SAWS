package probabilityDistributionsVOCA;

import customGUIComponentsISF.JIndirectSelectionButton;
import sourceSymbolSet.SourceSymbol;

/**
 * 
 * @author mb
 */
public class ProbDist_Fig3GolinRote98 extends
		UtilityClasses.ProbabilityDistribution<JIndirectSelectionButton> {
	private static final double p = 1.0/15.0;

	public ProbDist_Fig3GolinRote98() {
		this.put(SourceSymbol.VK_A, p);
		this.put(SourceSymbol.VK_B, p);
		this.put(SourceSymbol.VK_C, p);
		this.put(SourceSymbol.VK_D, p);
		this.put(SourceSymbol.VK_E, p);
		this.put(SourceSymbol.VK_F, p);
		this.put(SourceSymbol.VK_G, p);
		this.put(SourceSymbol.VK_H, p);
		this.put(SourceSymbol.VK_I, p);
		this.put(SourceSymbol.VK_J, p);
		this.put(SourceSymbol.VK_K, p);
		this.put(SourceSymbol.VK_L, p);
		this.put(SourceSymbol.VK_M, p);
		this.put(SourceSymbol.VK_N, p);
		this.put(SourceSymbol.VK_O, p);
	}
}
