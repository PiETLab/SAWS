package buttonLayouts;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.lang.reflect.Constructor;


import sourceSymbolSet.SourceSymbolSet;
import encodingTrees.TraversableEncodingTree;
import encodingTrees.obsolete.TraversableEncodingTreeI;

public class ButtonLayoutSpecificationGenerator {

	private static final boolean IS_VERBOSE = false;

	public ButtonLayoutSpecification createInstance(String variant) {
		ButtonLayoutSpecification keyboard = null;
		try {
			Constructor<?> constr = Class.forName(variant).getConstructor(
					(Class[]) null);
			keyboard = (ButtonLayoutSpecification) constr
					.newInstance((Object[]) null);
		} catch (Exception e) {
			// if class not found
			System.out
					.println("Could not construct an instance of: " + variant);
			e.printStackTrace();
			System.exit(0);
		}
		return keyboard;
	}

	public ButtonLayoutSpecification createInstance(String variant,
			TraversableEncodingTree encodingTree, SourceSymbolSet sourceSymbols) {
		ButtonLayoutSpecification keyboard = null;
		try {
			// Class<?> firstParamType =
			// encodingTree.getClass().getSuperclass();
			Class<?> firstParamType = encodingTree.getClass();
			Class<?> secondParamType = sourceSymbols.getClass().getSuperclass();
			Class<?>[] params = { firstParamType, secondParamType };
			// System.out.println("First param type: " + firstParamType );
			// System.out.println("2." +
			// encodingTree.getClass().getSuperclass());
			// System.out.println("3."
			// + encodingTree.getClass().getSuperclass().getSuperclass());
			// // System.out.println("a." +
			// sourceSymbols.getClass().getSuperclass());
			if (IS_VERBOSE) {
				System.out.println(this.getClass().getName()
						+ ": Looking for constructor: " + variant);
				System.out.println("with arguments: 1. " + firstParamType
						+ "\t 2." + secondParamType);
			}
			Constructor<?> constr = Class.forName(variant).getConstructor(
					params);
			Object[] args = { encodingTree, sourceSymbols };
			keyboard = (ButtonLayoutSpecification) constr.newInstance(args);
		} catch (Exception e) {
			// if class not found
			System.out
					.println("Could not construct an instance of: " + variant);
			e.printStackTrace();
			System.exit(0);
		}
		return keyboard;
	}

	public ButtonLayoutSpecification createInstance(
			TraversableEncodingTree encodingTree,
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		ButtonLayoutSpecification keyboard = null;
		String variant = paramModel.getKeyboardVariant();
		try {
			Class<?> firstParamType = encodingTree.getClass();
			Class<?> secondParamType = paramModel.getClass();
			Class<?>[] params = { firstParamType, secondParamType };
			// System.out.println("First param type: " + firstParamType );
			// System.out.println("2." +
			// encodingTree.getClass().getSuperclass());
			// System.out.println("3."
			// + encodingTree.getClass().getSuperclass().getSuperclass());
			// // System.out.println("a." +
			// sourceSymbols.getClass().getSuperclass());
			if (IS_VERBOSE) {
				System.out.println(this.getClass().getName()
						+ ": Looking for constructor. ");
				// System.out
				// .println("In: "
				// + this.getClass().getName()
				// +
				// " createInstance(TraversableEncodingTree, IndirectSelectionFacilityInvocationParameterModel)");
				System.out.println("Looking for constructor: " + variant);
				System.out.println("with arguments: 1. " + firstParamType
						+ "\t 2." + secondParamType);
			}
			Constructor<?> constr = Class.forName(variant).getConstructor(
					params);
			Object[] args = { encodingTree, paramModel };
			keyboard = (ButtonLayoutSpecification) constr.newInstance(args);
		} catch (Exception e) {
			// if class not found
			System.out
					.println("Could not construct an instance of: " + variant);
			e.printStackTrace();
			System.exit(0);
		}
		return keyboard;
	}
}
