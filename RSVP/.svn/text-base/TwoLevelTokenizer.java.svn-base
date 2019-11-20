package RSVP;

import java.util.List;
import java.util.NoSuchElementException;

import coreRSVP.RSVPDisplayer;

/**
 * This class implements a specialized tokenizer which operates over the leaves
 * of a two-level hierarchy. Let level 0 denote the root. All leaves belong to
 * level 1 internal nodes. No level 1 node is a leaf.
 * 
 * We use this data structure to represent a text document. The level 1 nodes
 * represent sentences, the level 2 nodes represent the words within the
 * sentence.
 * 
 * @author mb
 * 
 */
public class TwoLevelTokenizer {

	private EBook book;
	private List<RSVPChunkedSentence> chunkedSentences;

	// private int currentSentence;
	// private int currentWordWithinSentence;

	private int currIndexPosition;

	public TwoLevelTokenizer(EBook book) {
		this.book = book;
		chunkedSentences = book.getChunkedSentences();
		currIndexPosition = -1;
		// currentSentence = 0;
		// currentWordWithinSentence = -1;
	}

	public TwoLevelTokenizer() {
		this.book = null;
		chunkedSentences = null;
		currIndexPosition = -1;
		// currentSentence = 0;
		// currentWordWithinSentence = -1;
	}

	public int getCurrentIndexPosition() {
		return currIndexPosition;
	}

	public int getCurrentIndexPositionIntraToken() {
		int cumulativeTotal = 0;
		for (int i = 0; i <= currIndexPosition; i++) {
			LexicalToken tok = book.getToken(i);
			cumulativeTotal += tok.getWord().length() + 1;
		}
		cumulativeTotal -= 1;
		return cumulativeTotal;
	}

	/**
	 * gets the next lexical token from this tokenizer.
	 * 
	 * If the token is one that is immediately followed by a punctuation token,
	 * then this method returns an amalgam token that combines the two.
	 * 
	 * @return
	 */
	private LexicalToken nextTokenNotStrict(boolean isPeekAhead) {
		LexicalToken oneAhead = book.getToken(currIndexPosition + 1);
		LexicalToken twoAhead = book.getToken(currIndexPosition + 2);
		LexicalToken tmp = null;
		if (twoAhead == null) {
			tmp = oneAhead;
			if (isPeekAhead) {
			} else {
				currIndexPosition = currIndexPosition + 1;
			}
		} else {
			// System.out.println("XX " + oneAhead.toString());
			// System.out.println("XX " + twoAhead.toString());
			// System.out.println("XX " + oneAhead.getWord() + "\t"
			// + twoAhead.getWord() + "\t"
			// + twoAhead.isPunctuationSymbol());

			if (twoAhead.isPunctuationSymbol()) {
				if (isPeekAhead) {
					// tmp = oneAhead.clone();
					tmp = new LexicalToken(oneAhead.getWord()
							+ twoAhead.getWord(), "", "");
				} else {
					tmp = new LexicalToken(oneAhead.getWord()
							+ twoAhead.getWord(), "", "");
					currIndexPosition = currIndexPosition + 2;
				}
			} else {
				tmp = oneAhead;
				if (isPeekAhead) {
				} else {
					currIndexPosition = currIndexPosition + 1;
				}
			}
		}
		// System.out.println("ZZ " + tmp);
		return tmp;
	}

	/**
	 * This method returns the next token or the next two tokens if the second
	 * token after the first is a punctuation symbol.
	 * 
	 * @return
	 */
	public LexicalToken nextTokenNotStrict() {
		boolean isPeekAhead = false;
		return this.nextTokenNotStrict(isPeekAhead);
	}

	/**
	 * 
	 * @return the next single token, regardless of what it is
	 */
	public LexicalToken nextTokenStrict() {
		return nextTokenStrict(false);
	}

	/**
	 * 
	 * @return the next single token, regardless of what it is. If isPeekAhead
	 *         is true, then do not advance this tokenizer (e.g., a subsequent
	 *         call will return the same token again)
	 */
	public LexicalToken nextTokenStrict(boolean isPeekAhead) {
		int lookAheadIndex = currIndexPosition + 1;
		LexicalToken tmp = book.getToken(lookAheadIndex);
		if (isPeekAhead) {
		} else {
			currIndexPosition = lookAheadIndex;
		}
		return tmp;
	}

	// private boolean isFinalSentence() {
	// return isFinalSentence(currentSentence);
	// }

	// private boolean isFinalSentence(int theSentenceIndex) {
	// return theSentenceIndex == chunkedSentences.size() - 1;
	// }
	//
	// private boolean isFinalWord() {
	// return isFinalWord(currentSentence, currentWordWithinSentence);
	// }
	//
	// private boolean isFinalWord(int theSentenceIndex, int
	// theWordWithinSentence) {
	// return theWordWithinSentence == chunkedSentences.get(theSentenceIndex)
	// .getTokens().size() - 1;
	// }

	public boolean hasNextToken() {
		// System.out.println("s_" + currentSentence + " w_"
		// + currentWordWithinSentence + "(" + isFinalSentence()
		// + isFinalWord() + ")");
		return book.getToken(currIndexPosition + 1) != null;
	}

	public void restartSent() {

	}

	public void restartPrevSent() {

	}

	public void reinit() {
		currIndexPosition = -1;
		// if (currentWordWithinSentence == 0) {
		// if (currentSentence != 0)
		// currentSentence--;
		// } else {
		// currentWordWithinSentence--;
		// }
		//
	}

	/**
	 * Returns the next token, where a token is defined in a really lax way to
	 * include all of the subsequent tokens and punctuations that will fit on
	 * the display
	 * 
	 * @param displayer
	 * @return
	 */
	public LexicalToken nextTokenReallyLax(RSVPDisplayer displayer) {

		int SPACE_WIDTH = displayer.getFontMetrics().stringWidth(" ");
		LexicalToken tmp = nextTokenNotStrict();

		// see whether we can tack on tmp2

		LexicalToken tmp2 = null;
		// while (tmp.getDisplayWidth(displayer.getFontMetrics()) < displayer
		// .getWidth()) {
		if (hasNextToken()) {
			boolean isPeekAhead = true;
			tmp2 = this.nextTokenNotStrict(isPeekAhead);

			// if (tmp2.isPunctuationSymbol()) {
			// System.out.println(tmp);
			// tmp =
			if (tmp2 != null) {
				int potentialNewWidth = tmp.getDisplayWidth(displayer
						.getFontMetrics())
						+ SPACE_WIDTH
						+ tmp2.getDisplayWidth(displayer.getFontMetrics());
				// System.out.println(">> " + potentialNewWidth);
				if (potentialNewWidth < displayer.getWidth()) {
					tmp2 = this.nextTokenNotStrict();
					// System.out.println(">>>" + tmp2);
					tmp = new LexicalToken(tmp.getWord() + LexicalToken.SPACE
							+ tmp2.getWord(), "", "");
					// System.out.println(tmp);
					// advance the counters
				}
			}
			// }
		}
		// }
		// System.out
		// .println(tmp.getDisplayWidth(displayer.getFontMetrics()) <
		// displayer
		// .getWidth());
		return tmp;
	}
}
