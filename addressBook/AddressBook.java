package addressBook;

import java.util.List;
import java.util.Vector;
import sourceSymbolSet.SourceSymbolSet;

public class AddressBook extends SourceSymbolSet {

	private AddressBookFile file;
	private List<Addressee> entries;
	//private SourceSymbolSet entries2;

	public AddressBook(String fullPathLocation) {
		file = new AddressBookFile(fullPathLocation);
		//entries2 = new MeaganAddressBookSet();
		entries = new Vector<Addressee>();
		for (String line : file.getAllLines()) {
			Addressee addressee = new Addressee(line);
			entries.add(new Addressee(line));
			System.out.println("" + addressee);
			this.addToSymbolSet(addressee, addressee.getMarginalProbability());
		}
	}

}
