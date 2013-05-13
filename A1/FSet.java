/**
 * Assignment 1 - FSet.
 * @author Kevin
 * @author Langer
 * klanger@ccs.neu.edu
 *
 * An implementation of sets using abstract
 * data types. Three classes: abstract class FSet
 * and Empty and Insert which serve as the basic
 * creators for FSet. 
 */

/**
 * Class FSet provides framework for an ADT set.
*/
public abstract class FSet {
    
    //Abstract methods implemetned in Empty and Insert
    /**
     * sizeMethod
     * @param --
     * @return int
     */
    abstract int sizeMethod();
    /**
     * isEmptyMethod
     * @param --
     * @return boolean
     */
    abstract boolean isEmptyMethod();
    /**
     * containsMethod
     * @param --
     * @return boolean
     */
    abstract boolean containsMethod(String k);
    /**
     * isSubsetMethod
     * @param FSet
     * @return boolean
     */
    abstract boolean isSubsetMethod(FSet s2);
    /**
     * removeMethod
     * @param String
     * @return FSet
     */
    abstract FSet removeMethod(String k);
     /**
     * unionMethod
     * @param FSet
     * @return FSet
     */
    abstract FSet unionMethod(FSet s2);
    /**
     * intersectMethod
     * @param FSet
     * @return FSet
     */
    abstract FSet intersectMethod(FSet s2);
         
    /**Static Methods*/
    
    /**
     * EmptySet serves as the prefered creator
     * method for the FSet API to ensure duplicates are
     * never added. Returns new empty FSet of type Empty.
     * @param --
     * @return FSet
     */
    public static FSet emptySet() {
        return new Empty();
    }
    /**
     * insert serves as a creator
     * method for the FSet Does not check for
     * duplicates. Returns new FSet with additional
     * element k subsequent to s.
     * @param FSet
     * @param String
     * @return FSet
     */
    public static FSet insert(FSet s, String k) {
        return new Insert(s,k);    
    }
    /**
     * put adds to s if k is a
     * new element of the set.
     * Returns new FSet with additional
     * element k subsequent to s.
     * Note, put is not specific to class
     * Empty or to Insert.
     * @param FSet
     * @param String
     * @return FSet
     */
    public static FSet put(FSet s, String k) {
        return s.putMethod(k);   
    }
    /**
     * size gets the number of unique String k in FSet s
     * @param FSet
     * @return int
     */
    public static int size(FSet s) {
        return s.sizeMethod();
    } 
    /**
     * isEmpty evaulates if FSet s is type Empty or Insert.
     * @param FSet
     * @return boolean
     */
    public static boolean isEmpty(FSet s){
        return s.isEmptyMethod();    
    }
    /**
     * contains evaulates if FSet s has k.
     * @param FSet
     * @param String
     * @return boolean
     */
    public static boolean contains(FSet s, String k) {
        return s.containsMethod(k);
    }
    /**
     * isSubset evaulates if FSet s1 is fully part of FSet s2.
     * @param FSet
     * @param FSet
     * @return boolean
     */
    public static boolean isSubset(FSet s1, FSet s2) {
        return s1.isSubsetMethod(s2);
    }
    /**
     * removes String k if k is part of FSet s.
     * a FSet representing this is returned.
     * @param FSet
     * @param String
     * @return FSet
     */
    public static FSet remove(FSet s, String k) {
        return s.removeMethod(k);
    }
    /**
     * union evaulates the union of FSet s1 and FSet s2.
     * returns FSet regarding this union.
     * @param FSet
     * @param FSet
     * @return FSet
     */
    public static FSet union(FSet s1, FSet s2) {
        return s1.unionMethod(s2);
    }
    /**
     * intersect evaulates the intersection of FSet s1 and FSet s2.
     * returns FSet regarding this intersection.
     * @param FSet
     * @param FSet
     * @return FSet
     */
    public static FSet intersect(FSet s1, FSet s2) {
        return s1.intersectMethod(s2);
    }
    /**
     * put adds to s if k is a
     * new element of the set.
     * Returns new FSet with additional
     * element k subsequent to s.
     * This method will not be overwriten
     * in children of FSet.
     * @param FSet
     * @param String
     * @return FSet
     */
    public FSet putMethod(String k) {
        if ( contains( this,k) ) {
            return this;
        }
        return insert(this,k);
    }
    
    /**Dynamic Methods*/

    /**
     * String representation of FSet size.
     * @param --
     * @return FSet
     */
    public String toString() {
        return "{...(" + this.size(this) + " Strings)...}";
    }
    /**
     * Equality tester.
     * Catch for FSet inputs.
     * @param FSet
     * @return boolean
     */
    public boolean equals(FSet s) {
        return equals((Object) s);
    }
    /**
     * Equality tester.
     * Check type, if FSet or 
     * implemetation of FSet then
     * check to see if both parent FSet
     * and argument FSet are subsets of 
     * each other. 
     * Implementation of (from provided specs):
     * FSet.contains (s1, k) if and only if FSet.contains (s2, k)
     * @param FSet
     * @return boolean
     */
    public boolean equals(Object s) {    
        if ( !(s instanceof FSet)) {
            return false;
        }
        if ( isSubset(this, (FSet) s) && isSubset( (FSet) s, this) ) {
            return true;
        }
        return false;
    }
    /**
     * Dumb hashcode that returns size.
     * @param FSet
     * @return int
     */
    public int hashCode() {
        return size(this);
    }
    
}  

/**
 * Class Empty extends FSet.
 * An empty finite set. Has no items.
 */
class Empty extends FSet{
    /**
     *  Empty constructor for FSet
     */
    Empty(){}
    /**
     * isEmptyMethod returns a true boolean indicating
     * that the empty set is empty.
     * @param --
     * @return boolean
     */
    boolean isEmptyMethod() {
        return true;
    }
    /**
     * sizeMethod returns an int indicating
     * that the empty set is size 0.
     * @param --
     * @return int
     */
    int sizeMethod() {
        return 0;
    }
    /**
     * containsMethod returns a false boolean indicating
     * that the empty set contains nothing.
     * @param --
     * @return boolean
     */
    boolean containsMethod(String k) {
        return false;
    }
    /**
     * isSubsetMethod returns a true boolean indicating
     * that the empty set is always a subset irrevant of
     * paramater s2.
     * @param FSet
     * @return boolean
     */
    boolean isSubsetMethod(FSet s2) {
        return true;
    }
    /**
     * removeMethod returns a FSet indicating
     * that the empty set remains empty after any remove
     * regardless of paramater k.
     * @param String
     * @return FSet
     */
    FSet removeMethod(String k) {
        return this;
    }
    /**
     * unionMethod returns a FSet indicating
     * that the empty set is union with s2
     * regardless of paramater s2.
     * @param FSet
     * @return FSet
     */
    FSet unionMethod(FSet s2) {
        return s2;
    }
    /**
     * intersectMethod returns a FSet indicating
     * that the empty set is intersect with an
     * empty set regardless of paramter s2.
     * @param FSet
     * @return FSet
     */
    FSet intersectMethod(FSet s2) { 
        return this;
    }
}

/**
 * Class Insert extends FSet.
 * Has items. While insert will allow duplicates 
 * Size will not reflect that, nor will the other methods.
 */
class Insert extends FSet {
    /**
     * FSet s and k hold the present
     * working values of the list. Referenced with
     * the 'this' keyword throughout. 
    */
    FSet s;
    String k;
    /**
     * Insert constructor for FSet to add values 
     * to the set.
     * @param FSet 
     * @param String
     * @return --
     */
    Insert(FSet s, String k) {
        this.s = s;
        this.k = k;
    }
    /**
     * isEmptyMethod returns a false boolean indicating
     * that the insert set has a value that was inserted.
     * @param --
     * @return boolean
     */
    boolean isEmptyMethod() {
        return false;
    }
    /**
     * sizeMethod returns an int indicating
     * that the set size while checking for duplicates.
     * @param --
     * @return int
     */
    int sizeMethod() {
        //Use size variable to avoid duplicate calls to size()
        int size = size(s);
        if ( !contains (s, k) ) { 
            size += 1;
        } 
        return size;
    }
    /**
     * containsMethod returns a true boolean if
     * the passed string is equal to the set string.
     * for all strings in the set.
     * @param --
     * @return boolean
     */
    boolean containsMethod(String k) {
        if ( k.equals(this.k) ) {
            return true;
        }
        return contains(s,k);
    }
    /**
     * isSubsetMethod returns a false boolean if
     * the passed set does not contain the set's value
     * String k, for all withen the subset paramater s2.
     * @param FSet
     * @return boolean
     */
    boolean isSubsetMethod(FSet s2) {
        if ( contains(s2,k) ) {
            return isSubset( s , s2); 
        }
        return false;
    }
    /**
     * removeMethod returns a FSet indicating
     * the removal of set value String k if k 
     * exists in Fset s.
     * @param String
     * @return FSet
     */
    FSet removeMethod(String k) {
        if ( k.equals(this.k) ) {
            return remove(s,k);
        } 
        return insert( remove(s,k) , this.k );
    }
    /**
     * unionMethod returns a FSet indicating
     * that the union, or a set of all elements 
     * of both the current set and the passed set s2.
     * Inserts elements uncommon to the set using contains.
     * @param FSet
     * @return FSet
     */
    FSet unionMethod(FSet s2) {
        if ( contains (s2, k) ) {
            return union (s, s2);
        }
        return insert (union (s, s2), k);
    }
    /**
     * intersectMethod returns a FSet that is a
     * set of the common elemetns of both the
     * current set and the passed set s2. Uses
     * contains to identify common elements and build
     * the new list.
     * @param FSet
     * @return FSet
     */
    FSet intersectMethod(FSet s2) { 
        if ( contains (s2, k) ) {
            return insert (intersect (s, s2), k);
        }
        return intersect (s, s2);
    }
}
