package invSetUpAssist;

import java.util.Map;
import java.util.HashMap;
import java.io.ObjectInputStream;
import java.io.Serializable;

// The model is a gateway to the data (thus has filters and business rules)
public class VOCAAtrributeModel implements Serializable {
	private int DEFAULT_DWELL_TIME = 1050;

	private int dwellTimeInMSec;
	private Map<String, Person> map;

	private static final long serialVersionUID = 2L;

	public VOCAAtrributeModel() {
		this.map = new HashMap<String, Person>();
		this.setDwellTimeInMSec(DEFAULT_DWELL_TIME);
	}

	/**
	 * Creates a model from the given inputstream.
	 * 
	 * @param stream
	 *            an inputstream.
	 * @throws Exception
	 *             if the given inputstream does not contain a Map<String,
	 *             Person>.
	 */
	public VOCAAtrributeModel(ObjectInputStream stream) throws Exception {
		this.map = ((VOCAAtrributeModel) stream.readObject()).map;
	}

	/**
	 * Returns the size of this model, that is, the number of persons.
	 * 
	 * @return the size of this model.
	 */
	public int size() {
		return this.map.size();
	}

	/**
	 * Returns the person corresponding to the given name. Returns null if this
	 * model does not contain the given name.
	 * 
	 * @param name
	 *            a name.
	 * @return the person corresponding to the given name.
	 */
	public Person get(String name) {
		return this.map.get(name);
	}

	/**
	 * Adds the given name and person to this model. If the given name is
	 * already present, it is overwritten.
	 * 
	 * @param name
	 *            a name.
	 * @param person
	 *            a person.
	 * @pre. person.getName().equals(name)
	 */
	public void put(String name, Person person) {
		this.map.put(name, person);
	}

	public int getDwellTimeInMSec() {
		return dwellTimeInMSec;
	}

	public void setDwellTimeInMSec(int dwellTimeInMSec) {
		this.dwellTimeInMSec = dwellTimeInMSec;
	}
}
