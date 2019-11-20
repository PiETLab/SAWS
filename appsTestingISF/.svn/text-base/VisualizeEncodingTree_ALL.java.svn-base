/*
 * Created on 10-Aug-2004
 *
 */
package appsTestingISF;

import indirectTextEntrySystemVariants.TCFVariants;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard;

/**
 * 
 * This application is used to visualize a containment hierarchy that is
 * constructed for a virtual keyboard, so that it can be examined and validated.
 * Presently, the particular virtual keyboard is hard-coded in this app.
 * 
 * The output from this application is meant to be pasted into a LaTeX file. The
 * output for this visualization is constructed through the invocation of one or
 * more toString*() variant methods on the instance of the text composition
 * facility. See the API for IndirectSelectionKeyboard to see the possible
 * toString* variants.
 * 
 * [Nothing more specific is given here, since this application is modified on
 * an ad-hoc basis]
 * 
 * @author Melanie Baljko, 2004
 * 
 */
public class VisualizeEncodingTree_ALL {

	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException, ClassNotFoundException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {

		for (String kbVariant : TCFVariants.getVariants()) {
			Constructor<?> constr = Class.forName(kbVariant).getConstructor(
					(Class[]) null);
			AbstractOnScreenIndirectSelectionKeyboard keyboard = (AbstractOnScreenIndirectSelectionKeyboard) constr
					.newInstance((Object[]) null);
			keyboard.getEncodingTree().getRoot().propogateLabels(false);

			System.out.println(keyboard.getName());
			UtilityClassesISF.PrintingUtilities.generateLaTeXFile(keyboard, "tex");
		}
		System.out.println("Done.");
	}
}
