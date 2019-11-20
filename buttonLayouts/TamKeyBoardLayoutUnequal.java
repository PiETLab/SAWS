package buttonLayouts;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;


import encodingTrees.obsolete.TraversableEncodingTreeI;

import treeDataStructure.Node;

/**
 * This class implements a similar layout as shown in the paper [Baljko & Tam,
 * 2006]. The underneath containment hierarchy that is passed as an input, is
 * derived through the Huffman algorithm with unequal letter costs. The layout
 * of the keyboard is created manually, with the painfull process of trial and
 * error.
 * 
 * 
 * @author Fatima Ramay
 */

public class TamKeyBoardLayoutUnequal extends ButtonLayoutSpecification {

	SourceSymbolSet keyList;
	private static final int NUM_ROWS = 8;

	private static final int NUM_COLS = 6;

	private static final int BUTTON_WIDTH = 30;

	private static final int BUTTON_HEIGHT = 50;

	List<JPanel> onScreenButtonRows;

	public TamKeyBoardLayoutUnequal(TraversableEncodingTreeI chRoot,
			SourceSymbolSet selectableKeyList) {
		
		keyList = selectableKeyList;

		onScreenButtonRows = new Vector<JPanel>();
		JPanel currRow = new JPanel();

		for (int i = 0; i < getNumRows(); i++) {
			currRow = new JPanel();
			currRow.setLayout(new BoxLayout(currRow, BoxLayout.LINE_AXIS));
			for (int j = 0; j < getNumCols(); j++) {
				JIndirectSelectionButton but = getButton(chRoot, i, j);

				// but.setBorderPainted(isButtonEnabled(i, j));
				if (!but.equals(null)) {
					if (but.compareTo(SourceSymbol.VK_EMPTY.clone()) == 0) {
						but.setEnabled(false);
					} else {
						but.setEnabled(true);
					}
					// but.setEnabled(isButtonEnabled(i, j));
					// if (but.isEnabled())
					// but.setActionCommand(getButtonActionCommand(i, j));

					Dimension currButtonWidth = new Dimension(getButtonWidth(i,
							j)
							* getButtonWidth(), getButtonHeight());
					but.setPreferredSize(currButtonWidth);
					but.setMaximumSize(currButtonWidth);
					but.setMinimumSize(currButtonWidth);
					but.setFont(new Font("sanserif", Font.BOLD, 14));
					currRow.add(but);
				}
			}
			onScreenButtonRows.add(currRow);
		}
	}

	// the grid has 29 units across, the matrix says how many units each button
	// gets
	public int getButtonWidth(int row, int col) {
		// 6 buttons across get 4 units each
		int[][] tmp = { { 4, 4, 4, 4, 4, 4 }, { 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4 }, { 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4 }, { 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4 }, { 4, 4, 4, 4, 4, 4 } };
		return tmp[row][col];
	}

	public boolean isButtonEnabled(int row, int col) {
		int[][] isEnabled = { { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 0, 1, 1 }, { 0, 0, 0, 1, 1, 1 } };
		return (isEnabled[row][col] == 1);
	}

	public int getNumRows() {
		return NUM_ROWS;
	}

	public int getNumCols() {
		return NUM_COLS;
	}

	public int getButtonWidth() {
		return BUTTON_WIDTH;
	}

	public int getButtonHeight() {
		return BUTTON_HEIGHT;
	}

	@Override
	public int setNumRows() {
		final int NUM_ROWS = 8;
		return NUM_ROWS;
	}

	@Override
	public int setButtonWidth() {
		final int BUTTON_WIDTH = 30;
		return BUTTON_WIDTH;
	}

	@Override
	public int setButtonHeight() {
		final int BUTTON_HEIGHT = 50;
		return BUTTON_HEIGHT;
	}

	@Override
	public int setNumCols() {
		final int NUM_COLS = 6;
		return NUM_COLS;
	}

	// public int getNumUnits() {
	// return NUM_UNITS;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see keyboardVariants.KeyboardLayout#getAllButtons()
	 */
	/*
	 * public List<JVirtualKeyboardButton> getAllEnabledButtons() { List<JVirtualKeyboardButton>
	 * allButtons = new Vector<JVirtualKeyboardButton>(); for (JPanel currRow :
	 * onScreenButtonRows) { Component[] x = currRow.getComponents(); for (int i =
	 * 0; i < x.length; i++) { JVirtualKeyboardButton tmpBut =
	 * ((JVirtualKeyboardButton) x[i]); if (tmpBut.isEnabled()) {
	 * allButtons.add(tmpBut); } } } return allButtons; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see containmentHierarchyVariants.KeyboardLayout#getAllButtons()
	 */
	public List<JIndirectSelectionButton> getAllEnabledButtons() {
		List<JIndirectSelectionButton> allButtons = new Vector<JIndirectSelectionButton>();

		for (JIndirectSelectionButton but : keyList.getSourceSymbolsByRankOrder()) {
			allButtons.add(but);
		}

		return allButtons;
	}

	// public List<String> getAllActionCommands() {
	// List<JVirtualKeyboardButton> allButtons = getAllButtons();
	// List<String> allActionCommands = new Vector<String>();
	// for (JVirtualKeyboardButton but : allButtons) {
	// allActionCommands.add(but.getActionCommand());
	// }
	// return allActionCommands;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see keyboardVariants.KeyboardLayout#getRows()
	 */
	public List<JPanel> getRows() {
		return onScreenButtonRows;
	}

	private JIndirectSelectionButton getButton(TraversableEncodingTreeI root,
			int row, int col) {

		JIndirectSelectionButton button = getSubButton(row, col);

		if (button.compareTo(SourceSymbol.VK_EMPTY.clone()) == 0) {
			return SourceSymbol.VK_EMPTY.clone();
		}
		List<JIndirectSelectionButton> foundButton = new Vector<JIndirectSelectionButton>();

		// List <JVirtualKeyboardButton> actualButton =
		// findButton(root.getRoot(), button,foundButton);
		findButton(root.getRoot(), button, foundButton);
		if (!foundButton.isEmpty())
			return foundButton.get(0);
		else
			return null;
	}

	// private List<JVirtualKeyboardButton>
	// findButton(Node<JVirtualKeyboardButton> node, JVirtualKeyboardButton
	// button,List<JVirtualKeyboardButton> foundButton) {
	private void findButton(Node<JIndirectSelectionButton> node,
			JIndirectSelectionButton button,
			List<JIndirectSelectionButton> foundButton) {
		// List <Node<JVirtualKeyboardButton>> children =
		// parentNode.getChildren();

		// JPanel boxContainer = new JPanel();

		if (node.isLeaf()) {
			if ((node.getRepresentative()).compareTo(button) == 0) {
				foundButton.add(node.getRepresentative());
			}
			return;

			/*
			 * else { return null; }
			 */
		} else {

			List<Node<JIndirectSelectionButton>> children = node.getChildren();
			for (int i = 0; i < children.size(); i++) {
				findButton(children.get(i), button, foundButton);
				if (!foundButton.isEmpty()) {
					return;
				}

			}

		}
		return;
	}

	private JIndirectSelectionButton getSubButton(int row, int col) {

		JIndirectSelectionButton[][] buttons = {
				// \u02fd is unicode for the shelf symbol
				{ SourceSymbol.VK_G, SourceSymbol.VK_J, SourceSymbol.VK_4,
						SourceSymbol.VK_W, SourceSymbol.VK_T, SourceSymbol.VK_R },
				{ SourceSymbol.VK_EMPTY.clone(), SourceSymbol.VK_Q,
						SourceSymbol.VK_Z, SourceSymbol.VK_5, SourceSymbol.VK_A,
						SourceSymbol.VK_SPACE_SYMBOL },
				{ SourceSymbol.VK_Y, SourceSymbol.VK_COMMA, SourceSymbol.VK_6,
						SourceSymbol.VK_PERIOD, SourceSymbol.VK_N, SourceSymbol.VK_E },
				{ SourceSymbol.VK_QUESTIONMARK, SourceSymbol.VK_APOSTROPHE,
						SourceSymbol.VK_P, SourceSymbol.VK_B, SourceSymbol.VK_U,
						SourceSymbol.VK_S },
				{ SourceSymbol.VK_7, SourceSymbol.VK_PILCROW, SourceSymbol.VK_2,
						SourceSymbol.VK_K, SourceSymbol.VK_O, SourceSymbol.VK_D },
				{ SourceSymbol.VK_DEL, SourceSymbol.VK_8, SourceSymbol.VK_0,
						SourceSymbol.VK_X, SourceSymbol.VK_H, SourceSymbol.VK_I },
				{ SourceSymbol.VK_9, SourceSymbol.VK_1, SourceSymbol.VK_3,
						SourceSymbol.VK_C, SourceSymbol.VK_M, SourceSymbol.VK_L },
				{ SourceSymbol.VK_EMPTY.clone(), SourceSymbol.VK_EMPTY.clone(),
						SourceSymbol.VK_EMPTY.clone(), SourceSymbol.VK_V,
						SourceSymbol.VK_F, SourceSymbol.VK_EMPTY.clone() } };

		return buttons[row][col];
	}
}