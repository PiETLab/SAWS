/*
 * Created on 17-Feb-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package driverApplications_ISF;

import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.prefs.Preferences;

import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;

/**
 * This app sets up a GUI for providing text input to a TTS module.
 * 
 * @author M. Baljko
 */
public class JavaVoiceDriver {

	public static void main(String[] args)
		throws
			IllegalArgumentException,
			EngineException,
			AudioException,
			EngineStateError {

		Synthesizer synth;

		javax.speech.EngineList elist = Central.availableSynthesizers(null);
		System.out.println("Here is a list of the available synthesizers:");
		System.out.println(elist);
		//Collections.sort(elist, new MyComparator());
		Collections.sort(elist, new Comparator() {
			public int compare(Object obj1, Object obj2) {
				SynthesizerModeDesc desc1 = (SynthesizerModeDesc) obj1;
				SynthesizerModeDesc desc2 = (SynthesizerModeDesc) obj2;
				return desc1.getModeName().compareToIgnoreCase(
					desc2.getModeName());
			}
		});
		System.out.println("Same list, sorted:");
		System.out.println(elist);

		Hashtable list = new Hashtable(elist.size());
		for (int i = 0; i < elist.size(); i++) {
			SynthesizerModeDesc desc = (SynthesizerModeDesc) elist.elementAt(i);
			list.put(desc.getModeName(), desc);
			System.out.println("Mode Name: " + desc.getModeName());
		}
		System.out.println(
			"Here is a list of the descriptions of available synthesizers (prepended with mode name):");
		System.out.println(list);

		// Approach #1
		Preferences preferences = Preferences.userRoot().node("dlb/JavaVoice");
		// I really don't get how this preference is available and already set-up
		System.out.print("User Root: ");
		System.out.println(Preferences.userRoot());
		System.out.print("User's preference wrt voice: ");
		System.out.println(preferences.get("voice.selected", ""));
		synth =
			Central.createSynthesizer(
				(SynthesizerModeDesc) list.get(
					preferences.get("voice.selected", "")));

		// 	Approach #2
		//		SynthesizerModeDesc desc;
		//		desc = (SynthesizerModeDesc) elist.elementAt(0);
		//		synth = Central.createSynthesizer(desc);
		//
		//	Approach #3
		//		synth =
		//			Central.createSynthesizer((SynthesizerModeDesc) elist.elementAt(0));

		//desc = (SynthesizerModeDesc) elist.elementAt(1);
		//SynthesizerModeDesc desc =
		//	new SynthesizerModeDesc( Locale.UK );
		//synth = Central.createSynthesizer( desc );

		synth =
			Central.createSynthesizer(
				(SynthesizerModeDesc) Central.availableSynthesizers(
					null).elementAt(
					1));

		synth =
			Central.createSynthesizer(
				(SynthesizerModeDesc) list.get(
					preferences.get("voice.selected", "")));

		System.out.println(
			"Here is the synthesizer that will actually be used:");
		System.out.println(synth);
		synth.allocate();
		synth.resume();

		synth.speakPlainText("hello hello hello", null);
		System.out.println("Done.");

	}

}
class MyComparator implements Comparator {
	public int compare(Object obj1, Object obj2) {
		SynthesizerModeDesc desc1 = (SynthesizerModeDesc) obj1;
		SynthesizerModeDesc desc2 = (SynthesizerModeDesc) obj2;
		return desc1.getModeName().compareToIgnoreCase(desc2.getModeName());
	}

}
