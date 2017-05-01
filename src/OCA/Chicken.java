package OCA;

class Chick {
	// Note: constructors have no return type
	// parameter-less constructor is provided by default
	public Chick() {
		System.out.println("in Chick Constructor");
	}
}

public class Chicken {
	int numEggs = 0; // on line initialization
	String name;
	// String comb2 = aa + bb; // DOES NOT COMPILE - order counts
	String aa = "aa";
	String bb = "bb";
	String comb = aa + bb; // order counts
	{
		System.out.println("instance initializer");
	}

	// ran after fields / instance initialiers
	public Chicken() {
		System.out.println("in Chicken constructor");
		name = "Duke"; // or in constructor
	}

	public static void main(String[] args) {
		Chicken duke = new Chicken();
		System.out.println(duke.comb);
	}
}