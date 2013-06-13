/**
 * Assignment 9 - FMap.
 * @author Kevin Langer
 * klanger@ccs.neu.edu
 *
 * An implementation of maps using abstract
 * data types. Three classes: abstract class FMap
 * and Empty and Include which serve as the basic
 * creators for FMap.
 * An iterator is added to transverse this map.
 */

/**
 * ArrayList is the backbone of iterator
 * collections is used to sort the arrayList
 * Iterator is used to implement next, hasNext, and remove
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Class FMap provides framework for an ADT map.
*/
public abstract class FMap<K,V> implements Iterable<K> { 

    /** 
     * include
     * @param K key
     * @param V value
     * @return FMap<K,V> of implementation specific FMAp
     */
    public abstract FMap<K,V> include (K k, V v);

    /**
     * accept
     * @param K key 
     * @param V value
     * @return FMap<K,V> of FMap where all elements are visited
     */
    public abstract FMap<K,V> accept (Visitor<K,V> v);
    
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
     * addAllKeys
     * creats and returns an ArrayList of all unique keyvalues in FMap m
     * @param FMap to get keys from
     * @return ArrayList<k> containing all keys, no order expected.
     */ 
    abstract ArrayList<K> addAllKeys(FMap m);

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
        //Center hashcode in interger range
        int hash = 0x0000FFFF;
        for (K k : this) {
            //Make it unlikely that a non-negative v or k has a zero hashCode
            hash += (k.hashCode()+5)*59 ^ (this.get(k).hashCode()+7)*83;
        }
        return hash;
    }
            
}

/**
 * Class FMapL encapsulates all non-comparator FMaps
 */
abstract class FMapL<K,V> extends FMap<K,V> {

    //Warnings from unchecked cast in containsKey call
    @SuppressWarnings("unchecked")

    /* 
     * addAllKeys
     * heper method to fill the ArrayList<K>. Only
     * called by the constructor. only adds keys
     * once.
     * @param FMap to construct arrayList from
     * @return ArrayList<K> new and filled arrayList of unique keys
     */
    ArrayList<K> addAllKeys(FMap m) {
        //Parse variable for non-empty FMaps to Includes
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

    /*
     * accept
     * provides a list based FMap where all the elemts
     * of the FMap calling accept have been visited
     * The visitor is a transform function of the values
     * @param Visitor from the visitor class defined elsewhere
     * @return FMap list form of the FMap calling this method
     */ 
    public FMap<K,V> accept (Visitor<K,V> v) {
        //Construct new FMap        
        FMap<K,V> f = FMap.empty();
        for (K k : this ) {
            //preform accept transform
            f = f.include(k,v.visit(k,this.get(k)));     
        }
        return f;
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
    boolean isEmptyMethod() {
        return true;
    }
    
    /**
     * sizeMethod
     * get size of Map; is zero for an empty map
     * @param --
     * @return int with value zero
     */
    int sizeMethod() {
        return 0;
    }
    
    /**
     * containsKeyMethod
     * Empty maps contain no keys.
     * @param --
     * @return boolean value of false indiciating no keys.
     */
    boolean containsKeyMethod(K k) {
        return false;
    }
    
    /**
     * getMethod
     * Empty maps contain no keys, so no values at the key.
     * Throws exception for accessing from an empty map.
     * @param --
     * @return --
     */
    V getMethod(K k) {
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
    //Removed fast size to meet visitor timing requirements
    /*
    private int size;
    */

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
        //Removed fast size to meet visitor timing requirements
        /*
        size = m0.size();
        if (!m0.containsKey(k0)) {
            size++;
        }
        */
    }

    /**
     * isEmptyMethod
     * @param --
     * @return boolean set to false indiciating FMap has values.
     */
    boolean isEmptyMethod() {
        return false;
    }

    /**
     * sizeMethod
     * get size of unqiue elements in FMap
     * @param --
     * @return int representing the size of the FMap
     */
    int sizeMethod() {
        if (!m0.containsKey(k0)) {
            return 1 + m0.size();
        }
        return m0.size();
    }
    
    /**
     * containsKeyMethod
     * maps contain key if object k equals the key paramater
     * for some value in the map.
     * @param K key to check for
     * @return boolean value indiciating if key was found.
     */
    boolean containsKeyMethod(K k) {
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
    
} 

/**
 * Class BST encapsulates all comparator FMaps
 * implements red-black binary search trees to
 * improve FMap preformance.
 * Based on Chris Okasaki's Red-Black dissertation linked below 
 * http://www.ccs.neu.edu/course/cs3500su13-1/jfp99redblack.pdf
 */ 
abstract class BST<K,V> extends FMap<K,V> {
    
    /*
     * Colors represent node state
     * RED/BLACK chosen for legacy
     */
    protected enum Color {
        RED, BLACK
    }
    
    /*
     * return value at this
     */
    abstract V getV();

    /*
     * return key at this
     */

    abstract K getK();
    /*
     * return left subtree
     */

    abstract BST<K,V> getLeft();

    /*
     * return right subtree
     */
    abstract BST<K,V> getRight();

    /*
     * includeMethod
     * returns a binary search tree where the key value
     * pair is gaurnteed to be added to maintain BST structure
     * @param K key
     * @param V value
     * @return BST with key and value correctly added
     */
    abstract BST<K,V> includeMethod(K k, V v); 
    //Both BST_Empty and BST_Include must support a comparator
    java.util.Comparator<? super K> c;
    //Bosh BST_Empty and BST_Include have a color
    Color color;

     /*
     * include
     * wrapper for includeMethod to implement FMap's include
     * @param K key
     * @param V value
     * @return BST with key and value correctly added
     */
    public FMap<K,V> include(K k, V v) {
        return includeMethod(k,v);
    }
    
    //Needed for uncheck cast of K while adding to ArrayList
    @SuppressWarnings("unchecked")

     /**
     * addAllKeys
     * creats and returns an ArrayList of all unique keyvalues in FMap m
     * @param FMap to get keys from
     * @return ArrayList<k> containing all keys, no order expected.
     */
    ArrayList<K> addAllKeys(FMap m) {
        ArrayList<K> keysList = new ArrayList<K>();
        if (!this.isEmpty()) {
            BST_Include i = (BST_Include) m;
            keysList.add((K)i.k0);
        }
        return traverse(m,keysList);
    }

    //Needed for uncheck cast of K while adding to ArrayList
    @SuppressWarnings("unchecked")

    /**
     * traverse
     * implements inorder traversal while adding keys to the ArrayList
     * @param FMap which keys are added from
     * @param ArrayList<K> which keys are added to
     * @return ArrayList<k> of completed ArrayList<K> containing all keys
     */
    private ArrayList<K> traverse(FMap m, ArrayList<K> keysList) {
        //end when a empty branch is reached
        if (m.isEmpty()) {
            return keysList;
        }
        //non-empty elements are cast
        BST_Include i = (BST_Include) m;
        //Add non-empty elements to the ArrayList<K>

        //Travse tree in-order
        traverse(i.left,keysList);
        if( !i.right.isEmpty() ) {
            BST_Include ir = (BST_Include)i.right;
            keysList.add((K)ir.k0);
        }
        if( !i.left.isEmpty() ) {
            BST_Include il = (BST_Include)i.left;
            keysList.add((K)il.k0);
        }
        traverse(i.right,keysList);
        return keysList;
    }
    
    //Needed as BST_Include does not return a BST but rather a BST_Include
    @SuppressWarnings("unchecked")

    /**
     * node
     * Factory method for BST_Include
     * @param K key
     * @param V value
     * @return FMap new with correct structure and inserted key and value
     */
    BST<K,V> node(K k, V v, 
        BST<K,V> left, BST<K,V> right, Color color,
        java.util.Comparator<? super K> c) {
        return new BST_Include( k, v, left, right, color, c);
    } 

    /*
     * accept
     * provides a list based FMap where all the elemts
     * of the FMap calling accept have been visited
     * The visitor is a transform function of the values
     * @param Visitor from the visitor class defined elsewhere
     * @return FMap list form of the FMap calling this method
     */ 
    public FMap<K,V> accept (Visitor<K,V> v) {
        //Construct new FMap        
        FMap<K,V> f = FMap.empty( this.c);
        Iterator<K> i = this.iterator(this.c);
        K k;
        while( i.hasNext() ) {
            //preform accept transform
            k = i.next();
            f = f.include(k,v.visit(k,this.get( k )));     
        }
        return f;
    }

    /*
     * checks this's color for red
     * @return boolean indicating if the color is red
     */
    boolean isRed() {
        return color.equals(Color.RED);
    }
}

/**
 * Class BST_Include is a basic creator of BST and used to add elements.
 * implements binary search trees to improve FMap preformance.
 */
class BST_Include<K,V> extends BST<K,V> {

    /*
     * right is the right subtree, can be empty
     * left is the left subtree, can be emty
     * k0 is the key at node
     * v0 is the value at node
     * size is the cached size of the tree
     */ 
    BST<K,V> right;
    BST<K,V> left;
    K k0;
    V v0;
    int size;

    /* 
     * BST_Include
     * constructor that simply adds an element and updates size. 
     * BST_Include is NOT a client method and should not used as such.
     * @param K key
     * @param V value
     * @param left subtree, can be empty
     * @param right subtree, can be empty
     * @param Comparator<? super K> used to determine ordering
     * @return BST_Include a tree with updated key and value.
     */
    BST_Include(K k, V v, 
        BST<K,V> left, BST<K,V> right, Color color,
        java.util.Comparator<? super K> c) {
        size = right.size() + left.size() + 1 ;        
        k0 = k;
        v0 = v;
        this.right = right;
        this.left = left;
        this.c = c;
        this.color = color;
    }

    /*
     * includeMethod
     * correctly inserts new node into a tree.
     * duplicates are not added, newest value is kept.
     * @param K key
     * @param V value
     * @return BST<K,V> updated tree
     */
    @SuppressWarnings("unchecked")
    BST<K,V> includeMethod (K k, V v) {
        BST<K,V> t;
        if ( c.compare(k,k0) < 0 ) {
            t = node( k0, v0, left.includeMethod(k,v), right, color, c);
            return balanceMethod(t);
            //return t;
        }
        if ( c.compare(k,k0) > 0 ) {
           t = node( k0, v0, left, right.includeMethod(k,v), color, c);
            return balanceMethod(t);
            //return t;
        }
        t = node( k0, v, left, right, color, c);
        //return balanceMethod(t);
        return t;
    }    

    /*
     * balanceMethod
     * gets passed a BST and balances in accord
     * to the four cases given in the red black
     * tree paper. Fixes red parent invarient.
     * note: does NOT modify this.
     * @return BST<K,V> balanced tree
     */ 
    private BST<K,V> balanceMethod(BST<K,V> t) { 

        if ( t.getLeft().isRed() && t.getLeft().getLeft().isRed()  ) {

            /*     B
             *    / \
             *   R
             *  /
             * R
             */
            return swap(t.getLeft().getLeft().getLeft(), 
                t.getLeft().getLeft().getRight(), t.getLeft().getRight(),
                t.getRight(), t.getLeft().getLeft(), t.getLeft(), t); 

        } else if ( t.getLeft().isRed() && t.getLeft().getRight().isRed() ) {

            /*     B
             *    / \
             *   R
             *  / \
             *     R
             */
            return swap(t.getLeft().getLeft(), 
                t.getLeft().getRight().getLeft(), 
                t.getLeft().getRight().getRight(), t.getRight(),
                t.getLeft(), t.getLeft().getRight(), t);

        } else if ( t.getRight().isRed() && t.getRight().getLeft().isRed() ) {
            
            /*     B
             *    / \
             *       R 
             *      / \
             *     R
             */
            return swap(t.getLeft(), t.getRight().getLeft().getLeft(),
                t.getRight().getLeft().getRight(), t.getRight().getRight(),
                t, t.getRight().getLeft(), t.getRight() );

        } else if ( t.getRight().isRed() && t.getRight().getRight().isRed() ){
            
            /*     B
             *    / \
             *       R 
             *      / \
             *         R
             */
            return swap(t.getLeft(), t.getRight().getLeft(),
                t.getRight().getRight().getLeft(), 
                t.getRight().getRight().getRight(), t, t.getRight(),
                t.getRight().getRight());

        } 
        return (BST<K,V>)t;
    }

    /*
     * swap
     * preform a swap based on the a, b, c, d, x, y, z 
     * @param BST<K,V> x7 the subtrees specified in Okasaki maintaining names
     * @return BST<K,V> swaped based on order given in Okasaki
     */
    private BST<K,V> swap(BST<K,V> a, BST<K,V> b, BST<K,V> c, BST<K,V> d,
        BST<K,V> x, BST<K,V> y, BST<K,V> z) {
        
         /*     R
          *    / \
          *   B   B 
          *  / \ / \
          */
        BST<K,V> left = node(x.getK(),x.getV(), a, b, Color.BLACK, this.c);
        BST<K,V> right = node(z.getK(), z.getV(), c , d, Color.BLACK, this.c);
        return node(y.getK(), y.getV(), left, right, Color.RED, this.c);
    }

    /*
     * isEmptymethod
     * tree with nodes is not empty
     * @param --
     * @return boolean
     */
    boolean isEmptyMethod() {
        return false;
    } 

    /*
     * sizeMethod
     * number of unique elements in FMap
     * @param --
     * @return int 
     */
    int sizeMethod() {      
        return size;
    }

    /* 
     * containsKeyMethod
     * returns true if the key is in the FMap
     * checks all non-empty subtrees
     * @param K key 
     * @param boolean indiciating if it is contained
     */
    boolean containsKeyMethod(K k) {
        if ( c.compare(k,k0) < 0) {
            return left.containsKey(k);
        }
        if ( c.compare(k,k0) > 0) {
            return right.containsKey(k);
        }
        return k.equals(k0);
    }
    
    /* 
     * getMethod
     * returns value at specified key
     * checks all non-empty subtrees for key
     * @param K key
     * @param V value
     */
    V getMethod(K k) {
        if ( c.compare(k,k0) == 0 ) {
            return v0;
        } else if ( c.compare(k,k0) > 0 ) {
            return right.get(k);
        }
        return left.get(k);
    }

    //Needed for passing K to containsKey
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
     * getK
     * geter helper method
     * @param --
     * @return K key
     */
    K getK() {
        return k0;
    }

    /* 
     * getV
     * geter helper method
     * @param --
     * @return V value
     */
    V getV() {
        return v0;
    }

    /* 
     * getLeft
     * geter helper method
     * @param --
     * @return BST<K,V> left subtree
     */
    BST<K,V> getLeft() {
        return left;
    }
    
    /* 
     * getRight
     * geter helper method
     * @param --
     * @return BST<K,V> right subtree
     */
    BST<K,V> getRight() {
        return right;
    }
}

/**
 * Class BST_Empty is a basic creator of BST and used to represent an
 * empty subtree.
 * implements binary search trees to improve FMap preformance.
 */
class BST_Empty<K,V> extends BST<K,V> {
    
     /* 
     * BST_Empty
     * constructor that returns a tree with no elements. 
     * BST_Empty is used as the basic creator of the tree.
     * @param Comparator<? super K> used to determine ordering
     * @return BST_Empty an empty tree.
     */
    BST_Empty(java.util.Comparator<? super K> c) {
        this.c = c;
        this.color = Color.BLACK;
    }   

    /*
     * includeMethod
     * can always add a key and value to an empty FMap
     * @param K key
     * @param V value
     * @return BST<K,V> of tree containing only a root
     */
    BST<K,V> includeMethod (K k, V v) {
        //return node(k,v,this,this,this.color,this.c);
        return node(k,v,this,this,Color.RED,this.c);
    }

    /*
     * isEmptymethod
     * tree with no-nodes is empty
     * @param --
     * @return boolean
     */
    boolean isEmptyMethod() {
        return true;
    }

     /*
     * sizeMethod
     * number of unique keys in FMap
     * @param --
     * @return int with value 0, as empty FMaps have no keys
     */
    int sizeMethod() {
        return 0;
    }

    /* 
     * containsKeyMethod
     * all subtrees of empty are empty subtrees
     * @param K key 
     * @param boolean indiciating that the key is not in empty tree
     */
    boolean containsKeyMethod(K k) {
        return false;
    }

    /* 
     * getMethod
     * throws an exception as no keys are contains in an empty FMap
     * @param K key
     * @param V value
     */
    V getMethod(K k) {
        throw new RuntimeException("Key not found");
    }

     /* 
     * getK
     * throws an exception as no keys are contains in an empty FMap
     * @param --
     */
    K getK() {
        throw new RuntimeException("No keys in empty map");
    }

    /* 
     * getV
     * throws an exception as no keys are contains in an empty FMap
     * @param --
     */
    V getV() {
        throw new RuntimeException("No values in empty map");
    }

    /* 
     * getLeft
     * subtrees of an empty tree are identity
     * @param --
     * @return BST<K,V> empty tree
     */
    BST<K,V> getLeft() {
        return this;
    }

    /* 
     * getRight
     * subtrees of an empty tree are identity
     * @param --
     * @return BST<K,V> empty tree
     */
    BST<K,V> getRight() {
        return this;
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
    @SuppressWarnings("unchecked")
    FIterator(FMap m0) {
        this.m0 = m0;
        keysList = m0.addAllKeys(m0);
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
