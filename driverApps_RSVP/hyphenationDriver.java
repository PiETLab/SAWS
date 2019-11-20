package driverApps_RSVP;

import java.awt.Font;
import java.awt.Frame;
import java.util.List;

import javax.swing.JFrame;

import org.apache.fop.hyphenation.Hyphenation;
import org.apache.fop.hyphenation.HyphenationException;
import org.apache.fop.hyphenation.HyphenationTree;

import coreRSVP.RSVPController;
import coreRSVP.RSVPDisplayer;

import RSVP.EBook;
import RSVP.EbookChunkedTextFile;
import RSVP.LexicalToken;
import RSVP.EbookTextFile;
import RSVP.TwoLevelTokenizer;

//-mx1024M
public class hyphenationDriver {

	static TwoLevelTokenizer tok;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Font displayFont = new Font("Serif", Font.PLAIN, 400);
		RSVPDisplayer frame = new RSVPDisplayer(displayFont);
		// frame.identifyController(rsvpController);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(frame.getClass().getName());
		frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true); // alternative: frame.show()

		EBook eBook;

		EbookTextFile textFile = new EbookTextFile("eBooks/eBook-Berens07.txt");

		// file = "";
		// EbookChunkedTextFile chkFile = new
		// EbookChunkedTextFile("eBooks/eBook-Berens07.txt.chk");
		EbookChunkedTextFile chkFile = new EbookChunkedTextFile(
				"eBooks/eBook-tmp1.txt.chk");

		eBook = new EBook(chkFile);
		// eBook = new EBook(textFile, new Chunker());
		HyphenationTree ht = new HyphenationTree();
		try {
			// ensure this file and hyphenation.dtd is in the invocation
			// directory
			ht.loadPatterns("en_US.xml");
		} catch (HyphenationException e1) {
			e1.printStackTrace();
			System.exit(0);
		}

		tok = new TwoLevelTokenizer(eBook);

		int minCharCount = 2;

		Hyphenation hyphenationPattern = null;

		while (tok.hasNextToken()) {
			// LexicalToken lexicalToken = tok.nextTokenStrict();
			LexicalToken lexicalToken = tok.nextTokenNotStrict();
			System.out
					.print(lexicalToken.toString()
							+ "\t"
							+ (frame.getFontMetrics(displayFont).stringWidth(
									lexicalToken.getWord()) <= frame.getWidth())
							+ "\t");
			hyphenationPattern = ht.hyphenate(lexicalToken.getWord(),
					minCharCount, minCharCount);
			System.out.print(hyphenationPattern);
			if (hyphenationPattern != null) {
				List<String> substrings = lexicalToken
						.listifyByHyphenBreaks(ht);
				System.out
						.println("\t"
								+ prettyPrint(hyphenationPattern
										.getHyphenationPoints())
								+ "\t"
								+ substrings
								+ "\t"
								+ lexicalToken.relistifyToFitDisplay(frame
										.getWidth(), frame
										.getFontMetrics(displayFont),
										substrings));
			} else
				System.out.println();

		}

	}

	private static String prettyPrint(int[] hyphenationPoints) {
		String s = null;
		for (int i : hyphenationPoints) {
			if (s == null) {
				s = "" + i;
			} else {
				s += ", " + i;
			}
		}
		return "[" + s + "]";
	}

}
