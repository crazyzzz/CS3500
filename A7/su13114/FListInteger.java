public abstract class FListInteger{
	abstract FListInteger addMethod(Integer y);
	abstract boolean isEmptyMethod();
	abstract Integer getMethod(int n);
	abstract FListInteger setMethod(int n, Integer y);
	abstract int sizeMethod();
	abstract boolean containsMethod(Integer y);
	abstract String toStringMethod();
	abstract boolean equalsMethod(FListInteger l1);
	abstract int hashCodeMethod();
	abstract FListInteger getFMethod();

	public static FListInteger emptyList(){
		return new Empty();
	}

	public static FListInteger add(FListInteger f, Integer y){
		return f.addMethod(y);
	}

	public static boolean isEmpty(FListInteger f){
		return f.isEmptyMethod();
	}

	public static Integer get(FListInteger f, int n){
		return f.getMethod(n);
	}

	public static FListInteger set(FListInteger f, int n, Integer y){
		return f.setMethod(n, y);
	}

	public static int size(FListInteger f){
		return f.sizeMethod();
	}

	public static boolean contains(FListInteger f, Integer y){
		return f.containsMethod(y);
	}

	public String toString(){
		return this.toStringMethod();
	}

	public static FListInteger getF(FListInteger f){
		return f.getFMethod();
	}

	public boolean equals(Object obj){
		if (obj == null) {return false;
		} else {
			if (obj instanceof FListInteger) 
			{FListInteger l1 = (FListInteger) obj; 
				return this.equalsMethod(l1);
			} else {return false;
			}
		}
	}

	public int hashCode(){
		return this.hashCodeMethod();
	}
}

class Empty extends FListInteger{
	Empty(){}

	FListInteger addMethod(Integer y){
		return new List(this, y);
	}

	boolean isEmptyMethod(){
		return true;
	}

	Integer getMethod(int n){
		throw new RuntimeException 
			("Cannot get the Integer of an empty list");
	}

	FListInteger setMethod(int n, Integer y){
		throw new RuntimeException 
			("Cannot set the Integer of an empty list");
	}

	int sizeMethod(){
		return 0;
	}

	boolean containsMethod(Integer y){
		return false;
	}

	String toStringMethod(){
		return "[]";
	}

	FListInteger getFMethod(){
		throw new RuntimeException
			("Cannot get the FListInteger of an empty");
	}

	boolean equalsMethod(FListInteger l1){
		if (FListInteger.isEmpty(l1)) {return true;
		} else {return false;
		}
	}

	int hashCodeMethod(){
		return 0;
	}
}

class List extends FListInteger{
	FListInteger f;
	Integer x;
	List(FListInteger f, Integer x){
		this.f = f;
		this.x = x;
	}

	FListInteger addMethod(Integer y){
		return new List(this, y);
	}

	boolean isEmptyMethod(){
		return false;
	}

	Integer getMethod(int n){
		if (n == 0){return x;
		} else {if (n > 0){return f.getMethod(n - 1);
		} else {throw new RuntimeException 
			("Cannot get the Integer of a -nth list");
		}}
	}

	FListInteger setMethod(int n, Integer y){
		if (n == 0){
            return new List(f, y);
		} else {if (n > 0){return new List(f.setMethod(n - 1, y), x);
		} else {throw new RuntimeException 
			("Cannot set the int of a -nth list");
		}}
	}

	int sizeMethod(){
		return 1 + f.sizeMethod();
	}

	boolean containsMethod(Integer y){
		if (x.equals(y)) {return true;
		} else {return f.containsMethod(y);
		}
	}

	String toStringMethod(){
		if (f.isEmptyMethod()) {return "[" + x.toString() + "]";
		} else {return "[" + x.toString() + ", " + 
			f.toString().substring(1, f.toString().length());
		}
	}

	FListInteger getFMethod(){
		return f;
	}

	boolean equalsMethod(FListInteger l1){
		/*
		// testing to make sure this works
		if (this.sizeMethod() == l1.sizeMethod()) {
			// should change == to .equals as above, but this
			// is just for fixing the exception thrown
			if (x.equals(l1.getMethod(0))) {
				return f.equalsMethod(l1.getFMethod());
			} else {
				return false;
			}
		} else {
			return false;
		}
		*/
		if (x == l1.getMethod(0)) 
		{
            System.out.println(x + " " + l1.getMethod(0));
            if (this.sizeMethod() == l1.sizeMethod()) 
			{return f.equalsMethod(l1.getFMethod());
			} else {return false;
			}
		} else {return false;
		}
	}

	int hashCodeMethod(){
		return (int)Math.round(x) + f.hashCodeMethod();
	}
}
