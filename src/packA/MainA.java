package packA;

public class MainA {
	public static void main(String[] args) {
		ClassA aa = new ClassSubA();

		// aa.protMeth(); // ok to access protected because in same package
		aa.someMeth();
	}
}
