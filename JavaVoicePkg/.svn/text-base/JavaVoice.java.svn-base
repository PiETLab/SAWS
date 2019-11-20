package JavaVoicePkg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Hashtable;
import java.util.prefs.Preferences;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class JavaVoice extends JFrame implements Comparator {
	private Synthesizer synth;
	private File readFile = null;
	Preferences preferences = Preferences.userRoot().node("dlb/JavaVoice");
	JComboBox voices;
	Hashtable list;
	public JavaVoice() {
		super("Java Voice Synthesizer");
		addWindowListener(new AppCloser());
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		try {
			javax.speech.EngineList elist = Central.availableSynthesizers(null);
			System.out.println("Here is a list of the available synthesizers:");
			System.out.println(elist);
			list = new Hashtable(elist.size());
			Collections.sort(elist, this);
			for (int i = 0; i < elist.size(); i++) {
				SynthesizerModeDesc desc =
					(SynthesizerModeDesc) elist.elementAt(i);
				model.addElement(desc.getModeName());
				list.put(desc.getModeName(), desc);
			}
			System.out.println("Here is a list of the descriptions of available synthesizers:");
			System.out.println( list );
			synth = Central.createSynthesizer((SynthesizerModeDesc) list.get(preferences.get("voice.selected","")));
			//SynthesizerModeDesc desc =
			//	new SynthesizerModeDesc( Locale.UK );
			//synth = Central.createSynthesizer( desc );
			System.out.println("Here is the synthesizer that will actually be used:");
			System.out.println(synth);
			synth.allocate();
			synth.resume();
		} catch (EngineException ex) {
			ex.printStackTrace();
		} catch (AudioException ex) {
			ex.printStackTrace();
		}
		final JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textArea);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		JToolBar toolBar = new JToolBar();
		JButton button = new JButton("Open");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser fileChooser =
					new JFileChooser(
						preferences.get(
							"voice.openDirectory",
							System.getProperty("user.dir")));
				if (fileChooser.showOpenDialog(null)
					== JFileChooser.APPROVE_OPTION) {
					readFile = fileChooser.getSelectedFile();
					preferences.put(
						"voice.openDirectory",
						readFile.getParent());
					try {
						FileReader reader = new FileReader(readFile);
						textArea.read(reader, readFile.getName());
						reader.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		toolBar.add(button);
		button = new JButton("Clear");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				textArea.setText("");
			}
		});
		toolBar.add(button);
		toolBar.add(button);
		button = new JButton("Start");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String str = textArea.getSelectedText();
				if (str != null && str.length() > 0) {
					synth.speakPlainText(str.toLowerCase(), null);
					textArea.setSelectionStart(0);
					textArea.setSelectionEnd(0);
				} else {
					str = textArea.getText();
					synth.speakPlainText(str.toLowerCase(), null);
				}
			}
		});
		toolBar.add(button);
		button = new JButton("Stop");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					synth.cancel();
				} catch (ArrayIndexOutOfBoundsException ex) {
				}
			}
		});
		toolBar.add(button);
		toolBar.addSeparator();
		toolBar.add(new JLabel("Voice:"));
		voices = new JComboBox(model);
		voices.setSelectedItem(preferences.get("voice.selected", ""));
		voices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					synth.deallocate();
					String str = (String) voices.getSelectedItem();
					preferences.put("voice.selected", str);
					synth =
						Central.createSynthesizer(
							(SynthesizerModeDesc) list.get(str));
					synth.allocate();
					synth.resume();
				} catch (EngineException ex) {
					ex.printStackTrace();
				} catch (AudioException ex) {
					ex.printStackTrace();
				}
			}
		});
		toolBar.add(voices);
		toolBar.addSeparator();
		button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					synth.deallocate();
				} catch (EngineException ex) {
					ex.printStackTrace();
				}
				preferences.putInt("voice.mainWindow.x", getLocation().x);
				preferences.putInt("voice.mainWindow.y", getLocation().y);
				preferences.putInt("voice.mainWindow.width", getSize().width);
				preferences.putInt("voice.mainWindow.height", getSize().height);
				System.exit(1);
			}
		});
		toolBar.add(button);
		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JTextField textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String txt = textField.getText();
				if (txt.length() > 0) {
					textArea.append(txt + '\n');
					synth.speakPlainText(txt, null);
					textField.setText("");
				}
			}
		});
		getContentPane().add(textField, BorderLayout.SOUTH);
		int x = preferences.getInt("voice.mainWindow.x", 1);
		int y = preferences.getInt("voice.mainWindow.y", 1);
		int width = preferences.getInt("voice.mainWindow.width", 640);
		int height = preferences.getInt("voice.mainWindow.height", 480);
		setLocation(x, y);
		setSize(width, height);
		setVisible(true);
	}
	public int compare(Object obj1, Object obj2) {
		SynthesizerModeDesc desc1 = (SynthesizerModeDesc) obj1;
		SynthesizerModeDesc desc2 = (SynthesizerModeDesc) obj2;
		return desc1.getModeName().compareToIgnoreCase(desc2.getModeName());
	}
	public static void main(String args[]) {
		new JavaVoice();
	}
	class AppCloser extends WindowAdapter {
		public void windowClosing(WindowEvent ev) {
			try {
				synth.deallocate();
			} catch (EngineException ex) {
				ex.printStackTrace();
			}
			System.exit(1);
		}
	}
}