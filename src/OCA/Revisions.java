package OCA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//generates as many files as there are classes !!

public class Revisions {
	// always, always, ALWAYS look for correct main() syntax (static? public? ...)
	public static void main(String... strings) {
		Object obj = new someClass(); // no cast nedded (to super class)
		try {
			// not cast to correct initial class --> exception
			someClassExt aa = (someClassExt) obj; // java.lang.ClassCastException:
																						// OCA.someClass cannot be cast to
																						// OCA.someClassExt
		} catch (Exception e) {
			print(e.getStackTrace());
		}

		List<someClassExt> aList = new ArrayList<>();
		aList.add(new someClassExt());
		for (someClass elem : aList) { // can use superclass
		}

		// Note: all string litterals are automatically instanciated into a String
		// object
		String str = "abcde";
		// a char can't be cast to a String (char is not a subclass)
		// String subStr = str.charAt(2); // DOES NOT COMPILE

		// TRUE:
		// The type of the object determines which properties exist within the
		// object in memory

		// TRY - CATCH is mandatory for CHECKED exception
		try {
			someClass.exceptionThing();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void print(Object obj) {
		System.out.println(obj.toString());
	}

}

interface someInt {
	int getSmthg(); // this is PUBLIC by default
}

class someClass implements someInt {

	@Override
	public int getSmthg() {// OK if public
		return 0;
	}

	// default visibility in classes = less than public!!!
	// int getSmthg() { // DOES NOT COMPILE - reduced visibility!!!!
	// return 0;
	// }

	// throws IllegalStateException would be an unchecked exception
	// (inherits from RuntimeException) --> would not have to be handled by caller
	public static void exceptionThing() throws IOException {

	}

	protected final void sneakyMeth() { // !!!! FINAL !!!
	}
}

class someClassExt extends someClass {
	// protected void sneakyMeth() { // cannot override final method
	// }
}
