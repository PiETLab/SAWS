package displayScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.lang.reflect.Constructor;

import buttonLayouts.ButtonLayoutSpecification;

import IndirectSelectionFacility.OnScreenKeyboardView;

import sourceSymbolSet.SourceSymbolSet;
import encodingTrees.TraversableEncodingTree;

public class DisplaySchemeForTraversableEncodingTreeStatesGenerator {

	private static final boolean IS_VERBOSE = false;

	public DisplaySchemeForTraversableEncodingTreeStates createInstance(
			TraversableEncodingTree encodingTree,
			OnScreenKeyboardView onScreenKeyboardRepresentation,
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		DisplaySchemeForTraversableEncodingTreeStates displayer = null;
		String variant = paramModel.getDisplayScheme();

		try {
			// TraversableEncodingTree encodingTree,
			// OnScreenKeyboardView onScreenKeyboardRepresentation,
			// IndirectSelectionFacilityInvocationParameterModel paramModel) {

			Class<?> firstParamType = encodingTree.getClass();
			Class<?> secondParamType = onScreenKeyboardRepresentation
					.getClass();
			Class<?> thirdParamType = paramModel.getClass();

			Class<?>[] params = { firstParamType, secondParamType,
					thirdParamType };
			// System.out.println("First param type: " + firstParamType );
			// System.out.println("2." +
			// encodingTree.getClass().getSuperclass());
			// System.out.println("3."
			// + encodingTree.getClass().getSuperclass().getSuperclass());
			// // System.out.println("a." +
			// sourceSymbols.getClass().getSuperclass());
			if (IS_VERBOSE) {
				System.out.println("Looking for constructor: " + variant);
				System.out.println("with arguments: 1. " + firstParamType
						+ "\t 2." + secondParamType + "\t 3." + thirdParamType);
			}
			Constructor<?> constr = Class.forName(variant).getConstructor(
					params);
			Object[] args = { encodingTree, onScreenKeyboardRepresentation,
					paramModel };
			displayer = (DisplaySchemeForTraversableEncodingTreeStates) constr
					.newInstance(args);
		} catch (Exception e) {
			// if class not found
			System.out
					.println("Could not construct an instance of: " + variant);
			e.printStackTrace();
			System.exit(0);
		}
		return displayer;
	}
}
