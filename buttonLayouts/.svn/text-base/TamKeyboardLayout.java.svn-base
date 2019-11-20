package buttonLayouts;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbolSet;
import encodingTrees.TraversableEncodingTree;


/**
 * This is a keyboard that was manually designed to match the binary encoding
 * tree of "X" selectable set with YYY probability distribution (need to
 * re-identify "X", YYY - can't recall right now)
 * 
 * 
 * @author mb
 * 
 */
public class TamKeyboardLayout extends ButtonLayoutSpecification {

	List<JPanel> onScreenButtonRows;

	public TamKeyboardLayout(TraversableEncodingTree root,
			SourceSymbolSet selectableKeyList) {
		onScreenButtonRows = new Vector<JPanel>();
		JPanel currRow = new JPanel();
		for (int i = 0; i < getNumRows(); i++) {
			currRow = new JPanel();
			currRow.setLayout(new BoxLayout(currRow, BoxLayout.LINE_AXIS));
			for (int j = 0; j < getNumCols(); j++) {
				JIndirectSelectionButton but = getButton(i, j);

				// but.setBorderPainted(isButtonEnabled(i, j));

				but.setEnabled(isButtonEnabled(i, j));
				// if (but.isEnabled())
				// but.setActionCommand(getButtonActionCommand(i, j));

				Dimension currButtonWidth = new Dimension(getButtonWidth(i, j)
						* getButtonWidth(), getButtonHeight());
				but.setPreferredSize(currButtonWidth);
				but.setMaximumSize(currButtonWidth);
				but.setMinimumSize(currButtonWidth);
				but.setFont(new Font("sanserif", Font.BOLD, 14));
				currRow.add(but);
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
		int[][] isEnabled = { { 1, 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
				{ 0, 1, 1, 1, 1, 1 }, { 0, 1, 1, 0, 1, 0 } };
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
				// \u02fd is unicode for the shelf symbol
				{ JIndirectSelectionButton.VK_S, JIndirectSelectionButton.VK_F, JIndirectSelectionButton.VK_C,
						JIndirectSelectionButton.VK_T, JIndirectSelectionButton.VK_N, JIndirectSelectionButton.VK_R },
				{ JIndirectSelectionButton.VK_EMPTY.clone(), JIndirectSelectionButton.VK_H,
						JIndirectSelectionButton.VK_PERIOD, JIndirectSelectionButton.VK_QUESTIONMARK,
						JIndirectSelectionButton.VK_D, JIndirectSelectionButton.VK_PILCROW },
				{ JIndirectSelectionButton.VK_APOSTROPHE, JIndirectSelectionButton.VK_COMMA,
						JIndirectSelectionButton.VK_U, JIndirectSelectionButton.VK_M, JIndirectSelectionButton.VK_O,
						JIndirectSelectionButton.VK_E },
				{ JIndirectSelectionButton.VK_SPACE_SYMBOL, JIndirectSelectionButton.VK_7, JIndirectSelectionButton.VK_G,
						JIndirectSelectionButton.VK_P, JIndirectSelectionButton.VK_I, JIndirectSelectionButton.VK_L },
				{ JIndirectSelectionButton.VK_A, JIndirectSelectionButton.VK_8, JIndirectSelectionButton.VK_Z,
						JIndirectSelectionButton.VK_Y, JIndirectSelectionButton.VK_W, JIndirectSelectionButton.VK_V },
				{ JIndirectSelectionButton.VK_B, JIndirectSelectionButton.VK_9, JIndirectSelectionButton.VK_DEL,
						JIndirectSelectionButton.VK_X, JIndirectSelectionButton.VK_K, JIndirectSelectionButton.VK_5 },
				{ JIndirectSelectionButton.VK_EMPTY.clone(), JIndirectSelectionButton.VK_Q,
						JIndirectSelectionButton.VK_1, JIndirectSelectionButton.VK_J, JIndirectSelectionButton.VK_3,
						JIndirectSelectionButton.VK_6 },
				{ JIndirectSelectionButton.VK_EMPTY.clone(), JIndirectSelectionButton.VK_0,
						JIndirectSelectionButton.VK_2, JIndirectSelectionButton.VK_EMPTY.clone(),
						JIndirectSelectionButton.VK_4, JIndirectSelectionButton.VK_EMPTY.clone() } };

		return buttons[row][col];
	}

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
}