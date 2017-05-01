package OCA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chap4_Encapsulation {
	public static void main(String... args) {

		// access modifier:
		// public / private/protected (pack+sub) / default (pack access)

		// order!
		// public final/static/abstract [synchronized] void methName(params)
		// throwx someException

		// Test aaa = new Test(); // DOES NOT COMPILE (abstract)

		// NEXT : p 191

		// ********************* TEST *********************
		List<String> list = Arrays.asList("a", "B", "d", "c");
		Collections.sort(list);
		String[] array = list.toArray(new String[4]);
		for (String string : array)
			System.out.print(string);
	}
}

abstract class Test {
	// can define protected abstract class
	protected abstract void doSmthgProtected();

	abstract void doSmthgDefault();

	public abstract void doSmthgPublic();

	// public void 2Bad() {} // DOES NOT COMPILE

	// note: others is an optional parameter
	public void withVarArgs(int start, int... others) {
		System.out.println(others.length);
	}

	// DOES NOT COMPILE (varArgs must be last and only one permitted)
	// public abstract void withVarArgs2(int... others, int start);

	public void $weirdName_$() throws IllegalArgumentException {
		int a = 3;
	}
}
