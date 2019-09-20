package designpatterns.iterator;

/**
 * 
 ������ģʽ��Iterator��
 * 
 * �ṩһ�ַ���˳�����һ���ۺ϶����и���Ԫ�أ����ֲ���¶�ö�����ڲ���ʾ�� ���:
 * 
 * ����1 �ǰ ����1 ��� ����12 ��� ����13 ��� ����14 ��� ����15 ���
 * 
 * 
 * 
 * 
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-11 ����6:58:11
 */
public class Zmain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConcreteAggregate a = new ConcreteAggregate();
		a.set(0, "����1");
		a.set(1, "����12");
		a.set(2, "����13");
		a.set(3, "����14");
		a.set(4, "����15");
		Iterator i = new ConcreteIterator(a);
		Object item = i.first();
		System.out.println(item + "  �ǰ");
		while (!i.isDone()) {
			System.out.println(i.currentItem() + "   ���");
			i.next();
		}

	}
}
