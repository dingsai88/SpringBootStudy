package designpatterns.strategy;

/**
 * �̳г����� ʵ�ֳ��󷽷�
 * 
 * @author daniel
 * 
 */
class ConcreteStrategyA extends Strategy {
	public void AlgorithmInterface() {
		System.out.println("�㷨Aʵ��");
	}

}

class ConcreteStrategyB extends Strategy {
	public void AlgorithmInterface() {
		System.out.println("�㷨Bʵ��");
	}
}

class ConcreteStrategyC extends Strategy {
	public void AlgorithmInterface() {
		System.out.println("�㷨Cʵ��");
	}
}
