package OCA;

// !! IN EXAM: if line nb starts with 1 : assume imports should be given in the code sample
//java.nio.* is not good : * matches only classes, not sub-packages (file)
// java.nio.*.* : NO - can have only 1 wildcard at the end
// java.nio.files.Paths.* : NO - cannot import methods
import java.nio.file.Files;
import java.nio.file.Paths;
// import a.* would only import classes, not child packages/fields/methods
//note: java.lang is automatically imported
//no need to import from current package OCA
import java.util.Random;

// java.util.Date would take precedence over the wildcard (eg. java.sql.*)

//to compile manually: javac Zoo.java using a JDK - added in PATH
// could be several java files
// generates bytecode in a .class file
// launch: java Zoo  - no need for .class - need JRE or JDK (eventually package name)
// with arguments : java Zoo Bronx Zoo - could use quotes
//can includes other JARs at runtime (not in subDirs!) using java -cp ".;someOtherLocation/aaa/bbb"
public class Zoo {
	String $; // yep $ are allowed.
	// public access modifier
	// could be String args[] or String... args

	public static void main(String[] args) {
		String nonInit;
		// may throw ArrayOutOfBoundsException
		// System.out.println("Welcome!" + args[0] + "," + args[1]);
		// System.out.println("Welcome!" + nonInit); // DOES NOT COMPILE
		System.out.println("Welcome!");

		int between0And9 = new Random().nextInt(10); // 0 incl, 9 excl

		Animal wolf = new Animal(); // no import needed
	}

	// illustrates package imports
	public void read(Files files) {
		Paths.get("name");
	}
}
