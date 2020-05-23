package driverApplication;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class invokeHelloWorld {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		String MAIN_APP = "helloWorld";

		Class classWithMainMethod = Class.forName(MAIN_APP);
		// TECHNIQUE #1
		// Method[] methods = classWithMainMethod.getMethods();
		// Method mainMethod = null;
		// for (Method m : methods) {
		// if (m.getName().equals("main")) {
		// mainMethod = m;
		// }
		// }

		// TECHNIQUE #2
		// String[] commandLineArgs = {};
		// mainMethod = classWithMainMethod.getMethod("main",
		// new Class[] { commandLineArgs.getClass() });

		// TECHNIQUE #3 (most elegant)
		Method mainMethod = classWithMainMethod.getMethod("main",
				//new Class[] { ({"a", "b"}).getClass() });
				new Class[] { (new String[0]).getClass() });

		System.out.println(mainMethod);

		String[] commandLineArgs = {};
		Object[] argsForMainMethod = { commandLineArgs };

		String[] commandLineArgs2 = { "a", "b", "c" };
		Object[] argsForMainMethod2 = { commandLineArgs2 };

		// Since the main method is static, the first argument may be null (the
		// first arg represents
		// the object on which the method is invoked)
		//
		// The second argument represents the arguments to be used for the
		// method call
		mainMethod.invoke(null, argsForMainMethod);
		mainMethod.invoke(null, argsForMainMethod2);
	}
}
