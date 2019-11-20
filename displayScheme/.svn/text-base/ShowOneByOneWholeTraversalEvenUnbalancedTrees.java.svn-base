package displayScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import IndirectSelectionFacility.OnScreenKeyboardView;
import IndirectSelectionFacilityCommands.AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand;
import treeDataStructure.Node;
import treeDataStructure.SelectionGroup;
import encodingTrees.TraversableEncodingTree;

/**
 * 
 * Shows each selection group (SG) one by one within the current focus cycle.
 * Maintain this format for the entire traversal of the tree.
 * 
 * Each SG that is shown is shown in a single row. The height of the buttons are
 * the height of proportion of the display that has been allocated to the
 * "keyboard". The button widths remain the same from one SG to the next SG. The
 * button width is therefore determined by the size of the largest selection
 * group (excluding the root's SG).
 * 
 * The font size is automatically determined as the largest possible font size,
 * given amount of real estate allocated to a button in the worst-case scenario
 * (e.g., when the largest number of buttons are shown on the screen).
 * 
 * 
 * @author mb
 * 
 */
public class ShowOneByOneWholeTraversalEvenUnbalancedTrees extends
		DisplaySchemeForTraversableEncodingTreeStates {

	private final Color SELECTED_LEAF = Color.blue;

	private final Color SELECTED_INTERNAL = Color.MAGENTA;

	private double FOKUS_FONT_SIZE_BOOST = 1.0; // make the font of the
	// in-focus nodes 30% bigger
	// than the out of focus
	// state

	// private Font DEFAULT_FONT = new Font("sanserif", Font.PLAIN, 300);
	// private Font DEFAULT_FONT = new Font("serif", Font.PLAIN, 300);

	int INSET_WIDTH = 3;
	private Insets insets = new Insets(INSET_WIDTH, INSET_WIDTH + 1,
			INSET_WIDTH, INSET_WIDTH);

	private SelectionGroup sgThatIsShownInFocusOnView;

	private int groupSizeThatDisplayIsFittedTo = 0;

	public ShowOneByOneWholeTraversalEvenUnbalancedTrees(
			TraversableEncodingTree encodingTree,
			OnScreenKeyboardView onScreenKeyboardRepresentation,
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		super(encodingTree, onScreenKeyboardRepresentation, paramModel);
		// buttonFont = new Font("sansserif", Font.PLAIN, 12);
		arrangeAndShowKeyboard();
		System.out.println(this.getClass().getName() + ": Done.");

		// buttonFont = new Font("sanserif", Font.PLAIN, 30);
	}

	private void arrangeAndShowKeyboard() {

	}

	// protected int getButtonWidth() {
	//
	// int widthPerElement = 0;
	// // we show the first- and subsequent-generation selection groups one
	// // at
	// // a
	// // time
	// List<Node> childrenOfRoot = getEncodingTree().getRoot().getChildren();
	// List<Integer> theSGSizes = new Vector<Integer>();
	// for (Node n : childrenOfRoot) {
	// theSGSizes.add(n.getSelectionGroupSize());
	// }
	// Collections.sort(theSGSizes);
	// // get the SECOND largest SG
	// System.out.println(theSGSizes);
	// int maxNumberHorizontalElements = theSGSizes.get(theSGSizes.size() - 2);
	//
	// int availableWidth = (int) getOnScreenKeyboardRepresentation()
	// .getSize().width;
	//
	// widthPerElement = (int) Math.floor(availableWidth)
	// / maxNumberHorizontalElements;
	// // widthPerElement = widthPerElement - 1 *
	// // maxNumberHorizontalElements;
	// widthPerElement -= 2;
	//
	// if (IS_VERBOSE) {
	// System.out.println("unbal1. Width of: " + widthPerElement
	// + " for each of the # elements: "
	// + maxNumberHorizontalElements + " total width: "
	// + availableWidth);
	// }
	//
	// return widthPerElement;
	//
	// }

	protected int getButtonWidth(int availableWidth,
			int maxNumberHorizontalElements) {

		int widthPerElement = 0;

		widthPerElement = (int) Math.floor(availableWidth)
				/ maxNumberHorizontalElements;
		// widthPerElement = widthPerElement - 1 *
		// maxNumberHorizontalElements;
		widthPerElement -= 2;

		if (IS_VERBOSE) {
			// System.out.println("unbal2. Width of: " + widthPerElement
			// + " for each of the # elements: "
			// + maxNumberHorizontalElements + " total width: "
			// + availableWidth);
		}
		return widthPerElement;

	}

	@Override
	public void updateAppearance() {
		// System.out.println(this.getClass().getName() +
		// ": any sg is in fokus: "
		// + sgThatIsShownInFocusOnView);

		if (sgThatIsShownInFocusOnView != null) {
			resetToDefault(sgThatIsShownInFocusOnView);
			// System.out.println("sgThatIsShownInFocusOnView: "
			// + sgThatIsShownInFocusOnView);
			// sgThatIsShownInFocusOnView.changeBackgroundColour(DEFAULT_COLOUR);
		}
		SelectionGroup sg = getEncodingTree().getCurrentSelectionGroup();
		// resetToDefault(sg);
		sgThatIsShownInFocusOnView = sg;

		if (IS_VERBOSE) {
			// if (true) {
			String label = "nontrivial";
			if (sgThatIsShownInFocusOnView.isTrivial())
				label = "trivial";
			// System.out.println(this.getClass().getName()
			// + " "
			// + label
			// + " sg just put in fokus: "
			// + sgThatIsShownInFocusOnView
			// + " can fit on display? "
			// + canFitOnDisplay(sg)
			// + " and parent? "
			// + canFitOnDisplay(getEncodingTree()
			// .getCurrentSelectionGroupParent()));
		}

		// versionLeaveInPositionAndHighlight(sg, getEncodingTree()
		// .getCurrentSelectionGroupParent());
		// if sg is trivial, show whole focus cycle instead of just sg
		// if (sg.isTrivial()) {
		// // showTrivialNodeInFocus(sg);
		// versionLeaveInPositionAndHighlight(sg, getEncodingTree()
		// .getCurrentSelectionGroupParent());
		// // showTrivialNodeInFocus(sg);
		// } else {
		if (canFitOnDisplay(sg)
				&& canFitOnDisplay(getEncodingTree()
						.getCurrentSelectionGroupParent())) {
			versionLeaveInPositionAndHighlight(sg);
		} else if (canFitOnDisplay(sg)
				&& !canFitOnDisplay(getEncodingTree()
						.getCurrentSelectionGroupParent())) {
			showNonTrivialNodeInFokus(sg);
		} else {
			showNonTrivialNodeInFokus(sg);
		}
		// }
	}

	private void versionLeaveInPositionAndHighlight(SelectionGroup sgChild,
			SelectionGroup sg) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBackground(super.paramModel.getBackgroundColour());
		panel.setForeground(super.paramModel.getTextColour());

		Dimension buttonDimension;
		int buttonWidth;
		// panel.setBackground(BACKGROUND_COLOUR);

		// panel.add(new JLabel("x"));

		// SelectionGroup sg = encodingTree.getCurrentSelectionGroup();
		// System.out.print("Nontrivial group in focus: ");

		int sizeSG = sg.extractMembers().size();
		for (SourceSymbol x : sg.extractMembers()) {
			// SourceSymbol x = sg.extractMembers().get(0);
			// if (panel.getComponentCount() != 0) {
			// panel.add(Box.createRigidArea(new Dimension(0, 0)));
			// }
			// System.out.println("Source Symbol to be shown: " + x);
			// JIndirectSelectionButton but = (JIndirectSelectionButton) x;
			JButton but = (JButton) x;
			massageButtonFormattingTrivial(but, x.getTextLabel());

			// but.setMargin(insets);
			// but.setFont(buttonFont);
			// but.setText(x.getTextLabel());
			// // but.setBackground(BACKGROUND_COLOUR);
			// but.setPreferredSize(getButtonDimension());
			// but.setMaximumSize(getButtonDimension());
			// but.setMinimumSize(getButtonDimension());
			// but.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.add(but);
			// panel.add(new JLabel("HI!"));
		}

		for (SourceSymbol x : sgChild.extractMembers()) {
			JIndirectSelectionButton but = (JIndirectSelectionButton) x;
			// but.setFont(getDerivedFontForFokus(buttonFont,
			// FOKUS_FONT_SIZE_BOOST));
			but.setBackground(paramModel.getTextColour());
			but.setForeground(paramModel.getBackgroundColour());
		}

		getOnScreenKeyboardRepresentation().removeAll();
		// System.out.println("adding from here");
		getOnScreenKeyboardRepresentation().add(panel);
		// http://www.jguru.com/faq/view.jsp?EID=88716
		getOnScreenKeyboardRepresentation().invalidate();
		getOnScreenKeyboardRepresentation().validate();
		getOnScreenKeyboardRepresentation().repaint();

	}

	private boolean canFitOnDisplay(SelectionGroup sg) {
		if (IS_VERBOSE)
			System.out.println("can fit on display?"
					+ sg.extractMembers().size() + "\t"
					+ groupSizeThatDisplayIsFittedTo);
		return sg.extractMembers().size() <= groupSizeThatDisplayIsFittedTo;
	}

	private void resetToDefault(SelectionGroup sg) {
		// sg.changeBackgroundColour(DEFAULT_COLOUR);

		for (SourceSymbol x : sg.extractMembers()) {
			JButton but = (JButton) x;
			but.setFont(super.getFont());
			but.setOpaque(true);
			but.setBackground(paramModel.getBackgroundColour());
			// but.setBackground(Color.RED);
			but.setForeground(paramModel.getTextColour());
			but.setHorizontalAlignment(paramModel.getHorizontalAlignment());
			but.setVerticalAlignment(paramModel.getVerticalAlignment());
			// but.setText("x");
			// System.out.println("reset font on button: " +
			// x.toStringOneChar());
		}
		// onScreenKeyboardRepresentation.revalidate();
	}

	/**
	 * Gets the font, given as a derivation of the passed font
	 * 
	 * @param f
	 * @return
	 */
	public Font getDerivedFontForFokus(Font f) {
		return getDerivedFontForFokus(f, FOKUS_FONT_SIZE_BOOST);
	}

	/**
	 * If a non-trivial node is in focus, then show only that node in the
	 * display
	 * 
	 * @param sg
	 */
	private void showNonTrivialNodeInFokus(SelectionGroup sg) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBackground(super.paramModel.getBackgroundColour());
		panel.setForeground(super.paramModel.getTextColour());

		// panel.setBackground(BACKGROUND_COLOUR);

		// panel.add(new JLabel("x"));

		// SelectionGroup sg = encodingTree.getCurrentSelectionGroup();
		// System.out.print("Nontrivial group in focus: ");

		int sizeSG = sg.extractMembers().size();
		for (SourceSymbol x : sg.extractMembers()) {
			// SourceSymbol x = sg.extractMembers().get(0);
			// if (panel.getComponentCount() != 0) {
			// panel.add(Box.createRigidArea(new Dimension(0, 0)));
			// }
			// System.out.println("Source Symbol to be shown: " + x);
			// JIndirectSelectionButton but = (JIndirectSelectionButton) x;
			JButton but = (JButton) x;
			massageButtonFormattingNonTrivial(but, x.getTextLabel(), sizeSG);

			// but.setMargin(insets);
			// but.setFont(buttonFont);
			// but.setText(x.getTextLabel());
			// // but.setBackground(BACKGROUND_COLOUR);
			// but.setPreferredSize(getButtonDimension());
			// but.setMaximumSize(getButtonDimension());
			// but.setMinimumSize(getButtonDimension());
			// but.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.add(but);
			// panel.add(new JLabel("HI!"));
		}
		// System.out.println();

		getOnScreenKeyboardRepresentation().removeAll();
		// System.out.println("adding from here");
		getOnScreenKeyboardRepresentation().add(panel);
		// http://www.jguru.com/faq/view.jsp?EID=88716
		getOnScreenKeyboardRepresentation().invalidate();
		getOnScreenKeyboardRepresentation().validate();
		getOnScreenKeyboardRepresentation().repaint();
		// getOnScreenKeyboardRepresentation().revalidate();
	}

	/**
	 * Leave display as-is, but change font of trivial node that is in focus
	 * 
	 * @param sg
	 */
	private void showTrivialNodeInFocus(SelectionGroup sg) {
		versionLeaveInPositionAndHighlight(sg);
	}

	private void versionClearDisplayAndShowSGOnly(SelectionGroup sg) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBackground(super.paramModel.getBackgroundColour());
		panel.setForeground(super.paramModel.getTextColour());

		Dimension buttonDimension;
		int buttonWidth;
		// panel.setBackground(BACKGROUND_COLOUR);

		// panel.add(new JLabel("x"));

		// SelectionGroup sg = encodingTree.getCurrentSelectionGroup();
		// System.out.print("Nontrivial group in focus: ");

		int sizeSG = sg.extractMembers().size();
		for (SourceSymbol x : sg.extractMembers()) {
			// SourceSymbol x = sg.extractMembers().get(0);
			// if (panel.getComponentCount() != 0) {
			// panel.add(Box.createRigidArea(new Dimension(0, 0)));
			// }
			// System.out.println("Source Symbol to be shown: " + x);
			// JIndirectSelectionButton but = (JIndirectSelectionButton) x;
			JButton but = (JButton) x;
			massageButtonFormattingTrivial(but, x.getTextLabel());

			// but.setMargin(insets);
			// but.setFont(buttonFont);
			// but.setText(x.getTextLabel());
			// // but.setBackground(BACKGROUND_COLOUR);
			// but.setPreferredSize(getButtonDimension());
			// but.setMaximumSize(getButtonDimension());
			// but.setMinimumSize(getButtonDimension());
			// but.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.add(but);
			// panel.add(new JLabel("HI!"));
		}
		// System.out.println();

		getOnScreenKeyboardRepresentation().removeAll();
		// System.out.println("adding from here");
		getOnScreenKeyboardRepresentation().add(panel);
		// http://www.jguru.com/faq/view.jsp?EID=88716
		getOnScreenKeyboardRepresentation().invalidate();
		getOnScreenKeyboardRepresentation().validate();
		getOnScreenKeyboardRepresentation().repaint();

		// getOnScreenKeyboardRepresentation().revalidate();
		// Image img = new VolatileImage();
		// Graphics2D g = (Graphics2D) but.getGraphics();
		// Map<TextAttribute, Object> fontAttributes = new
		// HashMap<TextAttribute, Object>();
		// fontAttributes.put(TextAttribute.UNDERLINE,
		// TextAttribute.UNDERLINE_ON);
		// TextLayout tl = new TextLayout("X", fontAttributes, g
		// .getFontRenderContext());
		// Dimension d = but.getSize();
		// tl.draw(g, d.width, d.height);
		// but.validate();
	}

	private void versionLeaveInPositionAndHighlight(SelectionGroup sg) {
		for (SourceSymbol x : sg.extractMembers()) {
			JIndirectSelectionButton but = (JIndirectSelectionButton) x;
			// but.setFont(getDerivedFontForFokus(buttonFont,
			// FOKUS_FONT_SIZE_BOOST));
			but.setBackground(paramModel.getTextColour());
			but.setForeground(paramModel.getBackgroundColour());
			if (IS_VERBOSE) {

			}
		}
		// but.setBackground(Color.RED);
		// but.setForeground(Color.GREEN);

	}

	/**
	 * 
	 * EFFECT SOLELY THRU SIDE-EFFECTS
	 * 
	 * @param but
	 * @param possiblyHTMLlabel
	 * @param sizeSG
	 */
	private void massageButtonFormattingNonTrivial(JButton but,
			String possiblyHTMLlabel, int sizeSG) {
		but.setMargin(insets);
		but.setFont(this.getFont());

		but.setText(possiblyHTMLlabel);
		but.setBackground(super.paramModel.getBackgroundColour());

		but.setForeground(super.paramModel.getTextColour());

		// System.out.println("button label: " + but.getTextLabel());
		// but.setFont(getDerivedFontForFokus(buttonFont));
		setButtonWidth(getButtonWidth((int) getOnScreenKeyboardRepresentation()
				.getSize().width, sizeSG));
		// System.out.println("button dimension, size: " + getButtonDimension()
		// + "\t" + sizeSG);

		but.setPreferredSize(getButtonDimension());
		but.setMaximumSize(getButtonDimension());
		but.setMinimumSize(getButtonDimension());
		but.setVerticalAlignment(SwingConstants.TOP);
		but.setHorizontalAlignment(paramModel.getHorizontalAlignment());
	}

	// public void toggleUpperLowerCase(String string) {
	// // if (isLowerCase)
	// if (this.getActionCommand().toLowerCase().equals(
	// this.getActionCommand())) {
	// setActionCommand(getActionCommand().toUpperCase());
	// AppendAndConditionallyReinitializeCommand.shouldConvertToLowerCase =
	// false;
	// // this.getVOCACommand().setUpperCase();
	// } else {
	// setActionCommand(getActionCommand().toLowerCase());
	// AppendAndConditionallyReinitializeCommand.shouldConvertToLowerCase =
	// true;
	// }
	// }

	/**
	 * 
	 * EFFECT SOLELY THRU SIDE-EFFECTS
	 * 
	 * @param but
	 * @param possiblyHTMLlabel
	 */
	private void massageButtonFormattingTrivial(JButton but,
			String possiblyHTMLlabel) {
		but.setMargin(insets);
		but.setFont(getFontForLeaves());
		but.setText(possiblyHTMLlabel);
		but.setBackground(super.paramModel.getBackgroundColour());
		but.setForeground(super.paramModel.getTextColour());

		// System.out.println("button label: " + but.getTextLabel());
		// but.setFont(getDerivedFontForFokus(buttonFont));
		but.setPreferredSize(getButtonDimension());
		but.setMaximumSize(getButtonDimension());
		but.setMinimumSize(getButtonDimension());
		but.setVerticalAlignment(SwingConstants.TOP);
		but.setHorizontalAlignment(paramModel.getHorizontalAlignment());
		// but.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public Font getFontForLeaves() {
		 //return this.getFont().deriveFont(60f);
		return this.getFont();
	}

	@Override
	public void highlightCurrentSelectionGroupAsSelected() {
		if (false) {
			SelectionGroup sg = getEncodingTree().getCurrentSelectionGroup();
			if (sg.isTrivial()) {
				sg.changeBackgroundColour(SELECTED_LEAF);
			} else {
				sg.changeBackgroundColour(SELECTED_INTERNAL);
			}
			getOnScreenKeyboardRepresentation().revalidate();
		}
	}

	/**
	 * OVERRIDDEN METHOD TO GET CUSTOM BEHAVIOUR.
	 * 
	 * DON'T OPTIMIZE FONT FOR LARGEST SG, INSTEAD OPTIMIZE FOR SECOND LARGEST
	 * SG
	 * 
	 * 
	 */
	public void resizeOptimizedComponents() {

		int widthPerElement = 0;
		if (getOnScreenKeyboardRepresentation() != null) {
			// we show the first- and subsequent-generation selection groups one
			// at
			// a
			// time
			List<Node> childrenOfRoot = getEncodingTree().getRoot()
					.getChildren();
			List<Integer> theSGSizes = new Vector<Integer>();
			for (Node n : childrenOfRoot) {
				theSGSizes.add(n.getSelectionGroupSize());
			}
			Collections.sort(theSGSizes);

			// get the SECOND largest SG
			System.out.println(theSGSizes);
			int maxNumberHorizontalElements = theSGSizes
					.get(theSGSizes.size() - 2);

			// / NO! get the largest SG
			maxNumberHorizontalElements = theSGSizes.get(theSGSizes.size() - 1);

			// change this for the Elijah special case
			//maxNumberHorizontalElements = theSGSizes.size();
			
			groupSizeThatDisplayIsFittedTo = maxNumberHorizontalElements;

			int availableWidth = (int) getOnScreenKeyboardRepresentation()
					.getSize().width;

			widthPerElement = (int) Math.floor(availableWidth)
					/ maxNumberHorizontalElements;
			// widthPerElement = widthPerElement - 1 *
			// maxNumberHorizontalElements;
			widthPerElement -= 2;

			if (true) {
				System.out.println("abWidth of: " + widthPerElement
						+ " for each of the # elements: "
						+ maxNumberHorizontalElements + " total width: "
						+ availableWidth);
			}
		}
		setButtonWidth(widthPerElement);

		// THE HEIGHT

		int availableHeight = (int) getOnScreenKeyboardRepresentation()
				.getSize().height;
		int heightPerElement = (int) Math.floor(availableHeight);
		// heightPerElement -= 2;
		if (IS_VERBOSE) {
			System.out.println("Height of: " + heightPerElement
					+ " for each of the elements.");
		}
		setButtonHeight(heightPerElement);

		setButtonDimension(new Dimension(getButtonWidth(), getButtonHeight()));

		setTheFont(getOptimalFont());
	}

}
