package designpatterns.factory;

/**
 * 
 * @author daniel
 * @version ���ģʽ�򵥹���
 */
public class Operation {

	private double numberA = 0;
	private double numberB = 0;

	/**
	 * @return the numberA
	 */
	public double getNumberA() {
		return numberA;
	}

	/**
	 * @param numberA
	 *            the numberA to set
	 */
	public void setNumberA(double numberA) {
		this.numberA = numberA;
	}

	/**
	 * @return the numberB
	 */
	public double getNumberB() {
		return numberB;
	}

	/**
	 * @param numberB
	 *            the numberB to set
	 */
	public void setNumberB(double numberB) {
		this.numberB = numberB;
	}

	/**
	 * ���㷽��
	 * 
	 * @return ������
	 */
	public double getResult() {
		double result = 0;
		return result;
	}

}