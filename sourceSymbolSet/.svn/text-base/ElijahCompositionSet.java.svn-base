package sourceSymbolSet;

import java.awt.Image;

import javax.swing.ImageIcon;

import customGUIComponentsISF.JIndirectSelectionButton;

/**
 * A makeshift probability distribution.
 * 
 * http://mathforum.org/epigone/sci.math.num-analysis/frayclonlax/76pm1t$6j@bgtnsc03
 * .worldnet.att.net "One-Gram Probability Distribution" from Alan G. Konheim's
 * "Cryptography -- A Primer," John Wiley, 1981, p. 16:
 * 
 * 
 * above distribution multiplied by 100000; assumes space occurs every 5
 * characters (therefore count in 100000/5); assume mean gloss length is 5 words
 * (mean of 5 characters each). Every 5*5+4 (spaces) = 29 characters, or
 * 100000/29 assume punctuation has middle-of-road frequency assume digits are
 * low frequency
 * 
 * See"mb/Documents/ProfContrib/ResearchProjects/TextCompFacilitiesDesign/spreadsheets/ProbabilityDistributions.xls"
 * 
 * @author mb
 * @modified by Fatima
 */
public class ElijahCompositionSet extends SourceSymbolSet {
	public static String imageFileA = System.getProperty("user.home")
			+ "/Documents/workspace/Prj-AssistiveTechnology/images/apple.jpg";
	public static String imageFileB = System.getProperty("user.home")
			+ "/Documents/workspace/Prj-AssistiveTechnology/images/butterfly.jpg";
	public static String imageFileC = System.getProperty("user.home")
			+ "/Documents/workspace/Prj-AssistiveTechnology/images/cat2.jpg";
	public static String imageFileD = System.getProperty("user.home")
			+ "/Documents/workspace/Prj-AssistiveTechnology/images/dog.jpg";

	// public static ImageIcon imgA;// = new ImageIcon(imageFileA);
	// public static ImageIcon imgB;// = new ImageIcon(imageFileB);
	// public static ImageIcon imgC;// = new ImageIcon(imageFileC);
	// public static ImageIcon imgD;// = new ImageIcon(imageFileD);

	public static ImageIcon imgA = new ImageIcon(imageFileA);
	public static ImageIcon imgB = new ImageIcon(imageFileB);
	public static ImageIcon imgC = new ImageIcon(imageFileC);
	public static ImageIcon imgD = new ImageIcon(imageFileD);

	public ElijahCompositionSet() {
		super();
		// ImageIcon imgA = new ImageIcon(imageFileA);
		// ImageIcon imgB = new ImageIcon(imageFileB);
		// ImageIcon imgC = new ImageIcon(imageFileC);
		// ImageIcon imgD = new ImageIcon(imageFileD);
		imgA.setImage(scaleUp(imgA));
		imgB.setImage(scaleUp(imgB));
		imgC.setImage(scaleUp(imgC));
		imgD.setImage(scaleUp(imgD));
		JIndirectSelectionButton a = JIndirectSelectionButton.VK_A;
		a.setIcon(imgA);
		a.setBorderPainted(false);
		a.setContentAreaFilled(false);
		JIndirectSelectionButton b = JIndirectSelectionButton.VK_B;
		b.setIcon(imgB);
		// b.setOpaque(true);
		b.setContentAreaFilled(false);
		JIndirectSelectionButton c = JIndirectSelectionButton.VK_C;
		c.setIcon(imgC);
		// c.setOpaque(true);
		c.setContentAreaFilled(false);

		JIndirectSelectionButton d = JIndirectSelectionButton.VK_D;
		d.setIcon(imgD);
		// c.setOpaque(true);
		d.setContentAreaFilled(false);

		this.addToSymbolSet(a, 0.107640493957604);
		this.addToSymbolSet(b, 0.0862609786700125);
		this.addToSymbolSet(c, 0.0706597107574457);
		this.addToSymbolSet(d, 0.0706597107574457);
		this.renormalize();
		if (!this.isSumsTo1()) {
			throw new RuntimeException(
					"The probability has a problem, the sum is :"
							+ this.getSumOfProbabilities());
		}
	}

	private Image scaleUp(ImageIcon orig) {
		return orig.getImage().getScaledInstance(200, -1, Image.SCALE_SMOOTH);

	}

	public static Image getRightImage(String theText) {
		Image img = ElijahCompositionSet.imgA.getImage();
		if (theText.equals("B")) {
			img = ElijahCompositionSet.imgB.getImage();
		}
		if (theText.equals("C")) {
			img = ElijahCompositionSet.imgC.getImage();
		}
		if (theText.equals("D")) {
			img = ElijahCompositionSet.imgD.getImage();
		}

		return img;
	}
}
