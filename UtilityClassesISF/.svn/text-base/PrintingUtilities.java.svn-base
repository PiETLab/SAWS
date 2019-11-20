package UtilityClassesISF;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard;

import encodingTrees.obsolete.MyTraversableEncodingTree;

public class PrintingUtilities {

	private static String getHeader() {
		StringBuffer buf = new StringBuffer();
		buf.append("\\documentclass[12pt]{article}" + "\n"
				+ "\\usepackage{palatino}" + "\\usepackage{fullpage}" + "\n"
				+ "\\usepackage{url}" + "\n" + "\\usepackage{amsmath}" + "\n"
				+ "\\usepackage{epic}" + "\n" + "\\usepackage{ecltree}" + "\n"
				+ "\\renewcommand{\\ttdefault}{cmtt}" + "\n"
				+ "\\begin{document}" + "\n");
		return buf.toString();
	}

	private static String getFooter() {
		StringBuffer buf = new StringBuffer();
		buf.append("\\end{document}");
		return buf.toString();
	}

	private static void generateLaTeXFile(String name, MyTraversableEncodingTree ch,
			List<Double> prob) {
		try {
			File file = new File("tex" + File.separator + "chex-" + name
					+ ".tex");
			FileWriter outputFile = new FileWriter(file);
			StringBuffer buf = new StringBuffer();

			buf.append(getHeader());
			buf.append("\\noindent \\url{" + name + "}\n\n");
			buf.append("\\noindent \\url{" + prob + "}\n\n");
			// buf.append(keyboard.toStringLaTeX());
			// buf.append("\\newpage" + "\n");

			buf.append("\\newpage" + "\n");

			final boolean SHOW_NODE_IDS = false;
			final boolean SHOW_NODE_DEPTHS = true;
			// final boolean SHOW_NODE_DEPTHS = false;
			buf
					.append(ch.toStringLaTeXecltree(SHOW_NODE_IDS,
							SHOW_NODE_DEPTHS, true));

			buf.append("%% PROB DIST %%\n");
			buf.append(probToStringLaTeX(prob));

			buf.append("\\newpage" + "\n");

			buf.append(ch.getCode().toStringLaTeX());
			buf.append(getFooter());
			outputFile.write(buf.toString());
			outputFile.flush();
			System.out.println("Done Creating file " + name);
		} catch (IOException e) {
			System.out.println("Problems creating file");
			e.printStackTrace();
			System.exit(0);
		}

	}

	private static String probToStringLaTeX(List<Double> prob) {

		StringBuffer buf = new StringBuffer();
		// buf.append(this.getIdentifier() + "\n");
		// Set<X> s = values.keySet();
		// Collection<Double> v = values.values();
		// List<X> l = new Vector<X>();
		// l.addAll(s);
		// Collections.sort(l);

		buf.append("\\begin{tabular}{l l}");
		for (Double x : prob) {
			// buf.append("" + x.toStringLaTeX() + "\t&\t" + get(x) + "\\\\\n");
			buf.append("" + x.toString() + "\t&\t" + x + "\\\\\n");
		}
		buf.append("\\end{tabular}");
		return buf.toString();
	}

	/**
	 * This method creates a tex file in the specified subdir that is a
	 * stand-alone latex file that displays the encoding tree of the passed
	 * onscreen indirect selection keyboard
	 * 
	 * @param keyboard
	 * @param subDir
	 */
	public static void generateLaTeXFile(
			AbstractOnScreenIndirectSelectionKeyboard keyboard, String subDir) {
		try {
			boolean isVerbose = true;
			File file = new File("tex" + File.separator + "ch-"
					+ keyboard.getName() + ".tex");
			String fileName = "ET-" + keyboard.getName() + ".tex";
			FileWriter outputFile = OutputFile.setUpOutputFile(subDir,
					fileName, isVerbose);

			StringBuffer buf = new StringBuffer();

			buf.append(getHeader());
			buf.append("\\noindent \\url{" + keyboard.getName() + "}\n\n");
			if (keyboard.getProbDist() != null) {
				buf.append("\\noindent \\url{"
						+ keyboard.getProbDist().getIdentifier() + "}\n\n");
			}
			buf.append(keyboard.toStringLaTeX());
			buf.append("\\newpage" + "\n");
			buf.append(keyboard.getEncodingTree().getCode()
					.toStringLaTeX());
			buf.append(getFooter());
			outputFile.write(buf.toString());
			outputFile.flush();
			System.out.println("Done.");
		} catch (IOException e) {
			System.out.println("Problems creating file");
			e.printStackTrace();
			System.exit(0);
		}
	}

}
