package designpatterns.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * ʵ�־ۼ���
 * 
 * @author daniel
 * 
 */
public class ConcreteAggregate extends Aggregate {
	private List<Object> items = new ArrayList<Object>();

	@Override
	public Iterator createIterator() {
		return new ConcreteIterator(this);
	}

	/**
	 * ����������
	 * 
	 * @return
	 */
	public int count() {
		return this.items.size();
	}

	/**
	 * ������� ��Ӧ��ֵ
	 * 
	 * @param index
	 * @return
	 */
	public Object get(int index) {
		return this.items.get(index);
	}

	/**
	 * ����������
	 * 
	 * @param index
	 * @param value
	 */
	public void set(int index, Object value) {
		this.items.add(index, value);
	}
}
