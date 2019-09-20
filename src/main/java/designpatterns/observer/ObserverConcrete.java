package designpatterns.observer;

/**
 * ������ʵ����
 * 
 * @author daniel
 * 
 */
public class ObserverConcrete extends Observer {
	private double agPrice;
	private String name;
	private SubjectConcrete subjectConcrete;// ������ʵ����

	/**
	 * ���� ������ �� �Լ���˽����Ϣ
	 * 
	 * @param subjectConcrete
	 * @param name
	 */
	public ObserverConcrete(SubjectConcrete subjectConcrete, String name) {
		this.subjectConcrete = subjectConcrete;
		this.name = name;
	}

	/**
	 * ��÷����� ����������
	 */
	@Override
	public void update() {
		agPrice = subjectConcrete.getAgPrice();
		System.out.println("�۲���" + name + " �۲쵽 ���¼۸��� " + agPrice);
	}

}