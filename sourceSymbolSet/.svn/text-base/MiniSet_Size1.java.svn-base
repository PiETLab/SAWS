package sourceSymbolSet;

import customGUIComponentsISF.JIndirectSelectionButton;
import sourceSymbolSet.SourceSymbol;

/**
 * 
 * @author mb
 * 
 */
public class MiniSet_Size1 extends SourceSymbolSet {

	public MiniSet_Size1() {
		this.addToSymbolSet(JIndirectSelectionButton.VK_Q, 1);

		if (!this.renormalize()) {
			throw new RuntimeException(
					"The probability has a problem, the sum is: "
							+ this.getSumOfProbabilities());
		}
	}
}
