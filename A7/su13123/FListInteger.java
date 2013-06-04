public abstract class FListInteger{

	public static FListInteger emptyList(){
		return new EmptyList();}

	public static FListInteger add(FListInteger f, Integer x){
		return new Add(f, x);
	}


	abstract boolean isEmptyMethod();
	public static boolean isEmpty(FListInteger f) {
		return f.isEmptyMethod();
	}

	abstract Integer getMethod(int n);
	public static Integer get(FListInteger f, int n){
		return f.getMethod(n);
	}

	abstract FListInteger setMethod(int n, Integer y);
	public static FListInteger set(FListInteger f2, int n, Integer y){
		return f2.setMethod(n, y);
	}
	abstract int sizeMethod();
	public static int size(FListInteger f){
		return f.sizeMethod();
	}


	abstract boolean containsMethod(Integer y);
	public static boolean contains(FListInteger f, Integer y){
		return f.containsMethod(y);
	}


	abstract public String toString();

	public boolean equals(Object o){
		if (!(o instanceof FListInteger)) {
			return false;
		}
		else {
			FListInteger f2 = (FListInteger) o;
			if (! (FListInteger.size(this) 
						== FListInteger.size (f2)))
				return false;
			else
				for (int i=0; i < FListInteger.size(this); i++){
					if 
						(! (FListInteger.get(this, i) == (FListInteger.get(f2, i)))) 
							return false;

				}
			{return true;}
		}

	}

	public int hashCode() {
		return FListInteger.size(this);
	}



}


class EmptyList extends FListInteger{

	EmptyList(){}


	boolean isEmptyMethod() {
		return true;
	}


	Integer getMethod(int n) {
		throw new RuntimeException
			("can't work with emptylist or out of bound");
	}


	FListInteger setMethod(int n, Integer y) {
		throw new RuntimeException("can't work with emptylist");
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

}


class Add extends FListInteger{
	FListInteger f;
	Integer x;

	Add(FListInteger f, Integer x){
		this.f = f;
		this.x = x;
	}

	public boolean isEmptyMethod() {
		return false;
	}


	public Integer getMethod(int n) {
		if (n == 0){
			return x;}
		else {
			return FListInteger.get(f, n - 1);}
	}


	public FListInteger setMethod(int n, Integer y) {
		if (n == 0){
			return FListInteger.add(this.f, y);}
		else {
			return 
				FListInteger.add(FListInteger.set(this.f, n - 1, y), this.x);}
	}


	int sizeMethod() {
		return 1 + FListInteger.size(f);
	}

	public boolean containsMethod(Integer y) {
		if (x.equals(y)){
			return true;}
		else {
			return FListInteger.contains(f, y);}
	}

	public String toString() {
		if (FListInteger.isEmpty(f)){
			return "[" + x.toString() + "]";}
		else {
			return "[" + x.toString() + ", "
				+  f.toString().substring(1, f.toString().length());}
	}


}
