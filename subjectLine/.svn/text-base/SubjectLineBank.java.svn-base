package subjectLine;

import java.util.List;
import java.util.Vector;
import sourceSymbolSet.SourceSymbolSet;

public class SubjectLineBank extends SourceSymbolSet {

	private SubjectLineFile file;
	private List<SubjectLine> entries;

	// private SourceSymbolSet entries2;

	public SubjectLineBank(String fullPathLocation) {
		file = new SubjectLineFile(fullPathLocation);
		// entries2 = new MeaganAddressBookSet();
		entries = new Vector<SubjectLine>();
		for (String line : file.getAllLines()) {
			SubjectLine subjectLine = new SubjectLine(line);
			entries.add(new SubjectLine(line));
			if (IS_VERBOSE)
				System.out.println("" + subjectLine);
			this.addToSymbolSet(subjectLine, subjectLine
					.getMarginalProbability());
		}
	}

}
