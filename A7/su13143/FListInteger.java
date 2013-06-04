public abstract class FListInteger {
	abstract boolean isEmptyMethod();
	abstract int sizeMethod();
	abstract boolean containsMethod(Integer n);
	abstract FListInteger setMethod(int pos, Integer n);
	abstract Integer getMethod(int pos);
	public static FListInteger emptyList() {
		return new emptyList();
	}
	public static FListInteger add(FListInteger l, Integer n) {
		return new add(l, n);
	}
	public static boolean isEmpty(FListInteger l) {
		return l.isEmptyMethod();
	}
	public static int size(FListInteger l) {
		return l.sizeMethod();
	}
	public static boolean contains(FListInteger l, Integer n) {
		return l.containsMethod(n);
	}
	public static Integer get(FListInteger l, int p) {
		return l.getMethod(p);
	}
	public static FListInteger set(FListInteger l, int p, int n) {
		return l.setMethod(p, n);
	}
}
class emptyList extends FListInteger {
	emptyList() {
	}

	boolean isEmptyMethod() {
		return true;
	}

	int sizeMethod() {
		return 0;
	}

	boolean containsMethod(Integer n) {
		return false;
	}

	Integer getMethod(int p) {
		return null;
	}

	FListInteger setMethod(int p, Integer d) {
		return this;
	}


	public String toString() {
		return "[]";
	}

	public boolean equals() {
		return false;
	}

	public int hashCode() {
		return 0;
	}
}

class add extends FListInteger {
	FListInteger l; 
	Integer n;

	add(FListInteger l, Integer n) {
		this.l = l;
		this.n = n;
	}

	boolean isEmptyMethod() {
		return false;
	}

	int sizeMethod() {
		return 1 + size(l);
	}

	boolean containsMethod(Integer n) {
		if (n.equals(this.n))
			return true;
		return contains(l, n);
	}

	Integer getMethod(int p) {
		if (p == 0)
			return n;
		return get(l, p - 1);
	}

	FListInteger setMethod(int p, Integer d) {
		if (p == 0)
			return add(l, d);
		return add(set(l, p - 1, d), n);
	}

	public String toString() {
		if (isEmpty(l))
			return "[" + n.toString() + "]";
		return "[" + n.toString() + ", "
			+ l.toString().substring(1, l.toString().length());
	}

	public boolean equals(Object obj) {
		boolean sameValues = true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		if (size(this) != size((FListInteger) obj))
			return false;

		for (int i = 0; i < size(this); i++) {
			if (get(this, i).equals(get((FListInteger) obj, i)) == false)
				sameValues = false;
		}

		return sameValues;

	}

	public int hashCode() {
		return (int) (n + l.hashCode());
	}

}
