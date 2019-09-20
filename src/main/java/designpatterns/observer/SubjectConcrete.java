package designpatterns.observer;

/**
 * ���������� ���뷢���ľ������� �����۸�
 * 
 * @author daniel
 * 
 */
public class SubjectConcrete extends Subject {
	// �����۸�
	private double agPrice;

	/**
	 * @return the agPrice
	 */
	public double getAgPrice() {
		return agPrice;
	}

	/**
	 * @param agPrice
	 *            the agPrice to set
	 */
	public void setAgPrice(double agPrice) {
		this.agPrice = agPrice;
		this.Notify();
	}

}