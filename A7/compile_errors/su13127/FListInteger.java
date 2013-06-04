public abstract class FListInteger{

	abstract boolean isEmptyMethod();
	abstract Integer getMethod(int n);
	abstract FListInteger setMethod(int n, Integer y);
	abstract int sizeMethod();
	abstract boolean containsMethod(Integer y);
	abstract String toStringMethod();

	public static FListInteger emptyList(){
		return new EmptyList();
	}

	public static FListInteger add(FListInteger f, Integer x){
		return new Add(f,x);
	}

	public static boolean isEmpty(FListInteger f){
		return f.isEmptyMethod();
	}

	public static Integer get(FListInteger f, int n){
		return f.getMethod(n);
	}

	public static FListInteger set(FListInteger f, int n, Integer x){
		return f.setMethod(n,x);
	}

	public static int size(FListInteger f){
		return f.sizeMethod();
	}

	public static boolean contains(FListInteger f, Integer x){
		return f.containsMethod(x);
	}

	public String toString(){
		return this.toStringMethod();
	}

	public boolean equals(Object o){
		if (!(o instanceof FListInteger))
			return false;

		else if (this.isEmptyMethod())
			return ((FListInteger)o).isEmptyMethod();

		else if (size(this) == size((FListInteger)o)) {
			for (int i=0; i < size(this); i++) {
				if (!(get(this, i).equals(get(((FListInteger)o), i)))){
					return false;
				}
			}
			return true;
		}
		else
			return false; 
	}

	public int hashCode(){
		int x = 0.0;

		for (int i=0; i < size(this); i++) {
			x = x + get(this, i);
		}

		return ((int)x);
	}

}


class EmptyList extends FListInteger{

	EmptyList(){}

	boolean isEmptyMethod(){
		return true;
	}

	Integer getMethod(int n){
		throw new RuntimeException("Get of Empty List");
	}

	FListInteger setMethod(int n, Integer y){
		throw new RuntimeException("Set of Empty List");
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
}



class Add extends FListInteger{

	FListInteger f; 
	Integer x; 

	Add(FListInteger f, Integer x){
		this.f = f;
		this.x = x;
	}

	boolean isEmptyMethod(){
		return false;
	}

	Integer getMethod(int n){
		if (n == 0)
			return x;
		else
			return get(f, (n-1));
	}

	FListInteger setMethod(int n, Integer y){
		if (n == 0)
			return add(f, y);
		else
			return add(set(f, (n-1), y), x);
	}

	int sizeMethod(){
		return (1 + size(f));
	}

	boolean containsMethod(Integer y){
		if (x.equals(y))
			return true;
		else
			return contains(f,y);
	}

	String toStringMethod(){
		if (isEmpty(f))
			return "[" + x.toString() + "]";
		else
			return "[" + x.toString() + "," 
				+ f.toString().substring(1, f.toString().length());
	}
}
