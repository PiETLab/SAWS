package UtilityClassesISF;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile {

	/**
	 * The file is created in the subdirectory that is indicated in the
	 * argument.
	 * 
	 * @param fileName
	 * @return
	 */
	public static FileWriter setUpOutputFile(String directory, String fileName,
			boolean isVerbose) {
		FileWriter outputFile = null;
		try {
			File subdir = new File(directory);
			if (!subdir.exists()) {
				subdir.mkdir();
			}
			File file = new File(subdir, fileName);
			// if (!file.canWrite()) {
			outputFile = new FileWriter(file);
			if (isVerbose) {
				System.out.println("Creating output file: " + file.toString());
			}
			// } else {
			// throw new IOException("Cannot create latex file: " + fileName );
			// + outputFile.getClass().getName());
			// }
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return outputFile;
	}

}
