package designpatterns.prototype;

public class ConcretePrototype1 implements Prototype {
	@Override
	public Object Clone() {
		return (Prototype) new ConcretePrototype1();
	}
}
