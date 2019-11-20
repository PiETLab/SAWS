package customGUIComponentsISF;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sourceSymbolSet.SourceSymbol;

/**
 * This class implements a widget which provides services to manage a text gloss
 * which is composed character-by-character. It may be the case that the entire
 * text gloss may not fit in the displayable area. This class distinguishes
 * between the entire gloss and the displayed portion of the gloss
 * 
 * @author mb
 * 
 */
public class JTextDisplayer extends JPanel {

	private static Font DEFAULT_FONT = new Font("serif", Font.PLAIN, 16);
	// private static Font DEFAULT_FONT = new Font("serif", Font.PLAIN, 300);

	// private Font displayFont;
	private List<SourceSymbol> sourceSymbolSetToBeDisplayed;

	// private JTextArea theTextArea;
	private JTextDisplayerWithFontInformation theTextArea;

	private String mostRecentlyAppended;

	public JTextDisplayer() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		theTextArea = new JTextDisplayerWithFontInformation();
	}

	public JTextDisplayer(
			IndirectSelectionFacilityInvocationParameterModel paramManager) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		// theTextArea = new JTextArea();
		theTextArea = new JTextDisplayerWithFontInformation(paramManager);
		// getTheTextArea().setFont(paramManager.get);

		// this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// this.setBorder(new EtchedBorder());

		// this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// theTextArea.setBorder(new TitledBorder("Composed Text:"));
		// theTextArea.setBorder(new TitledBorder(""));
		// theTextArea.setBorder(new LineBorder(Color.MAGENTA, 2));

		// int width = (int) Toolkit.getDefaultToolkit().getScreenSize()
		// .getWidth();
		// int height = Toolkit.getDefaultToolkit().getFontMetrics(DEFAULT_FONT)
		// .getHeight();
		// System.out.println(this.getClass().getName() + " width + height: "
		// + width + "\t" + height);

		// this.setSize(DEFAULT_HEIGHT, 2*DEFAULT_WIDTH);

		// this.setMaximumSize(availableRealEstateForGlossWidget);
		// theTextArea.setMaximumSize(availableRealEstateForGlossWidget);
		// this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT * 10);

		// this.setAlignmentX(Component.LEFT_ALIGNMENT);

		// this.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

		// compositionArea.setMaximumSize(new Dimension(DEFAULT_WIDTH +500 ,
		// DEFAULT_HEIGHT));
		// compositionArea.setMinimumSize(new Dimension(DEFAULT_WIDTH +500,
		// DEFAULT_HEIGHT));

		// theTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		// theTextArea.setAlignmentY(Component.TOP_ALIGNMENT);
		// theTextArea.setLineWrap(false);
		// // this.setWrapStyleWord(true);
		// theTextArea.setWrapStyleWord(false);
		//
		// // this is a kludge so that we can use JTextComponent's caret;
		// install
		// // listener to swallow keyboard events on the text area
		// theTextArea.setEditable(true);
		// theTextArea.addKeyListener(new SwallowingKeyListener());
		//
		// theTextArea.setBackground(Color.WHITE);
		// // theTextArea.setBackground(Color.RED);
		//
		// theTextArea.setSize(new Dimension(
		// availableRealEstateForGlossWidget.width - 6,
		// availableRealEstateForGlossWidget.height - 6));
		// theTextArea.setSize(new Dimension(
		// availableRealEstateForGlossWidget.width,
		// availableRealEstateForGlossWidget.height));
		// theTextArea.setMargin(new Insets(50, 50, 50, 50));

		resetText();
		// setGloss("PRETEND THAT I ALREADY TYPED THIS ");
		// setDisplayWithAppropriateSubPortionOfGloss(getTheText() +
		// DEFAULT_CARET);

		// this.theGloss = "";
		// this.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		// theTextArea.setText(theGloss + DEFAULT_CARET);

		JScrollPane scrollPane = new JScrollPane(theTextArea);
		// JScrollPane scrollPane = new JScrollPane(null);
		// scrollPane.setPreferredSize(availableRealEstateForGlossWidget);

		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setBackground(Color.YELLOW);
		// scrollPane.setBorder(null);

		// scrollPane.setBorder(new LineBorder(Color.GREEN, 2));

		this.add(scrollPane);
		// this.add(new TextDisplayerWithFontInformation());
		theTextArea.grabFocus();
		// if (isFontToBeSetAsLargeAsPossible) {
		// getTheTextArea().setDisplayFontAsLargesAsPossible(
		// this.sourceSymbolSetToBeDisplayed);
		// }

		// augment("hi");

	}

	public void paint(Graphics g) {
		// System.out.println("Painting: " + this.getClass().getName());
		// if (isFontToBeSetAsLargeAsPossible) {
		// getTheTextArea().setDisplayFontAsLargesAsPossible(
		// this.sourceSymbolSetToBeDisplayed);
		// }
		super.paint(g);
	}

	public void augmentGloss(String str) {
		mostRecentlyAppended = str;
		getTheTextArea().insertStringImmediatelyAfterCaret(str);
		getTheTextArea().repaint();
	}

	// public void setFont(Font f) {
	// getTheTextArea().setFont(f);
	// }

	public void resetText() {
		getTheTextArea().resetTheText();
	}

	public String getTheText() {
		// return this.getText().substring(0, this.getText().length() - 1)
		// .toLowerCase();
		return getTheTextArea().getTheText();
	}

	public void removeCharImmediatelyBeforeCaret() {
		getTheTextArea().removeCharacterImmediatelyBeforeCaret();
	}

	public void setDisplayFontAsLargesAsPossible(
			List<SourceSymbol> sourceSymbolSet) {
		this.sourceSymbolSetToBeDisplayed = sourceSymbolSet;

		theTextArea
				.setDisplayFontAsLargesAsPossible(sourceSymbolSetToBeDisplayed);

		// getTheTextArea().setDisplayFontAsLargesAsPossible(sourceSymbolSet);
		// Font optFont = this.getDisplayFont().deriveFont(
		// (float) this.getMaxPointSize((int) Math.ceil(this
		// .getSpecifiedSize().getHeight()), sourceSymbolSet));
		// System.out.println("Optimal Font for Display is: " + optFont);
		// System.out.println("Baseline: " + optFont.getBaselineFor('Q'));
		// getTheTextArea().setFont(optFont);
	}

	public void setPreferredSize(Dimension dim) {
		super.setPreferredSize(dim);
		theTextArea.setPreferredSize(dim);
	}

	public void setMaximumSize(Dimension dim) {
		super.setMaximumSize(dim);
		theTextArea.setMaximumSize(dim);
	}

	public void setMinimumSize(Dimension dim) {
		super.setMinimumSize(dim);
		theTextArea.setMinimumSize(dim);
	}

	public Font getDisplayFont() {
		return getTheTextArea().getFont();
	}

	// public void setDisplayFont(Font displayFont) {
	// this.displayFont = displayFont;
	// getTheTextArea().setFont(this.displayFont);
	// }

	public JTextDisplayerWithFontInformation getTheTextArea() {
		return theTextArea;
	}

	public void repositionCaret(int newPosition) {
		theTextArea.repositionCaret(newPosition);
	}

	public void clearGloss() {
		theTextArea.clearGloss();
	}

	public void showFontHints() {
		theTextArea.showFontHints();
	}

	public String getMostRecentlyAppended() {
		return mostRecentlyAppended;
	}

}
