package packB;

import packA.ClassA;

public class ClassB extends ClassA { // override ok cross-package

	public void otherMeth2() {
		System.out.println("in ClassB.otherMeth2");
		this._prot = 3; //can't access priv
	}
	
}
