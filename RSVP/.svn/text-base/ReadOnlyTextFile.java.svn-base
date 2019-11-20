package RSVP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;


/**
 * This class implements a text document which is parse-able into sentences. A
 * sentence is understood to be a sequence of characters that is terminated by
 * LF-CR (altho this may be a system dependent parameter).
 * 
 * @author mb
 * 
 */
public abstract class ReadOnlyTextFile {

	private String fullFileName;
	protected File file;

	private BufferedReader inputFile;

	public ReadOnlyTextFile(String fullFileName) {
		this.fullFileName = fullFileName;
		file = new File(fullFileName);

		try {
			// Create file if it does not exist
			boolean success = file.createNewFile();
			if (success) {
				System.out.println("File does not exist: " + fullFileName);
				// File did not exist and was created
				// fileWriter = new FileWriter(file);
				// if (addTimeStamp) {
				// fileWriter.write(COMMENT_PREFIX + "Created file \"" + file
				// + "\" " + getTimeStamp() + "\n");

				// fileWriter.flush();
				// }
			} else {
				// File already exists
				System.out.println("Found file: " + fullFileName);
			}
			// if ( file.exists() ) file.delete(); This done automatically....
			// file.createNewFile();
			// Don't need to test if ( file.canWrite() ), if not exception will
			// be thrown

		} catch (IOException e) {
			System.out.println(" Problems creating file corresponding to "
					+ file);
			e.printStackTrace();
			System.exit(0);
		}

	}

	public String readLine() {
		String s = null;
		try {
			s = inputFile.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return s;
	}

	public void close() {
		try {
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void open() {
		try {
			inputFile = new BufferedReader(new FileReader(fullFileName));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public List<String> getAllLines() {
		List<String> sentences = new Vector<String>();
		this.open();
		String line;
		while ((line = this.readLine()) != null) {
			sentences.add(line);
			// System.out.println(line + "\n");
		}
		return sentences;
	}
	
	public static boolean isCommentLine(String s) {
		return s.charAt(0) == '#';
	}


}
