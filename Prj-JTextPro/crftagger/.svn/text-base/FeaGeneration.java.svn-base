/*
 Copyright (C) 2006, Xuan-Hieu Phan
 
 Email:	hieuxuan@ecei.tohoku.ac.jp
 pxhieu@gmail.com
 URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
 
 Graduate School of Information Sciences,
 Tohoku University
 */

package crftagger;

import java.io.Serializable;
import java.util.*;

public class FeaGeneration implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -948849098181923785L;

	public static List<String> tokenize(String sen) {
		List<String> results = new ArrayList<String>();

		StringTokenizer strTok = new StringTokenizer(sen, " \t\r\n");
		int len = strTok.countTokens();

		for (int i = 0; i < len; i++) {
			results.add(strTok.nextToken());
		}

		return results;
	}

	public static List<List<String>> feaGeneration(List sen) {
		List<List<String>> seq = new ArrayList<List<String>>();

		for (int i = 0; i < sen.size(); i++) {
			seq.add(cpGen(sen, i));
		}

		return seq;
	}

	// context predicate generation for each position
	public static List<String> cpGen(List seq, int i) {
		List<String> results = new ArrayList<String>();

		int j;
		int seqLen = seq.size();

		if (i < 0 || i > seqLen - 1) {
			return results;
		}

		// single word
		for (j = -2; j <= 2; j++) {
			if (i + j >= 0 && i + j < seqLen) {
				// 1 = w:
				String cp = "1:";
				cp += Integer.toString(j) + ":" + (String) seq.get(i + j);
				results.add(cp.toLowerCase());
			}
		}

		// prefixes
		for (j = 0; j <= 0; j++) {
			if (i + j >= 0 && i + j < seqLen) {
				String currentToken = (String) seq.get(i + j);

				int prefixLen = currentToken.length() - 2;
				if (prefixLen > 4) {
					prefixLen = 4;
				}

				for (int count = 1; count <= prefixLen; count++) {
					String prefix = currentToken.substring(0, count);
					// 2 = p:
					String cp = "2:";
					cp += Integer.toString(j) + ":" + prefix;
					results.add(cp);
				}
			}
		}

		// suffixes
		for (j = 0; j <= 0; j++) {
			if (i + j >= 0 && i + j < seqLen) {
				String currentToken = (String) seq.get(i + j);

				int suffixLen = currentToken.length() - 2;
				if (suffixLen > 4) {
					suffixLen = 4;
				}

				for (int count = 1; count <= suffixLen; count++) {
					String suffix = currentToken.substring(currentToken
							.length()
							- count, currentToken.length());
					// 3 = s:
					String cp = "3:";
					cp += Integer.toString(j) + ":" + suffix;
					results.add(cp);
				}
			}
		}

		// two consecutive words
		for (j = -1; j <= 0; j++) {
			if (i + j >= 0 && i + j + 1 < seqLen) {
				// 4 = ww:
				String cp = "4:";
				cp += Integer.toString(j) + ":" + Integer.toString(j + 1) + ":"
						+ (String) seq.get(i + j) + ":"
						+ (String) seq.get(i + j + 1);
				results.add(cp.toLowerCase());
			}
		}

		for (j = 0; j <= 0; j++) {
			if (i + j >= 0 && i + j < seqLen) {
				int k;

				String currentToken = (String) seq.get(i + j);
				int tokenLen = currentToken.length();

				boolean isAllCap = true;
				k = 0;
				while (k < tokenLen) {
					if (!(Character.isUpperCase(currentToken.charAt(k)))) {
						isAllCap = false;
						break;
					}
					k++;
				}

				if (isAllCap) {
					// 5 = i:allc
					String cp = "5:" + Integer.toString(j);
					results.add(cp);

					if (currentToken.endsWith("S")) {
						// 6 = i:allcs
						cp = "6:" + Integer.toString(j);
						results.add(cp);
					}
				}

				if (!isAllCap && Character.isUpperCase(currentToken.charAt(0))) {
					// 7 = i:fstc
					String cp = "7:" + Integer.toString(j);
					results.add(cp);

					String preToken = null;
					if (i + j > 0) {
						preToken = (String) seq.get(i + j - 1);
					}

					if (i + j == 0
							|| (i + j > 0 && preToken.compareTo("``") == 0)) {
						// 8 = i:fstsfstc
						cp = "8:" + Integer.toString(j);
						results.add(cp);

					} else {
						// 9 = i:nfstsfstc
						cp = "9:" + Integer.toString(j);
						results.add(cp);
					}

					if (currentToken.endsWith("s")) {
						// 10 = i:fstcs
						cp = "10:" + Integer.toString(j);
						results.add(cp);

						if (i + j == 0
								|| (i + j > 0 && preToken.compareTo("``") == 0)) {
							// 11 = i:fstsfstcs
							cp = "11:" + Integer.toString(j);
							results.add(cp);

						} else {
							// 12 = i:nfstsfstcs
							cp = "12:" + Integer.toString(j);
							results.add(cp);
						}
					}
				}

				boolean hasNumber = false;
				k = 0;
				while (k < tokenLen) {
					if (Character.isDigit(currentToken.charAt(k))) {
						hasNumber = true;
						break;
					}
					k++;
				}

				boolean isAllNumber = true;
				k = 0;
				while (k < tokenLen) {
					if (!(Character.isDigit(currentToken.charAt(k)))
							&& currentToken.charAt(k) != '.'
							&& currentToken.charAt(k) != ',') {
						isAllNumber = false;
						break;
					}
					k++;
				}
				if (!hasNumber) {
					isAllNumber = false;
				}

				if (isAllNumber) {
					// 13 = n:alln
					String cp = "13:" + Integer.toString(j);
					results.add(cp);
				}

				if (!isAllNumber && hasNumber) {
					// 14 = n:hasn
					String cp = "14:" + Integer.toString(j);
					results.add(cp);
				}

				boolean hasHyphen = false;
				k = 0;
				while (k < tokenLen) {
					if (currentToken.charAt(k) == '-') {
						hasHyphen = true;
						break;
					}
					k++;
				}

				if (hasHyphen) {
					// 15 = h:hyph
					String cp = "15:" + Integer.toString(j);
					results.add(cp);
				}
			}
		}

		return results;
	}

} // end of class FeaGeneration

