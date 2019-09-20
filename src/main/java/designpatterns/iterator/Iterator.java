package designpatterns.iterator;

/**
 * ������ ������
 * @author daniel
 *
 */
public abstract class Iterator {
 //���ص�һ������
 public abstract Object first();
 //��һ������
 public abstract Object next();
 //�Ƿ�����һ��
 public abstract boolean isDone();
 //��ǰ����
 public abstract Object currentItem();

}