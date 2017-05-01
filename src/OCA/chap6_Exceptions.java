package OCA;

public class chap6_Exceptions {
	public static void main(String... args) {
		print("hey");

		// Errors
		// eg. // NoClassDefFoundError

		// java.lang.RuntimeExceptions are UNCHECKED exceptions
		// inherits from Exception, then Throwable
		// examples:
		// NullPointerException
		// ArrayIndexOutofBoundsException
		// ArithmeticException
		// ClassCastException
		// IllegalArgumentException / (? IllegalStateException)
		// NumberFormatException (thrown programmatically)

		// !!! unchecked exception (eg. Runtime) can be thrown from any method
		// without the need of a "throws" declarationS

		// checked exceptions: Exceptions which are NOT extended from
		// RuntimeException. Examples
		// FileNotFoundException
		// IOException

		// many catches, one finally
		// more restrictive catches first (sub classes ect) otherwise it does not
		// compile
		// need: at least either a finally or a catch

		// if exceptions thrown in finally AND a catch block, the one from finally
		// wins (the one in the catch block is forgotten)

		// System.exit(0) --> ends catch AND/OR finally straight away

		// cannot throw CHECKED exception in overridden method when parent does not
		// only allowed to declare fewer exceptions than parent method (subclass of
		// exception or no exception at all). note: does not apply to unchecked
		// exceptions

		// cannot catch an exception which would not be EVER thrown
		// (does not compile)
		// for instance an IOException out of a system.out.println()
	}

	static void print(Object iStr) {
		System.out.println(iStr.toString());
	}
}
