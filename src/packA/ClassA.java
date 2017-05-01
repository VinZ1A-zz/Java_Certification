package packA;

public class ClassA {
	
	class ClassAB {
		private int _priv = 11;
		public int _pub = 12;
		protected int _prot = 13;
	}
	
	public ClassA() {
		System.out.println("in ClassA constr");
	}
	
	private int _priv = 1;
	public int _pub = 2;
	protected int _prot = 3;
	
	public void someMeth() {
		System.out.println("in ClassA.someMeth");
		ClassAB ab = new ClassAB();
		ab._pub = 3;
		ab._priv = 3;
		ab._prot = 3;
	}
}
