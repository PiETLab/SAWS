package encodingTrees;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;

import treeDataStructure.InternalNode;
import treeDataStructure.LeafNode;
import treeDataStructure.Node;

/**
 * Unigram-based layout
 * 
 * This class implements the keyboard layout shown in figure 1 of vankatagiri99,
 * intended for row-column scanning (according to letter frequency)
 * 
 * @author mb
 * 
 */
public class UnigramBasedET_2Level extends ManualEncodingTreeSpecification {

	final int NUM_ROWS = 8;
	final int NUM_COLS = 6;

	List<List<SourceSymbol>> subTrees;

	List<JPanel> onScreenButtonRows;

	public UnigramBasedET_2Level() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see containmentHierarchyVariants.KeyboardLayout#getAllButtons()
	 */
	public List<JIndirectSelectionButton> getAllEnabledButtons() {
		List<JIndirectSelectionButton> allButtons = new Vector<JIndirectSelectionButton>();
		for (JPanel currRow : onScreenButtonRows) {
			Component[] x = currRow.getComponents();
			for (int i = 0; i < x.length; i++) {
				JIndirectSelectionButton tmpBut = ((JIndirectSelectionButton) x[i]);
				if (tmpBut.isEnabled()) {
					allButtons.add(tmpBut);
				}
			}
		}
		return allButtons;
	}

	public List<List<SourceSymbol>> getSubTrees() {
		subTrees = new Vector<List<SourceSymbol>>();
		for (int i = 0; i < NUM_ROWS; i++) {
			List<SourceSymbol> currSubTree = new Vector<SourceSymbol>();
			for (int j = 0; j < NUM_COLS; j++) {
				SourceSymbol sourceSymbol = getSourceSymbol(i, j);
				if (!sourceSymbol.equals(JIndirectSelectionButton.VK_EMPTY)) {
					currSubTree.add(sourceSymbol);
				}
			}
			subTrees.add(currSubTree);
		}
		return subTrees;
	}

	private SourceSymbol getSourceSymbol(int row, int col) {
		JIndirectSelectionButton[][] buttons = {
				{ JIndirectSelectionButton.VK_SPACE_SYMBOL,
						JIndirectSelectionButton.VK_T,
						JIndirectSelectionButton.VK_I,
						JIndirectSelectionButton.VK_H,
						JIndirectSelectionButton.VK_F,
						JIndirectSelectionButton.VK_B },
				{ JIndirectSelectionButton.VK_E, JIndirectSelectionButton.VK_O,
						JIndirectSelectionButton.VK_R,
						JIndirectSelectionButton.VK_C,
						JIndirectSelectionButton.VK_P,
						JIndirectSelectionButton.VK_Z },
				{ JIndirectSelectionButton.VK_A, JIndirectSelectionButton.VK_S,
						JIndirectSelectionButton.VK_U,
						JIndirectSelectionButton.VK_G,
						JIndirectSelectionButton.VK_Q,
						JIndirectSelectionButton.VK_DEL_SINGLE_SYMBOL },
				{ JIndirectSelectionButton.VK_N, JIndirectSelectionButton.VK_D,
						JIndirectSelectionButton.VK_Y,
						JIndirectSelectionButton.VK_J,
						JIndirectSelectionButton.VK_COMMA,
						JIndirectSelectionButton.VK_0 },
				{ JIndirectSelectionButton.VK_L, JIndirectSelectionButton.VK_W,
						JIndirectSelectionButton.VK_X,
						JIndirectSelectionButton.VK_PERIOD,
						JIndirectSelectionButton.VK_1,
						JIndirectSelectionButton.VK_2 },
				{ JIndirectSelectionButton.VK_M, JIndirectSelectionButton.VK_K,
						JIndirectSelectionButton.VK_QUESTIONMARK,
						JIndirectSelectionButton.VK_3,
						JIndirectSelectionButton.VK_4,
						JIndirectSelectionButton.VK_5 },
				{ JIndirectSelectionButton.VK_V,
						JIndirectSelectionButton.VK_APOSTROPHE,
						JIndirectSelectionButton.VK_6,
						JIndirectSelectionButton.VK_7,
						JIndirectSelectionButton.VK_8,
						JIndirectSelectionButton.VK_9 },
				{ JIndirectSelectionButton.VK_PILCROW,
						JIndirectSelectionButton.VK_EMPTY,
						JIndirectSelectionButton.VK_EMPTY.clone(),
						JIndirectSelectionButton.VK_EMPTY.clone(),
						JIndirectSelectionButton.VK_EMPTY.clone(),
						JIndirectSelectionButton.VK_EMPTY.clone() } };
		return buttons[row][col];
	}

	public static String getDescriptor() {
		return "Unigram 2-level encoding tree as in Fig 1 of [Venkatagiri 1999]";
	}

	@Override
	public Node getRootOfEncodingTree() {
		InternalNode root = new InternalNode();
		for (List<SourceSymbol> currRow : this.getSubTrees()) {
			InternalNode rootChild = new InternalNode();
			for (SourceSymbol s : currRow) {
				Node tmp = new LeafNode(s);
				rootChild.addChild(tmp);
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
		return root;
	}

}