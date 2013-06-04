public abstract class FListInteger{
	abstract boolean isEmptyMethod();
	abstract int getMethod(Integer k);
	abstract FListInteger setMethod(Integer k, Integer d );
	abstract int sizeMethod();
	abstract boolean containsMethod(Integer d);


	public static FListInteger emptyList(){
		return new EmptyList();
	}

	public static FListInteger  add(FListInteger l, int d){
		return new Add(l, d);
	}

	public static boolean isEmpty(FListInteger l){
		return l.isEmptyMethod();
	}

	public static int get(FListInteger l, int k){
		return l.getMethod(k);
	}

	public static FListInteger set
		(FListInteger l, int k, int d){
			return l.setMethod(k, d);

		}

	public static int size(FListInteger l){
		return l.sizeMethod();
	}

	public static boolean contains(FListInteger l, int d){
		return l.containsMethod(d);
	}

	public String toString(){
		if (this.isEmptyMethod())
			return "[]";
		else  {Add l  = (Add) this;
			if (l.f.isEmptyMethod())
				return "[" + l.x.toString() + "]";
			else return "[" + l.x.toString() + ", "
				+  l.f.toString().substring(1, l.f.toString().length());}

	}

	public boolean equals(Object o){
		if (!(o instanceof FListInteger)  )
			return false;
		else  {FListInteger f2 = (FListInteger) o; 

			if (! (this.sizeMethod() == f2.sizeMethod()))
				return false;
			else{
				for (int i = 0; i < this.sizeMethod(); i++) {
					if
						(!(FListInteger.get(this, i)
						   ==(FListInteger.get(f2, i))))
							return false;
				}
				return true;
			}
		}
	}
	public int hashCode(){
		return this.sizeMethod();

	}
}

class EmptyList extends FListInteger{
	EmptyList(){}

	boolean isEmptyMethod(){
		return true;
	}

	int getMethod(Integer k){
		throw new RuntimeException("no such element");

	}

	FListInteger setMethod(Integer k, Integer d ){
		throw new RuntimeException("no");

	}

	int sizeMethod(){
		return 0;

	}

	boolean containsMethod(Integer d){
		return false;
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

	int getMethod(Integer k){
		if (k == 0)
			return x;
		else return FListInteger.get(f, k-1);

	}

	FListInteger setMethod(Integer n, Integer y ){
		if (n == 0)
			return FListInteger.add(f, y);
		else return
			FListInteger.add(FListInteger.set(f, n - 1, y), x);
	}

	int sizeMethod(){
		return 1 + FListInteger.size(f);

	}

	boolean containsMethod(Integer y){
		if ( x.equals(y))
			return true;
		else return FListInteger.contains(f, y) ;

	}




}
