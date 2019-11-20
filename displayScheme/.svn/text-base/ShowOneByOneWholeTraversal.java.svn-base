package displayScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sourceSymbolSet.SourceSymbol;
import IndirectSelectionFacility.OnScreenKeyboardView;
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
public class ShowOneByOneWholeTraversal extends
		DisplaySchemeForTraversableEncodingTreeStates {

	private final Color SELECTED_LEAF = Color.blue;

	private final Color SELECTED_INTERNAL = Color.MAGENTA;

	private double FOKUS_FONT_SIZE_BOOST = 1.0; // make the font of the
	// in-focus nodes 30% bigger
	// than the out of focus
	// state

	// private Font DEFAULT_FONT = new Font("sanserif", Font.PLAIN, 300);
	// private Font DEFAULT_FONT = new Font("serif", Font.PLAIN, 300);

	int INSET_WIDTH = 0;
	private Insets insets = new Insets(INSET_WIDTH, INSET_WIDTH + 1,
			INSET_WIDTH, INSET_WIDTH);

	private SelectionGroup sgThatIsShownInFocusOnView;

	public ShowOneByOneWholeTraversal(TraversableEncodingTree encodingTree,
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

	protected int getButtonWidth() {
		// we show the first- and subsequent-generation selection groups one at
		// a
		// time
		List<Node> childrenOfRoot = getEncodingTree().getRoot().getChildren();
		int maxNumberHorizontalElements = 0;
		for (Node n : childrenOfRoot) {
			int tmp = n.getSelectionGroupSize();
			// System.out.println("curr child: " + n + "\n sg size: " + tmp);
			// if tmp is 0, then this means the node is the parent only to
			// leaves
			// tmp = n.getMaxOutdegree();
			if (tmp > maxNumberHorizontalElements) {
				maxNumberHorizontalElements = tmp;
			}
		}
		if (maxNumberHorizontalElements == 0) {
			maxNumberHorizontalElements = childrenOfRoot.size();
		}
		// int maxNumberHorizontalElements = getEncodingTree().getRoot()
		// .getMaxOutdegreeExcludingParentsOfLeaves();

		/* purposely leave this operation as integer division */

		int availableWidth = (int) getOnScreenKeyboardRepresentation()
				.getSize().width;

		int widthPerElement = (int) Math.floor(availableWidth)
				/ maxNumberHorizontalElements;
		// widthPerElement = widthPerElement - 1 * maxNumberHorizontalElements;
		widthPerElement -= 2;

		if (super.IS_VERBOSE) {
			System.out.println("**Width of: " + widthPerElement
					+ " for each of the # elements: "
					+ maxNumberHorizontalElements + " total width: "
					+ availableWidth);
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
			System.out.println(this.getClass().getName() + " " + label
					+ " sg just put in fokus: " + sgThatIsShownInFocusOnView);
		}

		// if sg is trivial, show whole focus cycle instead of just sg
		if (sg.isTrivial()) {
			showTrivialNodeInFocus(sg);
		} else {
			showNonTrivialNodeInFokus(sg);
		}
	}

	private void resetToDefault(SelectionGroup sg) {
		// sg.changeBackgroundColour(DEFAULT_COLOUR);

		for (SourceSymbol x : sg.extractMembers()) {
			JButton but = (JButton) x;
			but.setFont(super.getFont());
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

		Dimension buttonDimension;
		int buttonWidth;
		// panel.setBackground(BACKGROUND_COLOUR);

		// panel.add(new JLabel("x"));

		// SelectionGroup sg = encodingTree.getCurrentSelectionGroup();
		// System.out.print("Nontrivial group in focus: ");

		for (SourceSymbol x : sg.extractMembers()) {
			// SourceSymbol x = sg.extractMembers().get(0);
			// if (panel.getComponentCount() != 0) {
			// panel.add(Box.createRigidArea(new Dimension(0, 0)));
			// }
			// System.out.println("Source Symbol to be shown: " + x);
			// JIndirectSelectionButton but = (JIndirectSelectionButton) x;
			JButton but = (JButton) x;
			massageButtonFormattingNonTrivial(but, x.getTextLabel());

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
		getOnScreenKeyboardRepresentation().revalidate();
	}

	/**
	 * Leave display as-is, but change font of trivial node that is in focus
	 * 
	 * @param sg
	 */
	private void showTrivialNodeInFocus(SelectionGroup sg) {
		// sg.changeBackgroundColour(IN_FOKUS_COLOUR);
		// JIndirectSelectionButton but2 = (JIndirectSelectionButton)
		// sg.getFirst();
		// but2.setFont(getDerivedFontForFokus(buttonFont));

		// this part is to show ONLY the button in focus
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBackground(super.paramModel.getBackgroundColour());
		panel.setForeground(super.paramModel.getTextColour());

		// SelectionGroup sg = encodingTree.getCurrentSelectionGroup();
		for (SourceSymbol x : sg.extractMembers()) {
			// SourceSymbol x = sg.extractMembers().get(0);
			// if (panel.getComponentCount() != 0) {
			// panel.add(Box.createRigidArea(new Dimension(0, 0)));
			// }
			// System.out.println("update extract: " + x.getTextLabel());
			// JIndirectSelectionButton but = (JIndirectSelectionButton) x;
			JButton but = (JButton) x;
			massageButtonFormattingTrivial(but, x.getTextLabel());
			// but.setMargin(insets);
			// but.setFont(buttonFont);
			// but.setText(x.getTextLabel());
			// System.out.println("button label: " + x.getTextLabel());
			// // but.setFont(getDerivedFontForFokus(buttonFont));
			// but.setPreferredSize(getButtonDimension());
			// but.setMaximumSize(getButtonDimension());
			// but.setMinimumSize(getButtonDimension());
			// but.setHorizontalAlignment(SwingConstants.LEFT);
			// but.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.add(but);
		}
		getOnScreenKeyboardRepresentation().removeAll();
		// onScreenKeyboardRepresentation.revalidate();
		// onScreenKeyboardRepresentation.setLayout(new
		// BoxLayout(onScreenKeyboardRepresentation, BoxLayout.PAGE_AXIS));
		// onScreenKeyboardRepresentation.setAlignmentX(Component.LEFT_ALIGNMENT);
		// System.out.println("adding from here: " + );
		getOnScreenKeyboardRepresentation().add(panel);
		getOnScreenKeyboardRepresentation().revalidate();

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

	/**
	 * 
	 * EFFECT SOLELY THRU SIDE-EFFECTS
	 * 
	 * @param but
	 * @param possiblyHTMLlabel
	 */
	private void massageButtonFormattingNonTrivial(JButton but,
			String possiblyHTMLlabel) {
		but.setMargin(insets);
		but.setFont(super.getFont());
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
	}

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
		but.setFont(super.getFont());
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

}
