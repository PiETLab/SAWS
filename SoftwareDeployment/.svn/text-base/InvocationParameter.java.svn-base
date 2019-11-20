package SoftwareDeployment;

import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

import parameterSelectionWidgetsAndControllers.ParameterSelectionController;

import sun.misc.Launcher;

/**
 * This class encapsulates information about a command-line parameter. It was
 * created to provide services to the driver application for the indirect text
 * entry application, but in principle can provide services to any sort of
 * driver application.
 * 
 * This class and all of its sub-classes are predicated on the assumption that
 * any run-time parameter is composed of the following sequence of characters
 * (none of which can be spaces): 1. a prefix (e.g., the dash "-"), 2. an
 * argument signifier (e.g., "et" or any other word), and (optionally) 3. a
 * value (any sequence of digits or letters, but with no spaces).
 * 
 * It is understood that the value may be the name of a class. The user provides
 * the name of the class with the understanding that an instance of the class
 * will be created for the application.
 * 
 * @author mb
 * 
 */

public abstract class InvocationParameter {

	private String ARG_PREFIX = "-";

	protected String associatedValue = null; // getDefaultAssociatedValue();

	public String getArgumentPrefix() {
		return ARG_PREFIX;
	}

	/**
	 * @return The string that should be printed if help is required on this
	 *         invocation parameter
	 */
	public abstract String getVerboseExplaination();

	public abstract String getParameterPrefix();

	public String getParameterName() {
		return this.getClass().getName();
	};

	public abstract String getDefaultAssociatedValue();

	public boolean isMatch(String str) {
		return str.equals(getArgumentPrefix() + getParameterPrefix());
	}

	/**
	 * This method takes a array of run-time parameters, determines which one
	 * corresponds to the invocation parameter represented by this class and
	 * extracts the corresponding data. The run-time parameters are tokenized by
	 * spaces. If a invocation occurs multiple times, the first instance is
	 * used.
	 * 
	 * @param args
	 * @return
	 */
	public boolean extractMatchingParameter(String[] args) {
		for (String s : args) {
			// System.out.println("Looking at " + s);
			if (s.startsWith(getArgumentPrefix() + getParameterPrefix() + " ")) {
				s = s.substring((getArgumentPrefix() + getParameterPrefix())
						.length());
				s = s.trim();
				System.out.println("Parameter: " + this.getParameterPrefix()
						+ "\tValue: " + s + "\t(Default value: "
						+ this.getDefaultAssociatedValue() + ")");
				// System.out.println("Match found for: "
				// + this.getParameterPrefix() + " Value: " + s);
				this.setAssociatedValue(s);
				return true;
			}
		}
		System.out.println("Parameter: " + this.getParameterPrefix()
				+ "\tValue: not found.  Using value: "
				+ this.getAssociatedValue());
		return false;
	}

	public String getAssociatedValue() {
		if (associatedValue == null)
			return this.getDefaultAssociatedValue();
		else
			return associatedValue;
	}

	public void setAssociatedValue(String string) {
		associatedValue = string;
//		if (this.getGUIController() != null) {
//			this.getGUIController().setAssociatedValue(string);
//		} else {
//			System.out.println("cannot set associated value on controller");
//		}
	}

	public String getSummary() {
		return this.toString() + " (Default: "
				+ this.getDefaultAssociatedValue() + ")";
	}

	public String optionify(String[] options) {
		String s = "";
		for (String x : options) {
			// System.out.println(x);
			s = s + x + "|";
		}
		if (!s.equals("")) {
			s = s.substring(0, s.length() - 1);
		}
		s = "{" + s + "}";
		return s;
	}

	public String optionify(List<String> options) {
		String s = "";
		for (String x : options) {
			// System.out.println(x);
			s = s + x + "|";
		}
		if (!s.equals("")) {
			s = s.substring(0, s.length() - 1);
		}
		s = "{" + s + "}";
		return s;
	}

	public String toString() {
		return this.getParameterName() + ": " + getAssociatedValue();
	}

	public List<String> findAllSubclasses(String pckgname, String superclassname) {
		List<String> allSubclasses = new Vector<String>();
		// Code from JWhich
		// ======
		// Translate the package name into an absolute path
		String name = new String(pckgname);
		if (!name.startsWith("/")) {
			name = "/" + name;
		}
		name = name.replace('.', '/');

		// Get a File object for the package
		URL url = Launcher.class.getResource(name);
		File directory = new File(url.getFile());
		// New code
		// ======
		if (directory.exists()) {
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				// System.out.println("file: " + files[i]);

				// we are only interested in .class files
				if (files[i].endsWith(".class")) {
					// removes the .class extension
					String classname = files[i].substring(0,
							files[i].length() - 6);
					try {
						// System.out.println("for superclass: " + pckgname +
						// "."
						// + classname);
						Constructor<?>[] allConstructors = Class.forName(
								pckgname + "." + classname).getConstructors();
						for (Constructor<?> c : allConstructors) {
							// System.out.println("constructor: " + c);
							// System.out.println(c.getDeclaringClass());
							// System.out.println(Class.forName(
							// pckgname + "." + superclassname)
							// .isAssignableFrom(c.getDeclaringClass()));
							if (Class.forName(pckgname + "." + superclassname)
									.isAssignableFrom(c.getDeclaringClass())
									&& !c.getDeclaringClass().equals(
											Class.forName(pckgname + "."
													+ superclassname))) {
								allSubclasses.add(classname);
							}
						}
					} catch (ClassNotFoundException cnfex) {
						System.err.println(cnfex);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return allSubclasses;
	}

	public List<String> findAllSubclassesOldVersion(String pckgname,
			String superclassname, Class<?>[] constructorSig,
			Object[] constructorArgs) {
		List<String> allSubclasses = new Vector<String>();
		// Code from JWhich
		// ======
		// Translate the package name into an absolute path
		String name = new String(pckgname);
		if (!name.startsWith("/")) {
			name = "/" + name;
		}
		name = name.replace('.', '/');

		// Get a File object for the package
		URL url = Launcher.class.getResource(name);
		File directory = new File(url.getFile());
		// New code
		// ======
		if (directory.exists()) {
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				System.out.println("file: " + files[i]);

				// we are only interested in .class files
				System.out.println(files[i].endsWith(".class"));

				if (files[i].endsWith(".class")) {
					// removes the .class extension
					String classname = files[i].substring(0,
							files[i].length() - 6);
					try {
						// Try to create an instance of the object
						// Object o = Class.forName(pckgname + "." +
						// classname).newInstance();
						// a better way - find the constructor with the passed
						// sig and invoke it with the passes args

						// Class<?>[] params = {
						// Class.forName("OnScreenKeyboard") };
						System.out.println("for superclass: " + pckgname + "."
								+ classname);
						Constructor<?>[] allConstructors = Class.forName(
								pckgname + "." + classname).getConstructors();
						for (Constructor<?> c : allConstructors) {
							System.out.println("constructor: " + c);
							System.out.println(c.getDeclaringClass());
							System.out.println(Class.forName(
									pckgname + "." + superclassname)
									.isAssignableFrom(c.getDeclaringClass()));
							if (Class.forName(pckgname + "." + superclassname)
									.isAssignableFrom(c.getDeclaringClass())
									&& !c.getDeclaringClass().equals(
											Class.forName(pckgname + "."
													+ superclassname))) {
								allSubclasses.add(classname);
							}
						}

						// an alternative way, which requires an instance to
						// actually be created
						// Constructor<?>[] allConstructors2 = new
						// Constructor<?>[Class
						// .forName(pckgname + "." + classname)
						// .getConstructors().length];
						// Constructor<?> constr = Class.forName(
						// pckgname + "." + classname).getConstructor(
						// constructorSig);
						// // Object[] args = {onScreenKeyboard};
						// Object o2 = constr.newInstance(constructorArgs);
						// System.out.println("testing: " + classname);
						//
						// // if (o instanceof KeyboardLayout) {
						// if (Class.forName(pckgname + "." + superclassname)
						// .isInstance(o)) {
						// System.out.println(classname);
						// allSubclasses.add(classname);
						// }
					} catch (ClassNotFoundException cnfex) {
						System.err.println(cnfex);
						// } catch (InstantiationException iex) {
						// // We try to instantiate an interface
						// // or an object that does not have a
						// // default constructor
						// } catch (IllegalAccessException iaex) {
						// // The class is not public
					} catch (SecurityException e) {
						e.printStackTrace();
						// } catch (NoSuchMethodException e) {
						// e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						// } catch (InvocationTargetException e) {
						// e.printStackTrace();
					}
				}
			}
		}
		return allSubclasses;
	}

	public JComponent getGUIWidget() {
		return null;
	}

	public ParameterSelectionController getGUIController() {
		return null;
	}

}
