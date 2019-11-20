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



public class TestingKeyboardLayoutETAON extends ButtonLayoutSpecification {

	@Override
	public int setNumRows() {
		final int NUM_ROWS = 1;
		return NUM_ROWS;
	}

	@Override
	public int setNumCols() {
		final int NUM_COLS = 5;
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

	public TestingKeyboardLayoutETAON() {
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

	public int getButtonWidth(int row, int col) {
		int[][] tmp = { { 4, 4, 4, 4, 4 } };
		return tmp[row][col];
	}

	public boolean isButtonEnabled(int row, int col) {
		int[][] isEnabled = { { 1, 1, 1, 1, 1 } };
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
				{ SourceSymbol.VK_E, SourceSymbol.VK_T, SourceSymbol.VK_A,
						SourceSymbol.VK_O, SourceSymbol.VK_N } };
		return buttons[row][col];
	}
}