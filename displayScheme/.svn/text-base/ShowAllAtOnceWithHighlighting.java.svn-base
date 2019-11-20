package displayScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;

import IndirectSelectionFacility.OnScreenKeyboardView;
import treeDataStructure.Node;
import treeDataStructure.SelectionGroup;
import encodingTrees.TraversableEncodingTree;

/**
 * Shows all SGs at once (i.e., all of the selectables) arranged in some sort of
 * grid patterns that resembles a keyboard and indicates in-focus SG using
 * highlighting (change of colour).
 * 
 * @author mb
 * 
 */
public class ShowAllAtOnceWithHighlighting extends
		DisplaySchemeForTraversableEncodingTreeStates {

	private static final Color DEFAULT_COLOUR = Color.WHITE;

	private final Color IN_FOKUS_INITIAL_COLOUR = Color.BLACK;

	// private final Color IN_FOKUS_COLOUR = Color.black;
	private final Color IN_FOKUS_COLOUR = Color.GREEN;

	private final Color SELECTED_COLOUR = Color.blue;

	private final Color SELECTED_COLOUR2 = Color.MAGENTA;

	private SelectionGroup wasPutIntoFocus;

	public ShowAllAtOnceWithHighlighting(TraversableEncodingTree encodingTree,
			OnScreenKeyboardView onScreenKeyboardRepresentation,
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		super(encodingTree, onScreenKeyboardRepresentation, paramModel);
		arrangeAndShowKeyboard();
	}

	@Override
	public void updateAppearance() {
		// arrangeAndShowKeyboard();
		// encodingTree.getRoot().getNodeSelectionGroup().changeBackgroundColour(
		// DEFAULT_COLOUR);
		if (wasPutIntoFocus != null) {
			wasPutIntoFocus.changeBackgroundColour(DEFAULT_COLOUR);
		}

		SelectionGroup sg = getEncodingTree().getCurrentSelectionGroup();
		sg.changeBackgroundColour(IN_FOKUS_COLOUR);
		wasPutIntoFocus = sg;

		if (IS_VERBOSE) {
			System.out.println("sg: " + sg.toString());
		}

		// sg.putInFokus();
		// getOnScreenKeyboardRepresentation().revalidate();

		// getOnScreenKeyboardRepresentation().removeAll();
		// System.out.println("adding from here");
		// getOnScreenKeyboardRepresentation().add(panel);
		// http://www.jguru.com/faq/view.jsp?EID=88716
		getOnScreenKeyboardRepresentation().invalidate();
		getOnScreenKeyboardRepresentation().validate();
		getOnScreenKeyboardRepresentation().repaint();

	}

	public void arrangeAndShowKeyboard() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		// panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		java.util.List<JPanel> rows = getOnScreenKeyboardRepresentation()
				.getKeyboard().getRows();

		// 2 pixel border around

		int availableHeight = (int) getOnScreenKeyboardRepresentation()
				.getSize().height - 4;

		int butHeight = (availableHeight) / rows.size();
		// System.out.println("arrangeAndShowKeyboard: but height :" +
		// butHeight);
		for (JPanel theRow : rows) {
			JPanel theRowContainer = new JPanel();
			theRowContainer.setLayout(new BoxLayout(theRowContainer,
					BoxLayout.LINE_AXIS));
			theRowContainer.setAlignmentX(Component.LEFT_ALIGNMENT);

			// theRowContainer.setBackground(DEFAULT_COLOUR);

			Component[] theButtons = theRow.getComponents();
			// 2 pixel border around

			int butWidth;
			butWidth = ((int) getOnScreenKeyboardRepresentation().getSize()
					.getWidth() - (theButtons.length * 6))
					/ theButtons.length;
			butWidth = ((int) getOnScreenKeyboardRepresentation().getSize()
					.getWidth())
					/ theButtons.length;
			// int theFontSize = super.getMaxPointSize(new Dimension(butWidth,
			// butHeight), 1.0);
			float theFontSize = 20f;
			Font theFont = super.getFont().deriveFont(theFontSize);
			if (IS_VERBOSE) {
				System.out.println("arrangeAndShowKeyboard: " + theFont);
				System.out.println("Button width: " + butWidth);
				butWidth = super.getButtonWidth();
				System.out.println("Button width: " + butWidth);
			}

			// System.out
			// .println("arrangeAndShowKeyboard: but width :" + butWidth);

			for (int butIndex = 0; butIndex < theButtons.length; butIndex++) {
				JIndirectSelectionButton but2 = (JIndirectSelectionButton) theButtons[butIndex];
				Dimension dim = new Dimension(butWidth, butHeight);

				but2.setFont(theFont);
				but2.setSize(dim);
				but2.setPreferredSize(dim);
				but2.setMaximumSize(dim);
				but2.setMinimumSize(dim);
				but2.setAlignmentX(Component.LEFT_ALIGNMENT);
				theRowContainer.add(but2);
				theRowContainer.add(Box.createHorizontalGlue());
				// panel.add(but2);
			}
			panel.add(theRowContainer);
			// panel.add(theRow);

		}
		super.getEncodingTree().getRoot().getSelectionGroup()
				.changeBackgroundColour(DEFAULT_COLOUR);

		// Iterator<JPanel> it = rows.iterator();
		//
		// while (it.hasNext()) {
		// JPanel row = it.next();
		// // row.setSize((int) Toolkit.getDefaultToolkit().getScreenSize()
		// // .getWidth(), 400);
		//
		// // getContentPane().add(row);
		// panel.add(row);
		// // System.out.println("buttons in row: " + row.countComponents());
		// }
		getOnScreenKeyboardRepresentation().removeAll();
		// System.out.println("adding from here");
		getOnScreenKeyboardRepresentation().add(panel);
		// http://www.jguru.com/faq/view.jsp?EID=88716
		getOnScreenKeyboardRepresentation().invalidate();
		getOnScreenKeyboardRepresentation().validate();
		getOnScreenKeyboardRepresentation().repaint();
	}

	@Override
	public void highlightCurrentSelectionGroupAsSelected() {
		SelectionGroup sg = getEncodingTree().getCurrentSelectionGroup();
		if (sg.isTrivial()) {
			sg.changeBackgroundColour(SELECTED_COLOUR);
			// System.out.println("blue.");
		} else {
			sg.changeBackgroundColour(SELECTED_COLOUR2);
		}
		getOnScreenKeyboardRepresentation().revalidate();
	}

	@Override
	public Font getDerivedFontForFokus(Font f) {
		return getFont();
	}

	public void resizeOptimizedComponents() {

		int widthPerElement = 50;
		setButtonWidth(widthPerElement);

		// THE HEIGHT

		int availableHeight = (int) getOnScreenKeyboardRepresentation()
				.getSize().height;
		int heightPerElement = (int) Math.floor(availableHeight);
		// heightPerElement -= 2;
		if (IS_VERBOSE) {
			System.out.println("Width and Height of buttons: "
					+ widthPerElement + "\t" + heightPerElement);
		}
		setButtonHeight(heightPerElement);

		setButtonDimension(new Dimension(getButtonWidth(), getButtonHeight()));
		setTheFont(getFont().deriveFont(20f));
		if (IS_VERBOSE) {
			System.out.println("Width and Height of buttons with font: "
					+ widthPerElement + "\t" + heightPerElement);
		}
		// setTheFont(getOptimalFont());
	}

}
