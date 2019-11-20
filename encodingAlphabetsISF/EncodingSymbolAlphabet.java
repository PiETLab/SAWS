package encodingAlphabetsISF;

/**
 * This class represents a set of encoding symbols.
 * 
 * Each encoding symbol has an index (where the indices start at 0 and are
 * numbered consecutively
 * 
 * Each encoding symbol has a cost (which is given by a double value
 * 
 * @author mb
 * 
 */
public abstract class EncodingSymbolAlphabet {

	public abstract double getCost(int index);

}
