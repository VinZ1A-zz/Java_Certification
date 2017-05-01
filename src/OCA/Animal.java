package OCA;

public class Animal {
	String name;

	public String getName() {
		return name;
		// int a = 2.3f; // can't convert float to int - DOES NOT COMPILE
		// int b = 2.3; // can't convert double to int - DOES NOT COMPILE

	}

	public void setName(String newName) {
		name = newName;
	}

	public void tests() {
		int a, b = 0; // a is not initialized
		if (b < 1) {
			// System.out.println(a); // a is not initialized, does not compile!!

			StringBuilder aStrBld = new StringBuilder(5); // capacity
			// StringBuilder aStrBld2 = new StringBuilder(5.3); // DOES NOT COMPILE
			// StringBuilder aStrBld3 = new StringBuilder(false); // DOES NOT COMPILE

			// String hey = new String(false); // does not compile
			// String hey = new String(5.4); // does not compile

			Integer aInt = 2;
			int aIntPrim = 4;
			Double aDbl = 3.2;
			double aDblPrim = 4.5;
			aDblPrim = aInt; // OK but woud not be ok with aDbl
			// aIntPrim = aDbl; // Does not compile
		}
	}
}

// only one public class with same name as file
class Animal2 {
}