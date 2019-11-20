package unequalLetterCostCode;

import java.util.List;
import java.util.Vector;

import IndirectSelectionFacilityCommands.AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand;

import customGUIComponentsISF.JIndirectSelectionButton;

import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import treeDataStructure.InternalNode;
import treeDataStructure.LeafNode;

public class EncodingAlphabet {

	List<Integer> encodingAlphabetCost;
	List<JIndirectSelectionButton> alphabet;

	public static final int unitCost = 1;

	public static final JIndirectSelectionButton VK_a = new JIndirectSelectionButton(
			"a", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("a"));

	public static final JIndirectSelectionButton VK_b = new JIndirectSelectionButton(
			"b", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("b"));

	public static final JIndirectSelectionButton VK_c = new JIndirectSelectionButton(
			"c", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("c"));

	public static final JIndirectSelectionButton VK_d = new JIndirectSelectionButton(
			"d", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("d"));

	public static final JIndirectSelectionButton VK_e = new JIndirectSelectionButton(
			"e", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("e"));

	public static final JIndirectSelectionButton VK_f = new JIndirectSelectionButton(
			"f", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("f"));

	public static final JIndirectSelectionButton VK_g = new JIndirectSelectionButton(
			"g", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("g"));

	public static final JIndirectSelectionButton VK_h = new JIndirectSelectionButton(
			"h", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("h"));

	public static final JIndirectSelectionButton VK_i = new JIndirectSelectionButton(
			"i", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("i"));

	public static final JIndirectSelectionButton VK_j = new JIndirectSelectionButton(
			"j", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("j"));

	public static final JIndirectSelectionButton VK_k = new JIndirectSelectionButton(
			"k", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("k"));

	public static final JIndirectSelectionButton VK_l = new JIndirectSelectionButton(
			"l", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("l"));

	public static final JIndirectSelectionButton VK_m = new JIndirectSelectionButton(
			"m", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("m"));

	public static final JIndirectSelectionButton VK_n = new JIndirectSelectionButton(
			"n", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("n"));

	public static final JIndirectSelectionButton VK_o = new JIndirectSelectionButton(
			"o", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("o"));

	public static final JIndirectSelectionButton VK_p = new JIndirectSelectionButton(
			"p", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("p"));

	public static final JIndirectSelectionButton VK_q = new JIndirectSelectionButton(
			"q", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("q"));

	public static final JIndirectSelectionButton VK_r = new JIndirectSelectionButton(
			"r", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("r"));

	public static final JIndirectSelectionButton VK_s = new JIndirectSelectionButton(
			"s", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("s"));

	public static final JIndirectSelectionButton VK_t = new JIndirectSelectionButton(
			"t", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("t"));

	public static final JIndirectSelectionButton VK_u = new JIndirectSelectionButton(
			"u", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("u"));

	public static final JIndirectSelectionButton VK_v = new JIndirectSelectionButton(
			"v", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("v"));

	public static final JIndirectSelectionButton VK_w = new JIndirectSelectionButton(
			"w", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("w"));

	public static final JIndirectSelectionButton VK_x = new JIndirectSelectionButton(
			"x", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("x"));

	public static final JIndirectSelectionButton VK_y = new JIndirectSelectionButton(
			"y", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("y"));

	public static final JIndirectSelectionButton VK_z = new JIndirectSelectionButton(
			"z", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("z"));

	public EncodingAlphabet() {
		encodingAlphabetCost = new Vector<Integer>();
		alphabet = new Vector<JIndirectSelectionButton>();

		alphabet.add(VK_a);
		alphabet.add(VK_b);
		alphabet.add(VK_c);
		alphabet.add(VK_d);
		alphabet.add(VK_e);
		alphabet.add(VK_f);
		alphabet.add(VK_g);
		alphabet.add(VK_h);
		alphabet.add(VK_i);
		alphabet.add(VK_j);
		alphabet.add(VK_k);
		alphabet.add(VK_l);
		alphabet.add(VK_m);
		alphabet.add(VK_n);
		alphabet.add(VK_o);
		alphabet.add(VK_p);
		alphabet.add(VK_q);
		alphabet.add(VK_r);
		alphabet.add(VK_s);
		alphabet.add(VK_t);
		alphabet.add(VK_u);
		alphabet.add(VK_v);
		alphabet.add(VK_w);
		alphabet.add(VK_x);
		alphabet.add(VK_y);
		alphabet.add(VK_z);

		int cost = 0;
		for (int i = 0; i < alphabet.size(); i++) {
			cost = cost + unitCost;
			encodingAlphabetCost.add(cost);

		}
	}

	public EncodingAlphabet(int size) {
		this();
		/*
		 * List <JVirtualKeyboardButton> tempAlphabet = new Vector<JVirtualKeyboardButton>();
		 * List <Integer> tempCost = new Vector<Integer>();
		 * 
		 * for(int i = 0; i < size; i ++) { tempAlphabet.add(alphabet.get(i));
		 * tempCost.add(alphabet.get(i)); }
		 */
		int alphaSize = alphabet.size();
		List<JIndirectSelectionButton> tempAlpha = new Vector<JIndirectSelectionButton>();
		List<Integer> tempInt = new Vector<Integer>();
		if (size < alphaSize) {

			for (int i = 0; i < size; i++) {
				tempInt.add(encodingAlphabetCost.get(i));
				tempAlpha.add(alphabet.get(i));
			}

			alphabet = tempAlpha;
			encodingAlphabetCost = tempInt;
		}
	}

	public JIndirectSelectionButton getAlphabet(int index) {
		return alphabet.get(index);
	}

	public int getAlphabetCost(int index) {
		return encodingAlphabetCost.get(index);
	}

	public List<JIndirectSelectionButton> getAlphabetList() {
		return alphabet;
	}

	public List<Integer> getAlphabetCostList() {
		return encodingAlphabetCost;
	}

	public int size() {
		return alphabet.size();
	}

	public String toString() {
		return alphabet.toString();

	}
}
