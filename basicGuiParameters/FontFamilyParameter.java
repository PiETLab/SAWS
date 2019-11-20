package basicGuiParameters;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import parameterSelectionWidgetsAndControllers.FontSelectionController;
import parameterSelectionWidgetsAndControllers.FontSelectionView;
import parameterSelectionWidgetsAndControllers.ParameterSelectionController;


import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

/**
 * This class represents the invocation parameter that controls whether focus
 * advancement is to be used.
 * 
 * RESTRICTION: font family must be expressible as a single word (so "serif"
 * works, but not "Academy Letter")
 * 
 * @author mb
 * 
 */
public class FontFamilyParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.DisplayFontFamilyParameter;

	private String DEFAULT_FAMILY = FontSelectionController.getDefaultValue()
			.toString(); // "sansserif";
	private FontSelectionView fp;
	private FontSelectionController fc;

	public FontFamilyParameter() {
		Dimension availableRealEstate = Toolkit.getDefaultToolkit()
				.getScreenSize();
		// fp = new FontSelectionView(new Dimension(availableRealEstate.width,
		// availableRealEstate.height / 12));
		fp = new FontSelectionView();
		fc = new FontSelectionController(fp, this);

	}

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix() + getParameterPrefix()
				+ " X, where X is the name of the font family.  "
				+ "Default is " + DEFAULT_FAMILY + "";

	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "" + DEFAULT_FAMILY;
	}

	@Override
	public String getParameterName() {
		return this.getClass().getName();
	}

	public JPanel getGUIWidget() {
		// // FontPanel fp = new FontPanel(new Dimension(100, 400));
		// Dimension availableRealEstate = Toolkit.getDefaultToolkit()
		// .getScreenSize();
		// FontSelectionView fp = new FontSelectionView(new Dimension(
		// availableRealEstate.width, availableRealEstate.height / 12));
		// FontSelectionController fc = new FontSelectionController(fp);
		// // fp.add
		return fp;

	}

	public ParameterSelectionController getGUIController() {
		return fc;
	}

}
