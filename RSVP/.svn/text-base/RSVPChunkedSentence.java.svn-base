package RSVP;

import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class RSVPChunkedSentence {

	private String positionWithinMessage_FineGranularity;

	private String positionWithinMessage_CoarseGranularity;

	private String sentID;

	private String taggedSent;

	private List<String> taggedTokens;

	private List<LexicalToken> tokens;

	/**
	 * second and third args give position of this chunked sentence within the
	 * message (e.g., 3.6)
	 * 
	 * @param string
	 */
	public RSVPChunkedSentence(String string, int index, int maxIndex) {
		taggedSent = string;
		sentID = "" + index;
		positionWithinMessage_FineGranularity = "" + maxIndex + "." + index;
		positionWithinMessage_CoarseGranularity = getGranularity2(index,
				maxIndex);
		taggedTokens = this.tokenize(string);
		tokens = new Vector<LexicalToken>();
		int tokenIndex = 0;
		for (String s : taggedTokens) {
			LexicalToken lt = new LexicalToken(s);
			lt.addUniqueIdentifier(index, tokenIndex);
			tokens.add(lt);
			tokenIndex++;
			// System.out.println(tokens.get(tokens.size() - 1));
		}

	}

	private String getGranularity2(int index, int maxIndex) {
		String positionCode = "";
		if ((maxIndex == 1) || (maxIndex == 2)) {
			positionCode = getPositionWithinMessageGranularity1();
		} else {
			if (index == 1) {
				positionCode = "n." + index;
			} else if (index > 1 && index < maxIndex) {
				positionCode = "n.middle";
			} else if (index == maxIndex) {
				positionCode = "n.n";
			}
		}
		return positionCode;
	}

	/**
	 * Count number of characters in total
	 * 
	 * @return
	 */
	public int getNumChars() {
		int count = 0;
		for (LexicalToken t : getTokens()) {
			if (!t.isPunctuationSymbol()) {
				count += t.getWord().length();
			}
		}
		return count;
	}

	/**
	 * Count every token excluding punctuation tokens
	 * 
	 * @return
	 */
	public int getNumLexicalTokens() {
		int count = 0;
		for (LexicalToken t : getTokens()) {
			if (!t.isPunctuationSymbol()) {
				count++;
			}
		}
		return count;
	}

	public String stripTags(String s) {
		String DELIM = "/";
		StringTokenizer tok = new StringTokenizer(s, DELIM);
		return tok.nextToken();
	}

	public List<String> tokenize(String s) {
		String DELIM = " ";
		StringTokenizer tok = new StringTokenizer(s, DELIM);
		List<String> taggedTokens = new Vector<String>();
		while (tok.hasMoreTokens()) {
			taggedTokens.add(tok.nextToken());
		}
		return taggedTokens;
	}

	public String getSentenceID() {
		return sentID;
	}

	public String getPositionWithinMessageGranularity1() {
		return positionWithinMessage_FineGranularity;
	}

	public String getPositionWithinMessageGranularity2() {
		return positionWithinMessage_CoarseGranularity;
	}

	/**
	 * Returns something in the following form:
	 * 
	 * Sorry/NNP/B-NP if/IN/B-SBAR I/PRP/B-NP 'm/VBP/B-VP being/VBG/I-VP
	 * OTT/WP$/I-VP about/IN/B-PP
	 * 
	 * @return
	 */
	public List<LexicalToken> getTokens() {
		return tokens;
	}

	public String toStringWithoutTags() {
		StringBuffer buf = new StringBuffer();
		for (LexicalToken t : getTokens()) {
			buf.append(t.getWord() + " ");
		}
		return buf.toString();
	}

	public String toStringLaTeX() {
		StringBuffer buf = new StringBuffer();
		for (LexicalToken t : getTokens()) {
			buf.append(LaTeXFix(t.getWord()) + " ");

			// // s will be of the form "Sorry/NNP/B-NP"
			// StringTokenizer tok = new StringTokenizer(s, DELIM);
			// // buf.append("\\begin{verbatim}");
			// buf.append(LaTeXFix(tok.nextToken()) + " ");
			// // buf.append("\\myss{");
			// // while (tok.hasMoreTokens()) {
			// // buf.append(DELIM + LaTeXFix(tok.nextToken()));
			// // }
			// // buf.append("} ");
		}
		// buf.append("\n ");
		return buf.toString();
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (LexicalToken t : getTokens()) {
			buf.append(t + " ");

			// // s will be of the form "Sorry/NNP/B-NP"
			// StringTokenizer tok = new StringTokenizer(s, DELIM);
			// // buf.append("\\begin{verbatim}");
			// buf.append(LaTeXFix(tok.nextToken()) + " ");
			// // buf.append("\\myss{");
			// // while (tok.hasMoreTokens()) {
			// // buf.append(DELIM + LaTeXFix(tok.nextToken()));
			// // }
			// // buf.append("} ");
		}
		// buf.append("\n ");
		return buf.toString();
	}

	static private String LaTeXFix(String s) {
		String tmp = s;
		tmp = tmp.replaceAll("\\$", "\\\\\\$");
		tmp = tmp.replaceAll("\\%", "\\\\\\%");
		tmp = tmp.replaceAll("\\#", "\\\\\\#");
		tmp = tmp.replaceAll("\\&", "\\\\\\&");
		tmp = tmp.replaceAll("\\_", "\\\\\\_");
		return tmp;
	}

	public String toStringWithTags() {
		return getTaggedSent();
	}

	public static String dataHeader() {
		StringBuffer buf = new StringBuffer();
		buf.append("Chunked Sentence");
		buf.append("\t" + "Sent ID");
		buf.append("\t" + "Sent Pos(I)");
		buf.append("\t" + "Sent Pos(II)");
		buf.append("\t" + "Size (LexTok)");
		buf.append("\t" + "Size (char)");
		return buf.toString();
	}

	public String data() {
		StringBuffer buf = new StringBuffer();
		buf.append(getTaggedSent());
		buf.append("\t" + getSentenceID());
		buf.append("\t" + getPositionWithinMessageGranularity1());
		buf.append("\t" + getPositionWithinMessageGranularity2());
		buf.append("\t" + getNumLexicalTokens());
		buf.append("\t" + getNumChars());
		return buf.toString();
	}

	public String getTaggedSent() {
		return taggedSent;
	}

	public LexicalToken get(int index) {
		return tokens.get(index);
	}
}
