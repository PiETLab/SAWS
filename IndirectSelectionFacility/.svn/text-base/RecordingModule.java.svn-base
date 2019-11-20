/*
 * Created on 8-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package IndirectSelectionFacility;
import java.io.*;

/**
 * @author Melanie Baljko
 */
public class RecordingModule {
	RandomAccessFile output;
	FileWriter outputFile;
	File file;

	public RecordingModule(String corpusID, String tcfLabel)
		throws IOException {
		//Calendar rightNow = Calendar.getInstance();
		file = new File(corpusID + "-" + tcfLabel);
		try {
			// if ( file.exists() ) file.delete();  This done automatically....
			// file.createNewFile();
			// Don't need to test if ( file.canWrite() ), if not exception will be thrown
			outputFile = new FileWriter(file);
			outputFile.write("Created file " + this.getFileName() + "\n");
			outputFile.flush();
		} catch (IOException e) {
			System.out.println( "Problems creating file" );
			throw e;
		}
	} // end of constructor

	public void recordData(String data) {
		try {
			//	    output.writeChars( data );
			outputFile.write(data);
			outputFile.flush();
		} catch (IOException e) {
			System.out.println("Problems writing to file" + e);
		}
	}

	public String getFileName() {
		return file.getName();
	}

}
