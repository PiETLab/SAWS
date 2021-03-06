package buttonLayouts;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;
import encodingTrees.TraversableEncodingTree;


/**
 * This class implements the keyboard layout shown in figure 4 of vankatagiri99,
 * intended for linear scanning (according to letter frequency)
 * 
 * @author mb
 * 
 */
public class SingleRowKB extends ButtonLayoutSpecification {

	private Font font = new Font("sanserif", Font.PLAIN, 12);

	@Override
	public int setNumRows() {
		final int NUM_ROWS = 1;
		return NUM_ROWS;
	}

	@Override
	public int setNumCols() {
		// final int NUM_COLS = 29;
		final int NUM_COLS = 28;
		return NUM_COLS;
	}

	@Override
	public int setButtonWidth() {
		//final int BUTTON_WIDTH = 28;
		final int BUTTON_WIDTH = 35;
		return BUTTON_WIDTH;
	}

	@Override
	public int setButtonHeight() {
		final int BUTTON_HEIGHT = 50;
		return BUTTON_HEIGHT;
	}

	List<JPanel> onScreenButtonRows;

	public SingleRowKB(TraversableEncodingTree root,
			SourceSymbolSet selectableKeyList) {
		// need to traverse leaves of encoding tree from left to right
		List<SourceSymbol> list = root.getLeaves();
		onScreenButtonRows = new Vector<JPanel>();
		JPanel currRow = new JPanel();
		currRow.setLayout(new BoxLayout(currRow, BoxLayout.LINE_AXIS));
		for (int i = 0; i < list.size(); i++) {
			JIndirectSelectionButton but = (JIndirectSelectionButton) list
					.get(i);

			// but.setBorderPainted(isButtonEnabled(i, j));

			but.setEnabled(true);// isButtonEnabled(i, j));
			// if (but.isEnabled())
			// but.setActionCommand(getButtonActionCommand(i, j));

			Dimension currButtonWidth = new Dimension(// getButtonWidth(1, 1)
					getButtonWidth(), getButtonHeight());
			Insets insets = new Insets(1, 1, 1, 1);
			but.setMargin(insets);

			but.setPreferredSize(currButtonWidth);
			but.setMaximumSize(currButtonWidth);
			but.setMinimumSize(currButtonWidth);
			but.setFont(font);
			currRow.add(but);
		}
		onScreenButtonRows.add(currRow);
	}

	public int getButtonWidth(int row, int col) {
		// 6 buttons across get 4 units each = 24 units in total
		int[][] tmp = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
		return tmp[row][col];
	}

	public boolean isButtonEnabled(int row, int col) {
		int[][] isEnabled = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
		return (isEnabled[row][col] == 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see containmentHierarchyVariants.KeyboardLayout#getAllButtons()
	 */
	public List<JIndirectSelectionButton> getAllEnabledButtons() {
		int top = 3;
		int bottom = 3;
		int left = 2;
		int right = 2;
		Insets ins = new Insets(top, left, bottom, right);

		List<JIndirectSelectionButton> allButtons = new Vector<JIndirectSelectionButton>();
		for (JPanel currRow : onScreenButtonRows) {
			Component[] x = currRow.getComponents();
			for (int i = 0; i < x.length; i++) {
				JIndirectSelectionButton tmpBut = ((JIndirectSelectionButton) x[i]);
				if (tmpBut.isEnabled()) {
					allButtons.add(tmpBut);
					tmpBut.setMargin(ins);
					// System.out.println(tmpBut.getMargin());
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
		JIndirectSelectionButton[][] buttons = { {
				JIndirectSelectionButton.VK_DEL,
				JIndirectSelectionButton.VK_SPACE_SYMBOL,
				JIndirectSelectionButton.VK_A, JIndirectSelectionButton.VK_B,
				JIndirectSelectionButton.VK_C, JIndirectSelectionButton.VK_D,
				JIndirectSelectionButton.VK_E, JIndirectSelectionButton.VK_F,
				JIndirectSelectionButton.VK_G, JIndirectSelectionButton.VK_H,
				JIndirectSelectionButton.VK_I, JIndirectSelectionButton.VK_J,
				JIndirectSelectionButton.VK_K, JIndirectSelectionButton.VK_L,
				JIndirectSelectionButton.VK_M, JIndirectSelectionButton.VK_N,
				JIndirectSelectionButton.VK_O, JIndirectSelectionButton.VK_P,
				JIndirectSelectionButton.VK_Q, JIndirectSelectionButton.VK_R,
				JIndirectSelectionButton.VK_S, JIndirectSelectionButton.VK_T,
				JIndirectSelectionButton.VK_U, JIndirectSelectionButton.VK_V,
				JIndirectSelectionButton.VK_W, JIndirectSelectionButton.VK_X,
				JIndirectSelectionButton.VK_Y, JIndirectSelectionButton.VK_Z } };
		// Selectable.VK_PILCROW } };
		// x4 punctuation
		// Selectable.VK_COMMA,
		// Selectable.VK_PERIOD, Selectable.VK_QUESTIONMARK,
		// Selectable.VK_APOSTROPHE,
		// x10 digits
		// Selectable.VK_0, Selectable.VK_1,
		// Selectable.VK_2, Selectable.VK_3, Selectable.VK_1,
		// Selectable.VK_5, Selectable.VK_6, Selectable.VK_7,
		// Selectable.VK_8, Selectable.VK_9 } };
		return buttons[row][col];
	}

}