package OCP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class chap3_Generics {

	List<Object> _aaa = new ArrayList<>(); // yes we can create arrays of Objects
	Object[] _bbb = new Object[2]; // YES

	@SuppressWarnings("unused")
	public static void main(String... args) {
		Integer hey = 3;
		someGen<String> aa = new someGen<String>();

		aa.<Integer>getSmthg(hey);
		// or
		aa.getSmthg(hey); // compiler can figure out type of param

		List<? extends Flyer> flyers = new ArrayList<>();
		// flyers.add(new Goose()); // DOES NOT COMPILE
	}

	interface someInterface<T> {

	}

	static class Bird {
	}

	static class Sparrow extends Bird {

	}

	// upper-bounded generics are immutable!
	void upperBoundedWildcard() {
		List<? extends Bird> birds = new ArrayList<>();
		// birds.add(new Sparrow()); //DOES NOT COMPILE, becomes immutable
		// birds.add(new Bird());// DOES NOT COMPILE

		List<? extends Number> numList = new ArrayList<>();
		// numList.add(new Integer(3)); // DOES NOT COMPILE EITHER
		total(numList, new Integer(3));
	}

	interface Flyer {
	}

	static class HangGlider implements Flyer {
	}

	static class Goose implements Flyer {
	}

	// !! note usage of extends rather than implement!
	void upperBoundWithInterface(List<? extends Flyer> iFlyers) {
		// iFlyers.add(new Goose()); // DOES NOT COMPILE
	}

	public static long total(List<? extends Number> iNums, Number iNum) {
		// iNums.add(iNum); // DOES NOT COMPILE
		long count = 0;
		for (Number number : iNums) {
			count += number.longValue();
		}
		return count;
	}

	static void lowerBoundedExample() {
		List<String> listStrings = new ArrayList<String>();
		listStrings.add("yop");
		List<Object> someStuff = new ArrayList<Object>(listStrings);
		useLowerBounded(someStuff);
		useLowerBounded(listStrings);

		// tricky
		// means all objects of the same type, at least IOException
		List<? super IOException> exceptions = new ArrayList<Exception>();
		// exceptions.add(new Exception()); // DOES NOT COMPILE (could end up with a
		// list of Exceptions)
		exceptions.add(new IOException());
		exceptions.add(new FileNotFoundException()); // it is also an IOException
	}

	// can pass list of String or Object
	// anything that is a super class of String is OK.
	static void useLowerBounded(List<? super String> iList) {
		iList.add(new String("hey"));
	}

	static class someGen<T> implements someInterface<T> { // cannot mix T and U
		@SuppressWarnings("unused")
		void someMeth() {
			// T aaa = new T(); // DOES NOT COMPILE

			List<T> aaa = new ArrayList<>(); // yes
			// but
			// T[] someArray = new T[3]; // DOES NOT COMPILE
		}

		// need to declare <U> before method return type (U is NOT a T).
		protected <U> List<U> getSmthg(U iIn) {
			List<U> aaa = new ArrayList<>();
			aaa.add(iIn);
			return aaa;
		}

		// could not do: (return type is not a generic element
		// protected <U> <? extends U> getSmthgX(List<? extends U> iIn) {
		protected <U> U getSmthgX(List<? extends U> iIn) {
			return iIn.get(0);

		}
	}

}
