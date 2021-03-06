package abstractOnScreenIndirectSelectionKeyboard;

import buttonLayouts.ButtonLayoutSpecification;
import customGUIComponentsISF.JIndirectSelectionButton;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;
import encodingTrees.obsolete.MyTraversableEncodingTree;
import treeDataStructure.Node;
import UtilityClassesISF.ProbabilityDistribution;

/**
 * This class implements a indirect text entry system. It derives the encoding
 * tree is on the basis of a gridded keyboard layout. The encoding is then
 * derived directly from the KBL (e.g., using the rows and columns as the basis
 * for the structure of the encoding tree and the order of the nodes within it).
 * 
 * TES1: encoding tree derived from KBL
 * 
 * @author Melanie Baljko
 */
public abstract class AbstractOnScreenIndirectSelectionKeyboard_KBLthenET
		extends AbstractOnScreenIndirectSelectionKeyboard {

	/**
	 * Constructor, which must be given a probability distribution over the
	 * selectables. The class that extends this abstract class will have defined
	 * the body of the abstract methods "setUpKeyboardLayout" and
	 * "buildContainmentHierarchy". See this class for how the two entities (KBL
	 * and CH) interact.
	 * 
	 * @param pd
	 *            the probability distribution over the selectables
	 * 
	 * PRE the domain of the PD corresponds to the active buttons on the KBL
	 */
	public AbstractOnScreenIndirectSelectionKeyboard_KBLthenET() {
		super();
		this.setKeyboardLayout(deriveKeyboardLayout());
		this.encodingTree = new MyTraversableEncodingTree(
				constructEncodingFromKeyboardLayout(this.getKeyboardLayout()));
		super.setSelectableSet(this.getKeyboardLayout().getSelectables());
	}

	public abstract ButtonLayoutSpecification deriveKeyboardLayout();

	/**
	 * This method builds the containment hierarchy
	 * 
	 * @return the root node of the containment hierarchy
	 */
	public abstract Node constructEncodingFromKeyboardLayout(ButtonLayoutSpecification kbl);

	@Override
	/**
	 * There is no probability distribution associated with the encoding tree
	 * that is derived from the KBL (although we can conceptualize that there is
	 * one latent, see Foad's thesis)
	 * 
	 */
	public ProbabilityDistribution getProbDist() {
		return null;
	}

}
