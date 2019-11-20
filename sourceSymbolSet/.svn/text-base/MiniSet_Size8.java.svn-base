package sourceSymbolSet;

import customGUIComponentsISF.JIndirectSelectionButton;
import sourceSymbolSet.SourceSymbol;

/**
 * 
 * @author mb
 * 
 */
public class MiniSet_Size8 extends SourceSymbolSet {

	public MiniSet_Size8() {
		this.addToSymbolSet(JIndirectSelectionButton.VK_E, 1);
		this.addToSymbolSet(JIndirectSelectionButton.VK_A, 1);
		this.addToSymbolSet(JIndirectSelectionButton.VK_N, 1);
		this.addToSymbolSet(JIndirectSelectionButton.VK_I, 1);
		this.addToSymbolSet(JIndirectSelectionButton.VK_C, 1);
		this.addToSymbolSet(JIndirectSelectionButton.VK_U, 1);
		this.addToSymbolSet(JIndirectSelectionButton.VK_B, 1);
		this.addToSymbolSet(JIndirectSelectionButton.VK_K, 1);

		if (!this.renormalize()) {
			throw new RuntimeException(
					"The probability has a problem, the sum is: "
							+ this.getSumOfProbabilities());
		}
	}
}
