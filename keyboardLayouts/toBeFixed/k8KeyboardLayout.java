package keyboardLayouts.toBeFixed;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;




public class k8KeyboardLayout extends ButtonLayoutSpecification {
	
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

	public k8KeyboardLayout() {
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
		int[][] isEnabled = { { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
				{ 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
				{ 0, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 0 } };
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
	 * @see containmentHierarchyVariants.KeyboardLayout#getRows()
	 */
	public List<JPanel> getRows() {
		return onScreenButtonRows;
	}

	private JIndirectSelectionButton getButton(int row, int col) {

		JIndirectSelectionButton[][] buttons = {
				// \u02fd is unicode for the shelf symbol
				{ SourceSymbol.VK_T, SourceSymbol.VK_S, SourceSymbol.VK_F,
						SourceSymbol.VK_C, SourceSymbol.VK_N,
						SourceSymbol.VK_R },
				{ SourceSymbol.VK_COMMA, SourceSymbol.VK_PERIOD, SourceSymbol.VK_U,
						SourceSymbol.VK_M,
						SourceSymbol.VK_D, SourceSymbol.VK_PILCROW },
				{ SourceSymbol.VK_EMPTY.clone(), SourceSymbol.VK_QUESTIONMARK,
						SourceSymbol.VK_APOSTROPHE, SourceSymbol.VK_H,
						SourceSymbol.VK_O, SourceSymbol.VK_E },
				{ SourceSymbol.VK_SPACE_SYMBOL, SourceSymbol.VK_P,
						SourceSymbol.VK_G,
						SourceSymbol.VK_Y, SourceSymbol.VK_L,
						SourceSymbol.VK_I },
				{ SourceSymbol.VK_A, SourceSymbol.VK_X, SourceSymbol.VK_Z,
						SourceSymbol.VK_7, SourceSymbol.VK_V, SourceSymbol.VK_W },
				{ SourceSymbol.VK_B, SourceSymbol.VK_J, SourceSymbol.VK_DEL,
						SourceSymbol.VK_8, SourceSymbol.VK_K, SourceSymbol.VK_5 },
				{ SourceSymbol.VK_EMPTY.clone(), SourceSymbol.VK_9, SourceSymbol.VK_Q,
						SourceSymbol.VK_1, SourceSymbol.VK_3, SourceSymbol.VK_6 },
				{ SourceSymbol.VK_EMPTY.clone(), SourceSymbol.VK_EMPTY.clone(),
						SourceSymbol.VK_0, SourceSymbol.VK_2, SourceSymbol.VK_4,
						SourceSymbol.VK_EMPTY.clone() } };

		return buttons[row][col];
	}
}