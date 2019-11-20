package IndirectSelectionFacility;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import javax.swing.*;
import javax.swing.border.LineBorder;

import buttonLayouts.ButtonLayoutSpecification;
import buttonLayouts.ButtonLayoutSpecificationGenerator;

import sourceSymbolSet.SourceSymbol;

import treeDataStructure.SelectionGroup;

import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * This class implements a view of an on-screen keyboard. It can be instantiated
 * even without the encoding tree, but the encoding tree must be specified
 * before the display scheme can be set up
 * 
 * @author Melanie Baljko
 */
public class OnScreenKeyboardView extends JPanel {

	private static final long serialVersionUID = 3862229336357015317L;

	private ButtonLayoutSpecification keyboardContainer;
	private IndirectSelectionFacilityInvocationParameterModel paramModel;

	private boolean IS_VERBOSE = false;

	public OnScreenKeyboardView(
			ButtonLayoutSpecification keyboardDisplayContainer,
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		this.paramModel = paramModel;

		// this.setBorder(new LineBorder(Color.BLUE, 2));
		// this.setBackground(Color.YELLOW);
		this.setBackground(paramModel.getBackgroundColour());
		// this.setBackground(Color.BLUE);

		this.keyboardContainer = keyboardDisplayContainer;
		// this.keyboard.setFontFamily(fontFamilyToUse);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		// this.setLayout(new FlowLayout());

		// tells the display scheme what its container is, but the container has
		// not yet been made visible, this panel belongs to a parent, which has
		// not yet been created

		keyboardDisplayContainer.setOnScreenKeyboardView(this);

		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName() + ": Size : "
					+ this.getSize());
		}

	}

	public Component add(Component c) {
		// this.setBackground(Color.ORANGE);
		// System.out.println("Num elements: " + this.getComponentCount()
		// + " adding component c: " + c);
		return super.add(c);
	}

//	public void removeAll() {
//		System.out.println("Num elements: " + this.getComponentCount());
//
//		super.removeAll();
//		//this.makeDisplayEmpty();
//
//		System.out.println("Num elements: " + this.getComponentCount());
//
//	}

	/**
	 * This method returns the name of the indirect selection keyboard that this
	 * instance uses.
	 * 
	 * @return the name of the keyboard variant
	 */
	public String getKeyboardVariantName() {
		return getKeyboard().getClass().getName();
	}

	public ButtonLayoutSpecification getKeyboard() {
		return keyboardContainer;
	}

	/**
	 * Service provided by this class to update its own appearance, on the basis
	 * of the current state.
	 * 
	 * There are different schemes for displaying the state of the traversable
	 * encoding tree on the keyboard.
	 * 
	 * Rules are based on which node is the fokus parent.
	 * 
	 * 
	 */
	public void updateAppearance() {
		// System.exit(0);
		keyboardContainer.updateAppearance();
	}

	public void highlightCurrentSelectionGroupAsSelected() {
		keyboardContainer.highlightCurrentSelectionGroupAsSelected();
	}

	public void makeDisplayEmpty() {
		keyboardContainer.emptyPanel();
	}

	public void resizeOptimizedComponents() {
		keyboardContainer.resizeOptimizedComponents();
	}
}
