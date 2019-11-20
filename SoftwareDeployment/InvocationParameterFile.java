package SoftwareDeployment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @author mb
 * 
 */
public class InvocationParameterFile {

	private String fullFileName;
	private File file;
	private FileWriter fileWriter;
	private boolean addTimeStamp = true;
	private final String COMMENT_PREFIX = "# ";

	private BufferedReader inputFile;

	/**
	 * Safely create an instance. If named file exists, it is not overwritten
	 * and instead can be read from.
	 * 
	 * @param rsvpParameterFilename
	 * @param isOverwriteMode
	 */
	public InvocationParameterFile(String fullFileName) {
		file = new File(fullFileName);

		if (!file.exists()) {
			throw new RuntimeException("Parameter file not found: "
					+ fullFileName);
		}
	}

	/**
	 * Create an instance. If isColbberMode, then if named file exists, it is
	 * overwritten (empty file put in its place).
	 * 
	 * @param rsvpParameterFilename
	 * @param isColbberMode
	 */
	public InvocationParameterFile(String fullFileName, boolean isColbberMode) {
		this.fullFileName = fullFileName;

		file = new File(fullFileName);

		try {
			// Create file if it does not exist
			boolean success = file.createNewFile();
			if (success || isColbberMode) {
				// File did not exist and was created
				fileWriter = new FileWriter(file);
				if (addTimeStamp) {
					fileWriter.write(COMMENT_PREFIX + "Created file \"" + file
							+ "\" " + getTimeStamp() + "\n");

					fileWriter.flush();
				}
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

	public String getTimeStamp() {
		return (new Date()).toString();
	}

	public String extractFirstNonCommentLine() {
		String s2 = "";
		List<String> lines = this.getAllLines();
		for (String line : lines) {
			if (!this.isCommentLine(line)) {
				s2 = line;
				break;
			}
		}
		return s2;
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
			FileReader fr = new FileReader(file);
			inputFile = new BufferedReader(fr);
			//inputFile = new BufferedReader(new FileReader(fullFileName));
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

	public void println(String string) {
		try {
			fileWriter.write(string + "\n");
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
