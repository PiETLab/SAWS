package buttonLayouts;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import customGUIComponentsISF.JIndirectSelectionButton;

import encodingTrees.TraversableEncodingTree;

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
public class RowColumnSizedForTwoLevelEncodingTree extends
		ButtonLayoutSpecification {

	@Override
	public int setNumRows() {
		final int NUM_ROWS = 8;
		return NUM_ROWS;
	}

	@Override
	public int setNumCols() {
		final int NUM_COLS = 6;
		return NUM_COLS;
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

	List<JPanel> onScreenButtonRows;

	public RowColumnSizedForTwoLevelEncodingTree(
			TraversableEncodingTree encodingTree,
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		super(encodingTree, paramModel);
		onScreenButtonRows = new Vector<JPanel>();
		JPanel currRow = new JPanel();
		int rowIndex = 0;
		for (Node subtree : encodingTree.getRoot().getChildren()) {

			currRow = new JPanel();
			currRow.setLayout(new BoxLayout(currRow, BoxLayout.LINE_AXIS));
			if (subtree.isLeaf()) {
				JIndirectSelectionButton but = (JIndirectSelectionButton) subtree
						.getSelectionGroup().getFirst();
				currRow.add(but);
			} else {
				int colIndex = 0;
				for (Node leaf : subtree.getChildren()) {
					JIndirectSelectionButton but = (JIndirectSelectionButton) leaf
							.getSelectionGroup().getFirst();

					// but.setBorderPainted(isButtonEnabled(i, j));
					// but.setEnabled(isButtonEnabled(i, j));
					// if (but.isEnabled())
					// but.setActionCommand(getButtonActionCommand(i, j));

					// Dimension currButtonWidth = new Dimension(getButtonWidth(
					// rowIndex, colIndex)
					// * getButtonWidth(), getButtonHeight());
					// but.setPreferredSize(currButtonWidth);
					// but.setMaximumSize(currButtonWidth);
					// but.setMinimumSize(currButtonWidth);
					// but.setFont(new Font("sanserif", Font.BOLD, 14));
					currRow.add(but);
					colIndex++;
				}
			}
			onScreenButtonRows.add(currRow);
			rowIndex++;
		}
		System.out.println(this.getClass().getName() + ": Done.");

	}

	public int getButtonWidth(int row, int col) {
		// 6 buttons across get 4 units each = 24 units in total
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
				{ 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0 } };
		return (isEnabled[row][col] == 1);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see containmentHierarchyVariants.KeyboardLayout#getRows()
	 */
	public List<JPanel> getRows() {
		return onScreenButtonRows;
	}

	private JIndirectSelectionButton getButton(int row, int col) {
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
						JIndirectSelectionButton.VK_DEL },
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
		return "Unigram layout as in Fig 1 of [Venkatagiri 1999]";
	}

}