package encodingTrees;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.MeaganCompositionSet;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;
import treeDataStructure.*;

/**
 * Unigram-based layout
 * 
 * This class implements the keyboard layout shown in figure 1 of vankatagiri99,
 * intended for row-column scanning (according to letter frequency)
 * 
 * @author mb
 * 
 */
public class AlphabeticET_MeaganSubset_2Level extends
		ManualEncodingTreeSpecification {

	final int NUM_ROWS = 6;
	final int NUM_COLS = 5;

	List<List<SourceSymbol>> subTrees;

	List<JPanel> onScreenButtonRows;

	public AlphabeticET_MeaganSubset_2Level() {
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
		// kludge - want to populate the source symbols with probability data -
		// borrow it from the following. a side effect is that the source
		// symbols will get their marginal probabilities set
		SourceSymbolSet sss = new MeaganCompositionSet();

		subTrees = new Vector<List<SourceSymbol>>();
		for (int i = 0; i < NUM_ROWS; i++) {
			List<SourceSymbol> currSubTree = new Vector<SourceSymbol>();
			for (int j = 0; j < NUM_COLS; j++) {
				SourceSymbol sourceSymbol = getSourceSymbol(i, j);
				if (!sourceSymbol.equals(JIndirectSelectionButton.VK_EMPTY)) {
					// double prob = sss.getAllSourceSymbols().
					currSubTree.add(sourceSymbol);
				}
			}
			subTrees.add(currSubTree);
		}
		return subTrees;
	}

	private SourceSymbol getSourceSymbol(int row, int col) {
		JIndirectSelectionButton tmp = JIndirectSelectionButton.VK_EMPTY;
		JIndirectSelectionButton[][] buttons = {
				{ JIndirectSelectionButton.VK_SPACE_SYMBOL_SIMPLE,
						JIndirectSelectionButton.VK_A,
						JIndirectSelectionButton.VK_B,
						JIndirectSelectionButton.VK_C,
						JIndirectSelectionButton.VK_D },
				{ JIndirectSelectionButton.VK_E, JIndirectSelectionButton.VK_F,
						JIndirectSelectionButton.VK_G,
						JIndirectSelectionButton.VK_H,
						JIndirectSelectionButton.VK_I },
				{ JIndirectSelectionButton.VK_J, JIndirectSelectionButton.VK_K,
						JIndirectSelectionButton.VK_L,
						JIndirectSelectionButton.VK_M,
						JIndirectSelectionButton.VK_N },
				{ JIndirectSelectionButton.VK_O, JIndirectSelectionButton.VK_P,
						JIndirectSelectionButton.VK_Q,
						JIndirectSelectionButton.VK_R,
						JIndirectSelectionButton.VK_S },
				{ JIndirectSelectionButton.VK_T, JIndirectSelectionButton.VK_U,
						JIndirectSelectionButton.VK_V,
						JIndirectSelectionButton.VK_W,
						JIndirectSelectionButton.VK_X },
				{ JIndirectSelectionButton.VK_Y, JIndirectSelectionButton.VK_Z,
						JIndirectSelectionButton.VK_PERIOD,
						JIndirectSelectionButton.VK_DEL_SINGLE_SYMBOL,
						JIndirectSelectionButton.VK_REVIEW_COMMAND } };
		return buttons[row][col];
	}

	public static String getDescriptor() {
		return "Alphabetic 2-level encoding tree designed for Meagan";
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