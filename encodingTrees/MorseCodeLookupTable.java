package encodingTrees;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import customGUIComponentsISF.JIndirectSelectionButton;


import sourceSymbolSet.SourceSymbol;

public class MorseCodeLookupTable {

	private Map<SourceSymbol, String> code;

	public MorseCodeLookupTable() {
		code = new TreeMap<SourceSymbol, String>();
		// cut and paste this from "morsecode.xls"
		// eventualy all the symbols will be added, but for now just the letters
		// and a few extras
		code.put(JIndirectSelectionButton.VK_H, "11113");
		code.put(JIndirectSelectionButton.VK_V, "11123");
		code.put(JIndirectSelectionButton.VK_S, "1113");
		code.put(JIndirectSelectionButton.VK_F, "11213");
		code.put(JIndirectSelectionButton.VK_U, "1123");
		code.put(JIndirectSelectionButton.VK_I, "113");
		code.put(JIndirectSelectionButton.VK_L, "12113");
		code.put(JIndirectSelectionButton.VK_PERIOD, "1212123");
		code.put(JIndirectSelectionButton.VK_R, "1213");
		code.put(JIndirectSelectionButton.VK_P, "12213");
		code.put(JIndirectSelectionButton.VK_J, "12223");
		code.put(JIndirectSelectionButton.VK_W, "1223");
		code.put(JIndirectSelectionButton.VK_A, "123");
		code.put(JIndirectSelectionButton.VK_E, "13");
		code.put(JIndirectSelectionButton.VK_B, "21113");
		code.put(JIndirectSelectionButton.VK_X, "21123");
		code.put(JIndirectSelectionButton.VK_D, "2113");
		code.put(JIndirectSelectionButton.VK_C, "21213");
		code.put(JIndirectSelectionButton.VK_Y, "21223");
		code.put(JIndirectSelectionButton.VK_K, "2123");
		code.put(JIndirectSelectionButton.VK_N, "213");
		code.put(JIndirectSelectionButton.VK_COMMA, "2211223");
		code.put(JIndirectSelectionButton.VK_Z, "22113");
		code.put(JIndirectSelectionButton.VK_Q, "22123");
		code.put(JIndirectSelectionButton.VK_G, "2213");
		code.put(JIndirectSelectionButton.VK_O, "2223");
		code.put(JIndirectSelectionButton.VK_M, "223");
		code.put(JIndirectSelectionButton.VK_T, "23");
		code.put(JIndirectSelectionButton.VK_SPACE_TEXT, "4");

	}

	public List<SourceSymbol> getSourceSymbolsInOrderSortedByModifiedLexicographicalOnMorseCodeword() {

		Map<String, SourceSymbol> converse = new TreeMap<String, SourceSymbol>(
				new MyKeyComparator());
		for (SourceSymbol s : code.keySet()) {
			//System.out.println("conversifying: " + s);
			converse.put(code.get(s), s);
		}

		List<SourceSymbol> list = new Vector<SourceSymbol>();
		for (String s : converse.keySet()) {
			list.add(converse.get(s));
		}
		return list;
	}

	public String getCode(SourceSymbol sourceSymbol) {
		return code.get(sourceSymbol);
	}

	class MyKeyComparator implements Comparator<String>, Serializable {

		private static final long serialVersionUID = 5355138899616868266L;

		/**
		 * implements modified lexicographical ordering; if the strings are not
		 * the same length, then the shorter one is treated as though it is the
		 * same length as the longer one
		 */
		public int compare(String o1, String o2) {

			// String o1 = o1a;
			// String o2 = o2a;
			int lengthDifference = o1.length() - o2.length();
			if (lengthDifference >= 0) {
				for (int i = 0; i < lengthDifference; i++) {
					o2 = o2 + "Z";
				}

			} else {
				lengthDifference *= -1;
				for (int i = 0; i < lengthDifference; i++) {
					o1 = o1 + "Z";
				}
			}
			return o1.compareTo(o2);
		}
	}

}
