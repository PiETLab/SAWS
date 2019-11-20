package com.waldura.tw;

import java.util.List;
import java.util.Vector;

/**
 * A city is identified by its name, a single uppercase character. Conversions
 * to/from characters are handled by {@link #getName()} and
 * {@link #valueOf(char)}, respectively.
 * <p>
 * Package members are also given access to an identity relationship between
 * citiesInstantiated and numbers: they can converts between <code>City</code> instances
 * and numbers using {@link #valueOf(int)} and {@link #getIndex()}. This
 * special relationship is used by the
 * {@link com.waldura.tw.DenseRoutesMap DensesRoutesMap} to store citiesInstantiated in an
 * array.
 * 
 * @author Renaud Waldura &lt;renaud+tw@waldura.com&gt;
 * @version $Id: City.java,v 1.1 2002/11/16 20:37:52 renaud Exp $
 */

public class City implements Comparable {
	// public final class City implements Comparable {

	/**
	 * The largest possible number of citiesInstantiated.
	 */
	//public static final int MAX_NUMBER = 1000;

	// private static City[] citiesInstantiated = new City[MAX_NUMBER];
	private static List<City> citiesInstantiated = new Vector<City>();

	public static City A;

	public static City B;

	public static City C;

	public static City D;

	public static City E;

	public static City F;

//	public static void initializeToAlphabet() {
//		// initialize all City objects
//		int index = 0;
//		for (char c = 'A'; c <= 'Z'; c++) {
//			// citiesInstantiated[getIndexForName(c)] = new City(c);
//			// citiesInstantiated[index] = new City(c + "");
//			citiesInstantiated.add(new City(c + ""));
//			index++;
//		}
//
//		A = City.valueOf("A");
//		B = City.valueOf("B");
//		C = City.valueOf("C");
//		D = City.valueOf("D");
//		E = City.valueOf("E");
//		F = City.valueOf("F");
//
//	}

	public static void reset() {
		citiesInstantiated = new Vector<City>();
	}
	
	private static int getIndexForName(String name) {
		// return name - 'A';
		// find the position in "citiesInstantiated" that corresponds to this name
		// for (int i = 0; i < citiesInstantiated.length; i++) {
		for (int i = 0; i < citiesInstantiated.size(); i++) {
			if (citiesInstantiated.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

//	private static char getNameForIndex(int index) {
//		return (char) ('A' + index);
//	}

	public static City valueOf(String name) {
		// if (name < 'A' || name > 'Z') {
		// throw new IllegalArgumentException("Invalid city name: " + name);
		// }

		//return citiesInstantiated[getIndexForName(name)];
		return citiesInstantiated.get(getIndexForName(name));
	}

	/*
	 * Package members only.
	 * 
	 * MB: I reinterpreted this to be getIndexOf
	 */
	static City valueOf(int n) {
		// if (n < 0 || n > 25) {
		// throw new IllegalArgumentException("Invalid city number: " + n);
		// }
		//return citiesInstantiated[n];
		return citiesInstantiated.get(n);
		// return valueOf(getNameForIndex(n));
	}

	// MB: changed datatype to String
	private final String name;

	// MB: implemented my own indexing scheme; use the position within the citiesInstantiated List
	private int numericalIdentifier;
	
	/**
	 * Private constructor.
	 * 
	 * @param name
	 */
	public City(String name) {
		this.name = name;
		this.numericalIdentifier = citiesInstantiated.size();
		citiesInstantiated.add(this);
	}

	public String getName() {
		return name;
	}

	/*
	 * Package members only.
	 */
	int getIndex() {
		return this.numericalIdentifier;
		// return getIndexForName(name);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// return String.valueOf(name);
		return this.name + "(" + this.numericalIdentifier + ")";
	}

	/**
	 * Two citiesInstantiated are considered equal if they are the same object, or their
	 * names are the same.
	 * 
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object o) {
		return this == o || equals((City) o);
	}

	private boolean equals(City c) {
		return this.name == c.name;
	}

	/**
	 * Compare two citiesInstantiated by name.
	 * 
	 * @see java.lang.Comparable#compareTo(Object)
	 */
	public int compareTo(Object o) {
		return compareTo((City) o);
	}

	public int compareTo(City c) {
		// return this.name - c.name;
		// return this.index - c.getIndex();

		// rather than using index, base comparison on prefering a city whose
		// row or col is the same, versus having both row and column the same

		// order the citiesInstantiated according to their rows; in case of ties, order by
		// relative col position (a city on same col is closer than one not on
		// same col)
		int rowIndex = this.getRowIndex();
		int colIndex = this.getColIndex();

		int rowIndexOther = c.getRowIndex();
		int colIndexOther = c.getColIndex();

		int val = 0;
		if (this.getIndex() == c.getIndex()) {
			val = 0;
		} else {
			val = colIndex - colIndexOther;
			if (val == 0) {
				val = rowIndex - rowIndexOther;
			}

			// // if we are comparing two diagonal elements to each other, use
			// // row-column ordering
			// // we know they are not the same
			// if ((rowIndex == colIndex) && (rowIndexOther == colIndexOther)) {
			// // val = this.getName().compareTo(c.getName());
			// val = rowIndex - rowIndexOther;
			// }
			// // if we are comparing two elements, neither are diagonal, use
			// // row-major, column-major
			// if ((rowIndex != colIndex) && (rowIndexOther != colIndexOther)) {
			// // val = this.getName().compareTo(c.getName());
			// val = rowIndex - rowIndexOther;
			// if (val == 0) {
			// val = colIndex - colIndexOther;
			// }
			// }
			//			
			// // if this element is a diagonal, and other is not
			//
			// if (rowIndex == rowIndexOther) {
			// val = colIndex - colIndexOther;
			// } else if (colIndex == colIndexOther) {
			// val = rowIndex - rowIndexOther;
			// }
			//
			// // use row-major, column-major ordering for all non-diagonal
			// entries
			// // for diagonal entries, imagine that their new position is
			// offset
			// // by the size of citiesInstantiated
			//
			// if (rowIndex == colIndex) {
			// if (val > 0) {
			// val = val + citiesInstantiated.length;
			// } else if (val < 0) {
			// val = val - citiesInstantiated.length;
			// }
			// }
			//
			// if (rowIndexOther == colIndexOther) {
			// if (val > 0) {
			// val = val + citiesInstantiated.length;
			// } else if (val < 0) {
			// val = val - citiesInstantiated.length;
			// }
			// }

			// if (Math.abs(rowIndex - rowIndexOther) == 1
			// && Math.abs(colIndex - colIndexOther) == 1) {
			// val = citiesInstantiated.length;
			// if (rowIndex > rowIndexOther) {
			// val *= -1;
			// }
			// } else {
			//
			// }
			// val = Math.abs(rowIndexOther - rowIndex)
			// + Math.abs(colIndexOther - colIndex);
			// if (rowIndexOther < rowIndex || colIndexOther < colIndex) {
			// val *= -1;
			// }
		}
		// val *= -1;
		return val;
	}

	/**
	 * returns [rowIndex, colIndex]
	 * 
	 * @param cityName
	 * @return
	 */
	private int[] parseName() {
		String ROW_MARKER = "R";
		String COL_MARKER = "C";
		// names of the form "R2C3"
		int index1 = this.getName().indexOf(ROW_MARKER);
		int index2 = this.getName().indexOf(COL_MARKER);
		// System.out.println(cityName);

		int rowIndex = Integer.parseInt(this.getName().substring(index1 + 1,
				index2));
		int colIndex = Integer.parseInt(this.getName().substring(index2 + 1));

		int[] result = { rowIndex, colIndex };
		// int[] result = new int[2];
		return result;
	}

	/**
	 * 
	 * @param cityName
	 * @return
	 */
	public int getRowIndex() {
		return parseName()[0];
	}

	/**
	 * 
	 * @param cityName
	 * @return
	 */
	public int getColIndex() {
		return parseName()[1];
	}

}
