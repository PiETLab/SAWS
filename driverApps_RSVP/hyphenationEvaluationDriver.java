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
public class hyphenationEvaluationDriver {

	static TwoLevelTokenizer tok;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out
				.println("Can the RSVP software find a way to display every word? ");

		Font displayFont;
		displayFont = new Font("Serif", Font.PLAIN, 400);
		displayFont = new Font("Serif", Font.PLAIN, 390);
		
		RSVPDisplayer frame = new RSVPDisplayer(displayFont);
		// frame.identifyController(rsvpController);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(frame.getClass().getName());
		frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(false); // alternative: frame.show()

		EBook eBook;

		EbookTextFile textFile = new EbookTextFile("eBooks/eBook-Berens07.txt");

		EbookChunkedTextFile chkFile = new EbookChunkedTextFile(
				"eBooks/eBook-Berens07.txt.chk");
		// EbookChunkedTextFile chkFile = new EbookChunkedTextFile(
		// "eBooks/eBook-tmp1.txt.chk");

		eBook = new EBook(chkFile);
		// eBook = new EBook(textFile, new Chunker());

		tok = new TwoLevelTokenizer(eBook);

		HyphenationTree ht = new HyphenationTree();
		try {
			ht.loadPatterns("en_US.xml");
		} catch (HyphenationException e1) {
			e1.printStackTrace();
		}

		// check each word and determine whether it can be displayed
		while (tok.hasNextToken()) {
			// LexicalToken lexicalToken = tok.nextTokenStrict();
			LexicalToken lexicalToken = tok.nextTokenNotStrict();

			boolean canFit = lexicalToken.canFitOnDisplay(frame.getWidth(),
					frame.getFontMetrics(displayFont));
			boolean canFit2 = lexicalToken.canBeSubtokenizedToFitOnDisplay(
					frame.getWidth(), frame.getFontMetrics(displayFont), ht);
			if (!canFit2) {
				List<String> subtoks = lexicalToken
						.listifyByHyphenBreaksMaximalSize(frame.getWidth(),
								frame.getFontMetrics(displayFont), ht);
				System.out.println(lexicalToken.toString()
						+ "\t"
						+ canFit2
						+ "\t"
						+ subtoks
						+ "\t"
						+ frame.getWidth()
						+ "\t"
						+ frame.getFontMetrics(displayFont).stringWidth(
								lexicalToken.getWord()));

			}
		}
	}
	
}
