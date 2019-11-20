/*
 * Created on 26-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package abstractOnScreenIndirectSelectionKeyboard;


import java.awt.*;
import java.util.Vector;
import java.util.List;

import javax.swing.JPanel;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import encodingTrees.EncodingUtilities;


import treeDataStructure.InternalNode;
import treeDataStructure.LeafNode;
import treeDataStructure.Node;

/**
 * This class implements a TES in which the encoding tree is is taken from the
 * keyboard layout (row-column format). Thus, the keyboard layout must be
 * specified prior to invoking the constructEncodingFromKeyboardLayout() method.
 * 
 * @author Melanie Baljko
 */
public abstract class AbstractBinaryTreeIndirectSelectionKeyboard extends
		AbstractOnScreenIndirectSelectionKeyboard_KBLthenET {

	public AbstractBinaryTreeIndirectSelectionKeyboard() {
		super();
	}

	@Override
	/**
	 * This method sets up the containment hierarchy.
	 * 
	 * PRE : the keyboard layout has been specified
	 * 
	 * Takes all the selectables, in row-major order (intended for single row,
	 * but could work for multiple rows); divides into half and assigns each to
	 * subtree; repeat until only leaves remain and no further sub-divisions can
	 * be made.
	 * 
	 * 
	 */
	public Node constructEncodingFromKeyboardLayout(
			ButtonLayoutSpecification keyboardLayout) {
		List<Node> forest = new Vector<Node>();
		for (JPanel currRow : keyboardLayout.getRows()) {
			Component[] x = currRow.getComponents();
			for (int i = 0; i < x.length; i++) {
				JIndirectSelectionButton tmpBut = ((JIndirectSelectionButton) x[i]);
				if (tmpBut.isEnabled()) {
					forest.add(new LeafNode(tmpBut));
				}
			}
		}
		int k = 2;
		Node root = EncodingUtilities.encodeKaryTree(forest, k);
		return root;
	}

}