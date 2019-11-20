package UtilityClassesISF;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import encodingTrees.*;

public class XMLFileWrapper {

	private File xmlFileLocation;
	private File xmlFile;
	private Document doc;
	private NodeList msgNodes;

	private final String MESSAGE_TAG_NAME = "message";

	private final String RECORD_TAG_NAME = "record";
	private boolean IS_VERBOSE = false;

	public XMLFileWrapper(File xmlFileLocation, File f) {
		this.xmlFileLocation = xmlFileLocation;
		this.xmlFile = f;
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// Document doc = db.parse(new File(xmlFile));
			doc = db.parse(new File(xmlFileLocation, xmlFile.getName()));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			System.exit(0);
		}

		msgNodes = doc.getElementsByTagName(MESSAGE_TAG_NAME);
		// NodeList levelNodes = doc.getElementsByTagName("level");
		// DETERMINE WHICH KEYBOARD WAS USED
	}

	public XMLFileWrapper(File xmlFile) {
		this.xmlFileLocation = xmlFile.getParentFile();
		this.xmlFile = xmlFile;
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = db.parse(xmlFile);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			System.exit(0);
		}

		msgNodes = doc.getElementsByTagName(MESSAGE_TAG_NAME);
		// NodeList levelNodes = doc.getElementsByTagName("level");
		// DETERMINE WHICH KEYBOARD WAS USED
	}

	public String getTargetText() {
		return null;
	}

	public String getSubjectID() {
		return null;
	}

	public String getTypeOfCond() {
		return null;
	}

	public String getConditionSequence() {
		return null;
	}

	public String getTrialNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getDwellPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Code getCode() {
		Code c = null;
		// System.out.println("LOOKING FOR ENCODING TREE");

		// look through all message element nodes, each of which has exactly one
		// child node.
		// find the message with the unique keyword "encodingTree"

		NodeList levelNodes = doc.getElementsByTagName("level");

		// List<String> interfaceActions = new Vector<String>();
		for (int i = 0; i < msgNodes.getLength(); i++) {
			Node n = msgNodes.item(i);
			NodeList elements = n.getChildNodes();
			for (int j = 0; j < elements.getLength(); j++) {
				Node theChildNode = elements.item(j);
				// System.out.println("Node: "
				// + n.getNodeName()
				// + " "
				// + n.getNodeType()
				// + " "
				// + n.getNodeValue()
				// + "\t"
				// + levelNodes.item(i).getChildNodes().item(0)
				// .getNodeValue() + "\tChild #:" + j + "> "
				// + theChildNode.getTextContent());
				String elementContents = theChildNode.getTextContent();
				if (containsEncodingTree(elementContents)) {
					// System.out.println("FOUND");
					// strip off the keyword
					elementContents = elementContents.replaceAll(Code
							.getLoggerFileUniqueIdentifier(), "");
					StringTokenizer tok = new StringTokenizer(elementContents,
							"\n");
					List<CodeWord> cw = new Vector<CodeWord>();
					while (tok.hasMoreTokens()) {
						String currCodeWordAsText = tok.nextToken();
						CodeWord cwFromText = new CodeWord(currCodeWordAsText);
						cw.add(cwFromText);
						// System.out
						// .println(cwFromText.toStringParseableFormat());
					}
					c = new Code(cw);
				}
			}
			// Node n2 = elements.item(0);
			// output.println("> " + n2.getTextContent());
			// if (Level.parse(elements.item(LEVEL_INDEX).getTextContent())
			// .equals(Level.FINER)) {
			// // output.println(elements.item(MESSAGE_INDEX).getTextContent());
			// interfaceActions.add((elements.item(MESSAGE_INDEX)
			// .getTextContent()));
			// }
		}
		return c;
	}

	private boolean containsEncodingTree(String elementContents) {
		return elementContents.contains(Code.getLoggerFileUniqueIdentifier());
	}

	public List<String> getAllInterfaceActions() {

		// the indices below were determined by printing out all
		// children nodes
		final int LEVEL_INDEX = 9;
		final int MESSAGE_INDEX = 17;

		NodeList recordNodes = doc.getElementsByTagName(RECORD_TAG_NAME);

		List<String> interfaceActions = new Vector<String>();
		for (int i = 0; i < recordNodes.getLength(); i++) {
			// if (containsEncodingTree(elementContents)) {

			Node n = recordNodes.item(i);
			NodeList elements = n.getChildNodes();
			// for (int j = 0; j < elements.getLength(); j++) {
			// Node n2 = elements.item(j);
			// System.out.println(j + "> " + n2.getTextContent());
			// }
			// Node n2 = elements.item(0);
			// output.println("> " + n2.getTextContent());
			if (Level.parse(elements.item(LEVEL_INDEX).getTextContent())
					.equals(Level.FINER)) {

				String elementContents = elements.item(MESSAGE_INDEX)
						.getTextContent();

				if (!containsEncodingTree(elementContents)) {
					if (IS_VERBOSE) {
						System.out.println(this.getClass().getName()
								+ ": Contents Extracted: " + elementContents);
						// System.out.println(this.getClass().getName()
						// + ": is Encoding Tree?  "
						// + containsEncodingTree(elementContents));
					}
					interfaceActions.add(elementContents);
				}
			}
		}
		return interfaceActions;
	}

	/**
	 * This method checks each of the nodes in the passed node list and
	 * determines whether any of them mention the passed keyword and the
	 * corresponding value
	 * 
	 * e.g., keyboardVariant=blah
	 * 
	 * 
	 * It assumes that the text content is of the form <keyword>:<relevant part>
	 * 
	 * (delimited by colon with no additional spaces)
	 * 
	 * @param nl
	 * @return
	 */
	private String getValue(NodeList nl, String keyWord) {
		final String DELIM = ":";
		String correspondingValue = null;
		// find which keyboard variant was used in this trial
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getTextContent().indexOf(keyWord) != -1) {
				String s = nl.item(i).getTextContent();
				// output.println(s);
				String found = s.substring(s.indexOf(DELIM) + 1);
				// System.out.println(found);
				correspondingValue = found;
			}
		}
		return correspondingValue;
	}

	public String getConciseName() {
		return xmlFile.getName();
	}

}
