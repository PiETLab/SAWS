package SoftwareDeployment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * Created on 24-Feb-2005
 *
 */

/**
 * This is a convenience class that implement an output file. It is merely a
 * wrapper for a subset of Java's I/O class FileWriter. It also takes care of
 * exception handling.
 * 
 * 
 * @author Melanie Baljko
 */
public class ParameterFile_olderVersion {

	private File file;

	private FileWriter outputFile;

	private final String COMMENT_PREFIX = "# ";

	/**
	 * Creates an instance of an output file. The name of the file is taken from
	 * the value of the parameter that is passed to this constructor. When the
	 * file is created, a line of text is written to it that gives a date and
	 * time stamp.
	 * 
	 * @param fileName
	 *            The name of the output file to be created.
	 * @throws IOException
	 */
	public ParameterFile_olderVersion(String fileName) {
		this(fileName, true);
	} // end of constructor

	/**
	 * 
	 * @param fileName
	 *            The name of the output file to be created.
	 * @throws IOException
	 */

	/**
	 * Creates an instance of an output file. The name of the file is taken from
	 * the value of the parameter that is passed to this constructor. When the
	 * file is created, a line of text is written to it that gives a date and
	 * time stamp.
	 * 
	 * @param fileName
	 *            full name of output file to be created (can include path info)
	 * @param addTimeStamp
	 *            whether to add a timestamp as the first line
	 * @throws IOException
	 */
	public ParameterFile_olderVersion(String fileName, boolean addTimeStamp) {

		file = new File(fileName);
		try {
			// if ( file.exists() ) file.delete(); This done automatically....
			// file.createNewFile();
			// Don't need to test if ( file.canWrite() ), if not exception will
			// be thrown
			outputFile = new FileWriter(file);
			if (addTimeStamp) {
				outputFile.write(COMMENT_PREFIX + "Created file \"" + fileName
						+ "\" " + getTimeStamp2() + "\n");

				outputFile.flush();
			}

		} catch (IOException e) {
			System.out.println(" Problems creating file corresponding to "
					+ fileName);
			e.printStackTrace();
			System.exit(0);
		}
	} // end of constructor

	public String getTimeStamp() {
		final String MONTHS = "JanFebMarAprMayJunJulAugSepOctNovDec";
		final String DAYS = "SunMonTueWedThuFriSat";
		Calendar rightNow = Calendar.getInstance();
		String month = MONTHS.substring(3 * (rightNow.get(Calendar.MONTH)),
				3 * (rightNow.get(Calendar.MONTH)) + 3);
		String day = DAYS.substring(
				3 * (rightNow.get(Calendar.DAY_OF_WEEK) - 1), 3 * (rightNow
						.get(Calendar.DAY_OF_WEEK) - 1) + 3);

		return day + month + rightNow.get(Calendar.DAY_OF_MONTH);
	}
	
	public String getTimeStamp2() {
		return (new Date()).toString();
	}
	

	/**
	 * This method writes the String s to this file, followed by a carriage
	 * return.
	 * 
	 * @param s
	 *            The string to be written to this output file.
	 */
	public void println(String s) {
		try {
			outputFile.write(s + "\n");
			outputFile.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method writes the String s to this file.
	 * 
	 * @param s
	 *            The string to be written to this output file.
	 */
	public void print(String s) {
		try {
			outputFile.write(s);
			outputFile.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void recordData(String data) {
		try {
			// output.writeChars( data );
			outputFile.write(data);
			outputFile.flush();
		} catch (IOException e) {
			System.out.println("Problems writing to file" + e);
		}
	}

	/**
	 * This method ensures that everything in the output buffer is indeed
	 * written to this file.
	 */
	public void flush() {
		try {
			outputFile.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedReader getBufferedReader() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}

	public String getName() {
		return file.getName();
	}

	public String getFullName() {
		return file.getAbsolutePath();
	}

	public String toString() {
		return file.getName();
	}

}