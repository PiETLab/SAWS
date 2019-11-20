package sourceSymbolSet;

import java.util.List;

import customGUIComponentsISF.JIndirectSelectionButton;


/**
 * 
 * @author mb
 */
public class SourceSymbolSetFromLeaves extends SourceSymbolSet {

	public SourceSymbolSetFromLeaves(List<SourceSymbol> leaves) {
		super();
		for (SourceSymbol symbol : leaves) {
			if (!symbol.equals(JIndirectSelectionButton.VK_EMPTY)) {
				this.addToSymbolSet(symbol, symbol.getMarginalProbability());
			}
		}
		this.renormalize();
	}
}
