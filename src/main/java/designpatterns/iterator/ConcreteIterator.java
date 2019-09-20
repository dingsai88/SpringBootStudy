package designpatterns.iterator;

/**
 * ������ʵ����
 * 
 * @author daniel
 * 
 */
public class ConcreteIterator extends Iterator {
	// �ۼ���
	private ConcreteAggregate aggregate;
	private int current = 0;

	/**
	 * ��ʼ������ۼ���
	 * 
	 * @param aggregate
	 */
	public ConcreteIterator(ConcreteAggregate aggregate) {
		this.aggregate = aggregate;
	}

	/**
	 * ��õ�ǰ��ֵ
	 */
	@Override
	public Object currentItem() {
 		return aggregate.get(current);
	}

	/**
	 * ���ص�һ��ֵ
	 */
	@Override
	public Object first() {
 		return this.aggregate.get(0);
	}

	/**
	 * �ж��Ƿ�����һ�� false ������һ��
	 */
	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return current >= aggregate.count() ? true : false;
	}

	/**
	 * ��һ������
	 */
	@Override
	public Object next() {
		Object ret = null;
		current++;
		if (current < aggregate.count()) {
			ret = aggregate.get(current);
		}

 		return ret;
	}

}