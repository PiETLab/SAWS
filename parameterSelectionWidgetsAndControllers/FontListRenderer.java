package parameterSelectionWidgetsAndControllers;

import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

// http://forums.dzone.com/java/601-ultimate-challenge-jcombobox.html
public class FontListRenderer extends JLabel implements ListCellRenderer {

	Map<String, Font> fontList = new HashMap<String, Font>();
	private float fontSize = 30f;
	private Font[] fontListAll;

	public FontListRenderer() {
		// fontList.put(MyListItemModel.FONT.ARIAL, new
		// Font("Arial Black Italic",
		// Font.PLAIN, 20));
		// fontList.put(MyListItemModel.FONT.WEBDINGS, new Font("Webdings",
		// Font.PLAIN, 12));
		// fontList.put(MyListItemModel.FONT.TIMESNEWROMAN, new Font(
		// "Times New Roman", Font.PLAIN, 14));
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// MyListItemModel item = (MyListItemModel) value;
		// setFont(fontList.get(item.getFont()));
		String item = (String) value;
		Font theFont = this.getFontToMatchStringBetter(item);
		if (theFont != null)
			theFont = theFont.deriveFont(fontSize);
		setFont(theFont);
		setText(item);
		return this;
	}

	public void addItem(String name, Font font) {
		fontList.put(name, font);
	}

	private Font getFontToMatchStringBetter(String s) {
		return fontList.get(s);
	}

	private Font getFontToMatchString(String s) {
		if (fontListAll == null)
			fontListAll = GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getAllFonts();
		Font foundFont = null;
		for (Font f : fontListAll) {
			if (f.getName().equals(s)) {
				foundFont = f;
				break;
			}
		}
		return foundFont;
	}
}

class MyListItemModel {

	public static enum FONT {
		ARIAL, TIMESNEWROMAN, WEBDINGS
	}

	private FONT font;
	private String text;

	public MyListItemModel(FONT newFont, String newText) {
		font = newFont;
		text = newText;
	}

	public FONT getFont() {
		return font;
	}

	public String getText() {
		return text;
	}
}
