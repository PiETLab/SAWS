package coreIndirectSelectionFacility;

import java.lang.reflect.Constructor;

import encodingTrees.ManualEncodingTreeSpecification;

import sourceSymbolSet.SourceSymbolSet;

public class ManualETGenerator {

	private boolean IS_VERBOSE = false;

	public ManualEncodingTreeSpecification createInstance(
			String variantTechnique) {
		ManualEncodingTreeSpecification manualEncodingTree = null;
		try {
			Class<?>[] params = null;
			// System.out.println("Attempting to create source symbol set: " +
			// variantTechnique);
			Constructor<?> constr = Class.forName(variantTechnique)
					.getConstructor(params);
			Object[] args = null;
			manualEncodingTree = (ManualEncodingTreeSpecification) constr
					.newInstance(args);
		} catch (Exception e) {
			// if class not found
			System.out.println("Could not construct an instance of: "
					+ variantTechnique);
			e.printStackTrace();
			System.exit(0);
		}
		if (IS_VERBOSE)
			System.out.println("Created instance of: "
					+ manualEncodingTree.getIdentifier());

		return manualEncodingTree;
	}

}
