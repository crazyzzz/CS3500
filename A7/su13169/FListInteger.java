public abstract class FListInteger {

	abstract int sizeMethod();

	abstract boolean isEmptyMethod();

	abstract Integer getMethod(int n);

	abstract boolean containsMethod(Integer y);

	abstract FListInteger setMethod(int x, Integer y); 

	public abstract String toString();



	public static FListInteger emptyList(){
		return new EmptyList();
	}

	public static FListInteger add(FListInteger f, Integer x){
		return new Add(f,x);
	}

	public static int size(FListInteger f){
		return f.sizeMethod();
	}

	public static boolean isEmpty(FListInteger f){
		return f.isEmptyMethod();
	}

	public static boolean contains(FListInteger f, Integer y){
		return f.containsMethod(y);
	}

	public static Integer get(FListInteger f, int n){
		return f.getMethod(n);
	}

	public static FListInteger set(FListInteger f, int n, Integer y){
		return f.setMethod(n, y);
	}

	public boolean equals(Object o){
		if (o instanceof FListInteger){
			FListInteger f2 = (FListInteger) o;
			if (!(size(this) == size(f2))) {
				return false; }
			for (int i = 0; i < size(this); i++){
				if(!(FListInteger.get(this, i).
							equals(FListInteger.get(f2, i))))
				{
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}


	public int hashCode(){
		return 0;
	}
}

class EmptyList extends FListInteger {

	EmptyList(){}

	int sizeMethod(){
		return 0;
	}

	boolean isEmptyMethod(){
		return true;
	}

	boolean containsMethod(Integer y){
		return false;
	}

	Integer getMethod(int n){
		throw new RuntimeException("can not get element from empty list");
	}

	FListInteger setMethod(int n, Integer y){
		throw new RuntimeException("can not set empty list");
	}

	public String toString(){
		return "[]";
	}
}

class Add extends FListInteger {

	FListInteger f;     
	Integer x;   

	Add(FListInteger f, Integer x){
		this.f = f;
		this.x = x;   
	}

	int sizeMethod(){
		return 1 + FListInteger.size (f);
	}

	boolean isEmptyMethod(){
		return false;
	}

	boolean containsMethod(Integer y){
		if ( x.equals(y)) {
			return true;
		} else {
			return FListInteger.contains (f, y); 
		}  
	}

	Integer getMethod(int n){
		if (n == 0) {
			return x; }
		else {
			return FListInteger.get (f, n - 1);
		}
	}

	FListInteger setMethod(int n, Integer y){
		if (n == 0){
			return FListInteger.add (f, y); }
		else{
			return FListInteger.add (FListInteger.set (f, n - 1, y), x);
		}
	}

	public String toString(){
		if (FListInteger.isEmpty (f)) {
			return "[" + x.toString() + "]"; } 
		else {
			return "[" + x.toString() + ", " +  f.toString().
				substring(1, f.toString().length());
		}
	}
}   

