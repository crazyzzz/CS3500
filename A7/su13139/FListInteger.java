public abstract class FListInteger {

	abstract boolean isEmptyMethod();

	abstract Integer getMethod(int n);

	abstract FListInteger setMethod(int n, Integer y);

	abstract int sizeMethod();

	abstract int sumMethod();

	abstract boolean containsMethod(Integer y);

	public static FListInteger emptyList() {
		return new EmptyList();
	}

	public static FListInteger add(FListInteger f, Integer x) {
		return new Add(f,x);
	}

	public static boolean isEmpty(FListInteger f) {
		return f.isEmptyMethod();
	}

	public static Integer get(FListInteger f, int n) {
		return f.getMethod(n);
	}

	public static FListInteger set(FListInteger f, int n, Integer y) {
		return f.setMethod(n, y);
	}

	public static int size(FListInteger f) {
		return f.sizeMethod();
	}

	public static int sum(FListInteger f) {
		return f.sumMethod();
	}

	public static boolean contains(FListInteger f, Integer y) {
		return f.containsMethod(y);
	}

	public boolean equals(Object o) {
		if(o instanceof FListInteger) {  
			FListInteger l = (FListInteger) o;
			return (FListInteger.isEmpty(this) && FListInteger.isEmpty(l))
				|| (FListInteger.size (this) == FListInteger.size (l)
						&& this.listEquals(l));
		} else {
			return false;
		}
	}

	public boolean listEquals(FListInteger l) {
		for(int i = 0; i < FListInteger.size(this); i = i + 1 ) {
			FListInteger.get(this, i).equals(FListInteger.get(l, i));
		}
		return true;
	}

	public int hashCode() {
		return FListInteger.sum(this);
	}
}

class EmptyList extends FListInteger {
	EmptyList() {}

	boolean isEmptyMethod() {
		return true;
	}

	Integer getMethod(int n) {
		return 0;
	}

	FListInteger setMethod(int n, Integer y) {
		return new Add(FListInteger.emptyList(), y);
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

	int sumMethod() {
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

	boolean isEmptyMethod() {
		return false;
	}

	Integer getMethod(int n) {
		if(n == 0) {
			return x;
		} else {
			return FListInteger.get(f, n - 1);
		}
	}

	FListInteger setMethod(int n, Integer y) {
		if(n == 0) {
			return FListInteger.add(f, y);
		} else {
			return FListInteger.add(FListInteger.set(f, n - 1, y), x);
		}
	}

	int sizeMethod() {
		return 1 + FListInteger.size(f);
	}

	int sumMethod() {
		return x + FListInteger.sum(f);
	}

	boolean containsMethod(Integer y) {
		if (x.equals(y)) {
			return true;
		} else {
			return FListInteger.contains(f, y);
		}
	}

	public String toString() {
		if(FListInteger.isEmpty(f)) {
			return "[" + x.toString() + "]";
		} else {
			return "[" + x.toString() + ", "
				+  f.toString().substring(1, f.toString().length());
		}
	}
}
