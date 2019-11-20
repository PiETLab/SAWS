package jUnitTestsISF;

import encodingTrees.TraversableEncodingTreeGenerator;
import encodingTrees.obsolete.TraversableEncodingTreeI;
import sourceSymbolSet.SourceSymbolSet;
import sourceSymbolSet.SourceSymbolSetGenerator;
import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard_KBLthenET;
import indirectTextEntrySystemVariants.RowColUnigram;
import junit.framework.TestCase;

/**
 * A suite of tests designed to test the capability to create an encoding tree
 * automatically from a Selectable Set
 * 
 * @author mb
 * 
 */
public class EncodingTreeUC extends TestCase {

	public EncodingTreeUC(String name) {
		super(name);
	}

	/**
	 * This runs before each test in this test case (a test case can run
	 * multiple tests; Each test runs in its own fixture)
	 * 
	 */
	protected void setUp() {
	}

	public void test1() {
		SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
		SourceSymbolSet sourceSymbols = sssGen
				.createInstance("sourceSymbolSet."
						+ "SourceSymbolSet_8SymbolsEquiprobable");

		TraversableEncodingTreeGenerator tetGen = new TraversableEncodingTreeGenerator();
		TraversableEncodingTreeI encodingTree = tetGen.createInstance(
				"encodingTrees." + "HuffmanUnequalCosts", sourceSymbols,
				new Integer(8));
//		System.out.printf("%s\t%s\t%s\n", encodingTree.getCode()
//				.getMeanEncodingCost());
		System.out.println(encodingTree.getCode().toStringCostTable());


		// System.out.printf("%s\t%s\t%s\n", sourceSymbols
		// .getMinExpectedCostLowerBound(i), encodingTree.getCode()
		// .getMeanEncodingCost(), sourceSymbols
		// .getMinExpectedCostUpperBound(i));

		// assertNoiselessCodingTheorem(sourceSymbols);
	}

	// public void test2() {
	// SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
	// SourceSymbolSet sourceSymbols = sssGen
	// .createInstance("sourceSymbolSet."
	// + "SourceSymbolSet_10SymbolsOnly");
	// assertNoiselessCodingTheorem(sourceSymbols);
	// }
	//	
	// public void test3() {
	// SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
	// SourceSymbolSet sourceSymbols = sssGen
	// .createInstance("sourceSymbolSet."
	// + "SourceSymbolSet_16SymbolsOnly");
	// assertNoiselessCodingTheorem(sourceSymbols);
	// }
	//
	// public void test4() {
	// SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
	// SourceSymbolSet sourceSymbols = sssGen
	// .createInstance("sourceSymbolSet."
	// + "SourceSymbolSet_Venkatagiri99_Hypothesized");
	// assertNoiselessCodingTheorem(sourceSymbols);
	// }
	//		
	// public void test5() {
	// SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
	// SourceSymbolSet sourceSymbols = sssGen
	// .createInstance("sourceSymbolSet."
	// + "SourceSymbolSet_KonheimCharactersAndSpaceOnly");
	// assertNoiselessCodingTheorem(sourceSymbols);
	// }
	//
	//
	// public void testIdentity() {
	// // assertSame(a, Node.getCity("A"));
	// // assertSame(Node.getCity(Node.getIndexForName("A")), a);
	// // assertDifferent(Node.getCity("D"), Node.getCity("E"));
	// }
	//
	// public void testEquality() {
	// // assertEquals(Node.getCity("A"), Node.getCity("A"));
	// // assertEquals(Node.getCity("A"), a);
	// // assertEquals(a, a);
	// //
	// // assertNotEquals(a, b);
	// }
	//
	// public void testInvalidValueOf() {
	// // assertEquals(Node.getCity(" "), null);
	// // getCity does not throw Exception
	// // try {
	// // Node.getCity(" ");
	// // fail("should have failed");
	// // } catch (IllegalArgumentException iae) {
	// // // this is the successful case
	// // }
	// }
	//
	// public void testGetIndex() {
	// // assertIndexEquals("A", 0);
	// // assertIndexEquals("B", 1);
	// // assertIndexEquals("C", 2);
	// // assertIndexEquals("D", 3);
	// // assertIndexEquals("E", 4);
	// // assertIndexEquals("F", 5);
	// }
	//
	// public void testName() {
	// // assertEquals(c.getName(), c.getName());
	// // assertNotEquals(d.getName(), e.getName());
	// }
	//
	// // public void testCompareTo() {
	// // assertTrue("incorrect comparison", a.compareTo(a) == 0);
	// //
	// // assertTrue("incorrect comparison", Node.A.compareTo(Node.B) < 0);
	// // assertTrue("incorrect comparison", Node.B.compareTo(Node.C) < 0);
	// // assertTrue("incorrect comparison", Node.A.compareTo(Node.C) < 0);
	// //
	// // assertTrue("incorrect comparison", Node.B.compareTo(Node.A) > 0);
	// // assertTrue("incorrect comparison", Node.C.compareTo(Node.B) > 0);
	// // assertTrue("incorrect comparison", Node.C.compareTo(Node.A) > 0);
	// // }
	//
	// private void assertIndexEquals(String s, int expectedIndex) {
	// // assertEquals(expectedIndex, Node.getCity(s).getIndex());
	// }
	//
	// //
	// // private void assertNotEquals(char c1, char c2) {
	// // assertTrue("expected not equal", c1 != c2);
	// // }
	// //
	// private void assertNotEquals(Object o1, Object o2) {
	// // assertTrue("expected not equal", !o1.equals(o2));
	// }
	//
	// private void assertDifferent(Object o1, Object o2) {
	// // assertTrue("expected different", o1 != o2);
	// }
	//
	// private void assertBounds(Double val, Double lowerBound, Double
	// upperBound) {
	// assertTrue("lower bound not valid", lowerBound <= val);
	// assertTrue("upper bound not valid", upperBound >= val);
	// }
	//
	// private void assertNoiselessCodingTheorem(SourceSymbolSet sourceSymbols)
	// {
	// System.out.printf("lB\tMEC\tuB\n");
	// TraversableEncodingTreeGenerator tetGen = new
	// TraversableEncodingTreeGenerator();
	// for (int i = 2; i <= sourceSymbols.size(); i++) {
	// TraversableEncodingTreeI encodingTree = tetGen.createInstance(
	// "encodingTrees." + "HuffmanEqualCosts", sourceSymbols,
	// new Integer(i));
	// System.out.printf("%s\t%s\t%s\n", sourceSymbols
	// .getMinExpectedCostLowerBound(i), encodingTree.getCode()
	// .getMeanEncodingCost(), sourceSymbols
	// .getMinExpectedCostUpperBound(i));
	// assertBounds(encodingTree.getCode().getMeanEncodingCost(),
	// sourceSymbols.getMinExpectedCostLowerBound(i),
	// sourceSymbols.getMinExpectedCostUpperBound(i));
	// }
	// }

}
