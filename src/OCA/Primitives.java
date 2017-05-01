package OCA;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Primitives {

	boolean aBool; // defaults to false
	byte aByte; // 8 bits int eg. from -128 to 127 (because 0 is positive)
	short aShort; // 16 bits int
	int aInt; // 32 bits int // up to Integer.MAX_VALUE, > 2.1 billion
	long aLong; // 64 bits int
	float aFloat; // 32 bits floating-point eg 123.45f
	double aDouble; // 64 bits floating-point eg. 123.45
	char aChar; // 16 bits Unicode, defaults to '\u0000' (NUL)

	final String _finalStr = "needsInit"; // need init
	String _nonFinalStr;

	@SuppressWarnings("unused")
	// public static void main(String[] args) { // can also use varargs
	public static void main(String... args) {
		// float test = 123.12; // DOES NOT COMPILE - cannot convert double to
		// float
		float some32Float = 123.45f; // defines a litteral
		// int int32 = (long) 123; // DOES NOT COMPILE - cannot convert 64 bits
		// to
		// 32
		// can use either upper or lower case here
		long someLongNb = 3111222333L; // 3111222333 alone would NOT COMPILE
		int octal = 017;
		System.out.println(octal); // prints 15 in decimal
		int hexa = 0xFF;
		System.out.println(hexa); // prints 255 in decimal
		int binary = 0b110;
		System.out.println(binary); // prints 6 in decimal
		int aCoolMillion = 1_000_000; // makes it readable
		System.out.println("Coolest million: " + aCoolMillion);

		// primitives and references
		// int value = null; // DOES NOT COMPILE
		String s = null; // OK
		String s1 = new String("Hello"); // not using String pool!
		String s2 = "Hello"; // quickest way, using pool --> BETTER
		// s1 and s2 are accessing the same element in the pool of Strings
		int i1, i2, i3 = 0;
		// System.out.println("OUPS - not initialized : " + i1); // DOES NOT
		// COMPILE
		// int num, String value; // NOPE
		// i1;// DOES NOT COMPILE - need to do smthg with it

		// identifiers need to start by letter, $ or _
		// can contain also numbers, can't be reserved words (includes const and
		// goto!) - other tricky one: strictfp

		// local variables are not initialized by default
		int x;
		if (i3 == 0) {
			x = 3;
		}
		// System.out.println(x); // DOES NOT COMPILE (partially initialized)
		x = 3;
		int y = ++x * 5 / x-- + --x; // x-- returns new value while still
		// executing
		// left to right
		short sx = 3;
		short sy = 10;
		// Note: an explicit cast would simply indicate the compiler not to cast
		// to
		// int
		// (no cost) - and accep any eventual overflow/underflow
		// short sMult = sy * sx; // DOES NOT COMPILE (short converted to int as
		// soon as used)

		long xl = 5;
		int yi = 10;
		yi *= x; // OK since cast yi to long then cast result back to int

		// result of assignment is itself
		int weirdAssignment = (x = 3);

		int xx = 6;
		// xx is not incremented (first part is true and using short-circuit op)
		boolean res1 = (xx >= 6) || (++xx <= 7);

		if (5 == 5.0) {
			// yep that's true (left side promoted to double)
		}

		if (x < 15) {

		} else if (x < 11) { // unreachable but compiles
		}

		// do { // executes at least once
		// int x2 = 2;
		// } while (x2 < 10); // DOES NOT COMPILE

		// inifite loop by default, similar to for(;;)
		// for (int x3 = 2; ;) {
		//
		// }
		// int x4 = x3;// DOES NOT COMPILE

		// OK (both init vars must be of the same type
		// for (int x3 = 0, y3 = 0, z4 = 0 ; x3 < 10; y3++, x3 += 2) {
		//
		// }

		// labels
		int[][] array2D = { { 5, 2, 1, 3 }, { 3, 9, 8, 9 }, { 5, 7, 12, 7 } };
		OUTER_LOOP: for (int[] array1D : array2D) { // clearer for
			// breaks/continues
			if (array1D[2] >= 12) {
				break OUTER_LOOP; // useful if nested loops - not good practice
			}
		}

		// ***************** CHAP 3 ***************************
		Primitives aaa = new Primitives(); // class badly named ;)
		// aaa._finalStr = "yo"; // final String: ref can't be overwritten
		aaa._nonFinalStr = "yo"; // OK but creates new string

		String usingStringPool = "inZePool";
		String usingStringPoolAgain = "inZePool";
		String notUsingPool = new String("I know what Im doing");
		if (usingStringPool == usingStringPoolAgain) {
			System.out.println("equal because in the pool");
		}
		if (usingStringPool == new String(" inZePool".trim())) {
			System.out.println("WILL NOT BE CALLED (new string created)");
		}

		System.out.println(1 + 2 + "test" + 1 + 2); // 3test12 (evaluation from
		// left to right)

		String animals = "animals";
		// do not change original string!
		System.out.println(animals.substring(1, 4)); // nim (index 4 is NOT
		// reached)
		System.out.println(animals.replace('a', 'A')); // AnimAls
		// trim removes white space, \t, \r, \n

		// not mutable - not thread safe (as opposed to StringBuffer)
		StringBuilder aStr = new StringBuilder("hey").append("yo");
		aStr.insert(3, "-"); // insert AT index 3 (at the 'y')
		System.out.println(aStr);
		aStr.deleteCharAt(3); // deletes the '-'
		System.out.println(aStr);
		StringBuilder aStrAlloc = new StringBuilder(10); // allocates space!
		// (CAPACITY)

		Tiger t1 = new Tiger(); // does not have equals()
		Tiger t2 = new Tiger();
		if (t1.equals(t2)) {
			System.out.println("you need to implement EQUALS() for this");
		}
		if (t1 == t2) {
			System.out.println("nope. different references");
		}

		int[] numbers = new int[3]; // could be: int numbers[] with space also
		int[] numbers2 = { 1, 2, 3 }; // with init
		int[] numbers2Alias = numbers2;
		if (numbers2.equals(numbers2Alias)) { // does not look at elements
			System.out.println("yep - ref equality");
		}
		int numbers3[]; // points to null when not initialized

		// DOES NOT COMPILE
		// String attemptToCastFromStringBuilder = new StringBuilder("got me!");

		int[] numbersToSort = { 4, 7, 6, 1, 2, 3 };
		Arrays.sort(numbersToSort);
		// better than numz.toString() (garbage) : [1, 2, 3, 4, 6, 7]
		System.out.println(Arrays.toString(numbersToSort));

		int[] numz = { 2, 4, 6, 8 };
		System.out.println(Arrays.binarySearch(numz, 4)); // 1
		// (could have been between 2 and 3) --> should be inserted at 3
		// adds -1 for consistency
		System.out.println(Arrays.binarySearch(numz, 7)); // -4
		System.out.println(Arrays.binarySearch(numz, 1)); // -1
		System.out.println(Arrays.binarySearch(numz, 100)); // -5

		String[][] matrix2D = new String[3][2];
		// could be different sizes
		int[][] diffSizes = { { 1, 2 }, { 2 }, { 1, 2, 3 } };
		// second size allocated later
		int[][] varArgs = new int[2][];
		varArgs[0] = new int[5];
		varArgs[1] = new int[4]; // ! initialize all 2nd dims!

		for (int[] inner : varArgs) {
			for (int outer : inner) {
				// do smthg
			}
		}

		List<String> someList = new ArrayList<>(); // good practice
		someList.add("first");
		someList.add(1, "second");
		someList.add(2, "third");
		// someList.add(10, "wherever"); // index out of bounds Excep.
		System.out.println(someList); // [first, second, third]
		System.out.println(someList.remove("fourth")); // false
		someList.remove(0); // removes @ index 0 (!!! beware of list of
		// Integers!)
		System.out.println(someList); // [second, third]
		someList.remove("second");
		someList.remove("pas la"); // ok, just returns false
		System.out.println(someList); // [third]

		someList.set(0, "THIRD");
		someList.add(null); // also possible
		System.out.println(someList); // [THIRD, null]
		Object[] objArray = someList.toArray();
		String[] strArray = someList.toArray(new String[0]);
		System.out.println("size of converted list into Array : " + strArray.length); // 2

		// also: isEmpty(), size(), clear(), contains()
		// equals() : contains same elements in same order (invoke equals on
		// elements)

		Integer wrapper = Integer.valueOf("123");
		// hash : 366712642
		System.out.println("before call : " + System.identityHashCode(wrapper));
		changeInt(wrapper); // Integer is immutable
		System.out.println(wrapper); // 123 NOT CHANGED

		String[] arrStr = { "hello", "coucou", "salut" };
		// size change not allowed!
		List<String> arrStrToList = Arrays.asList(arrStr);
		System.out.println(arrStrToList); // [hello, coucou, salut]
		// arrStrToList.add("test"); // java.lang.UnsupportedOperationException
		arrStrToList.set(0, "g'day");
		// this changes also the array!!! ("backed array") - same data store
		System.out.println(Arrays.toString(arrStr)); // [g'day, coucou, salut]

		// cool creation of List with init (NOT IN OCA) - using varargs
		List<String> strList = Arrays.asList("one", "two");

		Collections.sort(arrStrToList);
		System.out.println(arrStrToList); // [coucou, g'day, salut]

		// *********** dates and time **************
		// from java.time.*
		print(LocalDate.now());// 2017-01-20
		print(LocalTime.now());// 14:07:30.575
		print(LocalDateTime.now());// 2017-01-20T14:07:30.575

		// Month.JANUARY is 1 (1 based!) - using static LocalDate::of() method
		LocalDate date1 = LocalDate.of(2017, Month.JANUARY, 20);

		// nanoseconds to 999.999.999 - seconds and nanoseconds are optional
		LocalTime time1 = LocalTime.of(6, 15, 30, 400);

		// can throw DateTimeException (eg. 32 January ect...)
		LocalDateTime dateTime1 = LocalDateTime.of(2017, Month.JANUARY, 20, 6, 15);
		// or
		dateTime1 = LocalDateTime.of(date1, time1);

		// LocalDate d = new LocalDate(); // DOES NOT COMPILE

		date1 = date1.plusDays(2); // !!!! does not change existing obj !!!!
		date1 = date1.plusWeeks(3); // ect..., could do minusX as well
		print(date1); // 2017-02-12
		// can chain eg. minusDays(1).minusHours(2). ect..

		// !!! can only change date fields in date or datetime objects
		// !!! can only change time fields in time or datetime objects

		LocalDate date2 = LocalDate.of(2017, Month.MARCH, 20);
		if (date1.isBefore(date2)) {
			print("JANUARY is before MARCH");
		}

		// can only use time with time-objs and date with date-objs
		Period period = Period.ofMonths(2);
		date1.plus(period);
		Period period2 = Period.of(1, 0, 7); // period of 1 year and 7 days
		// !!! pitfall! static method used!!
		Period oneWeekOnly = Period.ofDays(2).ofWeeks(1); // every week!!

		print(date1.getDayOfWeek()); // SUNDAY // ect...
		print(date1.getMonth()); // FEBRUARY (not MonthValue)

		// using formatting:
		print(date1.format(DateTimeFormatter.ISO_LOCAL_DATE)); // 2017-02-12
		DateTimeFormatter shortDT = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		print(shortDT.format(dateTime1)); // 1/20/17
		print(shortDT.format(date1)); // 2/12/17
		// UnsupportedTemporalTypeException !!!
		// print(shortDT.format(time1)); // 2/12/17

		// can also write: (equivalent)
		DateTimeFormatter mediumD = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		print(date1.format(mediumD));// Feb 12, 2017
		// ofLocalizedDate --> can only be used on Date or DateTime!
		// ofLocalizedTime --> can only be used on Time or DateTime!
		// ofLocalizedDateTime --> can only be used on DateTime!
		DateTimeFormatter mediumDT = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		print(dateTime1.format(mediumDT));// Jan 20, 2017 6:15:30 AM

		// own format:
		DateTimeFormatter ownFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
		print(dateTime1.format(ownFormat)); // January 20, 2017, 06:15
		// M(1), MM(01), MMM(Jan), MMM(January)
		// dd or d ; yy or yyyy;

		// note: formatter with only time stuff (eg. hh:m) will throw an
		// exception on a Date instance

		// parsing date
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate date = LocalDate.parse("01 02 2015", f);
		LocalTime time = LocalTime.parse("11:22"); // using default
	}

	// will NOT change value despite using wrapper
	static void changeInt(Integer iInt) {
		System.out.println("before change : " + System.identityHashCode(iInt)); // still
		// 366712642
		iInt = 7; // new (Immutable) Integer created! Hash = 1829164700
		System.out.println("after change : " + System.identityHashCode(iInt));
	}

	static void print(Object iStr) {
		System.out.println(iStr.toString());
	}
}

class Tiger {
	// NOT in OCA certif
	// public boolean equals(Object anObject) { //xxx }
}
