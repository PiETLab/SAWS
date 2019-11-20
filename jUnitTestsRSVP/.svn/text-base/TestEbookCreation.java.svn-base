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
public class TestEbookCreation extends TestCase {

	/**
	 * This runs before each test in this test case (a test case can run
	 * multiple tests; Each test runs in its own fixture)
	 * 
	 */
	protected void setUp() {
		System.out.println("**************************");
	}

	/**
	 * In this test, check to see that we can tokenize an already-chunked
	 * string.
	 * 
	 */
	public void test2() {
		String s1 = "Their/PRP$/B-NP weapons/NNS/I-NP also/RB/B-ADVP resembled/VBD/B-VP those/DT/B-NP used/VBN/B-VP by/IN/B-PP mankind/JJ/B-ADJP ;/:/O we/PRP/B-NP hear/VBP/B-VP of/IN/B-PP spears/NNS/B-NP ,/,/O shields/NNS/B-NP ,/,/O helmets/VBZ/B-VP ,/,/O bows/NNS/B-NP and/CC/O arrows/VBZ/B-NP ,/,/B-PP &/CC/I-PP c./UH/B-INTJ ,/,/O being/VBG/B-VP employed/VBN/I-VP by/IN/B-PP the/DT/B-NP gods/NNS/I-NP ././O"
				+ "\n"
				+ "Each/DT/B-NP deity/NN/I-NP possessed/VBD/B-VP a/DT/B-NP beautiful/JJ/I-NP chariot/NN/I-NP ,/,/O which/WDT/B-NP ,/,/O drawn/VBN/B-VP by/IN/B-PP horses/NNS/B-NP or/CC/O other/JJ/B-NP animals/NNS/I-NP of/IN/B-PP celestial/JJ/B-NP breed/NN/I-NP ,/,/O conveyed/VBD/B-VP them/PRP/B-NP rapidly/RB/B-ADVP over/IN/B-PP land/NN/B-NP and/CC/I-NP sea/NN/I-NP according/VBG/B-PP to/TO/B-PP their/PRP$/B-NP pleasure/NN/I-NP ././O";
		EBook eBook = new EBook(s1);
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
