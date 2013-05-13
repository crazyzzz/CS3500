public abstract class FListInteger { 
    //abstract FListInteger emptyListMethod();
    //abstract FListInteger addMethod(FListInteger f, Integer x);
    abstract boolean isEmptyMethod();
    abstract Integer getMethod(int n);
    abstract FListInteger setMethod(int n, Integer y);
    abstract int sizeMethod();
    abstract boolean containsMethod(Integer y);

    public static FListInteger emptyList() {
        return new Empty();
    }
    public static FListInteger add(FListInteger f, Integer x) {
        return new Add( f,x );
    }
    public static boolean isEmpty(FListInteger f) {
        return f.isEmptyMethod();
    }
    public static Integer get(FListInteger f, int n) {
        return f.getMethod( n );
    }
    public static FListInteger set(FListInteger f, int n, Integer y ) {
        return f.setMethod( n, y );
    }
    public static int size(FListInteger f) {
        return f.sizeMethod();
    }
    public static boolean contains(FListInteger f, Integer y) {
        return f.containsMethod(y);
    }
    public boolean equals(FListInteger f2) {
        int size_v = this.sizeMethod();
        if (size_v != f2.sizeMethod()) {
            return false;
        }
        for (int i = 0; i < size_v; i++ ) {
            if ( !(this.getMethod(i).equals( f2.getMethod(i) ) ) ) {
                return false;
            }
        }
        return true;
    }
    public boolean equals(Object f2) {
        if (f2 instanceof FListInteger) {
            return equals(f2);
        }
        return false;
    }
}
class Empty extends FListInteger {
    Empty() {}
     Integer getMethod( int n ) {
        if ( n == 0 ) { 
            return null;
        } else if (n < 0 ) { 
             throw new RuntimeException("Attempted to get negative number");      
        }
        return get( this,n-1 );
    }
    FListInteger setMethod( int n, Integer y ) {
        if ( n == 0 ) { 
            return add(this,y);
        } else if (n < 0 ) { 
             throw new RuntimeException("Attempted to get negative number");      
        }
        return add( set(this,n-1,y),null);
    }
    boolean isEmptyMethod() {
        return true;    
    }
    int sizeMethod() {
        return 0;
    }
    boolean containsMethod(Integer y) {
        return false;
    }
    public String toString() {
        return "[]";
    }
    public int hashCode() {
        return 0;
    }
}
class Add extends FListInteger {
    FListInteger f;
    Integer x;
    
    Add(FListInteger f, Integer x) {
        this.f = f;
        this.x = x;
    }
    Integer getMethod( int n ) {
        if ( n == 0 ) { 
            return x;
        } else if (n < 0 ) { 
             throw new RuntimeException("Attempted to get negative number");      
        }
        return get( f,n-1 );
    }
    FListInteger setMethod( int n, Integer y ) {
        if ( n == 0 ) { 
            return add(f,y);
        } else if (n < 0 ) { 
             throw new RuntimeException("Attempted to get negative number");      
        }
        return add( set(f,n-1,y),x);
    }
    boolean isEmptyMethod() {
        return false;
    }
    int sizeMethod() {
        return size(f)+1;
    }
    boolean containsMethod(Integer y) {
        if ( x.equals(y) ) { 
            return true;
        }
        return contains(f,y);
    }
    public String toString() {
        if( isEmpty(f) ) { 
            return "[" + x.toString() + "]";
        }
        return "[" + x.toString() + ", " +  f.toString().substring(1, f.toString().length());
    }
    public int hashCode() {
        int size_v = size(f);
        int hash = 0;
        while (size_v > 0) {
            hash=hash*5 + get(f,--size_v).intValue();
        }
        return hash;
    }
}
