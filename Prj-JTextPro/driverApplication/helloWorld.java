package driverApplication;
public class helloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(args.getClass().isArray());
		if (args.length == 0 ) {
			System.out.println("hello world, with no args");
		}
		if (args.length > 0 ) {
			System.out.println("hello world, with multiple args: ");
			for (int i = 0; i< args.length; i++) {
				System.out.println(args[i]);
			}
		}

	}

}
