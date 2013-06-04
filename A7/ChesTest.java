/*******************************************************************//**
 *      @author Ches Koblents
 *      @email ches@koblents.com
 *      @date May 13, 2013
 *      @file TestFListInteger.java
 *      Assignment 2, CS3500, Summer 1 2013
 **********************************************************************/

/**
 *	TestFListInteger - black box test class for FListInteger.
 *	Tests creation, non-overriden methods (static), and overriden
 *		methods (dynamic) as defined in the specification.
 *	Tests the same methods again to ensure there are no side effects
 *		(since created objects must be immutable.)
 *	Contains a main function to launch the program,
 *		some instance variables for keeping track of testing,
 *		several lists used for testing,
 *		several test functions,
 *		and several auxilary test helper functions.
 *	Auxiliary test helper functions (such as assertTrue) were copied from
 *		the professor's example for assignment 1, as was the structure
 *		of this test program.
 */
// errors:
// hash code can't test !=
// test equals versus null, not correct object
// test equals against two identical lists, first element different,
//	last different, middle different
public class TestFListInteger {
	/**
	 *	Main method - launch program, instantiate TestFListInteger,
	 *		and run tests.
	 *	@param args	ignored
	 */
	public static void main(String[] args) {
		TestFListInteger test = new TestFListInteger();

		test.creation();
		test.nonoverride();
		test.override();
		// test again for side effects
		test.nonoverride();
		test.override();
		test.summarize();
	}

	/** Number of tests */
	private int totalTests;
	/** Number of tests resulting in error */
	private int totalErrors;
	/** Integer representing 8 */
	private final Integer eight	= new Integer(8);
	/** Integer representing 9 */
	private final Integer nine	= new Integer(9);
	/** Integer representing 10 */
	private final Integer ten	= new Integer(10);
	/** Integer representing 11 */
	private final Integer eleven	= new Integer(11);

	/** Empty list */
	private FListInteger f0;
	/** List containined { 8 } */
	private FListInteger f1;
	/** List containined { 9, 8 } */
	private FListInteger f2;
	/** List containined { 10, 9, 8 } */
	private FListInteger f3;
	/** List containined { 11, 10, 9, 8 } */
	private FListInteger f4;

	/** List containined { 9 } */
	private FListInteger fs0;
	/** List containined { 9, 9 } */
	private FListInteger fs1;
	/** List containined { 10, 9, 9 } */
	private FListInteger fs2;
	/** List containined { 10, 8, 9 } */
	private FListInteger fs3;
	/** List containined { 11, 10, 9, 11 } */
	private FListInteger fs4;
	/** List containined { 11, 10, 9, 11 } */
	private FListInteger fs5;
	/** List containined { 11 } */
	private FListInteger fs6;

	/**
	 *	Print out number of tests, number of failures.
	 *	Auxiliary test function copied from professor's code sample.
	 */
	private void summarize () {
		System.out.println();
		System.out.println (totalErrors + " errors found in " +
				totalTests + " tests.");
	}

	/**
	 *	Constructur.
	 *	Set tests count, error count to zero.
	 */
	public TestFListInteger () {
		totalTests = 0;
		totalErrors = 0;
	}

	/**
	 *	Creation tests.
	 *	Tests emptyList, add, set.
	 */
	private void creation() {
		try {
			// create lists with emptyList, add
			f0 = FListInteger.emptyList();
			f1 = FListInteger.add(f0, eight);
			f2 = FListInteger.add(f1, nine);
			f3 = FListInteger.add(f2, ten);
			f4 = FListInteger.add(f3, eleven);

			// create lists with set
			fs0 = FListInteger.set(f1, 0, nine);
			fs1 = FListInteger.set(f2, 1, nine);
			fs2 = FListInteger.set(f3, 2, nine);
			fs3 = FListInteger.set(fs2, 1, eight);
			fs4 = FListInteger.set(f4, 3, eleven);
			fs5 = FListInteger.set(fs4, 3, eleven);
			fs6 = FListInteger.set(f1, 0, eleven);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error during creation.");
		}
	}

	/**
	 *	Test static functions (not ones inherited from Object).
	 *	Tests isEmpty, size, get, contains.
	 */
	private void nonoverride() {
		try {
			// test empty
			assertTrue("f0 empty", FListInteger.isEmpty(f0));
			assertFalse("f1 nonempty", FListInteger.isEmpty(f1));
			assertFalse("fs0 nonempty", FListInteger.isEmpty(fs0));

			// test size
			assertTrue("f0 size 0", FListInteger.size(f0) == 0);
			assertTrue("f1 size 1", FListInteger.size(f1) == 1);
			assertTrue("f2 size 2", FListInteger.size(f2) == 2);
			assertTrue("f3 size 3", FListInteger.size(f3) == 3);
			assertTrue("fs0 size 1", FListInteger.size(fs0) == 1);
			assertTrue("fs1 size 2", FListInteger.size(fs1) == 2);
			assertTrue("fs2 size 3", FListInteger.size(fs2) == 3);
			assertFalse("fs0 not size 0",
				FListInteger.size(fs0) == 0);

			// test get
			assertTrue("f1[0] == 8",
				FListInteger.get(f1, 0).equals(eight));
			assertTrue("f4[3] == 8",
				FListInteger.get(f4, 3).equals(eight));
			assertTrue("f4[2] == 9",
				FListInteger.get(f4, 2).equals(nine));
			assertTrue("f4[1] == 10",
				FListInteger.get(f4, 1).equals(ten));
			assertTrue("f4[0] == 11",
				FListInteger.get(f4, 0).equals(eleven));
			assertTrue("fs5[0] == 11",
				FListInteger.get(fs5, 0).equals(eleven));
			assertFalse("fs5[1] != 11",
				FListInteger.get(fs5, 1).equals(eleven));
			assertTrue("fs5[2] == 9",
				FListInteger.get(fs5, 2).equals(nine));
			assertFalse("fs5[3] != 8",
				FListInteger.get(fs5, 3).equals(eight));


			// test contains
			assertFalse("f0 doesn't contain 8",
				FListInteger.contains(f0, eight));
			assertTrue("f1 contains 8",
				FListInteger.contains(f1, eight));
			assertFalse("fs5 doesn't contain 8",
				FListInteger.contains(fs5, eight));
			assertTrue("fs5 contains 11",
				FListInteger.contains(fs5, eleven));
			assertTrue("fs4 contains 10",
				FListInteger.contains(fs4, ten));

			// set/get should throw an error; however, they may
			// return null... what they should not return is a
			// value.
			try {
				Integer i = FListInteger.get(f0, 0);
				FListInteger fsnever =
					FListInteger.set(fs4, 10, eleven);
				if (i != null || fsnever != null) {
					System.out.println("Warning: " +
						"Out of bounds did not " +
						"produce exception or null " +
						"result.");
				}
			} catch (Exception e) {
				// good! should have thrown
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error during nonoverride methods.");
		}
	}

	/**
	 *	Test dynamic methods that override methods from Object.
	 *	Tests equals, hashCode, toString.
	 */
	private void override() {
		try {
			// test equals
			assertTrue("f0 == f0", f0.equals(f0));
			assertTrue("f0 == emptyList",
				f0.equals(FListInteger.emptyList()));
			assertTrue("f0, 9 == fs0",
				FListInteger.add(f0, nine).equals(fs0));
			assertTrue("fs0, 0->8 == f1",
				FListInteger.set(fs0, 0, new Integer(8)).
				equals(f1));
			assertTrue("fs4 == fs5", fs4.equals(fs5));
			assertTrue("fs5 == fs4", fs5.equals(fs4));
			assertFalse("fs4 != fs3", fs4.equals(fs3));
			assertFalse("f1 != f0", f1.equals(f0));
			assertFalse("f0 != f1", f0.equals(f1));
			// test equal versus wrong things
			assertFalse("f3 != null", f3.equals(null));
			assertFalse("f3 != Object", f3.equals(new Object()));
			assertFalse("f3 != Integer(1)",
				f3.equals(new Integer(1)));
			// test equal for where only the first element differs
			assertFalse("fs1 != f2", fs1.equals(f2));
			// test equal for where only the last element differs
			assertFalse("f2 [9, 8] != [8, 8]",
				f2.equals(
					FListInteger.add(
					FListInteger.add(
						FListInteger.emptyList(), eight
					), eight
					)
				)
			);

			// apologies in advance... but this works well to make
			// sure we're not using any previously declared vars
			assertTrue("f4 == [11, 10, 9, 8]",
				f4.equals(
					FListInteger.add(
					FListInteger.add(
					FListInteger.add(
					FListInteger.add(
						FListInteger.emptyList(), eight
					), nine
					), ten
					), eleven
					)
				)
			);

			// test hashCode being at least not terribly full
			// of collisions for trivial cases
			assertTrue("f0 hashcode == f0 hashcode",
				f0.hashCode() == f0.hashCode());
			assertTrue("f4 hashcode == f4 hashcode",
				f4.hashCode() == f4.hashCode());
			assertTrue("fs4 hashcode == fs5 hashcode",
				fs4.hashCode() == fs5.hashCode());
			assertTrue("f2 [9, 8] hashcode == [9, 8] hashcode",
				f2.hashCode() ==
					FListInteger.add(FListInteger.add(
						FListInteger.emptyList(), eight
					), nine).hashCode()
			);

			// test toString
			assertTrue("f0 toString == []",
				f0.toString().equals("[]"));
			assertFalse("f1 toString != []",
				f1.toString().equals("[]"));
			assertTrue("f2 toString == [9, 8]",
				f2.toString().equals("[9, 8]"));
			assertTrue("fs4 toString == [11, 10, 9, 11]",
				fs4.toString().equals("[11, 10, 9, 11]"));
			assertTrue("fs5 toString == [11, 10, 9, 11]",
				fs5.toString().equals("[11, 10, 9, 11]"));
			assertTrue("fs4 toString == fs5 toString",
				fs4.toString().equals(fs5.toString()));
			assertFalse("fs4 toString != f4 toString",
				fs4.toString().equals(f4.toString()));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error during override methods.");
		}
	}

	/**
	 *	Run a named test resulting in true.
	 *	If it fails, log and print the name and number.
	 *	Auxiliary test function copied from professor's code sample.
	 *	@param name	test name
	 *	@param result	test result
	 */
	private void assertTrue(String name, boolean result) {
		if (!result) {
			System.out.println ();
			System.out.println ("***** Test failed ***** "
					+ name + ": test " + totalTests);
			totalErrors = totalErrors + 1;
		}
		totalTests++;
	}

	/**
	 *	Run a named test resulting in false.
	 *	If it fails, log and print the name and number.
	 *	Auxiliary test function copied from professor's code sample.
	 *	@param name	test name
	 *	@param result	test result
	 */
	private void assertFalse(String name, boolean result) {
		if (result) {
			System.out.println ();
			System.out.println ("***** Test failed ***** "
					+ name + ": test " + totalTests);
			totalErrors = totalErrors + 1;
		}
		totalTests++;
	}
}
