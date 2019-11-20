package jUnitTestsRSVP;

import java.awt.Font;
import java.awt.Frame;
import java.util.List;

import javax.swing.JFrame;

import coreRSVP.RSVPDisplayer;

import RSVP.EBook;
import RSVP.EbookChunkedTextFile;
import RSVP.LexicalToken;
import RSVP.RSVPChunkedSentence;
import RSVP.TwoLevelTokenizer;
import junit.framework.TestCase;

public class TestTokenizer extends TestCase {

	/**
	 * This runs before each test in this test case (a test case can run
	 * multiple tests; Each test runs in its own fixture)
	 * 
	 */
	protected void setUp() {
		System.out.println("**************************");
	}

	public void test1() {
		// for reference purposes, here is the sentence in the chunked file:
		// String s1 = "Here is a test sentence.  And here is another one.";
		String filename = "/Users/mb/Documents/workspace/Prj-LowVisionIndirectTextEntry/eBooks/junit.txt.chk";
		EbookChunkedTextFile chkFile = new EbookChunkedTextFile(filename);
		EBook eBook = new EBook(chkFile);
		TwoLevelTokenizer tok = new TwoLevelTokenizer(eBook);
		while (tok.hasNextToken()) {
			LexicalToken lexicalToken = tok.nextTokenStrict();
			System.out.println(lexicalToken.getWord() + "\t");
		}
	}

	public void test2() {
		// for reference purposes, here is the sentence in the chunked file:
		// String s1 = "Here is a test sentence.  And here is another one.";
		String filename = "/Users/mb/Documents/workspace/Prj-LowVisionIndirectTextEntry/eBooks/junit.txt.chk";
		EbookChunkedTextFile chkFile = new EbookChunkedTextFile(filename);
		EBook eBook = new EBook(chkFile);
		TwoLevelTokenizer tok = new TwoLevelTokenizer(eBook);
		while (tok.hasNextToken()) {
			LexicalToken lexicalToken = tok.nextTokenNotStrict();
			System.out.println(lexicalToken.getWord() + "\t");
		}
	}

	public void test3() {

		Font displayFont;
		displayFont = new Font("Serif", Font.PLAIN, 700);

		RSVPDisplayer frame = new RSVPDisplayer(displayFont);
		// frame.identifyController(rsvpController);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(frame.getClass().getName());
		frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(false); // alternative: frame.show()

		// for reference purposes, here is the sentence in the chunked file:
		// String s1 = "Here is a test sentence.  And here is another one.";
		String filename = "/Users/mb/Documents/workspace/Prj-LowVisionIndirectTextEntry/eBooks/junit.txt.chk";
		EbookChunkedTextFile chkFile = new EbookChunkedTextFile(filename);
		EBook eBook = new EBook(chkFile);
		TwoLevelTokenizer tok = new TwoLevelTokenizer(eBook);
		while (tok.hasNextToken()) {
			LexicalToken lexicalToken = tok.nextTokenReallyLax(frame);
			System.out.println(lexicalToken.getWord() + "\t");
		}
	}

}
