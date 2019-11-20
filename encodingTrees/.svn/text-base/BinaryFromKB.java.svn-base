/*
 * Created on 26-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package encodingTrees;

import java.awt.Component;

import javax.swing.JPanel;

import buttonLayouts.ButtonLayoutSpecification;
import buttonLayouts.SingleRowLexico;

import customGUIComponentsISF.JIndirectSelectionButton;

import TreeDataStructure.InternalNode;
import TreeDataStructure.LeafNode;
import TreeDataStructure.Node;
import abstractOnScreenIndirectSelectionKeyboard.AbstractBinaryTreeIndirectSelectionKeyboard;
import abstractOnScreenIndirectSelectionKeyboard.AbstractLinearIndirectSelectionKeyboard;
import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import keyboardLayouts.VenkatagiriKeyboardLayout_A4;

/**
 * @author Melanie Baljko
 */
public class BinaryFromKB extends EncodingTreeFromManualSpecification {

	public BinaryFromKB(ButtonLayoutSpecification kb) {
		super(kb);
	}

	@Override
	public String getCreatingClass() {
		return this.getClass().getName();
	}

	/**
	 * Assumes KeyboardDisplayScheme has a single row, put all elements into a
	 * tree
	 */
	@Override
	public Node deriveRootOfEncoding(ButtonLayoutSpecification kb) {
		InternalNode root = new InternalNode();
		JPanel currRow = kb.getRows().get(0);
		Component[] x = currRow.getComponents();
		for (int i = 0; i < x.length; i++) {
			JIndirectSelectionButton tmpBut = ((JIndirectSelectionButton) x[i]);
			if (tmpBut.isEnabled()) {
				Node tmp = new LeafNode(tmpBut);
				root.addChild(tmp);
			}
		}
		return root;
	}

}
