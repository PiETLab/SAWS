/*
 Copyright (C) 2006, Xuan-Hieu Phan
 
 Email:	hieuxuan@ecei.tohoku.ac.jp
 pxhieu@gmail.com
 URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
 
 Graduate School of Information Sciences,
 Tohoku University
 */

package crfchunker;

import java.io.Serializable;
import java.util.*;

public class FeaGeneration implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4628641271498598040L;

	public static List<String> tokenize(String sen) {
		List<String> results = new ArrayList<String>();

		StringTokenizer strTok = new StringTokenizer(sen, " \t\r\n");
		int len = strTok.countTokens();

		for (int i = 0; i < len; i++) {
			results.add(strTok.nextToken());
		}

		return results;
	}

	public static List<List<String>> feaGeneration(List sen, List posTags) {
		List<List<String>> seq = new ArrayList<List<String>>();

		for (int i = 0; i < sen.size(); i++) {
			seq.add(cpGen(sen, posTags, i));
		}

		return seq;
	}

	// context predicate generation for each position
	public static List<String> cpGen(List sen, List posTags, int i) {
		List<String> results = new ArrayList<String>();

		int seqLen = sen.size();

		if (i < 0 || i > seqLen - 1) {
			return results;
		}

		String[][] tokens = new String[seqLen][2];
		int j;
		for (j = 0; j < seqLen; j++) {
			tokens[j][0] = ((String) sen.get(j)).toLowerCase();
			tokens[j][1] = ((String) posTags.get(j)).toLowerCase();
		}

		// single word
		for (j = -2; j <= 2; j++) {
			if (i + j >= 0 && i + j < seqLen) {
				// 1 = w:
				String cp = "1:";
				cp += Integer.toString(j) + ":" + tokens[i + j][0];
				results.add(cp);
			}
		}

		// two consecutive words
		for (j = -1; j <= 0; j++) {
			if (i + j >= 0 && i + j + 1 < seqLen) {
				// 2 = ww:
				String cp = "2:";
				cp += Integer.toString(j) + Integer.toString(j + 1) + ":"
						+ tokens[i + j][0] + ":" + tokens[i + j + 1][0];
				results.add(cp);
			}
		}

		// single POS tag
		for (j = -2; j <= 2; j++) {
			if (i + j >= 0 && i + j < seqLen) {
				// 3 = p:
				String cp = "3:";
				cp += Integer.toString(j) + ":" + tokens[i + j][1];
				results.add(cp);
			}
		}

		// two consecutive POS tags
		for (j = -2; j <= 1; j++) {
			if (i + j >= 0 && i + j + 1 < seqLen) {
				// 4 = pp:
				String cp = "4:";
				cp += Integer.toString(j) + Integer.toString(j + 1) + ":"
						+ tokens[i + j][1] + ":" + tokens[i + j + 1][1];
				results.add(cp);
			}
		}

		// three consecutive POS tags
		for (j = -2; j <= 0; j++) {
			if (i + j >= 0 && i + j + 2 < seqLen) {
				// 5 = ppp:
				String cp = "5:";
				cp += Integer.toString(j) + Integer.toString(j + 1)
						+ Integer.toString(j + 2) + ":" + tokens[i + j][1]
						+ ":" + tokens[i + j + 1][1] + ":"
						+ tokens[i + j + 2][1];
				results.add(cp);

				/*
				 * if (j == -1) { // 6 = pppw: cp = "6:"; cp +=
				 * Integer.toString(j) + Integer.toString(j + 1) +
				 * Integer.toString(j + 2) + Integer.toString(j + 1) + ":" +
				 * tokens[i + j][1] + ":" + tokens[i + j + 1][1] + ":" +
				 * tokens[i + j + 2][1] + ":" + tokens[i + j + 1][0];
				 * results.add(cp); }
				 */
			}
		}

		/*
		 * // single POS tag and single word for (j = -1; j <= 0; j++) { if (i +
		 * j >= 0 && i + j < seqLen) { // 7 = pw: String cp = "7:"; cp +=
		 * Integer.toString(j) + Integer.toString(j) + ":" + tokens[i + j][1] +
		 * ":" + tokens[i + j][0]; results.add(cp); } }
		 *  // two consecutive POS tags and single word for (j = -1; j < 0; j++) {
		 * if (i + j >= 0 && i + j + 1 < seqLen) { // 8 = ppw: String cp = "8:";
		 * cp += Integer.toString(j) + Integer.toString(j + 1) +
		 * Integer.toString(j) + ":" + tokens[i + j][1] + ":" + tokens[i + j +
		 * 1][1] + ":" + tokens[i + j][0]; results.add(cp);
		 *  // 9 = ppw: cp = "9:"; cp += Integer.toString(j) +
		 * Integer.toString(j + 1) + Integer.toString(j + 1) + ":" + tokens[i +
		 * j][1] + ":" + tokens[i + j + 1][1] + ":" + tokens[i + j + 1][0];
		 * results.add(cp); } }
		 *  // two consecutive words and single POS tag for (j = -1; j < 0; j++) {
		 * if (i + j >= 0 && i + j + 1 < seqLen) { // 10 = pww: String cp =
		 * "10:"; cp += Integer.toString(j) + Integer.toString(j + 1) +
		 * Integer.toString(j) + ":" + tokens[i + j][0] + ":" + tokens[i + j +
		 * 1][0] + ":" + tokens[i + j][1]; results.add(cp);
		 *  // 11 = pww: cp = "11:"; cp += Integer.toString(j) +
		 * Integer.toString(j + 1) + Integer.toString(j + 1) + ":" + tokens[i +
		 * j][0] + ":" + tokens[i + j + 1][0] + ":" + tokens[i + j + 1][1];
		 * results.add(cp); } }
		 */

		return results;
	}

} // end of class FeaGenneration

