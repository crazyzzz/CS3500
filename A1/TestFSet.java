/**
 * Basic test program for assignment 1 - FSet.
 * @author Clinger
 * @author Schmidt
 */
public class TestFSet {

    /**
     * Runs the tests.
     * @param args the command line arguments
     */         
    public static void main(String args[]) {
        TestFSet test = new TestFSet();

        // Test with 0-argument FSet.emptySet().

        System.out.println("Testing 0-argument emptySet()");
        test.creation();
        test.accessors(0);
        test.usual();
        test.accessors(0);    // test twice to detect side effects
        test.usual();

        test.summarize();
    }

    /**
     * Prints a summary of the tests.
     */
    private void summarize () {
        System.out.println();
        System.out.println (totalErrors + " errors found in " +
                            totalTests + " tests.");
    }

    public TestFSet () { }

    // String objects to serve as elements.

    private final String alice   = "Alice";
    private final String bob   = "Bob";
    private final String carol = "Carol";
    private final String dave  = "Dave";

    // FSet objects to be created and then tested.

    private FSet f0;    // { }
    private FSet f1;    // { alice }
    private FSet f2;    // { bob, alice }
    private FSet f3;    // { carol, bob, alice }
    private FSet f4;    // { dave, carol, bob, alice }
    private FSet f5;    // { alice, bob, alice }
    private FSet f6;    // { carol, dave, bob, alice }
    private FSet f7;    // { alice, bob, bob, alice }

    private FSet s1;    // { alice }
    private FSet s2;    // { bob }
    private FSet s3;    // { carol }
    private FSet s4;    // { dave }

    private FSet s12;    // { alice, bob }
    private FSet s13;    // { alice, carol }
    private FSet s14;    // { alice, dave }
    private FSet s23;    // { bob, carol }
    private FSet s34;    // { carol, dave }

    private FSet s123;   // { alice, bob, carol }
    private FSet s124;   // { alice, bob, dave }
    private FSet s134;   // { alice, carol, dave }
    private FSet s234;   // { bob, carol, dave }

    /**
     * Creates some FSet objects.
     */
    private void creation () {
        try {
            f0 = FSet.emptySet();
            f1 = FSet.insert (f0, alice);
            f2 = FSet.insert (f1, bob);
            f3 = FSet.insert (f2, carol);
            f4 = FSet.insert (f3, dave);
            f5 = FSet.insert (f2, alice);
            f6 = FSet.insert (FSet.insert (f2, dave), carol);

            f7 = FSet.insert (f0, alice);
            f7 = FSet.insert (f7, bob);
            f7 = FSet.insert (f7, bob);
            f7 = FSet.insert (f7, alice);

            s1 = FSet.insert (f0, alice);
            s2 = FSet.insert (f0, bob);
            s3 = FSet.insert (f0, carol);
            s4 = FSet.insert (f0, dave);

            s12 = FSet.insert (f1, "Bob");
            s13 = FSet.insert (f1, "Carol");
            s14 = FSet.insert (f1, "Dave");
            s23 = FSet.insert (s2, "Carol");
            s34 = FSet.insert (s3, "Dave");

            s123 = FSet.put (s12, carol);
            s124 = FSet.put (s12, dave);
            s134 = FSet.put (s13, dave);
            s234 = FSet.put (s23, dave);

            s134 = FSet.put (s134, dave);
            s234 = FSet.put (s234, dave);

        }
        catch (Exception e) {
            System.out.println("Exception thrown during creation tests:");
            System.out.println(e);
            assertTrue ("creation", false);
        }
    }

    /**
     * Tests the accessors.
     */
    private void accessors (int nargs) {
        try {
	    //testing isEmpty
            assertTrue ("empty", FSet.isEmpty (f0));
            assertFalse ("nonempty", FSet.isEmpty (f1));
            assertFalse ("nonempty", FSet.isEmpty (f3));

	    //testing size
            assertTrue ("f0.size()", FSet.size (f0) == 0);
            assertTrue ("f1.size()", FSet.size (f1) == 1);
            assertTrue ("f2.size()", FSet.size (f2) == 2);
            assertTrue ("f3.size()", FSet.size (f3) == 3);
            assertTrue ("f4.size()", FSet.size (f4) == 4);
            assertTrue ("f5.size()", FSet.size (f5) == 2);
            assertTrue ("f7.size()", FSet.size (f7) == 2);

	    //testing contains
            assertFalse ("contains01", FSet.contains (f0, alice));
            assertFalse ("contains04", FSet.contains (f0, dave));
	    assertTrue  ("contains11", FSet.contains (f1, alice));
            assertTrue  ("contains11n", FSet.contains (f1, "Alice"));
            assertFalse ("contains14", FSet.contains (f1, dave));
            assertTrue  ("contains21", FSet.contains (f2, alice));
            assertFalse ("contains24", FSet.contains (f2, dave));
            assertTrue  ("contains31", FSet.contains (f3, alice));
            assertFalse ("contains34", FSet.contains (f3, dave));
            assertTrue  ("contains41", FSet.contains (f4, alice));
            assertTrue  ("contains44", FSet.contains (f4, dave));
            assertTrue  ("contains51", FSet.contains (f5, alice));
            assertFalse ("contains54", FSet.contains (f5, dave));

	    //testing isSubset
            assertTrue  ("isSubset00", FSet.isSubset (f0, f0));
            assertTrue  ("isSubset01", FSet.isSubset (f0, f1));
            assertFalse ("isSubset10", FSet.isSubset (f1, f0));
            assertTrue  ("isSubset02", FSet.isSubset (f0, f2));
            assertFalse ("isSubset20", FSet.isSubset (f2, f0));
            assertTrue  ("isSubset24", FSet.isSubset (f2, f4));
            assertFalse ("isSubset42", FSet.isSubset (f4, f2));
            assertTrue  ("isSubset27", FSet.isSubset (f2, f7));
            assertTrue  ("isSubset72", FSet.isSubset (f7, f2));
            assertTrue  ("isSubset 13 3", FSet.isSubset (s13, f3));
            assertFalse ("isSubset 3 13", FSet.isSubset (f3, s13));
            assertTrue  ("isSubset 123 3", FSet.isSubset (s123, f3));
            assertTrue  ("isSubset 3 123", FSet.isSubset (f3, s123));

	    //testing remove
            assertTrue ("remove f0 1", FSet.isEmpty (FSet.remove (f0, alice)));
            assertTrue ("remove f1 1", FSet.isEmpty (FSet.remove (f1, alice)));
            assertTrue ("remove f1 2", FSet.remove (f1, bob).equals(s1));
            assertTrue ("remove f2 1", FSet.remove (f2, alice).equals(s2));
            assertTrue ("remove f2 2", FSet.remove (f2, bob).equals(s1));
            assertTrue ("remove f2 3", FSet.remove (f2, carol).equals(s12));
            assertTrue ("remove f2 12",
                        FSet.isEmpty (FSet.remove (FSet.remove (f2, alice),
                                                   bob)));
            assertTrue ("remove f2 21",
                        FSet.isEmpty (FSet.remove (FSet.remove (f2, bob),
                                                   alice)));
            assertTrue ("remove f6 1", FSet.remove (f6, alice).equals(s234));
            assertTrue ("remove f6 2", FSet.remove (f6, bob).equals(s134));
            assertTrue ("remove f6 3", FSet.remove (f6, carol).equals(s124));
            assertTrue ("remove f6 4", FSet.remove (f6, dave).equals(s123));

	    //testing union
            assertTrue ("union f0 f0", FSet.isEmpty (FSet.union (f0, f0)));
            assertTrue ("union f0 s1", FSet.union (f0, s1).equals(s1));
            assertTrue ("union s1 f0", FSet.union (s1, f0).equals(s1));
            assertTrue ("union s1 s1", FSet.union (s1, s1).equals(s1));
            assertTrue ("union s1 s2", FSet.union (s1, s2).equals(s12));
            assertTrue ("union s2 s1", FSet.union (s2, s1).equals(s12));
            assertTrue ("union s2 s2", FSet.union (s2, s2).equals(s2));
            assertTrue ("union s12 s3", FSet.union (s12, s3).equals(s123));
            assertTrue ("union s3 s12", FSet.union (s3, s12).equals(s123));
            assertTrue ("union s13 s2", FSet.union (s13, s2).equals(s123));
            assertTrue ("union s2 s13", FSet.union (s2, s13).equals(s123));
            assertTrue ("union s123 s234",
                        FSet.union (s123, s234).equals(f4));
            assertTrue ("union f4 f6",
                        FSet.union (f4, f6).equals(f4));
            assertTrue ("union f6 f4",
                        FSet.union (f6, f4).equals(f4));
            assertTrue ("union f6 f7",
                        FSet.union (f6, f7).equals(f4));
            assertTrue ("union f7 f6",
                        FSet.union (f7, f6).equals(f4));

	    //testing intersect
            assertTrue ("intersect f0 f0",
                        FSet.isEmpty (FSet.intersect (f0, f0)));
            assertTrue ("intersect f0 s1",
                        FSet.isEmpty (FSet.intersect (f0, s1)));
            assertTrue ("intersect s1 f0",
                        FSet.isEmpty (FSet.intersect (s1, f0)));
            assertTrue ("intersect s1 s1",
                        FSet.intersect (s1, s1).equals(s1));
            assertTrue ("intersect s1 s2",
                        FSet.isEmpty (FSet.intersect (s1, s2)));
            assertTrue ("intersect s2 s1",
                        FSet.isEmpty (FSet.intersect (s2, s1)));
            assertTrue ("intersect s2 s2",
                        FSet.intersect (s2, s2).equals(s2));
            assertTrue ("intersect s12 s3",
                        FSet.isEmpty (FSet.intersect (s12, s3)));
            assertTrue ("intersect s3 s12",
                        FSet.isEmpty (FSet.intersect (s3, s12)));
            assertTrue ("intersect s13 s2",
                        FSet.isEmpty (FSet.intersect (s13, s2)));
            assertTrue ("intersect s2 s13",
                        FSet.isEmpty (FSet.intersect (s2, s13)));
            assertTrue ("intersect s123 s234",
                        FSet.intersect (s123, s234).equals(s23));
            assertTrue ("intersect f4 f6",
                        FSet.intersect (f4, f6).equals(FSet.union (s12, s34)));
            assertTrue ("intersect f6 f4",
                        FSet.intersect (f6, f4).equals(FSet.union (s12, s34)));
            assertTrue ("intersect f6 f7",
                        FSet.intersect (f6, f7).equals(s12));
            assertTrue ("intersect f7 f6",
                        FSet.intersect (f7, f6).equals(s12));
        }
        catch (Exception e) {
            System.out.println("Exception thrown during accessors tests:");
            System.out.println(e);
            assertTrue ("accessors", false);
        }
    }

    /**
     * Tests the usual overridden methods.
     */
    private void usual () {
        try {
	    //testing toString
            assertTrue ("toString0",
                        f0.toString().equals("{...(0 Strings)...}"));
            assertTrue ("toString1",
                        f1.toString().equals("{...(1 Strings)...}"));
            assertTrue ("toString7",
                        f7.toString().equals("{...(2 Strings)...}"));

	    //testing equals
            assertTrue ("equals00", f0.equals(f0));
            assertTrue ("equals33", f3.equals(f3));
            assertTrue ("equals55", f5.equals(f5));
            assertTrue ("equals46", f4.equals(f6));
            assertTrue ("equals64", f6.equals(f4));
            assertTrue ("equals27", f2.equals(f7));
            assertTrue ("equals72", f7.equals(f2));

            assertFalse ("equals01", f0.equals(f1));
            assertFalse ("equals02", f0.equals(f2));
            assertFalse ("equals10", f1.equals(f0));
            assertFalse ("equals12", f1.equals(f2));
            assertFalse ("equals21", f2.equals(f1));
            assertFalse ("equals23", f2.equals(f3));
            assertFalse ("equals35", f3.equals(f5));

            assertFalse ("equals0string", f0.equals("just a string"));
            assertFalse ("equals4string", f4.equals("just a string"));

            assertFalse ("equals0null", f0.equals(null));
            assertFalse ("equals1null", f1.equals(null));

	    //testing hashCode
            assertTrue ("hashCode00", f0.hashCode() == f0.hashCode());
            assertTrue ("hashCode44", f4.hashCode() == f4.hashCode());
            assertTrue ("hashCode46", f4.hashCode() == f6.hashCode());
            assertTrue ("hashCode27", f2.hashCode() == f7.hashCode());
        }
        catch (Exception e) {
            System.out.println("Exception thrown during usual tests:");
            System.out.println(e);
            assertTrue ("usual", false);
        }
    }

    ////////////////////////////////////////////////////////////////

    private int totalTests = 0;       // tests run so far
    private int totalErrors = 0;      // errors so far

    /**
     * For anonymous tests.  Deprecated.
     * @param result the result to test
     */
    private void assertTrue (boolean result) {
	assertTrue ("anonymous", result);
    }

    /**
     * Prints failure report if the result is not true.
     * @param name the name of the test
     * @param result the result to test
     */
    private void assertTrue (String name, boolean result) {
        if (! result) {
            System.out.println ();
            System.out.println ("***** Test failed ***** "
                                + name + ": " +totalTests);
            totalErrors = totalErrors + 1;
        }/*else{
	    System.out.println("----- Test passed ----- "
			       + name + ": " +totalTests);
			       }*/
        totalTests = totalTests + 1;
    }

    /**
     * For anonymous tests.  Deprecated.
     * @param result the result to test
     */
    private void assertFalse (boolean result) {
        assertTrue (! result);
    }

    /**
     * Prints failure report if the result is not false.
     * @param name the name of the test
     * @param result the result to test
     */
    private void assertFalse (String name, boolean result) {
        assertTrue (name, ! result);
    }

}
