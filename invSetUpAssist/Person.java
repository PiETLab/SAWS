package invSetUpAssist;

import java.util.Random;
import java.io.Serializable;

// Encapsulates a person with a name and an age
public class Person implements Serializable {
	private String name;
	private int age;

	private static int numberOfPersons = 0;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a person of age 0 whose name is either John Doe or Jane Doe.
	 * The name is randomly chosen.
	 */
	public Person() {
		Random random = new Random();
		if (random.nextBoolean()) {
			this.name = "John Doe";
		} else {
			this.name = "Jane Doe";
		}
	}

	/**
	 * Constructs a person with the given name and age.
	 * 
	 * @param name
	 *            The name of the person.
	 * @param age
	 *            The age of the person.
	 * @pre. age >= 0.
	 */
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		Person.numberOfPersons++;
	}

	/**
	 * Sets the name of this person to the given name.
	 * 
	 * @param name
	 *            The new name of this person.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the age of this person to the given age if the given age is greater
	 * than or equal to 0; returns whether the age has been set.
	 * 
	 * @param age
	 *            The new age of this person.
	 * @return true of age >= 0, false otherwise.
	 */
	public boolean setAge(int age) {
		boolean changed = age >= 0;
		if (changed) {
			this.age = age;
		}
		return changed;
	}

	/**
	 * Returns the name of this person.
	 * 
	 * @return The name of this person.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the age of this person.
	 * 
	 * @return The age of this person.
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Returns the number of persons that have been created so far.
	 * 
	 * @return The number of persons that have been created so far.
	 */
	public static int getNumberOfPersons() {
		return Person.numberOfPersons;
	}

	/**
	 * Tests for equality of this person and the given other person. Two persons
	 * are equal if they have the same name and the same age. If both names are
	 * null, then they are considered the same.
	 * 
	 * @param other
	 *            Another person.
	 * @return true if this person and the other person are the same, false
	 *         otherwise.
	 */
	public boolean equals(Object other) {
		return other != null
				&& other instanceof Person
				&& this.age == ((Person) other).age
				&& ((this.name == null && ((Person) other).name == null) || (this.name != null && this.name
						.equals(((Person) other).name)));
	}

	/**
	 * Returns a string representation of this person. This string
	 * representation consists of the name of this person followed by the string "
	 * is " followed by the age of this person followed by the string " year(s)
	 * old."
	 * 
	 * @return A string representation of this person.
	 */
	public String toString() {
		return this.getName() + " is " + this.getAge() + " year(s) old";
	}
}
