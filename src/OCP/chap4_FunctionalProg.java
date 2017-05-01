package OCP;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class chap4_FunctionalProg {
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] args) {
		// Functional interfaces (contains single abstract method)
		// ********* ALL OF THOSE MUST BE KNOWN!!! ****************
		// Supplier<T> : T get()
		Supplier<LocalDate> s1 = LocalDate::now; // using method reference
		Supplier<LocalDate> s2 = () -> LocalDate.now(); // using lambda
		Supplier<StringBuilder> sStrBld = StringBuilder::new;
		Supplier<ArrayList> sLst = ArrayList::new; // raw type
		Supplier<ArrayList<String>> sLstStr = ArrayList<String>::new;
		// displays OCP.chap4_FunctionalProg$$Lambda$5/455659002@eed1f14
		// $$ means that the class only exists in memory
		print(sLstStr);

		// Consumer<T> : void accept(T)
		// BiConsumer<T,U> : void accept(T,U)
		Consumer<String> c1 = System.out::println;
		Consumer<String> c2 = x -> System.out.println(x);
		Map<String, Integer> aMap = new HashMap<>();
		// Note: could be String,String
		BiConsumer<String, Integer> b1 = aMap::put; // method reference on instance
		BiConsumer<String, Integer> b2 = (k, v) -> aMap.put(k, v); // lambda

		// Predicate<T> : boolean test(T)
		// BiPredicate<T,U> : boolean test(T,U)
		Predicate<String> p1 = String::isEmpty;
		Predicate<String> isEgg = s -> s.contains("egg");
		Predicate<String> isBrown = s -> s.contains("brown");
		// Predicate ex4 = String::isEmpty; // DOES NOT COMPILE (Object by default)
		Predicate pObjDispEmpty = (x) -> x.toString().isEmpty(); // OK

		// can chain them
		Predicate<String> isBrownEgg = isEgg.and(isBrown);
		BiPredicate<String, String> p2 = String::startsWith;

		// Function<T,R> : R apply(T)
		// BiFunction<T,U,R> : R apply(T,U)
		// one or two type(s) into another type
		Function<String, Integer> f1 = String::length;
		BiFunction<String, String, String> f2 = String::concat;
		BiFunction<String, String, String> f2Bis = (str, toAdd) -> str.concat(toAdd);
		// can also create own function
		TriFunction<String, String, String, Integer> f3 = (str1, str2, str3) -> new String(str1 + str2 + str3).length();

		// UnaryOperator<T> : T apply(T)
		// BinaryOperator<T> : T apply(T,T)
		// transforms one or two T(s) into a different T
		UnaryOperator<String> u1 = String::toUpperCase;
		BinaryOperator<String> u2 = String::concat;

		// also: Runnable (no param, no return)...

		// use Functional interface
		LocalDate aDate = s1.get(); // supplier
		c1.accept("PrintThis"); // Consumer
		b1.accept("Chicken", 1);// BiConsumer
		print(aMap);
		print(p1.test("")); // true
		p2.test("chicken", "chick"); // true
		String res = new String();
		print(f2.apply("Ja", "Va")); // JaVa
		print(u1.apply("upper")); // UPPER
		print(u2.apply("vinz ", "ping"));

		// ************* Using Streams ************************
		// stream : sequence of data.
		// can apply a stream pipeline: operations on stream (eg. like assembly
		// line)
		// important notes: - steps (stream operations) depends on each other's
		// results
		// - Stream operations are finite
		// - data is generated when needed

		// can be source, intermediate operations, or terminal operations
		// intermediate ops: not required, can exist multiple times, return stream,
		// does not invalidate input stream
		// terminal ops: single one is required, does not return a stream, is
		// executed when called, invalidates input stream

		// example of workers: take signs out of box (source), paint them (interm.),
		// put sign in pile (terminal)
		// steps are: W1 -> W2 -> W3 (creates pile) -> W1 -> W2 -> W3 (adds in pile)

		// could add intermediate step: count nb of painted signs until 2 reached
		// intermediate could stop the line even before the source is emptied

		// ******** STREAM SOURCE *************
		Stream<String> empty = Stream.empty(); // count = 0
		Stream<Integer> oneElem = Stream.of(1); // count = 1
		Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 2
		List<String> listStrs = Arrays.asList("a", "b", "c");
		Stream<String> fromList = listStrs.stream();
		// not worth it for small lists (overhead)
		Stream<String> fromListParallel = listStrs.parallelStream(); // allows
																																	// parallel
																																	// processing

		// cannot be an infinite stream source anyway, just as many as needed
		// (see usage of limit() )
		Stream<Double> randoms = Stream.generate(Math::random);
		Stream<String> heapsOfMonkeys = Stream.generate(() -> "chimp");
		Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2); // 1: starting
																																// value (seed)

		// ***************** STREAM TERMINAL OPS *********************
		// Note: Required (unlike intermediate ops)
		// Reduces: combines result into single primitive or Object
		// count(): --> doesn't terminate, long, Reduces
		print("size of fromList: " + fromList.count());

		// min(), max() --> Doesn't terminate, Optional<T>, Reduces
		Stream<String> stream1 = Stream.of("monkey", "ape", "bonobo");
		Optional<String> min = stream1.min((ss1, ss2) -> ss1.length() - ss2.length());
		min.ifPresent(System.out::println); // ape is shortest
		// may not be present
		Optional<?> minEmpty = Stream.empty().min((x1, x2) -> 0);
		print(minEmpty.isPresent()); // false

		// findAny(), findFirst()
		// Terminates, Optional<T>, does not reduce (eg. not all elems are
		// processed)
		// ok on infinite streams (generates only one elem)
		Optional<Integer> anyOdd = oddNumbers.findAny();
		print(anyOdd.get()); // returns 1 (easiest, gets first one)

		// allMatch(), anyMatch() and noneMatch()
		// may terminate, returns boolean, Does not reduce
		Predicate<String> predIsFirstCharALetter = x -> Character.isLetter(x.charAt(0));
		List<String> combList = Arrays.asList("monkey", "2", "chimp");
		Stream<String> keepStream = combList.stream();
		print(keepStream.allMatch(predIsFirstCharALetter)); // false
		// closes stream !!!
		// java.lang.IllegalStateException: stream has already been operated upon or
		// closed
		// print(keepStream.anyMatch(predIsFirstCharALetter)); // true (1st
		// // used)
		// re-create stream every time!!
		print(combList.stream().noneMatch(predIsFirstCharALetter)); // false
		// noneMatch or allMatch shall not be used on an infinite stream

		// forEach() : Does not terminate, returns void (eg. not a reduction)
		// should not be used to filter elements
		// uses a Consumer
		combList.forEach(System.out::print); // monkey2chimp
		print("");
		// !!! could be also called on a Collection (this is a different method)
		Stream ofOne = Stream.of(1);
		// for (Integer i : ofOne) { // DOES NOT COMPILE (not Iterable)
		// }

		// reduce(): doesnt terminate, may or may not return smthg
		// combines stream into single object
		{
			Stream<String> wolfStream = Stream.of("w", "o", "l", "f");
			Stream<String> wolfStreamSame = Stream.of("w", "o", "l", "f"); // cannot
																																			// use
																																			// twice
			// with initial value XX
			String word = wolfStream.reduce("XX", (s, c) -> s + c);
			print(word); // XXwolf
			String word2 = wolfStreamSame.reduce("", String::concat);
			print(word2); // wolf
			Stream<Integer> someNumbers = Stream.of(2, 3, 4);
			print(someNumbers.reduce(1, (a, b) -> a * b)); // 24 = 2*3*4
			// if identity (1) is omitted we get an Optional[24]
			// when using no Identity, when no element, an empty Optional is returned
			Stream<Integer> emptyStrInt = Stream.empty();
			BinaryOperator<Integer> multiplier = (a, b) -> a * b;
			print(emptyStrInt.reduce(multiplier)); // Optional.empty
			// when one elem in Stream, element is returned

			// can also use reduce with one more func prog to deal with parallel
			// streams
			// xxxx - example to add
		}

		// collect() - mutable Reduction
		// more efficient than regular reduction
		// uses a Supplier and two BiConsumers, or a Collector
		{
			Stream<String> wolfStream = Stream.of("w", "o", "l", "f");
			// initializes with new, first BiConsumer is appending strings together,
			// second BiConsumer is appending results when working in parallel (using
			// smaller collections) : eg. using Supplier, Accumulator and Combiner
			StringBuilder word = wolfStream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
			print(word); // wolf

			Stream<String> wolfStream2 = Stream.of("w", "o", "l", "f", "w"); // note:
																																				// two
																																				// "w"'s
			TreeSet<String> set2 = wolfStream2.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
			print(set2); // [f, l, o, w] - ordered
			// easier to read using Collector:
			Stream<String> wolfStream3 = Stream.of("w", "o", "l", "f", "w");
			TreeSet<String> set3 = wolfStream3.collect(Collectors.toCollection(TreeSet::new));
			print(set3); // [f, l, o, w] - ordered
			// when no sorting is needed:
			Stream<String> wolfStream4 = Stream.of("w", "o", "l", "f", "w");
			Set<String> set4 = wolfStream4.collect(Collectors.toSet());
			// see specific topic on Collectors later
			print(set4); // for instance: [f, w, l, o] - unordered but still uniques
			// could be a hashSet but no guarantee
		}

		// ***************** STREAM INTERMEDIATE OPS *********************
		// returns (even infinite) stream - does not invalidate input(??)
		{
			// filter() - filters according to Predicate
			Stream<String> si = Stream.of("monkey", "gorilla", "bonobo");
			si.filter(x -> x.startsWith("m")).forEach(System.out::println); // monkey

			// distinct() - removes duplicates out of stream (does not need to be
			// adjacent) - uses equals().
			Stream<String> si2 = Stream.of("duck", "goose", "duck", "duck");
			si2.distinct().forEach(System.out::print); // duckgoose

			// limit(int maxsize) : limit size of stream and
			// skip(int n) : skips first n elements
			Stream<Integer> si3 = Stream.iterate(1, n -> n + 1);
			si3.skip(5).limit(2).forEach(System.out::print); // 67
			print("");

			// map() - maps each element to the return of the given Function
			Stream<String> si4 = Stream.of("monkey", "gorilla", "bonobo");
			// could also do x -> x.length() (a String is turned into an Integer)
			si4.map(String::length).forEach(System.out::print); // 676
			print("");

			// flatMap() - combines several streams into a single stream
			List<String> zero = Arrays.asList();
			List<String> one = Arrays.asList("Bonobo");
			List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
			Stream<List<String>> animals = Stream.of(zero, one, two);
			// stream of lists if we were executing this instead:
			// animals.forEach(System.out::print);// [][Bonobo][Mama Gorilla, Baby
			// // Gorilla]
			// this gets back BonoboMama GorillaBaby Gorilla
			animals.flatMap(l -> l.stream()).forEach(System.out::print);
			print("");

			// sorted() - using either natural ordering or Comparator
			Stream<String> si5 = Stream.of("brown-", "bear-");
			print("");
			si5.sorted().forEach(System.out::print); // bear-brown-
			// could also use Comparator
			Stream<String> si5b = Stream.of("brown-", "bear-", "zoubidou-");
			print("");
			// zoubidou-brown-bear-
			si5b.sorted(Comparator.reverseOrder()).forEach(System.out::print);
			print("");
			// Note: this does not work!! DOES NOT COMPILE ***************** TO KNOW
			// si5b.sorted(Comparator::reverseOrder); // have to use method itself,not
			// a reference

			// peek() - does not change the stream (debug)
			// uses Consumer
			Stream<String> si6 = Stream.of("black bear-", "brown bear-", "grizzly-");
			// peeks "grizzly-" while counting
			// absolutely nothing to do with peek() in a Dequeue!
			long count = si6.filter(s -> s.startsWith("g")).peek(System.out::println).count();
			print(count); // 1
			// Note: could potentially write peek() which tempers the data been looked
			// at,
			// eg. l -> l.remove(0) when manipulating a Stream of List references

			// **************chaining operations in pipeline **************
			{
				// much easier to read than dealing with lots of iterations and list
				// duplicates
				// Eg. "the 2 first alphabetically sorted names of length 4 in a list"
				Arrays.asList("Toby", "Anna", "Leroy", "Alex").stream()//
						.filter(n -> n.length() == 4) //
						.sorted() //
						.limit(2) //
						.forEach(System.out::println); // Alex Anna

			}
		}

		// up to page 225

	}

	// *********** using Optional (contains value or no value) *****************
	// advantages against null : API shows that there might not be a value
	static class OptionalShow {
		public static Optional<Double> average(int... scores) {
			if (scores.length == 0)
				return Optional.empty();
			double sum = 0;
			for (int score : scores)
				sum += score;
			// not great as we could have:
			// // average().get() --> //java.util.NoSuchElementException: No value
			// present
			return Optional.of(sum / scores.length);
		}

		public static void main(String... args) {
			print(average(91, 100)); // Optional[95.5]
			print(average()); // Optional.empty
			// get the value when it's there
			Optional<Double> opt = average(1, 3);
			if (opt.isPresent()) {
				print("yep we have a value : " + opt.get());
			}
			// ensures we create an empty Optional in case of null value
			Double value = null;
			Optional o = (value == null) ? Optional.empty() : Optional.of(value);
			// shortened to
			Optional o1 = Optional.ofNullable(value); // identical behavior through
																								// factory
			// other method: ifPresent(Consumer c) : calls c when Optional is not
			// empty
			opt.ifPresent(System.out::println); // no need for if()...
			// orElse(T other) : returns other when Optional is empty
			Optional<Double> emptyOpt = average();
			// note : orElseXXX would return the value if not empty!
			print(emptyOpt.orElse(Double.NaN)); // NaN // must be a Double
			// orElseGet(Supplier s) : returns result of call to Supplier when
			// Optional is empty
			print(emptyOpt.orElseGet(() -> Math.random())); // must return a Double
			// orElseThrow(Supplier s) : throws exception created by calling supplier
			try {
				print(emptyOpt.orElseThrow(() -> new IllegalStateException()));
			} catch (RuntimeException e) { // nothing done
			}
		}
	}

	// own functional interface
	interface TriFunction<T, U, V, R> {
		R apply(T t, U u, V v);
	}

	public static void print(Object obj) {
		System.out.println(obj.toString());
	}
}
