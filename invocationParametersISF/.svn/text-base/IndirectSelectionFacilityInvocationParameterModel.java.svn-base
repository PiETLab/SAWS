package invocationParametersISF;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

import parameterSelectionWidgetsAndControllers.ColourSelectionController;

import basicGuiParameters.*;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;
import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterFile;
import SoftwareDeployment.InvocationParameterModel;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import displayScheme.DisplaySchemeForTraversableEncodingTreeStates;
import experimentHelper.ExperimentHelper;

/**
 * This class implements a model that keeps track of all of the various
 * parameters that can be modified when instantiating a indirect text entry
 * facility.
 * 
 * Each possible parameter is instantiated by a class that extends the
 * InvocationParameter class.
 * 
 * Use Focus Advancement: true/false
 * 
 * If false, then need n input actions.
 * 
 * If true, then specify Use Passive: true/false
 * 
 * If false, then need x2 input actions (map to select, adv focus)
 * 
 * If true, then need x1 input action
 * 
 * 
 */

public class IndirectSelectionFacilityInvocationParameterModel extends
		InvocationParameterModel {

	private DwellPeriodParameter dwellParam;
	// private InvocationParameter helpParam;
	private UseExperimentLoggingParameter expLoggingParam;
	private UserIDParameter userIDParam;

	// the following are needed for the construction of the encoding tree (in
	// all of the possible ways)
	private EncodingTreeManualAlgorithmicVariantParameter encodingTreeManualOrAlgorithmicParameter;
	private ManualEncodingTreeParameter manualEncodingTreeParam;
	private EncodingTreeAlgorithmicConstructionTechniqueParameter encodingTreeAlgorithmicConstructionTechniqueParam;
	private EncodingAlphabetParameter encodingAlphabetParam;
	private SourceSymbolSetParameter sourceSymbolSetParam;
	private FileCacheParameter fileCacheParam;

	// the following concern the appearance and behaviour of the interface to
	// the users

	private FontFamilyParameter fontFamilyParam;
	// private DisplayFontSizeParameter fontSizeParam;
	private RemoveButtonDecoratorParameter removeButtonDecoratorParam;
	private KeyboardRealEstateProportionParam keyboardRealEstateProportionParam;
	private TitleParameter titleParam;
	private FullScreenModeParameter fullScreenModeParam;
	private HorizontalAlignmentParameter horizontalAlignmentParameter;
	private VerticalAlignmentParameter verticalAlignmentParameter;
	private BackgroundColourParameter backgroundColourParameter;
	private TextColourParameter textColourParameter;
	private KeyboardAtTopParameter keyboardAtTopParam;

	private DisplaySchemeParameter displaySchemeParam;
	private ButtonLayoutSpecificationParameter keyboardVariantParam;
	private ActionSchemeParameter actionSchemeParameter;
	private FocusAdvancementParameter focusAdvancementParam;

	/**
	 * 
	 * 
	 * 
	 * @param args
	 *            of the form {"-fa", "active", "-dt", "1050"} as would be
	 *            derived from command-line invocation
	 */
	public IndirectSelectionFacilityInvocationParameterModel(String[] args) {
		super(args);
		// fh = new FileHandler("vocaLog." + getNow() + ".xml");
		// logger.addHandler(fh);
		// logger.setLevel(Level.ALL);

		// if (args.length == 0) {
		// output.println("Type \"VoiceOutputCommunicationAidApplication \""
		// + ARG_PREFIX + HELP_ARG + "\" for help");
		// System.exit(0);
		// }
		// if helpParam.getAssociatedValue()

		// if (args.length == 1 && helpParam.isMatch(args[0])) {
		// System.out.println(super.generateVerboseExplaination());
		// System.exit(0);
		// }

		if (this.isWithLogging()) {
			// experimentHelper = new ExperimentHelper();
		}
		// ExperimentHelper.setTurnedOn(false);
		// ExperimentHelper.setTurnedOn(true);

		System.out.println(super.generateSummary());
	}

	public IndirectSelectionFacilityInvocationParameterModel(String args) {
		super(args);
	}

	public IndirectSelectionFacilityInvocationParameterModel() {
		super("");
	}

	public IndirectSelectionFacilityInvocationParameterModel(
			InvocationParameterFile file) {
		String s2 = file.extractFirstNonCommentLine();
		System.out.println("first non comment line: " + s2);
		super.setPassedParameters(s2);
	}

	public String getKeyboardVariant() {
		return keyboardVariantParam.getAssociatedValue();
	}

	public String getUserId() {
		return userIDParam.getAssociatedValue();
	}

	public boolean isWithPassiveFocusAdvancement() {
		return focusAdvancementParam.getAssociatedValue().equals(
				FocusAdvancementParameter.PASSIVE);
		// return Boolean.parseBoolean(passiveFocusAdvancementParam
		// .getAssociatedValue());
	}

	public boolean isWithActiveFocusAdvancement() {
		return focusAdvancementParam.getAssociatedValue().equals(
				FocusAdvancementParameter.ACTIVE);
	}

	public Runnable getPassiveFokusAdvancer(
			IndirectSelectionFacilityController controller) {
		final int dwellTime = this.getDwellTime();
		final IndirectSelectionFacilityController controller2 = controller;

		return new Runnable() {
			public void run() {
				(new PassiveFokusAdvancerSwing(controller2, dwellTime))
						.startEventGenerator();
			}
		};
	}

	// public boolean isWithSimulatedUser() {
	// return Boolean.parseBoolean(
	// .substring((ARG_PREFIX + TMP).length()));
	// ;
	// }

	public int getDwellTime() {
		return Integer.parseInt(dwellParam.getAssociatedValue());
	}

	public boolean isWithLogging() {
		return Boolean.parseBoolean(expLoggingParam.getAssociatedValue());
		// return
		// Boolean.parseBoolean(focusAdvancementParam.getAssociatedValue());
	}

	public boolean isWithFocusAdvancement() {
		return !focusAdvancementParam.getAssociatedValue().equals(
				FocusAdvancementParameter.DIRECT);
		// return
		// Boolean.parseBoolean(focusAdvancementParam.getAssociatedValue());
	}

	public boolean isTrialStarted() {
		return ExperimentHelper.metaKeyHasBeenPressed();
	}

	public boolean isEncodingTreeToBeDerivedFromManualSpecification() {
		return encodingTreeManualOrAlgorithmicParameter.getAssociatedValue()
				.equals(EncodingTreeManualAlgorithmicVariantParameter.MANUAL);
	}

	public boolean isEncodingTreeToBeDerivedFromAlgorithm() {
		return encodingTreeManualOrAlgorithmicParameter
				.getAssociatedValue()
				.equals(
						EncodingTreeManualAlgorithmicVariantParameter.ALGORITHMIC);
	}

	public boolean isEncodingTreeToBeDerivedFromFileCache() {
		return encodingTreeManualOrAlgorithmicParameter
				.getAssociatedValue()
				.equals(
						EncodingTreeManualAlgorithmicVariantParameter.FILE_CACHE);
	}

	// public boolean isETThenKB() {
	// return encodingTreeManualOrAlgorithmicParameter
	// .getAssociatedValue()
	// .equals(
	// EncodingTreeManualAlgorithmicVariantParameter.ALGORITHMIC);
	// }

	public String getEncodingTreeAlgorithmicConstructionTechnique() {
		return encodingTreeAlgorithmicConstructionTechniqueParam
				.getAssociatedValue();
	}

	public Integer getEncodingAlphabetSize() {
		return Integer.parseInt(encodingAlphabetParam.getAssociatedValue());
	}

	public String getSourceSymbolSet() {
		return sourceSymbolSetParam.getAssociatedValue();
	}

	@Override
	protected List<InvocationParameter> defineAllParameters() {
		// helpParam = new VerboseHelpParameter();

		actionSchemeParameter = new ActionSchemeParameter();
		displaySchemeParam = new DisplaySchemeParameter();
		dwellParam = new DwellPeriodParameter();
		encodingAlphabetParam = new EncodingAlphabetParameter();
		encodingTreeAlgorithmicConstructionTechniqueParam = new EncodingTreeAlgorithmicConstructionTechniqueParameter();
		expLoggingParam = new UseExperimentLoggingParameter();
		focusAdvancementParam = new FocusAdvancementParameter();
		fontFamilyParam = new FontFamilyParameter();
		// fontSizeParam = new DisplayFontSizeParameter();
		keyboardRealEstateProportionParam = new KeyboardRealEstateProportionParam();
		keyboardVariantParam = new ButtonLayoutSpecificationParameter();
		manualEncodingTreeParam = new ManualEncodingTreeParameter();
		removeButtonDecoratorParam = new RemoveButtonDecoratorParameter();
		sourceSymbolSetParam = new SourceSymbolSetParameter();
		fileCacheParam = new FileCacheParameter();
		encodingTreeManualOrAlgorithmicParameter = new EncodingTreeManualAlgorithmicVariantParameter();
		userIDParam = new UserIDParameter();
		titleParam = new TitleParameter();
		fullScreenModeParam = new FullScreenModeParameter();
		horizontalAlignmentParameter = new HorizontalAlignmentParameter();
		verticalAlignmentParameter = new VerticalAlignmentParameter();
		backgroundColourParameter = new BackgroundColourParameter();
		textColourParameter = new TextColourParameter();
		keyboardAtTopParam = new KeyboardAtTopParameter();

		List<InvocationParameter> allParameters = new Vector<InvocationParameter>();
		allParameters.add(displaySchemeParam);
		allParameters.add(dwellParam);
		allParameters.add(encodingAlphabetParam);
		allParameters.add(encodingTreeAlgorithmicConstructionTechniqueParam);
		allParameters.add(expLoggingParam);
		allParameters.add(focusAdvancementParam);
		allParameters.add(fontFamilyParam);
		// allParameters.add(fontSizeParam);
		allParameters.add(keyboardVariantParam);
		allParameters.add(keyboardRealEstateProportionParam);
		allParameters.add(manualEncodingTreeParam);
		allParameters.add(removeButtonDecoratorParam);
		allParameters.add(sourceSymbolSetParam);
		allParameters.add(fileCacheParam);
		allParameters.add(encodingTreeManualOrAlgorithmicParameter);
		allParameters.add(userIDParam);
		allParameters.add(titleParam);
		allParameters.add(fullScreenModeParam);
		allParameters.add(horizontalAlignmentParameter);
		allParameters.add(verticalAlignmentParameter);
		allParameters.add(backgroundColourParameter);
		allParameters.add(textColourParameter);
		allParameters.add(actionSchemeParameter);
		allParameters.add(keyboardAtTopParam);
		return allParameters;
	}

	public String getManualEncodingTreeSpecification() {
		return manualEncodingTreeParam.getAssociatedValue();
	}

	public boolean isRemoveButtonDecorators() {
		return Boolean.parseBoolean(removeButtonDecoratorParam
				.getAssociatedValue());
	}

	public String getFontFamily() {
		return fontFamilyParam.getAssociatedValue();
	}

	public double getKeyboardRealEstateProportion() {
		return Double.parseDouble(keyboardRealEstateProportionParam
				.getAssociatedValue());
	}

	public String getDisplayScheme() {
		return displaySchemeParam.getAssociatedValue();
	}

	public String getTitle() {
		return titleParam.getAssociatedValue();
	}

	public boolean isFullScreen() {
		// return false;
		return Boolean.parseBoolean(fullScreenModeParam.getAssociatedValue());
	}

	public int getHorizontalAlignment() {
		return Integer.parseInt(horizontalAlignmentParameter
				.getAssociatedValue());
	}

	public int getVerticalAlignment() {
		return Integer
				.parseInt(verticalAlignmentParameter.getAssociatedValue());
	}

	public boolean isUsingAssistiveTechnologyMouseLikeDevice() {
		return true;
	}

	public Color getBackgroundColour() {
		return getColorFromString(backgroundColourParameter
				.getAssociatedValue());
	}

	/**
	 * 
	 * 
	 * @param colourValue
	 * @return true is the string is in the format:
	 *         java.awt.Color[r=255,g=255,b=255]
	 */
	private boolean isRGBFormat(String colourValue) {
		return colourValue.indexOf("Color") != -1;
	}

	private Color getColorFromString(String colourValue) {
		Color tmp = null;
		try {
			Class t = Class.forName("java.awt.Color");
			if (isRGBFormat(colourValue)) {
				int[] vals = ColourSelectionController
						.getRGBValuesFromString(colourValue);
				Class<?> firstParamType = java.lang.Integer.TYPE;
				Class<?> secondParamType = java.lang.Integer.TYPE;
				Class<?> thirdParamType = java.lang.Integer.TYPE;
				Class<?>[] params = { firstParamType, secondParamType,
						thirdParamType };
				Constructor<?> constr = t.getConstructor(params);
				Object[] args = { vals[0], vals[1], vals[2] };
				tmp = (Color) constr.newInstance(args);
			} else { // assume the associated value is a string that corresponds
				// to a static field of the Color class
				Field f = t.getField(colourValue);
				tmp = (Color) f.get(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return tmp;
	}

	public Color getTextColour() {
		return getColorFromString(textColourParameter.getAssociatedValue());
	}

	public boolean showFontHints() {
		return false;
	}

	public String getActionScheme() {
		return actionSchemeParameter.getAssociatedValue();
		// return "actionScheme.PassiveFocusAdvancementActionScheme_TrialMode2";
	}

	public boolean isKeyboardAtTop() {
		return Boolean.parseBoolean(keyboardAtTopParam.getAssociatedValue());
	}

	public String getFullFileNameOfCache() {
		// String path = System.getProperty("user.home");
		// String theFileName = "HuffmanEqualCostsK5.obj";
		// return path + File.separator + theFileName;
		return fileCacheParam.getAssociatedValue();
	}

}
