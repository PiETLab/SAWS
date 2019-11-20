/*
 * Created on 26-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package abstractOnScreenIndirectSelectionKeyboard;

import java.awt.*;

import javax.swing.JPanel;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;


import treeDataStructure.InternalNode;
import treeDataStructure.LeafNode;
import treeDataStructure.Node;

/**
 * This class implements a TES in which the encoding tree is is taken from the
 * keyboard layout (row-column format). Thus, the keyboard layout must be
 * specified prior to invoking the constructEncodingFromKeyboardLayout() method.
 * 
 * The first level consists of the rows of the keyboard and the second level
 * corresponds to the buttons within a given row.
 * 
 * @author Melanie Baljko
 */
public abstract class AbstractRowColIndirectSelectionKeyboard extends
		AbstractOnScreenIndirectSelectionKeyboard_KBLthenET {

	public AbstractRowColIndirectSelectionKeyboard() {
		super();
	}

	/**
	 * This method sets up the containment hierarchy.
	 * 
	 * PRE : the keyboard layout has been specified
	 * 
	 * Use the rows of the keyboard layout to derive the first level. Use the
	 * buttons within each row to derive the second level of the CH.
	 */
	@Override
	public Node constructEncodingFromKeyboardLayout(
			ButtonLayoutSpecification keyboardLayout) {
		InternalNode root = new InternalNode();

		for (JPanel currRow : keyboardLayout.getRows()) {
			InternalNode rootChild = new InternalNode();
			Component[] x = currRow.getComponents();
			for (int i = 0; i < x.length; i++) {
				JIndirectSelectionButton tmpBut = ((JIndirectSelectionButton) x[i]);
				if (tmpBut.isEnabled()) {
					Node tmp = new LeafNode(tmpBut);
					rootChild.addChild(tmp);
				}
			}
			// test whether this row contains just one button
			// if so, collapse the level and make the button directly a child of
			// the root
			if (rootChild.getChildren().size() == 1) {
				root.addChild(new LeafNode(rootChild.getFirstChild()
						.getSelectionGroup().getFirst()));
			} else {
				root.addChild(rootChild);
			}

		}

		// System.out.println(this.getName()
		// + ": Finished constructing containment hierarchy");
		return root;
	}

}