public abstract class FListInteger {

	abstract boolean isEmpty();

	abstract Integer get(int n);

	abstract FListInteger set(int n, Integer i);

	abstract int size();

	abstract boolean contains(Integer n);

	public static FListInteger emptyList() {
		return new EmptyList();
	}

	public static FListInteger add(FListInteger f, Integer n) {
		return new Add(f, n);
	}

	public static boolean isEmpty(FListInteger f) {
		return f.isEmpty();
	}

	public static Integer get(FListInteger f, int n) {
		return f.get(n);
	}

	public static FListInteger set(FListInteger f, int n, Integer i) {
		return f.set(n, i);
	}

	public static int size(FListInteger f) {
		return f.size();
	}

	public static boolean contains(FListInteger f, Integer n) {
		return f.contains(n);
	}

	public String toString() {
		return "[]";
	}

	public boolean equals(Object x) {
		if (x instanceof FListInteger) {
			FListInteger f = (FListInteger)x;
			if (FListInteger.isEmpty(this) && FListInteger.isEmpty(f)) {
				return true;
			} else if (size(this) == size(f)) {
				boolean output = true;
				for (int i = 0; i < size(this); i++) {
					if (! get(this, i).equals(get(f, i))) {
						output = false;
						break;
					}
				}
				return output;
			} else {
				return false;
			}
		} else {
			return false;
		} 
	}
}

class EmptyList extends FListInteger {

	EmptyList() {}

	boolean isEmpty() {
		return true;
	}

	Integer get(int n) {
		throw new RuntimeException("Integer not found");
	}

	FListInteger set(int n, Integer i) {
		throw new RuntimeException("Unable to set in an empty FListInteger");
	}

	int size() {
		return 0;
	}

	boolean contains(Integer n) {
		return false;
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

	boolean isEmpty() {
		return false;
	}

	Integer get(int n) {
		if (n == 0) {
			return this.x;
		} else {
			return get(this.f, n - 1);
		} 
	}

	FListInteger set(int n, Integer i) {
		if (n == 0) {
			return add(this.f, i);
		} else {
			return add(set(this.f, n - 1, i), this.x);
		}
	}

	int size() {
		return 1 + size(this.f);
	}

	boolean contains(Integer n) {
		if (x.equals(n)) {
			return true;
		} else 
			return contains(this.f, n);
	}

	public String toString() {
		if (isEmpty(this.f)) {
			return "[" + x.toString() + "]";
		} else {
			return "[" + x.toString() + ", " 
				+ this.f.toString().substring(1, this.f.toString().length());
		}
	}

	public int hashCode() {
		return this.size() + this.x + this.f.hashCode();
	}
}
