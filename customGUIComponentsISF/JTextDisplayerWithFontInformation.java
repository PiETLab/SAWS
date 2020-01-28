package customGUIComponentsISF;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JComponent;

import IndirectSelectionFacilityCommands.AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand;

import sourceSymbolSet.ElijahCompositionSet;
import sourceSymbolSet.SourceSymbol;

public class JTextDisplayerWithFontInformation extends JComponent {

	// public final String DEFAULT_CARET = "^";
	public final String DEFAULT_CARET = "";
	
	private Dimension screenSize;

	private String theText = "";// = INIT;

	private Font font = new Font("Dialog", Font.PLAIN, 96);

	private int currentCaretPosition;

	private Rectangle2D bounds;

	private TextLayout layout;

	private IndirectSelectionFacilityInvocationParameterModel paramManager;

	private boolean shouldShowFontHints;

	private int INSET_PADDING = 25;

	private boolean IS_IMAGE_ONLY = false;
	private boolean IS_IMAGE_AND_TEXT = false;
	private boolean IS_TEXT_ONLY = true;

	private static final boolean IS_VERBOSE = true;

	public JTextDisplayerWithFontInformation(
			IndirectSelectionFacilityInvocationParameterModel paramManager) {
		this();
		this.paramManager = paramManager;
		this.setFont(new Font(paramManager.getFontFamily(), Font.PLAIN, 96));
		
		this.setBackground(paramManager.getBackgroundColour());
		// this.setBackground(Color.CYAN);
		this.setForeground(paramManager.getTextColour());
		shouldShowFontHints = paramManager.showFontHints();
	}

	public JTextDisplayerWithFontInformation() {
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setAlignmentY(Component.TOP_ALIGNMENT);
		currentCaretPosition = 0;
	}
	
	public JTextDisplayerWithFontInformation(Dimension dimension,
			IndirectSelectionFacilityInvocationParameterModel paramManager) {
		this();
		this.paramManager = paramManager;
		this.setFont(new Font(paramManager.getFontFamily(), Font.PLAIN, 96));
		this.setBackground(paramManager.getBackgroundColour());
		// this.setBackground(Color.CYAN);
		this.setForeground(paramManager.getTextColour());
		shouldShowFontHints = paramManager.showFontHints();
		
		this.setSize(dimension);
		this.screenSize = dimension;
		
	}
		
	public void paint(Graphics g) {
		// drawTextCentered(g);
		if (getTheText().length() != 0) {
			if (IS_IMAGE_AND_TEXT) {
				drawTextCentered2(g);
			} else if (IS_IMAGE_ONLY) {
				drawTextCentered2(g);
			} else if (IS_TEXT_ONLY) {
				drawTextLeftJustified2(g);
			}
		}
	}

	private void drawTextCentered(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setFont(getFont());
		int width = getSize().width;
		int height = getSize().height;

		FontRenderContext frc = g2.getFontRenderContext();
		// System.out.println(frc + " " + this.getTheText() + " " + getFont());
		LineMetrics metrics = this.getFont().getLineMetrics(this.getTheText(),
				frc);
		// System.out.println("To render: " + this.getTheText());
		float messageWidth = (float) getFont().getStringBounds(
				this.getTheText(), frc).getWidth();

		// center text
		float ascent = metrics.getAscent();
		float descent = metrics.getDescent();
		float x = (width - messageWidth) / 2;
		float y = (height + metrics.getHeight()) / 2 - descent;

		int PAD = 25;
		g2.setPaint(getBackground());
		g2.fillRect(0, 0, width, height);

		g2.setPaint(getForeground());
		g2.drawString(this.getTheText(), x, y);

		if (shouldShowFontHints) {
			g2.setPaint(Color.MAGENTA); // Base lines
			drawLine(g2, x - PAD, y, x + messageWidth + PAD, y);
			drawLine(g2, x, y + PAD, x, y - ascent - PAD);
			g2.setPaint(Color.green); // Ascent line
			drawLine(g2, x - PAD, y - ascent, x + messageWidth + PAD, y
					- ascent);
			g2.setPaint(Color.red); // Descent line
			drawLine(g2, x - PAD, y + descent, x + messageWidth + PAD, y
					+ descent);
			// System.out.println("paint's ascent and descent: " + ascent + " "
			// + descent + " for font: " + this.getFont());
		}
	}

	/**
	 * draws text left justified. If the width of the text exceeds the width of
	 * the display, then the text wraps to the left
	 * 
	 * @param g
	 */
	private void drawTextLeftJustified(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setFont(getFont());
		int widthOfDisplayRegion = getSize().width;
		int heightOfDisplayRegion = getSize().height;

		FontRenderContext frc = g2.getFontRenderContext();
		// System.out.println(frc + " " + this.getTheText() + " " + getFont());
		// LineMetrics metrics =
		// this.getFont().getLineMetrics(this.getTheText(),
		// frc);

		TextLayout layout = new TextLayout(this.getTheText(), getFont(), frc);

		// System.out.println("To render: " + this.getTheText());
		float messageWidth = (float) getFont().getStringBounds(
				this.getTheText(), frc).getWidth();

		messageWidth = (float) layout.getBounds().getWidth();
		messageWidth = layout.getAdvance();

		// center text
		// float ascent = metrics.getAscent();
		float ascent = layout.getAscent();
		// float descent = metrics.getDescent();
		float descent = layout.getDescent();

		// float x = (width - messageWidth) / 2;
		float originX = 50; // this is a kludge so that the start of "J" doesn't
		// get
		// chopped
		if (messageWidth > widthOfDisplayRegion) {
			originX = widthOfDisplayRegion - messageWidth;
		}

		g2.setPaint(getBackground());
		g2.fillRect(0, 0, widthOfDisplayRegion, heightOfDisplayRegion);

		// float height2 = metrics.getHeight();
		float height2 = (float) layout.getBounds().getHeight();
		float originY = (heightOfDisplayRegion + height2) / 2 - descent;
		originY = originY + descent / 2;

		g2.setPaint(getForeground());
		g2.drawString(this.getTheText(), originX, originY);

		if (shouldShowFontHints) {
			int PAD = 0;
			g2.setPaint(Color.MAGENTA); // Base lines
			drawLine(g2, originX - PAD, originY, originX + messageWidth + PAD,
					originY);
			g2.setPaint(Color.YELLOW); // vertical at start
			drawLine(g2, originX, originY + PAD, originX, originY - ascent
					- PAD);
			g2.setPaint(Color.GREEN); // Ascent line (aka the line at the tops
			// of the letters)
			drawLine(g2, originX - PAD, originY - ascent, originX
					+ messageWidth + PAD, originY - ascent);
			g2.setPaint(Color.RED); // Descent line
			drawLine(g2, originX - PAD, originY + descent, originX
					+ messageWidth + PAD, originY + descent);

			g2.setPaint(Color.GRAY); // Descent line
			// Rectangle2D bounds = layout.getBounds();
			g2.drawRect((int) originX, (int) originY, 3, 3);
			g2.setPaint(Color.CYAN); // Descent line
			g2.drawRect((int) (originX + layout.getVisibleAdvance()),
					(int) originY, 3, 3);
			// g2.drawRect((int) bounds.getX() - 1, (int) bounds.getY() - 1,
			// (int) bounds.getWidth() + 2, (int) bounds.getHeight() + 2);

			// System.out.println("paint's ascent and descent: " + ascent + " "
			// + descent + " for font: " + this.getFont());

			g2.setPaint(Color.ORANGE); // Descent line
			// layout.draw(g2, originX, originY);
			Rectangle2D bounds = layout.getBounds();
			bounds.setRect(bounds.getX() + originX, bounds.getY() + originY,
					bounds.getWidth(), bounds.getHeight());
			g2.draw(bounds);

		}
	}

	/**
	 * draws text left justified. If the width of the text exceeds the width of
	 * the display, then the text wraps to the left
	 * 
	 * @param g
	 */
	private void drawTextLeftJustified2(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setFont(getFont());
		int widthOfDisplayRegion = getSize().width;
		int heightOfDisplayRegion = getSize().height;

		FontRenderContext frc = g2.getFontRenderContext();
		// System.out.println(frc + " " + this.getTheText() + " " + getFont());
		// LineMetrics metrics =
		// this.getFont().getLineMetrics(this.getTheText(),
		// frc);

		TextLayout localLayout = new TextLayout(this.getTheText(), getFont(),
				frc);

		// System.out.println("To render: " + this.getTheText());
		// float messageWidth = (float) getFont().getStringBounds(
		// this.getTheText(), frc).getWidth();

		// messageWidth = (float) layout.getBounds().getWidth();
		float messageWidth = localLayout.getAdvance();

		// center text
		// float ascent = metrics.getAscent();
		float ascent = layout.getAscent();
		// float descent = metrics.getDescent();
		float descent = layout.getDescent();

		// float x = (width - messageWidth) / 2;
		float originX = 50; // this is a kludge so that the start of "J" doesn't
		// get
		// chopped
		if (messageWidth > widthOfDisplayRegion) {
			originX = widthOfDisplayRegion - messageWidth;
		}

		g2.setPaint(getBackground());
		g2.fillRect(0, 0, widthOfDisplayRegion, heightOfDisplayRegion);

		// float height2 = metrics.getHeight();
		float height2 = (float) layout.getBounds().getHeight();
		float originY = (heightOfDisplayRegion + height2) / 2 - descent;
		// originY = originY + descent / 2;
		// float magic = 72;// descent / 2.0f + 50;
		originY = heightOfDisplayRegion - (float) layout.getBounds().getMaxY();// layout.getBounds();

		float diff = (heightOfDisplayRegion - height2) / 2f;
		originY = originY - diff;

		// System.out.println("Descent= " + descent);
		// System.out.println("Display Height= " + heightOfDisplayRegion);
		// System.out.println("bounds height = " + height2);
		// System.out.println("magic = " + magic);
		// System.out.println("= " + layout.getBounds().getMaxY());
		g2.setPaint(getForeground());
		// g2.drawString(this.getTheText(), originX, originY);

		localLayout.draw(g2, originX, originY);

		if (false) {
			Image img = ElijahCompositionSet.imgA.getImage();
			int w = img.getWidth(this);
			int h = img.getHeight(this);
			// g2.drawImage(img, (int) originX, (int) originY, w, h, this);
			g2.drawImage(img, (int) originX, 0, w, h, this);
		}

		if (shouldShowFontHints) {
			int PAD = 0;
			g2.setPaint(Color.MAGENTA); // Base lines
			drawLine(g2, originX - PAD, originY, originX + messageWidth + PAD,
					originY);
			g2.setPaint(Color.YELLOW); // vertical at start
			drawLine(g2, originX, originY + PAD, originX, originY - ascent
					- PAD);
			g2.setPaint(Color.GREEN); // Ascent line (aka the line at the tops
			// of the letters)
			drawLine(g2, originX - PAD, originY - ascent, originX
					+ messageWidth + PAD, originY - ascent);
			g2.setPaint(Color.RED); // Descent line
			drawLine(g2, originX - PAD, originY + descent, originX
					+ messageWidth + PAD, originY + descent);

			g2.setPaint(Color.GRAY); // Descent line
			// Rectangle2D bounds = layout.getBounds();
			g2.drawRect((int) originX, (int) originY, 3, 3);
			g2.setPaint(Color.CYAN); // Descent line
			g2.drawRect((int) (originX + layout.getVisibleAdvance()),
					(int) originY, 3, 3);
			// g2.drawRect((int) bounds.getX() - 1, (int) bounds.getY() - 1,
			// (int) bounds.getWidth() + 2, (int) bounds.getHeight() + 2);

			// System.out.println("paint's ascent and descent: " + ascent + " "
			// + descent + " for font: " + this.getFont());

			g2.setPaint(Color.ORANGE); // Descent line
			// layout.draw(g2, originX, originY);
			Rectangle2D bounds = layout.getBounds();
			bounds.setRect(bounds.getX() + originX, bounds.getY() + originY,
					bounds.getWidth(), bounds.getHeight());
			g2.draw(bounds);

		}
	}

	/**
	 * draws text left justified. If the width of the text exceeds the width of
	 * the display, then the text wraps to the left
	 * 
	 * @param g
	 */
	private void drawTextCentered2(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setFont(getFont());
		int widthOfDisplayRegion = getSize().width;
		int heightOfDisplayRegion = getSize().height;

		FontRenderContext frc = g2.getFontRenderContext();
		// System.out.println(frc + " " + this.getTheText() + " " + getFont());
		// LineMetrics metrics =
		// this.getFont().getLineMetrics(this.getTheText(),
		// frc);

		TextLayout localLayout = new TextLayout(this.getTheText(), getFont(),
				frc);

		// System.out.println("To render: " + this.getTheText());
		// float messageWidth = (float) getFont().getStringBounds(
		// this.getTheText(), frc).getWidth();

		// messageWidth = (float) layout.getBounds().getWidth();
		float messageWidth = localLayout.getAdvance();

		// center text
		// float ascent = metrics.getAscent();
		float ascent = layout.getAscent();
		// float descent = metrics.getDescent();
		float descent = layout.getDescent();

		// float x = (width - messageWidth) / 2;
		float originX = 50; // this is a kludge so that the start of "J" doesn't
		originX = (widthOfDisplayRegion - messageWidth) / 2;

		// get
		// chopped
		if (messageWidth > widthOfDisplayRegion) {
			originX = widthOfDisplayRegion - messageWidth;
		}

		g2.setPaint(getBackground());
		g2.fillRect(0, 0, widthOfDisplayRegion, heightOfDisplayRegion);

		// float height2 = metrics.getHeight();
		float height2 = (float) layout.getBounds().getHeight();
		float originY = (heightOfDisplayRegion + height2) / 2 - descent;
		// originY = originY + descent / 2;
		// float magic = 72;// descent / 2.0f + 50;
		originY = heightOfDisplayRegion - (float) layout.getBounds().getMaxY();// layout.getBounds();

		float diff = (heightOfDisplayRegion - height2) / 2f;
		originY = originY - diff;

		// System.out.println("Descent= " + descent);
		// System.out.println("Display Height= " + heightOfDisplayRegion);
		// System.out.println("bounds height = " + height2);
		// System.out.println("magic = " + magic);
		// System.out.println("= " + layout.getBounds().getMaxY());
		// g2.drawString(this.getTheText(), originX, originY);

		// Image img = ElijahCompositionSet.imgA.getImage();
		Image img = ElijahCompositionSet.getRightImage(this.getTheText());
		int w = img.getWidth(this);
		int h = img.getHeight(this);
		// g2.drawImage(img, (int) originX, (int) originY, w, h, this);

		if (IS_IMAGE_ONLY) {
			g2.drawImage(img, (int) originX - w / 2, h / 2, w, h, this);
		} else if (IS_IMAGE_AND_TEXT) {
			// originX = 50;
			originY -= 200;
			g2.drawImage(img, (int) originX - w / 2 - 50, h / 2, w, h, this);
			g2.setPaint(getForeground());
			g2.setPaint(Color.black);
			localLayout.draw(g2, originX + 50, originY + 20);
		}

		if (shouldShowFontHints) {
			int PAD = 0;
			g2.setPaint(Color.MAGENTA); // Base lines
			drawLine(g2, originX - PAD, originY, originX + messageWidth + PAD,
					originY);
			g2.setPaint(Color.YELLOW); // vertical at start
			drawLine(g2, originX, originY + PAD, originX, originY - ascent
					- PAD);
			g2.setPaint(Color.GREEN); // Ascent line (aka the line at the tops
			// of the letters)
			drawLine(g2, originX - PAD, originY - ascent, originX
					+ messageWidth + PAD, originY - ascent);
			g2.setPaint(Color.RED); // Descent line
			drawLine(g2, originX - PAD, originY + descent, originX
					+ messageWidth + PAD, originY + descent);

			g2.setPaint(Color.GRAY); // Descent line
			// Rectangle2D bounds = layout.getBounds();
			g2.drawRect((int) originX, (int) originY, 3, 3);
			g2.setPaint(Color.CYAN); // Descent line
			g2.drawRect((int) (originX + layout.getVisibleAdvance()),
					(int) originY, 3, 3);
			// g2.drawRect((int) bounds.getX() - 1, (int) bounds.getY() - 1,
			// (int) bounds.getWidth() + 2, (int) bounds.getHeight() + 2);

			// System.out.println("paint's ascent and descent: " + ascent + " "
			// + descent + " for font: " + this.getFont());

			g2.setPaint(Color.ORANGE); // Descent line
			// layout.draw(g2, originX, originY);
			Rectangle2D bounds = layout.getBounds();
			bounds.setRect(bounds.getX() + originX, bounds.getY() + originY,
					bounds.getWidth(), bounds.getHeight());
			g2.draw(bounds);

		}
	}

	private void drawLine(Graphics2D g2, double x0, double y0, double x1,
			double y1) {
		Shape line = new java.awt.geom.Line2D.Double(x0, y0, x1, y1);
		g2.draw(line);
	}

	public String getTheText() {
		return theText;
	}

	public void setTheText(String theText) {
		// System.out.println("updating theText: " + theText);
		this.theText = theText;
	}

	public Font getFont() {
		if (this.font == null) {
			return null;
		} else {
			return this.font;
		}
	}

	public void setFont(Font font) {
		if (IS_VERBOSE)
			System.out.println(this.getClass().getName()
					+ " Updating the font: " + font);

		// kludge for elijah
		// font = font.deriveFont(200f);
		this.font = font;
	}

	public Dimension getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}

	/**
	 * This method automatically adds the caret to the end of the displayed
	 * gloss
	 * 
	 * @param str
	 */
	public void setDisplayWithAppropriateSubPortionOfGloss(String str) {
		this.setTheText(getDisplayablePortion(str));
	}

	public void resetTheText() {
		this.setTheText("");
		currentCaretPosition = 0;

		// setTheText("");
		// setDisplayWithAppropriateSubPortionOfGloss(getTheText() +
		// DEFAULT_CARET);
		// theTextArea.setText(getDisplayablePortion(theGloss + DEFAULT_CARET));

	}

	public void insertStringImmediatelyAfterCaret(String str) {
		// String currContents = theTextArea.getText();
		// String newContents = currContents.substring(0,
		// currContents.length() - 1)
		// + str + currContents.substring(currContents.length() - 1);

		setTheText(insertSubstringAtGivenPosition(getTheText(), str,
				currentCaretPosition));
		currentCaretPosition = currentCaretPosition + str.length();
		// System.out.println(currentCaretPosition);
		// setDisplayWithAppropriateSubPortionOfGloss(getTheText() +
		// DEFAULT_CARET);
		// theTextArea.setText(getDisplayablePortion(theGloss + DEFAULT_CARET));
	}

	private String insertSubstringAtGivenPosition(String theString,
			String theStringToBeInserted, int position) {
		String theNewString;
		theNewString = theString.substring(0, position) + theStringToBeInserted
				+ theString.substring(position);
		return theNewString;
	}

	private String removeCharacterAtGivenPosition(String theString, int position) {
		String theNewString;
		System.out.println("theString: " + theString + " remove at position: "
				+ position);
		theNewString = theString.substring(0, position - 1)
				+ theString.substring(position);
		return theNewString;
	}

	private String getDisplayablePortion(String str) {
		// int glossWidth =
		// this.getGraphics().getFontMetrics().stringWidth(str);
		// System.out.println("gloss width: " + glossWidth + " avail width: "
		// + this.getWidth());
		// while (glossWidth > this.getWidth()) {
		// str = str.substring(1);
		// glossWidth = this.getGraphics().getFontMetrics().stringWidth(str);
		// System.out.println("gloss width: " + glossWidth + " avail width: "
		// + this.getWidth());
		// }
		return str;
	}

	public void removeCharacterImmediatelyBeforeCaret() {
		if (getTheText().length() > 0) {
			setTheText(removeCharacterAtGivenPosition(getTheText(),
					currentCaretPosition));
			currentCaretPosition = currentCaretPosition - 1;
			setDisplayWithAppropriateSubPortionOfGloss(getTheText()
					+ DEFAULT_CARET);
		}
		repaint();
	}

	public void setDisplayFontAsLargesAsPossible(
			List<SourceSymbol> sourceSymbolSet) {

		int availableHeight = this.getSize().height;
//		int availableHeight = this.screenSize.height;
		availableHeight = availableHeight - INSET_PADDING * 2;
		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName()
					+ " Derive max height for display of font: "
					+ availableHeight);
		}
		// int availableHeight = this.availableRealEstateForGlossWidget
		// .getHeight();
		// Font optFont = this.getFont().deriveFont(
		// (float) this.getMaxPointSize((int) Math.ceil(availableHeight),
		// sourceSymbolSet));
		Float optimalFontSize = this.getMaxPointSizeVersion3(availableHeight,
				sourceSymbolSet);
		Font optFont = null;
		if (IS_IMAGE_ONLY) {
			optimalFontSize = 1f;
		} else if (IS_IMAGE_AND_TEXT) {
			optimalFontSize = 150f;
		}
		if (optimalFontSize != null) {
			optFont = this.getFont().deriveFont(optimalFontSize);
			if (IS_VERBOSE) {
				System.out.println("Optimal Font for Display is: " + optFont);
			}
			// System.out.println("Baseline: " + optFont.getBaselineFor('Q'));
			this.setFont(optFont);
		}
	}

	/**
	 * Currently the most efficient implementation - uses TextLayout which is
	 * SLOOOOW, but with interpolation search
	 * 
	 * 
	 * @param allowableHeightPerElement
	 * @param sourceSymbolSet
	 * @return
	 */
	public float getMaxPointSizeVersion3(int allowableHeightPerElement,
			List<SourceSymbol> sourceSymbolSet) {
		String exemplarString = "";
		for (SourceSymbol s : sourceSymbolSet) {
			if ((s.getVOCACommand() instanceof AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand)
					&& (!s
   							.equals(JIndirectSelectionButton.VK_SPACE_SYMBOL_SIMPLE))) {
				System.out.println(exemplarString);
				exemplarString = exemplarString + s.getTextLabel();
			}
		}

		float minFontPointSize = 1f;
		float maxFontPointSize = 1000f;
		float theFontSize = (maxFontPointSize - minFontPointSize) / 2;

		Font f = getFont().deriveFont(theFontSize);
		Graphics2D g2 = (Graphics2D) this.getGraphics();

		while ((g2 != null) && (f != null)
				&& (maxFontPointSize - minFontPointSize >= 0.5)) {
			f = f.deriveFont(theFontSize);
			FontRenderContext frc = g2.getFontRenderContext();
			layout = new TextLayout(exemplarString, f, frc);
			bounds = layout.getBounds();
			double height = layout.getBounds().getHeight();
			double width = layout.getBounds().getWidth();
//			double width = this.getScreenSize().getWidth();
//			bounds.setFrame(0, 0, width, height);
			if (IS_VERBOSE) {
				System.out
						.println("max height of any source symbol under font of point size: "
								+ f.getSize()
								+ " is "
								+ height
								+ " for display height: "
								+ allowableHeightPerElement);
			}
			if (height == allowableHeightPerElement) {
				return theFontSize;
			} else {
				if (height < allowableHeightPerElement) {
					minFontPointSize = theFontSize;
				} else {
					maxFontPointSize = theFontSize;
				}
				theFontSize = Math.min(maxFontPointSize, Math.max(
						minFontPointSize, theFontSize
								* (float) allowableHeightPerElement
								/ (float) height));
			}
		}
		
		System.out.println("Did we get stuck in an infinite loop?");
		return theFontSize;

	}

	public int getMaxPointSizeVersion2(int allowableHeightPerElement,
			List<SourceSymbol> sourceSymbolSet) {
		String exemplarString = "";
		for (SourceSymbol s : sourceSymbolSet) {
			if ((s.getVOCACommand() instanceof AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand)
					&& (!s
							.equals(JIndirectSelectionButton.VK_SPACE_SYMBOL_SIMPLE))) {

				exemplarString = exemplarString + s.getTextLabel();
			}
		}

		Font f = getFont().deriveFont(1f);
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		FontRenderContext frc = g2.getFontRenderContext();
		layout = new TextLayout(exemplarString, f, frc);
		double height = layout.getBounds().getHeight();
		if (IS_VERBOSE) {
			System.out
					.println("max height of any source symbol under font of point size: "
							+ f.getSize()
							+ " is "
							+ height
							+ " for display height: "
							+ allowableHeightPerElement);
		}
		while (height < allowableHeightPerElement) {
			f = f.deriveFont((float) f.getSize() + 1);
			layout = new TextLayout(exemplarString, f, frc);
			height = layout.getBounds().getHeight();
			if (IS_VERBOSE) {
				System.out
						.println("max height of any source symbol under font of point size: "
								+ f.getSize()
								+ " is "
								+ height
								+ " for display height: "
								+ allowableHeightPerElement);
			}
		}
		f = f.deriveFont((float) f.getSize() - 1);

		layout = new TextLayout(exemplarString, f, frc);
		bounds = layout.getBounds();

		return f.getSize();
	}

	public int getMaxPointSize(int allowableHeightPerElement,
			List<SourceSymbol> sourceSymbolSet) {
		// start with a tiny font and enlarge until it is maximal
		Font f = getFont().deriveFont(1f);

		int currMaxDescent = getLargestBaselineDrop(sourceSymbolSet, this
				.getFontMetrics(f));
		int currMaxAscent = getLargestBaselineAscent(sourceSymbolSet, this
				.getFontMetrics(f));
		int currMaxHeight = Math.abs(currMaxDescent) + Math.abs(currMaxAscent);
		// while (currMaxHeight < allowableHeightPerElement) {
		while (currMaxHeight < allowableHeightPerElement) {
			f = f.deriveFont((float) f.getSize() + 1);
			currMaxDescent = getLargestBaselineDrop(sourceSymbolSet, this
					.getFontMetrics(f));
			currMaxAscent = getLargestBaselineAscent(sourceSymbolSet, this
					.getFontMetrics(f));
			currMaxHeight = Math.abs(currMaxDescent) + Math.abs(currMaxAscent);
			// System.out
			// .println("max width of any source symbol under font of point size: "
			// + f.getSize() + " is " + currMaxWidth);
			// System.out
			// .println("max height of any source symbol under font of point size: "
			// + f.getSize()
			// + " is "
			// + currMaxHeight
			// + " for display height: "
			// + allowableHeightPerElement);
		}
		f = f.deriveFont((float) f.getSize() - 1);

		// float newPointSize = 0;
		// newPointSize = (float) (Math.abs(currMaxAscent));
		// newPointSize = (float) (Math.abs(currMaxAscent) + Math
		// .abs(currMaxDescent));
		// f = f.deriveFont(newPointSize);
		return f.getSize();
	}

	private int getLargestBaselineAscent(List<SourceSymbol> sourceSymbolSet,
			FontMetrics fm) {
		FontRenderContext frc = ((Graphics2D) this.getGraphics())
				.getFontRenderContext();
		Float currLargestAscent = null;
		SourceSymbol symWithLargestAscent = null;

		for (SourceSymbol s : sourceSymbolSet) {
			if (s.getVOCACommand() instanceof AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand
					&& (!s.equals(JIndirectSelectionButton.VK_SPACE_SYMBOL))) {
				// TextLayout layout = new TextLayout(s.getTextLabel(), fm
				// .getFont(), metrics);
				// Rectangle2D bounds = layout.getBounds();
				// double currAscent = bounds.getMinY();
				LineMetrics metrics = fm.getFont().getLineMetrics(
						s.getTextLabel(), frc);
				float currAscent = metrics.getAscent();

				// ascent is measured as a -ve displacement vertically down from
				// top right hand corner origin (0,0), so the smaller the
				// number, the better
				// no, now ascent is from bottom right hand corner???
				if (currLargestAscent == null || currLargestAscent < currAscent) {
					currLargestAscent = currAscent;
					symWithLargestAscent = s;
				}
			}
		}
		// System.out.println("Largest baseline ascent for "
		// + symWithLargestAscent + " is " + currLargestAscent
		// + " for font: " + fm.getFont());

		return (int) Math.floor(currLargestAscent);
	}

	/**
	 * PRE - this component has already been drawn, otherwise baseline cannot be
	 * derived without a font rendered context
	 * 
	 * @param sourceSymbolSet
	 * @param fm
	 * @return
	 */
	private int getLargestBaselineDrop(List<SourceSymbol> sourceSymbolSet,
			FontMetrics fm) {
		FontRenderContext frc = ((Graphics2D) this.getGraphics())
				.getFontRenderContext();
		Float currLargestDescent = null;
		SourceSymbol symWithLargestDrop = null;
		for (SourceSymbol s : sourceSymbolSet) {
			if ((s.getVOCACommand() instanceof AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand)
					&& (!s.equals(JIndirectSelectionButton.VK_SPACE_SYMBOL))) {

				// TextLayout layout = new TextLayout(s.getTextLabel(), fm
				// .getFont(), frc);
				// Rectangle2D bounds = layout.getBounds();
				// double currDescent = bounds.getMaxY();

				LineMetrics metrics = fm.getFont().getLineMetrics(
						s.getTextLabel(), frc);
				float currDescent = metrics.getDescent();

				// this test determines whether character drops below baseline,
				// values above zero are in the lower quadrants of the
				// co-ordinate
				// space
				// the larger the +ve value, the bigger the descent

				if (currLargestDescent == null
						|| currLargestDescent > currDescent) {
					currLargestDescent = currDescent;
					symWithLargestDrop = s;
					// System.out.println("baseline drop for " +
					// s.getTextLabel()
					// + " is " + currDescent + " max baseline is: "
					// + currLargestDescent);
				}

				// System.out.println("Bounding box for " + s.getTextLabel() +
				// " "
				// + tmpMin + " " + tmpMax);
			}
		}
		// System.out.println("Largest baseline drop for " + symWithLargestDrop
		// + " is " + currLargestDescent + " for font: " + fm.getFont());
		return (int) Math.ceil(currLargestDescent);
	}

	public void repositionCaret(int newPosition) {
		System.out.println("Caret repositioned to index: " + newPosition
				+ " length of string: " + this.getTheText().length());
		currentCaretPosition = newPosition;
	}

	public void clearGloss() {
		setTheText("");
		currentCaretPosition = 0;
	}

	public void showFontHints() {
		shouldShowFontHints = true;
	}

}
