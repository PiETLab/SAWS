package IndirectSelectionFacility;

import javax.swing.*;

import buttonLayouts.ButtonLayoutSpecification;

import encodingTrees.TraversableEncodingTree;
import encodingTrees.obsolete.TraversableEncodingTreeI;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.*;


/**
 * This class implements an on-screen keyboard with no interactivity.
 * 
 * @author Melanie Baljko
 */
public class ET_From_KB {

	private TraversableEncodingTreeI keyboard;

	/**
	 * 
	 * @param variant
	 */
	public ET_From_KB(String variant) {
		try {
			Constructor<?> constr = Class.forName(variant).getConstructor(
					(Class[]) null);
			keyboard = (TraversableEncodingTreeI) constr.newInstance((Object[]) null);
		} catch (Exception e) {
			// if class not found
			System.out
					.println("Could not construct an instance of: " + variant);
			e.printStackTrace();
			System.exit(0);
		}

	}

	/**
	 * This method returns the name of the indirect selection keyboard that this
	 * instance uses.
	 * 
	 * @return the name of the keyboard variant
	 */
	public String getKeyboardVariantName() {
		// return this.getClass().toString();
		return getKeyboard().getClass().getName();
	}

	public TraversableEncodingTreeI getKeyboard() {
		return keyboard;
	}
}
