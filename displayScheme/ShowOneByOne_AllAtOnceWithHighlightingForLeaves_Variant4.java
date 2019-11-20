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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import IndirectSelectionFacility.OnScreenKeyboardView;
import treeDataStructure.Node;
import treeDataStructure.SelectionGroup;
import encodingTrees.TraversableEncodingTree;

/**
 * 
 * Shows each selection group (SG) one by one within the current focus cycle.
 * Maintain this format only for the first level(s) of the encoding tree.
 * 
 * Once the user descends to a level at which (??? what is the condition), then
 * all of the SG's are shown all at once, with focus indicated by variant #1
 * highlighting: shown in reverse video
 * 
 * The font size is automatically determined as the largest possible font size,
 * given amount of real estate allocated to a button in the worst-case scenario
 * (e.g., when the largest number of buttons are shown on the screen).
 * 
 * @author mb
 * 
 */
public class ShowOneByOne_AllAtOnceWithHighlightingForLeaves_Variant4 extends
		DisplaySchemeForTraversableEncodingTreeStates {

	private double FOKUS_FONT_SIZE_BOOST = 1.0; // make the font of the
	// in-focus nodes 30% bigger
	// than the out of focus
	// state

	int INSET_WIDTH = 0;
	private Insets insets = new Insets(INSET_WIDTH, INSET_WIDTH + 1,
			INSET_WIDTH, INSET_WIDTH);

	private SelectionGroup sgThatIsShownInFocusOnView;

	public ShowOneByOne_AllAtOnceWithHighlightingForLeaves_Variant4(
			TraversableEncodingTree encodingTree,
			OnScreenKeyboardView onScreenKeyboardRepresentation,
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		super(encodingTree, onScreenKeyboardRepresentation, paramModel);
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
			String label = "nontrivial";
			if (sgThatIsShownInFocusOnView.isTrivial())
				label = "trivial";
			System.out.println(this.getClass().getName() + " " + label
					+ " sg just put in fokus: " + sgThatIsShownInFocusOnView);
		}

		// System.out.println("updating appearance: " + sg);
		// System.out.println("is trivial?: "
		// + sgThatIsShownInFocusOnView.isTrivial());

		// int maxWidth = Toolkit.getDefaultToolkit().getS
		// encodingTree.getRoot().getNodeSelectionGroup().changeBackgroundColour(
		// DEFAULT_COLOUR);

		// if sg is trivial, show whole focus cycle instead of just sg
		if (sg.isTrivial()) {
			// showTrivialNodeInFocus(sg);
			// SourceSymbol n = sg.getFirst();
			// showNodeAsInFocus(n);
			showNonTrivialNodeInFokus(sg);
		} else {
			showNonTrivialNodeInFokus(sg);
		}
	}

	private void resetToDefault(SelectionGroup sg) {
		// sg.changeBackgroundColour(DEFAULT_COLOUR);

		for (SourceSymbol x : sg.extractMembers()) {
			JButton but = (JButton) x;
			but.setOpaque(true);
			but.setFont(super.getFont());
			but.setBackground(paramModel.getBackgroundColour());
			// but.setBackground(Color.RED);
			but.setForeground(paramModel.getTextColour());
			but.setHorizontalAlignment(paramModel.getHorizontalAlignment());
			but.setVerticalAlignment(paramModel.getVerticalAlignment());

			// but.setForeground(Color.RED);
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
		// http://www.jguru.com/faq/view.jsp?EID=88716
		getOnScreenKeyboardRepresentation().invalidate();
		getOnScreenKeyboardRepresentation().validate();
		getOnScreenKeyboardRepresentation().repaint();
		// getOnScreenKeyboardRepresentation().revalidate();
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
		but.setHorizontalAlignment(paramModel.getHorizontalAlignment());
		but.setVerticalAlignment(paramModel.getVerticalAlignment());

	}

	private void showNodeAsInFocus(SourceSymbol n) {
		JIndirectSelectionButton but = (JIndirectSelectionButton) n;
		// but.setFont(getDerivedFontForFokus(buttonFont,
		// FOKUS_FONT_SIZE_BOOST));
		but.setBackground(paramModel.getTextColour());
		but.setForeground(paramModel.getBackgroundColour());
		// but.repaint();
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

	@Override
	public void highlightCurrentSelectionGroupAsSelected() {
	}

}
