package OCA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chap3_CoreAPIs {
	public static void main(String... strings) {
		print(1 + 2 * 4 + "c"); // 9c - from left to right (still aplplying operator
														// priority)

		String str = "1";
		str = str + 2; // 12
		str.concat("3"); // still "12" (concat returns a new String -
											// immutability!!)
		print(str);

		String fromPoolA = "aaa";
		String fromPoolB = "aaa";
		String notFromPool = new String(fromPoolA);
		if (fromPoolA == fromPoolB) { // true
			print("both luckily from pool");
		}
		if (notFromPool == fromPoolB) { // false
			print("one of them out of pool");
		}

		// DOES NOT COMPILE
		// String thisShouldBeAChar = str.charAt(0); // need cast to String!!!

		// methods to know
		// length()
		// indexOf() (char or String) (-1 when not found)
		// substring() - goes to the char before the endIndex
		// equals - the only one reliable
		// contains() (true/false)
		// replace() (replaces ALL chars or Strings/StringBuilder)
		// they can all be chained

		StringBuilder strBld = new StringBuilder("smthg");
		StringBuilder strBldSame = strBld.append("else"); // mutability (also
																											// returned as HIMSELF (no
																											// obj
		// creation))
		if (strBld == strBldSame) {
			print("append returns same object ref");
		}
		// same methods : charAt, indexOf, length, substring , PLUS
		// append, insert, delete/deleteCharAt, reverse, toString
		// same methods for StringBuilder

		int aChar = 'a'; // yes, we can
		char aInt = 82;
		System.out.println(aInt); // prints 'R'

		Tiger t1 = new Tiger();
		Tiger t1Bis = new Tiger();
		Tiger t1Ref = t1;
		if (t1.equals(t1Ref)) // YES because same ref
			print("equals as well as == (ref comparison)");
		if (t1.equals(t1Bis)) // false
			print("equals() has been defined"); // not returned

		// ************************ arrays ****************************
		int[] array1 = new int[] { 1, 2, 3 };
		int[] array1Same = new int[] { 1, 2, 3 };
		int[] array2 = { 1, 4, 2 }; // shorter way
		int array3[]; // ok (with/without space)
		int[] oneDim, twoDim[]; // tricky & shitty
		int[] array1Ref = array1;
		if (array1.equals(array1Ref)) { // true
			print("equals on array DOES NOT compare elements, only REF");
		}
		if (array1.equals(array1Same)) { // false
			print("elements not compared");
		}

		// DOES NOT COMPILE
		// Object[] arrayObj = array1; // to Object is OK (unless primitive)

		String[] arrStr = { "1", "2", "3" };
		Object[] arrStrObj = arrStr; // OK for Objects refs
		String[] arrStrAgain = (String[]) arrStrObj; // OK
		// type checked
		// arrStr[0] = new StringBuilder("aa"); // DOES NOT COMPILE
		try {
			arrStrObj[0] = new StringBuilder("aa"); // EXCEPTION ARRAY STORE
		} catch (Exception e) {
			print(e); // java.lang.ArrayStoreException
		}

		Arrays.sort(array2); // 1,2,4
		// looking for 3
		print(Arrays.binarySearch(array2, 3)); // could have been at pos 2
		// --> -2 - 1 = -3

		// multi dim array
		int[][] partiallyInit = new int[4][];
		partiallyInit[0] = new int[5]; // others are still null

		// ARRAY LIST tricky stuffbgtSXZ
		// equals works when SAME elements in SAME ORDER
		Double aaa = 30d; // need the d !!
		print(aaa); // 30.0
		List<String> listStr = new ArrayList<>();
		listStr.add("1");
		listStr.add("2");
		Collections.sort(listStr);
		// FROM LIST TO ARRAY : myList.toArray
		Object[] objArray = listStr.toArray();
		objArray[0] = "hey";
		// creates with most appropriate size
		String[] strArray = listStr.toArray(new String[0]);
		// if size was 1 : other new Array created anyway (all elements copied over)
		for (String strElem : strArray) {
			print(strElem);
		}

		// from Array to List: Arrays.asList(myArray)
		// backed List - can't add/remove element !! (exception
		// UnsupportedOperation)
		List<String> backedLst = Arrays.asList(strArray);
		try {
			backedLst.add("NOPE!"); // java.lang.UnsupportedOperationException
		} catch (Exception e) {
			print(e);// java.lang.UnsupportedOperationException
		}
		backedLst.set(0, "overwrite"); // OK

		// cool trick - list of strings to ArrayList
		List<String> someArgs = Arrays.asList("one", "two");
	}

	static class Tiger {

	}

	public static void print(Object obj) {
		System.out.println(obj.toString());
	}
}
