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

public abstract class FMap<K,V> { 

    abstract boolean isEmptyMethod();
    abstract int sizeMethod();
    abstract boolean containsKeyMethod(K k);
    abstract V getMethod(K k);

    public static <K,V> FMap<K,V> empty() {
        return new Empty<K,V>();
    }
    public static  <K,V> FMap<K,V> empty(java.util.Comparator c) {
        return new Empty<K,V>();
    }

    public FMap<K,V> include (K k, V v) {
        return new Include<K,V>(this, k,v);
    }
      
    public boolean isEmpty() {
        return isEmptyMethod();
    }
 
    public int size() {
        return sizeMethod();
    }
    
    public boolean containsKey(K k) {
        return containsKeyMethod(k);
    }
    
    public V get(K k) {
        return getMethod(k);
    }
    public String toString() {
        return "{...(" + sizeMethod() + " keys mapped to values)...}";
    }
    public int hashCode() { 
        return sizeMethod();
    }
    private boolean equals(FMap m2) {
        return true;
    }
    public boolean equals( Object o) {
        if (o != null && o instanceof FMap) {
            FMap m2 = (FMap) o;
            if ( m2.size() == this.size() ) {
                return equals(m2);
            }
        }
        return false;
    }

}


class Empty<K,V> extends FMap<K,V> {

    Empty() {}
    
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

} 

class Include<K,V> extends FMap<K,V> {

    FMap<K,V> m0;
    K k0;
    V v0;

    Include (FMap<K,V> m0, K k0, V v0) {
        this.m0 = m0;
        this.k0 = k0;
        this.v0 = v0;
    }

    public boolean isEmptyMethod() {
        return false;
    }

    public int sizeMethod() {
        int size_m = m0.size();
        if ( m0.containsKey(k0) ) {
            return size_m;
        }
        return size_m + 1;
    }
    
    public boolean containsKeyMethod(K k) {
        if ( k.equals(k0) ) {
            return true;
        }   
        return m0.containsKey(k);
    }

    public V getMethod(K k) {
        if ( k.equals(k0) ) { 
            return v0;
        }
        return m0.get(k);
    }
} 

