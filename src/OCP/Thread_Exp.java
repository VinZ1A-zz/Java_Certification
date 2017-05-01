package OCP;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_Exp {

	public void repo_of_Code() {
		(new Thread(new DoSmthg(42))).start();
	}
	/// could also use executor service (to be completed)

}

// can also extend Thread --> (new DoSmthg(42).start())
class DoSmthg implements Runnable {

	private int _someData;

	public DoSmthg(int iIn) {
		_someData = iIn;
	}

	public void getSmthg() {
		Lock a = new ReentrantLock();
		try {
			Thread.sleep(100); // in ms
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// can use _someData

	}

}
