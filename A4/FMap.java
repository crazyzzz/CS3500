/**
 * Assignment 4 - FMap.
 * @author Kevin Langer
 * klanger@ccs.neu.edu
 *
 * An implementation of maps using abstract
 * data types. Three classes: abstract class FMap
 * and Empty and Include which serve as the basic
 * creators for FMap.
 */

/**
 * Class FMat provides framework for an ADT map.
*/
public abstract class FMap<K,V> { 

    /** 
     * isEmptyMethod
     * @param --
     * @return boolean representing if the map is empty
     */
    abstract boolean isEmptyMethod();

     /** 
     * sizeMethod
     * @param --
     * @return int representing number of unique keys in map
     */
    abstract int sizeMethod();
    
    /** 
     * containsKeyMethod
     * @param K key to check for in the map.
     * @return boolean representing if key is found in the map.
     */
    abstract boolean containsKeyMethod(K k);

    /** 
     * getMethod
     * @param K element for which V value is requested.
     * @return V is the object at specified key value.
     */
    abstract V getMethod(K k);

    /** 
     * equalsMethod
     * helper method for FMap's equals
     * @param FMap for which to test equality to owner.
     * @return boolean indiciating if lists contain the same key value pairs.
     */
    abstract boolean equalsMethod(FMap m2);

    /**
     * empty serves as the prefered creator
     * method for the FMap
     * Returns new empty FMap of type Empty.
     * @param --
     * @return FMap with no elements
     */
    public static <K,V> FMap<K,V> empty() {
        return new Empty<K,V>();
    }

    /**
     * empty serves as the prefered creator
     * method for the FMap
     * Does the same thing as empty()
     * Returns new empty FMap of type Empty.
     * @param java.util.Comparator is thrown away
     * @return FMap with no elements
     */
    public static  <K,V> FMap<K,V> empty(java.util.Comparator c) {
        return new Empty<K,V>();
    }

    /**
     * include serves as a creator
     * method for the FMap and adds 
     * new key value pairs to the FMap
     * Returns new FMap of type Include
     * containing atleast one key value pair.
     * @param K new key
     * @param V new value
     * @return FMap with atleast one key value.
     */
    public FMap<K,V> include (K k, V v) {
        return new Include<K,V>(this, k,v);
    }
    
     /**
     * isEmpty 
     * evaulates if parent FMap is type Empty or Add.
     * @param --
     * @return boolean indicating if it is Empty or has elements. 
     */  
    public boolean isEmpty() {
        return isEmptyMethod();
    }
 
    /**
     * size 
     * evaulates the amount of unique pairs in the parent FMap
     * @param --
     * @return int indicating how many unique pairs are in the FMap.
     */  
    public int size() {
        return sizeMethod();
    }
    
    /**
     * containsKey 
     * checks parent FMap for specific key.
     * @param K key to check for in FMap
     * @return boolean incidicating if the key is present.
     */  
    public boolean containsKey(K k) {
        return containsKeyMethod(k);
    }
    
    /**
     * get 
     * retrives the value at a passed key in the parent FMap.
     * @param K key to get value from.
     * @return V the value from the FMap.
     */  
    public V get(K k) {
        return getMethod(k);
    }

    /**
     * toString
     * Overwrites Object's toString
     * @param --
     * @return String formated to show the size of maped values in FMap.
     */
    public String toString() {
        return "{...(" + sizeMethod() + " keys mapped to values)...}";
    }

    /**
     * Equality tester.
     * Catch for FMap inputs.
     * uses helper method to evaluate FMap equality.
     * parse and cast. Here for readability of equals.
     * @param Object
     * @return boolean
     */    
    public boolean equals( Object o) {
        if (o != null && o instanceof FMap) {
            FMap m2 = (FMap) o;
             if (m2.size() == this.size() ) {
                return equalsMethod(m2);
            }
        }
        return false;
    }

}

/**
 * Class Empty extends FMap.
 * An empty FMap. Has no items.
 */
class Empty<K,V> extends FMap<K,V> {

    /**
     *  Empty constructor for FMap
     */
    Empty() {}
    
    /**
     * isEmptyMethod
     * @param --
     * @return boolean true boolean indicating that map is empty
     */
    public boolean isEmptyMethod() {
        return true;
    }
    
    /**
     * sizeMethod
     * get size of Map; is zero for an empty map
     * @param --
     * @return int with value zero
     */
    public int sizeMethod() {
        return 0;
    }
    
    /**
     * containsKeyMethod
     * Empty maps contain no keys.
     * @param --
     * @return boolean value of false indiciating no keys.
     */
    public boolean containsKeyMethod(K k) {
        return false;
    }
    
    /**
     * getMethod
     * Empty maps contain no keys, so no values at the key.
     * Throws exception for accessing from an empty map.
     * @param --
     * @return --
     */
    public V getMethod(K k) {
        throw new RuntimeException("Attempted to get from empty map");
    }

    /**
     * equalsMethod
     * At this method the size of the paramater and the parent
     * have been compared and determined to be the same. As
     * a result, it will always be true.
     * @param FMap map to compare
     * @return boolean indicating that the maps are equal
     */
    boolean equalsMethod(FMap m2) {
        return true;
    }
    
    /** 
     * An empty map has no values to generate hashs from
     * as a result, return same hash for all empty maps
     * @param --
     * @return int hash value
     */
    public int hashCode() { 
        return -1;
    }
} 

/**
 * Class Include extends FMap.
 * Has items. Can only change FMap values with calls to
 * Include. Size will increase with unique keys. 
 */
class Include<K,V> extends FMap<K,V> {

    /**
     * FMap m0 hold previous FMap.
     * K k0 is the key value of current.
     * V v0 is the value at the current key.
    */
    FMap<K,V> m0;
    K k0;
    V v0;

    /**
     * Include constructor for FMap to add values
     * to the list. Include is the only way to change 
     * the size of the list, but only on unique keys.
     * @param FMap previous FMap
     * @param K current key
     * @param V current value
     * @return --
     */
    Include (FMap<K,V> m0, K k0, V v0) {
        this.m0 = m0;
        this.k0 = k0;
        this.v0 = v0;
    }

    /**
     * isEmptyMethod
     * @param --
     * @return boolean set to false indiciating FMap has values.
     */
    public boolean isEmptyMethod() {
        return false;
    }

    /**
     * sizeMethod
     * get size of unqiue elements in FMap
     * @param --
     * @return int representing the size of the FMap
     */
    public int sizeMethod() {
        int size_m = m0.size();
        if ( m0.containsKey(k0) ) {
            return size_m;
        }
        return size_m + 1;
    }
    
    /**
     * containsKeyMethod
     * maps contain key if object k equals the key paramater
     * for some value in the map.
     * @param K key to check for
     * @return boolean value indiciating if key was found.
     */
    public boolean containsKeyMethod(K k) {
        if ( k.equals(k0) ) {
            return true;
        }   
        return m0.containsKey(k);
    }

    /**
     * getMethod
     * Finds the specified key and returns the value at that key.
     * @param K key to get value from
     * @return V value at specified key
     */
    public V getMethod(K k) {
        if ( k.equals(k0) ) { 
            return v0;
        }
        return m0.get(k);
    }
    
    /**
     * Used to supress:
     * unchecked call to containsKey(K) as a member of the raw type FMap
     * unchecked call to get(K) as a member of the raw type FMap
     */
    @SuppressWarnings("unchecked")
    
     /**
     * Itterate through list by size, with the knowlege that 
     * newest key values are the only relevant ones (do not
     * check shadow keys). If contains key and the value at
     * that key is equal to that of m2, for all values then
     * the two maps are equal. Requires parsing to includes
     * to get k0 and v0 information.
     * @param FMap to be tested, note size is already checked
     * @return boolean indicating if the maps are equal
     */
    boolean equalsMethod(FMap m2) {
        //save variable m to be used for back itteration of the FMap
        Include<K,V> m = this;
        //save size to be used as loop counter
        int size_m = this.size();        
        while (size_m != 0) {
            if (! (m2.containsKey(m.k0) && m2.get(k0).equals( v0 )) ) {
                return false;
            }
            size_m--;
            if (size_m != 0) {
                m = (Include<K,V>) m.m0;
            }
        }
        return true;   
    }
    
    /* 
     * hashCode
     * Iterate through map and sum the map indexes 
     * making sure to multiply old elements before adding
     * them to make the values place dependant in the 
     * list. Offset the values such that values for zero
     * will be different than having no values. Multiplication
     * immediate values chosen semi-arbitrarily and will likely
     * need adjusting to reduce collisions. 
     * @param --
     * @return int where m1.equals(lm) then hashCode(m1) == hashCode(m2)
    */
    public int hashCode() { 
        int hash = m0.hashCode();
        if ( m0.containsKey(k0) ) {
            return hash;
        }
        return (hash+5) * 7 ;
    }
} 

