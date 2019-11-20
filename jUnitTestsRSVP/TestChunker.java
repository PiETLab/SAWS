package jUnitTestsRSVP;

import java.util.List;

import RSVP.EBook;
import RSVP.EbookChunker;
import RSVP.EbookTextFile;
import RSVP.RSVPChunkedSentence;
import junit.framework.TestCase;

/**
 * Can we take a plain text file and chunk it?
 * 
 * 
 * @author mb
 * 
 */
public class TestChunker extends TestCase {

	/**
	 * This runs before each test in this test case (a test case can run
	 * multiple tests; Each test runs in its own fixture)
	 * 
	 */
	protected void setUp() {
		System.out.println("**************************");
	}

	/**
	 * In this test, check to see that every line of the passed file gets
	 * printed to the console.
	 * 
	 */
	public void test1() {
		EbookTextFile textFile = new EbookTextFile("eBooks/eBook-Berens07.txt");
		List<String> lines = textFile.getAllLines();
		for (String s : lines) {
			System.out.println(s);
		}
		// EBook eBook = new EBook(textFile, new EbookChunker());
	}

	/**
	 * In this test, check to see that every line of the passed file gets
	 * chunked into sentences. Do a quick and easy test that the last token of
	 * each token sequence is a /./O
	 * 
	 */
	public void test2() {
		EbookTextFile textFile = new EbookTextFile("eBooks/eBook-Berens07.txt");
		EBook eBook = new EBook(textFile, new EbookChunker());
		List<RSVPChunkedSentence> lines = eBook.getChunkedSentences();
		for (RSVPChunkedSentence s : lines) {
			System.out.println(s);
			boolean isLastTokenPunctuation = s.getTokens().get(
					s.getTokens().size() - 1).isPunctuationSymbol();
			assertTrue(isLastTokenPunctuation);
			boolean isLastTokenPeriod = s.getTokens().get(
					s.getTokens().size() - 1).isPeriod();
			assertTrue(isLastTokenPeriod);
		}
	}
}
