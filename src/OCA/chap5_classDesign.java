package OCA;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class chap5_classDesign {
	public static void main(String... args) {
		print("hey");

		// notes
		// interface does not inherit from Object
		// (otherwise multiple inheritance would be allowed)

		// to review: ORDER of calls (static, non static, constr blocks)
		// xxxxx

		StringBuilder aaa = new StringBuilder("test");
		String yo = "yo";
		// yo.concat(aaa); // DOES NOT COMPILE (no auto Cast!..)

		// overloading / overriding rules - write them ALL
		// *** OVERRIDING ***
		// happens WHEN : same input params (eg. signature)
		// at least or MORE ACCESSIBLE (!! beware when no access modifiers in child
		// class)
		// checked exception CANNOT be new or BROADER
		// return value is COVARIANT (same or subclass)
		// note : parent method can be called using super.XXX()

		// ***** overloading ******
		// different signatures (params)
		// complete freedom in return value, exceptions thrown

		// redeclaring private methods: (including static ones)
		// OK, but this is not an override
		// hiding static methods:
		// overriding rules still apply, PLUS :
		// either static in both parent AND child, or both NOT static.

		Zebra5 aZebra = new Zebra5();
		// illustrates STATIC method hiding (no polymorphism behavior here)
		aZebra.callAnimStatic3FromAnimal5(); // "from Animal"
		aZebra.callAnimStatic3FromZebra5(); // "from Zebra"

		// all ok
		int[] nums = new int[] { 1, 2, 3 };
		Object p = nums;
		int[] two = (int[]) p;
		System.out.println(10 / two[2]);

		short s = 123;
		log(s);
		log(25.0);
		takesDoubleOnly(500);

		String ss = "whatev";
		List listOne = new ArrayList();
		listOne.add(0.5);
		listOne.add(3.5);
		List listTwo = new ArrayList();
		listTwo.add(3.5);
		listTwo.add(0.5);
		// need to be same order
		if (listOne.equals(listTwo)) { // NOPE
			System.out.println("equals!");
		}
		System.out.println(listTwo);

		diffArgs(1);
		// would not compile if Integer & int... were used (ambiguous call)
		// diffArgs(null); // throes NullPointerException (null array)

		final int movieRating = 4;
		int badMovie = 0;

		switch (badMovie) {

		// not constant --> DOES NOT CoMPILE
		// case badMovie:
		// System.out.println("nooo 2 ");
		// break;
		// }

		case (int) 'a': // token invalid
		case 4:
		default:
			System.out.println("noooo");
		case 1 * 1: // compiles ok
			System.out.println("test");
		}

		StringBuilder aStrBld = new StringBuilder("s1");
		System.out.println("hey " + aStrBld);
		// System.out.println("cast attempt " + (String) aStrBld); // NOPE

		// int num = 3.0; // cannot convert double to int
		// float ten = 10d; // cannot convert double to float
		float ten = 10; // OK
		// int tenInt = 10f; // DOES NOT COMPILE
		int num = 3;
		short one = 1;
		// double double = 100; // DOES NOT COMPILE - reserved keyword
		int result = ((2 + num++) + 2) * 1;
		int result2 = ((2 + ++num) + 2) * 1;
		System.out.println(result); // 7 = 2 + 3 + 2 then num = 4
		System.out.println(result2); // 9 = 2 + 5 + 2

		// int hello = 1 ? 3 : 2; // DOES NOT COMPILE - need boolean
		Object hello = (num > 2) ? "yeo" : 2; // DOES NOT COMPILE - need boolean

		LocalDateTime aDate = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
		LocalDate aDateOnly = LocalDate.of(2015, 5, 10);
		DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:MM");
		System.out.println(aDate.format(f));
		aDate.minus(Period.of(1, 2, 3)); // does not change Date

		String aJava = " Java ";
		System.out.println(aJava.replace(" ", "") + "#");
		if (" Cafe " == aJava) { // OK
		}

		// no brackets and one line: ok
		// do
		// System.out.println("yo");
		// while (num > 2);
		String grades[] = { "1", "2" };
		String grades2[];
		grades2 = new String[] { "1", "2" };

	}

	static void print(Object iStr) {
		System.out.println(iStr.toString());
	}

	static void log(int i) {
		System.out.println("using int");
	}

	static void log(double i) {
		System.out.println("using double");
	}

	// ok with int
	static void takesDoubleOnly(double d) {

	}

	static void diffArgs(int... varArgs) {
		System.out.println("using varArgs");
	}

	static void diffArgs(int i) {
		System.out.println("using Integer");
	}

}

// notes from Exams
class Rodent {
	protected static Integer chew() throws Exception {
		chap5_classDesign.print("Rodent");
		return 1;
	}

	protected void chew2() {
	}

	private int aa;
	public String bb;
	private static int type;

	// ok, Bever is subclass of Object hence need explicit cast (but ok to cast)
	Beaver someBeaver = (Beaver) new Object();

	public static void main(String[] args) {
		System.out.println(type);
	}
}

class Beaver extends Rodent {
	// does not compile because chew() is not private in parent
	// (can't hide)
	// method in child is more accessible than in parent : OK
	// Runtime exception is subclass of Exception (unchecked exc) : OK
	// DOES NOT COMPILE
	// public Number chew() throws RuntimeException {
	// chap5_classDesign.print("Beaver");
	// return 2;
	// }

	private int aa; // hides aa from parent
	public String bb; // hides bb from parent
	// note: could still be accessed via super.bb

	String seq = "yo";
	// DOES NOT COMPILE: can't access non-static fields in static method
	// static {
	// seq += "c";
	// }

	public static void main(String[] args) {
		Rodent aRod = new Beaver();
		aRod.chew2();

	}

}

// all methods are by default : public abstract
// hence, can't be private, protected, or final
// note: when two interfaces contain same method with same signature but
// different return types:
// conflict --> can't implement both within the same class
// or abstract class or interface
abstract interface Herbivore { // abstract may be added
	// can't be private or protected / can't be abstract
	int amount = 10; // assumed to be public, static and final
	// public static String; // NEED TO BE INITIALIZED - DOES NOT COMPILE
	// for methods: assumed to be public abstract

	// either static with body
	// or abstract (default value) with no body
	// public static void eatGrass(); // DOES NOT COMPILE

	// use either default or static
	// public int chew() { // DOES NOT COMPILE
	// return 13;
	// }

	// static methods need a body
	public static void doNothing() {
	}

	// static methods need a body
	// not "inherited" when implemented by a class
	// need full name to invoke
	public static void aStaticMethodInInterface() {
	}

	// DOES NOT COMPILE
	// protected void testProtected() { // NOT OK in an INTERFACE
	// (unless inner interface)
	//
	// }

	// public by default, can't be protected or private
	// default method canot be static, final abstract
	default void defMeth() {
	}

	default void defMeth2() {
	}
}

interface VerySimilarHerbivore {
	// same name, signature, ect
	public static void aStaticMethodInInterface() {
	}

	// introduces a conflict with a default method in Herbivore
	// when implemented in a class
	// solution: implement method
	// void defMeth2();

}

// Note: ok to have a method with same signature in both Interfaces
// does not behave like default methods.
class implementsInterface implements Herbivore, VerySimilarHerbivore {
	// static is ok too
	public int amount = 20; // hiding (even final/static/public) variables is ok

	// can hide previously defined static methods
	public static String doNothing() {
		Herbivore.aStaticMethodInInterface(); // need to use explicitely
																					// Herbivore.XXX
		VerySimilarHerbivore.aStaticMethodInInterface();
		return "yo";
	}

	// cannot be public (or 'nothing eg. package protected)
	// need to be void (override)
	@Override
	public void defMeth() {
	}

}

// cannot be private, protected or final
abstract class LivingThing {
	// DOES NOT COMPILE - cannot be ABSTRACT & FINAL
	// final abstract void doSmthg();

	protected int aaa = 5;
	private int bbb = aaa; // OK
	public int unDeclared; // OK (not in an interface)

	// abstract meth cannot have body
	// public abstract void methWithBody() {} // DOES NOT COMPILE

	// cannot have an ABSTRACT PRIVATE method
	// private abstract void secretMeth(); // DOES NOT COMPILE

	// body is ok in abstract class
	protected void testProtected() { // protected is OK in an ABSTRACT CLASS

	}

	// public int doSMthg2(); // DOES NOT COMPILE (no body)
}

// abstract class cannot be final
// public final abstract class uselessAbstractClass { // DOES NOT COMPILE
// }

// Note: super() always called first when chaining constructors (Primate -> Ape
// -> Chimp)
class Animal5 {
	// no default constr exists (eg. no param)
	public Animal5(int param) {
	}

	private static void animStatic() {
	}

	static void animStatic2() {
	}

	public static String animStatic3() {
		return ("from Animal5");
	}

	public void callAnimStatic3FromAnimal5() {
		System.out.println(animStatic3());
	}

	public final void someFinalMeth() {

	}
}

class Zebra5 extends Animal5 {

	static final String species; // need assignement
	// block NEEDS to be static
	static { // or can be done in a static block
		species = "Raptor";
	}

	public Zebra5(int param) {
		super(param); // NEEDED as no default constr in parent
		// eg. super() would not invoke anything

	}

	public Zebra5() {
		// super(5); // OK - need to be first statement
		// or:
		this(5); // same rule

	}

	// okay, redeclares PRIVATE method
	// doesn't matter if it is a static one
	protected static String animStatic() {
		return "hey";
	}

	// would still be ok as method is private in parent
	// protected String animStatic() {
	// return "hey";
	// }

	// does not compile (method is static in parent)
	// protected String animStatic2() { // DOES NOT COMPILE
	// return "hey";
	// }

	public static String animStatic3() { // DOES NOT COMPILE
		return ("from Zebra5");
	}

	public void callAnimStatic3FromZebra5() {
		System.out.println(animStatic3());
	}

	// DOES NOT COMPILE AS FINAL (would be ok if private)
	// public void someFinalMeth() {
	//
	// }

}

interface CanFly {

	void fly();
}

interface HasWings {
	public abstract Object getWindSpan();
}

// does not have to implement methods as this is an abstract class
abstract class Falcon implements CanFly, HasWings {
	// abstract cannot contain a body
	// public abstract void plunge() {}; // DOES NOT COMPILE
}

class Bird extends Falcon {

	@Override
	public void fly() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getWindSpan() {
		// TODO Auto-generated method stub
		return null;
	}

}

class SomeReturn {

}

class SomeSubReturn extends SomeReturn {

}

interface Aquatic {
	public default SomeReturn getNumberOfGills(int input) {
		return new SomeReturn();
	}
}

class ClownFish implements Aquatic {
	// ok, as overload
	public String getNumberOfGills() {
		return "4";
	}

	// invalid override, String is not covariant with int
	// eg. Object is not a subclass of SomeReturn
	// public Object getNumberOfGills(int input) { // DOES NOT COMPILE
	// return new Object();
	// }
	@Override
	public SomeSubReturn getNumberOfGills(int input) {
		return new SomeSubReturn();
	}
}

abstract class Bird2 {
	private void fly() {
		System.out.println("Bird flying");
	}

	public static void main(String[] args) {
		Bird2 bird = new Pelican();
		bird.fly(); // "bird flying"
		// Note: would display "Pelican flying" if fly() was protected in parent
		// class
		// Bird2
	}
}

class Pelican extends Bird2 {
	protected void fly() {
		System.out.println("Pelican Flying");
	}
}
