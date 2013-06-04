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
		return new Add(f, x);
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

	public boolean equals(Object obj){
		if (obj == null)
			return false;
		if (!(obj instanceof FListInteger))
			return false;

		FListInteger that = (FListInteger) obj;
		if (size(this) != size(that))
			return false;

		for(int i = 0; i < size(this); i++){
			if (!get(this, i).equals(get(that, i)))
				return false;
		}
		return true;
	}

	public int hashCode(){
		if (isEmpty(this))
			return 0;
		else
			return (int)(get(this,0) * size(this));
	}
}


class EmptyList extends FListInteger{
	EmptyList(){};


	boolean isEmptyMethod(){
		return true;
	}

	Integer getMethod(int n){
		throw new RuntimeException("Index out of bounds.");
	}

	FListInteger setMethod(int n, Integer y){
		throw new RuntimeException("Index out of bounds.");
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
			return this.x;
		else
			return get(this.f, n-1);
	}

	FListInteger setMethod(int n, Integer y){
		if (n == 0)
			return add(this.f, y);
		else
			return add(set(this.f, n-1, y), this.x);
	}

	int sizeMethod(){
		return 1 + size(this.f);
	}


	boolean containsMethod(Integer y){
		return x.equals(y) || contains(f, y);
	}

	String toStringMethod(){
		if (isEmpty(f))
			return "[" + x.toString() + "]";
		else
			return "[" + x.toString() + ", " +
				f.toString().substring(1, f.toString().length());
	}
}

