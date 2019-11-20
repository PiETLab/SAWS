package encodingTrees;

import java.io.Serializable;
import java.util.List;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;
import IndirectSelectionFacility.OnScreenKeyboardView;
import treeDataStructure.Node;

/**
 * This interface specifies an encoding tree of a set of source symbols.
 * 
 * 
 * It is a hierarchy of nodes.
 * 
 * implements a containment hierarchy, which is a tree whose nodes are instances
 * of Node (either InternalNode or LeafNode).
 * <p>
 * Each node in the encoding tree is associated an object which is an instance
 * of a SelectionGroup.
 * <p>
 * Leaf nodes must be associated with trivial selection groups. Internal nodes
 * must be associated with non-trivial selection groups. Moreover, the selection
 * group of a parent must contain exactly the union of the members of the
 * selection group of each child.
 * 
 * @author Melanie Baljko
 */
public interface EncodingTree {

	/**
	 * @return the name of the particular subclass of this encoding tree
	 */
	public abstract String getCreatingClass();

	public abstract Node getRoot();

	public abstract int getDepth();

	public abstract String toString();

	public abstract String toStringPlainTextLispStyle();

	public abstract Code getCode();

	public abstract String toStringLaTeXLispStyle();

	public double getConditionalProbability(SourceSymbol transmittedSymbolNode,
			SourceSymbol targetSymbol);


	/**
	 * @param isShowInternalEdgeCostsOn
	 *            whether or not to show the costs on incoming edges to internal
	 *            nodes
	 * @param isShowNonInternalEdgeCostsOn
	 *            whether or not to show the costs on incoming edges to leaf
	 *            nodes
	 */
	public abstract String toStringLaTeXecltree(boolean isShowNodeIDsOn,
			boolean isShowInternalEdgeCostsOn,
			boolean isShowNonInternalEdgeCostsOn);

	/**
	 * 
	 * 
	 * @return
	 */
	public abstract String toStringLaTeXecltreeProbs();

	// public abstract void constructRowColEncodingFromKeyboardLayout(
	// KeyboardLayout onScreenKeyboard);
	//
	// public abstract void constructLinearEncodingFromKeyboardLayout(
	// KeyboardLayout onScreenKeyboard);
	//
	// public abstract void constructBinaryEncodingFromKeyboardLayout(
	// KeyboardLayout onScreenKeyboard);

	public abstract List<SourceSymbol> getLeaves();

	public abstract void registerEndOfConstructingTime();

	public abstract long getTimeToCreate();

	public abstract String getSourceSymbolSetIdentifier();
	
	public abstract SourceSymbolSet getSourceSymbolSet();

}