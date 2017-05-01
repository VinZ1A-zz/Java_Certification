import packA.ClassA;
import packA.ClassSubA;
import packB.ClassB;

public class zeMain {

	
	public static void main(String... args) {
		System.out.println("hey BB");
	}
	
	static void inheritanceTests() {

		ClassA aa = new ClassSubA();
		aa._pub = 10; // can't access _priv and _prot

		aa.someMeth();

		ClassB bb = new ClassB();
		bb.otherMeth2();
	}

}
