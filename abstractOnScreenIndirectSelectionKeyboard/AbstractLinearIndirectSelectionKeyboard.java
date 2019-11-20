/*
 * Created on 26-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package abstractOnScreenIndirectSelectionKeyboard;

import treeDataStructure.Node;

import java.awt.*;
import javax.swing.JPanel;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;


import treeDataStructure.InternalNode;
import treeDataStructure.LeafNode;
import treeDataStructure.Node;

/**
 * This class implements a TES in which the encoding tree is flat (depth of tree
 * is 1 and all buttons are leaves).
 * 
 * @author Melanie Baljko
 */
public abstract class AbstractLinearIndirectSelectionKeyboard extends
		AbstractOnScreenIndirectSelectionKeyboard_KBLthenET {

	public AbstractLinearIndirectSelectionKeyboard() {
		super();
	}

	/**
	 * This method sets up the containment hierarchy.
	 * 
	 * PRE : the keyboard layout has been specified
	 * 
	 * The encoding tree is flat (depth of tree is 1 and all buttons are
	 * leaves). Leaves are added in order in which they are encountered,
	 * traversing left-to-right, from top row down to bottom row.
	 */
	@Override
	public Node constructEncodingFromKeyboardLayout(
			ButtonLayoutSpecification keyboardLayout) {
		InternalNode root = new InternalNode();
		for (JPanel currRow : keyboardLayout.getRows()) {
			Component[] x = currRow.getComponents();
			for (int i = 0; i < x.length; i++) {
				JIndirectSelectionButton tmpBut = ((JIndirectSelectionButton) x[i]);
				if (tmpBut.isEnabled()) {
					Node tmp = new LeafNode(tmpBut);
					root.addChild(tmp);
				}
			}
		}
		return root;
	}
}
