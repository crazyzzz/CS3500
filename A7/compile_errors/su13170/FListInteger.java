public abstract class FListInteger {

	final static int HASHSIZE = 100;  


	private static FListInteger theEmptyFList = new EmptyList();

	abstract boolean isEmptyMethod();

	abstract int getMethod( int indx );

	abstract FListInteger setMethod( int indx, Integer n );

	abstract int sizeMethod();

	abstract boolean containsMethod( Integer n );


	static FListInteger emptyList (){
		return theEmptyFList;
	}

	static FListInteger add ( FListInteger set, int n ){
		return new Add( set, n );
	}

	static boolean isEmpty( FListInteger set ){
		return set.isEmptyMethod();
	}

	static int get( FListInteger set, int indx ){
		return set.getMethod( indx );
	}

	static FListInteger set( FListInteger set, int indx, Integer n ){
		return set.setMethod( indx, n );
	}

	static int size( FListInteger set){
		return set.sizeMethod();
	}

	static boolean contains( FListInteger set, Integer n ){
		return set.containsMethod( n );
	}

	public boolean equals( Object o ){

		if ( o instanceof FListInteger ){

			FListInteger set = (FListInteger) o;

			if ( FListInteger.isEmpty(set) && FListInteger.isEmpty(this) ){
				return true;
			}else if ( FListInteger.size(set) == FListInteger.size(this) ){

				for ( int k = 0; k < FListInteger.size(set); k++ )
					if (FListInteger.get(set, k) != FListInteger.get(this, k))
						return false;

				return true;
			}	
		}

		return false;
	}



	private static class Add extends FListInteger {

		FListInteger set; 
		Integer        n;  

		Add( FListInteger set, Integer n){
			this.set = set;
			this.n = n;
		}

		boolean isEmptyMethod(){
			return false;
		}

		int getMethod( int indx ){
			if ( indx == 0 )
				return this.n;
			else if ( indx > 0 )
				return FListInteger.get(this.set, indx - 1);
			else
				throw new IllegalArgumentException("Index must be positive");
		}

		FListInteger setMethod( int indx, Integer n ){
			if ( indx == 0 )
				return FListInteger.add(this.set, n);
			else if ( indx > 0 )
				return FListInteger.add(
						FListInteger.set(this.set, indx - 1, n), this.n);
			else
				throw new IllegalArgumentException("Index must be positive");
		}

		int sizeMethod(){
			return 1 + FListInteger.size(this.set);
		}

		boolean containsMethod( Integer n ){
			if ( this.n.equals(n) )
				return true;
			else if( !this.n.equals(n) )
				return FListInteger.contains(this.set, n);
			else
				throw new IllegalArgumentException();
		}

		public String toString(){

			if ( FListInteger.isEmpty( this.set ) )
				return "[" + Integer.toString(this.n) + "]";
			else if ( !FListInteger.isEmpty( this.set ) )
				return "[" + Integer.toString(this.n) + ", "
					+ this.set.toString().substring(1, 
							this.set.toString().length());
			else 
				throw new IllegalArgumentException();

		}

		public int hashCode(){

			return ( this.n * this.set.hashCode() ) % HASHSIZE;

		}
	}

	private static class EmptyList extends FListInteger {

		EmptyList(){}

		boolean isEmptyMethod(){
			return true;
		}

		int getMethod( int n ){
			throw new IllegalArgumentException("Set is empty");
		}

		FListInteger setMethod( int indx, Integer n ){
			throw new IllegalArgumentException("Set is empty");
		}

		int sizeMethod(){
			return 0;
		}

		boolean containsMethod( Integer n ){
			return false;
		}

		public String toString(){
			return "[]";
		}

		public int hashCode(){

			return 0;

		}
	}

}
