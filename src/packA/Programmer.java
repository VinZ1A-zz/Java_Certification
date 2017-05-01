package packA;

import java.util.ArrayList;
import java.util.List;

public class Programmer extends Writer {
	public static void write() {
		System.out.println("Writing code");
	}

	public static void main(String[] args) {
		Writer w = new Programmer();
		w.write(); // will show Writing... as this is a static method which
					// can't be overriden
	}

	// missing param in constructor of subclass
	class SuperClass {
		SuperClass(int x) {
			System.out.println("Super");
		}
	}

	public class SubClass extends SuperClass {
		SubClass() {
			super(10); // needed to compile
			System.out.println("Sub 2");
		}
	}

	public static class TestClass {
		public static void main(String[] args) {
			List<String> items = new ArrayList<>();
			items.add("Pen");
			items.add("Pencil");
			items.add("Box"); // will be printed
			for (String i : items) {
				if (i.indexOf("P") == 0) {
					continue;
				} else {
					System.out.print(i + " ");
				}
			}

			StringBuilder s1 = new StringBuilder("Java");
			String s2 = "Love";
			s1.append(s2); // modifies s1 --> JavaLove
			s1.substring(4); // doesn't actually do anything
			int foundAt = s1.indexOf(s2); // Love is 5th char in JavaLove
			System.out.println(foundAt);

		}
	}

	// 5 10 E1
	public static class Test {
		public static void main(String[] args) {
			int x = 10;
			int y = 2;
			try {
				for (int z = 2; z >= 0; z--) {
					int ans = x / z;
					System.out.print(ans + " ");
				}
			} catch (Exception e1) {
				System.out.println("E1");
			}
		}
	}

}