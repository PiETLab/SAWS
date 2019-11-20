package driverApps_RSVP;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.StyleContext;

import org.apache.fop.hyphenation.Hyphenation;
import org.apache.fop.hyphenation.HyphenationException;
import org.apache.fop.hyphenation.HyphenationTree;

import coreRSVP.RSVPController;
import coreRSVP.RSVPDisplayer;

import sourceSymbolSet.MeaganCompositionSet;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

import RSVP.EBook;
import RSVP.EbookChunkedTextFile;
import RSVP.LexicalToken;
import RSVP.EbookTextFile;
import RSVP.TwoLevelTokenizer;

//-mx1024M
public class fontSizeAnalysisDriver {

	static TwoLevelTokenizer tok;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Font displayFont;
		displayFont = new Font("Serif", Font.PLAIN, 400);
		RSVPDisplayer frame = new RSVPDisplayer(displayFont);

		SourceSymbolSet set = new MeaganCompositionSet();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setTitle(frame.getClass().getName());
		// frame.pack();
		// frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		// frame.setVisible(false); // alternative: frame.show()

		Font[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAllFonts();

		StyleContext context = StyleContext.getDefaultStyleContext();

		// Graphics g =
		// GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().

		// for (int i = 0; i < fontList.length; ++i) {
		for (int i = 0; i < 1; ++i) {
			Font f = fontList[i];
			f = f.deriveFont(400f); // get the same font, but in a larger size
			FontMetrics fm = context.getFontMetrics(f);
			System.out.println(f);
			getMaxWidth(set.getAllSourceSymbols(), fm, frame.getGraphics());
			// System.out.println(fm.getStringBounds("H", frame.getGraphics()));
			// System.out.println(fm.getWidths());

		}

		// System.out
		// .println("Can the RSVP software find a way to display every word? ");
		//
		// // displayFont = new Font("Serif", Font.PLAIN, 390);
		//
		//
		// EBook eBook;
		//
		// EbookTextFile textFile = new
		// EbookTextFile("eBooks/eBook-Berens07.txt");
		//
		// EbookChunkedTextFile chkFile = new EbookChunkedTextFile(
		// "eBooks/eBook-Berens07.txt.chk");
		// // EbookChunkedTextFile chkFile = new EbookChunkedTextFile(
		// // "eBooks/eBook-tmp1.txt.chk");
		//
		// eBook = new EBook(chkFile);
		// // eBook = new EBook(textFile, new Chunker());
		//
		// HyphenationTree ht = new HyphenationTree();
		// try {
		// ht.loadPatterns("en_US.xml");
		// } catch (HyphenationException e1) {
		// e1.printStackTrace();
		// }
		//
		// tok = new TwoLevelTokenizer(eBook);
		//
		// int displayWidth = frame.getWidth();
		// FontMetrics fm = frame.getFontMetrics(displayFont);
		//
		// int[] x = fm.getWidths();
		//
		// // check each word and determine whether it can be displayed
		// while (tok.hasNextToken()) {
		// // LexicalToken lexicalToken = tok.nextTokenStrict();
		// LexicalToken lexicalToken = tok.nextTokenNotStrict();
		//
		// // boolean canFit = lexicalToken.canFitOnDisplay(displayWidth, fm);
		// int maxPointSize = lexicalToken.getMaxPossiblePointSize(frame, fm
		// .getFont().getFamily(), fm.getFont().getStyle());
		// // boolean canFit2 = lexicalToken.canBeSubtokenizedToFitOnDisplay(
		// // displayWidth, fm, ht);
		// // if (!canFit2) {
		// // List<String> subtoks = lexicalToken
		// // .listifyByHyphenBreaksMaximalSize(frame.getWidth(), frame
		// // .getFontMetrics(displayFont), ht);
		// System.out.println(padToWidth(lexicalToken.toString()) + "\t"
		// + frame.getWidth()
		// // + "\t"
		// // + canFit2
		// + "\t" + lexicalToken.getDisplayWidth(fm)
		// // + frame.getFontMetrics(displayFont).stringWidth(
		// // lexicalToken.getWord()) + "\t" + maxPointSize
		// + "\t" + maxPointSize + "\t");
		//
		// // }
		// }
	}

	private static int getMaxWidth(Set<SourceSymbol> allSourceSymbols,
			FontMetrics fm, Graphics graphics) {
		for (SourceSymbol s : allSourceSymbols) {
			System.out.println(s.getTextLabel() + "\t"
					+ fm.getStringBounds(s.getTextLabel(), graphics));
		}
		return 0;
	}

	public static int getMaxWidth() {
		return 0;
	}

	private static String padToWidth(String string) {
		int toPad = 25 - string.length();
		while (toPad > 0) {
			string = string + " ";
			toPad--;
		}
		return string;
	}

}
