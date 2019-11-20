package sourceSymbolSet;

import java.lang.reflect.Constructor;

import buttonLayouts.ButtonLayoutSpecification;

import sourceSymbolSet.SourceSymbolSet;


public class SourceSymbolSetGenerator {

	public SourceSymbolSet createInstance(String variantTechnique) {
		SourceSymbolSet sourceSymbolSet = null;
		try {
			Class<?>[] params = null;
			//System.out.println("Attempting to create source symbol set: " + variantTechnique);
			Constructor<?> constr = Class.forName(
			// "sourceSymbolSet." +
					variantTechnique).getConstructor(params);
			Object[] args = null;
			sourceSymbolSet = (SourceSymbolSet) constr.newInstance(args);
		} catch (Exception e) {
			// if class not found
			System.out.println("Could not construct an instance of: "
					+ variantTechnique);
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Created instance of: "
				+ sourceSymbolSet.getIdentifier());

		return sourceSymbolSet;
	}
}
