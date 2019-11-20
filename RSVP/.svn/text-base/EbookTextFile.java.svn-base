package RSVP;

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
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import jtextpro.JTextPro;

/**
 * This class implements an ebook text file, which is a text file. There is no
 * special formatting (wrt line breaks corresponding to sentence breaks). It is
 * essentially a text file containing the text of a book.
 * 
 * @author mb
 * 
 */
public class EbookTextFile extends ReadOnlyTextFile {

	public EbookTextFile(String fullFileName) {
		super(fullFileName);
	}

	public String getFilenameCorrespondingChunkedFilename() {
		return file.getAbsolutePath()
				+ EbookChunkedTextFile.CHUNKED_FILE_EXTENSION;
	}

}
