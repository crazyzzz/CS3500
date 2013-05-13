/**
 * Basic test program for assignment 1 - FSet.
 * @author Clinger
 * @author Schmidt
 */
public class TestFListInteger {

    /**
     * Runs the tests.
     * @param args the command line arguments
     */         
    public static void main(String args[]) {
        TestFListInteger test = new TestFListInteger();

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

    public TestFListInteger () { }

    // String objects to serve as elements.
   
    private final Integer zero = new Integer(0);
    private final Integer one = new Integer(1);
    private final Integer two = new Integer(2);
    private final Integer three = new Integer(3);
    private final Integer four= new Integer(4);
    private final Integer five= new Integer(5);

    // FSet objects to be created and then tested.

    private FListInteger f0;
    private FListInteger f1;
    private FListInteger f2;
    private FListInteger f3;
    private FListInteger f4;
    private FListInteger f5;
    private FListInteger f6;
    private FListInteger f7;
    private FListInteger f8;
    private FListInteger f9;
    private FListInteger f10;
    private FListInteger f11;

    /**
     * Creates some FSet objects.
     */
    private void creation () {
        try {
            f0 = FListInteger.emptyList();
            f1 = FListInteger.add (f0, zero);
            f2 = FListInteger.add (f1, one);
            f3 = FListInteger.add (f2, two);
            f4 = FListInteger.add (f3, three);
            f5 = FListInteger.add (FListInteger.add (f2, two), zero);
            
            f6 = FListInteger.emptyList();
            f6 = FListInteger.set (f6, 0, zero);
            f6 = FListInteger.set (f6, 1, zero);            
            
            f7 = FListInteger.add (f0, zero);
            f7 = FListInteger.add (f7, four);
            f7 = FListInteger.add (f7, four);
            f7 = FListInteger.add (f7, four);
            f7 = FListInteger.add (f7, five);

            f8 = FListInteger.set (f0, 0, one);
            f8 = FListInteger.set (f8, 1, two);
            f8 = FListInteger.set (f8, 2, three);
            f8 = FListInteger.set (f8, 3, four);
            
            f9 = FListInteger.set (f7, 5, one);
            f9 = FListInteger.set (f9, 6, two);
            f9 = FListInteger.set (f9, 7, three);
            f9 = FListInteger.set (f9, 8, four);

            f10 = FListInteger.add (f0, four);
            f10 = FListInteger.add (f10, three);
            f10 = FListInteger.add (f10, three);
            f10 = FListInteger.add (f10, one);

            f11 = FListInteger.emptyList();
            f11 = FListInteger.add (f11, zero);
            f11 = FListInteger.add (f11, zero);

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
	        assertTrue("empty", FListInteger.isEmpty(f0) );
            assertFalse("nonempty", FListInteger.isEmpty(f1) );
            assertFalse("nonempty", FListInteger.isEmpty(f8) );
            
            //testing size
            assertTrue ("f0.size()", FListInteger.size (f0) == 0);
            assertTrue ("f1.size()", FListInteger.size (f1) == 1);
            assertTrue ("f8.size()", FListInteger.size (f8) == 4);
            assertTrue ("f9.size()", FListInteger.size (f9) == 9);
            assertTrue ("f10.size()", FListInteger.size (f10) == 4);
            //testing contains
            assertFalse ("contains01", FListInteger.contains (f0, one));
            assertFalse ("contains04", FListInteger.contains (f0, four));
            assertFalse ("contains11", FListInteger.contains (f1, one));
            assertFalse ("contains14", FListInteger.contains (f1, four));
            assertTrue ("contains21", FListInteger.contains (f2, one));
            assertFalse ("contains24", FListInteger.contains (f2, four));
            assertTrue ("contains31", FListInteger.contains (f3, one));
            assertFalse ("contains34", FListInteger.contains (f3, four));
            assertTrue ("contains41", FListInteger.contains (f4, one));
            assertFalse ("contains44", FListInteger.contains (f4, four));
            assertTrue ("contains51", FListInteger.contains (f5, one));
            assertTrue ("contains52", FListInteger.contains (f5, two));
            assertFalse ("contains54", FListInteger.contains (f5, four));   
            assertFalse ("contains71", FListInteger.contains (f7, one));
            assertTrue("contains74", FListInteger.contains (f7, four)); 
            assertTrue("contains81", FListInteger.contains (f8, one));  
            assertTrue("contains92", FListInteger.contains (f9, two));   
            //testing get
            assertTrue("get empty",FListInteger.get(f0,0) == null);
            assertTrue("get n=0",FListInteger.get(f10,0).equals(one));  
            assertFalse("get n>0",FListInteger.get(f9,8).equals(three));   
            assertTrue("get n> 0",FListInteger.get(f7,FListInteger.size(f7)-1).equals(zero));
            assertTrue("get n= 0",FListInteger.get(f7,0).equals(five));
            //testing set
            assertFalse("set n=0",FListInteger.set(f2,0,zero).equals(f2));
            assertTrue("set n = 0",FListInteger.set(f2,0,zero).equals(f11));
            assertTrue("set n > 0", FListInteger.set (f10, 1, two).equals(f8) );

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
                        f0.toString().equals("[]"));
            assertTrue ("toString1",
                        f1.toString().equals("[0]"));
            assertTrue ("toString8",
                        f8.toString().equals("[1, 2, 3, 4]"));
            //testing equals
            assertTrue ("equals00", f0.equals(f0));
            assertTrue ("equals33", f3.equals(f3));
            assertTrue ("equals55", f5.equals(f5));
            assertTrue ("equals46", f4.equals(f4));
            assertTrue ("equals64", f6.equals(f11));

            assertFalse ("equals01", f0.equals(f1));
            assertFalse ("equals02", f0.equals(f2));
            assertFalse ("equals10", f1.equals(f0));
            assertFalse ("equals12", f1.equals(f2));
            assertFalse ("equals21", f2.equals(f1));
            assertFalse ("equals23", f2.equals(f3));
            assertFalse ("equals35", f3.equals(f5));
            
            assertFalse ("equals0string", f0.equals(new Integer(0)));
	        assertFalse ("equals0string", f0.equals("just a string"));
            assertFalse ("equals4string", f4.equals("just a string"));

            assertFalse ("equals0null", f0.equals(null));
            assertFalse ("equals1null", f1.equals(null));
    
            //testing hashCode
            assertTrue ("hashCode00", f0.hashCode() == f0.hashCode());
            assertTrue ("hashCode44", f4.hashCode() == f4.hashCode());
            assertTrue ("hashCode46", FListInteger.set(f2,0,zero).hashCode() == f11.hashCode());
            assertTrue ("hashCode27", FListInteger.set (f10, 1, two).hashCode() == f8.hashCode());
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
