/*
 * Created on 9-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package encodingTrees;

import java.awt.Component;

import javax.swing.JPanel;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import TreeDataStructure.InternalNode;
import TreeDataStructure.LeafNode;
import TreeDataStructure.Node;

/**
 * 
 * @author Melanie Baljko
 */
public class LinearFromKB extends EncodingTreeFromManualSpecification {

	public LinearFromKB(ButtonLayoutSpecification kb) {
		super(kb);
	}

	@Override
	public String getCreatingClass() {
		return this.getClass().getName();
	}

	@Override
	public Node deriveRootOfEncoding(ButtonLayoutSpecification kb) {

		InternalNode root = new InternalNode();
		for (JPanel currRow : kb.getRows()) {
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
