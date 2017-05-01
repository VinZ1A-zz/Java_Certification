package OCP;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class chap3_ListsSetsMapsAndQueues {
	@SuppressWarnings("unused")
	static public void main(String... args) {
		// List (ordered), Set, Queue implement Collection. Map does not.

		// Collection methods
		Set<String> set = new HashSet<String>();
		set.add("hey"); // returns true
		set.add("hey"); // returns false (no duplicates)
		// note: always returns true in an ArrayList

		// remove(xx) returns false when nothing is removed
		// remove() only one element at a time
		// remove(100) --> removes at index 100
		// !!! contains(xx) exists for Collection, not for Map

		// easy ones: isEmpty, size, clear, contains
		// contains calls equals()

		// ****** LIST interface ****** (ordered, dups ok)
		// ArrayList
		// lookup elem using index # : constant time
		// adding/removing is slower
		// others: linkedList (adds/remove from beg/end), Vector/Stack(old)
		// set(xx) returns previous element
		// indexOf(xx) goes through whole list (sorting does not improve anything)

		// ******* SET interface ****** (no dups)
		// HashSet -- uses hashcode to retrieve elem (add/check if exists = constant
		// time)
		// no guaranteed ordering
		// TreeSet - adding / checking is n log(n) (no hash usage)
		// implements also NavigableSet interface
		// sets returns true when effectively added (not already there)
		// lower(xx) --> greatest elem which is < xx or null
		// floor(xx) --> greatest element wich is <= xx or null
		// similarly: ceiling(xx) (smallest just before xx or equal), and higher(xx)

		// ******** QUEUE interface ******
		// FIFO (first in, first out) : OFFER/poll/peek (adds at back)
		// can be used to implement Stack (LIFO) : PUSH/poll/peek (adds to front)
		// (not : Stack exists but is slow - use ArrayDeque instead)
		// LinkedList : double ended queue (can enqueue at back or front). Both List
		// and Queue - can access index #
		// ArrayDeque: double ended queue (resizable and more efficient)
		// add() --> at back (QUEUE) - true or exception
		// element() --> what is next elem (QUEUE) or exception
		// offer() --> at back (QUEUE) - true or false
		// remove() --> remove and returns nxt elem (QUEUE) - exception if empty
		// PUSH() --> at front(STACK/french QUEUE)
		// poll() --> remove and returns nxt elem from FRONT (QUEUE/STACK) - null if
		// empty
		// peek() --> look at next eleme at BACK (QUEUE/STACK) (or null)
		// POP() --> nxt elem at back (STACK) - exception if empty

		Queue<Integer> aQueue = new ArrayDeque<>();
		aQueue.offer(10);
		aQueue.offer(4);
		print(aQueue.peek()); // 10 (first elem added)
		print(aQueue.poll()); // 10 removed
		print(aQueue.peek()); // 4 is next

		ArrayDeque<Integer> aStack = new ArrayDeque<>(); // no push impl.
		aStack.push(10);
		aStack.push(8);
		aStack.push(4);
		print(aStack.peek()); // 4 (last elem added)
		print(aStack.poll()); // 4 removed

		// ********** MAP interface **********
		// TreeMap (sorted, navigable): adding/checking is n log n
		// HashMap : adding/retrieving: constant time - no order
		// LinkedHashMap : same but order kept (less efficient)
		// Hashtable (similar to Vector for Lists): older / thread-safe /
		// inefficient
		// get() returns null if not present
		// put() returns previous value or null
		// remove() returns null if nothing removed
		// containsKey() / containsValue()
		// keySet() --> Set<> of keys
		// values() --> Collection<> of values (for TreeSet: ordered by Keys)

		// when is null NOT allowed?
		// when sorted: TreeSet, TreeMap (no null keys)
		// not in ArrayDeque (null has special meaning)
		// not in Hashtable : no null keys AND values. (who cares.)

		// comparator - comparable
		// not using compareTo() : local inner class - kludgy
		Comparator<zeData> dataComp = new Comparator<zeData>() {

			@Override
			public int compare(zeData o1, zeData o2) {
				return o1._data2.compareTo(o2._data2);
			}

		};

		// other way using lambda
		Comparator<zeData> quickComp = (o1, o2) -> o1._id - o2._id;
		Comparator<zeData> quickComp2 = (zeData o1, zeData o2) -> {
			return o1._id - o2._id;
		};
		// or, even cooler, with a helper:
		Comparator<zeData> quickComp3 = zeDataHelper::compareByID; // ok because
																																// static

		// multi-field comparator using Comparator chaining
		class ChainingComp implements Comparator<zeData> {

			@Override
			// first by data1 then by data2
			public int compare(zeData s1, zeData s2) {
				// or, java8-like:
				Comparator<zeData> c = Comparator.comparing(s -> s.getData1());
				c = c.thenComparing(s -> s.getData2());
				return c.compare(s1, s2);
			}

		}

		List<zeData> listData = new ArrayList<>();
		listData.add(new zeData("bb", "aa"));
		listData.add(new zeData("aa", "bb"));
		Collections.sort(listData);
		print(listData); // by data1 (using compareTo)
		Collections.sort(listData, dataComp);
		print(listData); // by data2 (using custom dataComp)

		// sort uses compare methods
		// Collections.sort(someList) --> needs elements to implement Comparable
		// or pass a Comparator

		// binarySearch needs to be done on a SORTED list
		// (according to the chosen compareMethod!).
		// otherwise, answer is not defined (probably -1).

		// cannot add anything there, exception thrown
		Set<Unsortable> sortedSet = new TreeSet<>(); // it does compile!
		sortedSet.add(new Unsortable().setId(3)); // EXCEPTION cannot be cast to
																							// java.lang.Comparable

		// implements a Set with a custom Comparator
		Set<Unsortable> sortedSet_WithComp = new TreeSet<>(new Comparator<Unsortable>() {

			@Override
			public int compare(Unsortable o1, Unsortable o2) {
				return o1._someId = o1._someId;
			}

		});
		sortedSet_WithComp.add(new Unsortable().setId(3)); // OK.

		// ************ JAVA8 stuff ***************
		// removeIf, forEach, merge, computeIfPresent, computeIfAbsent
		// link to sort method: (consumer: single param in input, void output)
		// Consumer: single param, no return
		Consumer<List<Integer>> methodRef1 = Collections::sort; // param is inferred
		// or
		Consumer<List<Integer>> lambda1 = l -> Collections.sort(l);

		// using Predicate (one param, outputs a boolean)
		String str = "abc";
		Predicate<String> methodRef2 = str::startsWith; // param at runtime from str
		Predicate<String> methodRef3 = String::isEmpty; // no param needed (hence,
																										// from String)
		// Supplier: no param, returns any type
		@SuppressWarnings("rawtypes")
		Supplier<ArrayList> methodRef4 = ArrayList::new; // constructor ref

		// removeIf gets a Predicate in input
		listData.removeIf(s -> s._id == 0); // can't be a method ref if contains
																				// external parameter
		listData.replaceAll(x -> new zeData(x._data1 + "baa", x._data2 + "bee"));

		listData.forEach(c -> print(c)); // provides Consumer
		// or
		listData.forEach(System.out::println);

		// Java8 MAP APIs : merge, putIfAbsent, computeIfPresent, computeIfAbsent
		// putIfAbsent : only adds in map if key not already present
		// merge: uses a BiFunction to put conditionnaly a key/value pair
		// depending on existing data (may keep value or not --> merge terminology)
		// BiFunction is only called when both values are not null (otherwise it's
		// just a put)
		// when mapper returns null, key is removed from the map (when mapper is
		// called)
		// XXXXX - add example

		// computeIfPresent:
		// performs map value change only if key is present, using BiFunction
		// otherwise doesnot do anything and returns null
		// XXXXX - add example

		// ComputeIfAbsent: performs and add key/value only when key is not already
		// present (or if value is null)
		// need only a Function (not BiFunction) as no reference to existing list
		// element is needed
		// if returns null, key is not added at all
		// XXXXX - add example
	}

	static class Unsortable {
		int _someId;

		Unsortable setId(int i) {
			_someId = i;
			return this;
		}
	}

	// used in a cool way when definining a Comparator
	static class zeDataHelper {
		public static int compareByID(zeData o1, zeData o2) {
			return o1._id - o2._id;
		}
	}

	// can also use a Comparator to sort differently than the compareTo provided
	// (can solve consistency with equals (keeps Natural Ordering)
	static class zeData implements Comparable<zeData> { // generics usage is
																											// optional

		private String _data1;
		private String _data2;
		private int _id;

		public zeData(String iData1, String iData2) {
			if (iData1 == null || iData2 == null) {
				throw new IllegalArgumentException();
			}
			_data1 = iData1;
			_data2 = iData2;
		}

		@Override
		public String toString() {
			return _data1 + "/" + _data2;

		}

		public String getData1() {
			return _data1;
		}

		public String getData2() {
			return _data2;
		}

		@Override
		// returns 0: equality - need to be consistent with equals!
		// returns <0 : current < argument
		// returns >0 : current > argument
		// null not taken into account here. Probaby needs to come first
		public int compareTo(zeData o) {
			// only using first data
			return _data1.compareTo(o._data1);

			// could also be:
			// return _id - o._id;

			// or: compare one field, return the comparison on the other field
			// if first comparison is not null

		}

		// need to do equals as well for consistency.

	}

	static void print(Object iStr) {
		System.out.println(iStr.toString());
	}
}
