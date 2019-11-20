package actionScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.lang.reflect.Constructor;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;


import displayScheme.DisplaySchemeForTraversableEncodingTreeStates;

public class ActionSchemeGenerator {

	private boolean IS_VERBOSE = true;

	public ActionScheme createInstance(
			IndirectSelectionFacilityInvocationParameterModel paramModel,
			IndirectSelectionFacilityController isfController) {
		ActionScheme actionScheme = null;

		String variant = paramModel.getActionScheme();

		try {
			Class<?> firstParamType = paramModel.getClass();
			Class<?> secondParamType = isfController.getClass();
			Class<?>[] params = { firstParamType, secondParamType };
			// System.out.println("First param type: " + firstParamType );
			// System.out.println("2." +
			// encodingTree.getClass().getSuperclass());
			// System.out.println("3."
			// + encodingTree.getClass().getSuperclass().getSuperclass());
			// // System.out.println("a." +
			// sourceSymbols.getClass().getSuperclass());
			if (IS_VERBOSE) {
				System.out.println("Looking for constructor: " + variant);
				System.out.println("with arguments: 1. " + firstParamType);
				System.out.println("with arguments: 2. " + secondParamType);
			}
			Constructor<?> constr = Class.forName(variant).getConstructor(
					params);
			Object[] args = { paramModel, isfController };
			actionScheme = (ActionScheme) constr.newInstance(args);
		} catch (Exception e) {
			// if class not found
			System.out
					.println("Could not construct an instance of: " + variant);
			e.printStackTrace();
			System.exit(0);
		}
		return actionScheme;
	}

}
