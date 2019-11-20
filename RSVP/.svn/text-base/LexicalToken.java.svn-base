package RSVP;

import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.text.JTextComponent.KeyBinding;

import org.apache.fop.hyphenation.Hyphenation;
import org.apache.fop.hyphenation.HyphenationTree;

public class LexicalToken {

	private static final String HYPHEN = "-";

	private static final boolean IS_VERBOSE = false;

	public static final String SPACE = " ";

	private static final String PERIOD = ".";

	private static int MIN_HYPHENATION_CHAR_COUNT = 2;

	private String theWord;
	private String partOfSpeechTag;
	private String shallowParseTag;

	private String theAppendedPunctuationSymbol = "";

	private List<String> hyphenization;
	Iterator<String> hyphenizationIterator;

	private String DELIM = "/";
	// private String PUNCT_TAG = "/O";
	private String PUNCT_TAG = "O";

	private int sentIndex;

	private int tokenIndex;

	public LexicalToken(String s) {
		// s will be of the form "Sorry/NNP/B-NP"

		// System.out.println(s);
		StringTokenizer tok = new StringTokenizer(s, DELIM);
		theWord = tok.nextToken();
		partOfSpeechTag = tok.nextToken();
		shallowParseTag = tok.nextToken();
	}

	public LexicalToken(String theToken, String thePOSTag,
			String theShallowParseTag) {
		// s will be of the form "Sorry/NNP/B-NP"
		theWord = theToken;
		partOfSpeechTag = thePOSTag;
		shallowParseTag = theShallowParseTag;
	}

	public String getWord() {
		return theWord + theAppendedPunctuationSymbol;
	}

	public String toString() {
		return theWord + theAppendedPunctuationSymbol + "(" + partOfSpeechTag
				+ ")(" + shallowParseTag + ")";
	}

	public boolean isPunctuationSymbol() {
		return this.shallowParseTag.contains(PUNCT_TAG);
	}

//	/**
//	 * Causes the passed token to be amalgamated to this token as a punctuation
//	 * tag. The resulting amalgam retains the POS and shallow parse tag of this
//	 * token
//	 * 
//	 * @param tmp2
//	 * @return
//	 */
//	public LexicalToken addPunctuation(LexicalToken tmp2) {
//		theAppendedPunctuationSymbol = tmp2.getWord();
//		// theWord = theWord + tmp2.getWord();
//		// System.out.println(theWord);
//		return this;
//	}

	// /**
	// * Causes the passed token to be appended to this token, separated by a
	// * space. The resulting amalgam retains the POS and shallow parse tag of
	// * this token
	// *
	// * @param tmp2
	// * @return
	// */
	// public LexicalToken append(LexicalToken tmp2) {
	// theWord = this.getWord() + SPACE + tmp2.getWord();
	// // theAppendedPunctuationSymbol = tmp2.getWord();
	// // theWord = theWord + tmp2.getWord();
	// // System.out.println(theWord);
	// return this;
	// }

	/**
	 * Returns this token split into sub-strings such that the sub-strings
	 * correspond to sub-units that are delimited by hyphens, according to the
	 * hyphenation rules specified by the passed HyphenationTree. If there is no
	 * possible hyphenation, then this method returns null.
	 * 
	 * @return
	 */
	public List<String> listifyByHyphenBreaks(HyphenationTree ht) {
		List<String> theSubstrings = null;
		Hyphenation hyphenationPattern = ht.hyphenate(this.getWord(),
				MIN_HYPHENATION_CHAR_COUNT, MIN_HYPHENATION_CHAR_COUNT);
		// sample array: imag-i-na-tive [4, 5, 7]
		if (hyphenationPattern != null) {
			int[] hyphenationIndices = hyphenationPattern
					.getHyphenationPoints();
			String tmp;// = theWord;
			theSubstrings = new Vector<String>();
			tmp = theWord.substring(0, hyphenationIndices[0]);
			theSubstrings.add(tmp);
			for (int i = 1; i < hyphenationIndices.length; i++) {
				tmp = theWord.substring(hyphenationIndices[i - 1],
						hyphenationIndices[i]);
				theSubstrings.add(tmp);
			}
			tmp = theWord
					.substring(hyphenationIndices[hyphenationIndices.length - 1])
					+ theAppendedPunctuationSymbol;
			theSubstrings.add(tmp);
		}
		return theSubstrings;
	}

	/**
	 * Takes the list of substrings (each one corresponding to a hyphenized
	 * unit), and returns the list with the substrings amalgamated to so that
	 * each item is as large as possible but still fitting into the display.
	 * 
	 * If theSubstrings2 is null, then return null
	 * 
	 * @return
	 */
	public Iterator<String> relistifyToFitDisplay(int displayWidth,
			FontMetrics fm, List<String> wordSubunits) {
		List<String> resizedWordSubunits = new Vector<String>();

		if (wordSubunits == null) {
			resizedWordSubunits.add("*" + this.theWord
					+ this.theAppendedPunctuationSymbol);
		} else {
			resizedWordSubunits.add(wordSubunits.get(0));
			for (int i = 1; i <= wordSubunits.size() - 1; i++) {
				String prev = resizedWordSubunits.get(resizedWordSubunits
						.size() - 1);
				// // need special case - don't need hyphen for case in which i
				// is last index
				if (fm.stringWidth(prev + wordSubunits.get(i) + HYPHEN) <= displayWidth) {
					resizedWordSubunits.remove(resizedWordSubunits.size() - 1);
					resizedWordSubunits.add(prev + wordSubunits.get(i));
				} else if (i == wordSubunits.size() - 1
						&& fm.stringWidth(prev + wordSubunits.get(i)) <= displayWidth) {
					resizedWordSubunits.remove(resizedWordSubunits.size() - 1);
					resizedWordSubunits.add(prev + wordSubunits.get(i));
				} else {
					resizedWordSubunits.remove(resizedWordSubunits.size() - 1);
					resizedWordSubunits.add(prev + HYPHEN);
					resizedWordSubunits.add(wordSubunits.get(i));
				}
			}
		}
		return resizedWordSubunits.iterator();
	}

	/**
	 * Takes the list of substrings (each one corresponding to a word substring
	 * that is delimited by a word beginning, word end, or hyphen), and returns
	 * the list with the substrings amalgamated to so that each item is as large
	 * as possible but still fitting into the display. Uses a greedy algorithm.
	 * 
	 * If wordSubunits is null, then return null
	 * 
	 * @return
	 */
	// public List<String> relistifyByHyphenBreaksMaximalSize(int displayWidth,
	// FontMetrics fm, List<String> wordSubunits) {
	// List<String> resizedWordSubunits = null;
	//
	// if (wordSubunits == null) {
	// } else {
	// resizedWordSubunits = new Vector<String>();
	// resizedWordSubunits.add(wordSubunits.get(0));
	// for (int i = 1; i <= wordSubunits.size() - 1; i++) {
	// String prev = resizedWordSubunits.get(resizedWordSubunits
	// .size() - 1);
	// // // need special case - don't need hyphen for case in which i
	// // is last index
	// if (fm.stringWidth(prev + wordSubunits.get(i) + HYPHEN) <= displayWidth)
	// {
	// resizedWordSubunits.remove(resizedWordSubunits.size() - 1);
	// resizedWordSubunits.add(prev + wordSubunits.get(i));
	// } else if (i == wordSubunits.size() - 1
	// && fm.stringWidth(prev + wordSubunits.get(i)) <= displayWidth) {
	// resizedWordSubunits.remove(resizedWordSubunits.size() - 1);
	// resizedWordSubunits.add(prev + wordSubunits.get(i));
	// } else {
	// resizedWordSubunits.remove(resizedWordSubunits.size() - 1);
	// resizedWordSubunits.add(prev + HYPHEN);
	// resizedWordSubunits.add(wordSubunits.get(i));
	// }
	// }
	// }
	// return resizedWordSubunits;
	// }
	/**
	 * This method returns a list of substrings amalgamated to so that each item
	 * is as large as possible but still fitting into the display. Uses a greedy
	 * algorithm.
	 * 
	 * If wordSubunits is null, then return null
	 * 
	 * @return
	 */
	public List<String> listifyByHyphenBreaksMaximalSize(int displayWidth,
			FontMetrics fm, HyphenationTree ht) {

		List<String> resizedWordSubunits = null;

		List<String> wordSubunits = this.listifyByHyphenBreaks(ht);
		if (wordSubunits == null) {
			wordSubunits = this.listifyByHyphenBreaksIdiosyncratic(ht);
		}

		if (wordSubunits != null) {
			resizedWordSubunits = new Vector<String>();
			resizedWordSubunits.add(wordSubunits.get(0));
			for (int i = 1; i <= wordSubunits.size() - 1; i++) {
				String prev = resizedWordSubunits.get(resizedWordSubunits
						.size() - 1);
				// // need special case - don't need hyphen for case in which i
				// is last index
				if (fm.stringWidth(prev + wordSubunits.get(i) + HYPHEN) <= displayWidth) {
					resizedWordSubunits.remove(resizedWordSubunits.size() - 1);
					resizedWordSubunits.add(prev + wordSubunits.get(i));
				} else if (i == wordSubunits.size() - 1
						&& fm.stringWidth(prev + wordSubunits.get(i)) <= displayWidth) {
					resizedWordSubunits.remove(resizedWordSubunits.size() - 1);
					resizedWordSubunits.add(prev + wordSubunits.get(i));
				} else {
					resizedWordSubunits.remove(resizedWordSubunits.size() - 1);
					resizedWordSubunits.add(prev + HYPHEN);
					resizedWordSubunits.add(wordSubunits.get(i));
				}
			}
		}
		return resizedWordSubunits;
	}

	/**
	 * This method attempts to sub-tokenize this token by an idiosyncratic
	 * approach. The approach in which we attempt to hyphenate using the TeX
	 * rules has failed to produce a sub-tokenization. In this approach, we
	 * search for hyphens and if found, we break at those points.
	 * 
	 * @return
	 */
	private List<String> listifyByHyphenBreaksIdiosyncratic(HyphenationTree ht) {
		List<String> subtoks = new Vector<String>();

		String str = this.getWord();
		while (!str.equals("")) {
			// System.out.println(str);
			int pos = str.indexOf("-");
			if (pos != -1) {
				// System.out.println("hyphen found: " + pos + "\t substring:"
				// + str.substring(0, pos) + "/X/X");
				LexicalToken tokNew1 = new LexicalToken(str.substring(0, pos)
						+ "/X/X");
				// System.out.println("new tok: " + tokNew1.getWord());
				List<String> subtoks2 = tokNew1.listifyByHyphenBreaks(ht);
				// System.out.println("try to hyphenate sub-token: " +
				// subtoks2);
				if (subtoks2 != null) {
					for (String s : subtoks2) {
						subtoks.add(s);
						// System.out.println("sub-tokens so far: " + subtoks);
					}
				} else {
					subtoks.add(tokNew1.getWord());
					// System.out.println("sub-tokens so far: " + subtoks);
				}
				str = str.substring(pos + 1);
				// System.out.println("now parse this string: " + str);
			} else {
				LexicalToken tokNew1 = new LexicalToken(str + "/X/X");
				// System.out.println("new tok: " + tokNew1.getWord());
				List<String> subtoks2 = tokNew1.listifyByHyphenBreaks(ht);
				// System.out.println("try to hyphenate sub-token: " +
				// subtoks2);
				if (subtoks2 != null) {
					for (String s : subtoks2) {
						subtoks.add(s);
						// System.out.println("sub-tokens so far: " + subtoks);
					}
				} else {
					subtoks.add(tokNew1.getWord());
					// System.out.println("sub-tokens so far: " + subtoks);
				}
				str = "";
			}
		}
		return subtoks;
	}

	public boolean canFitOnDisplay(int displayWidth, FontMetrics fm) {
		// displayText2.setText(arg0);
		// if (IS_VERBOSE)
		// displayText2.setText("" + fm.stringWidth(string) + "/"
		// + this.getWidth());
		return fm.stringWidth(this.getWord()) <= displayWidth;
	}

	public boolean canBeSubtokenizedToFitOnDisplay(int displayWidth,
			FontMetrics fm, HyphenationTree ht) {
		boolean allFit = true;

		if (canFitOnDisplay(displayWidth, fm)) {
			return true;
		} else {
			List<String> subtoks = this.listifyByHyphenBreaksMaximalSize(
					displayWidth, fm, ht);
			if (subtoks != null) {
				for (String s : subtoks) {
					allFit = allFit && (fm.stringWidth(s) <= displayWidth);
				}
				return allFit;
			} else {
				return false;
			}
		}
	}

	/**
	 * Returns the first substring of this lexical token, which is sized to fit
	 * with the display.
	 * 
	 * Has side-effect of setting up iterator for this token that provides
	 * substrings, one at a time, where each substring is corresponds to a
	 * syllable or multi-syllable unit, each of which is sized to fit with the
	 * display.
	 * 
	 * @param displayWidth
	 * @param fm
	 * @param ht
	 * @return
	 */
	public String getFirstHyphenSubstring(int displayWidth, FontMetrics fm,
			HyphenationTree ht) {
		hyphenizationIterator = this.relistifyToFitDisplay(displayWidth, fm,
				this.listifyByHyphenBreaks(ht));
		return hyphenizationIterator.next();
	}

	public boolean hasMoreHyphenSubstring() {
		return hyphenizationIterator.hasNext();
	}

	public String getNextHyphenSubstring() {

		return hyphenizationIterator.next();
	}

	/**
	 * Determine the largest possible font point size for the passed font
	 * metrics such that this lexical token will fit in the given display width.
	 * If there is no such font point size, then this method returns null. (The
	 * smallest font point size considered is 8pt and the largest possible font
	 * size considered is 600).
	 * 
	 * This method requires the frame to be passed as an argument so that we can
	 * obtain the frame's font metrics, which in turn will tell us the display
	 * width of this lexical token for various font sizes.
	 * 
	 * @param frame
	 * @param fontFamily
	 *            the name of the font family (such as Serif, Sans-Serif, or
	 *            some other system-installed font)
	 * @param fontStyle
	 *            whether Font.ITALIC and/or Font.BOLD are on or off
	 *            (represented as a int)
	 * @return
	 */
	public Integer getMaxPossiblePointSize(JFrame frame, String fontFamily,
			int fontStyle) {
		// start with maxPointSize and test if string width is too large to fit
		// on display;
		// decrease and iterate until string width fits
		// String fontFamily = fm.getFont().getFamily();
		// int fontStyle = fm.getFont().getStyle();
		int fontSize = 600;

		FontMetrics fmTmp = frame.getFontMetrics(new Font(fontFamily,
				fontStyle, fontSize));
		while (!canFitOnDisplay(frame.getWidth(), fmTmp)) {
			fontSize = fontSize - 1;
			fmTmp = frame.getFontMetrics(new Font(fontFamily, fontStyle,
					fontSize));
		}
		return fontSize;
	}

	public int getDisplayWidth(FontMetrics fm) {
		return fm.stringWidth(this.getWord());
	}

	public LexicalToken clone() {
		String s = theWord + DELIM + partOfSpeechTag + DELIM + shallowParseTag;
		LexicalToken newOne = new LexicalToken(s);
		// System.out.println("&&&&" + s);
		return newOne;
	}

	public void addUniqueIdentifier(int sentIndex, int tokenIndex) {
		this.sentIndex = sentIndex;
		this.tokenIndex = tokenIndex;
	}

	public String getPOSTag() {
		return partOfSpeechTag;
	}

	public String getShallowParseTag() {
		return shallowParseTag;
	}

	public boolean isPeriod() {
		return getPOSTag().equals(PERIOD);
	}

}
