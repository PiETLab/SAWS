package probabilityDistributionsVOCA;

import sourceSymbolSet.SourceSymbol;

public class ProbDist_DomainSizeThree extends
		UtilityClasses.ProbabilityDistribution<SourceSymbol> {

	public ProbDist_DomainSizeThree() {
		this.put(SourceSymbol.VK_A, 0.70);
		this.put(SourceSymbol.VK_B, 0.20);
		this.put(SourceSymbol.VK_C, 0.10);
		this.put(SourceSymbol.VK_DEL, 0.0);
	}
}
