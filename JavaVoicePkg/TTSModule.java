/*
 * Created on 18-Feb-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package JavaVoicePkg;

import java.util.Hashtable;
import java.util.prefs.Preferences;

import javax.speech.synthesis.Synthesizer;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
//import java.util.Locale;
import javax.speech.synthesis.SynthesizerModeDesc;

/**
 * @author Melanie
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TTSModule {
	private static Synthesizer synth;

	public TTSModule() {
		// Set up speech synthesizer
		javax.speech.EngineList elist = Central.availableSynthesizers(null);
		Hashtable list = new Hashtable(elist.size());
		for (int i = 0; i < elist.size(); i++) {
			SynthesizerModeDesc desc = (SynthesizerModeDesc) elist.elementAt(i);
			list.put(desc.getModeName(), desc);
		}
		Preferences preferences = Preferences.userRoot().node("dlb/JavaVoice");
		// I still don't really understand how this preference gets specified
		//System.out.print("User's preference wrt voice: ");
		//System.out.println(preferences.get("voice.selected", ""));
		try {
			synth =
				Central.createSynthesizer(
					(SynthesizerModeDesc) list.get(
						preferences.get("voice.selected", "")));
			synth.allocate();
			synth.resume();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AudioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EngineStateError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void speakPlainText(String t) {
		synth.speakPlainText(t, null);

	}

}
