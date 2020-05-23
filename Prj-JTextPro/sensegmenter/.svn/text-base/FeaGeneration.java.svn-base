/*
 Copyright (C) 2006 by
 
 Xuan-Hieu Phan
 
 Email:	hieuxuan@ecei.tohoku.ac.jp
 pxhieu@gmail.com
 URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
 
 Graduate School of Information Sciences,
 Tohoku University
 */

package sensegmenter;

import java.io.*;
import java.util.*;
import jmaxent.*;

public class FeaGeneration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8692370306015561966L;

	public Classification classifier = null;

	public Map externalDict = null;

	public FeaGeneration(Classification classifier) {
		this.classifier = classifier;
		externalDict = new HashMap();

		init();
	}

	public void init() {
		// reading the external dictionary
		String filename = classifier.option.modelDir + File.separator
				+ "abbrlist.txt";
		BufferedReader fin = null;

		try {
			fin = new BufferedReader(new FileReader(filename));

			String line;
			while ((line = fin.readLine()) != null) {
				StringTokenizer strTok = new StringTokenizer(line, " \t\r\n");
				if (strTok.countTokens() <= 0) {
					continue;
				}

				String item = strTok.nextToken();
				externalDict.put(item.toLowerCase(), item.toLowerCase());
			}

		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	public String normalizeSen(String sen) {
		String res = "";

		StringTokenizer strTok = new StringTokenizer(sen, " \t\r\n");
		int len = strTok.countTokens();

		if (len == 0) {
			return res;
		}

		for (int i = 0; i < len; i++) {
			res += strTok.nextToken() + " ";
		}

		res = res.trim();

		/*
		 * if (!(res.endsWith(".") || res.endsWith("?") || res.endsWith("!") ||
		 * res.endsWith(".\"") || res.endsWith(".'") || res.endsWith(".''"))) {
		 * res += "."; }
		 */

		return res;
	}

	public List tokenize(String str) {
		List list = new ArrayList();

		StringTokenizer strTok = new StringTokenizer(str, " \t\r\n");
		int len = strTok.countTokens();
		for (int i = 0; i < len; i++) {
			list.add(strTok.nextToken());
		}

		return list;
	}

	public void feaGeneration(List tokens, List data, List majorIdxes,
			List minorIdxes) {
		data.clear();
		majorIdxes.clear();
		minorIdxes.clear();

		int i, j;

		for (i = 0; i < tokens.size(); i++) {
			String preToken = null;
			String token = null;
			String nexToken = null;

			token = (String) tokens.get(i);

			if (i > 0) {
				preToken = (String) tokens.get(i - 1);
			}
			if (i < tokens.size() - 1) {
				nexToken = (String) tokens.get(i + 1);
			}

			for (j = 0; j < token.length(); j++) {
				if (token.charAt(j) == '.' || token.charAt(j) == '?'
						|| token.charAt(j) == '!') {
					String cps = generateCps(preToken, token, nexToken, j);

					data.add(cps);
					majorIdxes.add(new Integer(i));
					minorIdxes.add(new Integer(j));
				}
			}
		}
	}

	public String generateCps(String preToken, String token, String nexToken,
			int pos) {
		String cps = "";

		// 01:tok=
		cps += " 01=" + token;
		// 02:tok-lower
		cps += " 02=" + token.toLowerCase();
		if (isFirstCap(token)) {
			// 03:tok-first-cap
			cps += " 03";
		}
		if (externalDict.containsKey(token.toLowerCase())) {
			// 04:tok-in-abbrlist
			cps += " 04";
		}
		if (containNumber(token)) {
			// 05:tok-has-num
			cps += " 05";
		}
		if (containLetter(token)) {
			// 06:tok-has-let
			cps += " 06";
		}
		if (containLetterAndDigit(token)) {
			// 07:tok-has-let-num
			cps += " 07";
		}
		if (isAllNumber(token)) {
			// 08:tok-is-all-num
			cps += " 08";
		}
		// 09:tok-countstop
		cps += " 09=" + Integer.toString(countStops(token));
		// 10:tok-countsign
		cps += " 10=" + Integer.toString(countSigns(token));

		String prefix = token.substring(0, pos);
		// 11:tok-pre
		cps += " 11=" + prefix;
		// 12:tok-pre-lower
		cps += " 12=" + prefix.toLowerCase();
		if (isFirstCap(prefix)) {
			// 13:tok-pre-first-cap
			cps += " 13";
		}

		String suffix = token.substring(pos + 1, token.length());
		// 14:tok-suf
		cps += " 14=" + suffix;
		// 15:tok-suf-lower
		cps += " 15=" + suffix.toLowerCase();
		if (isFirstCap(suffix)) {
			// 16:tok-suf-first-cap
			cps += " 16";
		}

		if (preToken != null) {
			// 17:pre-tok
			cps += " 17=" + preToken;
			// 18:pre-tok-lower
			cps += " 18=" + preToken.toLowerCase();
			if (isFirstCap(preToken)) {
				// 19:pre-tok-first-cap
				cps += " 19";
			}
			if (externalDict.containsKey(preToken.toLowerCase())) {
				// 20:pre-tok-in-abbrlist
				cps += " 20";
			}
			if (containNumber(preToken)) {
				// 21:pre-tok-has-num
				cps += " 21";
			}
			if (containLetter(preToken)) {
				// 22:pre-tok-has-let
				cps += " 22";
			}
			if (containLetterAndDigit(preToken)) {
				// 23:pre-tok-has-let-num
				cps += " 23";
			}
			if (isAllNumber(preToken)) {
				// 24:pre-tok-is-allnum
				cps += " 24";
			}
			// 25:pre-tok-countstop
			cps += " 25=" + Integer.toString(countStops(preToken));
			// 26:pre-tok-countsign
			cps += " 26=" + Integer.toString(countSigns(preToken));

		} else {
			// 27:pre-tok
			cps += " 27=null";
		}

		if (nexToken != null) {
			// 28:nex-tok
			cps += " 28=" + nexToken;
			// 29:nex-tok-lower
			cps += " 29=" + nexToken.toLowerCase();
			if (isFirstCap(nexToken)) {
				// 30:nex-tok-first-cap
				cps += " 30";
			}
			if (externalDict.containsKey(nexToken.toLowerCase())) {
				// 31:nex-tok-in-abbrlist
				cps += " 31";
			}

			if (nexToken.startsWith("\"") || nexToken.startsWith("''")
					|| nexToken.startsWith("``") || nexToken.startsWith("'")
					|| nexToken.startsWith("`")) {
				cps += " 39";
			}

			if (isFirstCap1(nexToken)) {
				cps += " 40";
			}

			if (containNumber(nexToken)) {
				// 32:nex-tok-has-num
				cps += " 32";
			}
			if (containLetter(nexToken)) {
				// 33:nex-tok-has-let
				cps += " 33";
			}
			if (containLetterAndDigit(nexToken)) {
				// 34:nex-tok-has-let-num
				cps += " 34";
			}
			if (isAllNumber(nexToken)) {
				// 35:nex-tok-is-allnum
				cps += " 35";
			}
			// 36:nex-tok-countstop
			cps += " 36=" + Integer.toString(countStops(nexToken));
			// 37:nex-tok-countsign
			cps += " 37=" + Integer.toString(countSigns(nexToken));

		} else {
			// 38:nex-tok
			cps += " 38=null";
		}

		return cps;
	}

	public static boolean containNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	public static boolean containLetter(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	public static boolean containLetterAndDigit(String str) {
		return (containLetter(str) && containNumber(str));
	}

	public static boolean isAllNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!(Character.isDigit(str.charAt(i)) || str.charAt(i) == '.'
					|| str.charAt(i) == ',' || str.charAt(i) == '%' || str
					.charAt(i) == '$')) {
				return false;
			}
		}

		return true;
	}

	public static boolean isFirstCap(String str) {
		if (str.length() > 0 && Character.isUpperCase(str.charAt(0))) {
			return true;
		}

		return false;
	}

	public static boolean isFirstCap1(String str) {
		if (str.length() <= 0) {
			return false;
		}

		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i))) {
				if (Character.isUpperCase(str.charAt(i))) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean isNotFirstCap(String str) {
		return !isFirstCap(str);
	}

	public static boolean endsWithSign(String str) {
		if (str.endsWith(".") || str.endsWith("?") || str.endsWith("!")
				|| str.endsWith(",") || str.endsWith(":") || str.endsWith("\"")
				|| str.endsWith("'") || str.endsWith("''") || str.endsWith(";")) {
			return true;
		}

		return false;
	}

	public static boolean endsWithStop(String str) {
		if (str.endsWith(".") || str.endsWith("?") || str.endsWith("!")) {
			return true;
		}

		return false;
	}

	public static int countStops(String str) {
		int count = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.' || str.charAt(i) == '?'
					|| str.charAt(i) == '!') {
				count++;
			}
		}

		return count;
	}

	public static int countSigns(String str) {
		int count = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.' || str.charAt(i) == '?'
					|| str.charAt(i) == '!' || str.charAt(i) == ','
					|| str.charAt(i) == ':' || str.charAt(i) == ';') {
				count++;
			}
		}

		return count;
	}

	public static boolean isStop(String str) {
		if (str.compareTo(".") == 0) {
			return true;
		}

		if (str.compareTo("?") == 0) {
			return true;
		}

		if (str.compareTo("!") == 0) {
			return true;
		}

		return false;
	}

	public static boolean isSign(String str) {
		if (str.compareTo(".") == 0) {
			return true;
		}

		if (str.compareTo("?") == 0) {
			return true;
		}

		if (str.compareTo("!") == 0) {
			return true;
		}

		if (str.compareTo(",") == 0) {
			return true;
		}

		if (str.compareTo(";") == 0) {
			return true;
		}

		if (str.compareTo(":") == 0) {
			return true;
		}

		if (str.compareTo("\"") == 0) {
			return true;
		}

		if (str.compareTo("'") == 0) {
			return true;
		}

		if (str.compareTo("``") == 0) {
			return true;
		}

		if (str.compareTo("''") == 0) {
			return true;
		}

		if (str.compareTo("%") == 0) {
			return true;
		}

		return false;
	}

} // end of class FeaGeneration

