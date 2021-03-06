package displayScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import IndirectSelectionFacility.OnScreenKeyboardView;

import sourceSymbolSet.SourceSymbol;
import treeDataStructure.Node;

import encodingTrees.TraversableEncodingTree;

/**
 * This class implements the scheme with which the keyboard is displayed (for
 * instance, whether all rows are shown at the same time or are shown one by
 * one). The primary way in which the display scheme is effected is through the
 * updateAppearance method.
 * 
 * The client is anticipated to be the class which implements the view of the
 * keyboard.
 * 
 * 
 * @author mb
 * 
 */
public abstract class DisplaySchemeForTraversableEncodingTreeStates {

	private boolean IS_IMAGE_ONLY = false;
	private boolean IS_IMAGE_AND_TEXT = false;
	private boolean IS_TEXT_ONLY = true;

	private String fontFamily;
	private Font theFont;

	private TraversableEncodingTree encodingTree;
	private OnScreenKeyboardView onScreenKeyboardRepresentation;
	protected IndirectSelectionFacilityInvocationParameterModel paramModel;
	protected static final boolean IS_VERBOSE = false;

	protected Dimension buttonDimension = new Dimension(10, 20);
	private int buttonWidth = 0;
	private int buttonHeight = 0;

	protected DisplaySchemeForTraversableEncodingTreeStates(
			TraversableEncodingTree encodingTree,
			OnScreenKeyboardView onScreenKeyboardRepresentation,
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		this.paramModel = paramModel;
		this.encodingTree = encodingTree;
		this.onScreenKeyboardRepresentation = onScreenKeyboardRepresentation;
		fontFamily = paramModel.getFontFamily();
		setTheFont(new Font(fontFamily, Font.PLAIN, 300));
		// this.resizeOptimizedComponents();
	}

	public abstract void updateAppearance();

	public abstract void highlightCurrentSelectionGroupAsSelected();

	protected TraversableEncodingTree getEncodingTree() {
		return encodingTree;
	}

	protected OnScreenKeyboardView getOnScreenKeyboardRepresentation() {
		return onScreenKeyboardRepresentation;
	}

	// protected int getButtonWidth() {
	// int maxNumberHorizontalElements = getEncodingTree().getRoot()
	// .getMaxOutdegree();
	// // System.out.println("maxNumberHorizontalElements: "
	// // + maxNumberHorizontalElements);
	//
	// /* purposely leave this operation as integer division */
	//
	// int availableWidth = (int) getOnScreenKeyboardRepresentation()
	// .getSpecifiedSize().getWidth();
	// availableWidth = (int)
	// getOnScreenKeyboardRepresentation().getSize().width;
	//
	// int widthPerElement = (int) Math.floor(availableWidth)
	// / maxNumberHorizontalElements;
	// widthPerElement = widthPerElement - 1 * maxNumberHorizontalElements;
	// return widthPerElement;
	// // return 50;
	// }

	protected int getButtonWidth() {
		return buttonWidth;
	}

	protected int getMaxPointSize(Dimension allowableDimension,
			double sizeRatioBoostForShowingFocus) {
		// System.out.println("Derive max font for buttons of size: "
		// + allowableDimension);
		// start with a tiny font and enlarge until it is maximal
		Font f = getFont().deriveFont(1f);
		Font f2 = getDerivedFontForFokus(f, sizeRatioBoostForShowingFocus);

		Dimension currMaxDimension = getMaxElementD(getFontDimensions(
				getEncodingTree().getLeaves(),
				getOnScreenKeyboardRepresentation().getFontMetrics(f2)));
		// System.out
		// .println("max dimension of any source symbol under font of point size: "
		// + f.getSize() + " is " + currMaxDimension);
		while ((currMaxDimension.width < allowableDimension.width)
				&& (currMaxDimension.height < allowableDimension.height)) {
			f = f.deriveFont((float) f.getSize() + 1);
			f2 = getDerivedFontForFokus(f, sizeRatioBoostForShowingFocus);
			currMaxDimension = getMaxElementD(getFontDimensions(
					getEncodingTree().getLeaves(),
					getOnScreenKeyboardRepresentation().getFontMetrics(f2)));
			// System.out
			// .println("max dimension of any source symbol under font of point size: "
			// + f.getSize() + " is " + currMaxDimension);
		}
		f = f.deriveFont((float) f.getSize() - 1);
		// System.out
		// .println("max dimension of any source symbol under font of point size: "
		// + f.getSize() + " is " + currMaxDimension);

		// System.out
		// .println("max width of any source symbol under font of point size: "
		// + f.getSize() + " is " + currMaxWidth);
		return f.getSize();
	}

	/**
	 * Determines the largest possible font point size for the default font such
	 * that any character will fit within the passed width
	 * 
	 * @param allowableWidthPerElement
	 * @return
	 */
	protected int getMaxPointSize(int allowableWidthPerElement,
			double sizeRatioBoostForShowingFocus) {
		// start with a tiny font and enlarge until it is maximal
		Font f = getFont().deriveFont(1f);
		Font f2 = getDerivedFontForFokus(f, sizeRatioBoostForShowingFocus);

		// int tmpWidth = Toolkit.getDefaultToolkit().getFontMetrics(f)
		// .getMaxAdvance();

		// int currMaxWidth = getMaxElement(onScreenKeyboardRepresentation
		// .getFontMetrics(f).getWidths());
		int currMaxWidth = getMaxElementI(getFontWidths(getEncodingTree()
				.getLeaves(), getOnScreenKeyboardRepresentation()
				.getFontMetrics(f2), allowableWidthPerElement));

		// System.out
		// .println("max width of any source symbol under font of point size: "
		// + f.getSize() + " is " + currMaxWidth);
		while (currMaxWidth < allowableWidthPerElement) {
			f = f.deriveFont((float) f.getSize() + 1);
			f2 = getDerivedFontForFokus(f, sizeRatioBoostForShowingFocus);
			// currMaxWidth = getMaxElement(onScreenKeyboardRepresentation
			// .getFontMetrics(f).getWidths());
			currMaxWidth = getMaxElementI(getFontWidths(getEncodingTree()
					.getLeaves(), getOnScreenKeyboardRepresentation()
					.getFontMetrics(f2), allowableWidthPerElement));
			// System.out
			// .println("max width of any source symbol under font of point size: "
			// + f.getSize() + " is " + currMaxWidth);
		}
		// System.out
		// .println("max width of any source symbol under font of point size: "
		// + f.getSize() + " is " + currMaxWidth);
		return f.getSize();
	}

	public abstract Font getDerivedFontForFokus(Font f);

	/**
	 * Gets the font, given as a derivation of the passed font
	 * 
	 * @param f
	 * @return
	 */
	protected Font getDerivedFontForFokus(Font f,
			double sizeRatioBoostForShowingFocus) {
		Font f2 = f
				.deriveFont((float) (f.getSize() * sizeRatioBoostForShowingFocus));
		f2 = f2.deriveFont(Font.BOLD);

		// the following doesn't work:

		// 
		// http://bugs.sun.com/bugdatabase/view_bug.do;:YfiG?bug_id=4296952

		// Map<TextAttribute, Object> fontAttributes = new
		// HashMap<TextAttribute, Object>();
		// // getDerivedFontForFokus( buttonFont).getAttributes(); // new
		// // HashMap<TextAttribute,
		// // Integer>();
		// // does this font have versions with these text attributes?
		// fontAttributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		// //fontAttributes.put(TextAttribute.UNDERLINE,
		// TextAttribute.UNDERLINE_ON);
		// fontAttributes.put(TextAttribute.STRIKETHROUGH,
		// TextAttribute.STRIKETHROUGH_ON);
		// f2 = f2.deriveFont(fontAttributes);

		// AffineTransform at = new AffineTransform();
		// double scaleX = 0;
		// double scaleY = 1.5;
		// at.scale(scaleX, scaleY);
		// f2 = f2.deriveFont(at);

		return f2;
	}

	public List<Dimension> getFontDimensions(
			List<SourceSymbol> sourceSymbolSet, FontMetrics fm) {
		List<Dimension> dimensions = new Vector<Dimension>();
		for (SourceSymbol s : sourceSymbolSet) {
			JButton but = new JButton();
			but.setText(s.getTextLabel());
			but.setFont(fm.getFont());

			Dimension butDim = new Dimension((int) Math.ceil(but
					.getPreferredSize().getWidth()), (int) Math.ceil(but
					.getPreferredSize().getHeight()));
			// System.out.println("Preferred size for :" + s.getTextLabel()
			// + " is: " + but.getPreferredSize());
			dimensions.add(butDim);
		}
		return dimensions;
	}

	private int getMaxElementI(List<Integer> list) {
		int max = list.get(0);
		for (int i : list) {
			if (i > max)
				max = i;
		}
		return max;
	}

	/**
	 * Finds dimension with largest width
	 * 
	 * @param list
	 * @return
	 */
	private Dimension getMaxElementD(List<Dimension> list) {
		Dimension max2 = list.get(0);
		for (Dimension i : list) {
			if (i.getWidth() > max2.getWidth())
				max2 = i;
		}
		return max2;
	}

	// private int getMaxElement(int[] list) {
	// int max = list[0];
	// for (int i = 1; i < list.length; i++) {
	// if (list[i] > max)
	// max = list[i];
	// }
	// return max;
	// }

	public Font getFont() {
		// return theFont.deriveFont(60f);
		return theFont;
	}

	public void emptyDisplay() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		// panel.add(new JLabel("hi"));

		getOnScreenKeyboardRepresentation().removeAll();
		getOnScreenKeyboardRepresentation().add(panel);

		// getOnScreenKeyboardRepresentation().add(new JLabel("hi"));
		// http://www.jguru.com/faq/view.jsp?EID=88716
		getOnScreenKeyboardRepresentation().invalidate();
		getOnScreenKeyboardRepresentation().validate();
		getOnScreenKeyboardRepresentation().repaint();

		// getOnScreenKeyboardRepresentation().revalidate();
	}

	public List<Integer> getFontWidths(List<SourceSymbol> sourceSymbolSet,
			FontMetrics fm, int allowableWidthPerElement) {
		List<Integer> widths = new Vector<Integer>();
		for (SourceSymbol s : sourceSymbolSet) {
			// System.out.println("for string width: " + s.getTextLabel());
			// this only works if the text label is NOT html-formatted
			int widthOfThisSourceSymbol = fm.stringWidth(s.getTextLabel());

			// System.out.println("width using stringWidth: "
			// + widthOfThisSourceSymbol);
			JButton but = new JButton();
			but.setText(s.getTextLabel());
			but.setFont(fm.getFont());
			// System.out.println(but.getPreferredSize());
			// View view = (View) but
			// .getClientProperty(javax.swing.plaf.basic.BasicHTML.propertyKey);
			// // #
			// boolean width = true;
			// int prefSize = allowableWidthPerElement;
			// view.setSize(width ? prefSize : 0, width ? 0 : prefSize);
			// view.setSize(allowableWidthPerElement, 0);
			// #
			// float w = view.getPreferredSpan(View.X_AXIS);
			double w = but.getPreferredSize().getWidth();
			// System.out.println("width using getPreferredSize: " + w);
			if (w < widthOfThisSourceSymbol) {
				widthOfThisSourceSymbol = (int) Math.ceil(w);
			}
			widths.add(widthOfThisSourceSymbol);
		}

		for (SourceSymbol s : sourceSymbolSet) {
			// System.out.println("for string width: " + s.getTextLabel());
			// this only works if the text label is NOT html-formatted
			int widthOfThisSourceSymbol = fm.stringWidth(s.getTextLabel()
					.toLowerCase());

			// System.out.println("width using stringWidth: "
			// + widthOfThisSourceSymbol);
			JButton but = new JButton();
			but.setText(s.getTextLabel().toLowerCase());
			but.setFont(fm.getFont());
			// System.out.println(but.getPreferredSize());
			// View view = (View) but
			// .getClientProperty(javax.swing.plaf.basic.BasicHTML.propertyKey);
			// // #
			// boolean width = true;
			// int prefSize = allowableWidthPerElement;
			// view.setSize(width ? prefSize : 0, width ? 0 : prefSize);
			// view.setSize(allowableWidthPerElement, 0);
			// #
			// float w = view.getPreferredSpan(View.X_AXIS);
			double w = but.getPreferredSize().getWidth();
			// System.out.println("width using getPreferredSize: " + w);
			if (w < widthOfThisSourceSymbol) {
				widthOfThisSourceSymbol = (int) Math.ceil(w);
			}
			widths.add(widthOfThisSourceSymbol);
		}

		return widths;
	}

	public List<Integer> getFontHeights(List<SourceSymbol> sourceSymbolSet,
			FontMetrics fm, int allowableHeightPerElement) {
		List<Integer> heights = new Vector<Integer>();
		for (SourceSymbol s : sourceSymbolSet) {
			// System.out.println("for string width: " + s.getTextLabel());
			// this only works if the text label is NOT html-formatted
			int heightOfThisSourceSymbol = 0;

			// System.out.println("width using stringWidth: "
			// + widthOfThisSourceSymbol);
			JButton but = new JButton();
			but.setText(s.getTextLabel());
			but.setFont(fm.getFont());
			// System.out.println(but.getPreferredSize());
			// View view = (View) but
			// .getClientProperty(javax.swing.plaf.basic.BasicHTML.propertyKey);
			// // #
			// boolean width = true;
			// int prefSize = allowableWidthPerElement;
			// view.setSize(width ? prefSize : 0, width ? 0 : prefSize);
			// view.setSize(allowableWidthPerElement, 0);
			// #
			// float w = view.getPreferredSpan(View.X_AXIS);
			double w = but.getPreferredSize().getHeight();
			// System.out.println("width using getPreferredSize: " + w);
			// if (w < heightOfThisSourceSymbol) {
			heightOfThisSourceSymbol = (int) Math.ceil(w);
			// }

			heights.add(heightOfThisSourceSymbol);
		}
		return heights;
	}

	/**
	 * Determines the largest possible font point size for the default font such
	 * that any character will fit within the passed width
	 * 
	 * @param allowableWidthPerElement
	 * @return
	 */
	protected int getMaxPointSizeWidthwise(int allowableWidthPerElement) {
		// start with a tiny font and enlarge until it is maximal
		Font f = getFont().deriveFont(1f);
		Font f2 = getDerivedFontForFokus(f);

		// int tmpWidth = Toolkit.getDefaultToolkit().getFontMetrics(f)
		// .getMaxAdvance();

		// int currMaxWidth = getMaxElement(onScreenKeyboardRepresentation
		// .getFontMetrics(f).getWidths());
		int currMaxWidth = getMaxElementI(getFontWidths(getEncodingTree()
				.getLeaves(), getOnScreenKeyboardRepresentation()
				.getFontMetrics(f2), allowableWidthPerElement));

		// System.out
		// .println("max width of any source symbol under font of point size: "
		// + f.getSize() + " is " + currMaxWidth);
		while (currMaxWidth < allowableWidthPerElement) {
			f = f.deriveFont((float) f.getSize() + 1);
			f2 = getDerivedFontForFokus(f);
			// currMaxWidth = getMaxElement(onScreenKeyboardRepresentation
			// .getFontMetrics(f).getWidths());
			currMaxWidth = getMaxElementI(getFontWidths(getEncodingTree()
					.getLeaves(), getOnScreenKeyboardRepresentation()
					.getFontMetrics(f2), allowableWidthPerElement));
			if (IS_VERBOSE)
				System.out
						.println("max width of any source symbol under font of point size: "
								+ f.getSize()
								+ " is "
								+ currMaxWidth
								+ " under allowable width: "
								+ allowableWidthPerElement);
		}
		// System.out
		// .println("max width of any source symbol under font of point size: "
		// + f.getSize() + " is " + currMaxWidth);
		return f.getSize();
	}

	/**
	 * Determines the largest possible font point size for the default font such
	 * that any character will fit within the passed width
	 * 
	 * @param allowableWidthPerElement
	 * @return
	 */
	protected int getMaxPointSizeHeightwise(int allowableHeightPerElement) {
		// start with a tiny font and enlarge until it is maximal
		Font f = getFont().deriveFont(1f);
		Font f2 = getDerivedFontForFokus(f);

		// int tmpWidth = Toolkit.getDefaultToolkit().getFontMetrics(f)
		// .getMaxAdvance();

		// int currMaxWidth = getMaxElement(onScreenKeyboardRepresentation
		// .getFontMetrics(f).getWidths());
		int currMaxHeight = getMaxElementI(getFontHeights(getEncodingTree()
				.getLeaves(), getOnScreenKeyboardRepresentation()
				.getFontMetrics(f2), allowableHeightPerElement));

		// System.out
		// .println("max width of any source symbol under font of point size: "
		// + f.getSize() + " is " + currMaxWidth);
		while (currMaxHeight < allowableHeightPerElement) {
			f = f.deriveFont((float) f.getSize() + 1);
			f2 = getDerivedFontForFokus(f);
			// currMaxWidth = getMaxElement(onScreenKeyboardRepresentation
			// .getFontMetrics(f).getWidths());
			currMaxHeight = getMaxElementI(getFontHeights(getEncodingTree()
					.getLeaves(), getOnScreenKeyboardRepresentation()
					.getFontMetrics(f2), allowableHeightPerElement));
			// System.out
			// .println("max width of any source symbol under font of point size: "
			// + f.getSize() + " is " + currMaxWidth);
			if (IS_VERBOSE)
				System.out
						.println("max width of any source symbol under font of point size: "
								+ f.getSize() + " is " + currMaxHeight);
		}
		return f.getSize();
	}

	/**
	 * Derive the optimal font for this view, which is the largest possible font
	 * such that, in any state, the button labels will not overflow.
	 * 
	 * The font size may be scaled up by FOKUS_FONT_SIZE_BOOST *and* the font
	 * may be bolded.
	 * 
	 * @return
	 */
	protected Font getOptimalFont() {
		int maxPointSizeWidthwise = getMaxPointSizeWidthwise(getButtonWidth());//
		// 2
		// *
		int maxPointSizeHeightwise = getMaxPointSizeHeightwise(getButtonHeight());// -
		// 2
		// *
		int max = maxPointSizeWidthwise;
		if (maxPointSizeHeightwise < maxPointSizeWidthwise)
			max = maxPointSizeHeightwise;

		// INSET_WIDTH);
		// maxPointSize = 250;
		// System.out.println("Total width available: "
		// + onScreenKeyboardRepresentation.getAvailableWidth());
		// System.out.println("Insets: "
		// + onScreenKeyboardRepresentation.getInsets());
		if (true)
			System.out.println("max font size: " + max);
		return getFont().deriveFont((float) (max) - 0f);
		// return getFont().deriveFont(10f);
	}

	protected int getButtonHeight() {
		return buttonHeight;
	}

	public Dimension getButtonDimension() {
		// if (buttonDimension == null) {
		// // System.out.println("Width/Height per button: "
		// // + getButtonWidth()
		// // + "\t"
		// // + getOnScreenKeyboardRepresentation().getSpecifiedSize()
		// // .getHeight());
		//
		// // int availableHeight = (int) getOnScreenKeyboardRepresentation()
		// // .getSpecifiedSize().getHeight();
		// int availableHeight = (int) getOnScreenKeyboardRepresentation()
		// .getSize().height;
		//
		// buttonDimension = new Dimension(getButtonWidth(), availableHeight);
		// }
		// buttonDimension = new Dimension(300, 30);
		return buttonDimension;
	}

	public void resizeOptimizedComponents() {

		int widthPerElement = 0;
		if (getOnScreenKeyboardRepresentation() != null) {
			// we show the first- and subsequent-generation selection groups one
			// at
			// a
			// time
			List<Node> childrenOfRoot = getEncodingTree().getRoot()
					.getChildren();
			int maxNumberHorizontalElements = 0;
			for (Node n : childrenOfRoot) {
				int tmp = n.getSelectionGroupSize();
				// System.out.println("curr child: " + n + "\n sg size: " +
				// tmp);
				// if tmp is 0, then this means the node is the parent only to
				// leaves
				// tmp = n.getMaxOutdegree();
				if (tmp > maxNumberHorizontalElements) {
					maxNumberHorizontalElements = tmp;
				}
			}
			// maxNumberHorizontalElements = getEncodingTree().getRoot()
			// .getMaxOutdegreeExcludingParentsOfLeaves();

			/* purposely leave this operation as integer division */

			// int availableWidth = (int) getOnScreenKeyboardRepresentation()
			// .getSpecifiedSize().getWidth();
			int availableWidth = (int) getOnScreenKeyboardRepresentation()
					.getSize().width;

			widthPerElement = (int) Math.floor(availableWidth)
					/ maxNumberHorizontalElements;
			// widthPerElement = widthPerElement - 1 *
			// maxNumberHorizontalElements;
			widthPerElement -= 2;

			if (IS_VERBOSE) {
				System.out.println("zzWidth of: " + widthPerElement
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

		// setTheFont(getFont().deriveFont(20f));
		setTheFont(getOptimalFont());
	}

	public void setButtonWidth(int buttonWidth) {
		this.buttonWidth = buttonWidth;
		this.buttonDimension = new Dimension(getButtonWidth(),
				getButtonHeight());

	}

	public void setButtonHeight(int buttonHeight) {
		this.buttonHeight = buttonHeight;
		this.buttonDimension = new Dimension(getButtonWidth(),
				getButtonHeight());
	}

	public void setButtonDimension(Dimension buttonDimension) {
		this.buttonDimension = buttonDimension;
	}

	public void setTheFont(Font theFont) {
		System.out.println(this.getClass().getName() + ":  Set the font to: "
				+ theFont);
		// kludge for elijah
		if (IS_IMAGE_ONLY) {
			theFont = theFont.deriveFont(1f);
		} else if (IS_IMAGE_AND_TEXT) {
			theFont = theFont.deriveFont(100f);
		} else if (IS_TEXT_ONLY) {
			this.theFont = theFont;
		}
	}

}
