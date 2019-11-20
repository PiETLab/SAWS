package driverApplications_ISF;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import encodingTrees.TraversableEncodingTree;

public class LaTeXHelper {

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

	public static void generateLaTeXFile(TraversableEncodingTree et,
			boolean isShowNodeIDsOn, boolean isShowInternalEdgeCostsOn,
			boolean isShowNonInternalEdgeCostsOn) {
		try {
			File file = new File("tex" + File.separator + "Summary-"
					+ et.getMannerOfCreationIdentifier() + "."
					+ et.getSourceSymbolSetIdentifier() + ".tex");
			FileWriter outputFile = new FileWriter(file);
			System.out.println("# " + "Creating file: " + file);

			String fileECLTree = "ecltree-"
					+ et.getMannerOfCreationIdentifier() + "."
					+ et.getSourceSymbolSetIdentifier() + ".tex";
			File fileECLTree2 = new File("tex" + File.separator + fileECLTree);
			FileWriter outputFileECLTree = new FileWriter(fileECLTree2);
			System.out.println("# " + "Creating file: " + fileECLTree2);

			StringBuffer buf = new StringBuffer();

			buf.append(getHeader());
			buf.append("\\noindent \\url{" + et.getMannerOfCreationIdentifier()
					+ "}\n\n");
			// buf.append("\\noindent \\url{" +
// isf.getProbDist().getIdentifier()
			// + "}\n\n");

			buf.append("\\input{" + fileECLTree + "}\n\n");

			outputFileECLTree.write(et.toStringLaTeXecltree(isShowNodeIDsOn,
					isShowInternalEdgeCostsOn, isShowNonInternalEdgeCostsOn));
			outputFileECLTree.flush();

			buf.append("\\newpage" + "\n");
			buf.append(et.getCode().toStringLaTeX());
			buf.append(getFooter());
			outputFile.write(buf.toString());
			outputFile.flush();
			System.out.println("# " + "Done.");
		} catch (IOException e) {
			System.out.println("Problems creating file");
			e.printStackTrace();
			System.exit(0);
		}
	}
}
