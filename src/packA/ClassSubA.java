package packA;

public class ClassSubA extends ClassA {

	public ClassSubA() {
		// note: classA constr called before automatically
		super(); // does NOT call constr a second time
		System.out.println("in ClassSubA constr");
	}

	public void otherMeth() {
		System.out.println("in ClassSubA.otherMeth");
		this._prot = 3; // can't access private
	}

	@Override
	public void someMeth() {
		// super.someMeth(); // will not call parent class by default
		System.out.println("in ClassSubA.someMeth");
		this._prot = 4; // can't access private (only prot / pub)
	}

	protected void protMeth() {
		System.out.println("in ClassSubA.protMeth");
	}
}
