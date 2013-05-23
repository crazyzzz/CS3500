/**
 * Assignment 5 - FMap.
 * @author Kevin Langer
 * klanger@ccs.neu.edu
 *
 * An implementation of maps using abstract
 * data types. Three classes: abstract class FMap
 * and Empty and Include which serve as the basic
 * creators for FMap.
 * An itterator is added to transverse this map.
 */

/**
 * ArrayList is the backbone of itterator
 * collections is used to sort the arrayList
 * Iterator is used to implement next, hasNext, and remove
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Class FMat provides framework for an ADT map.
*/
public abstract class FMap<K,V> implements Iterable<K> { 

    abstract FMap<K,V> include (K k, V v);

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

    abstract ArrayList<K> addTo(FMap m);

    /** 
     * iterator
     * gets a new instance of iterator for the map
     * @param --
     * @return Iterator<K> that can itterate through the map
     */
    public Iterator<K> iterator() {
        return new FIterator<K>(this);
    }        

     /** 
     * iterator
     * gets a new instance of iterator for the map
     * @param ava.util.Comparator<? super K> c collection to be used as sort
     * @return Iterator<K> that can iterate through the map, sorted by c
     */
    public Iterator<K> iterator(java.util.Comparator<? super K> c) {
        return new FIterator<K>(this,c);
    }        

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
    public static  <K,V> FMap<K,V> empty(java.util.Comparator<? super K> c) {
        return new BST_Empty<K,V>(c);
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
abstract class FMapL<K,V> extends FMap<K,V> {

    //Warnings from unchecked cast in containsKey call
    @SuppressWarnings("unchecked")

    /* 
     * addTo
     * heper method to fill the arrayList. Only
     * called by the constructor. only adds keys
     * once.
     * @param FMap to construct arrayList from
     * @return ArrayList<K> new and filled arrayList of unique keys
     */
    ArrayList<K> addTo(FMap m) {
        Include i;
        ArrayList<K> keysList = new ArrayList<K>();
        while (!m.isEmpty()) {
            i = (Include) m;    
            if ( !i.m0.containsKey(i.k0) ) {
                keysList.add((K)i.k0);
            }
            m = i.m0;
        }
        return keysList;
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
}
/**
 * Class FIterator<K> provides support for an FMap iterator
*/
class FIterator<K>  implements Iterator<K> {

    /*
     * m0 is a reference to the current FMap
     * c is an optional comparator used to sort
     * keysList is the arrayList used to store the itterable keys
     * keysIt is the itterator of the arrayList that provides support for next
     * and hasNext
     */    
    FMap m0;
    java.util.Comparator<? super K> c;
    ArrayList<K> keysList;
    Iterator<K> keysIt;

    /*
     * constructor for FIterator which constructs the arrayList and
     * stores the map.
     * @param FMap of current map to construct itterator from
     * @return --
     */
    FIterator(FMap m0) {
        this.m0 = m0;
        keysList = m0.addTo(m0);
        keysIt = keysList.iterator();
    }

    /* 
     * additonal constructor which calls previous constructor and 
     * then uses the comparator to sort the keys.
     * @param FMap of current map to construct itterator from
     * @param Comparator for which to sort by
     * @return --
     */
    FIterator(FMap m0, java.util.Comparator<? super K> c) {
        this(m0);
        this.c = c;
        Collections.sort(keysList,c);
    }
    
    /*
     * hasNext
     * returns if the ArrayList has more elements.
     * uses the arrayList itterator to do so.
     * @param --
     * boolean indicating if there are more element
     */
    public boolean hasNext() {
        return keysIt.hasNext(); 
    }

    /* 
     * next
     * get the next key from the arrayList's itterator
     * throws exception if !hasNext()=true
     * @param --
     * @return K of the next key 
     */
    public K next() {
        return keysIt.next();
    }

    /* 
     * remove
     * unsuppoted method
     * has no effects and throws exception
     * @param --
     * @return --
     */
    public void remove() {
        throw new 
            UnsupportedOperationException("UnsupportedOperationException");
    }     
}

/**
 * Class Empty extends FMap.
 * An empty FMap. Has no items.
 */
class Empty<K,V> extends FMapL<K,V> {

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
class Include<K,V> extends FMapL<K,V> {

    /**
     * FMap m0 hold previous FMap.
     * K k0 is the key value of current.
     * V v0 is the value at the current key.
    */
    FMap<K,V> m0;
    K k0;
    V v0;
    private int size;
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
        size = m0.size();
        if (!m0.containsKey(k0)) {
            size++;
        }
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
        return size;
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
        //K k is returned by this's itterator
        for (K k : this ) {
            if (! (m2.containsKey(k) && this.get(k).equals(m2.get(k))) ) {
                return false;
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
        return (hash+5) * 7;
    }
} 
abstract class BST<K,V> extends FMap<K,V> {
    abstract boolean isLeafMethod();
    abstract BST includeMethod(K k, V v); 

    java.util.Comparator<? super K> c;
    
    public boolean isLeaf() {
        return isLeafMethod();
    }

    public FMap include(K k, V v) {
        return includeMethod(k,v);
    }
    public int hashCode() { 
        return size();
    }
    @SuppressWarnings("unchecked")
    ArrayList<K> addTo(FMap m) {
        ArrayList<K> keysList = new ArrayList<K>();
        if (!this.isEmpty()) {
            BST_Include i = (BST_Include) m;
            keysList.add((K)i.k0);
        }
        return traverse(m,keysList);
    }
    @SuppressWarnings("unchecked")
    private ArrayList<K> traverse(FMap m, ArrayList<K> keysList) {
        if (m.isEmpty()) {
            return keysList;
        }
        BST_Include i = (BST_Include) m;
        if( !i.right.isEmpty() ) {
            BST_Include ir = (BST_Include)i.right;
            keysList.add((K)ir.k0);
        }
        if( !i.left.isEmpty() ) {
            BST_Include il = (BST_Include)i.left;
            keysList.add((K)il.k0);
        }
        traverse(i.right,keysList);
        traverse(i.left,keysList);
        return keysList;
    }
}

class BST_Include<K,V> extends BST<K,V> {

    BST<K,V> right;
    BST<K,V> left;
    K k0;
    V v0;
    int size;
    BST_Include(K k, V v, 
        BST<K,V> left, BST<K,V> right, java.util.Comparator<? super K> c) {
        k0 = k;
        v0 = v;
        this.right = right;
        this.left = left;
        this.c = c;
        size = right.size() + left.size() + 1;
    }
    @SuppressWarnings("unchecked")
    public BST<K,V> includeMethod (K k, V v) {
        if ( c.compare(k,k0) < 0 ) {
            return new BST_Include( k0, v0, left.includeMethod(k,v), right, c);
        }
        if ( c.compare(k,k0) > 0 ) {
            return new BST_Include( k0, v0, left, right.includeMethod(k,v), c);
        }
        size--;
        return new BST_Include( k0, v, left, right, c);
    }    
    public boolean isEmptyMethod() {
        return false;
    }
    public int sizeMethod() {
        return size;
    }
    public boolean containsKeyMethod(K k) {
        if ( c.compare(k,k0) < 0) {
            return left.containsKeyMethod(k);
        }
        if ( c.compare(k,k0) > 0) {
            return right.containsKeyMethod(k);
        }
        return k.equals(k0);
    }
    public V getMethod(K k) {
        if ( k.equals(k0) ) {
            return v0;
        } else if ( c.compare(k,k0) > 0 ) {
            return right.get(k);
        } else {
            return left.get(k);
        }
    }
    @SuppressWarnings("unchecked")
    public boolean equalsMethod(FMap m2) {
        //K k is returned by this's itterator
        for (K k : this ) {
            if (! (m2.containsKey(k) && this.get(k).equals(m2.get(k))) ) {
                return false;
            }
        }
        return true;   
    }
    public boolean isLeafMethod() {
        return left.isEmpty() && right.isEmpty();
    }
}

class BST_Empty<K,V> extends BST<K,V> {

    BST_Empty(java.util.Comparator<? super K> c) {
        this.c = c;
    }   
    public BST<K,V> includeMethod (K k, V v) {
        return new BST_Include<K,V>(k,v,this,this,this.c);
    }
    public boolean isEmptyMethod() {
        return true;
    }
    public int sizeMethod() {
        return 0;
    }
    public boolean containsKeyMethod(K k) {
        return false;
    }
    public V getMethod(K k) {
        throw new RuntimeException("Attempted to get from empty map");
    }
    public boolean equalsMethod(FMap m2) {
        return true;
    }
    public boolean isLeafMethod() {
        return true;
    }
}

