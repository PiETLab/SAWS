package encodingTrees;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Vector;

import buttonLayouts.ButtonLayoutSpecification;

import coreIndirectSelectionFacility.ManualETGenerator;

import sourceSymbolSet.SourceSymbolSet;
import sourceSymbolSet.SourceSymbolSetGenerator;

public class TraversableEncodingTreeGenerator {

	private static final boolean IS_VERBOSE = false;

	/**
	 * The method used to create an encoding tree from a keyboard layout
	 * 
	 * @param constructTechnique
	 *            must be the name of a class in the package "encodingTrees"
	 *            that takes the following argument as a constructor parameters.
	 *            At present, three classes implemented: BinaryFromKB,
	 *            LinearFromKB, or RowColFromKB
	 * @param keyboardLayout
	 *            must be an keyboard layout
	 * @return
	 */
	public TraversableEncodingTree createInstance(String constructTechnique,
			ButtonLayoutSpecification keyboardLayout) {
		EncodingTree encodingTree = null;
		try {
			// Class<?>[] params = { Class.forName(PARAM_CLASS) };
			Class<?>[] params = { keyboardLayout.getClass().getSuperclass() };
			if (IS_VERBOSE) {
				System.out.println(keyboardLayout.getClass().getSuperclass());
			}
			Constructor<?> constr = Class.forName(constructTechnique)
					.getConstructor(params);
			Object[] args = { keyboardLayout };
			encodingTree = (EncodingTree) constr.newInstance(args);
		} catch (Exception e) {
			// if class not found
			System.out.println("Could not construct an instance of: "
					+ constructTechnique);
			e.printStackTrace();
			System.exit(0);
		}
		if (IS_VERBOSE)
			System.out.println("Created instance of: "
					+ encodingTree.getCreatingClass());

		return new TraversableEncodingTree(encodingTree);
	}

	public TraversableEncodingTree createInstance(String constructTechnique,
			ManualEncodingTreeSpecification encodingTreeManualSpecification) {
		EncodingTree encodingTree = null;
		try {
			// Class<?>[] params = { Class.forName(PARAM_CLASS) };
			Class<?>[] params = { encodingTreeManualSpecification.getClass()
					.getSuperclass() };
			// Class<?>[] params = { null };

			if (IS_VERBOSE) {
				System.out.println("Constructor> " + constructTechnique);
				System.out.println("Parameter> " + params[0]);
			}
			Constructor<?> constr = Class.forName(constructTechnique)
					.getConstructor(params);
			Object[] args = { encodingTreeManualSpecification };
			encodingTree = (EncodingTree) constr.newInstance(args);
		} catch (Exception e) {
			// if class not found
			System.out.println("Could not construct an instance of: "
					+ constructTechnique);
			e.printStackTrace();
			System.exit(0);
		}
		if (IS_VERBOSE)
			System.out.println("Created instance of: "
					+ encodingTree.getCreatingClass());

		return new TraversableEncodingTree(encodingTree);
	}

	public TraversableEncodingTree createInstance(
			ManualEncodingTreeSpecification encodingTreeManualSpecification) {
		EncodingTree encodingTree = null;
		try {

			encodingTree = new EncodingTreeFromManualSpecification(
					encodingTreeManualSpecification);
		} catch (Exception e) {
			// if class not found
			System.out.println("Could not construct an instance of: "
					+ encodingTreeManualSpecification);
			e.printStackTrace();
			System.exit(0);
		}
		if (IS_VERBOSE)
			System.out.println("Created instance of: "
					+ encodingTree.getCreatingClass());

		return new TraversableEncodingTree(encodingTree);
	}

	/**
	 * The method used to create an encoding tree from a source symbol set
	 * (which has a probability distribution) and an encoding alphabet
	 * 
	 * @param constructTechnique
	 *            must be the name of a class in the package "encodingTrees"
	 *            that takes the following two arguments are constructor
	 *            parameters. At present, two classes implemented:
	 *            HuffmanUunequalCosts or HuffmanEqualCosts
	 * @param sourceSymbols
	 *            must be the name of a class in the package "sourceSymbolSet"
	 * @param encodingAlphabetSize
	 *            must be an integer that represents the size of the encoding
	 *            alphabet
	 * @return
	 */
	public TraversableEncodingTree createInstance(String constructTechnique,
			SourceSymbolSet sourceSymbols, Integer encodingAlphabetSize) {
		EncodingTree encodingTree = null;
		try {
			if (IS_VERBOSE)
				System.out.println("Attempting to construct an instance of: "
						+ constructTechnique
						+ " with encoding alphabet of size: "
						+ encodingAlphabetSize);
			// Class<?>[] params = { Class.forName(PARAM_CLASS2) };
			Class<?>[] params = { sourceSymbols.getClass().getSuperclass(),
					encodingAlphabetSize.getClass() };
			Constructor<?> constr = Class.forName(constructTechnique)
					.getConstructor(params);
			if (IS_VERBOSE) {
				System.out.println(sourceSymbols.getClass().getSuperclass());
				System.out.println(encodingAlphabetSize.getClass());
				System.out.println(Class.forName(constructTechnique));
			}
			Object[] args = { sourceSymbols, encodingAlphabetSize };
			encodingTree = (EncodingTree) constr.newInstance(args);
		} catch (Exception e) {
			// if class not found
			System.out.println("Could not construct an instance of: "
					+ constructTechnique);
			e.printStackTrace();
			System.exit(0);
		}
		if (IS_VERBOSE)
			System.out.println("Created instance of: "
					+ encodingTree.getCreatingClass());
		return new TraversableEncodingTree(encodingTree);
	}

	/**
	 * 
	 * @param constructTechnique
	 * @return
	 */
	public TraversableEncodingTree createInstance(String constructTechnique) {
		EncodingTree encodingTree = null;
		try {
			// Class<?>[] params = { Class.forName(PARAM_CLASS2) };
			// Class<?>[] params = { null };
			Class<?>[] params = null;
			Constructor<?> constr = Class.forName(constructTechnique)
					.getConstructor(params);
			// System.out.println(sourceSymbols.getClass().getSuperclass());
			// System.out.println(encodingAlphabetSize.getClass());
			// System.out.println( Class.forName(constructTechnique));
			// Object[] args = { null };
			Object[] args = null;
			encodingTree = (EncodingTree) constr.newInstance(args);
		} catch (Exception e) {
			// if class not found
			System.out.println("Could not construct an instance of: "
					+ constructTechnique);
			e.printStackTrace();
			System.exit(0);
		}
		if (IS_VERBOSE)
			System.out.println("Created instance of: "
					+ encodingTree.getCreatingClass());
		return new TraversableEncodingTree(encodingTree);
	}

	/**
	 * 
	 * @param paramMgr
	 * @return
	 */
	public TraversableEncodingTree generateEncodingTree(
			IndirectSelectionFacilityInvocationParameterModel paramManager) {

		TraversableEncodingTree encodingTree = null;

		TraversableEncodingTreeGenerator tetGen = new TraversableEncodingTreeGenerator();

		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName()
					+ " : generating encoding tree.");
		}

		if (paramManager.isEncodingTreeToBeDerivedFromManualSpecification()) {

			ManualETGenerator kbg = new ManualETGenerator();
			ManualEncodingTreeSpecification encodingTreeManualSpecification = kbg
					.createInstance(paramManager
							.getManualEncodingTreeSpecification());
			// onScreenKeyboard = new OnScreenKeyboard(paramManager
			// .getKeyboardVariant());

			// encodingTree = tetGen.createInstance(paramManager
			// .getEncodingTreeAlgorithmicConstructionTechnique(),
			// encodingTreeManualSpecification);

			encodingTree = tetGen
					.createInstance(encodingTreeManualSpecification);
			if (IS_VERBOSE) {
				System.out.println("Manual: ");
				System.out.println(encodingTree.toString());
			}

		} else if (paramManager.isEncodingTreeToBeDerivedFromAlgorithm()) {
			SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
			SourceSymbolSet sourceSymbols = sssGen.createInstance(paramManager
					.getSourceSymbolSet());
			if (IS_VERBOSE) {
				System.out.println("Generating from Source Symbol Set: ");
				System.out.println(sourceSymbols.toString());
			}
			encodingTree = tetGen.createInstance(paramManager
					.getEncodingTreeAlgorithmicConstructionTechnique(),
					sourceSymbols, paramManager.getEncodingAlphabetSize());
		} else if (paramManager.isEncodingTreeToBeDerivedFromFileCache()) {
			if (IS_VERBOSE) {
				System.out.println("Generating from File Cache: ");
				System.out.println(paramManager.getFullFileNameOfCache());
			}
			encodingTree = TraversableEncodingTree
					.getObjectFromFile(paramManager.getFullFileNameOfCache());
			;
		}
		return encodingTree;
	}

}
