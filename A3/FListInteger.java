/**
 * Assignment 3 - FListInteger.
 * @author Kevin Langer
 * klanger@ccs.neu.edu
 *
 * An implementation of lists using abstract
 * data types. Three classes: abstract class FListInteger
 * and Empty and Add which serve as the basic
 * creators for FListInteger. 
 */

/**
 * Class FListInteger provides framework for an ADT list.
*/
public abstract class FListInteger { 

    //Abstract methods implemetned in Empty and Insert
    
    /**
     * isEmptyMethod
     * @param --
     * @return boolean representing if the List is empty
     */
    abstract boolean isEmptyMethod();
    
    /**
     * getMethod
     * When giving a valid index in a list
     * that contains values, will return
     * the Integer at that index. Otherwise
     * will throw an exception.
     * @param int index in List
     * @return Integer at index
     */
    abstract Integer getMethod(int n);

    /**
     * setMethod 
     * When given a valid index the Integer value in the paramaters
     * will be set at the index given in the paramaters. Otherwise
     * throw an exception.
     * @param int Integer the index and the Integer to set at index
     * @return FListInteger representing list after set operation
     */
    abstract FListInteger setMethod(int n, Integer y);

    /**
     * sizeMethod
     * get size of List
     * @param --
     * @return int representing the size of the list
     */
    abstract int sizeMethod();

    /**
     * containsMethod
     * returns true if the Integer paramater is in the list
     * returns false if the Integer paramater is not in the list
     * @param Integer value to check for in the list
     * @return boolean indicating if the Integer is in the list
     */
    abstract boolean containsMethod(Integer y);

    /**
     * emptyList serves as the prefered creator
     * method for the FListInteger
     * Returns new empty FSet of type Empty.
     * @param --
     * @return FListInteger with no elements
     */
    public static FListInteger emptyList() {
        return new Empty();
    }

    /**
     * emptyList serves as a creator
     * method for the FListInteger and adds 
     * new elemnets to the lsit
     * Returns new empty FListInteger of type Add.
     * containing atleast one Integer.
     * @param --
     * @return FListInteger with atleast one Integer.
     */
    public static FListInteger add(FListInteger f, Integer x) {
        return new Add( f,x );
    }

     /**
     * isEmpty 
     * evaulates if FListInteger f is type Empty or Add.
     * @param FListInteger of type Empty or Add
     * @return boolean indicating if it is Empty or has elements. 
     */
    public static boolean isEmpty(FListInteger f) {
        return f.isEmptyMethod();
    }

    /**
     * get 
     * evaulates if FListInteger f is type Empty or Add.
     * @param FListInteger of type Empty or Add
     * @param int index in list
     * @return Integer that exists at specifed index 
     */
    public static Integer get(FListInteger f, int n) {
        return f.getMethod( n );
    }

    /**
     * set 
     * evaulates if FListInteger f is type Empty or Add.
     * changes the value if index is valid, throws exception otherwise.
     * @param FListInteger of type Empty or Add
     * @param int index in list
     * @param Integer to make value at n.
     * @return FListInteger that corosponds to the change made
     */
    public static FListInteger set(FListInteger f, int n, Integer y ) {
        return f.setMethod( n, y );
    }

    /**
     * size
     * get size of List
     * @param FListInteger of type Empty or Add
     * @return int representing the size of the list
     */
    public static int size(FListInteger f) {
        return f.sizeMethod();
    }

     /**
     * containsMethod
     * returns true if the Integer paramater is in the list
     * returns false if the Integer paramater is not in the list
     * @param FListInteger of type Empty or Add
     * @param Integer value to check for in the list
     * @return boolean indicating if the Integer is in the list
     */
    public static boolean contains(FListInteger f, Integer y) {
        return f.containsMethod(y);
    }

    /**
     * Equality tester.
     * Catch for fListInteger inputs.
     * parse and cast. Here for readability of equals.
     * @param Object
     * @return boolean
     */     
    public boolean equals(Object f2) {
        if (f2 instanceof FListInteger) {
            return equals((FListInteger) f2);
        }
        return false;
    }

    /* 
     * equals
     * check nullity, if null return false. 
     * next check to make sure the size is the same
     * if not return false. Next go through entire list
     * return false on first difference. If no differences
     * then the lists are the same. 
     * @param FListInteger of second list.
     * @return boolean indicating if lists are equals.
    */
    public boolean equals(FListInteger f2) {
        if (f2 == null) {
            return false;
        }

        //size of list 1 value such that it will not need to be recalculated
        int size_v = this.sizeMethod(); 
        if (size_v != f2.sizeMethod()) {
            return false;
        }
        //use i to iterate through entire list based on size of list1/list2
        for (int i = 0; i < size_v; i++ ) {
            if ( !(this.getMethod(i).equals( f2.getMethod(i) ) ) ) {
                return false;
            }
        }
        return true;
    }

    /* 
     * hashCode
     * Iterate through list and sum the list elements
     * making sure to multiply old elements before adding
     * them to make the values place dependant in the 
     * list. Offset the values such that values for zero
     * will be different than having no values. Multiplication
     * immediate values chosen semi-arbitrarily and will likely
     * need adjusting to reduce collisions. 
     * @param --
     * @return int where l1.equals(l2) then hashCode(l1) == hashCode(l2)
    */
    public int hashCode() {
        int size_v = this.sizeMethod(); //size of list for use in loop
        int hash = 0;   //Running total of hash
        while (size_v > 0) {
            hash=hash*5 + (this.getMethod(--size_v).intValue()+7);
        }
        return hash;
    }
}

/**
 * Class Empty extends FListInteger.
 * An empty list. Has no items.
 */
class Empty extends FListInteger {

    /**
     *  Empty constructor for FListInteger
     */
    Empty() {}

    /**
     * getMethod
     * will throw an exception
     * becasue no valid index in
     * an empty list.
     * @param int index in List
     */
     Integer getMethod( int n ) {
        throw new RuntimeException("Attempted to get from empty list");
    }

    /**
     * setMethod
     * will throw an exception
     * becasue no valid index in
     * an empty list.
     * @param int Integer index and Integer that will not be set
     */
    FListInteger setMethod( int n, Integer y ) {
        throw new RuntimeException("Attempted to set an invalid index");
    }

    /**
     * isEmptyMethod
     * @param --
     * @return boolean true boolean indicating that List is empty
     */
    boolean isEmptyMethod() {
        return true;    
    }
    
    /**
     * sizeMethod
     * get size of List; is zero for an empty list
     * @param --
     * @return int with value zero
     */
    int sizeMethod() {
        return 0;
    }

    /**
     * containsMethod
     * returns false as an empty list does not contain anything.
     * @param Integer value to check for in the list
     * @return boolean with value false indicating Integer is not in list.
     */
    boolean containsMethod(Integer y) {
        return false;
    }
    
    /**
     * toString
     * Overwrites Object's toString
     * @param --
     * @return String of value [] to display empty list
     */
    public String toString() {
        return "[]";
    }
}

/**
 * Class Add extends FListInteger.
 * Has items. Can only change List values with calls to
 * add and set. Size will not be changed with set. 
 */
class Add extends FListInteger {

    /**
     * FListInteger f and x hold the present
     * working values of the list. Referenced with
     * the 'this' keyword throughout. 
    */
    FListInteger f;
    Integer x;
    
    /**
     * Insert constructor for FListInteger to add values
     * to the list. Add is the only way to change 
     * the size of the list.
     * @param FListInteger 
     * @param Integer
     * @return --
     */
    Add(FListInteger f, Integer x) {
        this.f = f;
        this.x = x;
    }

    /**
     * getMethod
     * When giving a valid index in a list
     * that contains values, will return
     * the Integer at that index. Otherwise
     * will throw an exception.
     * @param int index in List
     * @return Integer at index
     */
    Integer getMethod( int n ) {
        if ( n == 0 ) { 
            return x;
        } else if (n < 0 ) { 
             throw new RuntimeException("Attempted to get negative index");
        }
        return get( f,n-1 );
    }

    /**
     * setMethod 
     * When given a valid index the Integer value in the paramaters
     * will be set at the index given in the paramaters. Otherwise
     * throw an exception.
     * Cannot change the size of the list with set.
     * @param int Integer the index and the Integer to set at index
     * @return FListInteger representing list after set operation
     */
    FListInteger setMethod( int n, Integer y ) {
        if ( n == 0 ) { 
            return add(f,y);
        } else if (n < 0 ) { 
             throw new RuntimeException("Attempted to set negative index");
        }
        return add( set(f,n-1,y),x);
    }
    
    /**
     * isEmptyMethod
     * @param --
     * @return boolean representing if the List is empty
     */
    boolean isEmptyMethod() {
        return false;
    }

    /**
     * sizeMethod
     * get size of List
     * @param --
     * @return int representing the size of the list
     */
    int sizeMethod() {
        return size(f)+1;
    }
    
    /**
     * containsMethod
     * returns true if the Integer paramater is in the list
     * returns false if the Integer paramater is not in the list
     * @param Integer value to check for in the list
     * @return boolean indicating if the Integer is in the list
     */
    boolean containsMethod(Integer y) {
        if ( x.equals(y) ) { 
            return true;
        }
        return contains(f,y);
    }

    /**
     * toString
     * Overwrites Object's toString
     * @param --
     * @return String of value [ .., ..] to display list
     */
    public String toString() {
        if( isEmpty(f) ) { 
            return "[" + x.toString() + "]";
        }
        return "[" + x.toString() + ", " 
            +  f.toString().substring(1, f.toString().length());
    }
}
