package jUnitTestsRSVP;

import java.util.List;

import RSVP.EBook;
import RSVP.EbookChunker;
import RSVP.RSVPChunkedSentence;
import junit.framework.TestCase;

/**
 * Can we take a plain text file and chunk it?
 * 
 * 
 * @author mb
 * 
 */
public class TestChunker2 extends TestCase {

	/**
	 * This runs before each test in this test case (a test case can run
	 * multiple tests; Each test runs in its own fixture)
	 * 
	 */
	protected void setUp() {
		System.out.println("**************************");
	}

	/**
	 * In this test, check to see that we can chunk a passed string.
	 * 
	 */
	public void test1() {
		String s1 = "Here is a test sentence.  And here is another one.";
		String filename = "/Users/mb/Documents/workspace/Prj-LowVisionIndirectTextEntry/eBooks/junit.txt.chk";
		EBook eBook = new EBook(s1, new EbookChunker(), filename);
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
