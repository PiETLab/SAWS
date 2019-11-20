package subjectLine;


import RSVP.ReadOnlyTextFile;

/**
 * 
 * @author mb
 * 
 */
public class SubjectLineFile extends ReadOnlyTextFile {

	public SubjectLineFile(String fullFileName) {
		super(fullFileName);
		System.out
				.println("Trying to create subject line bank from file at location: "
						+ fullFileName);
		System.out
				.println("Is ok? " + "");
	}

}
