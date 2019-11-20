package indirectTextEntrySystemVariants;

import java.util.List;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import sourceSymbolSet.SourceSymbol;
import keyboardLayouts.RamayKeyboardLayoutParam;
import TreeDataStructure.Node;
import UtilityClasses.ProbabilityDistribution;
import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanWithUnequalLetterCost;

public class HuffmanWithUnequalLetterCost_RamayKeyboard extends
		AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanWithUnequalLetterCost {

	private final static int SIZE_OF_ENCODING_ALPHABET = 2;

	private final static ProbabilityDistribution<SourceSymbol> PROBABILITY_DISTRIBUTION = new ProbDist_Venkatagiri99_Hypothesized();

	public HuffmanWithUnequalLetterCost_RamayKeyboard() {
		super(PROBABILITY_DISTRIBUTION, SIZE_OF_ENCODING_ALPHABET);
	}

	public ButtonLayoutSpecification setUpKeyboardLayout() {
		return new RamayKeyboardLayoutParam(containmentHierarchy,
				selectableKeyList);
	}

	@Override
	public Node buildContainmentHierarchy(int encodingAlphabetSize,
			List<Node> allNodes) {
		// TODO Auto-generated method stub
		return null;
	}

}
